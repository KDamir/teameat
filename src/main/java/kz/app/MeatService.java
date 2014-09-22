package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatTypes;
import kz.app.utils.HibernateUtil;

@ManagedBean
@SessionScoped
public class MeatService {

	private List<MeatPart> inputList;
	private Double sumWeight = 0.0;
	private Double sumProc = 0.0;
	private Double sumProdaj = 0.0;
	private List<String> list1;
	private String meatInfo;
    private Double pricePerKilo = 0.0;
    private Double totalWeight = 0.0;
    private Double totalCost = 0.0;


    @PostConstruct
	public void init() {
		inputList = new ArrayList<>();
        for(int i=0;i<10;i++) {
        	inputList.add(new MeatPart());
        }
        //System.out.println("PostConctruct");
        list1 = new ArrayList<>();
        MeatPartDao meatPartDao = new MeatPartDao();
        HibernateUtil.getSession().beginTransaction();
		List<MeatTypes> list = meatPartDao.getListMeatTypes();
		HibernateUtil.getSession().getTransaction().commit();
		list.forEach(e -> list1.add(e.getType()));
    }
	public List<MeatPart> getInputList() {
		return inputList;
	}

    public String getMeatInfo() {
        return meatInfo;
    }

    public void setMeatInfo(String meatInfo) {
        this.meatInfo = meatInfo;
    }

    public Double getPricePerKilo() {
        return pricePerKilo;
    }

    public void setPricePerKilo(Double pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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
		inputList.forEach(e -> sumProc += e.getWeightPercent());
		return round(sumProc,3);
	}
	/*Общая сумма продаж*/
	public Double getTotalSum() {
		return inputList.stream().mapToDouble(s -> s.getProfit()).sum();
	}
	public List<String> getList1() {
		return list1;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

    public Double getTotalCost() {
        totalCost = pricePerKilo * totalWeight;
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
