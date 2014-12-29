/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.dao;

import kz.app.entity.MeatTypesEntity;
import kz.app.utils.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author Дамир
 */
public class MeatTypeDao {
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
