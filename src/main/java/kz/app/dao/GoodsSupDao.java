/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import kz.app.entity.GoodsEntity;
import kz.app.entity.GoodsSupEntity;
import kz.app.entity.InventoryEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Дамир
 */
public class GoodsSupDao {
	public List<GoodsSupEntity> getListGoodsSup() {
		return HibernateUtil.createQueryForList("from GoodsSupEntity");
   }
    
	public List<GoodsSupEntity> getListSups(Date begin, Date end) {
		 Session session = HibernateUtil.getSession();
	        try {
	            session.beginTransaction();
	            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from GoodsSupEntity where date between :begin and :end");
	            query.setTimestamp("begin", begin);
	            query.setTimestamp("end", end);
	            List<GoodsSupEntity> list = query.list();
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
    
}