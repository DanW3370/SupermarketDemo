package com.supermarket.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;

@Entity
@Table(name="d_promotion")
@NamedQuery(name = "Promotion.findByProduct", query = "select p from Promotion p where p.product = ?1 order by quantity DESC")
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private Double save;
	
	@OneToOne
	@JoinColumn(name = "prod_id", referencedColumnName = "id")
	private Product product;
	
	@Column
	@NotNull
	private Integer type;
	
	@Column
	@NotNull
	private Integer quantity;
	
	public Promotion(double save, Product product, Integer type, int quantity) {
		this.save = save;
		this.product = product;
		this.type = type;
		this.quantity = quantity;
	}
	
	public Promotion() {
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getSave() {
		return save;
	}
	public void setSave(Double save) {
		this.save = save;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
