package com.qikserve.codingchallenge.service;

import java.util.ArrayList;
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
	 * @param OrderProduct orderProduct
	 * @return promotion: the best promotion can be used(save most money)
	 */
	public List<Promotion> findPromotion(OrderProduct orderProduct) {
		
		List<Promotion> promList =  promotionRepository.findByProduct(orderProduct.getProduct());
		
		List<Promotion> AppliedPromList = new ArrayList<>();
		int nums = orderProduct.getQuantity();
		for(int i=0;i<promList.size();i++) {
			Promotion promotion = promList.get(i);
			while(nums>=promotion.getQuantity()) {
				AppliedPromList.add(promotion);
				nums-=promotion.getQuantity();
			}
		}
		return AppliedPromList;
	}
	

	public List<Promotion> getAll() {
		return promotionRepository.findAll();
	}
}
