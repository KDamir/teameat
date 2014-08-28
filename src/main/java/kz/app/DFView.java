package kz.app;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

@ManagedBean(name="dfview")
public class DFView {

	public String addZakaz() {
		return "success";
	}

}
