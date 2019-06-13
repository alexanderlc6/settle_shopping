package com.bcg.test.settleshop.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by alexlu on 2019/6/13.
 */
@TableName("sku_info")
@Data
public class SkuInfo extends BaseEntity<SkuInfo> {
    /**
     * Sku code
     */
    @TableField("code")
    private String code;

    /**
     * Sku name
     */
    @TableField("name")
    private String name;

    /**
     * Sku price
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * Sku inventory qty
     */
    @TableField("inventory_qty")
    private Long inventoryQty;
}
