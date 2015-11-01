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
    	Parser.parse_meatType("D://GIT//teameat//import//meatType.xls");
    }
    
    public void importInventory() {
    	Parser.parse_inventory("D://GIT//teameat//import//inventory.xls");
    }
 
}
