/*
Navicat MySQL Data Transfer

Source Server         : dd
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : food

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2022-05-15 00:46:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `Food_id` int(11) NOT NULL AUTO_INCREMENT,
  `Food_name` varchar(20) DEFAULT NULL,
  `Money` varchar(20) DEFAULT NULL,
  `Count` int(11) DEFAULT NULL,
  PRIMARY KEY (`Food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('1', '米线', '12', '10');
INSERT INTO `foodtype` VALUES ('2', '炒饭', '15', '15');
INSERT INTO `foodtype` VALUES ('3', '麻辣烫', '25', '5');
INSERT INTO `foodtype` VALUES ('8', '炒米线', '12', '10');

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `book_name` varchar(20) NOT NULL COMMENT '书籍名称',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `pages` int(11) NOT NULL,
  `flag` varchar(10) DEFAULT NULL COMMENT '1表示为借阅\r\n2表示借阅',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('100', '大石桥', '屈原', '200', '1');
INSERT INTO `tb_book` VALUES ('1001', '离骚', '屈原', '186', '1');
INSERT INTO `tb_book` VALUES ('1002', '牡丹亭', '汤显祖', '186', '2');
INSERT INTO `tb_book` VALUES ('1003', '小红帽', '大灰狼', '200', '2');

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '学生姓名',
  `phone` varchar(255) NOT NULL COMMENT '电话号码',
  `adress` varchar(20) NOT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('12', '张达满', '18313933871', '禄劝彝族苗族自治县');
INSERT INTO `tb_student` VALUES ('25', '胡图图', '123', '翻斗花园210室');
INSERT INTO `tb_student` VALUES ('26', '张晓丽', '123456', '官渡区');
INSERT INTO `tb_student` VALUES ('27', '骄傲壁画', '456456', '西山区');
