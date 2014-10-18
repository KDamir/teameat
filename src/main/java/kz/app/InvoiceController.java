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
public class InvoiceController extends AbstractMeatPartController{

    private InvoiceEntity invoice;
    private List<ReceiverEntity> listReceiver;
    
    MeatPartDao meatPartDao;
    
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
        meatParts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatParts.add(new MeatPart());
        }
        meatPartDao = new MeatPartDao();
        categories = meatPartDao.getCategoriesList();
        listReceiver = meatPartDao.getReceiversList();
        types    = meatPartDao.getTypesList();
    }
    
    public List<MeatPart> getMeatParts() {
        return meatParts;
    }
    
    @Override
    public void updateOrder() {
        meatPartDao.saveInvoice(invoice);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, invoice));
        });
        invoice = new InvoiceEntity();
        meatParts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatParts.add(new MeatPart());
        }
        FacesMessage msg = null;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Накладная сохранена",
                "Информация о новой накладной сохранена в базе данных");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
