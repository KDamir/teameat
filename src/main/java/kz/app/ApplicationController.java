package kz.app;

import java.util.List;
import javax.annotation.PostConstruct;
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
    
    public static MeatPartDao dao;
    
    public static List<MeatTypesEntity> types;
    public static List<MeatCategoryEntity> categories;
    public static List<ReceiverEntity> receivers;
    
    private UserGroupsEntity group;
    
    @PostConstruct
    public void init() {
        dao = new MeatPartDao();
        types = dao.getTypesList();
        categories = dao.getCategoriesList();
        receivers = dao.getReceiversList();
        
        /*Пустая ячейка*/
        MeatCategoryEntity blankCategory = new MeatCategoryEntity();
        blankCategory.setName("");
        categories.add(0, blankCategory);
    }

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
