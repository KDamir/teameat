/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import kz.app.MeatPart;
import kz.app.dao.MeatPartDao;
import kz.app.entity.CalculationEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;

/**
 *
 * @author Дамир
 */
public class MeatPartConverter {
    public static MeatPartEntity convertMeatPartToEntity(MeatPart meatPart, InvoiceEntity invoice, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartEntity entity = new MeatPartEntity();
        entity.setPrice(meatPart.getPrice());
        entity.setWeight(meatPart.getWeight());
        entity.setCategoryId(meatPart.getCategory());
        entity.setTypeId(meatPart.getType());
        entity.setInvoiceId(invoice);
        entity.setCalculationId(calc);
        return entity;
    }

    public static MeatPart convertEntityToMeatPart(MeatPartEntity entity) {
        if(entity == null) {
            return null;
        }
        MeatPart meatPart = new MeatPart();
        meatPart.setPrice(entity.getPrice());
        meatPart.setWeight(entity.getWeight());
        meatPart.setCategory(entity.getCategoryId());
        meatPart.setType(entity.getTypeId());
        return meatPart;
       }
}
