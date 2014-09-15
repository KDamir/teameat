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

	public void updateOrder(int index) {
        MeatPart currentPart = inputList.get(index);

        if (index == inputList.size() - 1) {
            // Здесь будет вызов insert'а из ДАО
            MeatPart meatPart = new MeatPart();
            inputList.add(meatPart);
        } else {
            // Здесь будет вызов update'а из ДАО
        }
    }
	
	public void calculate() {
		for (MeatPart part:inputList) {
			part.setProc_ot_vesa(part.getWeight()*100/part.getVes_chasti());
			part.setSuma_prodaj(part.getWeight()*part.getProd_cena());
		}
	}
	/*Общий вес*/
	public Double getTotalWeight() {
		sumWeight = 0.0;
		inputList.forEach(e -> sumWeight += e.getWeight());
		return sumWeight;
	}
	/*Общий процент*/
	public Double getTotalPercent() {
		sumProc = 0.0;
		inputList.forEach(e -> sumProc += e.getProc_ot_vesa());
		return sumProc;
	}
	/*Общая сумма продаж*/
	public Double getTotalSum() {
		sumProdaj = 0.0;
		inputList.forEach(e -> sumProdaj += e.getSuma_prodaj());
		return sumProdaj;
	}
	
}
