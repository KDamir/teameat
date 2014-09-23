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
 * @author Дамир
 */
@Entity
@Table(name = "meat_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatCategory.findAll", query = "SELECT m FROM MeatCategory m"),
    @NamedQuery(name = "MeatCategory.findById", query = "SELECT m FROM MeatCategory m WHERE m.id = :id"),
    @NamedQuery(name = "MeatCategory.findByName", query = "SELECT m FROM MeatCategory m WHERE m.name = :name")})
public class MeatCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
//    @OneToMany(mappedBy = "categoryId")
//    private Collection<MeatPart> meatPartCollection;
//    @OneToMany(mappedBy = "categoryId")
//    private Collection<MeatTypes> meatTypesCollection;

    public MeatCategory() {
    }

    public MeatCategory(String name) {
        this.name = name;
    }

    public MeatCategory(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @XmlTransient
//    public Collection<MeatPart> getMeatPartCollection() {
//        return meatPartCollection;
//    }
//
//    public void setMeatPartCollection(Collection<MeatPart> meatPartCollection) {
//        this.meatPartCollection = meatPartCollection;
//    }
//
//    @XmlTransient
//    public Collection<MeatTypes> getMeatTypesCollection() {
//        return meatTypesCollection;
//    }
//
//    public void setMeatTypesCollection(Collection<MeatTypes> meatTypesCollection) {
//        this.meatTypesCollection = meatTypesCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeatCategory)) {
            return false;
        }
        MeatCategory other = (MeatCategory) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.MeatCategory[ name=" + name + " ]";
        return String.valueOf(id);
    }
    
}
