/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "storage_forms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StorageForm.findAll", query = "SELECT s FROM StorageForm s")
    , @NamedQuery(name = "StorageForm.findByITSMRequestNo", query = "SELECT s FROM StorageForm s WHERE s.iTSMRequestNo = :iTSMRequestNo")
    , @NamedQuery(name = "StorageForm.findByComments", query = "SELECT s FROM StorageForm s WHERE s.comments = :comments")
    , @NamedQuery(name = "StorageForm.findByExpectedGrowth", query = "SELECT s FROM StorageForm s WHERE s.expectedGrowth = :expectedGrowth")
    , @NamedQuery(name = "StorageForm.findByOwnerEmail", query = "SELECT s FROM StorageForm s WHERE s.ownerEmail = :ownerEmail")
    , @NamedQuery(name = "StorageForm.findByShareName", query = "SELECT s FROM StorageForm s WHERE s.shareName = :shareName")
    , @NamedQuery(name = "StorageForm.findShareNames", query = "SELECT s.shareName FROM StorageForm s")
    , @NamedQuery(name = "StorageForm.findByShareType", query = "SELECT s FROM StorageForm s WHERE s.shareType = :shareType")
    , @NamedQuery(name = "StorageForm.findBySizeInGB", query = "SELECT s FROM StorageForm s WHERE s.sizeInGB = :sizeInGB")
    , @NamedQuery(name = "StorageForm.findByTargetHosts", query = "SELECT s FROM StorageForm s WHERE s.targetHosts = :targetHosts")
    , @NamedQuery(name = "StorageForm.findByTeamEmail", query = "SELECT s FROM StorageForm s WHERE s.teamEmail = :teamEmail")})
public class StorageForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ITSMRequestNo")
    private String iTSMRequestNo;
    @Size(max = 255)
    @Column(name = "Comments")
    private String comments;
    @Column(name = "Expected_Growth")
    private Integer expectedGrowth;
    @Size(max = 255)
    @Column(name = "Owner_Email")
    private String ownerEmail;
    @Size(max = 255)
    @Column(name = "Share_Name")
    private String shareName;
    @Size(max = 10)
    @Column(name = "Share_Type")
    private String shareType;
    @Column(name = "Size_In_GB")
    private Integer sizeInGB;
    @Size(max = 255)
    @Column(name = "Target_Hosts")
    private String targetHosts;
    @Size(max = 255)
    @Column(name = "Team_Email")
    private String teamEmail;

    public StorageForm() {
    }

    public StorageForm(String iTSMRequestNo) {
        this.iTSMRequestNo = iTSMRequestNo;
    }

    public String getITSMRequestNo() {
        return iTSMRequestNo;
    }

    public void setITSMRequestNo(String iTSMRequestNo) {
        this.iTSMRequestNo = iTSMRequestNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getExpectedGrowth() {
        return expectedGrowth;
    }

    public void setExpectedGrowth(Integer expectedGrowth) {
        this.expectedGrowth = expectedGrowth;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public Integer getSizeInGB() {
        return sizeInGB;
    }

    public void setSizeInGB(Integer sizeInGB) {
        this.sizeInGB = sizeInGB;
    }

    public String getTargetHosts() {
        return targetHosts;
    }

    public void setTargetHosts(String targetHosts) {
        this.targetHosts = targetHosts;
    }

    public String getTeamEmail() {
        return teamEmail;
    }

    public void setTeamEmail(String teamEmail) {
        this.teamEmail = teamEmail;
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
        return "entity.StorageForms[ iTSMRequestNo=" + iTSMRequestNo + " ]";
    }
    
}
