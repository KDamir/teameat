package kz.app;

import java.awt.Desktop.Action;
import java.awt.Event;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import kz.app.Car;
import kz.app.CarService;
 

@ManagedBean(name="dtEditView")
@ViewScoped
public class EditView implements Serializable {
     
    private List<Car> cars1;
    
         
    @ManagedProperty("#{carService}")
    private CarService service;


	private Car selected_row;
    
	
	
    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        
    }
 
    public List<Car> getCars1() {
        return cars1;
    }
 
   
    public List<String> getCategories() {    		
        return service.getCategories();
    }
     
    public List<String> getNames() {
        return service.getNames();
    }
 
    public void setService(CarService service) {
        this.service = service;
    }
     
    public void onRowEdit(RowEditEvent event) {
    	
   // 	System.out.println(((Car)event.getObject()).getCategory());
    	
    	
        FacesMessage msg = new FacesMessage("Invoice Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
    }
  
    public void onRowEditInit(RowEditEvent event) {
    	
    	   // 	System.out.println(((Car)event.getObject()).getCategory());
    	    	System.out.println(cars1.indexOf(event.getObject()));
    	    	
    	        FacesMessage msg = new FacesMessage("Invoice Edited");
    	        FacesContext.getCurrentInstance().addMessage(null, msg);
    	        
    	        
    	        
    	    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        
    }
     
 
    public void categoryChanged(String category){
		//assign new value to localeCode
 //	   	System.out.println(getSelected_row().getCategory());
	       
	//       System.out.println(cars1.get(selected_row).getCategory());
	       
    	System.out.println(category);
			String [] colors = new String[10];
	        colors[0] = "Black";
	        colors[1] = "Black";
	        colors[2] = "Black";
	        colors[3] = "Black";
	        colors[4] = "Black";
	        colors[5] = "Black";
	        colors[6] = "Black";
	        colors[7] = "Yellow";
	        colors[8] = "Brown";
	        colors[9] = "Maroon";
	        
	        CarService.setNames(colors);
			
		
	}

	public Car getSelected_row() {
		return selected_row;
	}

	public void setSelected_row(Car selected_row) {
		this.selected_row = selected_row;
	}

	
    
}
