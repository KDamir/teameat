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
import java.util.List;
import kz.app.beans.UserLoginView;

@ManagedBean
@SessionScoped
public class InvoiceController extends AbstractMeatPartController{

    private InvoiceEntity invoice;
    private List<ReceiverEntity> listReceiver;
    
    private ReceiverEntity selectedReceiver;
    
    // Внесенный платеж
    private Double sumInput=null;
    // сдача
    private double renting = 0.0;
    
    private double totalRwd = 0.0;
    
    private double totalClientRwd = 0.0;
    
    private static MeatPartDao meatPartDao;
    
    private List<String> buf;
    
    private boolean isInvoiceForSell;
    
    private ReceiverEntity selectedRecOpt; 

	FacesMessage msg = null;
    FacesContext fc;
    
    UserLoginView user;
    
    
    
    public boolean isInvoiceForSell() {
		return isInvoiceForSell;
	}

	public void setInvoiceForSell(boolean isInvoiceForSell) {
		this.isInvoiceForSell = isInvoiceForSell;
	}
    
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
        fc = FacesContext.getCurrentInstance();
        user = (UserLoginView) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "userLoginView");
        buf = new ArrayList<>();
        meatPartDao = ApplicationController.dao;
        invoice = new InvoiceEntity();
        invoice.setSender(user.getUsername());
        meatParts = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            meatParts.add(new MeatPart());
        }        
        categories   = ApplicationController.categories;
        listReceiver = ApplicationController.receivers;
        types        = ApplicationController.types;
        
        /*TODO: Потом переделать
         */
        for (ReceiverEntity recItem : listReceiver) {
            if (recItem.getId() == 0) {
                invoice.setReceiverId(recItem);
                break;
            }
        }
    }
    
    @Override
    public void updateOrder() {
        buf.clear();
    	double sum = 0.0;/* Общая сумма товаров, которые будут оплачены баллами*/
        for(MeatPart part : meatParts) {
            if(part.getWeight() == null) {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено. Для сохранения введите вес!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            if(null != part.getCategory())
                buf.add(part.getCategory().getName());
            if(part.getBarcode() != null && part.isBall())
                sum = sum + part.getRevenue();
        }
        if(buf.isEmpty()) {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено. Для сохранения необходим хотя бы один товар!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return;
        }
        if (selectedReceiver != null) {
            if (sum > selectedReceiver.getReward()) {
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Действие отменено. Недостаточно баллов!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            /*Оплата баллами*/
            meatParts.stream().filter((part) -> (part.getBarcode() != null && part.isBall())).forEach((part) -> {
                selectedReceiver.setReward(selectedReceiver.getReward() - part.getRevenue());
            });
        }
    	// подсчет вознаграждения одного инвойса    	
    	invoice.setTotalReward(meatParts.stream().filter((part) -> (!part.isBall()))
                                                 .mapToDouble(MeatPart::getItemReward).sum());//Товары, оплаченные баллами не входят в вознагрождение
    	invoice.setTotalAmount(meatParts.stream().mapToDouble(MeatPart::getRevenue).sum());
        if (selectedReceiver != null) {
            selectedReceiver.setReward(selectedReceiver.getReward() + invoice.getTotalReward());
            meatPartDao.saveReceiver(selectedReceiver);
        }
        invoice.setDate(new Date());
        if (sumInput != null)
        	invoice.setPaidAmount(sumInput);
        meatPartDao.saveInvoice(invoice);
        // TODO: Должна быть валидация на заполнение нужных полей
        meatParts.forEach(e -> {
            if (e.getCategory() != null && e.getType() != null)
                meatPartDao.saveMeatPart(MeatPartConverter.convertMeatPartToEntity(e, invoice, null));
        });
        
        clear();
    }

    @Override
    public void deleteLastMeatPart() {
        int idx = meatParts.size() - 1;
        meatPartDao.deleteMeatPart(MeatPartConverter.convertMeatPartToEntity(meatParts.get(idx), invoice, null));
        meatParts.remove(idx);
    }

    public Double getSumInput() {
        return sumInput;
    }

    public void setSumInput(Double sumInput) {
        this.sumInput = sumInput;
    }
	
	
	 /*Сдача*/
    public Double getRenting() {
        if(sumInput == null) {
            return 0.0;
        }
        else
            return ( sumInput - meatParts.stream().mapToDouble(MeatPart::getRevenue).sum() );
    }

    public void clear() {
        invoice = new InvoiceEntity();
        invoice.setSender(user.getUsername());
        /*TODO: Потом переделать
         */
        if (listReceiver!=null)
        	for (ReceiverEntity recItem : listReceiver) {
        		if (recItem.getId() == 0) {
        			invoice.setReceiverId(recItem);
        			break;
        		}
        	}
        meatParts = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            meatParts.add(new MeatPart());
        }
        sumInput = null;
        renting = 0.0;
    }
    
}
