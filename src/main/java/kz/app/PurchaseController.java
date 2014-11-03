package kz.app;

import kz.app.dao.MeatPartPurchaseDao;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.SupplierEntity;
import kz.app.utils.MeatPartPurchaseConverter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class PurchaseController extends AbstractMeatPartController{

    private PurchaseEntity purchase;
    private List<SupplierEntity> listSupplier;
    
    // Внесенный платеж
    private double sumInput=0.0;
    // сдача
    private double renting = 0.0;
    
    private static MeatPartPurchaseDao meatPartPurchaseDao;
    
    public List<SupplierEntity> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<SupplierEntity> listSupplier) {
        this.listSupplier = listSupplier;
    }

    public PurchaseEntity getPurchase() {
        return purchase;
    }

    public void setPurchase(PurchaseEntity purchase) {
        this.purchase = purchase;
    }

    @PostConstruct
    public void init() {
        meatPartPurchaseDao = ApplicationController.daop;
        purchase = new PurchaseEntity();
        meatParts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatParts.add(new MeatPart());
        }        
        categories   = ApplicationController.categories;
        listSupplier = ApplicationController.suppliers;
        types        = ApplicationController.types;
    }
    
    @Override
    public void updateOrder() {
        meatPartPurchaseDao.savePurchase(purchase);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartPurchaseDao.saveMeatPartPurchase(MeatPartPurchaseConverter.convertMeatPartPurchaseToEntity(e, purchase, null));
        });
        purchase = new PurchaseEntity();
        meatParts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatParts.add(new MeatPart());
        }
        FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Накладная сохранена",
                "Информация о новой накладной сохранена в базе данных");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartPurchaseDao.deleteMeatPartPurchase(MeatPartPurchaseConverter.convertMeatPartPurchaseToEntity(meatParts.get(idx), purchase, null));
        meatParts.remove(idx);
    }

	public double getSumInput() {
		return sumInput;
	}

	public void setSumInput(double sumInput) {
		this.sumInput = sumInput;
	}
	
	
	 /*Сдача*/
    public Double getRenting() {
        return ( sumInput - meatParts.stream().mapToDouble(MeatPart::getRevenue).sum() );
    }
    
    
    
}
