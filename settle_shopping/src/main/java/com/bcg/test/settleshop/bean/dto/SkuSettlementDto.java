package com.bcg.test.settleshop.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alexlu on 2019/6/13.
 */
@Data
public class SkuSettlementDto {
    /**
     * Sku code
     */
    private String code;

    /**
     * Sku name
     */
    private String name;

    /**
     * Sku price
     */
    private BigDecimal price;

    /**
     * Rewarded Sku info list
     */
    private List<SkuSettlementDto> rewardedSkuList;

    /**
     * Actual sku quantity after settlement logic calculations
     */
    private Long actualSkuQuantity;

    /**
     * Subtotal amount of each Sku
     */
    private BigDecimal subTotalAmount;
}
