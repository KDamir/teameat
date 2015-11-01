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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 *
 * @author 
 */
@Entity
@Table(name = "kassa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kassa.findAll", query = "SELECT m FROM KassaEntity m"),
    @NamedQuery(name = "Kassa.findById", query = "SELECT m FROM KassaEntity m WHERE m.id = :id"),
    @NamedQuery(name = "Kassa.findBySum", query = "SELECT m FROM KassaEntity m WHERE m.sum = :sum")})
public class KassaEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    
    
    public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}


	@Id
   
    @Column(name = "sum")
    private Double sum = null;
    
    @Column(name = "date")
    private java.util.Date date;
//    @OneToMany(mappedBy = "categoryId")
//    private Collection<MeatPart> meatPartCollection;
//    @OneToMany(mappedBy = "categoryId")
//    private Collection<MeatTypes> meatTypesCollection;

    public KassaEntity() {
    }

    public KassaEntity(Double sum) {
        this.sum = sum;
    }
    public KassaEntity(int id) {
    	this.id = id;
    }
    
    public KassaEntity(Double sum, int id) {
        this.sum = sum;
        this.id = id;
    }
    
    public KassaEntity(Double sum, int id, Date date) {
        this.sum = sum;
        this.id = id;
        this.date = date;
    }
    
    public KassaEntity(Date date) {

        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	

	



    @Override
    public String toString() {
//        return "kz.app.entity.MeatCategoryEntity[ name=" + name + " ]";
        return String.valueOf(id);
    }
    
}
