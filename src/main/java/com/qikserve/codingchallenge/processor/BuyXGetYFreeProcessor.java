package com.qikserve.codingchallenge.processor;

import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.entity.ProductPromotionBuyXGetYFree;

public class BuyXGetYFreeProcessor extends AbstractPromotionProcessor {

    private final ProductPromotionBuyXGetYFree promotion;

    public BuyXGetYFreeProcessor(ProductPromotion promotion) {
        this.promotion = (ProductPromotionBuyXGetYFree) promotion;
    }

    @Override
    public boolean isCriteriaMatch(OrderItem item) {
        boolean match = false;

        int remainQtyToBeProcessed = this.calculateRemainQtyToBeProcessed(item);

        if(remainQtyToBeProcessed >= this.promotion.getRequiredQty() + this.promotion.getFreeQty()){
            item.addUsedPromotion(this.promotion);
            match = true;
        }

        return match;
    }

    @Override
    public long calculateSave(OrderItem item) {
        return item.getPrice() * this.promotion.getFreeQty();
    }
}
