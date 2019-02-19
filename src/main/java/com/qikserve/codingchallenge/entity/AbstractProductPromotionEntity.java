package com.qikserve.codingchallenge.entity;

abstract public class AbstractProductPromotionEntity {

  private String id;
  private ProductPromotion.Type type;

  AbstractProductPromotionEntity(String id, ProductPromotion.Type type) {
    this.id = id;
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductPromotion.Type getType() {
    return type;
  }

  public void setType(ProductPromotion.Type type) {
    this.type = type;
  }

}
