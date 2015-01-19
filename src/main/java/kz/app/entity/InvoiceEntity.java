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
 * @author Дамир
 */
@Entity
@Table(name = "invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM InvoiceEntity i"),
    @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM InvoiceEntity i WHERE i.id = :id"),
    @NamedQuery(name = "Invoice.findBySender", query = "SELECT i FROM InvoiceEntity i WHERE i.sender = :sender"),
    @NamedQuery(name = "Invoice.findByDate", query = "SELECT i FROM InvoiceEntity i WHERE i.date = :date")})
public class InvoiceEntity implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalReward")
    private double totalReward;
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
    @Column(name = "sender")
    private String sender;
    @Column(name = "date")
    private Date date;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
//    private Collection<MeatPart> meatPartCollection;
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    @ManyToOne
    private ReceiverEntity receiverId;

    public InvoiceEntity() {
    }

    public InvoiceEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public ReceiverEntity getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(ReceiverEntity receiverId) {
        this.receiverId = receiverId;
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
        if (!(object instanceof InvoiceEntity)) {
            return false;
        }
        InvoiceEntity other = (InvoiceEntity) object;
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

    public double getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(double totalReward) {
        this.totalReward = totalReward;
    }
    
}
