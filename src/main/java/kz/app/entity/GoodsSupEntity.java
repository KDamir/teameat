/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kuat
 */
@Entity
@Table(name = "goodssup")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoodsSup.findAll", query = "SELECT i FROM GoodsSupEntity i"),
    @NamedQuery(name = "GoodsSup.findById", query = "SELECT i FROM GoodsSupEntity i WHERE i.id = :id"),
    @NamedQuery(name = "GoodsSup.findByReceiver", query = "SELECT i FROM GoodsSupEntity i WHERE i.receiver = :receiver"),
    @NamedQuery(name = "GoodsSup.findByDate", query = "SELECT i FROM GoodsSupEntity i WHERE i.date = :date")})
public class GoodsSupEntity implements Serializable {
    @Basic(optional = false)
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;   
    /*   
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @Column(name = "type")
    private String type;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;
    @Column(name = "sender")
    private String sender;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "date")
    private Date date;
    @Column(name = "sum")
    private Double sum;
    
    */
    @Size(max = 45)
    @Column(name = "category")
    private String category;
    @Column(name = "meattype")
    private String type;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "price")
    private Double price;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "receiver")
    private String receiver;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "Date")
    private Date date;
    @Column(name = "sum")
    private Double sum;
    
    
    public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	


    public GoodsSupEntity() {
    }



    public GoodsSupEntity(String category, String type, Double weight, Double price, String receiver, String company_name, Date date, Double sum){
  //  	this.id = id;
    	this.category = category;
    	this.type=type;
    	this.weight=weight;
    	this.price=price;
    	this.receiver=receiver;
    	this.company_name=company_name;
    	this.date=date;
    	this.sum = sum;
    	
    }
 /*   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
*/
    public String getReceiver() {
        return receiver;
    }

    public void setSender(String receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
    	
        return date;
    }

    public void setDate(Date date) {

    	
        this.date = date;
    }

/*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GoodsSupEntity)) {
            return false;
        }
        GoodsSupEntity other = (GoodsSupEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.GoodsSupEntity[ id=" + id + " ]";
    }

*/
   
    
}