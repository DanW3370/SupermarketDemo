package com.qikserve.codingchallenge.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> orderItems;

    private Long totalPrice;
    private Long totalSave;
    private Long finalPrice;

    public List<OrderItem> getOrderItems() {
        if(this.orderItems == null){
            this.orderItems = new ArrayList<>();
        }
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getTotalPrice() {
        this.totalPrice = Long.valueOf(0);
        for(OrderItem item: this.getOrderItems()){
            this.totalPrice += item.getPrice();
        }

        return this.totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalSave() {
        this.totalSave = Long.valueOf(0);
        for(OrderItem item: this.getOrderItems()){
            this.totalSave += item.getSave();
        }

        return this.totalSave;
    }

    public void setTotalSave(Long totalSave) {
        this.totalSave = totalSave;
    }

    public Long getFinalPrice() {
        return this.totalPrice - this.totalSave;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void addOrderItem(OrderItem newItem) {
        this.getOrderItems().add(newItem);
    }
}
