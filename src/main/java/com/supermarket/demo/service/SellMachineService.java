package com.supermarket.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.supermarket.demo.entity.Order;
import com.supermarket.demo.entity.OrderProduct;
import com.supermarket.demo.entity.Product;
import com.supermarket.demo.entity.Promotion;
import com.supermarket.demo.exception.NotEnoughProductException;
import com.supermarket.demo.repository.OrderRepository;
import com.supermarket.demo.type.PromotionType;

@Service
public class SellMachineService {

	private Order currentOrder;
	
	private Map<Long,OrderProduct> orderProductMap = new HashMap<Long,OrderProduct>();
	private List<OrderProduct> productList = new ArrayList<>();
	
	private PromotionService promotionService;
	private OrderRepository orderRepository;
	private ProductService productService;

	public SellMachineService(PromotionService promotionService, OrderRepository orderRepository, ProductService productRepository) {
		this.promotionService = promotionService;
		this.orderRepository = orderRepository;
		this.productService = productRepository;
		initData();
	}

	private void initData() {
		//initial product data to database
		Product pizza = new Product("Pizza",10.00, 10);
		Product fries = new Product("Fries",3.00, 10);
		Product burger = new Product("Burger",10.00, 10);
		Product salad = new Product("Salad",5.00, 10);
		Product coke = new Product("Coke",2.00, 10);
		
		productService.create(pizza);
		productService.create(fries);
		productService.create(burger);
		productService.create(salad);
		productService.create(coke);
		
		//initial promotion Data to database
		Promotion pizzaPmt1 = new Promotion(3.99,pizza,PromotionType.PROMOTION_WITH_PRODUCT_QUANTITY, 2);
		Promotion pizzaPmt2 = new Promotion(9.99,pizza,PromotionType.PROMOTION_WITH_PRODUCT_QUANTITY, 3);
		Promotion burgerPmt = new Promotion(4.99,burger,PromotionType.PROMOTION_WITH_PRODUCT_QUANTITY, 2);
		Promotion friesPmt = new Promotion(0.99,burger,PromotionType.PROMOTION_WITH_PRODUCT_QUANTITY, 1);
		
		promotionService.create(pizzaPmt1);
		promotionService.create(pizzaPmt2);
		promotionService.create(burgerPmt);
		promotionService.create(friesPmt);
	}

	/**
	 * checkout: create a current order that can retrn to ront to display the info
	 * haven't saved the order in the database
	 * @param productList
	 * @return Order
	 * @throws NotEnoughProductException
	 */
	public Order checkOut() throws NotEnoughProductException {
		double totalPrice =0.00 ;
		double totalSavedPrice=0.00;		
		List<Promotion> promotionList = new ArrayList<>();
	
		
		for(int i=0;i<productList.size();i++) {
			OrderProduct orderProduct = productList.get(i);		
			//validate product
			validate(orderProduct);			
			
			//process promotions and totalPrice
			double price = orderProduct.getProduct().getPrice() * orderProduct.getQuantity() ;
			totalPrice+=price;
			//check if any promotion can be used
			List<Promotion> appliedPromotions =promotionService.findPromotion(orderProduct);
			
			for(int j=0;j<appliedPromotions.size();j++) {
				totalSavedPrice+=appliedPromotions.get(j).getSave();
			}
		}
		totalPrice-=totalSavedPrice;
		if(productList.size()>0) {
			this.currentOrder = new Order(productList,totalSavedPrice,totalPrice);
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
		if(product.getStore() < orderProdct.getQuantity()) {
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
	
	public List<OrderProduct> getProductListInBasket() {
		return this.productList;
	}
	
	public void cancel() {
		currentOrder= new Order();
	}

	public List<OrderProduct> addProduct(Long id) throws NotEnoughProductException {
		OrderProduct orderProduct = orderProductMap.get(id);
		if(orderProductMap.containsKey(id)) {
			orderProduct = orderProductMap.get(id);
			orderProduct.setQuantity(orderProduct.getQuantity() + 1);
			try {
				validate(orderProduct);
			} catch (NotEnoughProductException e) {
				orderProduct.setQuantity(orderProduct.getQuantity()-1);
				throw e;
			}
		}else {
			Optional<Product> product = productService.get(id);
			if(!product.isPresent()){
				throw new EntityNotFoundException("Entity not found for Id: " + id);
		    }
			orderProduct = new OrderProduct(product.get(),new Integer(1));
			orderProductMap.put(id, orderProduct);
			this.productList.add(orderProduct);
		}

		return this.productList;
	}

	public void cleanBasket() {
		orderProductMap.clear();
		this.productList.clear();
	}
}
