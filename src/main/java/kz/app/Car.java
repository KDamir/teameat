package kz.app;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Car {
	private String number;
	private String color;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Car() {}
	
	public Car(String num, String col) {
		this.number = num;
		this.color = col;
	}
}
