package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.dao.Registered_clientsDao;
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
import java.util.Iterator;
import java.util.List;

@ManagedBean
@SessionScoped
public class InvoiceController extends AbstractMeatPartController{

    private InvoiceEntity invoice;
    private List<ReceiverEntity> listReceiver;
    
    private ReceiverEntity selectedReceiver;
    
    // Внесенный платеж
    private double sumInput=0.0;
    // сдача
    private double renting = 0.0;
    
    private double totalRwd = 0.0;
    
    private double totalClientRwd = 0.0;
    
    private static MeatPartDao meatPartDao;
 
    FacesMessage msg = null;
    
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

    public List<ReceiverEntity> completeReceiver(String query) {
        List<ReceiverEntity> filteredThemes = new ArrayList<>();
         
        listReceiver.stream().filter((skin) -> (skin.getCompanyName().toLowerCase().startsWith(query)))
                             .forEach((skin) -> {
            filteredThemes.add(skin);
        });
         
        return filteredThemes;
    }
    
    public void showTotalReward(ReceiverEntity receiver){
    	if (receiver != null) {
//            Registered_clientsDao rcd = new Registered_clientsDao();
//    		System.out.println(receiver.getId()+"  "+receiver.getCompanyName());
//            totalClientRwd = rcd.getClientReward(receiver.getId());
            totalClientRwd = receiver.getReward();
    	}
    	else 
            totalClientRwd = 0.0;
        this.selectedReceiver = receiver;
    }

    
    public double getTotalRwd() {
        return totalRwd;
    }

    public void setTotalRwd(double totalRwd) {
        this.totalRwd = totalRwd;
    }

    public double getTotalClientRwd() {
        return totalClientRwd;
    }

    public void setTotalClientRwd(double totalClientRwd) {
        this.totalClientRwd = totalClientRwd;
    }

    public void setRenting(double renting) {
        this.renting = renting;
    }

    @PostConstruct
    public void init() {
        meatPartDao = ApplicationController.dao;
        invoice = new InvoiceEntity();
        meatParts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            meatParts.add(new MeatPart());
        }        
        categories   = ApplicationController.categories;
        listReceiver = ApplicationController.receivers;
        types        = ApplicationController.types;
    }
    
    @Override
    public void updateOrder() {
    	double sum = 0.0;
        for(MeatPart part : meatParts) {
            if(part.getBarcode() != null)
                sum = sum + part.getRevenue();
        }
        if(sum > selectedReceiver.getReward()) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено",
                    "Недостаточно баллов");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        meatParts.stream().forEach((part) -> {
            selectedReceiver.setReward(selectedReceiver.getReward() - part.getRevenue());
        });
    	// подсчет вознаграждения одного инвойса    	
    	invoice.setTotalReward(meatParts.stream().filter((part) -> (!part.isBall()))
                                                 .mapToDouble(MeatPart::getItemReward).sum());
    	invoice.setTotalAmount(meatParts.stream().mapToDouble(MeatPart::getRevenue).sum());
        selectedReceiver.setReward(selectedReceiver.getReward() + invoice.getTotalReward());
        meatPartDao.saveReceiver(selectedReceiver);
        invoice.setDate(new Date());
        meatPartDao.saveInvoice(invoice);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, invoice, null));
        });
        
        invoice = new InvoiceEntity();
        meatParts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            meatParts.add(new MeatPart());
        }
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
