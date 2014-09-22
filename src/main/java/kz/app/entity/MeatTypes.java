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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "meat_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatTypes.findAll", query = "SELECT m FROM MeatTypes m"),
    @NamedQuery(name = "MeatTypes.findById", query = "SELECT m FROM MeatTypes m WHERE m.id = :id"),
    @NamedQuery(name = "MeatTypes.findByType", query = "SELECT m FROM MeatTypes m WHERE m.type = :type")})
public class MeatTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "typeId")
    private Collection<MeatPart> meatPartCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private MeatCategory categoryId;

    public MeatTypes() {
    }

    public MeatTypes(Integer id) {
        this.id = id;
    }

    public MeatTypes(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlTransient
    public Collection<MeatPart> getMeatPartCollection() {
        return meatPartCollection;
    }

    public void setMeatPartCollection(Collection<MeatPart> meatPartCollection) {
        this.meatPartCollection = meatPartCollection;
    }

    public MeatCategory getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(MeatCategory categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof MeatTypes)) {
            return false;
        }
        MeatTypes other = (MeatTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.MeatTypes[ id=" + id + " ]";
    }
    
}
