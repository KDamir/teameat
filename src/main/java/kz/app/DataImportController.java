package kz.app;



import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import kz.app.utils.Parser;


@ManagedBean(name = "dataImportService")
@SessionScoped
public class DataImportController {


    @PostConstruct
    private void init() {
        
    }

  
    public void importMeatType() {
    	Parser.parse_meatType("/home/yerulan/teameat_new/teameat/import/meatType.xls");
    }
    
    public void importInventory() {
    	Parser.parse_inventory("/home/yerulan/teameat_new/teameat/import/inventory.xls");
    }
    

    
    public void exportMeatType() {
    	Parser.parse_exportMeatType("/home/yerulan/teameat_new/teameat/import/meatTypes-" + new java.util.Date().toString() + ".xls");
    }
    
    public void exportGoodsG() {
    	Parser.parse_exportGoodsG("/home/yerulan/teameat_new/teameat/import/Продажи " + new java.util.Date().toString() + ".xls");
    }
    
    public void exportGoodsSup() {
    	Parser.parse_exportGoodsSup("/home/yerulan/teameat_new/teameat/import/Покупки " + new java.util.Date().toString() + ".xls");
    }
    
    public void exportCategory() {
    	Parser.parse_exportCategory("/home/yerulan/teameat_new/teameat/import/Категории " + new java.util.Date().toString() + ".xls");
    }
    
    public void exportReceiver() {
    	Parser.parse_exportReceiver("/home/yerulan/teameat_new/teameat/import/Получатели " + new java.util.Date().toString() + ".xls");
    }
    
    public void exportSupplier() {
    	Parser.parse_exportSupplier("/home/yerulan/teameat_new/teameat/import/Поставщики " + new java.util.Date().toString() + ".xls");
    }
 
}
