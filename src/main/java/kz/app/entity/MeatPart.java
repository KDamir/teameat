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
 * @author Дамир
 */
@Entity
@Table(name = "meat_part")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatPart.findAll", query = "SELECT m FROM MeatPart m"),
    @NamedQuery(name = "MeatPart.findById", query = "SELECT m FROM MeatPart m WHERE m.id = :id"),
    @NamedQuery(name = "MeatPart.findByWeight", query = "SELECT m FROM MeatPart m WHERE m.weight = :weight"),
    @NamedQuery(name = "MeatPart.findByPrice", query = "SELECT m FROM MeatPart m WHERE m.price = :price")})
public class MeatPart implements Serializable {
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
    private MeatCategory categoryId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    @ManyToOne
    private MeatTypes typeId;

    public MeatPart() {
    }

    public MeatPart(Integer id) {
        this.id = id;
    }

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

    public MeatCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(MeatCategory categoryId) {
        this.categoryId = categoryId;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public MeatTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(MeatTypes typeId) {
        this.typeId = typeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeatPart)) {
            return false;
        }
        MeatPart other = (MeatPart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.MeatPart[ id=" + id + " ]";
        return String.valueOf(id);
    }
    
}
