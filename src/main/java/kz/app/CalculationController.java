package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatTypesEntity;
import kz.app.utils.HibernateUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CalculationController {

    private List<MeatPart> meatParts;
    private List<MeatTypesEntity> listMeatTypes;
    private String meatInfo;
    private Double pricePerKilo = 0.0;
    private Double carcassWeight = 0.0;
    private Double totalCost = 0.0;

    MeatPartDao meatPartDao;
//    CommonDao jpa;

    @PostConstruct
    public void init() {
        meatParts = new ArrayList<>();
        for(int i=0;i<10;i++) {
                meatParts.add(new MeatPart());
        }
//        jpa = new CommonDao(Persistence
//                    .createEntityManagerFactory("kz.app_teameat_war_0.0.1-SNAPSHOTPU"));
//        listMeatTypes = jpa.findMeatTypesEntityEntities();
        meatPartDao = new MeatPartDao();
        
        HibernateUtil.getSession().beginTransaction();
        listMeatTypes = meatPartDao.getListMeatTypes();
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public List<MeatPart> getMeatParts() {
            return meatParts;
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

    public void setCarcassWeight(Double carcassWeight) {
        this.carcassWeight = carcassWeight;
    }
    public Double getCarcassWeight() {
        return carcassWeight;
    }

    public void updateOrder(int index) {
        MeatPart currentPart = meatParts.get(index);

        if (index == meatParts.size() - 1) {
            // Здесь будет вызов insert'а из ДАО
            MeatPart meatPart = new MeatPart();
            meatParts.add(meatPart);
        } else {
            // Здесь будет вызов update'а из ДАО
        }
    }
    public void calculate() {
    }
	
    /*Общий вес*/
    public Double getTotalWeight() {
        return meatParts.stream().mapToDouble(MeatPart::getWeight).sum();
    }
    /*Общий процент*/
    public Double getTotalPercent() {
        return meatParts.stream().mapToDouble(e -> e.calculateWeightPercent(carcassWeight)).sum();
    }
    /*Общая сумма продаж*/
    public Double getTotalSalesAmount() {
        return meatParts.stream().mapToDouble(MeatPart::getRevenue).sum();
    }

    public List<MeatTypesEntity> getListMeatTypes() {
        return listMeatTypes;
    }
        
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public Double getTotalCost() {
        totalCost = pricePerKilo * carcassWeight;
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
}
