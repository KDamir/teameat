/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.util.List;

import kz.app.entity.GoodsEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.KassaEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author 
 */
public class KassaDao {
	
	
    public void saveKassa(KassaEntity kassa) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.save(kassa);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public Integer getKassaMaxId() {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from KassaEntity");
            List<KassaEntity> list = query.list();
            session.getTransaction().commit();
            if(list.isEmpty())
               return 0;
            return list.size();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }
    	
        
    }
    
    public KassaEntity getKassaById(Integer id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from KassaEntity where id = :id");
            List<KassaEntity> list = query.setParameter("id", id).list();
            sess.getTransaction().commit();
            if(list.isEmpty())
                return null;
            return list.get(0);
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public KassaEntity getLastKassa() {
    	return getKassaById(getKassaMaxId());
    	
    }
    
}
