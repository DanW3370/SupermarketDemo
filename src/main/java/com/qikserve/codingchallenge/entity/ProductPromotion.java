package com.qikserve.codingchallenge.entity;

public abstract class ProductPromotion {

  public static enum Type {
    BUY_X_GET_Y_FREE, QTY_BASED_PRICE_OVERRIDE, FLAT_PERCENT
  }

  private String id;
  private Type type;

  ProductPromotion(String id, Type type) {
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public Type getType() {
    return type;
  }

}
