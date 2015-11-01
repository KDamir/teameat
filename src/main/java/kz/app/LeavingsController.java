package kz.app;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import kz.app.dao.InventoryDao;
import kz.app.dao.InvoiceDao;
import kz.app.dao.LeavingsDao;
import kz.app.dao.MeatPartDao;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.dao.MeatTypeDao;
import kz.app.dao.PurchaseDao;
import kz.app.entity.InventoryEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.LeavingsEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.PurchaseEntity;

import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "leavingsController")
@SessionScoped
public class LeavingsController implements Serializable {

	private static final long serialVersionUID = 1L;
	private LazyDataModel<LeavingsEntity> leavings;
	private List<LeavingsEntity> leavingsInit; 
    private LeavingsDao lvDao;
	
	public LazyDataModel<LeavingsEntity> getLeavings() {
		return leavings;
	}

	public void setLeavings(LazyDataModel<LeavingsEntity> leavings) {
		this.leavings = leavings;
	}

	private LeavingsEntity selectedLeaving;
    
	

	public LeavingsEntity getSelectedLeaving() {
		return selectedLeaving;
	}

	public void setSelectedLeaving(LeavingsEntity selectedLeaving) {
		this.selectedLeaving = selectedLeaving;
	}

	@PostConstruct
	public void init() {
		lvDao = new LeavingsDao();	
		leavingsInit = lvDao.getListLeavingsEntity();
		leavings = new LeavingsEntityDataModel(leavingsInit);
	}
	
	public void update(){
		leavingsInit = lvDao.getListLeavingsEntity();
		leavings = new LeavingsEntityDataModel(leavingsInit);
	}

	

	

}
