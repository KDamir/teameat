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
public class CalculationController extends AbstractMeatPartController {

    private String meatInfo;
    private Double pricePerKilo = 0.0;
    private Double carcassWeight = 0.0;
    private Double totalCost = 0.0;

    private MeatPartDao meatPartDao;

    @PostConstruct
    public void init() {
        meatParts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            addNewMeatPart();
        }
        meatPartDao = new MeatPartDao();

        categories = meatPartDao.getCategoriesList();
        categories.add(0, getBlankCategory());

        types = meatPartDao.getTypesList();
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

    @Override
    public void updateOrder() {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
    }

    /*Общий процент*/
    public Double getTotalPercent() {
        return meatParts.stream().mapToDouble(e -> e.calculateWeightPercent(carcassWeight)).sum();
    }

    public Double getTotalCost() {
        totalCost = pricePerKilo * carcassWeight;
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}
