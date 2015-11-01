package kz.app;

import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kz.app.beans.UserLoginView;
import kz.app.dao.MeatPartDao;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.ReceiverEntity;

/**
 *
 * @author Дамир
 */
@ManagedBean
@ViewScoped
public class InvoiceProd extends AbstractMeatPartController{
    private static final Logger log = Logger.getLogger(InvoiceProd.class.getName());

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
    private List<String> buf;
    FacesMessage msg = null;
    FacesContext fc;
    UserLoginView user;

    @Override
    public void updateOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void showTotalReward(ReceiverEntity receiver){
        if (receiver != null) {
            totalClientRwd = receiver.getReward();
        }
        else 
            totalClientRwd = 0.0;
        
        this.selectedReceiver = receiver;
    }

    //<editor-fold defaultstate="collapsed" desc="comment">
    public InvoiceEntity getInvoice() {
        return invoice;
    }
    
    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }
    
    public List<ReceiverEntity> getListReceiver() {
        return listReceiver;
    }
    
    public void setListReceiver(List<ReceiverEntity> listReceiver) {
        this.listReceiver = listReceiver;
    }
    
    public UserLoginView getUser() {
        return user;
    }
    
    public void setUser(UserLoginView user) {
        this.user = user;
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
//</editor-fold>
    
}
