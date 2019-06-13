package com.bcg.test.settleshop.service.strategy;

import com.bcg.test.settleshop.bean.bo.ProcessSkuSettlementBo;
import com.bcg.test.settleshop.bean.dto.SkuSettlementDto;
import com.bcg.test.settleshop.entity.PromotionRule;
import com.bcg.test.settleshop.entity.SkuInfo;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * Created by alexlu on 2019/6/13.
 */
public class DiscountProcessStrategy extends PromotionProcessBaseStrategy {
    @Override
    public SkuSettlementDto processSkuSettlement(ProcessSkuSettlementBo requestBo) {
        SkuSettlementDto resultSkuProcessDto = null;
        SkuInfo promotionSkuInfo = requestBo.getSkuInfo();
        PromotionRule promotionRule = requestBo.getPromotionRule();

        //If threshold condition fulfilled, allow discount for sku subtotal calculation
        if(requestBo.getBuyQuantity() >= promotionRule.getPromotionThreshold()
                && promotionRule.getPromotionSkuDiscount() != null
                && promotionRule.getPromotionSkuDiscount().compareTo(BigDecimal.ZERO) > 0){
            resultSkuProcessDto = new SkuSettlementDto();
            BeanUtils.copyProperties(promotionSkuInfo, resultSkuProcessDto);
            resultSkuProcessDto.setActualSkuQuantity(requestBo.getBuyQuantity());
            resultSkuProcessDto.setSubTotalAmount(promotionSkuInfo.getPrice()
                                .multiply(BigDecimal.valueOf(requestBo.getBuyQuantity())
                                .multiply(BigDecimal.valueOf(1L).subtract(promotionRule.getPromotionSkuDiscount())))
                                .setScale(2));
        }

        return resultSkuProcessDto;
    }
}
