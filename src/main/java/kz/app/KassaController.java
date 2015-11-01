package kz.app;

import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.SupplierEntity;


import kz.app.entity.KassaEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



import kz.app.dao.InvoiceDao;
import kz.app.dao.KassaDao;
import kz.app.dao.MeatCategoryDao;
import kz.app.dao.MeatTypeDao;
import kz.app.dao.PurchaseDao;
import kz.app.dao.SupplierDao;
import kz.app.utils.Constants;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 17.11.2014
 * Time: 23:15
 */
@ManagedBean
@SessionScoped
public class KassaController {
    
    
    
    private KassaEntity kassa;
    private KassaDao kassaDao; 
    private InvoiceDao invoiceDao;
    private PurchaseDao purchaseDao;
    public Double lastSum;

    public Double getLastSum() {
		return lastSum;
	}


	@PostConstruct
    private void init() {
    	kassaDao = new KassaDao();
    	invoiceDao = new InvoiceDao();
    	purchaseDao = new PurchaseDao();
    	if (kassaDao.getLastKassa()==null)
    		lastSum =0.0;
    	else
    		lastSum = kassaDao.getLastKassa().getSum();
    	kassa = new KassaEntity();		
    	 	
    }


    public void addKassa(){
    	FacesContext context = FacesContext.getCurrentInstance();
    	kassa.setDate(new Date());
        if (kassa.getSum() == null)
        	return;
        kassaDao.saveKassa(kassa);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        kassa = new KassaEntity();
        
    }

    public KassaEntity getKassa() {
		return kassa;
	}


	public void setKassa(KassaEntity kassa) {
		this.kassa = kassa;
	}


	public void update() {
		if (kassaDao.getLastKassa()==null)
    		lastSum =0.0;
    	else
    		lastSum = kassaDao.getLastKassa().getSum();
		/*
        ApplicationController.refreshFromDB();
        categories       = ApplicationController.categories;
        types            = ApplicationController.types;
      
      */
        
        //selectedCategory = categories.get(0);
		
    }
	
	public void updateSum(){
		if (kassaDao.getLastKassa()==null)
    		lastSum =0.0;
    	else{
    		lastSum = kassaDao.getLastKassa().getSum();
			Date endDate = new Date();
	
	
			List<InvoiceEntity> listInvoice =  invoiceDao.getListInvoice(kassaDao.getLastKassa().getDate(), endDate);
			List<PurchaseEntity> listPurchase =  purchaseDao.getListPurchase(kassaDao.getLastKassa().getDate(), endDate);
		
			if (listInvoice != null)
				for (int i = 0; i < listInvoice.size();i++)
					lastSum = lastSum + listInvoice.get(i).getTotalAmount();
			if (listPurchase != null)
				for (int i = 0; i < listPurchase.size();i++)
					lastSum = lastSum - listPurchase.get(i).getTotalAmount();
    	}
	}

//</editor-fold>
}
