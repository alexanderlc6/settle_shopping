package com.bcg.test.settleshop.service.strategy;

import com.bcg.test.settleshop.enums.PromotionRuleTypeEnum;
import com.bcg.test.settleshop.exception.BusinessException;
import org.springframework.stereotype.Service;

/**
 * Created by alexlu on 2019/6/13.
 */
@Service
public class PromotionProcessContext {
    public PromotionProcessContext() {
    }

    public PromotionProcessBaseStrategy promotionProcessStrategy = null;

    public PromotionProcessBaseStrategy getPromotionProcessBaseStrategy(PromotionRuleTypeEnum promotionRuleTypeEnum)
            throws BusinessException {
        switch (promotionRuleTypeEnum){
            case REWARD_WITH_THRESHOLD:
                promotionProcessStrategy = new RewardSkuProcessStrategy();
                break;
            case COUNT_PRICE_REDUCTION:
                promotionProcessStrategy = new CountReductionProcessStrategy();
                break;
            case TOTAL_DISCOUNT_WITH_THRESHOLD:
                promotionProcessStrategy = new DiscountProcessStrategy();
                break;
        }

        return promotionProcessStrategy;
    }
}
