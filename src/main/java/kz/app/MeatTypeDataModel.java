package kz.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kz.app.entity.MeatTypesEntity;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
 
/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class MeatTypeDataModel extends LazyDataModel<MeatTypesEntity> {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<MeatTypesEntity> datasource;
     
    public MeatTypeDataModel(List<MeatTypesEntity> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public MeatTypesEntity getRowData(String rowKey) {
        for(MeatTypesEntity car : datasource) {
            if(car.getId().toString().equals(rowKey))
                return car;
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(MeatTypesEntity car) {
        return car.getId();
    }
 
    @Override
    public List<MeatTypesEntity> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<MeatTypesEntity> data = new ArrayList<MeatTypesEntity>();
 
        //filter
        for(MeatTypesEntity car : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(car.getClass().getField(filterProperty).get(car));
                        System.out.println( ": =" + fieldValue);
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        }
                        else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(car);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }
}