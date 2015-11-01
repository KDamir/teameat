package kz.app.dao;

import java.math.BigInteger;
import java.util.Date;


import java.util.List;

import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PurchaseDao {
   
    
    public List<PurchaseEntity> getListPurchase(Date begin, Date end) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from PurchaseEntity where date between :begin and :end");
            query.setTimestamp("begin", begin);
            query.setTimestamp("end", end);
            List<PurchaseEntity> list = query.list();
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
    
    public List<MeatPartPurchaseEntity> getListMeatPartPurchase(PurchaseEntity prc, MeatTypesEntity typeId) {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartPurchaseEntity where purchaseId = :prc AND typeId= :typeId");
            query.setParameter("prc", prc);
            query.setParameter("typeId", typeId);
            List<MeatPartPurchaseEntity> list = query.list();
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
    
    public List<MeatPartPurchaseEntity> getListMeatPartPurchaseByPrc(PurchaseEntity prc) {
    	//Session session = HibernateUtil.getSession();
    	
    	
        //try {
            //session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartPurchaseEntity where purchaseId = :prc");
            query.setParameter("prc", prc);
            List<MeatPartPurchaseEntity> list = query.list();
            //session.getTransaction().commit();
            return list;
        /*} catch (RuntimeException e) {
        	session.getTransaction().rollback();
            throw e;
        } finally {
        	 if(session.isOpen())
                 session.close();
        }*/
    }

    public void updatePurchase(PurchaseEntity pur, List<MeatPartPurchaseEntity> part) {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(pur);
            part.forEach(e -> session.saveOrUpdate(e));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }    
    }
}
