package com.qikserve.codingchallenge.rest;

import com.qikserve.codingchallenge.service.SellMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/machine")
public class ShoppingCartResource {

	@Autowired
	private SellMachineService machineService;
	
//	@GetMapping("/addProduct/{id}")
//	public List<OrderProduct> addProduct(@PathVariable Long id) {
//		return machineService.addProduct(id);
//	}
//
//	@GetMapping("/checkout")
//	public Order checkout() {
//		return machineService.checkOut();
//	}
//
//	@PostMapping("/placeOrder")
//	public Order placeOrder(@RequestBody Order order){
//		return machineService.placeOrder();
//	}
//
//	@GetMapping("/getBasket")
//	public List<OrderProduct> getBasket(){
//		return machineService.getProductListInBasket();
//	}
//
//	@DeleteMapping("/cancelOrder")
//	public void cancelOrder() {
//		machineService.cancel();
//	}
	
	@DeleteMapping("/cleanBasket")
	public void cleanBasket() {
		machineService.cleanBasket();
	}
}
