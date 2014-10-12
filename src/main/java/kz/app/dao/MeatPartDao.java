package kz.app.dao;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

import java.util.List;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 09.09.14
 * Time: 14:29
 */
public class MeatPartDao {

    public static final String GET_MEAT_TYPES = "from MeatTypesEntity";
    public static final String GET_MEAT_CATEGORIES = "from MeatCategoryEntity";
    public static final String GET_RECEIVERS = "from ReceiverEntity";

    public List<MeatTypesEntity> getTypesList() {
        return HibernateUtil.createQueryForList(GET_MEAT_TYPES);
    }

    public List<MeatCategoryEntity> getCategoriesList() {
        return HibernateUtil.createQueryForList(GET_MEAT_CATEGORIES);
    }
    
    public List<ReceiverEntity> getReceiversList() {
        return HibernateUtil.createQueryForList(GET_RECEIVERS);
    }

    // TODO: А роллбэк где?
    public void saveMeatPart(MeatPartEntity part) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(part);
        HibernateUtil.getSession().getTransaction().commit();
    }

    // TODO: А роллбэк где?
    public void saveInvoice(InvoiceEntity invoice) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(invoice);
        HibernateUtil.getSession().getTransaction().commit();
        
    }
    
    public ReceiverEntity getReceiverById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from ReceiverEntity where id = :id");
            List<ReceiverEntity> list = query.setParameter("id", Integer.parseInt(id)).list();
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
    
    public MeatTypesEntity getMeatTypeById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypesEntity where id = :id");
            List<MeatTypesEntity> list = query.setParameter("id", Integer.parseInt(id)).list();
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
    
    public MeatCategoryEntity getMeatCategoryById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategoryEntity where id = :id");
            List<MeatCategoryEntity> list = query.setParameter("id", Integer.parseInt(id)).list();
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
