package com.qikserve.codingchallenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.qikserve.codingchallenge.entity.Order;
import com.qikserve.codingchallenge.entity.OrderProduct;
import org.springframework.stereotype.Service;


@Service
public class SellMachineService {

	private Order currentOrder;
	
	private Map<Long, OrderProduct> orderProductMap = new HashMap<Long,OrderProduct>();
	private List<OrderProduct> productList = new ArrayList<>();
	
	private PromotionService promotionService;
	private ProductService productService;

	public SellMachineService(PromotionService promotionService, ProductService productRepository) {
		this.promotionService = promotionService;
		this.productService = productRepository;
		initData();
	}

	private void initData() {
	}

//	/**
//	 * checkout: create a current order that can retrn to ront to display the info
//	 * haven't saved the order in the database
//	 * @param productList
//	 * @return Order
//	 * @throws NotEnoughProductException
//	 */
//	public Order checkOut() throws NotEnoughProductException {
//		double totalPrice =0.00 ;
//		double totalSavedPrice=0.00;
//		List<Promotion> promotionList = new ArrayList<>();
//
//
//		for(int i=0;i<productList.size();i++) {
//			OrderProduct orderProduct = productList.get(i);
//			//validate product
//			validate(orderProduct);
//
//			//process promotions and totalPrice
//			double price = orderProduct.getProduct().getPrice() * orderProduct.getQuantity() ;
//			totalPrice+=price;
//			//check if any promotion can be used
//			List<Promotion> appliedPromotions =promotionService.findPromotion(orderProduct);
//
//			for(int j=0;j<appliedPromotions.size();j++) {
//				totalSavedPrice+=appliedPromotions.get(j).getSave();
//			}
//		}
//		totalPrice-=totalSavedPrice;
//		if(productList.size()>0) {
//			this.currentOrder = new Order(productList,totalSavedPrice,totalPrice);
//		}
//
//		return currentOrder;
//	}
//
//	/**
//	 * validate the orderProduct
//	 * @param orderProdct
//	 * @return Product
//	 * @throws NotEnoughProductException: if the product doesn't have enough stock
//	 */
//	public Product validate(OrderProduct orderProdct) throws NotEnoughProductException{
//		Product product = orderProdct.getProduct();
//		if(product.getStore() < orderProdct.getQuantity()) {
//			throw new NotEnoughProductException("There's no enough product stock!");
//		}
//		return product;
//	}
//
//	/**
//	 * place order: complete order
//	 * save the order record to database and cut the product stock
//	 * @return Order: the saved order
//	 * @throws NotEnoughProductException
//	 */
//	public Order placeOrder() throws NotEnoughProductException {
//		List<OrderProduct> list = currentOrder.getOrderProducts();
//		for(int i=0; i<list.size();i++) {
//			OrderProduct orderProduct = list.get(i);
//			Product product = validate(orderProduct);
//			product.setStore(product.getStore() - orderProduct.getQuantity());
//			productService.update(product);
//		}
//
//		return orderRepository.save(currentOrder);
//	}
//
//	public List<OrderProduct> getProductListInBasket() {
//		return this.productList;
//	}
//
//	public void cancel() {
//		currentOrder= new Order();
//	}
//
//	public List<OrderProduct> addProduct(Long id) throws NotEnoughProductException {
//		OrderProduct orderProduct = orderProductMap.get(id);
//		if(orderProductMap.containsKey(id)) {
//			orderProduct = orderProductMap.get(id);
//			orderProduct.setQuantity(orderProduct.getQuantity() + 1);
//			try {
//				validate(orderProduct);
//			} catch (NotEnoughProductException e) {
//				orderProduct.setQuantity(orderProduct.getQuantity()-1);
//				throw e;
//			}
//		}else {
//			Optional<Product> product = productService.get(id);
//			if(!product.isPresent()){
//				throw new EntityNotFoundException("Entity not found for Id: " + id);
//		    }
//			orderProduct = new OrderProduct(product.get(),new Integer(1));
//			orderProductMap.put(id, orderProduct);
//			this.productList.add(orderProduct);
//		}
//
//		return this.productList;
//	}

	public void cleanBasket() {
		orderProductMap.clear();
		this.productList.clear();
	}
}
