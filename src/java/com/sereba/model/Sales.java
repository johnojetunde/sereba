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
@Table(name = "sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sales.findAll", query = "SELECT s FROM Sales s"),
    @NamedQuery(name = "Sales.findBySalesId", query = "SELECT s FROM Sales s WHERE s.salesId = :salesId"),
    @NamedQuery(name = "Sales.findByQtySold", query = "SELECT s FROM Sales s WHERE s.qtySold = :qtySold"),
    @NamedQuery(name = "Sales.findByCostPrice", query = "SELECT s FROM Sales s WHERE s.costPrice = :costPrice"),
    @NamedQuery(name = "Sales.findByDiscount", query = "SELECT s FROM Sales s WHERE s.discount = :discount"),
    @NamedQuery(name = "Sales.findBySellingPrice", query = "SELECT s FROM Sales s WHERE s.sellingPrice = :sellingPrice"),
    @NamedQuery(name = "Sales.findByAmount", query = "SELECT s FROM Sales s WHERE s.amount = :amount"),
    @NamedQuery(name = "Sales.findByAdminIsDeleted", query = "SELECT s,st.fullname as staff_fullName,st.staffId,u.fullname as user_fullname,u.userId,p.prodName,p.prodMeasurement,p.prodImagePath,p.prodId FROM Sales s  JOIN StoreStaff st  JOIN User u  JOIN Product p WHERE s.prodId = p  AND s.creatorId = st AND st.userId = u AND s.isDeleted = :isDeleted GROUP BY s ORDER BY s.salesId DESC"),
    @NamedQuery(name = "Sales.findByCreatorIsDeleted", query = "SELECT s,st.fullname as staff_fullName,st.staffId,u.fullname as user_fullname,u.userId,p.prodName,p.prodMeasurement,p.prodImagePath,p.prodId FROM Sales s JOIN StoreStaff st  JOIN User u  JOIN Product p WHERE s.prodId = p  AND s.creatorId = st AND st.userId = u AND  s.isDeleted = :isDeleted AND st.userId = :userId GROUP BY s ORDER BY s.salesId DESC"),
    @NamedQuery(name = "Sales.selectSumSales", query = "SELECT p.prodImagePath,p.prodName,p.prodMeasurement,p.prodId,SUM(s.amount) as saleamount, count(s) as salecount, SUM(s.qtySold) as saleqty FROM Sales s JOIN Product p WHERE s.prodId = p  AND  s.prodId = :prodId AND (s.date >= :startDate AND s.date<= :endDate)"),
    @NamedQuery(name = "Sales.findByCreatorType", query = "SELECT s FROM Sales s WHERE s.creatorType = :creatorType"),
    @NamedQuery(name = "Sales.findByTimeCreated", query = "SELECT s FROM Sales s WHERE s.timeCreated = :timeCreated"),
    @NamedQuery(name = "Sales.findByTimeModified", query = "SELECT s FROM Sales s WHERE s.timeModified = :timeModified"),
    @NamedQuery(name = "Sales.findByIsDeleted", query = "SELECT s FROM Sales s WHERE s.isDeleted = :isDeleted"),
    @NamedQuery(name = "Sales.findBySource", query = "SELECT s FROM sales s WHERE s.source = :source")})
public class Sales implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sales_id")
    private Integer salesId;
    @Column(name = "qty_sold")
    private Integer qtySold;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost_price")
    private Double costPrice;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "selling_price")
    private Double sellingPrice;
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
    @JoinColumn(name = "prod_id", referencedColumnName = "prod_id")
    @ManyToOne
    private Product prodId;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    public Sales() {
    }

    public Sales(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getQtySold() {
        return qtySold;
    }

    public void setQtySold(Integer qtySold) {
        this.qtySold = qtySold;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
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

    public Product getProdId() {
        return prodId;
    }

    public void setProdId(Product prodId) {
        this.prodId = prodId;
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
        hash += (salesId != null ? salesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sales)) {
            return false;
        }
        Sales other = (Sales) object;
        if ((this.salesId == null && other.salesId != null) || (this.salesId != null && !this.salesId.equals(other.salesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.Sales[ salesId=" + salesId + " ]";
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
