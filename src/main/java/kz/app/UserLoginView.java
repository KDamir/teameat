package kz.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.primefaces.context.RequestContext;


@ManagedBean
public class UserLoginView {
	
	//@Resource(name="jdbc/mydb")
	private DataSource ds;
	
	private String username;
    
    private String password;
    
    public UserLoginView() {
    	try {
			Context tx = new InitialContext();
			ds = (DataSource) tx.lookup("java:comp/env/jdbc/mydb");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(ActionEvent event) {
		try {
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			String sql = "select surname from employee";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String sr = rs.getString("surname");
				System.out.println("surname = " + sr);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean logged = false;
		
		if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
			logged = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Добро пожаловать " + username + "!", username);
		} else {
			logged = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Неверный логин или пароль", "Invalid credentials");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("logged", logged);
	}

}
