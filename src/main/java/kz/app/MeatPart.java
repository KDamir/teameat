package kz.app;

import kz.app.entity.MeatCategoryEntity;
import kz.app.entity.MeatTypesEntity;

public class MeatPart {
	
//    private String type;
//    private String category;
    private MeatTypesEntity type;
    private MeatCategoryEntity category;
    // Вес
	private Double weight = 0.0;
    // Продажная цена
	private Double price = 0.0;
    // Процент от общего веса
	private Double weightPercent = 0.0;
    // Сумма продаж
	private Double revenue = 0.0;
    // Безубыточная цена
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

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
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Double getWeightPercent() {
		return weightPercent;
	}

	public void setWeightPercent(Double weightPercent) {
		this.weightPercent = weightPercent;
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

}
