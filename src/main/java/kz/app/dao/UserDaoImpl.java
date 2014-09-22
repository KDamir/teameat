package kz.app.dao;

import kz.app.entity.UsersEntity;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UsersEntity> getUser(String name) {
		Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from UsersEntity where username = :usr");
		query.setParameter("usr", name);
		List<UsersEntity> list = query.list();
		return list;
	}

	

}
