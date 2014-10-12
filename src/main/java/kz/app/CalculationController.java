package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.utils.Constants;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class CalculationController {

    private List<MeatPart> meatParts;
    private List<MeatTypesEntity> types;
    private List<MeatCategoryEntity> categories;
    private String meatInfo;
    private Double pricePerKilo = 0.0;
    private Double carcassWeight = 0.0;
    private Double totalCost = 0.0;

    private MeatPartDao meatPartDao;

    @PostConstruct
    public void init() {
        meatParts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            meatParts.add(new MeatPart());
        }
//        jpa = new CommonDao(Persistence
//                    .createEntityManagerFactory("kz.app_teameat_war_0.0.1-SNAPSHOTPU"));
//        types = jpa.findMeatTypesEntityEntities();
        meatPartDao = new MeatPartDao();

        categories = meatPartDao.getCategoriesList();
        categories.add(0, getBlankCategory());

        types = meatPartDao.getTypesList();
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

    public void updateOrder() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
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

    public List<MeatTypesEntity> filterTypes(MeatCategoryEntity selectedCategory) {
        List<MeatTypesEntity> filteredTypes = types.stream()
                .filter(type -> type.getCategoryId().equals(selectedCategory))
                .map(type -> type)
                .collect(Collectors.toList());
        filteredTypes.add(0, getBlankType());
        return filteredTypes;
    }

    public List<MeatCategoryEntity> getCategories() {
        return categories;
    }

    public Double getTotalCost() {
        totalCost = pricePerKilo * carcassWeight;
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public MeatCategoryEntity getBlankCategory() {
        MeatCategoryEntity blankCategory = new MeatCategoryEntity();
        blankCategory.setName("");
        return blankCategory;
    }

    public MeatTypesEntity getBlankType() {
        MeatTypesEntity blankType = new MeatTypesEntity();
        blankType.setName("");
        return blankType;
    }

    public void addNewMeatPart() {
        meatParts.add(new MeatPart());
    }

    public void deleteLastMeatPart() {
        meatParts.remove(meatParts.size() - 1);
    }

    public void resetType(MeatPart selectedPart) {
        selectedPart.setType(filterTypes(selectedPart.getCategory()).get(0));
    }
}
