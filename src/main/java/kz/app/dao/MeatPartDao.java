package kz.app.dao;

import kz.app.entity.MeatCategory;
import kz.app.entity.MeatTypes;

import java.util.List;
import kz.app.entity.Invoice;
import kz.app.entity.MeatPart;
import kz.app.entity.Receiver;
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
    
    public List<MeatTypes> getListMeatTypes() {
//        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
//        try {
//            sess.beginTransaction();
//            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes");
//            List<MeatTypes> list = query.list();
//            sess.getTransaction().commit();
//            list.forEach(e -> {System.out.println("listReceiver = " + e);});
//            return list;
//        } catch (RuntimeException e) {
//            sess.getTransaction().rollback();
//            throw e;
//        } finally {
//            if(sess.isOpen())
//                sess.close();
//        }
        return HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes").list();
    }

    public List<MeatCategory> getListCategory() {
//        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
//        try {
//            sess.beginTransaction();
//            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory");
//            List<MeatCategory> list = query.list();
//            sess.getTransaction().commit();
//            list.forEach(e -> {System.out.println("listReceiver = " + e);});
//            return list;
//        } catch (RuntimeException e) {
//            sess.getTransaction().rollback();
//            throw e;
//        } finally {
//            if(sess.isOpen())
//                sess.close();
//        }
        return HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory").list();
    }
    
    public List<Receiver> getListReceiver() {
//        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
//        try {
//            sess.beginTransaction();
//            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Receiver");
//            List<Receiver> list = query.list();
//            sess.getTransaction().commit();
//            list.forEach(e -> {System.out.println("listReceiver = " + e);});
//            return list;
//        } catch (RuntimeException e) {
//            sess.getTransaction().rollback();
//            throw e;
//        } finally {
//            if(sess.isOpen())
//                sess.close();
//        }
        return HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Receiver").list();
    }
    
    public void saveMeatPart(MeatPart part) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(part);
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void saveInvoice(Invoice invoice) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(invoice);
        HibernateUtil.getSession().getTransaction().commit();
        
    }
    
//    public MeatCategory getMeatCategoryByName(String name) {
//        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory where name = :name");
//        query.setParameter("name", name);
//        if(!query.list().isEmpty())
//            return (MeatCategory) query.list().get(0);
//        else return null;
//    }
//    
//    public MeatTypes getMeatTypesyByName(String type) {
//        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes where type = :type");
//        query.setParameter("type", type);
//        if(!query.list().isEmpty())
//            return (MeatTypes) query.list().get(0);
//        else return null;
//    }
    
    public Receiver getReceiverById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Receiver where id = :id");
            List<Receiver> list = query.setParameter("id", Integer.parseInt(id)).list();
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
    
    public MeatTypes getMeatTypeById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes where id = :id");
            List<MeatTypes> list = query.setParameter("id", Integer.parseInt(id)).list();
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
    
    public MeatCategory getMeatCategoryById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory where id = :id");
            List<MeatCategory> list = query.setParameter("id", Integer.parseInt(id)).list();
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
