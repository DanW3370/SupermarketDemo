package com.supermarket.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.entity.Order;
import com.supermarket.demo.entity.OrderProduct;
import com.supermarket.demo.service.SellMachineService;
import com.supermarket.exception.NotEnoughProductException;

@RestController
@RequestMapping("api/machine")
public class SellMachineResouce {

	@Autowired
	private SellMachineService machineService;
	
	@PostMapping("/addProduct/{id}")
	public List<OrderProduct> addProduct(@RequestParam String id){
		
		return machineService.addProduct(id);
	}
	
	@PostMapping("/checkout")
	public Order checkout(@RequestBody List<OrderProduct> list) throws NotEnoughProductException {
		return machineService.checkOut(list);
	}
	
	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody Order order) throws NotEnoughProductException{
		return machineService.placeOrder();
	}
	
	@DeleteMapping("/cancelOrder")
	public void cancelOrder() {
		machineService.cancel();
	}
	
	@DeleteMapping("/cleanBasket")
	public void cleanBasket() {
		machineService.cleanBasket();
	}
}
