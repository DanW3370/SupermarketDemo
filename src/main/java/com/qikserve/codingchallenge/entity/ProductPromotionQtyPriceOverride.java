package com.qikserve.codingchallenge.entity;

public class ProductPromotionQtyPriceOverride extends ProductPromotion {
  
  private int requiredQty;
  private int price;

  public ProductPromotionQtyPriceOverride(String id, int requiredQty, int price) {
    super(id, Type.QTY_BASED_PRICE_OVERRIDE);
    this.requiredQty = requiredQty;
    this.price = price;
  }

  public Integer getRequiredQty() {
    return requiredQty;
  }

  public Integer getPrice() {
    return price;
  }

}
