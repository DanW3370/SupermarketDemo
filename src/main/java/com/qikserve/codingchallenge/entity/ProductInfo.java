package com.qikserve.codingchallenge.entity;

public class ProductInfo {

    private String id;
    private String name;
    private long price;

    public ProductInfo(DetailedProductInfo dpi) {
        this.id = dpi.getId();
        this.name = dpi.getName();
        this.price = dpi.getPrice();
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

}
