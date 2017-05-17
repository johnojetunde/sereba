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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author John
 */
@Entity
@Table(name = "creditor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Creditor.findAll", query = "SELECT c FROM Creditor c"),
    @NamedQuery(name = "Creditor.findByCredId", query = "SELECT c FROM Creditor c WHERE c.credId = :credId"),
    @NamedQuery(name = "Creditor.findByProdName", query = "SELECT c FROM Creditor c WHERE c.prodName = :prodName"),
    @NamedQuery(name = "Creditor.findByProdDesc", query = "SELECT c FROM Creditor c WHERE c.prodDesc = :prodDesc"),
    @NamedQuery(name = "Creditor.findByProdQty", query = "SELECT c FROM Creditor c WHERE c.prodQty = :prodQty"),
    @NamedQuery(name = "Creditor.findByAdminIsDeleted", query = "SELECT c,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM Creditor c LEFT JOIN StoreStaff s LEFT JOIN User u WHERE c.creatorId = s AND s.userId = u AND c.isDeleted = :isDeleted GROUP BY c ORDER BY c.credId DESC"),
    @NamedQuery(name = "Creditor.findByCreatorIsDeleted", query = "SELECT c,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM Creditor c LEFT JOIN StoreStaff s LEFT JOIN User u WHERE c.creatorId = s AND s.userId = :userId AND c.isDeleted = :isDeleted GROUP BY c ORDER BY c.credId DESC"),
    @NamedQuery(name = "Creditor.findByProdMeasurement", query = "SELECT c FROM Creditor c WHERE c.prodMeasurement = :prodMeasurement"),
    @NamedQuery(name = "Creditor.findByAmount", query = "SELECT c FROM Creditor c WHERE c.amount = :amount"),
    @NamedQuery(name = "Creditor.findByExpiryDate", query = "SELECT c FROM Creditor c WHERE c.expiryDate = :expiryDate"),
    @NamedQuery(name = "Creditor.findByAmountDeposited", query = "SELECT c FROM Creditor c WHERE c.amountDeposited = :amountDeposited"),
    @NamedQuery(name = "Creditor.findByCreatorType", query = "SELECT c FROM Creditor c WHERE c.creatorType = :creatorType"),
    @NamedQuery(name = "Creditor.findByTimeModified", query = "SELECT c FROM Creditor c WHERE c.timeModified = :timeModified"),
    @NamedQuery(name = "Creditor.findByTimeCreated", query = "SELECT c FROM Creditor c WHERE c.timeCreated = :timeCreated"),
    @NamedQuery(name = "Creditor.findByIsDeleted", query = "SELECT c FROM Creditor c WHERE c.isDeleted = :isDeleted"),
    @NamedQuery(name = "Creditor.findBySource", query = "SELECT c FROM Creditor c WHERE c.source = :source")})
public class Creditor implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_qty")
    private int prodQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount_deposited")
    private double amountDeposited;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private int isDeleted;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cred_id")
    private Integer credId;
    @Size(max = 45)
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_desc")
    private String prodDesc;
    @Size(max = 45)
    @Column(name = "prod_measurement")
    private String prodMeasurement;
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Size(max = 45)
    @Column(name = "creator_type")
    private String creatorType;
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @JoinColumn(name = "creator_id", referencedColumnName = "staff_id")
    @ManyToOne
    private StoreStaff creatorId;
    @OneToMany(mappedBy = "credId")
    private Collection<CredPayment> credPaymentCollection;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    
    public Creditor() {
    }

    public Creditor(Integer credId) {
        this.credId = credId;
    }

    public Integer getCredId() {
        return credId;
    }

    public void setCredId(Integer credId) {
        this.credId = credId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Integer getProdQty() {
        return prodQty;
    }

    public void setProdQty(Integer prodQty) {
        this.prodQty = prodQty;
    }

    public String getProdMeasurement() {
        return prodMeasurement;
    }

    public void setProdMeasurement(String prodMeasurement) {
        this.prodMeasurement = prodMeasurement;
    }


    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Double getAmountDeposited() {
        return amountDeposited;
    }

    public void setAmountDeposited(Double amountDeposited) {
        this.amountDeposited = amountDeposited;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public Date getTimeModified() {
        return timeModified;
    }

    public void setTimeModified(Date timeModified) {
        this.timeModified = timeModified;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @XmlTransient
    public Collection<CredPayment> getCredPaymentCollection() {
        return credPaymentCollection;
    }

    public void setCredPaymentCollection(Collection<CredPayment> credPaymentCollection) {
        this.credPaymentCollection = credPaymentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credId != null ? credId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Creditor)) {
            return false;
        }
        Creditor other = (Creditor) object;
        if ((this.credId == null && other.credId != null) || (this.credId != null && !this.credId.equals(other.credId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.Creditor[ credId=" + credId + " ]";
    }

    
  
    

   
    
}
