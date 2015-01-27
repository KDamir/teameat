package kz.app;

import java.util.Comparator;

import kz.app.entity.MeatTypesEntity;

import org.primefaces.model.SortOrder;

 
public class LazySorter implements Comparator<MeatTypesEntity> {
 
    private String sortField;
     
    private SortOrder sortOrder;
     
    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }
 
    public int compare(MeatTypesEntity car1, MeatTypesEntity car2) {
        try {
            Object value1 = MeatTypesEntity.class.getField(this.sortField).get(car1);
            Object value2 = MeatTypesEntity.class.getField(this.sortField).get(car2);
 
            int value = ((Comparable)value1).compareTo(value2);
             
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch(Exception e) {
            throw new RuntimeException();
        }
    }
}