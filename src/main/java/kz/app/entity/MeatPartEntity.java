package kz.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "meat_part", schema = "", catalog = "teameat")
public class MeatPartEntity {
	private int id;
	private int idCategory;
	private int idType;
	private int idInvoice;
	private Double quantity;
	private Double price;
	
	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
	@Column(name = "category_id")
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	@Basic
	@Column(name = "type_id")
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	
	@Basic
	@Column(name = "weight")
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@Basic
	@Column(name = "price")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Basic
	@Column(name = "invoice_id")
	public int getIdInvoice() {
		return idInvoice;
	}
	public void setIdInvoice(int idInvoice) {
		this.idInvoice = idInvoice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idCategory;
		result = prime * result + idInvoice;
		result = prime * result + idType;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeatPartEntity other = (MeatPartEntity) obj;
		if (id != other.id)
			return false;
		if (idCategory != other.idCategory)
			return false;
		if (idInvoice != other.idInvoice)
			return false;
		if (idType != other.idType)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
}
