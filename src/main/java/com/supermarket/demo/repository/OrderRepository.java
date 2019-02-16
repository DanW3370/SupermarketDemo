package com.supermarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supermarket.demo.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
