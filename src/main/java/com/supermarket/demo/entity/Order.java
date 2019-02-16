package com.supermarket.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="d_order")
public class Order {
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany()
	@JoinColumn(name = "prod_id", referencedColumnName = "id")
	private List<OrderProduct> orderProducts = new ArrayList<>();
	
	@Column
	private Double totalPrice;	
	
	@Column
	private Double savedMoney;
	
	@JsonFormat(pattern = "dd/MM/yyyy") 
	private LocalDate dateCreated;

	public Order(List<OrderProduct> productList, List<Promotion> promotionList, double totalSavedPrice,
			double totalPrice) {
		setTotalPrice(totalPrice);
		setSavedMoney(totalSavedPrice);
		setOrderProducts(productList);
	
	}

	public Order() {
		
	}

	public double getSavedMoney() {
		return savedMoney;
	}

	public void setSavedMoney(double savedMoney) {
		this.savedMoney = savedMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Double getTotalPrice() {
		
		return new Double(0.00);
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Transient
	public int getNumberOfProducts() {
	    return this.orderProducts.size();
	}

}
