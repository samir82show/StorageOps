package controller;

import entity.StorageForm;
import entity.StorageFormFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "controller")
@RequestScoped
public class Controller implements Serializable {

    @EJB
    private StorageFormFacade storageFormFacade;
    @Inject
    private StorageForm storageForm;

    private String result;

    @PostConstruct
    public void init() {
        storageForm.setLastUpdatedDate(new Date());
        storageForm.setCreatedBy("admin");
        storageForm.setStatus("New");
    }


    public StorageForm getStorageForm() {
        return storageForm;
    }


    public String getResult() {
        return result;
    }

    public String findRequestNo() {
        String str = "";
        for (String s : storageFormFacade.findRequests()) {
            str += s + ",";
        }
        return str.replaceAll(",$", "");
    }

    public void fetchForm() {
        StorageForm tempForm = storageFormFacade.find(storageForm.getRequestNo());
        if (tempForm == null) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request " + storageForm.getRequestNo() + " is not found."));
            result = "Request " + storageForm.getRequestNo() + " is not found.";
        } else {
            storageForm = tempForm;
        }
    }

    public void editForm() {
        storageFormFacade.edit(storageForm);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request " + storageForm.getRequestNo() + " is edited."));

    }

    public void delete(StorageForm storageForm) {
        storageFormFacade.remove(storageForm);
    }
        
    public List<StorageForm> listStorageForms() {
        return storageFormFacade.findAll();
    }

}
