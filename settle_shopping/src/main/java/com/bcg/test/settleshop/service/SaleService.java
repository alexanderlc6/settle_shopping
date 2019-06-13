package com.bcg.test.settleshop.service;

import com.bcg.test.settleshop.bean.dto.OrderRequestDto;
import com.bcg.test.settleshop.bean.dto.OrderResponseDto;

/**
 * Created by alexlu on 2019/6/13.
 */
public interface SaleService {

    OrderResponseDto buy(OrderRequestDto orderRequstDto);
}
