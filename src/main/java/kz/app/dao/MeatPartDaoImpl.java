package kz.app.dao;

import java.util.List;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.utils.HibernateUtil;

public class MeatPartDaoImpl implements MeatPartDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<MeatTypesEntity> getListMeatTypes() {
		return HibernateUtil.getSessionfactory().getCurrentSession()
				.createQuery("from MeatTypesEntity").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MeatCategoryEntity> getListCategory() {
		return HibernateUtil.getSessionfactory().getCurrentSession()
				.createQuery("from MeatCategoryEntity").list();
	}
	
	

}
