package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class EditView {
	private List<Car> cars;
	
	@PostConstruct
	public void init() {
		cars = new ArrayList<Car>();
		cars.add(new Car("123", "white"));
		cars.add(new Car("345", "black"));
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getNumber());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit cancelled", ((Car) event.getObject()).getNumber());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
