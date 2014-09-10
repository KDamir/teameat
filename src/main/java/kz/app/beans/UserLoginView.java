package kz.app.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    boolean logged = false;

	// Группы пользователей. Возможно, стоит поместить в утилитный класс.
	// TODO: Возможно, лучше создать enum вместо строковых констант, так будет красивей. Проверить, съест ли enum'ы JSF.
	public static final String ADMINISTRATOR = "ADMINISTRATOR";
	public static final String SUPERVISOR = "SUPERVISOR";
	public static final String USER = "USER";
    
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
	
	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
	/*Проверка, авторизован ли пользователь, если нет, то редиректим на логин форму*/
	public boolean isLogged() {
		return logged;
	}

	/* TODO: Функция должна возвращать строковую константу группы юзеров.
		В зависимости от этого, будем редиректить на нужную страницу (через faces-config)
	 */
	public String login() {
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

		// Через UserDao забираем по юзернейму хэш пароля из БД
		// Здесь будет PasswordUtil.check(password, storedPassword)
		if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
			setLogged(true);
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Добро пожаловать " + username + "!", username);
		} else {
			setLogged(false);
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Неверный логин или пароль", "Invalid credentials");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("logged", logged);
		return USER;
	}

}
