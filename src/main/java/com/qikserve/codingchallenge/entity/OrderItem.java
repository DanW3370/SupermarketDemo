package com.qikserve.codingchallenge.entity;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    private DetailedProductInfo detailedProductInfo;
    private List<ProductPromotion> usedPromotions = new ArrayList<>();
    private int quantity;
    private long price;
    private long save;

    public DetailedProductInfo getDetailedProductInfo() {
        return detailedProductInfo;
    }

    public void setDetailedProductInfo(DetailedProductInfo detailedProductInfo) {
        this.detailedProductInfo = detailedProductInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        this.price = this.detailedProductInfo.getPrice() * this.quantity;
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSave() {
        return save;
    }

    public void setSave(long save) {
        this.save = save;
    }

    public List<ProductPromotion> getUsedPromotions() {
        return usedPromotions;
    }

    public void setUsedPromotions(List<ProductPromotion> usedPromotions) {
        this.usedPromotions = usedPromotions;
    }

    public void addUsedPromotion(ProductPromotion promotion){
        this.getUsedPromotions().add(promotion);
    }
}
