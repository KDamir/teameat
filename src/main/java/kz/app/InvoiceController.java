package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.utils.MeatPartConverter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class InvoiceController extends AbstractMeatPartController{

    private InvoiceEntity invoice;
    private List<ReceiverEntity> listReceiver;
    
    // Внесенный платеж
    private double sumInput=0.0;
    // сдача
    private double renting = 0.0;
    
    private static MeatPartDao meatPartDao;
    
    public List<ReceiverEntity> getListReceiver() {
        return listReceiver;
    }

    public void setListReceiver(List<ReceiverEntity> listReceiver) {
        this.listReceiver = listReceiver;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }

    @PostConstruct
    public void init() {
        meatPartDao = ApplicationController.dao;
        invoice = new InvoiceEntity();
        invoice.setDate(new Date());
        meatParts = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            meatParts.add(new MeatPart());
        }        
        categories   = ApplicationController.categories;
        listReceiver = ApplicationController.receivers;
        types        = ApplicationController.types;
    }
    
    @Override
    public void updateOrder() {
        meatPartDao.saveInvoice(invoice);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, invoice, null));
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

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartDao.deleteMeatPart(MeatPartConverter.convertMeatPartToEntity(meatParts.get(idx), invoice, null));
        meatParts.remove(idx);
    }

	public double getSumInput() {
		return sumInput;
	}

	public void setSumInput(double sumInput) {
		this.sumInput = sumInput;
	}
	
	
	 /*Сдача*/
    public Double getRenting() {
        return ( sumInput - meatParts.stream().mapToDouble(MeatPart::getRevenue).sum() );
    }
    
    
    
}
