package kz.app;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.SupplierEntity;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostConstruct
    private void init() {
        categories   = ApplicationController.categories;
        categories.remove(0);
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
}
