package com.supermarket.demo.rest;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.supermarket.demo.entity.Product;
import com.supermarket.demo.service.ProductService;


@RestController
@RequestMapping("api/products")
public class ProductResource {
	@Autowired
	ProductService productService;
	
	@GetMapping("/{id}")
	public Product get(@PathVariable String id){
		 Optional<Product> product = productService.get(id);

	        if(!product.isPresent()){
	            throw new EntityNotFoundException("Entity not found for Id: " + id);
	        }

	        return product.get();
		
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.create(product);
		
	}

	@PutMapping
	public Product update(@RequestBody Product product) {
		return productService.update(product);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		productService.delete(id);
	}
}
