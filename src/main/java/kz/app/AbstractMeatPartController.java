package kz.app;

import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 15.10.2014
 * Time: 22:52
 */
public abstract class AbstractMeatPartController {

    protected List<MeatPart> meatParts;
    protected List<MeatTypesEntity> types;
    protected List<MeatCategoryEntity> categories;
    
    private static final MeatPartDao mtd = new MeatPartDao();

    public List<MeatPart> getMeatParts() {
        return meatParts;
    }

    public List<MeatTypesEntity> getTypes() {
        return types;
    }

    public List<MeatCategoryEntity> getCategories() {
        return categories;
    }

    public abstract void updateOrder();

    /*Общий вес*/
    public Double getTotalWeight() {
        return meatParts.stream().mapToDouble(MeatPart::getWeight).sum();
    }

    /*Общая сумма продаж*/
    public Double getTotalSalesAmount() {
        return meatParts.stream().mapToDouble(MeatPart::getRevenue).sum();
    }
    
    

    public List<MeatTypesEntity> filterTypes(MeatCategoryEntity selectedCategory) {
        List<MeatTypesEntity> filteredTypes = types.stream()
                .filter(type -> type.getCategoryId().equals(selectedCategory))
                .map(type -> type)
                .collect(Collectors.toList());
        filteredTypes.add(0, getBlankType());
        return filteredTypes;
    }

    public MeatCategoryEntity getBlankCategory() {
        MeatCategoryEntity blankCategory = new MeatCategoryEntity();
        blankCategory.setName("");
        return blankCategory;
    }

    public MeatTypesEntity getBlankType() {
        MeatTypesEntity blankType = new MeatTypesEntity();
        blankType.setName("");
        return blankType;
    } 

    public void addNewMeatPart() {
        meatParts.add(new MeatPart());
    }

    public void deleteLastMeatPart() {
        meatParts.remove(meatParts.size() - 1);
    }

    public void resetType(MeatPart selectedPart) {
        selectedPart.setType(filterTypes(selectedPart.getCategory()).get(0));
    }
    
    public void resetCategoryTypePrice(MeatPart selectedPart){
    	selectedPart.setCategory(mtd.getMeatCategoryByBarcode(selectedPart.getBarcode()));
    	selectedPart.setType( mtd.getMeatTypeByBarcode(selectedPart.getBarcode()));
    	selectedPart.setPrice( mtd.getMeatTypeByBarcode(selectedPart.getBarcode()).getPrice_std());
    }
    
    public void deleteRow(MeatPart part) {
        meatParts.remove(part);
    }
}
