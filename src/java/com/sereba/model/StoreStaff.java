/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "store_staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreStaff.findAll", query = "SELECT s FROM StoreStaff s"),
    @NamedQuery(name = "StoreStaff.findByStaffId", query = "SELECT s FROM StoreStaff s WHERE s.staffId = :staffId"),
    @NamedQuery(name = "StoreStaff.findByFullname", query = "SELECT s FROM StoreStaff s WHERE s.fullname = :fullname"),
    @NamedQuery(name = "StoreStaff.findByEmail", query = "SELECT s FROM StoreStaff s WHERE s.email = :email"),
    @NamedQuery(name = "StoreStaff.findByEmailIsDeleted", query = "SELECT s FROM StoreStaff s WHERE s.email = :email AND s.isDeleted = :isDeleted"),
    @NamedQuery(name = "StoreStaff.findByEmailPasswordDeleted", query = "SELECT s FROM StoreStaff s WHERE s.email = :email AND s.password = :password AND s.isDeleted = :isDeleted"),
    @NamedQuery(name = "StoreStaff.findByCreatorIsDeleted", query = "SELECT s,u.fullname as user_fullname,u.userId,u.storeName as store_name,u FROM StoreStaff s LEFT JOIN User u WHERE s.userId = :userId AND s.isDeleted = :isDeleted GROUP BY s ORDER BY s.staffId DESC"),
    @NamedQuery(name = "StoreStaff.findByAdminIsDeleted", query = "SELECT s,u.fullname as user_fullname,u.userId,u.storeName as store_name,u FROM StoreStaff s LEFT JOIN User u WHERE s.userId = u AND s.isDeleted = :isDeleted GROUP BY s ORDER BY s.staffId DESC"),
    @NamedQuery(name = "StoreStaff.findByPassword", query = "SELECT s FROM StoreStaff s WHERE s.password = :password"),
    @NamedQuery(name = "StoreStaff.findByPhoneNumber", query = "SELECT s FROM StoreStaff s WHERE s.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "StoreStaff.findByTimeCreated", query = "SELECT s FROM StoreStaff s WHERE s.timeCreated = :timeCreated"),
    @NamedQuery(name = "StoreStaff.findByTimeModified", query = "SELECT s FROM StoreStaff s WHERE s.timeModified = :timeModified"),
    @NamedQuery(name = "StoreStaff.findByIsDeleted", query = "SELECT s FROM StoreStaff s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "StoreStaff.findBySource", query = "SELECT s FROM StoreStaff s WHERE s.source = :source")})
public class StoreStaff implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creatorId")
    private Collection<InventoryStock> inventoryStockCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staff_id")
    private Integer staffId;
    @Size(max = 45)
    @Column(name = "fullname")
    private String fullname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "acl")
    private String acl;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(mappedBy = "creatorId")
    private Collection<Product> productCollection;
    @OneToMany(mappedBy = "creatorId")
    private Collection<Creditor> creditorCollection;
    @OneToMany(mappedBy = "creatorId")
    private Collection<Inventory> inventoryCollection;
    @OneToMany(mappedBy = "creatorId")
    private Collection<CredPayment> credPaymentCollection;
    @OneToMany(mappedBy = "creatorId")
    private Collection<Sales> salesCollection;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    public StoreStaff() {
    }

    public StoreStaff(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<Creditor> getCreditorCollection() {
        return creditorCollection;
    }

    public void setCreditorCollection(Collection<Creditor> creditorCollection) {
        this.creditorCollection = creditorCollection;
    }

    @XmlTransient
    public Collection<Inventory> getInventoryCollection() {
        return inventoryCollection;
    }

    public void setInventoryCollection(Collection<Inventory> inventoryCollection) {
        this.inventoryCollection = inventoryCollection;
    }

    @XmlTransient
    public Collection<CredPayment> getCredPaymentCollection() {
        return credPaymentCollection;
    }

    public void setCredPaymentCollection(Collection<CredPayment> credPaymentCollection) {
        this.credPaymentCollection = credPaymentCollection;
    }

    @XmlTransient
    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreStaff)) {
            return false;
        }
        StoreStaff other = (StoreStaff) object;
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.StoreStaff[ staffId=" + staffId + " ]";
    }

    @XmlTransient
    public Collection<InventoryStock> getInventoryStockCollection() {
        return inventoryStockCollection;
    }

    public void setInventoryStockCollection(Collection<InventoryStock> inventoryStockCollection) {
        this.inventoryStockCollection = inventoryStockCollection;
    }
    
}
