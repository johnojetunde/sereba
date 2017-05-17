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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "payment_method")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByPmethodId", query = "SELECT p FROM PaymentMethod p WHERE p.pmethodId = :pmethodId"),
    @NamedQuery(name = "PaymentMethod.findByMethodName", query = "SELECT p FROM PaymentMethod p WHERE p.methodName = :methodName"),
    @NamedQuery(name = "PaymentMethod.findByTimeCreated", query = "SELECT p FROM PaymentMethod p WHERE p.timeCreated = :timeCreated"),
    @NamedQuery(name = "PaymentMethod.findByTimeModified", query = "SELECT p FROM PaymentMethod p WHERE p.timeModified = :timeModified"),
    @NamedQuery(name = "PaymentMethod.findByIsDeleted", query = "SELECT p FROM PaymentMethod p WHERE p.isDeleted = :isDeleted"),
    @NamedQuery(name = "PaymentMethod.findBySource", query = "SELECT p FROM PaymentMethod p WHERE p.source = :source")})
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pmethod_id")
    private Integer pmethodId;
    @Size(max = 45)
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @OneToMany(mappedBy = "pmethod")
    private Collection<SubscriptionPaylog> subscriptionPaylogCollection;
    @OneToMany(mappedBy = "pmethodId")
    private Collection<User> userCollection;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    public PaymentMethod() {
    }

    public PaymentMethod(Integer pmethodId) {
        this.pmethodId = pmethodId;
    }

    public Integer getPmethodId() {
        return pmethodId;
    }

    public void setPmethodId(Integer pmethodId) {
        this.pmethodId = pmethodId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlTransient
    public Collection<SubscriptionPaylog> getSubscriptionPaylogCollection() {
        return subscriptionPaylogCollection;
    }

    public void setSubscriptionPaylogCollection(Collection<SubscriptionPaylog> subscriptionPaylogCollection) {
        this.subscriptionPaylogCollection = subscriptionPaylogCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pmethodId != null ? pmethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.pmethodId == null && other.pmethodId != null) || (this.pmethodId != null && !this.pmethodId.equals(other.pmethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.PaymentMethod[ pmethodId=" + pmethodId + " ]";
    }
    
}
