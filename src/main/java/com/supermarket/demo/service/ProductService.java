package com.supermarket.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.supermarket.demo.entity.Product;

import repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product update(Product product) {
		return productRepository.save(product);
	}
	
	public void delete(String id) {
		productRepository.deleteById(id);
	}

	public Optional<Product> get(String id) {
		return productRepository.findById(id);
	}

	public Product create(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}
}
