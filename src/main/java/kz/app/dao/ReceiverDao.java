/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import kz.app.entity.GoodsSupEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.SupplierEntity;
import kz.app.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

/**
 *
 * @author 
 */
public class ReceiverDao {
    public void saveReceiver(ReceiverEntity receiver) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(receiver);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }

	public List<ReceiverEntity> getListReceiver() {
		return HibernateUtil.createQueryForList("from ReceiverEntity");
   }
}