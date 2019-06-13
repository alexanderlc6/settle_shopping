package com.bcg.test.settleshop.bean.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Order item info DTO
 * Created by alexlu on 2019/6/13.
 */
@Data
public class OrderItemRequestDto {
    /**
     * Sku Id
     */
    @NotNull(message = "Sku ID cannot be null!")
    private Long skuId;

    /**
     * Buy quantity
     */
    @Min(value = 1, message = "Buy quantity should be no less than 1!")
    private Long buyQuantity;
}
