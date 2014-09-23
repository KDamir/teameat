package kz.app.dao;

import kz.app.entity.MeatCategory;
import kz.app.entity.MeatTypes;

import java.util.List;
import kz.app.Invoice;
import kz.app.MeatPart;
import kz.app.entity.Receiver;
import kz.app.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    
    public List<Receiver> getListReceiver() {
        return HibernateUtil.getSessionfactory().getCurrentSession()
                            .createQuery("from Receiver").list();
    }
    
    public void saveMeatPart(kz.app.entity.MeatPart part) {
//        kz.app.entity.MeatPart meatEntity = new kz.app.entity.MeatPart();
//        meatEntity.setPrice(part.getPrice());
//        meatEntity.setQuantity(part.getQuantity());
//        meatEntity.setCategoryId(getMeatCategoryByName(part.getCategoryId().getName()));
//        meatEntity.setTypeId(getMeatTypesyByName(part.getTypeId().getType()));
//        meatEntity.setInvoiceId(null);
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(part);
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public void saveInvoice(kz.app.entity.Invoice invoice) {
        HibernateUtil.getSession().beginTransaction();
        HibernateUtil.getSession().save(invoice);
        HibernateUtil.getSession().getTransaction().commit();
        
    }
    
    public MeatCategory getMeatCategoryByName(String name) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory where name = :name");
        query.setParameter("name", name);
        if(!query.list().isEmpty())
            return (MeatCategory) query.list().get(0);
        else return null;
    }
    
    public MeatTypes getMeatTypesyByName(String type) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes where type = :type");
        query.setParameter("type", type);
        if(!query.list().isEmpty())
            return (MeatTypes) query.list().get(0);
        else return null;
    }
    
    public Receiver getReceiverById(String id) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from Receiver where id = :id");
        query.setParameter("id", Integer.parseInt(id));
        return (Receiver) query.list().get(0);
    }
    
    public MeatTypes getMeatTypeById(String id) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatTypes where id = :id");
        query.setParameter("id", Integer.parseInt(id));
        return (MeatTypes) query.list().get(0);
    }
    
    public MeatCategory getMeatCategoryById(String id) {
        Query query = HibernateUtil.getSessionfactory().getCurrentSession().createQuery("from MeatCategory where id = :id");
        query.setParameter("id", Integer.parseInt(id));
        return (MeatCategory) query.list().get(0);
    }

}
