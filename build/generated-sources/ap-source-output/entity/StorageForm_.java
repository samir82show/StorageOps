package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-02-21T16:28:05")
@StaticMetamodel(StorageForm.class)
public class StorageForm_ { 

    public static volatile SingularAttribute<StorageForm, Date> lastUpdatedDate;
    public static volatile SingularAttribute<StorageForm, String> comments;
    public static volatile SingularAttribute<StorageForm, String> targetHosts;
    public static volatile SingularAttribute<StorageForm, Integer> size;
    public static volatile SingularAttribute<StorageForm, String> createdBy;
    public static volatile SingularAttribute<StorageForm, String> teamEmail;
    public static volatile SingularAttribute<StorageForm, String> shareName;
    public static volatile SingularAttribute<StorageForm, String> requestNo;
    public static volatile SingularAttribute<StorageForm, String> shareType;
    public static volatile SingularAttribute<StorageForm, Integer> expectedGrowth;
    public static volatile SingularAttribute<StorageForm, String> ownerEmail;
    public static volatile SingularAttribute<StorageForm, String> status;

}