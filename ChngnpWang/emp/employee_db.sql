/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : employee_db

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 18/04/2022 15:51:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salary` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '李四', '女', 600.00);
INSERT INTO `employee` VALUES (2, '张三', '男', 500.00);

SET FOREIGN_KEY_CHECKS = 1;
