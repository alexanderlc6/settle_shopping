package com.bcg.test.settleshop.repository;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bcg.test.settleshop.entity.PromotionRule;
import com.bcg.test.settleshop.mapper.PromotionRuleMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by alexlu on 2019/6/13.
 */
@Repository
public class PromotionRuleRepository extends ServiceImpl<PromotionRuleMapper, PromotionRule> {
}
