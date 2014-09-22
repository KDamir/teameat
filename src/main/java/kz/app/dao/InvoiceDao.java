package kz.app.dao;

import kz.app.Invoice;
import kz.app.MeatPart;

import java.util.List;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

public class InvoiceDao {
    public List<Invoice> getListInvoice() {
        return HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from InvoiceEntity").list();
    }

    public void saveInvoice(Invoice inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<MeatPart> getListInvoicePart(Invoice inv) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartEntity where idInvoice = :inv");
            query.setParameter("inv", inv.getId());
            List<MeatPart> list = query.list();
            return list;
    }

    public void saveInvoicePart(Invoice inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
