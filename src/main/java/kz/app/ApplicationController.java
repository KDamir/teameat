package kz.app;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.UserGroupsEntity;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 07.10.2014
 * Time: 23:45
 */
@ManagedBean(name = "appBean")
@SessionScoped
public class ApplicationController{
    
    public static final MeatPartDao dao = new MeatPartDao();
    
    public static final List<MeatTypesEntity> types = dao.getTypesList();
    public static final List<MeatCategoryEntity> categories = dao.getCategoriesList();
    public static final List<ReceiverEntity> receivers = dao.getReceiversList();
    
    private UserGroupsEntity group;

    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public UserGroupsEntity getGroup() {
        return group;
    }
    
    public void setGroup(UserGroupsEntity group) {
        this.group = group;
    }
    
    public ApplicationController() {
    }
//</editor-fold>

}
