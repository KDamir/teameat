/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import java.math.BigInteger;

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
 * @author Дамир
 */
@Entity
@Table(name = "meat_part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatPart.findAll", query = "SELECT m FROM MeatPartEntity m"),
    @NamedQuery(name = "MeatPart.findById", query = "SELECT m FROM MeatPartEntity m WHERE m.id = :id"),
    @NamedQuery(name = "MeatPart.findByWeight", query = "SELECT m FROM MeatPartEntity m WHERE m.weight = :weight"),
    @NamedQuery(name = "MeatPart.findByPrice", query = "SELECT m FROM MeatPartEntity m WHERE m.price = :price")})
public class MeatPartEntity implements Serializable {
    public BigInteger getBarcode() {
		return barcode;
	}

	public void setBarcode(BigInteger barcode) {
		this.barcode = barcode;
	}

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
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private MeatCategoryEntity categoryId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private InvoiceEntity invoiceId;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private MeatTypesEntity typeId;
    @JoinColumn(name = "calculation_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private CalculationEntity calculationId;
    @Column(name = "barcode")
    private BigInteger barcode;
    @Column(name = "ball")
    private boolean ball;
    
    public boolean isBall() {
        return ball;
    }

    public void setBall(boolean ball) {
        this.ball = ball;
    }

    public MeatPartEntity() {
    }

    public MeatPartEntity(Integer id) {
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
    
    public InvoiceEntity getInvoiceId() {
        return invoiceId;
    }
    
    public void setInvoiceId(InvoiceEntity invoiceId) {
        this.invoiceId = invoiceId;
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
        if (!(object instanceof MeatPartEntity)) {
            return false;
        }
        MeatPartEntity other = (MeatPartEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
//        return "kz.app.entity.MeatPartEntity[ id=" + id + " ]";
        return String.valueOf(id);
    }
    
}
