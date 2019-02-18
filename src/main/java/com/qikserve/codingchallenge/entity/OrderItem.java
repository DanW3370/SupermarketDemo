package com.qikserve.codingchallenge.entity;

import java.util.List;

public class OrderItem {

    private DetailedProductInfo product;
    private List<ProductPromotion> promotion;
    private Integer quantity;
    private Long price;
    private Long save;

    public DetailedProductInfo getProduct() {
        return product;
    }

    public void setProduct(DetailedProductInfo product) {
        this.product = product;
    }

    public List<ProductPromotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<ProductPromotion> promotion) {
        this.promotion = promotion;
    }

    public Integer getQuantity() {
        if(this.quantity == null){
            this.quantity = 0;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getSave() {
        return save;
    }

    public void setSave(Long save) {
        this.save = save;
    }
}
