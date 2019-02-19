package com.qikserve.codingchallenge.rest;

import com.qikserve.codingchallenge.entity.Order;
import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/shoppingCart")
public class ShoppingCartResource {

	@Autowired
	private ShoppingCartService machineService;

	@GetMapping("/addProduct/{id}")
	public Order addProduct(@PathVariable String id) {
		return machineService.addProduct(id);
	}

	@GetMapping("/checkout")
	public Order checkout() {
		return machineService.checkOut();
	}

	@GetMapping("/getOrderHistory")
	public List<Order> getOrderHistory(){
		return machineService.getOrderHistory();
	}

	@DeleteMapping("/cleanBasket")
	public void cleanBasket() {
		machineService.cleanBasket();
	}
}
