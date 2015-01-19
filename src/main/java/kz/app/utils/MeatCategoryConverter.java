/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategoryEntity;

/**
 *
 * @author damir.keldibekov
 */
@FacesConverter(forClass = MeatCategoryEntity.class)
public class MeatCategoryConverter implements Converter{

    MeatPartDao dao = new MeatPartDao();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null) 
            return null;
        MeatCategoryEntity meatCategory = dao.getMeatCategoryById(string);
        return meatCategory;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((MeatCategoryEntity) o).toString();
    }
    
}
