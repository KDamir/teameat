package kz.app;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategory;
import kz.app.entity.MeatTypes;
import kz.app.entity.Receiver;
import kz.app.utils.HibernateUtil;

@ManagedBean
@SessionScoped
public class InvoiceService {
    private List<kz.app.entity.MeatPart> meatPartList;
    private kz.app.entity.Invoice invoice;
    private List<MeatCategory> listCategory;
    private List<MeatTypes> listTypes;
    private List<Receiver> listReceiver;

    public List<MeatCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<MeatCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public List<MeatTypes> getListTypes() {
        return listTypes;
    }

    public void setListTypes(List<MeatTypes> listTypes) {
        this.listTypes = listTypes;
    }

    public List<Receiver> getListReceiver() {
        return listReceiver;
    }

    public void setListReceiver(List<Receiver> listReceiver) {
        this.listReceiver = listReceiver;
    }

    public MeatPartDao getMeatPartDao() {
        return meatPartDao;
    }

    public void setMeatPartDao(MeatPartDao meatPartDao) {
        this.meatPartDao = meatPartDao;
    }
    
    MeatPartDao meatPartDao;

    public kz.app.entity.Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(kz.app.entity.Invoice invoice) {
        this.invoice = invoice;
    }

    @PostConstruct
    public void init() {
        invoice = new kz.app.entity.Invoice();
        meatPartList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatPartList.add(new MeatPart());
        }
//        listCategory = new ArrayList<>();
//        listTypes = new ArrayList<>();
//        listReceiver = new ArrayList<>();
        meatPartDao = new MeatPartDao();
        HibernateUtil.getSession().beginTransaction();
        listCategory = meatPartDao.getListCategory();
        listReceiver = meatPartDao.getListReceiver();
        listTypes = meatPartDao.getListMeatTypes();
        HibernateUtil.getSession().getTransaction().commit();
//        list.forEach(e -> listCategory.add(e.getName()));
//        listRec.forEach(e -> listReceiver.add(e.getCompanyName()));
//        lista.forEach(e -> listTypes.add(e.getType()));
    }
    
    public List<kz.app.entity.MeatPart> getMeatPartList() {
        return meatPartList;
    }
    
    public void saveInvoice() {
//        System.out.println("invoice = " + invoice.getReceiver() + ", " + invoice.getSender() + ", " + invoice.getDate());
//        meatPartList.forEach(e -> {System.out.println("meatPart = " + e.getCategory() + ", " + e.getType());});
        //System.out.println("MeatPartList = " + meatPartList);
//        HibernateUtil.getSession().beginTransaction();
        meatPartDao.saveInvoice(invoice);
        meatPartList.forEach(e -> {
            if(e != null)
                meatPartDao.saveMeatPart(e);
        });
        
//        meatPartList.forEach((MeatPart e) -> {
//            if(e != null)
//                meatPartDao.saveMeatPart(e, invoice);
//        });
//        HibernateUtil.getSession().getTransaction().commit();
        System.out.println("saved ok!");
    }

}
