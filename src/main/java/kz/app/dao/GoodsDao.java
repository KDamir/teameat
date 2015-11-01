package kz.app.dao;

import java.util.Date;

import kz.app.entity.GoodsEntity;

import java.util.List;

import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class GoodsDao {
	public List<GoodsEntity> getListGoods(Date begin, Date end) {
		 Session session = HibernateUtil.getSession();
	        try {
	            session.beginTransaction();
	            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from GoodsEntity where date between :begin and :end");
	            query.setTimestamp("begin", begin);
	            query.setTimestamp("end", end);
	            List<GoodsEntity> list = query.list();
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