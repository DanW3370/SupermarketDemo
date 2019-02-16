package com.supermarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.supermarket.demo.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

}
