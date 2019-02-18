package com.qikserve.codingchallenge.entity;

public class ProductPromotionFlatPercent extends ProductPromotion {

  private float amount;

  public ProductPromotionFlatPercent(String id, float amount) {
    super(id, Type.FLAT_PERCENT);
    this.amount = amount;
  }

  public float getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return this.getAmount() + " percent off ";
  }
}
