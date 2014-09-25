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
import javax.persistence.Persistence;
import kz.app.dao.CommonDao;
import kz.app.dao.InvoiceDao;
import kz.app.entity.InvoiceEntity;
import kz.app.utils.HibernateUtil;

/**
 *
 * @author damir.keldibekov
 */
@ManagedBean(name = "historyService")
@RequestScoped
public class InvoiceHistoryService {
    private List<InvoiceEntity> listInvoice;
    
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

    public List<InvoiceEntity> getListInvoice() {
        return listInvoice;
    }
}
