package com.bcg.test.settleshop.repository;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bcg.test.settleshop.entity.SkuInfo;
import com.bcg.test.settleshop.mapper.SkuInfoMapper;
import org.springframework.stereotype.Repository;

/**
 * Created by alexlu on 2019/6/13.
 */
@Repository
public class SkuRepository extends ServiceImpl<SkuInfoMapper, SkuInfo> {
}
