package com.bcg.test.settleshop.bean.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alexlu on 2019/6/13.
 */
@Data
public class OrderResponseDto {
    /**
     * Actual total paid amount
     */
    private BigDecimal actualTotalPaidAmount;

    /**
     * Sku settlement result list
     */
    private List<SkuSettlementDto> skuSettlementResultList;
}
