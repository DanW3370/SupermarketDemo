package com.qikserve.codingchallenge.processor;

import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.entity.ProductPromotionQtyPriceOverride;

public class QtyPriceOverrideProcessor extends AbstractPromotionProcessor {

    private final ProductPromotionQtyPriceOverride promotion;

    public QtyPriceOverrideProcessor(ProductPromotion promotion) {
        this.promotion = (ProductPromotionQtyPriceOverride) promotion;
    }

    @Override
    public boolean isCriteriaMatch(OrderItem item) {

        boolean match = false;

        int remainQtyToBeProcessed = this.calculateRemainQtyToBeProcessed(item);

        if(remainQtyToBeProcessed >= this.promotion.getRequiredQty()){
            item.addUsedPromotion(this.promotion);
            match = true;
        }

        return match;
    }

    @Override
    public long calculateSave(OrderItem item) {

        return item.getDetailedProductInfo().getPrice() * this.promotion.getRequiredQty() - this.promotion.getPrice();
    }
}
