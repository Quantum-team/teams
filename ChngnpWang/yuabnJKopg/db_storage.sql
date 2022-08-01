/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50525
 Source Host           : localhost:3306
 Source Schema         : db_storage

 Target Server Type    : MySQL
 Target Server Version : 50525
 File Encoding         : 65001

 Date: 27/06/2021 20:49:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_store
-- ----------------------------
DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store`  (
  `storeId` int(20) NOT NULL AUTO_INCREMENT,
  `storeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unitPrice` int(11) NOT NULL,
  `storeNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createDate` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`storeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_store
-- ----------------------------
INSERT INTO `tb_store` VALUES (1, '测试店铺', 1, '1', '2021-6-27');
INSERT INTO `tb_store` VALUES (2, '414', 14, '4114', '14');
INSERT INTO `tb_store` VALUES (3, '1525', 52, '2525', '25');

SET FOREIGN_KEY_CHECKS = 1;
