package kz.app;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

@ManagedBean
public class UserLoginView {
	private String username;
    
    private String password;

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
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean logged = false;
		
		if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
			logged = true;
			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
		} else {
			logged = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Erro", "Invalid credentials");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
		context.addCallbackParam("logged", logged);
	}

}
