/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import kz.app.MeatPart;
import kz.app.dao.MeatPartDao;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.dao.MeatPartReturnDao;
import kz.app.entity.CalculationEntity;
import kz.app.entity.InvoiceEntity;
import kz.app.entity.MeatPartEntity;
import kz.app.entity.MeatPartPurchaseEntity;
import kz.app.entity.MeatPartReturnEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.ReturnEntity;

/**
 *
 * @author Дамир
 */
public class MeatPartConverter {
    private static final MeatPartDao dao = new MeatPartDao();
    private static final MeatPartPurchaseDao daop = new MeatPartPurchaseDao();
    private static final MeatPartReturnDao daor = new MeatPartReturnDao();
    public static MeatPartEntity convertMeatPartToEntity(MeatPart meatPart, InvoiceEntity invoice, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartEntity entity = dao.getMeatPartById(meatPart.getId());
        if(entity == null)
            entity = new MeatPartEntity();
        entity.setPrice(meatPart.getPrice());
        entity.setWeight(meatPart.getWeight());
        entity.setCategoryId(meatPart.getCategory());
        entity.setTypeId(meatPart.getType());
        entity.setInvoiceId(invoice);
        entity.setCalculationId(calc);
        entity.setBall(meatPart.isBall());
        entity.setBarcode(meatPart.getBarcode());
        return entity;
    }

    public static MeatPart convertEntityToMeatPart(MeatPartEntity entity) {
        if(entity == null) {
            return null;
        }
        MeatPart meatPart = new MeatPart();
        meatPart.setId(entity.getId());
        meatPart.setPrice(entity.getPrice());
        meatPart.setWeight(entity.getWeight());
        meatPart.setCategory(entity.getCategoryId());
        meatPart.setType(entity.getTypeId());
        meatPart.setBall(entity.isBall());
        meatPart.setBarcode(entity.getBarcode());
        return meatPart;
       }
    
    public static MeatPart convertPurchaseEntityToMeatPart(MeatPartPurchaseEntity entity) {
        if(entity == null) {
            return null;
        }
        MeatPart meatPart = new MeatPart();
        meatPart.setId(entity.getId());
        meatPart.setPrice(entity.getPrice());
        meatPart.setWeight(entity.getWeight());
        meatPart.setCategory(entity.getCategoryId());
        meatPart.setType(entity.getTypeId());
        meatPart.setBall(false);
        meatPart.setBarcode(entity.getTypeId().getBarcode());
        return meatPart;
    }
    
    public static MeatPart convertReturnEntityToMeatPart(MeatPartReturnEntity entity) {
        if(entity == null) {
            return null;
        }
        MeatPart meatPart = new MeatPart();
        meatPart.setId(entity.getId());
        meatPart.setPrice(entity.getPrice());
        meatPart.setWeight(entity.getWeight());
        meatPart.setCategory(entity.getCategoryId());
        meatPart.setType(entity.getTypeId());
        meatPart.setBall(false);
        meatPart.setBarcode(entity.getTypeId().getBarcode());
        return meatPart;
    }
    
    public static MeatPartPurchaseEntity convertMeatPartToPurchaseEntity(MeatPart meatPart, PurchaseEntity purchase, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartPurchaseEntity entity = daop.getMeatPartPurchaseById(meatPart.getId());
        if(entity == null)
            entity = new MeatPartPurchaseEntity();
        entity.setPrice(meatPart.getPrice());
        entity.setWeight(meatPart.getWeight());
        entity.setCategoryId(meatPart.getCategory());
        entity.setTypeId(meatPart.getType());
        entity.setPurchaseId(purchase);
        entity.setCalculationId(calc);
        return entity;
    }
    
    public static MeatPartReturnEntity convertMeatPartToReturnEntity(MeatPart meatPart, ReturnEntity returning, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartReturnEntity entity = daor.getMeatPartReturnById(meatPart.getId());
        if(entity == null)
            entity = new MeatPartReturnEntity();
        entity.setPrice(meatPart.getPrice());
        entity.setWeight(meatPart.getWeight());
        entity.setCategoryId(meatPart.getCategory());
        entity.setTypeId(meatPart.getType());
        entity.setReturnId(returning);
        entity.setCalculationId(calc);
        return entity;
    }
    
}
