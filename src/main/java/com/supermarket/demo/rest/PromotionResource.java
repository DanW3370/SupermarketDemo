package com.supermarket.demo.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.entity.Promotion;
import com.supermarket.demo.service.PromotionService;

@RestController
@RequestMapping("api/promotions")
public class PromotionResource {
	@Autowired
	PromotionService promotionService;
	
	@PostMapping
	public Promotion create(@RequestBody Promotion promotion) {
		return promotionService.create(promotion);
	}
	
	@PutMapping
	public Promotion update(@RequestBody Promotion promotion) {
		return promotionService.update(promotion);
	}
	
	@GetMapping("/{id}")
	public Optional<Promotion> get(@RequestParam String id) {
		return promotionService.get(id);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@RequestParam String id) {
		promotionService.delete(id);
	}
}
