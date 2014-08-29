package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class TabbedView {
	
	private List<Car> list = new ArrayList<Car>();
	
	public List<Car> getList() {
		return list;
	}
	public void setList(List<Car> list) {
		this.list = list;
	}
	
	@PostConstruct
	public void init(){
		list.add(new Car("123","green"));
		list.add(new Car("345", "black"));
	}

}
