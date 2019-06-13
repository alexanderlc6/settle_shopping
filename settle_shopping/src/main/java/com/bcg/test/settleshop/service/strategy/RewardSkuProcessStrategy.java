package com.bcg.test.settleshop.service.strategy;

import com.bcg.test.settleshop.bean.bo.ProcessSkuSettlementBo;
import com.bcg.test.settleshop.bean.dto.SkuSettlementDto;
import com.bcg.test.settleshop.entity.PromotionRule;
import com.bcg.test.settleshop.entity.SkuInfo;
import com.bcg.test.settleshop.repository.SkuRepository;
import com.bcg.test.settleshop.utils.SpringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by alexlu on 2019/6/13.
 */
public class RewardSkuProcessStrategy extends PromotionProcessBaseStrategy {
    SkuRepository skuRepository = SpringUtils.getBean("skuRepository");

    @Override
    public SkuSettlementDto processSkuSettlement(ProcessSkuSettlementBo requestBo) {
        SkuSettlementDto resultSkuProcessDto = null;
        SkuInfo promotionSkuInfo = requestBo.getSkuInfo();
        PromotionRule promotionRule = requestBo.getPromotionRule();

        //Process reward sku
        if(promotionRule.getRewardSkuId() != null && promotionRule.getRewardSkuId() > 0L){
            SkuInfo rewardSkuInfo = skuRepository.selectById(promotionRule.getRewardSkuId());
            boolean hasRewardSku = rewardSkuInfo !=null
                    && (promotionRule.getPromotionThreshold() == null || promotionRule.getPromotionThreshold() == 0)
                    && promotionRule.getRewardSkuQuantity() != null && promotionRule.getRewardSkuQuantity() > 0;
            if(hasRewardSku){
                SkuSettlementDto rewardSkuSettlementDto = new SkuSettlementDto();
                rewardSkuSettlementDto.setSubTotalAmount(BigDecimal.ZERO);
                BeanUtils.copyProperties(rewardSkuInfo, rewardSkuSettlementDto);
                rewardSkuSettlementDto.setActualSkuQuantity(requestBo.getBuyQuantity() * promotionRule.getRewardSkuQuantity());

                resultSkuProcessDto = new SkuSettlementDto();
                resultSkuProcessDto.setRewardedSkuList(Arrays.asList(rewardSkuSettlementDto));

                BeanUtils.copyProperties(promotionSkuInfo, resultSkuProcessDto);
                resultSkuProcessDto.setActualSkuQuantity(requestBo.getBuyQuantity());
                resultSkuProcessDto.setSubTotalAmount(promotionSkuInfo.getPrice()
                                    .multiply(BigDecimal.valueOf(requestBo.getBuyQuantity())
                                    .setScale(2)));
            }
        }

        return resultSkuProcessDto;
    }
}
