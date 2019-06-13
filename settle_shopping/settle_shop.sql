/*
Navicat MySQL Data Transfer

Source Server         : My
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : settle_shop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-06-14 01:53:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for promotion_rule
-- ----------------------------
DROP TABLE IF EXISTS `promotion_rule`;
CREATE TABLE `promotion_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Sales rule Id',
  `version` bigint(20) DEFAULT NULL COMMENT 'Optimistic lock flag',
  `promotion_rule_type` int(11) DEFAULT NULL COMMENT 'Sales promotion type(1-RewardWithThreshold,2-CountPriceReduction,3-TotalDiscountWithThreshold)',
  `promotion_sku_id` bigint(20) DEFAULT NULL COMMENT 'Promotion sku Id',
  `reward_sku_id` bigint(20) DEFAULT NULL COMMENT 'Reward sku Id',
  `reward_sku_quantity` bigint(20) DEFAULT NULL COMMENT 'Reward sku quantity',
  `promotion_threshold` bigint(11) DEFAULT NULL COMMENT 'Promotion threshold condition value',
  `promotion_sku_discount` decimal(18,2) DEFAULT NULL COMMENT 'Promotion sku discount value',
  `price_reduction_sku_count` bigint(20) DEFAULT NULL COMMENT 'Price reduction count of sku',
  `is_delete` bit(1) DEFAULT b'0' COMMENT 'Valid flag(0-valid,1-invalid)',
  `creator_id` bigint(20) DEFAULT NULL COMMENT 'Record creator user Id',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record create time',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT 'Record modify user Id',
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record modify time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of promotion_rule
-- ----------------------------
INSERT INTO `promotion_rule` VALUES ('1', '1', '1', '2', '4', '1', '0', null, null, '\0', null, '2019-06-14 00:57:59', null, '2019-06-14 00:57:59');
INSERT INTO `promotion_rule` VALUES ('2', '1', '2', '1', null, null, '3', null, '1', '\0', null, '2019-06-13 18:48:53', null, '2019-06-13 18:48:53');
INSERT INTO `promotion_rule` VALUES ('3', '1', '3', '3', null, null, '3', '0.10', null, '\0', null, '2019-06-13 18:48:53', null, '2019-06-13 18:48:53');

-- ----------------------------
-- Table structure for sku_info
-- ----------------------------
DROP TABLE IF EXISTS `sku_info`;
CREATE TABLE `sku_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Sku Id',
  `version` bigint(20) DEFAULT NULL COMMENT 'Optimistic lock flag',
  `code` varchar(200) DEFAULT NULL COMMENT 'Sku code',
  `name` varchar(200) DEFAULT NULL COMMENT 'Sku name',
  `price` decimal(18,2) DEFAULT NULL COMMENT 'Sku price',
  `inventory_qty` bigint(20) DEFAULT NULL COMMENT 'Inventory Qty',
  `is_delete` bit(1) DEFAULT b'0' COMMENT 'Valid flag(0-valid,1-invalid)',
  `creator_id` bigint(20) DEFAULT NULL COMMENT 'Record creator user Id',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record create time',
  `modifier_id` bigint(20) DEFAULT NULL COMMENT 'Record modify user Id',
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Record modify time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sku_info
-- ----------------------------
INSERT INTO `sku_info` VALUES ('1', '1', '120P90', 'Google Home', '49.99', '10', '\0', null, '2019-06-13 18:48:51', null, '2019-06-13 18:48:51');
INSERT INTO `sku_info` VALUES ('2', '1', '43N23P', 'MacBook Pro', '5399.99', '5', '\0', null, '2019-06-13 18:48:51', null, '2019-06-13 18:48:51');
INSERT INTO `sku_info` VALUES ('3', '1', 'A304SD', 'Alexa Speaker', '109.50', '10', '\0', null, '2019-06-13 18:48:51', null, '2019-06-13 18:48:51');
INSERT INTO `sku_info` VALUES ('4', '1', '234234', 'Raspberry Pi B', '30.00', '2', '\0', null, '2019-06-13 18:48:51', null, '2019-06-13 18:48:51');
