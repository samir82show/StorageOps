package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "storage_forms_temp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorageForm.findAll", query = "SELECT s FROM StorageForm s")
    , @NamedQuery(name = "StorageForm.findByITSMRequestNo", query = "SELECT s FROM StorageForm s WHERE s.iTSMRequestNo = :iTSMRequestNo")
    , @NamedQuery(name = "StorageForm.findByShareType", query = "SELECT s FROM StorageForm s WHERE s.shareType = :shareType")
    , @NamedQuery(name = "StorageForm.findByShareName", query = "SELECT s FROM StorageForm s WHERE s.shareName = :shareName")
    , @NamedQuery(name = "StorageForm.findBySizeInGB", query = "SELECT s FROM StorageForm s WHERE s.sizeInGB = :sizeInGB")
    , @NamedQuery(name = "StorageForm.findByTargetHosts", query = "SELECT s FROM StorageForm s WHERE s.targetHosts = :targetHosts")
    , @NamedQuery(name = "StorageForm.findByOwnerEmail", query = "SELECT s FROM StorageForm s WHERE s.ownerEmail = :ownerEmail")
    , @NamedQuery(name = "StorageForm.findByTeamEmail", query = "SELECT s FROM StorageForm s WHERE s.teamEmail = :teamEmail")
    , @NamedQuery(name = "StorageForm.findByExpectedGrowth", query = "SELECT s FROM StorageForm s WHERE s.expectedGrowth = :expectedGrowth")
    , @NamedQuery(name = "StorageForm.findByComments", query = "SELECT s FROM StorageForm s WHERE s.comments = :comments")})
public class StorageForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ITSMRequestNo")
    private String iTSMRequestNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Share_Type")
    private int shareType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "Share_Name")
    private String shareName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Size_In_GB")
    private int sizeInGB;
    @Size(max = 1073741823)
    @Column(name = "Target_Hosts")
    private String targetHosts;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Owner_Email")
    private String ownerEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Team_Email")
    private String teamEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Expected_Growth")
    private int expectedGrowth;
    @Size(max = 1073741823)
    @Column(name = "Comments")
    private String comments;

    public StorageForm() {
    }

    public StorageForm(String iTSMRequestNo) {
        this.iTSMRequestNo = iTSMRequestNo;
    }

    public StorageForm(String iTSMRequestNo, int shareType, String shareName, int sizeInGB, String ownerEmail, String teamEmail, int expectedGrowth) {
        this.iTSMRequestNo = iTSMRequestNo;
        this.shareType = shareType;
        this.shareName = shareName;
        this.sizeInGB = sizeInGB;
        this.ownerEmail = ownerEmail;
        this.teamEmail = teamEmail;
        this.expectedGrowth = expectedGrowth;
    }

    public String getITSMRequestNo() {
        return iTSMRequestNo;
    }

    public void setITSMRequestNo(String iTSMRequestNo) {
        this.iTSMRequestNo = iTSMRequestNo;
    }

    public int getShareType() {
        return shareType;
    }

    public void setShareType(int shareType) {
        this.shareType = shareType;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public int getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(int sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public String getTargetHosts() {
        return targetHosts;
    }

    public void setTargetHosts(String targetHosts) {
        this.targetHosts = targetHosts;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
    }

    public int getExpectedGrowth() {
        return expectedGrowth;
    }

    public void setExpectedGrowth(int expectedGrowth) {
        this.expectedGrowth = expectedGrowth;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iTSMRequestNo != null ? iTSMRequestNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StorageForm)) {
            return false;
        }
        StorageForm other = (StorageForm) object;
        if ((this.iTSMRequestNo == null && other.iTSMRequestNo != null) || (this.iTSMRequestNo != null && !this.iTSMRequestNo.equals(other.iTSMRequestNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StorageForm{" + "iTSMRequestNo=" + iTSMRequestNo + ", shareType=" + shareType + ", shareName=" + shareName + ", sizeInGB=" + sizeInGB + ", targetHosts=" + targetHosts + ", ownerEmail=" + ownerEmail + ", teamEmail=" + teamEmail + ", expectedGrowth=" + expectedGrowth + ", comments=" + comments + '}';
    }

    
    
}
