package com.supermarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.demo.entity.Promotion;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>{

}
