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
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProdId", query = "SELECT p FROM Product p WHERE p.prodId = :prodId"),
    @NamedQuery(name = "Product.findByProdName", query = "SELECT p FROM Product p WHERE p.prodName = :prodName"),
    @NamedQuery(name = "Product.findByProdCostPrice", query = "SELECT p FROM Product p WHERE p.prodCostPrice = :prodCostPrice"),
    @NamedQuery(name = "Product.findByProdNameCreator", query = "SELECT p FROM Product p WHERE p.prodName = :prodName AND p.creatorId = :creatorId"),
    @NamedQuery(name = "Product.findByProdNameCreatorIsDeleted", query = "SELECT p FROM Product p LEFT JOIN StoreStaff s LEFT JOIN User u WHERE s.userId = :userId AND p.prodName = :prodName AND p.isDeleted = :isDeleted"),
    @NamedQuery(name = "Product.findByCreatorIsDeleted", query = "SELECT p,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullName,u.userId FROM Product p LEFT JOIN StoreStaff s LEFT JOIN User u WHERE p.creatorId = s AND s.userId = :userId AND p.isDeleted = :isDeleted GROUP BY p ORDER BY p.prodId DESC"),
    @NamedQuery(name = "Product.findByAdminIsDeleted", query = "SELECT p,s.fullname as staff_fullName,s.staffId,u.fullname as user_fullname,u.userId FROM Product p LEFT JOIN StoreStaff s LEFT JOIN User u WHERE p.creatorId = s AND s.userId = u AND p.isDeleted = :isDeleted GROUP BY p ORDER BY p.prodId DESC"),
    @NamedQuery(name = "Product.findByIsDeletedCreator", query = "SELECT p FROM Product p WHERE p.isDeleted = :isDeleted AND p.creatorId = :creatorId"),
    @NamedQuery(name = "Product.findByProdSellingPrice", query = "SELECT p FROM Product p WHERE p.prodSellingPrice = :prodSellingPrice"),
    @NamedQuery(name = "Product.findByProdQty", query = "SELECT p FROM Product p WHERE p.prodQty = :prodQty"),
    @NamedQuery(name = "Product.findByProdMeasurement", query = "SELECT p FROM Product p WHERE p.prodMeasurement = :prodMeasurement"),
    @NamedQuery(name = "Product.findByCreatorType", query = "SELECT p FROM Product p WHERE p.creatorType = :creatorType"),
    @NamedQuery(name = "Product.findByDiscount", query = "SELECT p FROM Product p WHERE p.discount = :discount"),
    @NamedQuery(name = "Product.findByProdImagePath", query = "SELECT p FROM Product p WHERE p.prodImagePath = :prodImagePath"),
    @NamedQuery(name = "Product.findByTimeCreated", query = "SELECT p FROM Product p WHERE p.timeCreated = :timeCreated"),
    @NamedQuery(name = "Product.findByTimeModified", query = "SELECT p FROM Product p WHERE p.timeModified = :timeModified"),
    @NamedQuery(name = "Product.findByIsDeleted", query = "SELECT p FROM Product p WHERE p.isDeleted = :isDeleted"),
    @NamedQuery(name = "Product.findByLastId" , query = "SELECT p FROM Product p ORDER BY p.prodId DESC"),
    @NamedQuery(name = "Product.findBySource", query = "SELECT p FROM Product p WHERE p.source = :source")
})
public class Product implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodId")
    private Collection<InventoryStock> inventoryStockCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_id")
    private Integer prodId;
    @Size(max = 45)
    @Column(name = "prod_name")
    private String prodName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prod_cost_price")
    private Double prodCostPrice;
    @Column(name = "prod_selling_price")
    private Double prodSellingPrice;
    @Column(name = "prod_qty")
    private Integer prodQty;
    @Size(max = 45)
    @Column(name = "prod_measurement")
    private String prodMeasurement;
    @Size(max = 45)
    @Column(name = "creator_type")
    private String creatorType;
    @Column(name = "discount")
    private Double discount;
    @Size(max = 45)
    @Column(name = "prod_image_path")
    private String prodImagePath;
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
    @OneToMany(mappedBy = "prodId")
    private Collection<Sales> salesCollection;
    @Size(max = 45)
    @Column(name = "source")
    private String source;
    
    public Product() {
    }

    public Product(Integer prodId) {
        this.prodId = prodId;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Double getProdCostPrice() {
        return prodCostPrice;
    }

    public void setProdCostPrice(Double prodCostPrice) {
        this.prodCostPrice = prodCostPrice;
    }

    public Double getProdSellingPrice() {
        return prodSellingPrice;
    }

    public void setProdSellingPrice(Double prodSellingPrice) {
        this.prodSellingPrice = prodSellingPrice;
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

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getProdImagePath() {
        return prodImagePath;
    }

    public void setProdImagePath(String prodImagePath) {
        this.prodImagePath = prodImagePath;
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
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sereba.model.Product[ prodId=" + prodId + " ]";
    }

    @XmlTransient
    public Collection<InventoryStock> getInventoryStockCollection() {
        return inventoryStockCollection;
    }

    public void setInventoryStockCollection(Collection<InventoryStock> inventoryStockCollection) {
        this.inventoryStockCollection = inventoryStockCollection;
    }
    
}
