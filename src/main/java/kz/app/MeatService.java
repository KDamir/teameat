package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MeatService {

	private List<MeatPart> inputList;
	private Double sumWeight = 0.0;
	private Double sumProc = 0.0;
	private Double sumProdaj = 0.0;

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

	public void updateOrder(MeatPart currentItem) {
        if ((inputList.lastIndexOf(currentItem) == inputList.size() - 1) && currentItem.getWeight() != 0.0 ) {
            MeatPart meatPart = new MeatPart();
            inputList.add(meatPart);
        }
		//inputList.forEach(System.out::println);
	}
	
	public void calculate() {
		for(MeatPart part:inputList) {
			if(part.getVes_chasti() != 0)
				part.setProc_ot_vesa(part.getWeight()*100/part.getVes_chasti());
			part.setSuma_prodaj(part.getWeight()*part.getProd_cena());
		}
	}
	/*Общий вес*/
	public Double calcWeight() {
		sumWeight = 0.0;
		inputList.forEach(e -> sumWeight += e.getWeight());
		return sumWeight;
	}
	/*Общий процент*/
	public Double calcProc() {
		sumProc = 0.0;
		inputList.forEach(e -> sumProc += e.getProc_ot_vesa());
		return sumProc;
	}
	/*Общая сумма продаж*/
	public Double calcSum() {
		sumProdaj = 0.0;
		inputList.forEach(e -> sumProdaj += e.getSuma_prodaj());
		return sumProdaj;
	}
	
}
