package kz.app;

import kz.app.beans.UserLoginView;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.entity.InvoiceEntity;
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
import java.util.Date;
import java.util.List;

@ManagedBean(name="purchaseController")
@SessionScoped
public class PurchaseController extends AbstractMeatPartController{

    private PurchaseEntity purchase;
    private List<SupplierEntity> listSupplier;
    FacesContext fc;	
    UserLoginView user;
    
    private List<String> buf;
    FacesMessage msg = null;
    
    // Внесенный платеж
    private Double sumInput=null;
    
    // сдача
    private double renting = 0.0;
    
    private static MeatPartPurchaseDao meatPartPurchaseDao;
    
    public List<SupplierEntity> getListSupplier() {
    	listSupplier = ApplicationController.suppliers;
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
        
        
        buf = new ArrayList<>();
        fc = FacesContext.getCurrentInstance();
        user = (UserLoginView) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "userLoginView");
        purchase.setReceiver(user.getUsername());
        meatParts = new ArrayList<>();
        for(int i = 0; i < 1; i++) {
            meatParts.add(new MeatPart());
        }        
        categories   = ApplicationController.categories;
        listSupplier = ApplicationController.suppliers;
        types        = ApplicationController.types;
    }
    
    @Override
    public void updateOrder() {
    	buf.clear();
    	for(MeatPart part : meatParts) {
            if(part.getWeight() == null) {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено. Для сохранения введите вес!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            if(null != part.getCategory())
                buf.add(part.getCategory().getName());
           
        }
        if(buf.isEmpty()) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено. Для сохранения необходим хотя бы один товар!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        
        
        purchase.setTotalAmount(getTotalSalesAmount());
        purchase.setDate(new Date());
        meatPartPurchaseDao.savePurchase(purchase);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartPurchaseDao.saveMeatPartPurchase(MeatPartPurchaseConverter.convertMeatPartPurchaseToEntity(e, purchase, null));
        });
        
        clear();
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

	public Double getSumInput() {
		return sumInput;
	}

	public void setSumInput(Double sumInput) { 	
		this.sumInput = sumInput;
	}
	
	
	 /*Сдача*/
    public Double getRenting() {
        if(sumInput == null) {
            return 0.0;
        }
        else
            return ( sumInput - meatParts.stream().mapToDouble(MeatPart::getRevenue).sum() );
    }
    
    public void clear() {
        purchase = new PurchaseEntity();
        
        purchase.setReceiver(user.getUsername());
        /*TODO: Потом переделать
         */
        if (listSupplier!=null)        	
        	for (SupplierEntity supItem : listSupplier) {
        		if (supItem.getId() == 1) {
        			purchase.setSupplierId(supItem);
        			break;
        		}
        	}
        meatParts = new ArrayList<>();
        for(int i = 0; i < 1; i++) {
            meatParts.add(new MeatPart());
        }
        sumInput = null;
        renting = 0.0;
    }
 
    @Override
    public void resetCategoryTypePrice(MeatPart selectedPart){
    	selectedPart.setCategory(getMtd().getMeatCategoryByBarcode(selectedPart.getBarcode()));
    	selectedPart.setType( getMtd().getMeatTypeByBarcode(selectedPart.getBarcode()));
    	selectedPart.setPrice( getMtd().getMeatTypeByBarcode(selectedPart.getBarcode()).getPrice_zakup());
    }
    
    public void update() {
    
        listSupplier = ApplicationController.suppliers;
       
    }
}
