/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.math.BigInteger;
import java.util.List;

import kz.app.entity.InventoryEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Дамир
 */
public class MeatTypeDao {
	
	
	
	
	public List<MeatTypesEntity> getListMeatType() {
        return HibernateUtil.createQueryForList("from MeatTypesEntity");
    }
	
	
	
    public void saveType(MeatTypesEntity type) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(type);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public Boolean existByBarcode(BigInteger barcode) {
    	Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession()
            		.createQuery("from MeatTypesEntity where barcode = :barcode")
            		.setParameter("barcode", barcode);
            if(query.list().isEmpty()) {
            	sess.getTransaction().commit();
            	return false;
            } else {
            	sess.getTransaction().commit();
            	return true;
            
            }
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public MeatTypesEntity getByBarcode(BigInteger barcode) {
    	Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession()
            		.createQuery("from MeatTypesEntity where barcode = :barcode");
            List<MeatTypesEntity> list = query.setParameter("barcode", barcode).list();
            if(list.isEmpty()) {
            	sess.getTransaction().commit();
            	return null;
            } else {
            	sess.getTransaction().commit();
            	return list.get(0);
            
            }
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    
    
}
