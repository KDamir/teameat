package kz.app;

import kz.app.entity.InventoryEntity;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.SupplierEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import kz.app.dao.InventoryDao;
import kz.app.dao.MeatCategoryDao;
import kz.app.dao.MeatTypeDao;
import kz.app.dao.SupplierDao;
import kz.app.utils.Constants;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 17.11.2014
 * Time: 23:15
 */
@ManagedBean
@SessionScoped
public class AdminFormController {
    protected List<MeatTypesEntity> types;
    protected List<MeatCategoryEntity> categories;
    protected List<SupplierEntity> suppliers;

    private SupplierEntity supplier;
    private MeatCategoryEntity category;
    private MeatCategoryEntity selectedCategory;
    private MeatTypesEntity type;
    private InventoryEntity	inventory;
    
    private SupplierDao supplierDao;
    private MeatCategoryDao categoryDao;
    private MeatTypeDao typeDao;
    private InventoryDao inventoryDao;

    @PostConstruct
    private void init() {
        supplierDao = new SupplierDao();
        categoryDao = new MeatCategoryDao();
        typeDao     = new MeatTypeDao();
        inventoryDao = new InventoryDao();
        
        categories   = ApplicationController.categories;
        selectedCategory = categories.get(0);
        types        = ApplicationController.types;
        

        supplier = new SupplierEntity();
        category = new MeatCategoryEntity();
        type = new MeatTypesEntity();
        inventory = new InventoryEntity();
    }

    public List<MeatTypesEntity> getSelectedCategoryTypes() {
        List<MeatTypesEntity> filteredTypes = types.stream()
                .filter(type -> type.getCategoryId().equals(selectedCategory))
                .map(type -> type)
                .collect(Collectors.toList());
        return filteredTypes;
    }

    public void resetType() {

    }
    
    public void addSupplier() {
        FacesContext context = FacesContext.getCurrentInstance();
        if ("".equals(supplier.getAddress())|| "".equals(supplier.getCompanyName()) ||
        	"".equals(supplier.getNote()) || "".equals(supplier.getPhoneNumber()))
        	return;
        
        supplierDao.saveSupplier(supplier);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        supplier = new SupplierEntity();
    }
    
    public void addCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        if ("".equals(category.getName()))
        	return;
        categoryDao.saveCategory(category);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        category = new MeatCategoryEntity();
    }
    
    public void addType() {
        FacesContext context = FacesContext.getCurrentInstance();
        if ("".equals(type.getBarcode()) || "".equals(type.getName()) ||
        	"".equals(type.getPrice_std()) || "".equals(type.getPrice_zakup())|| "".equals(type.getReward()))
        	return;
        if(typeDao.existByBarcode(type.getBarcode())) {
        	context.addMessage(null, new FacesMessage(Constants.BARCODE_EXIST_WARN));
        	return;
        }
        type.setCategoryId(selectedCategory);
        typeDao.saveType(type);
        
        // добавить новый элемент в таблицу инвентаризации с текущей датой и с количеством 0
        inventory.setBarcode(type.getBarcode());
        inventory.setDate(new Date());
        inventory.setQuantity(0.0);
        inventoryDao.saveInventory(inventory);
        
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        type = new MeatTypesEntity();
        inventory = new InventoryEntity();
    }
    
    public void update() {
        ApplicationController.refreshFromDB();
        categories       = ApplicationController.categories;
        types            = ApplicationController.types;
      
        
        //selectedCategory = categories.get(0);
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public List<MeatTypesEntity> getTypes() {
        return types;
    }
    
    public void setTypes(List<MeatTypesEntity> types) {
        this.types = types;
    }
    
    public List<MeatCategoryEntity> getCategories() {
        return categories;
    }
    
    public void setCategories(List<MeatCategoryEntity> categories) {
        this.categories = categories;
    }
    
    public SupplierEntity getSupplier() {
        return supplier;
    }
    
    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }
    
    public MeatCategoryEntity getCategory() {
        return category;
    }
    
    public void setCategory(MeatCategoryEntity category) {
        this.category = category;
    }
    
    public MeatCategoryEntity getSelectedCategory() {
        return selectedCategory;
    }
    
    public void setSelectedCategory(MeatCategoryEntity selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    
    public MeatTypesEntity getType() {
        return type;
    }
    
    public void setType(MeatTypesEntity type) {
        this.type = type;
    }
//</editor-fold>
}
