package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MeatService {
	@ManagedProperty("#{meatPart}")
	private MeatPart meatPart;
	
	//@ManagedProperty("#{meatList}")
	private List<MeatPart> inputList;

	@PostConstruct
	public void init() {
		inputList = new ArrayList<MeatPart>();
		//System.out.println("PostConctruct");
	}
	public List<MeatPart> getInputList() {
		return inputList;
	}

	public void setInputList(List<MeatPart> inputList) {
		this.inputList = inputList;
	}

	public MeatPart getMeatPart() {
		return meatPart;
	}

	public void setMeatPart(MeatPart meatPart) {
		this.meatPart = meatPart;
	}
	
	public Double proc_ot_vesa() {
		return meatPart.getVes()*100/meatPart.getVes_chasti();
	}
	
	public Double suma_prodaj() {
		return meatPart.getVes()*meatPart.getProd_cena();
	}
	
	public void raschet() {
		meatPart.setProc_ot_vesa(proc_ot_vesa());
		meatPart.setSuma_prodaj(suma_prodaj());
	}
	
	public void addZakaz() {
		//System.out.println(meatPart.getName() + " " + meatPart.getCena_za_kg() + " " + meatPart.getOstatki() + " " + meatPart.getProc_ot_vesa());
		meatPart.setProc_ot_vesa(proc_ot_vesa());
		meatPart.setSuma_prodaj(suma_prodaj());
		inputList.add(meatPart);
		inputList.forEach(System.out::println);
	}
	
}
