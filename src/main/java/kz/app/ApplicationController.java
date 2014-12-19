package kz.app;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import kz.app.dao.MeatPartDao;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.SupplierEntity;
import kz.app.entity.UserGroupsEntity;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 07.10.2014
 * Time: 23:45
 */
@ManagedBean(name = "appBean")
@SessionScoped
public class ApplicationController{
    
    public static MeatPartDao dao;
    
    public static MeatPartPurchaseDao daop;
    
    public static List<MeatTypesEntity> types;
    public static List<MeatCategoryEntity> categories;
    public static List<ReceiverEntity> receivers;
    public static List<SupplierEntity> suppliers;
    
    private UserGroupsEntity group;
    
    private String pageToDisplay;
    
    public static List<SupplierEntity> getSuppliers() {
		return suppliers;
	}

	public static void setSuppliers(List<SupplierEntity> suppliers) {
		ApplicationController.suppliers = suppliers;
	}

	@PostConstruct
    public void init() {
        dao = new MeatPartDao();
        daop = new MeatPartPurchaseDao();
        types = dao.getTypesList();
        categories = dao.getCategoriesList();
        receivers = dao.getReceiversList();
        suppliers = daop.getSuppliersList();
        
        /*Пустая ячейка*/
        MeatCategoryEntity blankCategory = new MeatCategoryEntity();
        blankCategory.setName("");
        categories.add(0, blankCategory);
        
            setPageToDisplay("invoiceHistory");
    }
    
    public boolean isAdmin() {
        return "admin".equals(group.getGroupname());
    }
    
    public boolean  isVendor() {
        return "vendor".equals(group.getGroupname());
    }
    
    public String getItemCss(String item) {
        if(item.equals(getPageToDisplay()))
            return "font-weight: bold;";
        return "";
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public String getPageToDisplay() {
        return pageToDisplay;
    }
    
    public void setPageToDisplay(String pageToDisplay) {    
        this.pageToDisplay = pageToDisplay;
    }

    public UserGroupsEntity getGroup() {
        return group;
    }
    
    public void setGroup(UserGroupsEntity group) {
        this.group = group;
    }
    
    public ApplicationController() {
    }
//</editor-fold>

}
