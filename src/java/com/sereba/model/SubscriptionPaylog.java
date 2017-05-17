/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sereba.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "subscription_paylog")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubscriptionPaylog.findAll", query = "SELECT s FROM SubscriptionPaylog s"),
    @NamedQuery(name = "SubscriptionPaylog.findBySubId", query = "SELECT s FROM SubscriptionPaylog s WHERE s.subId = :subId"),
    @NamedQuery(name = "SubscriptionPaylog.findByAmount", query = "SELECT s FROM SubscriptionPaylog s WHERE s.amount = :amount"),
    @NamedQuery(name = "SubscriptionPaylog.findByStartPeriod", query = "SELECT s FROM SubscriptionPaylog s WHERE s.startPeriod = :startPeriod"),
    @NamedQuery(name = "SubscriptionPaylog.findByEndPeriod", query = "SELECT s FROM SubscriptionPaylog s WHERE s.endPeriod = :endPeriod"),
    @NamedQuery(name = "SubscriptionPaylog.findByTimeCreated", query = "SELECT s FROM SubscriptionPaylog s WHERE s.timeCreated = :timeCreated"),
    @NamedQuery(name = "SubscriptionPaylog.findByTimeModified", query = "SELECT s FROM SubscriptionPaylog s WHERE s.timeModified = :timeModified"),
    @NamedQuery(name = "SubscriptionPaylog.findByTransactionStatus", query = "SELECT s FROM SubscriptionPaylog s WHERE s.transactionStatus = :transactionStatus"),
    @NamedQuery(name = "SubscriptionPaylog.findByTransactionMessage", query = "SELECT s FROM SubscriptionPaylog s WHERE s.transactionMessage = :transactionMessage"),
    @NamedQuery(name = "SubscriptionPaylog.findByIsDeleted", query = "SELECT s FROM SubscriptionPaylog s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "SubscriptionPaylog.findBySource", query = "SELECT s FROM SubscriptionPaylog s WHERE s.source = :source")})
public class SubscriptionPaylog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_id")
    private Integer subId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "start_period")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startPeriod;
    @Column(name = "end_period")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endPeriod;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "transaction_status")
    private Integer transactionStatus;
    @Size(max = 45)
    @Column(name = "transaction_message")
    private String transactionMessage;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @JoinColumn(name = "pmethod", referencedColumnName = "pmethod_id")
    @ManyToOne
    private PaymentMethod pmethod;
    @JoinColumn(name = "pplan_id", referencedColumnName = "pplan_id")
    @ManyToOne
    private PaymentPlan pplanId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne
    private User userId;
    @Size(max = 45)
    @Column(name = "source")
    private String source;

    public SubscriptionPaylog() {
    }

    public SubscriptionPaylog(Integer subId) {
        this.subId = subId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
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

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public void setTransactionMessage(String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public PaymentMethod getPmethod() {
        return pmethod;
    }

    public void setPmethod(PaymentMethod pmethod) {
        this.pmethod = pmethod;
    }

    public PaymentPlan getPplanId() {
        return pplanId;
    }

    public void setPplanId(PaymentPlan pplanId) {
        this.pplanId = pplanId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subId != null ? subId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubscriptionPaylog)) {
            return false;
        }
        SubscriptionPaylog other = (SubscriptionPaylog) object;
        if ((this.subId == null && other.subId != null) || (this.subId != null && !this.subId.equals(other.subId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.SubscriptionPaylog[ subId=" + subId + " ]";
    }
    
}
