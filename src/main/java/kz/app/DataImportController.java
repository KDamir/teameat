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
    	Parser.parse_meatType("D:\\GIT\\teameat\\import\\meatType.xls");
    }
    
    public void importInventory() {
    	Parser.parse_inventory("D:\\GIT\\teameat\\import\\inventory.xls");
    }
    

    
    public void exportMeatType() {
    	Parser.parse_exportMeatType("D:\\GIT\\teameat\\import\\meatTypes" +  ".xls");
    }
    
    public void exportGoodsG() {
    	Parser.parse_exportGoodsG("D:\\GIT\\teameat\\import\\Продажи" +  ".xls");
    }
    
    public void exportGoodsSup() {
    	Parser.parse_exportGoodsSup("D:\\GIT\\teameat\\import\\Покупки" +  ".xls");
    }
    
    public void exportCategory() {
    	Parser.parse_exportCategory("D:\\GIT\\teameat\\import\\Категории" + ".xls");
    }
    
    public void exportReceiver() {
    	Parser.parse_exportReceiver("D:\\GIT\\teameat\\import\\Получатели" + ".xls");
    }
    
    public void exportSupplier() {
    	Parser.parse_exportSupplier("D:\\GIT\\teameat\\import\\Поставщики" + ".xls");
    }
 
}
