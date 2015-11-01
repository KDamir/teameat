package kz.app;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import kz.app.dao.SupplierDao;
import kz.app.entity.SupplierEntity;

import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "supplierController")
@SessionScoped
public class SupplierController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private LazyDataModel<SupplierEntity> suppliers;
	public static SupplierDao daos;

	private SupplierEntity selectedSupplier;

	

	public SupplierEntity getSelectedSupplier() {
		return selectedSupplier;
	}

	public void setSelectedSupplier(SupplierEntity selectedSupplier) {
		this.selectedSupplier = selectedSupplier;
	}

	@PostConstruct
	public void init() {
		daos = new SupplierDao();
		suppliers = new SupplierDataModel(ApplicationController.suppliers);
	}

	public LazyDataModel<SupplierEntity> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(LazyDataModel<SupplierEntity> suppliers) {
		this.suppliers = suppliers;
	}

	public void onEdit() {
		
		daos.saveSupplier(selectedSupplier);
		FacesMessage msg;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Изменения сохранены","");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
