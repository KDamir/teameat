package kz.app.dao;

import kz.app.entity.InvoiceEntity;
import kz.app.MeatPart;

import java.util.List;
import kz.app.entity.MeatPartEntity;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

public class InvoiceDao {
    public List<InvoiceEntity> getListInvoice() {
        return HibernateUtil.createQueryForList("from InvoiceEntity");
    }

    public void saveInvoice(InvoiceEntity inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MeatPartEntity> getListMeatPart(InvoiceEntity inv) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartEntity where invoiceId = :inv");
            query.setParameter("inv", inv);
            List<MeatPartEntity> list = query.list();
            return list;
    }

    public void saveInvoicePart(InvoiceEntity inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateInvoice(InvoiceEntity inv, List<MeatPartEntity> part) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().update(inv);
        part.forEach(e -> HibernateUtil.getSession().update(e));
        HibernateUtil.getSession().getTransaction().commit();
    }

}
