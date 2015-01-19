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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Дамир
 */
@Entity
@Table(name = "meat_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeatTypes.findAll", query = "SELECT m FROM MeatTypesEntity m"),
    @NamedQuery(name = "MeatTypes.findById", query = "SELECT m FROM MeatTypesEntity m WHERE m.id = :id"),
    @NamedQuery(name = "MeatTypes.findByBarcode", query = "SELECT m FROM MeatTypesEntity m WHERE m.barcode = :barcode"),
    @NamedQuery(name = "MeatTypes.findByName", query = "SELECT m FROM MeatTypesEntity m WHERE m.name = :name")})
public class MeatTypesEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Column(name = "barcode")
    private BigInteger barcode;
    @Column(name = "price_std")
    private Double price_std;
    @Column(name = "reward")
    private Double reward;


	//    @OneToMany(mappedBy = "typeId")
//    private Collection<MeatPart> meatPartCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private MeatCategoryEntity categoryId;

    public BigInteger getBarcode() {
		return barcode;
	}

	public void setBarcode(BigInteger barcode) {
		this.barcode = barcode;
	}

	public Double getPrice_std() {
		return price_std;
	}

	public void setPrice_std(Double price_std) {
		this.price_std = price_std;
	}

	public MeatTypesEntity() {
    }

    public MeatTypesEntity(Integer id) {
        this.id = id;
    }

    public MeatTypesEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getReward() {
		return reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}
    
//    @XmlTransient
//    public Collection<MeatPart> getMeatPartCollection() {
//        return meatPartCollection;
//    }
//
//    public void setMeatPartCollection(Collection<MeatPart> meatPartCollection) {
//        this.meatPartCollection = meatPartCollection;
//    }

    public MeatCategoryEntity getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(MeatCategoryEntity categoryId) {
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
        if (!(object instanceof MeatTypesEntity)) {
            return false;
        }
        MeatTypesEntity other = (MeatTypesEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "kz.app.entity.MeatTypesEntity[ id=" + id + " ]";
        return String.valueOf(id);
    }
    
}
