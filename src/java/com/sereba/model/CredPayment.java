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
@Table(name = "cred_payment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CredPayment.findAll", query = "SELECT c FROM CredPayment c"),
    @NamedQuery(name = "CredPayment.findByCredpId", query = "SELECT c FROM CredPayment c WHERE c.credpId = :credpId"),
    @NamedQuery(name = "CredPayment.findByAmountPaid", query = "SELECT c FROM CredPayment c WHERE c.amountPaid = :amountPaid"),
    @NamedQuery(name = "CredPayment.findByCreatorType", query = "SELECT c FROM CredPayment c WHERE c.creatorType = :creatorType"),
    @NamedQuery(name = "CredPayment.findByTimeCreated", query = "SELECT c FROM CredPayment c WHERE c.timeCreated = :timeCreated"),
    @NamedQuery(name = "CredPayment.findByCreditIsDeleted", query = "SELECT c,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM CredPayment c LEFT JOIN StoreStaff s LEFT JOIN User u WHERE c.creatorId = s AND c.credId = :credId AND c.isDeleted = :isDeleted GROUP BY c ORDER BY c.credpId DESC"),
    @NamedQuery(name = "CredPayment.findByTimeModified", query = "SELECT c FROM CredPayment c WHERE c.timeModified = :timeModified"),
    @NamedQuery(name = "CredPayment.findByIsDeleted", query = "SELECT c FROM CredPayment c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "CredPayment.findBySource", query = "SELECT c FROM CredPayment c WHERE c.source = :source")})
public class CredPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "credp_id")
    private Integer credpId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Size(max = 45)
    @Column(name = "creator_type")
    private String creatorType;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "is_deleted")
    private Integer isDeleted;
    @JoinColumn(name = "creator_id", referencedColumnName = "staff_id")
    @ManyToOne
    private StoreStaff creatorId;
    @JoinColumn(name = "cred_id", referencedColumnName = "cred_id")
    @ManyToOne
    private Creditor credId;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    
    public CredPayment() {
    }

    public CredPayment(Integer credpId) {
        this.credpId = credpId;
    }

    public Integer getCredpId() {
        return credpId;
    }

    public void setCredpId(Integer credpId) {
        this.credpId = credpId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
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

    public StoreStaff getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(StoreStaff creatorId) {
        this.creatorId = creatorId;
    }

    public Creditor getCredId() {
        return credId;
    }

    public void setCredId(Creditor credId) {
        this.credId = credId;
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
        hash += (credpId != null ? credpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CredPayment)) {
            return false;
        }
        CredPayment other = (CredPayment) object;
        if ((this.credpId == null && other.credpId != null) || (this.credpId != null && !this.credpId.equals(other.credpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.CredPayment[ credpId=" + credpId + " ]";
    }
    
}
