package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class InvoiceController {
    private List<MeatPart> meatPartList;
    private InvoiceEntity invoice;
    private List<MeatCategoryEntity> listCategory;
    private List<MeatTypesEntity> listTypes;
    private List<ReceiverEntity> listReceiver;
    
    MeatPartDao meatPartDao;
    
//    CommonDao jpa;
//    
//    @Resource 
//    private UserTransaction utx;

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
        
//        jpa = new CommonDao(Persistence
//                    .createEntityManagerFactory("kz.app_teameat_war_0.0.1-SNAPSHOTPU"));
//
//        listCategory = jpa.findMeatCategoryEntityEntities();
//        listReceiver = jpa.findReceiverEntityEntities();
//        listTypes    = jpa.findMeatTypesEntityEntities();
        meatPartDao = new MeatPartDao();
        
        listCategory = meatPartDao.getCategoriesList();
        listReceiver = meatPartDao.getReceiversList();
        listTypes    = meatPartDao.getTypesList();
    }
    
    public List<MeatPart> getMeatPartList() {
        return meatPartList;
    }
    
    public void saveInvoice() {
        meatPartDao.saveInvoice(invoice);
        meatPartList.forEach(e -> {
            if(e.getCategory() != null &&  e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, invoice));
        });
        invoice = new InvoiceEntity();
        meatPartList = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatPartList.add(new MeatPart());
        }
        FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Накладная сохранена","сохранение накладной");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
