package kz.app.dao;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

import java.util.List;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 14:29
 */
public interface MeatPartDao {
	public List<MeatTypesEntity> getListMeatTypes();
	
	public List<MeatCategoryEntity> getListCategory();
}
