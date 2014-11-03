/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Куат
 */
@Entity
@Table(name = "purchase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Purchase.findAll", query = "SELECT i FROM PurchaseEntity i"),
    @NamedQuery(name = "Purchase.findById", query = "SELECT i FROM PurchaseEntity i WHERE i.id = :id"),
    @NamedQuery(name = "Purchase.findByReceiver", query = "SELECT i FROM PurchaseEntity i WHERE i.receiver = :receiver"),
    @NamedQuery(name = "Purchase.findByDate", query = "SELECT i FROM PurchaseEntity i WHERE i.date = :date")})
public class PurchaseEntity implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalAmount")
    private double totalAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "paidAmount")
    private double paidAmount;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "date")
    private Date date;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
//    private Collection<MeatPart> meatPartCollection;
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    @ManyToOne
    private SupplierEntity supplierId;

    public PurchaseEntity() {
    }

    public PurchaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    @XmlTransient
//    public Collection<MeatPart> getMeatPartCollection() {
//        return meatPartCollection;
//    }
//
//    public void setMeatPartCollection(Collection<MeatPart> meatPartCollection) {
//        this.meatPartCollection = meatPartCollection;
//    }

    public SupplierEntity getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(SupplierEntity supplierId) {
        this.supplierId = supplierId;
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
        if (!(object instanceof PurchaseEntity)) {
            return false;
        }
        PurchaseEntity other = (PurchaseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.InvoiceEntity[ id=" + id + " ]";
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }
    
}
