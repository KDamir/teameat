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

	private List<MeatPart> inputList;

	@PostConstruct
	public void init() {
		inputList = new ArrayList<>();
        MeatPart mp = new MeatPart();
        inputList.add(mp);
		//System.out.println("PostConctruct");
	}
	public List<MeatPart> getInputList() {
		return inputList;
	}

	public void setInputList(List<MeatPart> inputList) {
		this.inputList = inputList;
	}
/*
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
*/
	public void updateOrder(MeatPart currentItem) {
        // TODO: Добавить расчёт процента от общего веса и суммы продаж
        if (inputList.lastIndexOf(currentItem) == inputList.size() - 1) {
            MeatPart meatPart = new MeatPart();
            inputList.add(meatPart);
        }
		inputList.forEach(System.out::println);
	}
	
}
