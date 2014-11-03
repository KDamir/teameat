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
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.entity.ReceiverEntity;
import kz.app.entity.SupplierEntity;

/**
 *
 * @author kuat
 */
@FacesConverter(forClass = SupplierEntity.class)
public class SupplierConverter implements Converter{

    MeatPartPurchaseDao dao = new MeatPartPurchaseDao();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null) 
            return null;
        SupplierEntity supplier = dao.getSupplierById(string);
        return supplier;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((SupplierEntity) o).toString();
    }
    
}
