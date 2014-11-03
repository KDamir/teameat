/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import kz.app.MeatPart;
import kz.app.dao.MeatPartPurchaseDao;
import kz.app.entity.CalculationEntity;
import kz.app.entity.PurchaseEntity;
import kz.app.entity.MeatPartPurchaseEntity;

/**
 *
 * @author Куат
 */
public class MeatPartPurchaseConverter {
    private static MeatPartPurchaseDao dao = new MeatPartPurchaseDao();
    public static MeatPartPurchaseEntity convertMeatPartPurchaseToEntity(MeatPart meatPart, PurchaseEntity purchase, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartPurchaseEntity entity = dao.getMeatPartPurchaseById(meatPart.getId());
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

    public static MeatPart convertEntityToMeatPart(MeatPartPurchaseEntity entity) {
        if(entity == null) {
            return null;
        }
        MeatPart meatPart = new MeatPart();
        meatPart.setId(entity.getId());
        meatPart.setPrice(entity.getPrice());
        meatPart.setWeight(entity.getWeight());
        meatPart.setCategory(entity.getCategoryId());
        meatPart.setType(entity.getTypeId());
        return meatPart;
       }
}
