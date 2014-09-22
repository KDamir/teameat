/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kz.app.dao;

import kz.app.Invoice;
import kz.app.MeatPart;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

import java.util.List;

/**
 *
 * @author Дамир
 */
public class InvoiceDaoImpl implements InvoiceDao{

    @Override
    public List<Invoice> getListInvoice() {
        return HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from InvoiceEntity").list();
    }

    @Override
    public void saveInvoice(Invoice inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MeatPart> getListInvoicePart(Invoice inv) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartEntity where idInvoice = :inv");
            query.setParameter("inv", inv.getId());
            List<MeatPart> list = query.list();
            return list;
    }

    @Override
    public void saveInvoicePart(Invoice inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
