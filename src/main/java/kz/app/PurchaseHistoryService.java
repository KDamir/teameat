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

import kz.app.dao.MeatPartPurchaseDao;
import kz.app.dao.PurchaseDao;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.SupplierEntity;
import kz.app.utils.Constants;

/**
 *
 * 
 */
@ManagedBean(name = "purchaseHistoryService")
@SessionScoped
public class PurchaseHistoryService extends AbstractMeatPartController{
    private List<PurchaseEntity> listPurchase;
    private PurchaseEntity selectedPurchase;
    private List<SupplierEntity> listSupplier;

    private Date begin;
    private Date end;
    private boolean afterEditPressed;
    
    private static PurchaseDao daop;
    private static MeatPartPurchaseDao meatPartPurchaseDao;
    
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
        daop = new PurchaseDao();
        meatPartPurchaseDao = ApplicationController.daop;
        selectedPurchase = null;
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
        currentPurchase();
    }
    
    public void onEdit(PurchaseEntity purchaseS) {
        afterEditPressed=true;
        selectedPurchase = purchaseS;
        HibernateUtil.getSession().beginTransaction();
/*        meatParts = daop.getListMeatPartPurchaseByPrc(selectedPurchase)
                        .stream()
                        .map(MeatPartConverter::convertPurchaseEntityToMeatPart)
                        .collect(Collectors.toList());                                         */
        List<MeatPartPurchaseEntity> lmppe = daop.getListMeatPartPurchaseByPrc(selectedPurchase);
        Stream <MeatPartPurchaseEntity> smppe = lmppe.stream();
        Stream<MeatPart> stmp = smppe.map(MeatPartConverter::convertPurchaseEntityToMeatPart);
        meatParts = stmp.collect(Collectors.toList());
        
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void searchPurchase() {
        beginSql = new java.sql.Date(beginD.getTime());
        endSql = new java.sql.Date(endD.getTime());
        listPurchase = daop.getListPurchase(beginSql,endSql);
    }
    
    public void currentPurchase() {
        listPurchase = daop.getListPurchase(beginSql,endSql);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public List<SupplierEntity> getListSupplier() {
        return listSupplier;
    }

    public void setListSupplier(List<SupplierEntity> listSupplier) {
        this.listSupplier = listSupplier;
    }
    
    public List<PurchaseEntity> getListPurchase() {
        return listPurchase;
    }
    
    public PurchaseEntity getSelectedPurchase() {
        return selectedPurchase;
    }
    
    public void setSelectedPurchase(PurchaseEntity selectedPurchase) {
        this.selectedPurchase = selectedPurchase;
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
        selectedPurchase.setDate(new Date());
        selectedPurchase.setTotalAmount(getTotalSalesAmount());
        daop.updatePurchase(selectedPurchase, meatParts.stream()
                                            .map(mp -> MeatPartConverter.convertMeatPartToPurchaseEntity(mp, selectedPurchase, null))
                                            .collect(Collectors.toList())
        );
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
    }

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartPurchaseDao.deleteMeatPartPurchase(MeatPartConverter.convertMeatPartToPurchaseEntity(meatParts.get(idx), selectedPurchase, null));
        meatParts.remove(idx);
    }
    @Override
    public void deleteRow(MeatPart part) {
        meatPartPurchaseDao.deleteMeatPartPurchase(MeatPartConverter.convertMeatPartToPurchaseEntity(part, selectedPurchase, null));
        meatParts.remove(part);
    }
    
    
}
