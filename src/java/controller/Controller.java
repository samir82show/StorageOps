package controller;

import entity.Employee;
import entity.EmployeeFacade;
import entity.StorageForm;
import entity.StorageFormFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "controller")
@RequestScoped
public class Controller implements Serializable {

    @EJB
    private StorageFormFacade storageFormFacade;
    @EJB
    private EmployeeFacade employeeFacade;
    @Inject
    private StorageForm storageForm;
    @Inject
    private Employee employee;

    @PostConstruct
    public void init() {
        storageForm.setITSMRequestNo("rf11111");
        storageForm.setComments("my comment");
        storageForm.setExpectedGrowth(10);
        storageForm.setSizeInGB(30);
        storageForm.setOwnerEmail("asdasdasd@sidra.org");
        storageForm.setShareName("testshare");
        storageForm.setTargetHosts("asdasdasdasd");
        storageForm.setTeamEmail("asdasd@sidra.org");
        storageForm.setShareType(1);
    }
    
    
    public StorageForm getStorageForm() {
        return storageForm;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void createForm() {
        storageFormFacade.create(storageForm);
    }

    public void createEmployee() {
        employeeFacade.create(employee);
    }

}
