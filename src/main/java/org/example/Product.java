package org.example;

import java.util.Objects;

public class Product {

    private String name;
    private double unitCost;
    private String markup;
    private String promotion;


    public Product(String name, double unitCost, String markup, String promotion) {
        this.name = name;
        this.unitCost = unitCost;
        this.markup = markup;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public String getMarkup() {
        return markup;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public void setMarkup(String markup) {
        this.markup = markup;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getPaidQuantity(int quantity,String[] parts){
        int freeQuantity = Integer.parseInt(parts[3].substring(0,parts[3].length()-2));
        int count = 0;
        for(int i=1;i<=quantity;i++){
            if(i%freeQuantity==0){
                count++;
            }
        }
        quantity -= count;
        return quantity;
    }

    public double getProductPrice(int quantity){

        double markUpValuePercentage = 0.0;
        double markUpValuePerUnit = 0.0;
        double promotionValue = 0.0;

        //string markup to double value
        if (markup.endsWith("%")) {
            markUpValuePercentage = Double.parseDouble(markup.substring(0, markup.length() - 1)) / 100.0;
        } else if (markup.endsWith(" EUR/unit")) {
            markUpValuePerUnit = Double.parseDouble(markup.substring(0, markup.length() - 9));
        }

        //string promotion converting to double promotion
        if (this.promotion.equals("none")) {
            promotionValue = 0.0;
        } else if (this.promotion.endsWith("% off")) {
            promotionValue = Double.parseDouble(this.promotion.substring(0, this.promotion.length() - 5)) / 100.0;
        } else if (this.promotion.startsWith("Buy ")) {
            String[] parts = this.promotion.split(" ");
            quantity = this.getPaidQuantity(quantity,parts);
        }

        double productFinalPrice = 0.0;
        // calculate price for product
        if(markUpValuePerUnit==0.0){
            productFinalPrice = this.unitCost * (1 + markUpValuePercentage) * (1 - promotionValue);
        }else{
            productFinalPrice = (this.unitCost + markUpValuePerUnit)  -  (this.unitCost + markUpValuePerUnit)*promotionValue;
        }
        
        return quantity*productFinalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getName().equals(product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
