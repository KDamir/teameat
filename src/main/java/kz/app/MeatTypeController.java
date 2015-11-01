package kz.app;

import kz.app.dao.MeatTypeDao;
import kz.app.entity.MeatTypesEntity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "meatTypeController")
@SessionScoped
public class MeatTypeController implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private LazyDataModel<MeatTypesEntity> meatTypes;
	public static MeatTypeDao dao;

	private MeatTypesEntity selectedMeatType;

	public MeatTypesEntity getSelectedMeatType() {
		return selectedMeatType;
	}

	public void setSelectedMeatType(MeatTypesEntity selectedMeatType) {
		this.selectedMeatType = selectedMeatType;
	}

	@PostConstruct
	public void init() {
		dao = new MeatTypeDao();
		meatTypes = new MeatTypeDataModel(ApplicationController.types);
	}

	public LazyDataModel<MeatTypesEntity> getMeatTypes() {
		meatTypes = new MeatTypeDataModel(ApplicationController.types);
		return meatTypes;
	}

	public void setMeatTypes(LazyDataModel<MeatTypesEntity> meatTypes) {
		this.meatTypes = meatTypes;
	}

	public void onEdit() {
		
		dao.saveType(selectedMeatType);
		FacesMessage msg;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO ,"Изменения сохранены","");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
