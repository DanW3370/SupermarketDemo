package com.qikserve.codingchallenge.service;

import java.util.List;

import com.qikserve.codingchallenge.API;
import com.qikserve.codingchallenge.entity.Order;
import com.qikserve.codingchallenge.entity.OrderItem;
import org.springframework.stereotype.Service;


@Service
public class ShoppingCartService {

	private List<Order> completedOrder;
	private Order currentOrder;

	/**
	 * checkout: create a current order that can retrn to ront to display the info
	 * haven't saved the order in the database
	 * @return Order
	 */
	public Order checkOut() {
//		double totalPrice =0.00 ;
//		double totalSavedPrice=0.00;
//		List<Promotion> promotionList = new ArrayList<>();
//
//
//		for(int i=0;i<productList.size();i++) {
//			OrderItem orderProduct = productList.get(i);
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

		return currentOrder;
	}

	/**
	 * place order: complete order
	 * save the order record to database and cut the product stock
	 * @return Order: the saved order
	 */
	public Order placeOrder() {
		List<OrderItem> list = currentOrder.getOrderItems();
		for(int i=0; i<list.size();i++) {
//			OrderItem orderProduct = list.get(i);
//			Product product = validate(orderProduct);
//			product.setStore(product.getStore() - orderProduct.getQuantity());
//			productService.update(product);
		}

		return currentOrder;
	}

	public void cancel() {
		currentOrder= new Order();
	}

	public Order addProduct(String id) {

		if(this.currentOrder != null){
			this.currentOrder = new Order();
		}

		addOrderItemToOrder(id);

		return this.currentOrder;
	}

	private void addOrderItemToOrder(String id) {
		List<OrderItem> orderItems = this.currentOrder.getOrderItems();
		boolean found = false;
		for(OrderItem item: orderItems){
			if(item.getProduct().getId().equalsIgnoreCase(id)){

				item.setQuantity(item.getQuantity() + 1);
				found = true;
				break;
			}
		}

		if(!found){
			OrderItem newItem = new OrderItem();
			newItem.setProduct(API.getDetailedProductInfo(id));
			newItem.setQuantity(1);
			this.currentOrder.addOrderItem(newItem);
		}
	}

	public Order getCurrentOrder(){
		return this.currentOrder;
	}

	public void cleanBasket() {
		if(this.currentOrder != null){
			this.currentOrder.getOrderItems().clear();
		}
	}
}
