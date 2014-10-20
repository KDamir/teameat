package kz.app.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import kz.app.ApplicationController;

import kz.app.dao.UserDao;
import kz.app.entity.UsersEntity;
import kz.app.utils.HibernateUtil;

import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class UserLoginView{
	
    private String username;
    
    private String password;
    
    boolean logged = false;
    
    UserDao userDao;
    
    // Группы пользователей. Возможно, стоит поместить в утилитный класс.
    // TODO: Возможно, лучше создать enum вместо строковых констант, так будет красивей. Проверить, съест ли enum'ы JSF.
    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    public static final String SUPERVISOR = "SUPERVISOR";
    public static final String USER = "USER";
    
    @PostConstruct
    public void init() {
    	userDao = new UserDao();
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

        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;

        FacesContext fc = FacesContext.getCurrentInstance();
        ApplicationController appBean =(ApplicationController) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "appBean");

        UsersEntity user = userDao.getUserByLogin(username);
        if(user != null)
            if(user.getPassword().equals(password)) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Добро пожаловать " + username + "!", username);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.addCallbackParam("logged", logged);
                appBean.setGroup(user.getGroupId());
                return "success";
            } else {
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Неверный логин или пароль", "Invalid credentials");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.addCallbackParam("logged", logged);
                return "error";
            }
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Неверный логин или пароль", "Invalid credentials");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("logged", logged);
            return "error";
        }

        // Через UserDao забираем по юзернейму хэш пароля из БД
        // Здесь будет PasswordUtil.check(password, storedPassword)
        /*if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
                setLogged(true);
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Добро пожаловать " + username + "!", username);
        } else {
                setLogged(false);
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Неверный логин или пароль", "Invalid credentials");
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("logged", logged);*/
    }

    public String logout() {
        this.logged = false;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(session != null)
            session.invalidate();
        return "go_to_login";
    }
}
