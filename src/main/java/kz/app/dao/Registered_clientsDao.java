package kz.app.dao;


import kz.app.entity.Registered_clientsEntity;

import java.util.List;

import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class Registered_clientsDao {
    public List<Registered_clientsEntity> getListRegistered_clients() {
        return HibernateUtil.createQueryForList("from Registered_clientsEntity");
    }

    public void saveRegistered_clients(Registered_clientsEntity inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getClientReward(Integer inv) {
    	Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Registered_clientsEntity where receiverId = :inv");
            query.setParameter("inv", inv);
            List<Registered_clientsEntity> list = query.list(); 
            if(list.isEmpty())
                return 0.0;
            return list.get(0).getReward();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if(session.isOpen())
                session.close();
        }
    }



}
