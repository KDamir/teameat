/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Куат
 */
@Entity
@Table(name = "meat_part_purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatPartPurchase.findAll", query = "SELECT m FROM MeatPartPurchaseEntity m"),
    @NamedQuery(name = "MeatPartPurchase.findById", query = "SELECT m FROM MeatPartPurchaseEntity m WHERE m.id = :id"),
    @NamedQuery(name = "MeatPartPurchase.findByWeight", query = "SELECT m FROM MeatPartPurchaseEntity m WHERE m.weight = :weight"),
    @NamedQuery(name = "MeatPartPurchase.findByPrice", query = "SELECT m FROM MeatPartPurchaseEntity m WHERE m.price = :price")})
public class MeatPartPurchaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;
    public PurchaseEntity getPurchaseId() {
		return purchaseId;
	}

	@JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private MeatCategoryEntity categoryId;
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private PurchaseEntity purchaseId;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private MeatTypesEntity typeId;
    @JoinColumn(name = "calculation_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private CalculationEntity calculationId;

    public MeatPartPurchaseEntity() {
    }

    public MeatPartPurchaseEntity(Integer id) {
        this.id = id;
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Double getWeight() {
        return weight;
    }
    
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public MeatCategoryEntity getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(MeatCategoryEntity categoryId) {
        this.categoryId = categoryId;
    }
    
    public PurchaseEntity getInvoiceId() {
        return purchaseId;
    }
    
    public void setPurchaseId(PurchaseEntity purchaseId) {
        this.purchaseId = purchaseId;
    }
    
    public MeatTypesEntity getTypeId() {
        return typeId;
    }
    
    public void setTypeId(MeatTypesEntity typeId) {
        this.typeId = typeId;
    }
    
    public CalculationEntity getCalculationId() {
        return calculationId;
    }
    
    public void setCalculationId(CalculationEntity calculationId) {
        this.calculationId = calculationId;
    }
//</editor-fold>
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeatPartPurchaseEntity)) {
            return false;
        }
        MeatPartPurchaseEntity other = (MeatPartPurchaseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.MeatPartEntity[ id=" + id + " ]";
        return String.valueOf(id);
    }
    
}
