package com.supermarket.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.demo.entity.Order;
import com.supermarket.demo.entity.OrderProduct;
import com.supermarket.demo.service.SellMachineService;
import com.supermarket.demo.exception.NotEnoughProductException;

@RestController
@RequestMapping("api/machine")
public class SellMachineResouce {

	@Autowired
	private SellMachineService machineService;
	
	@GetMapping("/addProduct/{id}")
	public List<OrderProduct> addProduct(@PathVariable Long id) throws NotEnoughProductException{	
		return machineService.addProduct(id);
	}
	
	@GetMapping("/checkout")
	public Order checkout() throws NotEnoughProductException {
		return machineService.checkOut();
	}
	
	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody Order order) throws NotEnoughProductException{
		return machineService.placeOrder();
	}
	
	@GetMapping("/getBasket")
	public List<OrderProduct> getBasket(){	
		return machineService.getProductListInBasket();
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
