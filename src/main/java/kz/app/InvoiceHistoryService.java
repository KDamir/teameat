/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.Persistence;
import kz.app.dao.CommonDao;
import kz.app.dao.InvoiceDao;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.utils.HibernateUtil;

/**
 *
 * @author damir.keldibekov
 */
@ManagedBean(name = "historyService")
@SessionScoped
public class InvoiceHistoryService {
    private List<InvoiceEntity> listInvoice;
    private InvoiceEntity selectedInvoice;
    private List<MeatPartEntity> selectedmeatPartList;
    
    private static final InvoiceDao dao = new InvoiceDao();
//    private static final CommonDao jpa = new CommonDao(Persistence
//                    .createEntityManagerFactory("kz.app_teameat_war_0.0.1-SNAPSHOTPU"));
    
    @PostConstruct
    public void init() {
//        listInvoice = jpa.findInvoiceEntityEntities();
        HibernateUtil.getSession().beginTransaction();
        listInvoice = dao.getListInvoice();
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void onEdit(InvoiceEntity invoiceS) {
        selectedInvoice = invoiceS;
        HibernateUtil.getSession().beginTransaction();
        selectedmeatPartList = dao.getListMeatPart(selectedInvoice);
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void updateInvoice() {
        dao.updateInvoice(selectedInvoice, selectedmeatPartList);
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

    public List<MeatPartEntity> getSelectedmeatPartList() {
        return selectedmeatPartList;
    }

    public void setSelectedmeatPartList(List<MeatPartEntity> selectedmeatPartList) {
        this.selectedmeatPartList = selectedmeatPartList;
    }
    
    
}
