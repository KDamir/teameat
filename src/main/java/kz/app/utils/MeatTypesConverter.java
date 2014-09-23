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
import kz.app.entity.MeatTypes;

/**
 *
 * @author damir.keldibekov
 */
@FacesConverter(forClass = MeatTypes.class)
public class MeatTypesConverter implements Converter{

    MeatPartDao dao = new MeatPartDao();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null) 
            return null;
        HibernateUtil.getSession().beginTransaction();
        MeatTypes meatType = dao.getMeatTypeById(string);
        HibernateUtil.getSession().getTransaction().commit();
        return meatType;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((MeatTypes) o).toString();
    }
    
}
