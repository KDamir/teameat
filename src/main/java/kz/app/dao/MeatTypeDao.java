/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.math.BigInteger;
import java.util.List;

import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
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
    
    
    
}
