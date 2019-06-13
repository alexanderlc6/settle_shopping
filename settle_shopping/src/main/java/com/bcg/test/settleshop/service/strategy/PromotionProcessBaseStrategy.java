package com.bcg.test.settleshop.service.strategy;

import com.bcg.test.settleshop.bean.bo.ProcessSkuSettlementBo;
import com.bcg.test.settleshop.bean.dto.SkuSettlementDto;
import com.bcg.test.settleshop.enums.BusinessErrorCodeEnum;
import com.bcg.test.settleshop.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Promotion process base strategy actions definition for multiple promotion rules
 * Created by alexlu on 2019/6/13.
 */
@Component
@Slf4j
public abstract class PromotionProcessBaseStrategy {

    /**
     * Process Sku settlement
     * @param requestBo
     * @return
     */
    public abstract SkuSettlementDto processSkuSettlement(ProcessSkuSettlementBo requestBo);

    public SkuSettlementDto executePromotionProcess(ProcessSkuSettlementBo requestBo){
        //Do pre-check before process
        if(requestBo.getSkuInfo() == null || requestBo.getPromotionRule() == null){
            return null;
        }

        if(requestBo.getSkuInfo().getInventoryQty() == null
                || requestBo.getSkuInfo().getInventoryQty() < requestBo.getBuyQuantity()){
            throw new BusinessException(BusinessErrorCodeEnum.SKU_INVENTORY_NOT_ENOUGH.getCode(),
                    BusinessErrorCodeEnum.SKU_INVENTORY_NOT_ENOUGH.getMessage());
        }

        //Execute process
        return processSkuSettlement(requestBo);
    }
}
