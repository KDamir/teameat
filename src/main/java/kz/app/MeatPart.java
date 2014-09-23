package kz.app;

public class MeatPart{
	
    private String type;
    private String category;
    // Вес
    private Double weight = 0.0;
    // Продажная цена
    private Double price = 0.0;
    // Процент от общего веса
    private Double weightPercent = 0.0;
    // Сумма продаж
    private Double profit = 0.0;
    // Безубыточная цена
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
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
