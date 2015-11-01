/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.app.utils;

import kz.app.MeatPart;
import kz.app.dao.MeatPartReturnDao;
import kz.app.entity.CalculationEntity;
import kz.app.entity.MeatPartReturnEntity;
import kz.app.entity.ReturnEntity;

/**
 *
 * @author Куат
 */
public class MeatPartReturnConverter {
    private static MeatPartReturnDao dao = new MeatPartReturnDao();
    public static MeatPartReturnEntity convertMeatPartReturnToEntity(MeatPart meatPart, ReturnEntity returning, CalculationEntity calc) {
        if(meatPart == null) {
            return null;
        }
        MeatPartReturnEntity entity = dao.getMeatPartReturnById(meatPart.getId());
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

    public static MeatPart convertEntityToMeatPart(MeatPartReturnEntity entity) {
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
