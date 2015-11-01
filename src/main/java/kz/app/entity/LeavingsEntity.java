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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kuat
 */
@Entity
@Table(name = "leavings")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Leavings.findAll", query = "SELECT i FROM LeavingsEntity i")})
 public class LeavingsEntity implements Serializable {
    @Basic(optional = false)
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barcode")
    private BigInteger barcode;
    public BigInteger getBarcode() {
		return barcode;
	}



	public String getName() {
		return name;
	}



	public double getInv_quantity() {
		return inv_quantity;
	}



	public double getOstatok() {
		return ostatok;
	}



	public double getSold() {
		return sold;
	}



	public double getSupplied() {
		return supplied;
	}



	public double getPrice() {
		return price;
	}



	public double getPrice_zakup() {
		return price_zakup;
	}



	public double getSold_sum() {
		return sold_sum;
	}



	public double getSupplied_sum() {
		return supplied_sum;
	}



	public double getProfit() {
		return profit;
	}



	public String getSupplier() {
		return supplier;
	}

	public Double getReturning() {
		return returning;
	}




	@Column(name = "name")
    private String name;
    @Column(name = "inv_quantity")
    private Double inv_quantity;
    @Column(name = "ostatok")
    private Double ostatok;
    @Column(name = "sold")
    private Double sold;
    
	@Column(name = "supplied")
    private Double supplied;
    @Column(name = "returning")
    private Double returning;
    @Column(name = "price")
    private Double price;
    @Column(name = "price_zakup")
    private Double price_zakup;
    @Column(name = "sold_sum")
    private Double sold_sum;
    @Column(name = "supplied_sum")
    private Double supplied_sum;
    @Column(name = "profit")
    private Double profit;
    @Column(name = "supplier")
    private String supplier;
   
    

    public LeavingsEntity() {
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
