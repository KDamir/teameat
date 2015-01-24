package kz.app;

import java.math.BigInteger;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

public class MeatPart {
    private Integer id;
    private MeatTypesEntity type;
    private MeatCategoryEntity category;
    
    private boolean ball;
    
    public boolean isBall(){
    	return ball;
    }
    
    public void setBall(boolean ball) {
        this.ball = ball;
    }
    
    // Вес
	private Double weight = 1.0;
    // Продажная цена
	private Double price = 0.0;

	public BigInteger getBarcode() {
		return barcode;
	}

	public void setBarcode(BigInteger barcode) {
		this.barcode = barcode;
	}

	private BigInteger barcode;
	
	
	
    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    public MeatTypesEntity getType() {
        return type;
    }

    public void setType(MeatTypesEntity type) {
        this.type = type;
    }

    public MeatCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(MeatCategoryEntity category) {
        this.category = category;
    }

    public Double getRevenue() {
        return weight * price;
    }

    public Double getItemReward(){
    	if (type == null)
    		return 0.0;
    	return weight * type.getReward();
    }
    
    // TODO: Возможно, имеет смысл отрефакторить: хранить в митпарте ссылку на его митсервис. Или хранить общий вес туши в каждом митпарте.
    public Double calculateWeightPercent(Double carcassWeight) {
        return carcassWeight==0 ? weight : weight/carcassWeight*100;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//</editor-fold>

}
