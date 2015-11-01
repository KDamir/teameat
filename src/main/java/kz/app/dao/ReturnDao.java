package kz.app.dao;

import java.math.BigInteger;
import java.util.Date;


import java.util.List;

import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatPartReturnEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReturnEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReturnDao {
   
    
    public List<ReturnEntity> getListReturn(Date begin, Date end) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from ReturnEntity where date between :begin and :end");
            query.setTimestamp("begin", begin);
            query.setTimestamp("end", end);
            List<ReturnEntity> list = query.list();
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
    
    public List<MeatPartReturnEntity> getListMeatPartReturn(ReturnEntity prc, MeatTypesEntity typeId) {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartReturnEntity where purchaseId = :prc AND typeId= :typeId");
            query.setParameter("prc", prc);
            query.setParameter("typeId", typeId);
            List<MeatPartReturnEntity> list = query.list();
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
    
    public List<MeatPartReturnEntity> getListMeatPartReturnByPrc(ReturnEntity prc) {
    	//Session session = HibernateUtil.getSession();
    	
    	
        //try {
            //session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartReturnEntity where returnId = :prc");
            query.setParameter("prc", prc);
            List<MeatPartReturnEntity> list = query.list();
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

    public void updateReturn(ReturnEntity pur, List<MeatPartReturnEntity> part) {
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
