package com.qikserve.codingchallenge.service;

import com.qikserve.codingchallenge.API;
import com.qikserve.codingchallenge.entity.DetailedProductInfo;
import com.qikserve.codingchallenge.entity.Order;
import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.exception.InvalidPromotionTypeException;
import com.qikserve.codingchallenge.processor.PromotionProcessorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ShoppingCartService {

	HashMap<Long, Order> completedOrder = new HashMap<>();
	private Order currentOrder;

	/**
	 * checkout: create a current order that can retrn to ront to display the info
	 * haven't saved the order in the database
	 * @return Order
	 */
	public Order checkOut() {
		this.currentOrder.setId(this.completedOrder.size());
		this.currentOrder.setOrderDate(Instant.now());
		Order cOrder = new Order();
		BeanUtils.copyProperties(this.currentOrder, cOrder);
		this.completedOrder.put(cOrder.getId(), cOrder);

		this.currentOrder = new Order();
		return cOrder;
	}

	public void cancel() {
		currentOrder= new Order();
	}

	public Order addProduct(String id) {

		if(this.currentOrder == null){
			this.currentOrder = new Order();
		}

		addOrderItemToOrder(id);

		return this.currentOrder;
	}

	private void addOrderItemToOrder(String id) {

		List<OrderItem> orderItems = this.currentOrder.getOrderItems();
		boolean found = false;
		for(OrderItem item: orderItems){
			if(item.getDetailedProductInfo().getId().equalsIgnoreCase(id)){

				item.setQuantity(item.getQuantity() + 1);
				item.setSave(findAndApplyPromotion(item));
				found = true;
				break;
			}
		}

		if(!found){
			addNewOrderItem(id);
		}
	}

	private long findAndApplyPromotion(OrderItem item) {
		long totalSave = item.getSave();

		DetailedProductInfo detailProduct = item.getDetailedProductInfo();
		ProductPromotion[] availablePromotions = detailProduct.getPromotions();

		for(ProductPromotion availablePromotion: availablePromotions){
			try {
				totalSave += PromotionProcessorFactory.getPromotionProcess(availablePromotion).processDiscount(item);
			} catch (InvalidPromotionTypeException e) {
				//Log the exception in logger.
				//Dont process the promotion
				totalSave = 0;
			}
		}

		return totalSave;
	}

	private void addNewOrderItem(String id) {
		OrderItem newItem = new OrderItem();
		newItem.setDetailedProductInfo(API.getDetailedProductInfo(id));
		newItem.setQuantity(1);
		newItem.setSave(findAndApplyPromotion(newItem));
		this.currentOrder.addOrderItem(newItem);
	}

	public void cleanBasket() {
		if(this.currentOrder != null){
			this.currentOrder.getOrderItems().clear();
		}
	}

	public List<Order> getOrderHistory(){
		return new ArrayList<>(this.completedOrder.values());
	}
}
