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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import kz.app.entity.CalculationEntity;
import kz.app.utils.MeatPartConverter;

@ManagedBean
@SessionScoped
public class CalculationController extends AbstractMeatPartController {

    private Double totalCost = 0.0;
    
    private CalculationEntity calc;

    private MeatPartDao meatPartDao;

    @PostConstruct
    public void init() {
        calc = new CalculationEntity();
        meatParts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            addNewMeatPart();
        }
        meatPartDao = new MeatPartDao();
        categories = ApplicationController.categories;
        categories.add(0, getBlankCategory());

        types = ApplicationController.types;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public CalculationEntity getCalc() {
        return calc;
    }
    
    public void setCalc(CalculationEntity calc) {
        this.calc = calc;
    }
//</editor-fold>
    

    @Override
    public void updateOrder() {
        FacesContext context = FacesContext.getCurrentInstance();
        calc.setDate(new Date());
        meatPartDao.saveCalculation(calc);
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, null, calc));
        });
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        //calc = new CalculationEntity();
    }

    /*Общий процент*/
    public Double getTotalPercent() {
        return meatParts.stream().mapToDouble(e -> e.calculateWeightPercent(calc.getVesChasti())).sum();
    }

    public Double getTotalCost() {
        totalCost = calc.getCenaZaKg() * calc.getVesChasti();
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}
