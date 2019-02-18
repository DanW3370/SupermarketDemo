package com.qikserve.codingchallenge.entity;

public class ProductPromotionBuyXGetYFree extends ProductPromotion {

  private int requiredQty;
  private int freeQty;

  public ProductPromotionBuyXGetYFree(String id, int requiredQty, int freeQty) {
    super(id, Type.BUY_X_GET_Y_FREE);
    this.requiredQty = requiredQty;
    this.freeQty = freeQty;
  }

  public int getRequiredQty() {
    return requiredQty;
  }

  public int getFreeQty() {
    return freeQty;
  }

  @Override
  public String toString() {
    return "Buy " + this.getRequiredQty() + " get " + this.freeQty + " Free";
  }
}
