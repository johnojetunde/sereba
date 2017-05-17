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
@Table(name = "inventory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventory.findAll", query = "SELECT i FROM Inventory i"),
    @NamedQuery(name = "Inventory.findByInvId", query = "SELECT i FROM Inventory i WHERE i.invId = :invId"),
    @NamedQuery(name = "Inventory.findByTitle", query = "SELECT i FROM Inventory i WHERE i.title = :title"),
    @NamedQuery(name = "Inventory.findByAmount", query = "SELECT i FROM Inventory i WHERE i.amount = :amount"),
    @NamedQuery(name = "Inventory.findByTransType", query = "SELECT i FROM Inventory i WHERE i.transType = :transType"),
    @NamedQuery(name = "Inventory.findByAdminIsDeleted", query = "SELECT i,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM Inventory i JOIN StoreStaff s JOIN User u WHERE i.creatorId = s AND s.userId = u AND i.isDeleted = :isDeleted GROUP BY i ORDER BY i.invId DESC"),
    @NamedQuery(name = "Inventory.findByCreatorIsDeleted", query = "SELECT i,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM Inventory i JOIN StoreStaff s JOIN User u WHERE i.creatorId = s AND s.userId = u AND s.userId = :userId AND i.isDeleted = :isDeleted GROUP BY i ORDER BY i.invId DESC"),
    @NamedQuery(name = "Inventory.findByCreatorType", query = "SELECT i FROM Inventory i WHERE i.creatorType = :creatorType"),
    @NamedQuery(name = "Inventory.findByDate", query = "SELECT i FROM Inventory i WHERE i.date = :date"),
    @NamedQuery(name = "Inventory.findByTimeCreated", query = "SELECT i FROM Inventory i WHERE i.timeCreated = :timeCreated"),
    @NamedQuery(name = "Inventory.findByTimeModified", query = "SELECT i FROM Inventory i WHERE i.timeModified = :timeModified"),
    @NamedQuery(name = "Inventory.findByIsDeleted", query = "SELECT i FROM Inventory i WHERE i.isDeleted = :isDeleted"),
    @NamedQuery(name = "Inventory.findBySource", query = "SELECT i FROM inventory i WHERE i.source = :source")})
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "inv_id")
    private Integer invId;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @Column(name = "trans_type")
    private String transType;
    @Size(max = 45)
    @Column(name = "creator_type")
    private String creatorType;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
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
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    public Inventory() {
    }

    public Inventory(Integer invId) {
        this.invId = invId;
    }

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invId != null ? invId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.invId == null && other.invId != null) || (this.invId != null && !this.invId.equals(other.invId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.Inventory[ invId=" + invId + " ]";
    }
    
}
