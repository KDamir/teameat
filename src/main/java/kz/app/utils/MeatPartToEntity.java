/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import kz.app.dao.MeatPartDao;
import kz.app.entity.Invoice;
import kz.app.entity.MeatPart;

/**
 *
 * @author Дамир
 */
public class MeatPartToEntity {
    private final static MeatPartDao dao = new MeatPartDao();
    
    public static MeatPart getMeatPartEntity(kz.app.MeatPart meatPart, Invoice invoice) {
        if(meatPart == null) {
            return null;
        }
        MeatPart entity = new MeatPart();
        entity.setPrice(meatPart.getPrice());
        entity.setWeight(meatPart.getWeight());
        entity.setCategoryId(dao.getMeatCategoryById(meatPart.getCategory()));
        entity.setTypeId(dao.getMeatTypeById(meatPart.getType()));
        entity.setInvoiceId(invoice);
        return entity;
    }
}
