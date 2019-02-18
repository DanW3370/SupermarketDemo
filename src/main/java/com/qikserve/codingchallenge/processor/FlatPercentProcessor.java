package com.qikserve.codingchallenge.processor;

import com.qikserve.codingchallenge.entity.DetailedProductInfo;
import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.entity.ProductPromotionFlatPercent;

public class FlatPercentProcessor extends AbstractPromotionProcessor {

    private final ProductPromotionFlatPercent promotion;

    public FlatPercentProcessor(ProductPromotion promotion) {
        this.promotion = (ProductPromotionFlatPercent) promotion;
    }

    @Override
    public boolean isCriteriaMatch(OrderItem item) {
        //Flat percent will apply to 1 item, so condition always match
        item.addUsedPromotion(this.promotion);
        return true;
    }

    @Override
    public long calculateSave(OrderItem item) {
        DetailedProductInfo productInfo = item.getDetailedProductInfo();

        return (long) (productInfo.getPrice() * this.promotion.getAmount());
    }
}
