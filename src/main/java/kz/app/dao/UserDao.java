package kz.app.dao;

import kz.app.entity.Users;

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
    public List<Users> getUser(String name) {
		Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Users where username = :usr");
		query.setParameter("usr", name);
		List<Users> list = query.list();
		return list;
	}
}
