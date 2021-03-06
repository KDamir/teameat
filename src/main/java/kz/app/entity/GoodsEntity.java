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
@Table(name = "goodsg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goods.findAll", query = "SELECT i FROM GoodsEntity i"),
    @NamedQuery(name = "Goods.findById", query = "SELECT i FROM GoodsEntity i WHERE i.id = :id"),
    @NamedQuery(name = "Goods.findBySender", query = "SELECT i FROM GoodsEntity i WHERE i.sender = :sender"),
    @NamedQuery(name = "Goods.findByDate", query = "SELECT i FROM GoodsEntity i WHERE i.date = :date")})
public class GoodsEntity implements Serializable {
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
    private double weight;
    @Column(name = "price")
    private double price;
    @Column(name = "sender")
    private String sender;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "date")
    private Date date;
    @Column(name = "sum")
    private double sum;
    
    */
    @Size(max = 45)
    @Column(name = "categoryG")
    private String category;
    @Column(name = "meattypeG")
    private String type;
    @Column(name = "weightG")
    private double weight;
    @Column(name = "priceG")
    private double price;
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "senderG")
    private String sender;
    @Column(name = "company_nameG")
    private String company_name;
    @Column(name = "sellDateG")
    private Date date;
    @Column(name = "sumG")
    private double sum;
    
    
    public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	


    public GoodsEntity() {
    }



    public GoodsEntity(String category, String type, double weight, double price, String sender, String company_name, Date date, double sum){
  //  	this.id = id;
    	this.category = category;
    	this.type=type;
    	this.weight=weight;
    	this.price=price;
    	this.sender=sender;
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
        if (!(object instanceof GoodsEntity)) {
            return false;
        }
        GoodsEntity other = (GoodsEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "kz.app.entity.GoodsEntity[ id=" + id + " ]";
    }

*/
   
    
}
