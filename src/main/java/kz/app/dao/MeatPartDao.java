package kz.app.dao;

import kz.app.entity.MeatCategory;
import kz.app.entity.MeatTypes;

import java.util.List;
import kz.app.utils.HibernateUtil;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 14:29
 */
public class MeatPartDao {
    public List<MeatTypes> getListMeatTypes() {
            return HibernateUtil.getSessionfactory().getCurrentSession()
                            .createQuery("from MeatTypes").list();
    }

    public List<MeatCategory> getListCategory() {
            return HibernateUtil.getSessionfactory().getCurrentSession()
                            .createQuery("from MeatCategory").list();
    }
}
