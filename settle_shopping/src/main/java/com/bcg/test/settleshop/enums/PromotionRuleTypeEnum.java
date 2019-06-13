package com.bcg.test.settleshop.enums;

/**
 * Promotion rule type enum
 * Created by alexlu on 2019/5/30.
 */
public enum PromotionRuleTypeEnum {
    REWARD_WITH_THRESHOLD(1, "RewardWithThreshold"),
    COUNT_PRICE_REDUCTION(2, "CountPriceReduction"),
    TOTAL_DISCOUNT_WITH_THRESHOLD(3, "TotalDiscountWithThreshold");

    /**
     * 状态码
     */
    private Integer promotionRuleType;

    /**
     * 状态信息
     */
    private String promotionRuleTypeName;

    public Integer getPromotionRuleType() {
        return promotionRuleType;
    }


    public String getPromotionRuleTypeName() {
        return promotionRuleTypeName;
    }


    PromotionRuleTypeEnum(Integer promotionRuleType, String promotionRuleTypeName) {
        this.promotionRuleType = promotionRuleType;
        this.promotionRuleTypeName = promotionRuleTypeName != null ? promotionRuleTypeName : "";
    }

    /**
     * Get enum object by key
     * @param promotionRuleType
     * @return
     */
    public static PromotionRuleTypeEnum getValueByCode(Integer promotionRuleType){
        for(PromotionRuleTypeEnum businessFlowProcessTypeEnum : PromotionRuleTypeEnum.values()){
            if(businessFlowProcessTypeEnum.getPromotionRuleType().equals(promotionRuleType)){
                return businessFlowProcessTypeEnum;
            }
        }

        return null;
    }
}
