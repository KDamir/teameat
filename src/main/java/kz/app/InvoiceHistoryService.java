/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import kz.app.dao.InvoiceDao;
import kz.app.dao.MeatPartDao;
import kz.app.entity.InvoiceEntity;
import kz.app.utils.HibernateUtil;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.entity.ReceiverEntity;
import kz.app.utils.Constants;

/**
 *
 * 
 */
@ManagedBean(name = "historyService")
@SessionScoped
public class InvoiceHistoryService extends AbstractMeatPartController{
    private List<InvoiceEntity> listInvoice;
    private InvoiceEntity selectedInvoice;
    private List<ReceiverEntity> listReceiver;

    private Date begin;
    private Date end;
    private boolean afterEditPressed;
    
    private static InvoiceDao dao;
    private static MeatPartDao meatPartDao;
    
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
        dao = new InvoiceDao();
        meatPartDao = ApplicationController.dao;
        selectedInvoice = null;
        categories = ApplicationController.categories;
        types = ApplicationController.types;
        listReceiver = ApplicationController.receivers;

        begin = new Date();
        end = new Date();
        //searchInvoice();
        beginLoc = LocalDate.now();
        endLoc = LocalDate.now().plusDays(1);
        
        
        beginD = Date.from(beginLoc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        endD = Date.from(endLoc.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        beginSql = new java.sql.Date(beginD.getTime());
        endSql = new java.sql.Date(endD.getTime());
        currentInvoice();
    }
    
    public void onEdit(InvoiceEntity invoiceS) {
        afterEditPressed=true;
        selectedInvoice = invoiceS;
        HibernateUtil.getSession().beginTransaction();
        meatParts = dao.getListMeatPart(selectedInvoice)
                        .stream()
                        .map(MeatPartConverter::convertEntityToMeatPart)
                        .collect(Collectors.toList());
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void searchInvoice() {
        beginSql = new java.sql.Date(beginD.getTime());
        endSql = new java.sql.Date(endD.getTime());
        listInvoice = dao.getListInvoice(beginSql,endSql);
    }
    
    public void currentInvoice() {
        listInvoice = dao.getListInvoice(beginSql,endSql);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public List<ReceiverEntity> getListReceiver() {
        return listReceiver;
    }

    public void setListReceiver(List<ReceiverEntity> listReceiver) {
        this.listReceiver = listReceiver;
    }
    
    public List<InvoiceEntity> getListInvoice() {
        return listInvoice;
    }
    
    public InvoiceEntity getSelectedInvoice() {
        return selectedInvoice;
    }
    
    public void setSelectedInvoice(InvoiceEntity selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
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
        selectedInvoice.setDate(new Date());
        selectedInvoice.setTotalAmount(meatParts.stream().mapToDouble(MeatPart::getRevenue).sum());
        dao.updateInvoice(selectedInvoice, meatParts.stream()
                                            .map(mp -> MeatPartConverter.convertMeatPartToEntity(mp, selectedInvoice, null))
                                            .collect(Collectors.toList())
        );
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
    }

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartDao.deleteMeatPart(MeatPartConverter.convertMeatPartToEntity(meatParts.get(idx), selectedInvoice, null));
        meatParts.remove(idx);
    }
    @Override
    public void deleteRow(MeatPart part) {
        meatPartDao.deleteMeatPart(MeatPartConverter.convertMeatPartToEntity(part, selectedInvoice, null));
        meatParts.remove(part);
    }
    
    
}
