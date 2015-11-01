package kz.app.dao;
import java.util.List;

import kz.app.entity.LeavingsEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class LeavingsDao {
	public List<LeavingsEntity> getListLeavingsEntity() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from LeavingsEntity");
            List<LeavingsEntity> list = query.list();
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