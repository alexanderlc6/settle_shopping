package com.bcg.test.settleshop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by alexlu on 2019/6/13.
 */
@TableName("promotion_rule")
@Data
public class PromotionRule extends BaseEntity<PromotionRule>{
    /**
     * Sales promotion type
     * 1-RewardWithThreshold
     * 2-CountPriceReduction
     * 3-TotalDiscountWithThreshold
     */
    @TableField("promotion_rule_type")
    private Integer promotionRuleType;

    /**
     * Promotion sku Id
     */
    @TableField("promotion_sku_id")
    private Long promotionSkuId;

    /**
     * Reward sku Id
     */
    @TableField("reward_sku_id")
    private Long rewardSkuId;

    @TableField("reward_sku_quantity")
    private Long rewardSkuQuantity;

    /**
     * Promotion threshold condition value
     */
    @TableField("promotion_threshold")
    private Long promotionThreshold;

    /**
     * Promotion sku discount value
     * For example: 10% discount - 0.1
     */
    @TableField("promotion_sku_discount")
    private BigDecimal promotionSkuDiscount;

    /**
     * Price reduction count of sku
     */
    @TableField("price_reduction_sku_count")
    private Long priceReductionSkuCount;
}
