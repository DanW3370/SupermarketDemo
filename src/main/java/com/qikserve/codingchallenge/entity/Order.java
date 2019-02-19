package com.qikserve.codingchallenge.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private long id;
    private Instant orderDate;

    private List<OrderItem> orderItems = new ArrayList<>();

    private long totalPrice;
    private long totalSave;
    private long finalPrice;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getTotalPrice() {
        this.totalPrice = 0;
        for(OrderItem item: this.getOrderItems()){
            this.totalPrice += item.getPrice();
        }

        return this.totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTotalSave() {
        this.totalSave = 0;
        for(OrderItem item: this.getOrderItems()){
            this.totalSave += item.getSave();
        }

        return this.totalSave;
    }

    public void setTotalSave(long totalSave) {
        this.totalSave = totalSave;
    }

    public long getFinalPrice() {
        return this.totalPrice - this.totalSave;
    }

    public void setFinalPrice(long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public void addOrderItem(OrderItem newItem) {
        this.getOrderItems().add(newItem);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
