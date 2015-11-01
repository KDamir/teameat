package kz.app.dao;

import java.math.BigInteger;
import java.util.Date;

import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatTypesEntity;

import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import kz.app.entity.MeatPartEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

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
        HibernateUtil.getSession().saveOrUpdate(inv);
        part.forEach(e -> HibernateUtil.getSession().saveOrUpdate(e));
        HibernateUtil.getSession().getTransaction().commit();
    }
    

    public List<InvoiceEntity> getListInvoice(Date begin,Date end) {
    	
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from InvoiceEntity where date between :begin and :end");
            
            query.setTimestamp("begin", begin);
            
            query.setTimestamp("end", end);
            
            List<InvoiceEntity> list = query.list();
            session.getTransaction().commit();
            if(list.isEmpty())
               return null;
            return list;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }
    }
    
    public List<MeatPartEntity> getListMeatPart(InvoiceEntity inv, MeatTypesEntity typeId) {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartEntity where invoiceId = :inv AND typeId= :typeId");
            query.setParameter("inv", inv);
            query.setParameter("typeId", typeId);
            List<MeatPartEntity> list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }
    }

}
