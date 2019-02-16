package com.supermarket.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import com.supermarket.demo.entity.Order;
import com.supermarket.demo.entity.OrderProduct;
import com.supermarket.demo.entity.Product;
import com.supermarket.demo.entity.Promotion;
import com.supermarket.demo.type.PromotionType;
import com.supermarket.exception.NotEnoughProductException;

import repository.OrderRepository;

public class SellMachineService {

	private Order currentOrder = new Order();
	
	private Map<String,OrderProduct> OrderProductMap = new HashMap<String,OrderProduct>();
	
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductService productService;	
	
	
	/**
	 * checkout: create a current order that can retrn to ront to display the info
	 * haven't saved the order in the database
	 * @param productList
	 * @return Order
	 * @throws NotEnoughProductException
	 */
	public Order checkOut(List<OrderProduct> productList) throws NotEnoughProductException {
		double totalPrice =0.00 ;
		double totalSavedPrice=0.00;		
		List<Promotion> promotionList = new ArrayList<>();
		
		for(int i=0;i<productList.size();i++) {
			OrderProduct orderProduct = productList.get(i);
			
			//validate product
			validate(orderProduct);			
			
			//process promotions and totalPrice
			double price = orderProduct.getProduct().getPrice() * orderProduct.getQuantity() ;
			//check if any promotion can be used
			Promotion promotion =promotionService.getPromotionByProduct(orderProduct);
			if(promotion!=null && promotion.getType().equals(PromotionType.PROMOTION_WITH_PRODUCT_QUANTITY)) {
				double savedPrice = promotion.getSave();
				price = orderProduct.getProduct().getPrice() * orderProduct.getQuantity() - savedPrice;
				
				totalSavedPrice+=savedPrice;
				
				promotionList.add(promotion);
			}			
			totalPrice+=price;			
		}
		
		if(this.currentOrder == null) {
			this.currentOrder = new Order(productList,promotionList,totalSavedPrice,totalPrice);
		}
		return currentOrder;
	}
	
	/** 
	 * validate the orderProduct
	 * @param orderProdct
	 * @return Product
	 * @throws NotEnoughProductException: if the product doesn't have enough stock
	 */
	public Product validate(OrderProduct orderProdct) throws NotEnoughProductException{
		Product product = orderProdct.getProduct();
		if(product.getStore() <= orderProdct.getQuantity()) {
			throw new NotEnoughProductException("There's no enough product stock!");
		}
		return product;
	}

	/**
	 * place order: complete order
	 * save the order record to database and cut the product stock
	 * @return Order: the saved order
	 * @throws NotEnoughProductException
	 */
	public Order placeOrder() throws NotEnoughProductException {
		List<OrderProduct> list = currentOrder.getOrderProducts();
		for(int i=0; i<list.size();i++) {
			OrderProduct orderProduct = list.get(i);
			Product product = validate(orderProduct);
			product.setStore(product.getStore() - orderProduct.getQuantity());
			productService.update(product);
		}
		return orderRepository.save(currentOrder);
	}
	
	public void cancel() {
		currentOrder= new Order();
		OrderProductMap = new HashMap<String,OrderProduct>();
	}

	public List<OrderProduct> addProduct(String id) {
		OrderProduct orderProduct = OrderProductMap.get(id);
		if(OrderProductMap.containsKey(id)) {
			orderProduct = OrderProductMap.get(id);
			orderProduct.setQuantity(orderProduct.getQuantity() + 1);			
		}else {
			Optional<Product> product = productService.get(id);
			if(!product.isPresent()){
				throw new EntityNotFoundException("Entity not found for Id: " + id);
		    }
			orderProduct = new OrderProduct(product.get(),new Integer(1));
			OrderProductMap.put(id, orderProduct);
		}
		
		List<OrderProduct> productList = new ArrayList<>();
		for(OrderProduct entry: OrderProductMap.values()) {
			productList.add(entry);
		}
		return productList;
		
		
	}
}
