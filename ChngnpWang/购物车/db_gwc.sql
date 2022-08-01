/*
 Navicat Premium Data Transfer

 Source Server         : ZY
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : db_gwc

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 27/04/2022 20:07:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `author` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `price` int(11) NOT NULL COMMENT '价格',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES (1, '小林家的龙女仆', 'A', 26, '萌系', '京阿尼回归，小林变男人');
INSERT INTO `tb_goods` VALUES (2, '纯白之音', '罗川真理茂', 27, '竞技', '感受传统乐器的魅力');
INSERT INTO `tb_goods` VALUES (3, '无职转生', '理不尽', 10, '轻小说', '一开口就知道是LSP了');
INSERT INTO `tb_goods` VALUES (4, '奥创纪元', 'Marvel', 34, '科幻', '一个毁灭性造物的诞生');
INSERT INTO `tb_goods` VALUES (5, '黑白无双', '于彦舒', 32, '冒险，格斗', '经典国漫动画正在热播，真人剧企划中');
INSERT INTO `tb_goods` VALUES (6, '妖精的尾巴', '真洁岛', 23, '冒险，魔法', '属于妖精的尾巴的门扉，为你打开');
INSERT INTO `tb_goods` VALUES (7, '名侦探柯南', '青山冈昌', 35, '侦探类', '死神小学生');
INSERT INTO `tb_goods` VALUES (9, '变态青春', '山本中学', 32, '欢乐', '青春');
INSERT INTO `tb_goods` VALUES (10, '南家三姐妹', '樱场小春', 29, '欢乐', '南家三姊妹的日常生活为中心的搞笑漫画');
INSERT INTO `tb_goods` VALUES (11, '二月的胜者', '高濑志帆', 31, '热血，职场', '二月的胜者漫画 ，知名小升初补习塾讲师·黑木藏人');
INSERT INTO `tb_goods` VALUES (12, '能干的猫今天也忧郁', '山田', 28, '生活，治愈', '只要有你和啤酒在，那就是幸福');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', '123456');
INSERT INTO `tb_user` VALUES (2, 'jack', '123456');

SET FOREIGN_KEY_CHECKS = 1;
