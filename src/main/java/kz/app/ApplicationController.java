package kz.app;

import java.util.List;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;
import kz.app.entity.ReceiverEntity;

/**
 * Created by Vlad Zheltovskiy.
 * E-mail: vladislav.zheltovsky@inessoft.kz
 * Skype: v.zheltovskiy
 * <p/>
 * Date: 07.10.2014
 * Time: 23:45
 */
public class ApplicationController{
    
    private static final MeatPartDao dao = new MeatPartDao();
    
    public static final List<MeatTypesEntity> types = dao.getTypesList();
    public static final List<MeatCategoryEntity> categories = dao.getCategoriesList();
    public static final List<ReceiverEntity> receivers = dao.getReceiversList();
    
    public ApplicationController() {
    }

}
