package com.qikserve.codingchallenge.processor;

import com.qikserve.codingchallenge.entity.OrderItem;
import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.entity.ProductPromotionBuyXGetYFree;
import com.qikserve.codingchallenge.entity.ProductPromotionQtyPriceOverride;

import java.util.List;

public abstract class AbstractPromotionProcessor {

    public long processDiscount(OrderItem item){

        if(this.isCriteriaMatch(item)){

            return calculateSave(item);
        } else
            return 0;
    }

    protected int calculateRemainQtyToBeProcessed(OrderItem item) {

        List<ProductPromotion> usedPromotions = item.getUsedPromotions();

        int remainQty = item.getQuantity();

        if(!usedPromotions.isEmpty()){

            for (ProductPromotion usedPromotion: usedPromotions){
                if(usedPromotion instanceof ProductPromotionBuyXGetYFree){
                    ProductPromotionBuyXGetYFree freePromotion = (ProductPromotionBuyXGetYFree) usedPromotion;
                    /** buyXGetYFree will consume Y freeQty and X requiredQty; **/
                    remainQty -= freePromotion.getFreeQty() + freePromotion.getRequiredQty();
                } else if(usedPromotion instanceof ProductPromotionQtyPriceOverride){

                    ProductPromotionQtyPriceOverride  qtyOverridePromotion = (ProductPromotionQtyPriceOverride) usedPromotion;
                    remainQty -= qtyOverridePromotion.getRequiredQty();
                }
            }
        }

        return remainQty;
    }

    abstract boolean isCriteriaMatch(OrderItem item);

    abstract long calculateSave(OrderItem item);
}
