/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app;

import java.time.LocalDate;
import java.time.ZoneId;

import kz.app.utils.HibernateUtil;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.dao.MeatPartReturnDao;
import kz.app.dao.ReturnDao;
import kz.app.entity.MeatPartReturnEntity;
import kz.app.entity.ReturnEntity;
import kz.app.entity.SupplierEntity;
import kz.app.utils.Constants;

/**
 *
 * 
 */
@ManagedBean(name = "returningHistoryService")
@SessionScoped
public class ReturningHistoryService extends AbstractMeatPartController{
    private List<ReturnEntity> listReturning;
    private ReturnEntity selectedReturning;
    private List<SupplierEntity> listSupplier;

    private Date begin;
    private Date end;
    private boolean afterEditPressed;
    
    private static ReturnDao daoReturn;
    private static MeatPartReturnDao meatPartReturnDao;
    
    LocalDate beginLoc;
    LocalDate endLoc;
    Date beginD;
    Date endD;
    java.sql.Date beginSql;
    java.sql.Date endSql;

    public boolean isAfterEditPressed() {
        return afterEditPressed;
    }

    @PostConstruct
    public void init() {
        daoReturn = new ReturnDao();
        meatPartReturnDao = ApplicationController.daor;
        selectedReturning = null;
        categories = ApplicationController.categories;
        types = ApplicationController.types;
        listSupplier = ApplicationController.suppliers;

        begin = new Date();
        end = new Date();
        //searchInvoice();
        beginLoc = LocalDate.now();
        endLoc = LocalDate.now().plusDays(1);
        
        
        beginD = Date.from(beginLoc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        endD = Date.from(endLoc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        beginSql = new java.sql.Date(beginD.getTime());
        endSql = new java.sql.Date(endD.getTime());
        currentReturning();
    }
    
    public void onEdit(ReturnEntity returning) {
        afterEditPressed=true;
        selectedReturning = returning;
        HibernateUtil.getSession().beginTransaction();
/*        meatParts = daop.getListMeatPartPurchaseByPrc(selectedPurchase)
                        .stream()
                        .map(MeatPartConverter::convertPurchaseEntityToMeatPart)
                        .collect(Collectors.toList());                                         */
        List<MeatPartReturnEntity> lmppe = daoReturn.getListMeatPartReturnByPrc(selectedReturning);
        Stream <MeatPartReturnEntity> smppe = lmppe.stream();
        Stream<MeatPart> stmp = smppe.map(MeatPartConverter::convertReturnEntityToMeatPart);
        meatParts = stmp.collect(Collectors.toList());
        
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void searchReturning() {
        beginSql = new java.sql.Date(beginD.getTime());
        endSql = new java.sql.Date(endD.getTime());
        listReturning = daoReturn.getListReturn(beginSql,endSql);
    }
    
    public void currentReturning() {
        listReturning = daoReturn.getListReturn(beginSql,endSql);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public List<SupplierEntity> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<SupplierEntity> listSupplier) {
        this.listSupplier = listSupplier;
    }
    
    
    public List<ReturnEntity> getListReturning() {
		return listReturning;
	}

	public void setListReturning(List<ReturnEntity> listReturning) {
		this.listReturning = listReturning;
	}

	public ReturnEntity getSelectedReturning() {
		return selectedReturning;
	}

	public void setSelectedReturning(ReturnEntity selectedReturning) {
		this.selectedReturning = selectedReturning;
	}

	public Date getBegin() {
        return begin;
    }
    
    public void setBegin(Date begin) {
        this.begin = begin;
    }
    
    public Date getEnd() {
        return end;
    }
    
    public void setEnd(Date end) {
        this.end = end;
    }
//</editor-fold>

    @Override
    public void updateOrder() {
        FacesContext context = FacesContext.getCurrentInstance();
        selectedReturning.setDate(new Date());
        selectedReturning.setTotalAmount(getTotalSalesAmount());
        daoReturn.updateReturn(selectedReturning, meatParts.stream()
                                            .map(mp -> MeatPartConverter.convertMeatPartToReturnEntity(mp, selectedReturning, null))
                                            .collect(Collectors.toList())
        );
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
    }

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartReturnDao.deleteMeatPartReturn(MeatPartConverter.convertMeatPartToReturnEntity(meatParts.get(idx), selectedReturning, null));
        meatParts.remove(idx);
    }
    @Override
    public void deleteRow(MeatPart part) {
        meatPartReturnDao.deleteMeatPartReturn(MeatPartConverter.convertMeatPartToReturnEntity(part, selectedReturning, null));
        meatParts.remove(part);
    }
    
    
}
