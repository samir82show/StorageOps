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

    @PostConstruct
    public void init() {
//        storageForm.setComments("my comments");
//        storageForm.setExpectedGrowth(3445);
//        storageForm.setITSMRequestNo("rf123234");
//        storageForm.setOwnerEmail("aaaaa@sidar.org");
//        storageForm.setTeamEmail("aaaaa@sidar.org");
//        storageForm.setShareName("share name");
//        storageForm.setShareType("NFS");
//        storageForm.setSizeInGB(4433);
//        storageForm.setTargetHosts("1123...3434..345345");
        storageForm.setRequestDate(new Date());
        storageForm.setStatus("New");
    }

    public StorageForm getStorageForm() {
        return storageForm;
    }

    public void createStorageForm() {
        if (storageFormFacade.find(storageForm.getITSMRequestNo()) == null) {
            storageFormFacade.create(storageForm);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request form for " + storageForm.getShareName() + " submitted."));
            storageForm.setComments(null);
            storageForm.setExpectedGrowth(0);
            storageForm.setITSMRequestNo(null);
            storageForm.setOwnerEmail(null);
            storageForm.setTeamEmail(null);
            storageForm.setShareName(null);
            storageForm.setShareType(null);
            storageForm.setSizeInGB(0);
            storageForm.setTargetHosts(null);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request " + storageForm.getITSMRequestNo() + " already exists."));
        }
    }

//    public String findByShareName() {
//        String str = "";
//        for (String s : storageFormFacade.findByShareName()) {
//            str += s + ",";
//        }
//        System.out.println("shares: " + str);
//        return str.replaceAll(",$", "");
//    }

    public void fetchForm() {
        StorageForm tempForm = storageFormFacade.find(storageForm.getITSMRequestNo());
        if (tempForm == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request " + storageForm.getITSMRequestNo() + " is not found."));
        } else {
            storageForm = tempForm;
        }
//        storageForm = storageFormFacade.find("rf11112");
        System.out.println("form is: " + storageForm.getITSMRequestNo());
    }

    public List<StorageForm> listStorageForms() {
        return storageFormFacade.findAll();
    }

}
