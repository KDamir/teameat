package kz.app;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.SupplierEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

    private SupplierEntity supplier;
    private MeatCategoryEntity category;
    private MeatCategoryEntity selectedCategory;
    private MeatTypesEntity type;
    
    private SupplierDao supplierDao;
    private MeatCategoryDao categoryDao;
    private MeatTypeDao typeDao;

    @PostConstruct
    private void init() {
        supplierDao = new SupplierDao();
        categoryDao = new MeatCategoryDao();
        typeDao     = new MeatTypeDao();
        
        categories   = ApplicationController.categories;
        selectedCategory = categories.get(0);
        types        = ApplicationController.types;

        supplier = new SupplierEntity();
        category = new MeatCategoryEntity();
        type = new MeatTypesEntity();
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
        supplierDao.saveSupplier(supplier);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        supplier = new SupplierEntity();
    }
    
    public void addCategory() {
        FacesContext context = FacesContext.getCurrentInstance();
        categoryDao.saveCategory(category);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        category = new MeatCategoryEntity();
    }
    
    public void addType() {
        FacesContext context = FacesContext.getCurrentInstance();
        type.setCategoryId(selectedCategory);
        typeDao.saveType(type);
        context.addMessage(null, new FacesMessage(Constants.UPDATE_SUCCESSFUL));
        update();
        /*для создания след. объекта*/
        type = new MeatTypesEntity();
    }
    
    public void update() {
        ApplicationController.refreshFromDB();
        categories       = ApplicationController.categories;
        types            = ApplicationController.types;
        selectedCategory = categories.get(0);
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
