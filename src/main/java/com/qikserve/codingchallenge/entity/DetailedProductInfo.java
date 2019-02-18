package com.qikserve.codingchallenge.entity;

public class DetailedProductInfo {

    private String id;
    private String name;
    private long price;
    private ProductPromotion[] promotions;

    public DetailedProductInfo(String id, String name, long price, ProductPromotion[] promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public ProductPromotion[] getPromotions() {
        return promotions;
    }

}
