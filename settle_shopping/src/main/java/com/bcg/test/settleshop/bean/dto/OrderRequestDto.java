package com.bcg.test.settleshop.bean.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Order request DTO
 * Created by alexlu on 2019/6/13.
 */
@Data
public class OrderRequestDto {

    /**
     * Request order item list
     */
    @NotEmpty(message = "Request order item list should not be empty!")
    private List<OrderItemRequestDto> orderItemList;
}
