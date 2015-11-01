package kz.app;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;




public class Ostatki  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	private Integer id;
	
	private BigInteger barcode;
	private String name;
	private double quantityInv;
	private double quantityOst;
	private String supplierName;
	private double raznica;
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getBarcode() {
		return barcode;
	}



	public void setBarcode(BigInteger barcode) {
		this.barcode = barcode;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getQuantityInv() {
		return quantityInv;
	}



	public void setQuantityInv(double quantityInv) {
		this.quantityInv = quantityInv;
	}



	public double getQuantityOst() {
		return quantityOst;
	}



	public void setQuantityOst(double quantityOst) {
		this.quantityOst = quantityOst;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}



	public double getRaznica() {
		return raznica;
	}



	public void setRaznica(double raznica) {
		this.raznica = raznica;
	}


    public Ostatki(){
    	
    }
	
	public Ostatki(BigInteger barcode,String name, double quantityInv, 
						double quantityOst, String supplierName, double raznica ){
		this.barcode = barcode;
		this.name = name;
		this.quantityInv=quantityInv;
		this.quantityOst =quantityOst;
		this.supplierName =supplierName;
		this.raznica = raznica;
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
	        if (!(object instanceof Ostatki)) {
	            return false;
	        }
	        Ostatki other = (Ostatki) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
//	        return "kz.app.entity.MeatTypesEntity[ id=" + id + " ]";
	        return String.valueOf(id);
	    }
	    
	 
}
