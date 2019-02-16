package com.supermarket.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Product {
	@Id
	@NotNull
	@Size(max=50)
	@Column(length=50)
	private String id;
	
	@NotNull
	@Size(max=50)
	@Column(length=50)
	private String  name;
	
	@NotNull
	private double price;
	
	private Integer store;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
}
