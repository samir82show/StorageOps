package entity;

import entity.util.Helper;
import entity.util.JsfUtil;
import entity.util.PaginationHelper;
import facade.GroupsFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

@Named("groupController")
@SessionScoped
public class GroupController implements Serializable {

    private boolean loggedIn = false;
    private Groups current;
    private DataModel items = null;
    @EJB
    private facade.GroupsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private String groups;
    private String groupName;
    private String username;
    private String password;

    @PostConstruct
    public void init() {
        System.out.println("loggedIn................... " + loggedIn);
//        loggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void verifyLoggedIn() throws IOException {
        if(!loggedIn) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public GroupController() {
    }

    public Groups getSelected() {
        if (current == null) {
            current = new Groups();
            selectedItemIndex = -1;
        }
        return current;
    }

    public String getGroups() {
        return groups;
    }

    private GroupsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Groups();
        selectedItemIndex = -1;
        return "Create";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String login() {
        Helper helper = new Helper();
        Hashtable<String, String> env = helper.ldapConnect("ldap://mv3wcddc01prv.smrc.sidra.org:389/DC=smrc,DC=sidra,DC=org", "simple", "sawad@smrc.sidra.org",
                "Shadow!123");
        DirContext context = null;
        try {
            context = new InitialDirContext(env);
        } catch (AuthenticationNotSupportedException ex) {
            System.out.println("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
            System.out.println("incorrect password or username");
        } catch (NamingException ex) {
            System.out.println("error when trying to create the context");
        }

        for (Groups g : ejbFacade.findAll()) {
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String nameFilter = "(&(sAMAccountName=sawad)(objectclass=person))";
            String name = null;
            try {
                NamingEnumeration<SearchResult> answer = context.search("", nameFilter, searchControls);
                while (answer.hasMoreElements()) {
                    SearchResult searchResult = answer.next();
                    name = searchResult.getAttributes().get("cn").get(0).toString();
                }
            } catch (NamingException ex) {
                System.out.println(ex.getMessage());
            }

            String filter = "(&(sAMAccountName=SG-StorageAdmin)(objectclass=group))";
            try {
                NamingEnumeration<SearchResult> answer = context.search("", filter, searchControls);
                while (answer.hasMoreElements()) {
                    SearchResult searchResult = answer.next();
//                System.out.println(searchResult.getAttributes());
                    NamingEnumeration<?> values = searchResult.getAttributes().get("member").getAll();
                    while (values.hasMore()) {
                        String sr = (String) values.next();
                        if (sr.contains(name)) {
                            loggedIn = true;
                            FacesContext.getCurrentInstance().getExternalContext().redirect("list_all.xhtml");
//                            return "list_all";
                        } else {
                            System.out.println("Login error");
                        }
                    }
                }
            } catch (NamingException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(GroupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String create() {
        Helper helper = new Helper();
        Hashtable<String, String> env = helper.ldapConnect("ldap://mv3wcddc01prv.smrc.sidra.org:389/DC=smrc,DC=sidra,DC=org", "simple", "sawad@smrc.sidra.org", "Shadow!123");
        DirContext context = null;
        try {
            context = new InitialDirContext(env);
        } catch (AuthenticationNotSupportedException ex) {
            System.out.println("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
            System.out.println("incorrect password or username");
        } catch (NamingException ex) {
            System.out.println("error when trying to create the context");
        }
        SearchControls searchControls = new SearchControls();
        String filter = "(&(sAMAccountName=" + current.getName() + ")(objectclass=group))";
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        try {
            NamingEnumeration<SearchResult> answer = context.search("", filter, searchControls);
            if (!answer.hasMoreElements()) {
                JsfUtil.addErrorMessage(null, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                return null;
            }
            while (answer.hasMoreElements()) {
                SearchResult searchResult = answer.next();
                String group = searchResult.getAttributes().get("cn").get(0).toString();
                System.out.println("fdasdfsadfsadf");
                if (group.equalsIgnoreCase(current.getName())) {
                    System.out.println("This storage group is found");
                    getFacade().create(current);
                    JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupCreated"));
                } else {
                    System.out.println("This storage group is not availble");
                }
            }
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
        return prepareCreate();

    }

    public String prepareEdit() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Groups) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GroupDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Groups getGroup(java.lang.Long id) {
        return ejbFacade.find(id);

    }

    @FacesConverter(forClass = Groups.class)
    public static class GroupControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GroupController controller = (GroupController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "groupController");
            return controller.getGroup(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Groups) {
                Groups o = (Groups) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Groups.class.getName());
            }
        }

    }

}
