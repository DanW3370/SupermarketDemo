package com.supermarket.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Order_Prod")
public class OrderProduct {
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
	private Order order;
	@Column
	@NotNull
	private Integer quantity;
	
	@Column
	private double price;
	
	@OneToOne
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	
	public OrderProduct(Product product, Integer quantity) {
		setProduct(product);
		setQuantity(quantity);
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
}
