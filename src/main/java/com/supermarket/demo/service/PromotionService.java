package com.supermarket.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.demo.entity.OrderProduct;
import com.supermarket.demo.entity.Promotion;

import com.supermarket.demo.repository.PromotionRepository;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepository promotionRepository;
	
	public Promotion create(Promotion promotion) {
		return promotionRepository.save(promotion);
	}
	
	public Promotion update(Promotion promotion) {
		return promotionRepository.save(promotion);
	}
	
	public void delete(String id) {
		promotionRepository.deleteById(id);
	}
	
	public Optional<Promotion> get(String id) {
		return promotionRepository.findById(id);
	}
	
	/**
	 * lookup the promotion of the product with the quatity
	 * @param OrderProduct product
	 * @return promotion
	 */
	public Promotion getPromotionByProduct(OrderProduct product) {
		
		return null;
	}

	public List<Promotion> getAll() {
		return promotionRepository.findAll();
	}
}
