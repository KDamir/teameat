package kz.app.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Дамир
 */
@ManagedBean(name = "sessionPref")
@SessionScoped
public class SessionPref {
    
    private String login;
    private String password;
    private String pageToDisplay;
    
    @PostConstruct
    public void init() {
        login = null;
        password = null;
        pageToDisplay = "invoice";
    }

    //<editor-fold defaultstate="collapsed" desc="getter/setter">
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPageToDisplay() {
        return pageToDisplay;
    }
    
    public void setPageToDisplay(String pageToDisplay) {
        this.pageToDisplay = pageToDisplay;
    }
//</editor-fold>
    
}
