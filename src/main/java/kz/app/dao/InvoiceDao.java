package kz.app.dao;

import kz.app.entity.InvoiceEntity;
import kz.app.MeatPart;

import java.util.List;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

public class InvoiceDao {
    public List<InvoiceEntity> getListInvoice() {
        return HibernateUtil.getSessionfactory().getCurrentSession()
                .createQuery("from InvoiceEntity").list();
    }

    public void saveInvoice(InvoiceEntity inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MeatPart> getListInvoicePart(InvoiceEntity inv) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartEntity where idInvoice = :inv");
            query.setParameter("inv", inv.getId());
            List<MeatPart> list = query.list();
            return list;
    }

    public void saveInvoicePart(InvoiceEntity inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
