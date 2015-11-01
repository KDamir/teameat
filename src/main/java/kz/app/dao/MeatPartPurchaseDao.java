package kz.app.dao;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import kz.app.entity.CalculationEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.SupplierEntity;
import kz.app.utils.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 */
public class MeatPartPurchaseDao {

    public static final String GET_MEAT_TYPES = "from MeatTypesEntity";
    public static final String GET_MEAT_CATEGORIES = "from MeatCategoryEntity";
    public static final String GET_SUPPLIERS = "from SupplierEntity";

    public List<MeatTypesEntity> getTypesList() {
        return HibernateUtil.createQueryForList(GET_MEAT_TYPES);
    }

    public List<MeatCategoryEntity> getCategoriesList() {
        return HibernateUtil.createQueryForList(GET_MEAT_CATEGORIES);
    }
    
    public List<SupplierEntity> getSuppliersList() {
        return HibernateUtil.createQueryForList(GET_SUPPLIERS);
    }

    public void saveMeatPartPurchase(MeatPartPurchaseEntity part) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(part);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public void deleteMeatPartPurchase(MeatPartPurchaseEntity part) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.delete(part);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }

    public void savePurchase(PurchaseEntity invoice) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(invoice);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public void saveCalculation(CalculationEntity calc) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            sess.saveOrUpdate(calc);
            sess.getTransaction().commit();
        } catch (RuntimeException e) {
            sess.getTransaction().rollback();
            throw e;
        } finally {
            if(sess.isOpen())
                sess.close();
        }
    }
    
    public SupplierEntity getSupplierById(String id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from SupplierEntity where id = :id");
            List<SupplierEntity> list = query.setParameter("id", Integer.parseInt(id)).list();
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
    
    public MeatPartPurchaseEntity getMeatPartPurchaseById(Integer id) {
        Session sess = HibernateUtil.getSessionfactory().getCurrentSession();
        try {
            sess.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartPurchaseEntity where id = :id");
            List<MeatPartPurchaseEntity> list = query.setParameter("id", id).list();
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

    public List<MeatPartPurchaseEntity> getListMeatPartByBarcode(BigInteger barcode) {
    	
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatPartPurchaseEntity where barcode = :barcode");
            
            query.setParameter("barcode", barcode);
           
            List<MeatPartPurchaseEntity> list = query.list();
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
