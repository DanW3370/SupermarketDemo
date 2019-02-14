package com.supermarket.demo.entity;

import java.util.List;

public class Product {
	private Integer id;
	private String  name;
	private double price;
	private List promotions;
	private Integer store;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public Integer getStore() {
		return store;
	}
	public void setStore(Integer store) {
		this.store = store;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public List getPromotions() {
		return promotions;
	}
	public void setPromotions(List promotions) {
		this.promotions = promotions;
	}
	
}
