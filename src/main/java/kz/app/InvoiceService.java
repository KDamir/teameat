package kz.app;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.utils.HibernateUtil;
import kz.app.utils.MeatPartToEntity;

@ManagedBean
@SessionScoped
public class InvoiceService {
    private List<MeatPart> meatPartList;
    private InvoiceEntity invoice;
    private List<MeatCategoryEntity> listCategory;
    private List<MeatTypesEntity> listTypes;
    private List<ReceiverEntity> listReceiver;
    
    MeatPartDao meatPartDao;

    public List<MeatCategoryEntity> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<MeatCategoryEntity> listCategory) {
        this.listCategory = listCategory;
    }

    public List<MeatTypesEntity> getListTypes() {
        return listTypes;
    }

    public void setListTypes(List<MeatTypesEntity> listTypes) {
        this.listTypes = listTypes;
    }

    public List<ReceiverEntity> getListReceiver() {
        return listReceiver;
    }

    public void setListReceiver(List<ReceiverEntity> listReceiver) {
        this.listReceiver = listReceiver;
    }

    public MeatPartDao getMeatPartDao() {
        return meatPartDao;
    }

    public void setMeatPartDao(MeatPartDao meatPartDao) {
        this.meatPartDao = meatPartDao;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    @PostConstruct
    public void init() {
        invoice = new InvoiceEntity();
        meatPartList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatPartList.add(new MeatPart());
        }

        meatPartDao = new MeatPartDao();
        
        HibernateUtil.getSession().beginTransaction();
        listCategory = meatPartDao.getListCategory();
        listReceiver = meatPartDao.getListReceiver();
        listTypes    = meatPartDao.getListMeatTypes();
        HibernateUtil.getSession().getTransaction().commit();
    }
    
    public List<MeatPart> getMeatPartList() {
        return meatPartList;
    }
    
    public void saveInvoice() {
        meatPartDao.saveInvoice(invoice);
        meatPartList.forEach(e -> {
            if(e.getCategory() != null &&  e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartToEntity.getMeatPartEntity(e, invoice));
        });

        System.out.println("saved ok!");
    }

}
