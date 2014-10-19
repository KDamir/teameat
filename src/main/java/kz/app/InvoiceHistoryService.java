/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app;

import kz.app.dao.InvoiceDao;
import kz.app.dao.MeatPartDao;
import kz.app.entity.InvoiceEntity;
import kz.app.utils.HibernateUtil;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author damir.keldibekov
 */
@ManagedBean(name = "historyService")
@SessionScoped
public class InvoiceHistoryService extends AbstractMeatPartController{
    private List<InvoiceEntity> listInvoice;
    private InvoiceEntity selectedInvoice;

    private static final InvoiceDao dao = new InvoiceDao();
    private Date begin;
    private Date end;
    private MeatPartDao meatPartDao;
    private boolean afterEditPressed;

    public boolean isAfterEditPressed() {
        return afterEditPressed;
    }

    @PostConstruct
    public void init() {
//        listInvoice = jpa.findInvoiceEntityEntities();
        selectedInvoice = null;
        meatPartDao = new MeatPartDao();

        categories = meatPartDao.getCategoriesList();
        categories.add(0, getBlankCategory());

        types = meatPartDao.getTypesList();
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
        java.sql.Date beginSql = new java.sql.Date(begin.getTime());
        java.sql.Date endSql = new java.sql.Date(end.getTime());
        listInvoice = dao.getListInvoice(endSql, beginSql);
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

    @Override
    public void updateOrder() {
        dao.updateInvoice(selectedInvoice, meatParts.stream()
                                            .map(mp -> MeatPartConverter.convertMeatPartToEntity(mp, selectedInvoice, null))
                                            .collect(Collectors.toList())
        );
    }
}
