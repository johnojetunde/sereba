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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "inventory_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventoryStock.findAll", query = "SELECT i FROM InventoryStock i"),
    @NamedQuery(name = "InventoryStock.findByInvstkId", query = "SELECT i FROM InventoryStock i WHERE i.invstkId = :invstkId"),
    @NamedQuery(name = "InventoryStock.findByProdQty", query = "SELECT i FROM InventoryStock i WHERE i.prodQty = :prodQty"),
    @NamedQuery(name = "InventoryStock.findByCostPrice", query = "SELECT i FROM InventoryStock i WHERE i.costPrice = :costPrice"),
    @NamedQuery(name = "InventoryStock.findBySellingPrice", query = "SELECT i FROM InventoryStock i WHERE i.sellingPrice = :sellingPrice"),
    @NamedQuery(name = "InventoryStock.findByDiscount", query = "SELECT i FROM InventoryStock i WHERE i.discount = :discount"),
    @NamedQuery(name = "InventoryStock.findByCreatorType", query = "SELECT i FROM InventoryStock i WHERE i.creatorType = :creatorType"),
    @NamedQuery(name = "InventoryStock.findByDate", query = "SELECT i FROM InventoryStock i WHERE i.date = :date"),
    @NamedQuery(name = "InventoryStock.findByTimeCreated", query = "SELECT i FROM InventoryStock i WHERE i.timeCreated = :timeCreated"),
    @NamedQuery(name = "InventoryStock.findByTimeModified", query = "SELECT i FROM InventoryStock i WHERE i.timeModified = :timeModified"),
    @NamedQuery(name = "InventoryStock.findByIsDeleted", query = "SELECT i FROM InventoryStock i WHERE i.isDeleted = :isDeleted"),
    @NamedQuery(name = "InventoryStock.findByCreatorProductIsDeleted", query = "SELECT i,st.fullname as staff_fullName,st.staffId,u.fullname as user_fullname,u.userId,p.prodName,p.prodMeasurement,p.prodImagePath,p.prodId FROM InventoryStock i JOIN StoreStaff st  JOIN User u  JOIN Product p WHERE i.prodId = p AND i.prodId = :prodId AND i.creatorId = st AND st.userId = u AND  i.isDeleted = :isDeleted AND st.userId = :userId GROUP BY i ORDER BY i.invstkId DESC"),
    @NamedQuery(name = "InventoryStock.findByLastId", query = "SELECT i FROM InventoryStock i ORDER BY i.invstkId DESC"),
    @NamedQuery(name = "InventoryStock.findBySource", query = "SELECT i FROM InventoryStock i WHERE i.source = :source")
        
})
public class InventoryStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invstk_id")
    private Integer invstkId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_qty")
    private int prodQty;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost_price")
    private double costPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "selling_price")
    private double sellingPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private double discount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "creator_type")
    private String creatorType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeModified;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private int isDeleted;
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    @ManyToOne(optional = false)
    private Product prodId;
    @JoinColumn(name = "creator_id", referencedColumnName = "staff_id")
    @ManyToOne(optional = false)
    private StoreStaff creatorId;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    public InventoryStock() {
    }

    public InventoryStock(Integer invstkId) {
        this.invstkId = invstkId;
    }

    public InventoryStock(Integer invstkId, int prodQty, double costPrice, double sellingPrice, double discount, String creatorType, Date date, Date timeCreated, Date timeModified, int isDeleted) {
        this.invstkId = invstkId;
        this.prodQty = prodQty;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.creatorType = creatorType;
        this.date = date;
        this.timeCreated = timeCreated;
        this.timeModified = timeModified;
        this.isDeleted = isDeleted;
    }

    public Integer getInvstkId() {
        return invstkId;
    }

    public void setInvstkId(Integer invstkId) {
        this.invstkId = invstkId;
    }

    public int getProdQty() {
        return prodQty;
    }

    public void setProdQty(int prodQty) {
        this.prodQty = prodQty;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Product getProdId() {
        return prodId;
    }

    public void setProdId(Product prodId) {
        this.prodId = prodId;
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
        hash += (invstkId != null ? invstkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InventoryStock)) {
            return false;
        }
        InventoryStock other = (InventoryStock) object;
        if ((this.invstkId == null && other.invstkId != null) || (this.invstkId != null && !this.invstkId.equals(other.invstkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.InventoryStock[ invstkId=" + invstkId + " ]";
    }
    
}
