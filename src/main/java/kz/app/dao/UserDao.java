package kz.app.dao;

import kz.app.entity.UsersEntity;

import java.util.List;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 16:17
 */
public class UserDao { 
    public UsersEntity getUserByLogin(String name) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from UsersEntity where username = :usr");
            query.setParameter("usr", name);
            List<UsersEntity> list = query.list();
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
}
