/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.math.BigInteger;
import java.util.List;

import kz.app.entity.InventoryEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author
 */
public class InventoryDao {
	
	
	
	
	public List<InventoryEntity> getListInventory() {
        return HibernateUtil.createQueryForList("from InventoryEntity");
    }
	
	
	
    public void saveInventory(InventoryEntity inventory) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(inventory);
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
            		.createQuery("from InventoryEntity where barcode = :barcode")
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

    public InventoryEntity getByBarcode(BigInteger barcode) {
    	Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession()
            		.createQuery("from InventoryEntity where barcode = :barcode");
            List<InventoryEntity> list = query.setParameter("barcode", barcode).list();
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


	public void deleteInventory() {
		Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.createSQLQuery("truncate table inventory").executeUpdate();
	
		sess.getTransaction().commit();
        }catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
        
	}
    
    
    
}
