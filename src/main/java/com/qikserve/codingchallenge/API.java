package com.qikserve.codingchallenge;

import com.qikserve.codingchallenge.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API {

  private static final List<ProductInfo> allProducts;
  private static final Map<String, DetailedProductInfo> detailedProductInfo;

  static {
    allProducts = new ArrayList<ProductInfo>();
    detailedProductInfo = new HashMap<String, DetailedProductInfo>();

    DetailedProductInfo amazingBurger = new DetailedProductInfo("PWWe3w1SDU", "Amazing Burger!", 999, 
        new ProductPromotion[] {new ProductPromotionBuyXGetYFree("ZRAwbsO2qM", 2, 1)});
    DetailedProductInfo amazingPizza = new DetailedProductInfo("Dwt5F7KAhi", "Amazing Pizza!", 1099,
        new ProductPromotion[] {new ProductPromotionQtyPriceOverride("ibt3EEYczW", 2, 1799)});
    DetailedProductInfo amazingSalad = new DetailedProductInfo("C8GDyLrHJb", "Amazing Salad!", 499,
        new ProductPromotion[] {new ProductPromotionFlatPercent("Gm1piPn7Fg", 10.0f)});
    DetailedProductInfo boringFries = new DetailedProductInfo("4MB7UfpTQs", "Boring Fries!", 199,
            new ProductPromotion[] {});

    addToProductStore(amazingBurger);
    addToProductStore(amazingPizza);
    addToProductStore(amazingSalad);
    addToProductStore(boringFries);
  }

  public static List<ProductInfo> getAllProducts() {
    return allProducts;
  }

  public static DetailedProductInfo getDetailedProductInfo(String productId) {
    return detailedProductInfo.get(productId);
  }

  private static void addToProductStore(DetailedProductInfo dpi) {
    allProducts.add(new ProductInfo(dpi));
    detailedProductInfo.put(dpi.getId(), dpi);
  }

}
