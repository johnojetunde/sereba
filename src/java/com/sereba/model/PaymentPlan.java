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
@Table(name = "payment_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentPlan.findAll", query = "SELECT p FROM PaymentPlan p"),
    @NamedQuery(name = "PaymentPlan.findByPplanId", query = "SELECT p FROM PaymentPlan p WHERE p.pplanId = :pplanId"),
    @NamedQuery(name = "PaymentPlan.findByPaymentName", query = "SELECT p FROM PaymentPlan p WHERE p.paymentName = :paymentName"),
    @NamedQuery(name = "PaymentPlan.findByPaymentDuration", query = "SELECT p FROM PaymentPlan p WHERE p.paymentDuration = :paymentDuration"),
    @NamedQuery(name = "PaymentPlan.findByTimeCreated", query = "SELECT p FROM PaymentPlan p WHERE p.timeCreated = :timeCreated"),
    @NamedQuery(name = "PaymentPlan.findByTimeModified", query = "SELECT p FROM PaymentPlan p WHERE p.timeModified = :timeModified"),
    @NamedQuery(name = "PaymentPlan.findByIsDeleted", query = "SELECT p FROM PaymentPlan p WHERE p.isDeleted = :isDeleted"),
    @NamedQuery(name = "PaymentPlan.findBySource", query = "SELECT p FROM PaymentPlan p WHERE p.source = :source")})
public class PaymentPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pplan_id")
    private Integer pplanId;
    @Size(max = 45)
    @Column(name = "payment_name")
    private String paymentName;
    @Size(max = 45)
    @Column(name = "payment_duration")
    private String paymentDuration;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @OneToMany(mappedBy = "pplanId")
    private Collection<SubscriptionPaylog> subscriptionPaylogCollection;
    @OneToMany(mappedBy = "pplanId")
    private Collection<User> userCollection;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    public PaymentPlan() {
    }

    public PaymentPlan(Integer pplanId) {
        this.pplanId = pplanId;
    }

    public Integer getPplanId() {
        return pplanId;
    }

    public void setPplanId(Integer pplanId) {
        this.pplanId = pplanId;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentDuration() {
        return paymentDuration;
    }

    public void setPaymentDuration(String paymentDuration) {
        this.paymentDuration = paymentDuration;
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
        hash += (pplanId != null ? pplanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentPlan)) {
            return false;
        }
        PaymentPlan other = (PaymentPlan) object;
        if ((this.pplanId == null && other.pplanId != null) || (this.pplanId != null && !this.pplanId.equals(other.pplanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.PaymentPlan[ pplanId=" + pplanId + " ]";
    }
    
}
