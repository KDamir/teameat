package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="meatList")
@SessionScoped
public class MeatList {

	private List<MeatPart> listMeat;

	public List<MeatPart> getListMeat() {
		return listMeat;
	}

	public void setListMeat(List<MeatPart> listMeat) {
		this.listMeat = listMeat;
	}
	
	@PostConstruct
	public void init() {
		listMeat = new ArrayList<MeatPart>();
	}
}
