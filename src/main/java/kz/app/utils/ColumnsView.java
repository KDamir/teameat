package kz.app.utils;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;


import kz.app.entity.GoodsEntity;


@ManagedBean(name="dtColumnsView")
@ViewScoped
public class ColumnsView implements Serializable {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static List<String> VALID_COLUMN_KEYS = Arrays.asList("id", "category", "type", "weight", "price", "sender", "company_name", "date");
     
    private String columnTemplate = "category type weight price sender date";
     
    private List<ColumnModel> columns;
     
    private List<GoodsEntity> listGoods;
     
    private List<GoodsEntity> filteredGoods;
     
 //   @ManagedProperty("#{carService}")
//    private CarService service;
 
    @PostConstruct
    public void init() {
//        cars = service.createCars(10);
         
        createDynamicColumns();
    }
     
    public List<GoodsEntity> getListGoods() {
        return listGoods;
    }
 
    public List<GoodsEntity> getFilteredGoods() {
        return filteredGoods;
    }
 
    public void setFilteredCars(List<GoodsEntity> filteredGoods) {
        this.filteredGoods = filteredGoods;
    }
/* 
    public void setService(CarService service) {
        this.service = service;
    }
 */
    public String getColumnTemplate() {
        return columnTemplate;
    }
 
    public void setColumnTemplate(String columnTemplate) {
        this.columnTemplate = columnTemplate;
    }
 
    public List<ColumnModel> getColumns() {
        return columns;
    }
 
    private void createDynamicColumns() {
        String[] columnKeys = columnTemplate.split(" ");
        columns = new ArrayList<ColumnModel>();   
         
        for(String columnKey : columnKeys) {
            String key = columnKey.trim();
             
            if(VALID_COLUMN_KEYS.contains(key)) {
                columns.add(new ColumnModel(columnKey.toUpperCase(), columnKey));
            }
        }
    }
     
    public void updateColumns() {
        //reset table state
 //       UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form:historyTableP");
 //       table.setValueExpression("sortBy", null);
        
         
        //update columns
        createDynamicColumns();
    }
     
    static public class ColumnModel implements Serializable {
 
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String header;
        private String property;
 
        public ColumnModel(String header, String property) {
            this.header = header;
            this.property = property;
        }
 
        public String getHeader() {
            return header;
        }
 
        public String getProperty() {
            return property;
        }
    }
}