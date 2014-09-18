package kz.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import kz.app.dao.MeatPartDao;
import kz.app.dao.MeatPartDaoImpl;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.utils.HibernateUtil;

@ManagedBean
@SessionScoped
public class MeatService {

	private List<MeatPart> inputList;
	private Double sumWeight = 0.0;
	private Double sumProc = 0.0;
	private Double sumProdaj = 0.0;
	private List<String> list1;
	
	@PostConstruct
	public void init() {
		inputList = new ArrayList<>();
        for(int i=0;i<10;i++) {
        	inputList.add(new MeatPart());
        }
        //System.out.println("PostConctruct");
        list1 = new ArrayList<>();
        MeatPartDao meatPartDao = new MeatPartDaoImpl();
        HibernateUtil.getSession().beginTransaction();
		List<MeatTypesEntity> list = meatPartDao.getListMeatTypes();
		HibernateUtil.getSession().getTransaction().commit();
		list.forEach(e -> list1.add(e.getType()));
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
	public void calcTotal() {
		InfoPart tmp = inputList.get(0).getInfo();
		tmp.setTotalCoast(tmp.getVes_chasti()*tmp.getCena_za_kg());
	}
	
	public void calculate(MeatPart part) {
		/*for (MeatPart part:inputList) {
			part.setProc_ot_vesa(part.getWeight()*100/part.getVes_chasti());
			part.setSuma_prodaj(part.getWeight()*part.getProd_cena());
		}*/
		part.setProc_ot_vesa(part.getWeight()*100/inputList.get(0).getInfo().getVes_chasti());
		part.setSuma_prodaj(part.getWeight()*part.getProd_cena());
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
		return round(sumProc,3);
	}
	/*Общая сумма продаж*/
	public Double getTotalSum() {
		sumProdaj = 0.0;
		inputList.forEach(e -> sumProdaj += e.getSuma_prodaj());
		return sumProdaj;
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
	
}
