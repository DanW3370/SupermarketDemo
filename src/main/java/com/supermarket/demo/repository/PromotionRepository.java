package com.supermarket.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.demo.entity.Product;
import com.supermarket.demo.entity.Promotion;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>{

	  List<Promotion> findByProduct(Product product);

}
