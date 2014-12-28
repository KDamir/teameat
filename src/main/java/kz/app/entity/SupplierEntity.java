/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Куат
 */
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT r FROM SupplierEntity r"),
    @NamedQuery(name = "Supplier.findByCompanyName", query = "SELECT r FROM SupplierEntity r WHERE r.companyName = :companyName"),
    @NamedQuery(name = "Supplier.findById", query = "SELECT r FROM SupplierEntity r WHERE r.id = :id")})
public class SupplierEntity implements Serializable {
    @OneToMany(mappedBy = "supplierId")
    private Collection<PurchaseEntity> purchaseEntityCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "company_name")
    private String companyName;
    @Basic(optional = true)
    @Size(max = 10)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic(optional = true)
    @NotNull
    @Size(max = 50)
    @Column(name = "address")
    private String address;
    @Basic(optional = true)
    @NotNull
    @Size(max = 1000)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
//    @OneToMany(mappedBy = "receiverId")
//    private Collection<Invoice> invoiceCollection;

    public SupplierEntity() {
    }

    public SupplierEntity(String companyName) {
        this.companyName = companyName;
    }

    public SupplierEntity(String companyName, int id) {
        this.companyName = companyName;
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyName != null ? companyName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.companyName == null && other.companyName != null) || (this.companyName != null && !this.companyName.equals(other.companyName))) {
            return false;
    }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.ReceiverEntity[ companyName=" + companyName + " ]";
        return String.valueOf(id);
    }

    @XmlTransient
    public Collection<PurchaseEntity> getPurchaseEntityCollection() {
        return purchaseEntityCollection;
    }

    public void setPurchaseEntityCollection(Collection<PurchaseEntity> purchaseEntityCollection) {
        this.purchaseEntityCollection = purchaseEntityCollection;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
