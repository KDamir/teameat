package kz.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kz.app.Car;

 
@ManagedBean(name = "carService")
@ApplicationScoped
public class CarService {
     
	private DataSource ds;
	public Connection con;
	public Statement stmt;
	
    private static String[] names ;
     
    private static String[] categories;
  
    private EditView ev;
     
    public static void setNames(String[] names) {
		CarService.names = names;
	}

    public void dbConnection(){
    	try {
			Context tx = new InitialContext();
			ds = (DataSource) tx.lookup("java:comp/env/jdbc/teameat");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
    		con = ds.getConnection();
    		stmt = con.createStatement();
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	return ;
    }
    
    public void dbConnectionClose(){
    	try {
    		stmt.close();
    		con.close();
    	
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    }
    
	public List<Car> createCars(int size) {
		
		// таблица в ТТН
        List<Car> list = new ArrayList<Car>();
        for(int i = 0 ; i < size ; i++) {
            list.add(new Car("","","",""));
        	 
        }
        
        // заполнение комбо-боксов : names, categories
        dbConnection();

        return list;
    }
     
 
    public List<String> getNames() {
    	names = new String[1];
    	names[0] ="";
        return Arrays.asList(names);
    }
     
    public List<String> getCategories() { 
    	try {
        	String sql_num = "SELECT COUNT(*) AS rowcount FROM meat_category";
        	ResultSet rs_num = stmt.executeQuery(sql_num);
        	rs_num.next();
        	int count = rs_num.getInt("rowcount");
        	categories = new String[count];
        	rs_num.close();
        	
        	String sql = "select type from meat_category";
    		ResultSet rs = stmt.executeQuery(sql);
    		int i = 0;
    		
    		while(rs.next()) {
    			categories[i] = rs.getString("type");
    			++i;
    		}
    		rs.close();
    		
    		
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
        return Arrays.asList(categories);
    }
}
