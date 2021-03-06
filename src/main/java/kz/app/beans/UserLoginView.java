package kz.app.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import kz.app.ApplicationController;

import kz.app.dao.UserDao;
import kz.app.entity.UsersEntity;
import kz.app.utils.PasswordUtil;
import org.primefaces.context.RequestContext;


@ManagedBean
@SessionScoped
public class UserLoginView{
    private static final Logger logger = Logger.getLogger(UserLoginView.class.getName());
    
    private String username;
    
    private String password;
    
    boolean logged = false;
    
    UserDao userDao;
    
    ApplicationController appBean;
    
    FacesContext fc;
    FacesMessage msg;
    RequestContext context;
    
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
    public String login() throws Exception {

        context = RequestContext.getCurrentInstance();
        msg = null;
        fc = FacesContext.getCurrentInstance();
        
        UsersEntity user = userDao.getUserByLogin(username);
        if(user != null)
            if(PasswordUtil.check(password,user.getPassword())) {
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Добро пожаловать " + username + "!", username);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                context.addCallbackParam("logged", logged);
                this.logged = true;
                logger.log(Level.INFO,"user={0}; Login.",new Object[]{username});
                appBean =(ApplicationController) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "appBean");
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
        logger.log(Level.INFO,"user={0}; Logout.",new Object[]{username});
        return "/pages/login.xhtml";
    }
}
