package kz.app;

import java.io.Serializable;

public class Car implements Serializable {

	public String category;
	public String name;
	public String quantity;
    public String price;

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


    public Car(String category, String name, String quantity, String price) {
		this.category = category;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	
    

}
