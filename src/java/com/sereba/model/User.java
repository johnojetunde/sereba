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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByFullname", query = "SELECT u FROM User u WHERE u.fullname = :fullname"),
    @NamedQuery(name = "User.findByStoreName", query = "SELECT u FROM User u WHERE u.storeName = :storeName"),
    @NamedQuery(name = "User.findByStoreNameDeleted", query = "SELECT u FROM User u WHERE u.storeName = :storeName AND u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findByStoreNameNotSameIsDeleted", query = "SELECT u FROM User u WHERE u.storeName = :storeName AND  u.userId != :userId AND u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByEmailDeleted", query = "SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmailPasswordDeleted", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findByPhoneNumber", query = "SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "User.findByPayStart", query = "SELECT u FROM User u WHERE u.payStart = :payStart"),
    @NamedQuery(name = "User.findByPayExpire", query = "SELECT u FROM User u WHERE u.payExpire = :payExpire"),
    @NamedQuery(name = "User.findByActivated", query = "SELECT u FROM User u WHERE u.activated = :activated"),
    @NamedQuery(name = "User.findByTimeCreated", query = "SELECT u FROM User u WHERE u.timeCreated = :timeCreated"),
    @NamedQuery(name = "User.findByTimeModified", query = "SELECT u FROM User u WHERE u.timeModified = :timeModified"),
    @NamedQuery(name = "User.findByLastId", query = "SELECT u FROM User u ORDER BY u.userId DESC"),
    @NamedQuery(name = "User.findByIsDeleted", query = "SELECT u FROM User u WHERE u.isDeleted = :isDeleted"),
    @NamedQuery(name = "User.findBySource", query = "SELECT u FROM User u WHERE u.source = :source")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Size(max = 45)
    @Column(name = "fullname")
    private String fullname;
    @Size(max = 45)
    @Column(name = "store_name")
    private String storeName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 200)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 45)
    @Column(name = "source")
    private String source;

   
    @Lob
    @Size(max = 2147483647)
    @Column(name = "contact_address")
    private String contactAddress;
    @Column(name = "pay_start")
    @Temporal(TemporalType.DATE)
    private Date payStart;
    @Column(name = "pay_expire")
    @Temporal(TemporalType.DATE)
    private Date payExpire;
    @Column(name = "activated")
    private Integer activated;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<StoreStaff> storeStaffCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<SubscriptionPaylog> subscriptionPaylogCollection;
    @JoinColumn(name = "approved_by", referencedColumnName = "admin_id")
    @ManyToOne
    private Admin approvedBy;
    @JoinColumn(name = "pmethod_id", referencedColumnName = "pmethod_id")
    @ManyToOne
    private PaymentMethod pmethodId;
    @JoinColumn(name = "pplan_id", referencedColumnName = "pplan_id")
    @ManyToOne
    private PaymentPlan pplanId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public Date getPayStart() {
        return payStart;
    }

    public void setPayStart(Date payStart) {
        this.payStart = payStart;
    }

    public Date getPayExpire() {
        return payExpire;
    }

    public void setPayExpire(Date payExpire) {
        this.payExpire = payExpire;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    public Collection<StoreStaff> getStoreStaffCollection() {
        return storeStaffCollection;
    }

    public void setStoreStaffCollection(Collection<StoreStaff> storeStaffCollection) {
        this.storeStaffCollection = storeStaffCollection;
    }

    @XmlTransient
    public Collection<SubscriptionPaylog> getSubscriptionPaylogCollection() {
        return subscriptionPaylogCollection;
    }

    public void setSubscriptionPaylogCollection(Collection<SubscriptionPaylog> subscriptionPaylogCollection) {
        this.subscriptionPaylogCollection = subscriptionPaylogCollection;
    }

    public Admin getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Admin approvedBy) {
        this.approvedBy = approvedBy;
    }

    public PaymentMethod getPmethodId() {
        return pmethodId;
    }

    public void setPmethodId(PaymentMethod pmethodId) {
        this.pmethodId = pmethodId;
    }

    public PaymentPlan getPplanId() {
        return pplanId;
    }

    public void setPplanId(PaymentPlan pplanId) {
        this.pplanId = pplanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.User[ userId=" + userId + " ]";
    }
    
}
