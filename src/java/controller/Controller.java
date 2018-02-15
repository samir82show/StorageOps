package controller;

import entity.StorageForm;
import entity.StorageFormFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
//        storageForm.setShareType(1);
//        storageForm.setSizeInGB(4433);
//        storageForm.setTargetHosts("1123...3434..345345");
    }

    public StorageForm getStorageForm() {
        return storageForm;
    }

    public void createStorageForm() {
        storageFormFacade.create(storageForm);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Request form for " + storageForm.getShareName() + " submitted."));
        storageForm.setComments(null);
        storageForm.setExpectedGrowth(0);
        storageForm.setITSMRequestNo(null);
        storageForm.setOwnerEmail(null);
        storageForm.setTeamEmail(null);
        storageForm.setShareName(null);
        storageForm.setShareType(0);
        storageForm.setSizeInGB(0);
        storageForm.setTargetHosts(null);
    }
    
    public List<StorageForm> listStorageForms() {
        return storageFormFacade.findAll();
    }

}
