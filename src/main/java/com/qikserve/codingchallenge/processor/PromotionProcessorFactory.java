package com.qikserve.codingchallenge.processor;

import com.qikserve.codingchallenge.entity.ProductPromotion;
import com.qikserve.codingchallenge.exception.InvalidPromotionTypeException;

public final class PromotionProcessorFactory {

    private PromotionProcessorFactory(){
        throw new IllegalStateException("Utility class");
    }

    public static AbstractPromotionProcessor getPromotionProcess(ProductPromotion promotion) throws InvalidPromotionTypeException {
        ProductPromotion.Type type = promotion.getType();

        switch(type){
            case BUY_X_GET_Y_FREE:
                return new BuyXGetYFreeProcessor(promotion);
            case FLAT_PERCENT:
                return new FlatPercentProcessor(promotion);
            case QTY_BASED_PRICE_OVERRIDE:
                return new QtyPriceOverrideProcessor(promotion);
            default:
                throw new InvalidPromotionTypeException("Unknown promotion type detected");
        }
    }
}
