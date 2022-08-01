/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 127.0.0.1:3306
 Source Schema         : source_db

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 19/03/2022 15:48:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_source
-- ----------------------------
DROP TABLE IF EXISTS `tb_source`;
CREATE TABLE `tb_source` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `buyDate` datetime NOT NULL COMMENT '采购时间',
  `price` int NOT NULL COMMENT '价格',
  `imageUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图片地址',
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_source
-- ----------------------------
BEGIN;
INSERT INTO `tb_source` VALUES (6, '中性笔', '办公用品', '2021-02-04 00:00:00', 100, 't.png', '0');
INSERT INTO `tb_source` VALUES (7, 'A4纸', '办公用品', '2021-02-05 00:00:00', 300, 't.png', '0');
INSERT INTO `tb_source` VALUES (8, '鼠标', '办公用品', '2021-02-07 00:00:00', 500, 't.png', '0');
INSERT INTO `tb_source` VALUES (9, '打印机', '办公用品', '2021-02-09 00:00:00', 1500, 't.png', '0');
INSERT INTO `tb_source` VALUES (11, '12', 'test', '2022-03-02 00:00:00', 20, 't.png', '0');
INSERT INTO `tb_source` VALUES (12, '11', '21', '2022-03-05 00:00:00', 20, 't.png', '0');
INSERT INTO `tb_source` VALUES (13, '12', '21', '2022-03-07 00:00:00', 20, 't.png', '0');
INSERT INTO `tb_source` VALUES (14, '123', '12', '2011-01-23 00:00:00', 12, 't.png', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
