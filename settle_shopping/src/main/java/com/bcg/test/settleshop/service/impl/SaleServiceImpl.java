package com.bcg.test.settleshop.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bcg.test.settleshop.bean.bo.ProcessSkuSettlementBo;
import com.bcg.test.settleshop.bean.dto.OrderRequestDto;
import com.bcg.test.settleshop.bean.dto.OrderResponseDto;
import com.bcg.test.settleshop.bean.dto.SkuSettlementDto;
import com.bcg.test.settleshop.entity.PromotionRule;
import com.bcg.test.settleshop.entity.SkuInfo;
import com.bcg.test.settleshop.enums.BusinessErrorCodeEnum;
import com.bcg.test.settleshop.enums.PromotionRuleTypeEnum;
import com.bcg.test.settleshop.enums.SystemErrorCodeEnum;
import com.bcg.test.settleshop.exception.BusinessException;
import com.bcg.test.settleshop.repository.PromotionRuleRepository;
import com.bcg.test.settleshop.repository.SkuRepository;
import com.bcg.test.settleshop.service.SaleService;
import com.bcg.test.settleshop.service.strategy.PromotionProcessBaseStrategy;
import com.bcg.test.settleshop.service.strategy.PromotionProcessContext;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Created by alexlu on 2019/6/13.
 */
@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    PromotionRuleRepository promotionRuleRepository;

    @Autowired
    SkuRepository skuRepository;

    @Autowired
    PromotionProcessContext promotionProcessContext;

    /**
     * Buy sku items with order dealing
     * @param orderRequestDto
     * @return
     */
    @Override
    public OrderResponseDto buy(OrderRequestDto orderRequestDto){
        if(CollectionUtils.isEmpty(orderRequestDto.getOrderItemList())){
            throw new BusinessException(SystemErrorCodeEnum.ILLEGAL_PARAMETER.getCode(),
                    SystemErrorCodeEnum.ILLEGAL_PARAMETER.getMessage());
        }

        ThreadFactory orderPromotionThreadFactory = new ThreadFactoryBuilder().setNameFormat("order-promotion-%d").build();
        ExecutorService orderPromotionProcessThreadPool = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), orderPromotionThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        //Execute as individual parallel tasks for promotion settlement process of each sku item
        CompletionService<List<SkuSettlementDto>> orderSettlementSvc = new ExecutorCompletionService<>(orderPromotionProcessThreadPool);
        orderRequestDto.getOrderItemList().parallelStream().forEach(oi -> {
            //Validation for sku basic info
            SkuInfo skuInfo = skuRepository.selectById(oi.getSkuId());
            if(skuInfo == null){
                throw new BusinessException(BusinessErrorCodeEnum.CANNOT_FOUND_SKU_BASE_INFO.getCode(),
                        BusinessErrorCodeEnum.CANNOT_FOUND_SKU_BASE_INFO.getMessage());
            }

            //Query related rule exists or not
            PromotionRule filter = new PromotionRule();
            filter.setPromotionSkuId(oi.getSkuId());
            filter.setDelete(false);
            Wrapper<PromotionRule> wrapper = new EntityWrapper<>(filter);
            PromotionRule promotionRule = promotionRuleRepository.selectOne(wrapper);
            //If no corresponding promotion rule, return
            if(promotionRule == null){
                return;
            }

            orderSettlementSvc.submit(new Callable() {
                @Override
                public SkuSettlementDto call() throws Exception {
                    //Start execute promotion settlement process
                    PromotionProcessBaseStrategy strategy = promotionProcessContext.getPromotionProcessBaseStrategy(
                                PromotionRuleTypeEnum.getValueByCode(promotionRule.getPromotionRuleType()));

                    ProcessSkuSettlementBo processSkuSettlementBo = new ProcessSkuSettlementBo();
                    processSkuSettlementBo.setPromotionRule(promotionRule);
                    processSkuSettlementBo.setSkuInfo(skuInfo);
                    BeanUtils.copyProperties(oi, processSkuSettlementBo);

                    return strategy.processSkuSettlement(processSkuSettlementBo);
                }
            });
        });

        //Get the summary result of each task
        List<SkuSettlementDto> processResultSkuList = new ArrayList();
        orderRequestDto.getOrderItemList().parallelStream().forEach(oi -> {
            try {
                SkuSettlementDto processedSkuInfo = (SkuSettlementDto)orderSettlementSvc.take().get();
                if(processedSkuInfo != null) {
                    processResultSkuList.add(processedSkuInfo);
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new BusinessException(BusinessErrorCodeEnum.PROCESS_SKU_PROMOTION_SETTLEMENT_FAILED.getCode(),
                        BusinessErrorCodeEnum.PROCESS_SKU_PROMOTION_SETTLEMENT_FAILED.getMessage() + e.getStackTrace());
            }
        });

        orderPromotionProcessThreadPool.shutdown();

        OrderResponseDto orderResponseDto = null;
        if(!CollectionUtils.isEmpty(processResultSkuList)){
            orderResponseDto = new OrderResponseDto();
            orderResponseDto.setSkuSettlementResultList(processResultSkuList);

            //Calculate total order actual paid amount
            List<BigDecimal> allSkuSubtotalAmount = processResultSkuList.stream()
                                                    .map(SkuSettlementDto::getSubTotalAmount)
                                                    .collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(allSkuSubtotalAmount)){
                orderResponseDto.setActualTotalPaidAmount(allSkuSubtotalAmount.stream()
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                                .setScale(2));
            }
        }

        return orderResponseDto;
    }
}
