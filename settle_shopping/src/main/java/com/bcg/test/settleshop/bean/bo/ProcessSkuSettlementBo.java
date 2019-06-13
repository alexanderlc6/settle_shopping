package com.bcg.test.settleshop.bean.bo;

import com.bcg.test.settleshop.bean.dto.OrderItemRequestDto;
import com.bcg.test.settleshop.entity.PromotionRule;
import com.bcg.test.settleshop.entity.SkuInfo;
import lombok.Data;

/**
 * Created by alexlu on 2019/6/14.
 */
@Data
public class ProcessSkuSettlementBo extends OrderItemRequestDto {
    /**
     * Promotion rule of current sku to be processed
     */
    private PromotionRule promotionRule;

    /**
     * Current sku basic info
     */
    private SkuInfo skuInfo;
}
