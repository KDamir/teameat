package kz.app.dao;

import kz.app.entity.UsersEntity;

import java.util.List;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 16:17
 */
public class UserDao { 
    public List<UsersEntity> getUser(String name) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from UsersEntity where username = :usr");
        query.setParameter("usr", name);
        List<UsersEntity> list = query.list();
        return list;
    }
}
