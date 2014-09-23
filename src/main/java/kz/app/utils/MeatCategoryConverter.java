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
import kz.app.dao.InvoiceDao;
import kz.app.dao.MeatPartDao;
import kz.app.entity.MeatCategory;

/**
 *
 * @author damir.keldibekov
 */
@FacesConverter(forClass = MeatCategory.class)
public class MeatCategoryConverter implements Converter{

    MeatPartDao dao = new MeatPartDao();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null) 
            return null;
        HibernateUtil.getSession().beginTransaction();
        MeatCategory meatCategory = dao.getMeatCategoryById(string);
        HibernateUtil.getSession().getTransaction().commit();
        return meatCategory;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((MeatCategory) o).toString();
    }
    
}
