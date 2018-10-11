/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.88
Source Server Version : 50718
Source Host           : 192.168.0.88:3306
Source Database       : logistics

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-12-26 21:17:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_anchored_company
-- ----------------------------
DROP TABLE IF EXISTS `tb_anchored_company`;
CREATE TABLE `tb_anchored_company` (
  `id` int(11) NOT NULL COMMENT '挂靠公司id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '挂靠公司名称',
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `status` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态 0 不可用 1 可用',
  `create_date` datetime DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `linkman` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='挂靠公司信息表';

-- ----------------------------
-- Records of tb_anchored_company
-- ----------------------------
INSERT INTO `tb_anchored_company` VALUES ('1', '深合软件有限公司', '18715083549', null, null, null, null);
INSERT INTO `tb_anchored_company` VALUES ('7', '深合软件有限公司（合肥）分公司', '18715083549', null, null, null, null);
INSERT INTO `tb_anchored_company` VALUES ('8', '深合软件有限公司（深圳）分公司', '18715083549', null, null, null, null);
INSERT INTO `tb_anchored_company` VALUES ('9', '深合软件有限公司（广州）分公司', '18715083549', null, null, null, null);
INSERT INTO `tb_anchored_company` VALUES ('10', '深合软件有限公司（北京）分公司', '18715083549', null, null, null, null);
INSERT INTO `tb_anchored_company` VALUES ('11', '深合软件有限公司（上海）分公司', '18715083549', null, null, null, null);

-- ----------------------------
-- Table structure for tb_anchore_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_anchore_record`;
CREATE TABLE `tb_anchore_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `anchored_id` int(11) DEFAULT NULL COMMENT '公司或者车队id',
  `anchored_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '公司或者车队',
  `anchored_phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '挂靠公司或者车队联系方式',
  `anchored_date` datetime DEFAULT NULL COMMENT '操作时间  -挂靠或取消',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态\n            0:已取消\n            1:已挂靠\n            2:被拒\n            3:申请挂靠中',
  `anchored_reason` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '挂靠原因',
  `type` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '1.个人挂车队  2:个人挂公司  3，车队挂公司',
  `operator` int(11) DEFAULT NULL COMMENT '当前记录操作人',
  `delete_flag` tinyint(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=latin1 COMMENT='挂靠公司记录表';

-- ----------------------------
-- Records of tb_anchore_record
-- ----------------------------
INSERT INTO `tb_anchore_record` VALUES ('1', '33315', 'haha', null, '2017-11-08 10:56:20', '5078', 'dx', '1', 'dd', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('2', '1', '深合软件公司', null, '2017-11-23 13:56:56', '82308', '阿狗', '1', 'dd', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('3', '1', '深合软件公司', null, '2017-11-08 14:14:39', '82308', '阿狗', '1', 'dd', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('4', '1', '深合软件公司', null, '2017-11-01 14:15:16', '82308', '阿狗', '1', 'ss', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('5', '1', '深合软件公司', null, '2017-11-14 14:15:42', '82308', '阿狗', '1', 'ff', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('6', '1', '33315', null, '2017-11-14 14:15:42', '82308', '阿狗', '1', 'sdsd', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('7', '8', '深合软件有限公司（合肥）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('8', '2', '深合软件有限公司（深圳）分公司', null, '2017-11-09 20:15:09', '5079', '112', '1', 'dfd', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('9', '3', '深合软件有限公司（广州）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('10', '4', '深合软件有限公司（上海）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('11', '5', '深合软件有限公司（北京）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('12', '6', '深合软件有限公司（哈尔滨）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('13', '7', '深合软件有限公司（三亚）分公司', null, '2017-11-08 19:11:20', '5079', 'ss', '1', '22', null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('14', '33315', '飞凌车队', '15556478965', '2017-11-08 16:00:03', '5077', '李四', '1', null, null, null, null);
INSERT INTO `tb_anchore_record` VALUES ('15', '1', '深合软件有限公司', '18715083549', '2017-11-29 16:42:01', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('16', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 16:42:45', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('17', '1', '深合软件有限公司', '18715083549', '2017-11-29 16:45:00', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('20', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 17:18:52', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('21', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 17:23:48', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('22', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 17:25:30', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('23', '8', '深合软件有限公司（深圳）分公司', '18715083549', '2017-11-29 17:25:56', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('24', '11', '深合软件有限公司（上海）分公司', '18715083549', '2017-11-29 17:26:46', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('25', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 17:38:27', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('26', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-29 17:38:39', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('27', '8', '深合软件有限公司（深圳）分公司', '18715083549', '2017-11-29 17:43:29', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('28', '9', '深合软件有限公司（广州）分公司', '18715083549', '2017-11-29 17:43:33', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('29', '33315', '老死机车队', '18715083549', '2017-11-30 10:23:51', '5079', '阿狗', '1', null, '1', null, null);
INSERT INTO `tb_anchore_record` VALUES ('30', '9', '深合软件有限公司（广州）分公司', '18715083549', '2017-11-30 11:18:56', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('31', '10', '深合软件有限公司（北京）分公司', '18715083549', '2017-11-30 11:19:04', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('32', '11', '深合软件有限公司（上海）分公司', '18715083549', '2017-11-30 11:19:22', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('33', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-30 11:19:31', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('34', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-11-30 11:19:40', '82308', 'admin', '3', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('35', '10', '深合软件有限公司（北京）分公司', '18715083549', '2017-11-30 11:19:45', '82308', 'admin', '0', null, '3', null, null);
INSERT INTO `tb_anchore_record` VALUES ('36', '33315', '老爷们车队', '18715083549', '2017-11-30 11:30:39', '5079', '阿狗', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('37', '1', '深合软件有限公司', '18715083549', '2017-11-30 11:37:10', '5079', '阿狗', '0', null, '2', null, null);
INSERT INTO `tb_anchore_record` VALUES ('39', '33315', '33315', '18715083549', '2017-11-30 11:30:39', '975255', '我不是驾驶员', '1', null, '1', null, null);
INSERT INTO `tb_anchore_record` VALUES ('53', '96510', '鬼魅车队', '18715523215', '2017-11-30 19:45:46', '5079', '阿狗', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('56', '96510', '鬼魅车队', '18715523215', '2017-11-30 20:44:45', '5079', '阿狗', '1', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('57', '96510', '鬼魅车队', '18715523215', '2017-11-30 20:46:09', '5079', '阿狗', '0', '车主端取消掉挂靠自己的司机', '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('58', '1', '深合软件有限公司', '18715083549', '2017-12-04 10:40:45', '709328', 'demo', '3', null, '3', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('61', '96510', '鬼魅车队', '18715523215', '2017-12-04 11:07:41', '5079', '阿狗', '0', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('62', '1', '深合软件有限公司', '18715083549', '2017-12-04 11:39:58', '5079', '阿狗', '3', null, '2', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('63', '7', '深合软件有限公司（合肥）分公司', '18715083549', '2017-12-04 11:53:20', '82308', 'admin', '3', null, '3', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('64', '33315', '神龙车队', '18715083549', '2017-12-04 11:54:16', '5079', '阿狗', '3', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('65', '33315', '神龙车队', '18715083549', '2017-12-04 14:06:21', '5079', '阿狗', '3', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('85', '31001', '无敌风火轮车队', '18860486308', '2017-12-04 15:50:35', '947995', '林二两', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('86', '31001', '无敌风火轮车队', '18860486308', '2017-12-04 15:51:15', '947995', '林二两', '1', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('87', '31001', '无敌风火轮车队', '18860486308', '2017-12-04 15:51:52', '947995', '林二两', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('89', '31001', '无敌风火轮车队', '18860486308', '2017-12-04 15:52:50', '947995', '林二两', '3', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('90', '33315', '由游车队', '18715083549', '2017-12-04 16:25:53', '947995', '林二两', '3', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('91', '6021', '18715083549', '18715083549', '2017-12-04 16:33:12', '947995', '林二两', '3', null, '1', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('92', '1', '深合软件有限公司', '18715083549', '2017-12-06 17:33:25', '82308', 'admin', '3', 'ok', '3', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('93', '1', '深合软件有限公司', '18715083549', '2017-12-05 17:34:11', '947995', '林二两', '3', 'okk', '2', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('98', '1', '深合软件有限公司', '18715083549', '2017-12-07 17:30:30', '82308', 'admin', '1', null, '3', '1', '1');
INSERT INTO `tb_anchore_record` VALUES ('100', '1', '深合软件有限公司', '18715083549', '2017-12-08 09:33:28', '82308', 'admin', '0', null, '3', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('101', '1', '深合软件有限公司', '18715083549', '2017-12-08 09:59:01', '82308', 'admin', '0', null, '3', null, '0');
INSERT INTO `tb_anchore_record` VALUES ('102', '99862', '车队A-test', '18860486308', '2017-12-14 10:51:29', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('103', '99862', '车队A-test', '18860486308', '2017-12-14 10:51:50', '997966', 'gerena', '1', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('104', '99862', '车队A-test', '18860486308', '2017-12-14 11:14:41', '997966', 'gerena', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('105', '99862', '车队A-test', '18860486308', '2017-12-14 11:40:25', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('106', '99862', '车队A-test', '18860486308', '2017-12-14 11:40:37', '997966', 'gerena', '1', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('107', '99862', '车队A-test', '18860486308', '2017-12-14 11:41:29', '997966', 'gerena', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('108', '99862', '车队A-test', '18860486308', '2017-12-14 11:43:13', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('109', '99862', '车队A-test', '18860486308', '2017-12-14 11:43:31', '997966', 'gerena', '1', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('110', '99862', '车队A-test', '18860486308', '2017-12-14 11:43:43', '997966', 'gerena', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('111', '99862', '车队A-test', '18860486308', '2017-12-14 11:47:43', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('112', '99862', '车队A-test', '18860486308', '2017-12-14 11:47:58', '997966', 'gerena', '1', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('113', '99862', '车队A-test', '18860486308', '2017-12-14 11:52:49', '997966', 'gerena', '0', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('114', '99862', '车队A-test', '18860486308', '2017-12-14 11:57:27', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('115', '99862', '车队A-test', '18860486308', '2017-12-14 11:57:38', '997966', 'gerena', '1', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('116', '99862', '车队A-test', '18860486308', '2017-12-14 11:57:44', '997966', 'gerena', '0', '车主 取消掉挂靠自己的司机', '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('117', '99862', '车队A-test', '18860486308', '2017-12-14 13:46:05', '997966', 'gerena', '3', null, '1', null, '1');
INSERT INTO `tb_anchore_record` VALUES ('118', '99862', '车队A-test', '18860486308', '2017-12-14 13:46:49', '997966', 'gerena', '1', null, '1', null, '0');

-- ----------------------------
-- Table structure for tb_branch_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_branch_group`;
CREATE TABLE `tb_branch_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分支机构id',
  `code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '机构名称',
  `short_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '简称',
  `ascription_id` int(11) DEFAULT NULL COMMENT '网点归属id',
  `ascription_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '网点归属名',
  `level` tinyint(4) DEFAULT NULL COMMENT '级别',
  `responsibler` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '负责人',
  `responsiblerId` int(11) DEFAULT NULL COMMENT '负责人id',
  `area_id` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '区域id',
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `relation_train_location_id` int(11) DEFAULT NULL COMMENT '关联火车站',
  `relation_train_location_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '关联火车站名称',
  `relation_begin_location_id` int(11) DEFAULT NULL COMMENT '关联发运地id',
  `relation_begin_location` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '关联发运地',
  `comment` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态:0  已禁用                1 正在使用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COMMENT='分支机构';

-- ----------------------------
-- Records of tb_branch_group
-- ----------------------------
INSERT INTO `tb_branch_group` VALUES ('1', '101', '合肥分支中心', 'C', '1', '大高', '1', '', '19', '北京,崇文区,null', '合肥市高新区', '2', '合肥站', null, '[{\"province\":\"北京\",\"city\":\"东城区\"},{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', 'Cc', '1', '2017-12-25 10:25:34');
INSERT INTO `tb_branch_group` VALUES ('2', '102', '新疆分支中心', '新疆', '1', '新疆', '1', '', null, '安徽,合肥,蜀山区', '乌鲁木齐大中路', '2', '库里火车站', null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '1', null);
INSERT INTO `tb_branch_group` VALUES ('3', '103', '大西洋', 'aaaa', '2', null, '2', null, null, '安徽,合肥,蜀山区', null, null, null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '1', null);
INSERT INTO `tb_branch_group` VALUES ('4', '104', '一级站点', '站点', null, null, null, null, null, '安徽,合肥,蜀山区', null, null, null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '0', '2017-12-21 16:59:44');
INSERT INTO `tb_branch_group` VALUES ('5', '104', '二级站点', '站点', null, null, null, null, null, '安徽,合肥,蜀山区', null, null, null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '0', '2017-12-21 17:05:07');
INSERT INTO `tb_branch_group` VALUES ('12', '12', '12', '10', '1', null, '1', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '1', '2017-12-24 14:35:49');
INSERT INTO `tb_branch_group` VALUES ('13', '123', '石吕飞', '123', '1', null, '1', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', null, '1', '2017-12-24 15:06:16');
INSERT INTO `tb_branch_group` VALUES ('14', '', '', '', '1', null, '1', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', '', '0', '2017-12-24 15:32:14');
INSERT INTO `tb_branch_group` VALUES ('15', '', '', '', '1', null, '1', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', '', '0', '2017-12-24 15:32:24');
INSERT INTO `tb_branch_group` VALUES ('16', '10', '101', '10', '12', null, '2', null, '19', '安徽,合肥,蜀山区', null, '2', null, null, '', '101123213', '0', '2017-12-24 17:16:26');
INSERT INTO `tb_branch_group` VALUES ('17', '12', '123', '123', '1', null, '1', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', '123', '1', '2017-12-24 16:13:09');
INSERT INTO `tb_branch_group` VALUES ('18', '10', '10', '11', '12', null, '2', null, null, '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', '1111', '1', '2017-12-24 16:15:43');
INSERT INTO `tb_branch_group` VALUES ('19', '12', '23', '123', '2', null, '2', null, '19', '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"},{\"province\":\"安徽\",\"city\":\"安庆\",\"district\":\"太湖县\"}]', '123213', '0', '2017-12-24 17:21:58');
INSERT INTO `tb_branch_group` VALUES ('20', '104', '深和分支中心', 'B', '1', null, '2', null, '19', '安徽,合肥,蜀山区', null, '2', null, null, '[{\"province\":\"安徽\",\"city\":\"合肥\",\"district\":\"蜀山区\"}]', 'cc', '1', '2017-12-25 10:47:36');

-- ----------------------------
-- Table structure for tb_branch_group_train_station
-- ----------------------------
DROP TABLE IF EXISTS `tb_branch_group_train_station`;
CREATE TABLE `tb_branch_group_train_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `train_station_id` int(11) DEFAULT NULL COMMENT '火车站点id',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构id',
  PRIMARY KEY (`id`),
  KEY `ref_branch_id` (`branch_group_id`),
  KEY `ref_train_station_id` (`train_station_id`),
  CONSTRAINT `ref_train_station_id` FOREIGN KEY (`train_station_id`) REFERENCES `tb_train_station` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_branch_group_train_station
-- ----------------------------
INSERT INTO `tb_branch_group_train_station` VALUES ('1', '1', '1');
INSERT INTO `tb_branch_group_train_station` VALUES ('2', '2', '2');

-- ----------------------------
-- Table structure for tb_cargo
-- ----------------------------
DROP TABLE IF EXISTS `tb_cargo`;
CREATE TABLE `tb_cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cargo_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '货物品名',
  `cargo_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1 COMMENT='货物表';

-- ----------------------------
-- Records of tb_cargo
-- ----------------------------
INSERT INTO `tb_cargo` VALUES ('1', '煤', '1001');
INSERT INTO `tb_cargo` VALUES ('2', '石子', '1003');
INSERT INTO `tb_cargo` VALUES ('3', '杯子', '102');
INSERT INTO `tb_cargo` VALUES ('9', 'haha', '123');
INSERT INTO `tb_cargo` VALUES ('10', '沙', '223');
INSERT INTO `tb_cargo` VALUES ('13', 'xx', '2444');
INSERT INTO `tb_cargo` VALUES ('21', '水泥', '10001');
INSERT INTO `tb_cargo` VALUES ('25', 'ss', 'ss');
INSERT INTO `tb_cargo` VALUES ('26', 'ss', 'ss');
INSERT INTO `tb_cargo` VALUES ('27', 'ss', '111');
INSERT INTO `tb_cargo` VALUES ('29', 'ww', 'dd');
INSERT INTO `tb_cargo` VALUES ('33', 'aaa', '123321');
INSERT INTO `tb_cargo` VALUES ('34', 'aaa', '123321');
INSERT INTO `tb_cargo` VALUES ('46', 'ccc', '22');
INSERT INTO `tb_cargo` VALUES ('47', '23', '123121');
INSERT INTO `tb_cargo` VALUES ('51', '沙子', '1004');
INSERT INTO `tb_cargo` VALUES ('52', '沙子', '1005');
INSERT INTO `tb_cargo` VALUES ('53', 'xx', '222');
INSERT INTO `tb_cargo` VALUES ('54', '煤炭2', '1006');
INSERT INTO `tb_cargo` VALUES ('55', '沙子', '1007');
INSERT INTO `tb_cargo` VALUES ('58', 'df5', '4453');
INSERT INTO `tb_cargo` VALUES ('62', 'nnn5', '444455');
INSERT INTO `tb_cargo` VALUES ('63', 'dsdff', '39999');
INSERT INTO `tb_cargo` VALUES ('64', 'mmmmn', '10013');
INSERT INTO `tb_cargo` VALUES ('65', 'rfrfru', '34343');

-- ----------------------------
-- Table structure for tb_cargo_location
-- ----------------------------
DROP TABLE IF EXISTS `tb_cargo_location`;
CREATE TABLE `tb_cargo_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '编号',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `tonnage` float DEFAULT NULL COMMENT '吨位',
  `freight_yard_id` int(11) DEFAULT NULL COMMENT '货场id',
  `delete_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_freight_yard_id` (`freight_yard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1 COMMENT='货位表';

-- ----------------------------
-- Records of tb_cargo_location
-- ----------------------------
INSERT INTO `tb_cargo_location` VALUES ('1', '1', '1', '1', '1', '0');
INSERT INTO `tb_cargo_location` VALUES ('5', 'HJD888', '京东物流方', null, '9', '0');
INSERT INTO `tb_cargo_location` VALUES ('6', 'HJD999', '天猫物流方', null, '9', '0');
INSERT INTO `tb_cargo_location` VALUES ('7', null, '3', null, '10', '0');
INSERT INTO `tb_cargo_location` VALUES ('8', 'd', '4', null, '10', '0');
INSERT INTO `tb_cargo_location` VALUES ('9', null, '5', null, '11', '0');
INSERT INTO `tb_cargo_location` VALUES ('13', null, '6', null, '13', '0');
INSERT INTO `tb_cargo_location` VALUES ('14', null, '5', null, '14', '0');
INSERT INTO `tb_cargo_location` VALUES ('15', null, '8', null, '15', '0');
INSERT INTO `tb_cargo_location` VALUES ('16', null, '89', null, '16', '0');
INSERT INTO `tb_cargo_location` VALUES ('17', null, '10', null, '18', '0');
INSERT INTO `tb_cargo_location` VALUES ('18', '213', '11', null, '18', '0');
INSERT INTO `tb_cargo_location` VALUES ('19', null, '112', null, '20', '0');
INSERT INTO `tb_cargo_location` VALUES ('20', 'kkk', '13', null, '20', '0');
INSERT INTO `tb_cargo_location` VALUES ('21', '华人', '14', null, '24', '0');
INSERT INTO `tb_cargo_location` VALUES ('22', '新人', '15', null, '25', '0');
INSERT INTO `tb_cargo_location` VALUES ('23', '新人新人', '16', null, '25', '0');
INSERT INTO `tb_cargo_location` VALUES ('29', '打死你', '17', null, '31', '0');
INSERT INTO `tb_cargo_location` VALUES ('30', 'XXX', '18', null, '32', '0');
INSERT INTO `tb_cargo_location` VALUES ('31', 'CCC', '19', null, '32', '0');
INSERT INTO `tb_cargo_location` VALUES ('38', 'AA', '20', null, '34', '0');
INSERT INTO `tb_cargo_location` VALUES ('39', 'AAA', '21', null, '34', '0');
INSERT INTO `tb_cargo_location` VALUES ('40', 'BBBB', '22', null, '34', '0');
INSERT INTO `tb_cargo_location` VALUES ('45', 'XXXX', '23', null, '33', '0');
INSERT INTO `tb_cargo_location` VALUES ('46', 'ASD', '24', null, '33', '0');
INSERT INTO `tb_cargo_location` VALUES ('49', 'd', '25', null, '35', '0');
INSERT INTO `tb_cargo_location` VALUES ('50', '', '26', null, '35', '0');
INSERT INTO `tb_cargo_location` VALUES ('52', 'ASD', '27', null, '38', '0');
INSERT INTO `tb_cargo_location` VALUES ('53', 'ASD', '28', null, '39', '0');
INSERT INTO `tb_cargo_location` VALUES ('54', 'fa', '29', null, '40', '0');
INSERT INTO `tb_cargo_location` VALUES ('55', 'ASDAS', '30', null, '41', '0');
INSERT INTO `tb_cargo_location` VALUES ('56', 'ASD', '21', null, '41', '0');
INSERT INTO `tb_cargo_location` VALUES ('57', 'ASD', 'ASD', null, '41', '0');
INSERT INTO `tb_cargo_location` VALUES ('58', 'asd', 'asd', null, '36', '0');
INSERT INTO `tb_cargo_location` VALUES ('60', 'null', '35', null, '12', '0');
INSERT INTO `tb_cargo_location` VALUES ('61', 'asd', 'asd', null, '12', '0');
INSERT INTO `tb_cargo_location` VALUES ('62', 'asd', '456', null, '12', '0');
INSERT INTO `tb_cargo_location` VALUES ('63', '1<br>', '1<br>', null, '37', null);

-- ----------------------------
-- Table structure for tb_car_team
-- ----------------------------
DROP TABLE IF EXISTS `tb_car_team`;
CREATE TABLE `tb_car_team` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车队负责人id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车队负责人名',
  `birthday` datetime DEFAULT NULL COMMENT '出生年月',
  `area_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '住址',
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '详细地址',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `idcard_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车队负责人身份证号',
  `idcard_photo_front` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证正面图片',
  `idcard_photo_back` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证反面图片',
  `car_item_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车队名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99863 DEFAULT CHARSET=latin1 COMMENT='车队负责人表';

-- ----------------------------
-- Records of tb_car_team
-- ----------------------------
INSERT INTO `tb_car_team` VALUES ('18344', '12321', null, '安徽省/合肥市/瑶海区', '蜀山区信旺华府骏苑', '1', '18715083548', '12321321', null, null, '123255552');
INSERT INTO `tb_car_team` VALUES ('22865', '石吕飞', '1899-12-04 00:20:00', null, '蜀山区信旺华府骏苑', '1', null, '340825199409201052', null, null, '2321312');
INSERT INTO `tb_car_team` VALUES ('31001', '林亮亮', '2017-12-04 00:00:00', '安徽省/合肥市/蜀山区', '肥西路哈哈', '1', '18860486308', '3425221111111166554', '395317.jpg', '462138.png', '无敌风火轮车队');
INSERT INTO `tb_car_team` VALUES ('33315', '王二麻子', '1994-08-01 00:00:00', '安徽省安庆市太湖县', '蜀山区信旺华府骏苑2#', '0', '18715083549', '654218954221255484', '861277.png', '390258.png', '悠悠车队');
INSERT INTO `tb_car_team` VALUES ('40471', '石吕飞', '1994-08-01 01:00:00', null, '蜀山区信旺华府骏苑', null, '', '', null, '1511330616193.jpg', '456546');
INSERT INTO `tb_car_team` VALUES ('43365', '阿道夫', '2017-11-27 00:00:00', '安徽省/芜湖市/弋江区', '蜀山区信旺华府骏苑', '1', '18700083549', '12321312312', null, null, '24565');
INSERT INTO `tb_car_team` VALUES ('49836', '', null, '', '', null, '', '', null, null, '4565465468');
INSERT INTO `tb_car_team` VALUES ('51251', '阿道夫', '2017-10-31 00:00:00', '安徽省/合肥市/瑶海区', '蜀山区信旺华府骏苑', '1', '18715083549', '3408251994092010552', null, null, '454');
INSERT INTO `tb_car_team` VALUES ('51359', '18715083549', '2017-11-27 00:00:00', '安徽省/合肥市/庐阳区', '蜀山区信旺华府骏苑', '1', '18715083549', '18715083549', null, null, '18715083549');
INSERT INTO `tb_car_team` VALUES ('66799', '石吕飞', '1994-09-20 00:00:00', '安徽省/安庆市/太湖县', '老城', '1', '18715083549', '340825199409201055', null, null, '飞凌车队123');
INSERT INTO `tb_car_team` VALUES ('71761', '18715083579', '2017-11-28 00:00:00', '安徽省/合肥市/瑶海区', '18715083579', '1', '18715083579', '18715083579', '913058.png', '604854.png', '18715083579');
INSERT INTO `tb_car_team` VALUES ('71824', '18715083549', '2017-11-30 00:00:00', '安徽省/合肥市/瑶海区', '18715083549', '1', '18715083549', '18715083549', '854082.png', '879395.png', '18715083549');
INSERT INTO `tb_car_team` VALUES ('74584', '', null, null, '', null, '', '', null, '/upload/1511330592757.jpg', '56456786');
INSERT INTO `tb_car_team` VALUES ('83436', '石吕飞', '1994-08-01 01:00:00', null, '蜀山区信旺华府骏苑', '1', '18715083549', '340825199409201052', null, '/upload/1511319202260.jpg', '飞凌车队');
INSERT INTO `tb_car_team` VALUES ('88563', '1232112', '2017-10-02 00:00:00', '安徽省/合肥市/瑶海区', '123', '1', '15755137148', '123', '438684.png', '606299.png', '1232112323');
INSERT INTO `tb_car_team` VALUES ('96510', 'demo', '2017-12-07 00:00:00', '安徽省/合肥市/蜀山区', 'demo', '1', '18715523215', '34082519940920151', '511939.jpg', '186197.jpg', '鬼魅车队');
INSERT INTO `tb_car_team` VALUES ('99861', '15355137895', '2017-11-28 00:00:00', '安徽省/合肥市/瑶海区', '15355137895', '1', '15355137895', '15355137895', '537192.png', '919115.png', '15355137895');
INSERT INTO `tb_car_team` VALUES ('99862', '车队-A', '2017-12-12 00:00:00', '安徽省/芜湖市/弋江区', '小吃街', '1', '18860486308', '342522166655555555', '169205.jpg', '702587.jpg', '车队A-test');

-- ----------------------------
-- Table structure for tb_container
-- ----------------------------
DROP TABLE IF EXISTS `tb_container`;
CREATE TABLE `tb_container` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `container_type_id` int(11) DEFAULT NULL COMMENT '箱型',
  `container_num` varchar(200) DEFAULT NULL COMMENT '编码',
  `east_container` varchar(200) DEFAULT NULL COMMENT '箱东',
  `container_kind` varchar(200) DEFAULT NULL COMMENT '箱类',
  `container_code` varchar(200) DEFAULT NULL COMMENT '箱码',
  `container_id` varchar(200) DEFAULT NULL COMMENT '集装箱箱号',
  `length` int(11) DEFAULT NULL COMMENT '长',
  `width` int(11) DEFAULT NULL COMMENT '宽',
  `hight` int(11) DEFAULT NULL COMMENT '高',
  `size` int(11) DEFAULT NULL COMMENT '尺寸',
  `volume` int(11) DEFAULT NULL COMMENT '容积',
  `self_weight` int(11) DEFAULT NULL COMMENT '自重',
  `weight` int(11) DEFAULT NULL COMMENT '载重',
  `train_location_id` int(11) DEFAULT NULL COMMENT '集装箱站点\n            ',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_22` (`container_type_id`),
  KEY `FK_Reference_23` (`train_location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1 COMMENT='集装箱表';

-- ----------------------------
-- Records of tb_container
-- ----------------------------
INSERT INTO `tb_container` VALUES ('23', '11', 'A', 'A', 'A', 'A', 'AAA9587445', null, null, null, null, null, null, null, null);
INSERT INTO `tb_container` VALUES ('24', '11', 'a', 'D', 'C', 'Q', 'DCQ8894655', '132', '132', '312', '123', '123', '123', '123', null);
INSERT INTO `tb_container` VALUES ('25', '12', 'C', 'C', 'C', 'C', 'CCCC55646', '123', '123', '123', '123', '123', '123', '123', null);
INSERT INTO `tb_container` VALUES ('58', '45', 'TT', 'TA', 'DA', 'FA', 'HHHH8888', '123', '123', '123', '123', '123', '123', '123', null);

-- ----------------------------
-- Table structure for tb_container_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_container_type`;
CREATE TABLE `tb_container_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `code` varchar(200) DEFAULT NULL COMMENT '编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='箱型表';

-- ----------------------------
-- Records of tb_container_type
-- ----------------------------
INSERT INTO `tb_container_type` VALUES ('11', 'A', 'A');
INSERT INTO `tb_container_type` VALUES ('12', 'B', 'B');
INSERT INTO `tb_container_type` VALUES ('13', 'C', 'C');

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户id',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `account` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录账号名',
  `passwd` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '登录密码',
  `company_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '企业名称',
  `short_name` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '企业简称',
  `branch_id` int(11) DEFAULT NULL COMMENT '分支id',
  `address_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业地址',
  `detail_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业详细地址',
  `company_contacts` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '企业联系人',
  `department` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门',
  `station_phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  `station_fax` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '传真',
  `email` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `bank_account` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '银行账户',
  `account_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '户名',
  `open_bank` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '开户行',
  `open_bank_num` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '行号',
  `duty_paragraph` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '税号',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态:0可用 1不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COMMENT='客户表';

-- ----------------------------
-- Records of tb_customer
-- ----------------------------
INSERT INTO `tb_customer` VALUES ('1', '2017-11-26 17:49:01', 'zs', '202cb962ac59075b964b07152d234b70', '深合软件', '深合', '1', '河北,石家庄,长安区', '合肥市。。。', '王3', '财务部', '1505515555', null, null, null, null, null, null, null, '0');
INSERT INTO `tb_customer` VALUES ('2', '2017-11-27 17:49:11', 'ls', '202cb962ac59075b964b07152d234b70', '秦龙矿业', '秦龙', null, '河北,石家庄,长安区', '乌鲁木齐。。。', '李4', '项目部', '1895454646', '18715284343548', '18715284343548', '青龙', '18715284343548', '中国银行', '18715284343548', '18715284343548', '0');
INSERT INTO `tb_customer` VALUES ('8', '2017-12-18 16:33:52', null, '202cb962ac59075b964b07152d234b70', '123', '12', null, '河北,石家庄,长安区', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0');
INSERT INTO `tb_customer` VALUES ('9', '2017-12-18 16:37:53', null, '202cb962ac59075b964b07152d234b70', '123', '123', null, '河北,石家庄,长安区', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '123', '0');
INSERT INTO `tb_customer` VALUES ('10', '2017-12-18 16:40:03', null, '202cb962ac59075b964b07152d234b70', '大唐国际', '大唐国际', null, '北京,东城区,null', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', '0');
INSERT INTO `tb_customer` VALUES ('11', '2017-12-18 16:40:39', null, '202cb962ac59075b964b07152d234b70', 'addUserModal', 'addUserModal', null, '北京,合肥,瑶海区', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', 'addUserModal', '0');
INSERT INTO `tb_customer` VALUES ('13', '2017-12-18 16:42:52', null, '202cb962ac59075b964b07152d234b70', 'data.status==200', 'data.status==200', null, '北京,合肥,瑶海区', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', 'data.status==200', '0');
INSERT INTO `tb_customer` VALUES ('14', '2017-12-25 14:26:16', null, null, '1', '1', null, '北京,东城区,null', '111111111111', '1', '1', 'haoma', '1', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for tb_customer_branch_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer_branch_group`;
CREATE TABLE `tb_customer_branch_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  PRIMARY KEY (`id`),
  KEY `ref_bra_group_id` (`branch_group_id`),
  KEY `ref_cus_id` (`customer_id`),
  CONSTRAINT `ref_cus_id` FOREIGN KEY (`customer_id`) REFERENCES `tb_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer_branch_group
-- ----------------------------
INSERT INTO `tb_customer_branch_group` VALUES ('10', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('11', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('12', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('13', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('14', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('15', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('34', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('35', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('36', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('37', '1', null);
INSERT INTO `tb_customer_branch_group` VALUES ('43', '1', '11');
INSERT INTO `tb_customer_branch_group` VALUES ('45', '1', '13');
INSERT INTO `tb_customer_branch_group` VALUES ('52', '1', '9');
INSERT INTO `tb_customer_branch_group` VALUES ('59', '1', '2');
INSERT INTO `tb_customer_branch_group` VALUES ('60', '2', '2');
INSERT INTO `tb_customer_branch_group` VALUES ('61', '2', '8');
INSERT INTO `tb_customer_branch_group` VALUES ('62', '1', '10');
INSERT INTO `tb_customer_branch_group` VALUES ('64', '1', '14');

-- ----------------------------
-- Table structure for tb_finance_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_finance_account`;
CREATE TABLE `tb_finance_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '账号名称',
  `account_opening_time` datetime DEFAULT NULL COMMENT '开户时间',
  `account_type` tinyint(4) DEFAULT NULL COMMENT '账户类型',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构',
  `account_balance` decimal(10,0) DEFAULT NULL COMMENT '账户余额',
  `vigilance_amount` decimal(10,0) DEFAULT NULL COMMENT '警戒金额',
  `non_use_amount` decimal(10,0) DEFAULT NULL COMMENT '停用金额',
  `account_name` varchar(200) DEFAULT NULL COMMENT '账户名',
  `account_num` varchar(200) DEFAULT NULL COMMENT '账号',
  `open_bank` varchar(200) DEFAULT NULL COMMENT '开户行',
  `bank_num` varchar(200) DEFAULT NULL COMMENT '行号',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '删除状态：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_finance_account
-- ----------------------------
INSERT INTO `tb_finance_account` VALUES ('4', 'cscs', '2017-12-15 00:00:00', '2', '2', '0', '555', '55', 'ddd', 'scs', 'ff', 'fdfd', 'fdf', '0');
INSERT INTO `tb_finance_account` VALUES ('8', 'dsd', '2017-12-05 00:00:00', '2', '2', '0', '22', '22', 'sds', 'dsd', '22', '22', '', '0');
INSERT INTO `tb_finance_account` VALUES ('11', 'ww', '2017-12-12 00:00:00', '1', '1', '0', '22', '22', 'ss', 'ss', 'ss', 'ss', 'ss', '0');
INSERT INTO `tb_finance_account` VALUES ('13', '2323', '2017-12-13 00:00:00', '3', '2', '0', '33', '22', 'f', 'ff', 'ee', 'ee', 'ee', '0');
INSERT INTO `tb_finance_account` VALUES ('14', 'ddd', '2017-12-13 00:00:00', '3', '1', '0', '4', '4', 'gg', 'ggg', 'ff', 'ff', 'ff', '0');
INSERT INTO `tb_finance_account` VALUES ('15', 'zzz', '2017-12-18 00:00:00', '2', '2', '0', '66', '66', '6', '66', '66', '66', '66', '0');
INSERT INTO `tb_finance_account` VALUES ('16', 'vvv', '2017-12-18 00:00:00', '1', '1', '0', '56', '76', 'vv', 'vv', 'cvv', 'vv', 'vv', '0');

-- ----------------------------
-- Table structure for tb_freight_yard
-- ----------------------------
DROP TABLE IF EXISTS `tb_freight_yard`;
CREATE TABLE `tb_freight_yard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '货场id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `is_isolated` tinyint(4) DEFAULT NULL COMMENT '是否独立',
  `linkman` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系人',
  `phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  `address_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `freight_yard_img` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '货场平面图',
  `train_station_id` int(11) DEFAULT NULL COMMENT '火车站点',
  `delete_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_21` (`train_station_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1 COMMENT='货场表';

-- ----------------------------
-- Records of tb_freight_yard
-- ----------------------------
INSERT INTO `tb_freight_yard` VALUES ('12', 'A', '1', 'sad', 'asd', '安徽省/芜湖市/弋江区', 'asd', 'sad', '428717.png', '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('14', 'B', '0', 'dd', 'dd', '', 'dd', 'ddd', null, '2', '0');
INSERT INTO `tb_freight_yard` VALUES ('18', 'C', '0', 'gg', 'gg', '', 'ggggg', '213213', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('19', 'D', '0', 'jj', 'jj', '', 'jj', 'jjj', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('20', 'E', '0', 'kk', 'kk', '', 'kk', 'kkk', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('21', 'F', '0', 'oo', 'oo', '', 'ooo', 'ooo', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('22', 'G', '0', 'oo', 'ooo', '', 'ooo', '阿发杀神风萨法', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('23', 'H', '0', '好人', '好人', '北京市/北京市/东城区', '好人', '好人', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('24', 'I', '1', '华人', '华人', '', '华人', '华人', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('25', 'J', '0', '新人', '新人', '', '新人', '新人新人', null, '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('26', 'K', '0', '偶记', '偶记', '', '偶记', '偶记', null, '2', '0');
INSERT INTO `tb_freight_yard` VALUES ('28', 'L', '1', 'VV', 'VV', '安徽省/芜湖市/弋江区', 'VVV', 'VVVV', '632722.jpg', '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('30', 'M', '0', 'YJ', 'YJ', '重庆市/重庆市/万州区', 'YJ', 'YJ', '226297.jpg', '1', '0');
INSERT INTO `tb_freight_yard` VALUES ('32', 'N', '1', 'XXX', 'XXX', '广西壮族自治区/贵港市/平南县', 'XXX', 'ZXCZXVZXC', '894331.jpg', '2', '0');
INSERT INTO `tb_freight_yard` VALUES ('33', 'O', '1', '456', '456', '广东省/江门市/恩平市', '456', '456', '428236.jpg', '5', '0');
INSERT INTO `tb_freight_yard` VALUES ('36', 'P', '1', 'ccv', 'cv', '贵州省/铜仁市/思南县', '北京市/北京市/石景山区,cvasd', 'cvc', '697007.jpg', '5', '0');
INSERT INTO `tb_freight_yard` VALUES ('37', 'Q', '0', '1', '1', '安徽省/芜湖市/弋江区', '1', '1', '331844.gif', '1', null);

-- ----------------------------
-- Table structure for tb_history_location
-- ----------------------------
DROP TABLE IF EXISTS `tb_history_location`;
CREATE TABLE `tb_history_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '运单id',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `location` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '位置',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型\n            1:短驳\n            2:火运',
  `latitude` decimal(10,0) DEFAULT NULL COMMENT '经度',
  `longitude` decimal(10,0) DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_27` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='(历史位置信息表)';

-- ----------------------------
-- Records of tb_history_location
-- ----------------------------
INSERT INTO `tb_history_location` VALUES ('1', '2', '2017-11-23 19:11:07', '合肥市高新区', '2', '179', '175');
INSERT INTO `tb_history_location` VALUES ('2', '2', '2017-11-27 10:37:00', '合肥市庐阳区', '2', '178', '165');
INSERT INTO `tb_history_location` VALUES ('3', '2', '2017-11-28 14:13:42', '南京市雨花台区', '2', '158', '154');
INSERT INTO `tb_history_location` VALUES ('4', '2', '2017-12-23 19:46:26', '黄山路与肥西路交口', '2', null, null);
INSERT INTO `tb_history_location` VALUES ('5', '2', '2017-12-24 14:55:52', '兴科大厦', '2', null, null);
INSERT INTO `tb_history_location` VALUES ('8', '6', '2017-12-25 19:16:20', '黄山路', '2', null, null);
INSERT INTO `tb_history_location` VALUES ('9', '6', '2017-12-25 19:17:03', '肥西路', '2', null, null);

-- ----------------------------
-- Table structure for tb_login_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_login_user`;
CREATE TABLE `tb_login_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `passwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `type` tinyint(4) DEFAULT NULL COMMENT '用户类型\n            0:个人\n            1:车队负责人',
  `state` tinyint(4) DEFAULT '0' COMMENT '设置用户状态 0 不可用 1 可用',
  `create_date` datetime DEFAULT NULL COMMENT '用户创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `information_id` int(11) DEFAULT NULL COMMENT '用户详情id',
  `car_team_id` int(11) DEFAULT NULL COMMENT '车队外键',
  `anchored_date` datetime DEFAULT NULL COMMENT '个人挂靠车队时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`information_id`),
  KEY `FK_Reference_2` (`car_team_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`information_id`) REFERENCES `tb_user_information` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`car_team_id`) REFERENCES `tb_car_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=997967 DEFAULT CHARSET=utf8 COMMENT='车辆管理平台 用户表';

-- ----------------------------
-- Records of tb_login_user
-- ----------------------------
INSERT INTO `tb_login_user` VALUES ('61', 'shilvfei', 'e10adc3949ba59abbe56e057f20f883e', '18715083541', '1', '1', '2017-11-27 17:59:49', null, '2017-11-27 18:00:05', null, '66799', null);
INSERT INTO `tb_login_user` VALUES ('5077', '小李', 'e10adc3949ba59abbe56e057f20f883e', '18715073541', '0', '1', '2017-11-21 15:35:21', null, '2017-11-29 14:08:21', '57', '33315', '2017-11-29 14:03:29');
INSERT INTO `tb_login_user` VALUES ('5078', '张三', 'e10adc3949ba59abbe56e057f20f883e', '18860486302', '0', '1', '2017-11-21 15:35:21', null, '2017-12-06 13:43:54', '56', '33315', '2017-11-08 11:45:10');
INSERT INTO `tb_login_user` VALUES ('5079', '阿狗', 'c33367701511b4f6020ec61ded352059', '18110275652', '0', '1', '2017-11-21 15:35:21', null, '2017-12-05 19:59:22', '58', null, '2017-11-07 12:03:55');
INSERT INTO `tb_login_user` VALUES ('17477', '123', '202cb962ac59075b964b07152d234b70', '123', '0', '1', '2017-11-27 15:41:21', null, null, '58', '33315', '2017-11-05 12:03:58');
INSERT INTO `tb_login_user` VALUES ('48068', '测试01', 'e10adc3949ba59abbe56e057f20f883e', '18700083549', '0', '0', '2017-11-27 16:27:04', null, '2017-11-30 10:26:19', '58', '33315', '2017-11-01 12:04:28');
INSERT INTO `tb_login_user` VALUES ('82308', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '18110275654', '1', '1', '2017-11-21 15:22:56', null, '2017-12-14 10:38:38', null, '33315', '2017-11-29 11:45:46');
INSERT INTO `tb_login_user` VALUES ('82692', '测试', 'e10adc3949ba59abbe56e057f20f883e', '18700083549', '1', '1', '2017-11-27 16:22:53', null, null, null, '43365', null);
INSERT INTO `tb_login_user` VALUES ('332632', '', 'd41d8cd98f00b204e9800998ecf8427e', '', '0', '1', '2017-12-01 10:32:24', null, null, '112053', null, null);
INSERT INTO `tb_login_user` VALUES ('408383', '18715083519', 'fdac1bc3be488589aa19301b30c10797', '18715083119', '0', '1', '2017-12-06 13:59:45', null, '2017-12-06 13:59:54', null, null, null);
INSERT INTO `tb_login_user` VALUES ('463301', '测试123', 'e10adc3949ba59abbe56e057f20f883e', '18715085468', '0', '1', '2017-11-30 20:46:31', null, '2017-11-30 21:05:47', '175328', null, null);
INSERT INTO `tb_login_user` VALUES ('467151', '1575578950', '517b36b5d1acf55c306ddf4adafcedf0', '1575578950', '0', '1', '2017-12-01 10:18:33', null, null, '722895', null, null);
INSERT INTO `tb_login_user` VALUES ('574517', '1213', '202cb962ac59075b964b07152d234b70', '15755137148', '1', '1', '2017-12-01 11:09:39', null, null, null, '88563', null);
INSERT INTO `tb_login_user` VALUES ('608129', '15355137895', 'c2a9e09e9b31e077150e43a2f1e80d19', '15355137895', '1', '1', '2017-12-01 09:23:09', null, '2017-12-01 09:23:16', null, '99861', null);
INSERT INTO `tb_login_user` VALUES ('661272', '20171201', '3d28a2ad3fa34e473d565b43860e8d89', '15155789632', '0', '1', '2017-12-01 09:12:56', null, '2017-12-01 09:13:07', '123342', null, null);
INSERT INTO `tb_login_user` VALUES ('709328', 'demo', 'e10adc3949ba59abbe56e057f20f883e', '15645645645', '1', '1', '2017-11-28 20:05:19', null, '2017-12-04 11:38:29', null, null, null);
INSERT INTO `tb_login_user` VALUES ('835053', '我是驾驶员', 'e10adc3949ba59abbe56e057f20f883e', '15755137199', '0', '1', '2017-11-30 13:04:52', null, '2017-11-30 13:05:04', '574697', null, null);
INSERT INTO `tb_login_user` VALUES ('891974', '18715083571', '1c8e7dc2c93e1b9440c41eb1e0c51f45', '18715083571', '1', '1', '2017-11-30 21:25:21', null, '2017-11-30 21:25:37', null, '71761', null);
INSERT INTO `tb_login_user` VALUES ('947995', '林二两', 'e10adc3949ba59abbe56e057f20f883e', '18860486300', '0', '1', '2017-12-04 14:27:16', null, '2017-12-04 16:33:56', '371025', null, null);
INSERT INTO `tb_login_user` VALUES ('975255', '我不是驾驶员', 'e10adc3949ba59abbe56e057f20f883e', '15755137194', '0', '1', '2017-11-30 14:42:31', null, '2017-11-30 14:55:01', '992345', '33315', '2017-11-01 14:54:15');
INSERT INTO `tb_login_user` VALUES ('997963', '林亮', 'e10adc3949ba59abbe56e057f20f883e', '18860486307', '1', '1', '2017-12-04 14:21:18', null, '2017-12-04 15:51:36', null, '31001', null);
INSERT INTO `tb_login_user` VALUES ('997964', 'cheduia', 'e10adc3949ba59abbe56e057f20f883e', '18860486308', '1', '1', '2017-12-14 10:46:24', null, '2017-12-14 13:52:34', null, '99862', null);
INSERT INTO `tb_login_user` VALUES ('997966', 'gerena', 'e10adc3949ba59abbe56e057f20f883e', '18355196932', '0', '1', '2017-12-14 10:52:19', null, '2017-12-14 13:47:30', '992346', '99862', null);

-- ----------------------------
-- Table structure for tb_massage_code_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_massage_code_record`;
CREATE TABLE `tb_massage_code_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(200) DEFAULT NULL COMMENT '手机号',
  `creat_time` datetime DEFAULT NULL COMMENT '操作时间',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '记录条数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_massage_code_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL COMMENT '唯一编号',
  `name` varchar(10) NOT NULL COMMENT '菜单名称(10字符以内)',
  `code` varchar(30) NOT NULL COMMENT '菜单编码(30字符以内)',
  `url` varchar(255) DEFAULT NULL,
  `icon_class` varchar(50) DEFAULT NULL COMMENT '图标样式(50字符以内)',
  `parent_id` int(11) NOT NULL COMMENT '父级编号',
  `delete_flag` tinyint(4) NOT NULL COMMENT '删除标识',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '业务', 'business', 'html/business/businessHome.html', 'nav_img1', '0', '0', '1');
INSERT INTO `tb_menu` VALUES ('2', '财务', 'finance', null, 'nav_img2', '0', '0', '1');
INSERT INTO `tb_menu` VALUES ('3', '设置', 'manage', null, 'nav_img3', '0', '0', '1');
INSERT INTO `tb_menu` VALUES ('4', '项目管理', 'project', null, 'leftIcon projectIcon', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('5', '短驳管理', 'shortBarge', null, 'leftIcon shortImg', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('6', '火运管理', 'fireManagement', null, 'leftIcon train', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('7', '库存管理', 'inventory', null, 'leftIcon stockImg', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('8', '业务查询', 'businessQuery', null, 'leftIcon businessImg', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('9', '日志系统', 'logSystem', null, 'leftIcon logImg', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('10', '工具', 'tool', null, 'leftIcon toolImg', '1', '0', '2');
INSERT INTO `tb_menu` VALUES ('11', '计费与对账', 'billReconciliation', null, 'financeIcon charging', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('12', '结算与对账', 'settlementAccountability', null, 'financeIcon Settlement', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('13', '预付款结算', 'prepaymentSettlement', null, 'financeIcon advance', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('14', '费用账目', 'costAccount', null, 'financeIcon cost', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('15', '应收款结算', 'receivablesSettlement', null, 'financeIcon payable', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('16', '物料管理', 'materialManagement', null, 'financeIcon materiel', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('17', '财务调整', 'financialAdjustment', null, 'financeIcon adjustment', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('18', '财务查询', '', null, 'financeIcon financeQuery', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('19', '统计报表', '', null, 'financeIcon statistics', '2', '0', '2');
INSERT INTO `tb_menu` VALUES ('20', '人与组织', 'humanOrganization', null, 'financeIcon personnel', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('21', '客户管理', 'customerManagement', null, 'financeIcon customer', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('22', '站点管理', 'siteManagement', null, 'financeIcon sites', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('23', '运输管理', 'transportationManagement', null, 'financeIcon transport', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('24', '货位管理', 'cargoManagement', null, 'financeIcon locations', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('25', '系统管理', 'systemManagement', null, 'financeIcon systems', '3', '1', '2');
INSERT INTO `tb_menu` VALUES ('26', '财务管理', 'financialManagement', null, 'financeIcon financialManagement', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('27', '异常管理', 'abnormalManagement', null, 'financeIcon abnormalIcon', '3', '0', '2');
INSERT INTO `tb_menu` VALUES ('28', '项目管理', 'projectManagment', '/projectManagment/projectManagment.do', null, '4', '0', '3');
INSERT INTO `tb_menu` VALUES ('29', '项目运营管理', 'projectOperation', '/project/projectOperation.do', null, '4', '0', '3');
INSERT INTO `tb_menu` VALUES ('30', '项目核查', 'projectCheck', '/project/projectCheck.do', null, '4', '0', '3');
INSERT INTO `tb_menu` VALUES ('31', '发布任务', 'distribution', 'business/short/job/list.do', null, '5', '0', '3');
INSERT INTO `tb_menu` VALUES ('32', '集装箱管理', 'truckContainer', 'business/short/boxManager/list.do', null, '5', '0', '3');
INSERT INTO `tb_menu` VALUES ('33', '散装箱管理', 'truckCbulkLoading', 'business/short/bulkPacking/list.do', null, '5', '0', '3');
INSERT INTO `tb_menu` VALUES ('34', '集装箱管理', 'trainCcontainer', 'trainOrder/showTrainOrderByType.do', null, '6', '0', '3');
INSERT INTO `tb_menu` VALUES ('35', '散装箱管理', 'fireTrainCbulkLoading', 'fireTrainCbulkLoading/showTrainOrderByType.do', null, '6', '0', '3');
INSERT INTO `tb_menu` VALUES ('36', '短驳出入库查询', 'shortAccessStorage', 'html/business/inventory/shortAccessStorage.html', null, '7', '0', '3');
INSERT INTO `tb_menu` VALUES ('37', '火运出入库查询', 'trainAccessStorage', 'html/business/inventory/trainAccessStorage.html', null, '7', '0', '3');
INSERT INTO `tb_menu` VALUES ('38', '库存盘查', 'inventoryCheck', 'html/business/inventory/inventoryCheck.html', null, '7', '0', '3');
INSERT INTO `tb_menu` VALUES ('39', '库存计费', 'inventoryBilling', 'html/business/inventory/inventoryBilling.html', null, '7', '1', '3');
INSERT INTO `tb_menu` VALUES ('40', '项目查询', 'projectQuery', null, null, '8', '0', '3');
INSERT INTO `tb_menu` VALUES ('41', '运单查询', 'orderQuery', null, null, '8', '0', '3');
INSERT INTO `tb_menu` VALUES ('42', '车辆查询', 'carQuery', null, null, '8', '0', '3');
INSERT INTO `tb_menu` VALUES ('43', '异常查询', 'abnormalQuery', null, null, '8', '0', '3');
INSERT INTO `tb_menu` VALUES ('44', '运费支出', 'freightPayout', 'html/finance/billReconciliation/freightPayout.html', null, '11', '0', '3');
INSERT INTO `tb_menu` VALUES ('45', '司机对账', 'driverReconciliation', 'html/finance/billReconciliation/driverReconciliation.html', null, '11', '0', '3');
INSERT INTO `tb_menu` VALUES ('46', '客户对账', 'customerReconciliation', 'html/finance/billReconciliation/customerReconciliation.html', null, '11', '0', '3');
INSERT INTO `tb_menu` VALUES ('47', '费用对账', 'costReconciliation', 'html/finance/billReconciliation/costReconciliation.html', null, '11', '0', '3');
INSERT INTO `tb_menu` VALUES ('48', '网点对账', 'dotReconciliation', 'html/finance/billReconciliation/dotReconciliation.html', null, '11', '0', '3');
INSERT INTO `tb_menu` VALUES ('49', '司机结算', 'driverAccounts', 'html/finance/settlementAccountability/driverAccounts.html', null, '12', '0', '3');
INSERT INTO `tb_menu` VALUES ('50', '客户结算', 'customerAccounts', 'html/finance/settlementAccountability/customerAccounts.html', null, '12', '0', '3');
INSERT INTO `tb_menu` VALUES ('51', '网点交账', 'dotAccountability', 'html/finance/settlementAccountability/dotAccountability.html', null, '12', '0', '3');
INSERT INTO `tb_menu` VALUES ('52', '预付款存入', 'imprestPayment', 'html/finance/prepaymentSettlement/imprestPayment.html', null, '13', '0', '3');
INSERT INTO `tb_menu` VALUES ('53', '预付款抵用', 'imprestOffset', 'html/finance/prepaymentSettlement/imprestOffset.html', '', '13', '0', '3');
INSERT INTO `tb_menu` VALUES ('54', '预付款支抵明细', 'imprestDetail', 'html/finance/prepaymentSettlement/imprestDetail.html', null, '13', '0', '3');
INSERT INTO `tb_menu` VALUES ('55', '发票管理', 'invoiceManagement', 'html/finance/invoiceManagement/invoiceManagement.html', null, '14', '0', '3');
INSERT INTO `tb_menu` VALUES ('56', '收支序时账', 'balanceJournal', 'html/finance/invoiceManagement/balanceJournal.html', null, '14', '0', '3');
INSERT INTO `tb_menu` VALUES ('57', '三方应收款管理', 'threePartiesReceivables', 'html/finance/receivablesSettlement/threePartiesReceivables.html', null, '15', '0', '3');
INSERT INTO `tb_menu` VALUES ('58', '企业应收款', 'enterpriseReceivables', 'html/finance/receivablesSettlement/enterpriseReceivables.html', null, '15', '0', '3');
INSERT INTO `tb_menu` VALUES ('59', '油气卡管理', 'oilgasManagement', 'html/finance/materialManagement/oilgasManagement.html', null, '16', '0', '3');
INSERT INTO `tb_menu` VALUES ('60', '物料管理', 'materialManagement', 'html/finance/materialManagement/materialManagement.html', null, '16', '0', '3');
INSERT INTO `tb_menu` VALUES ('61', '运单调整', 'waybillAdjustment', null, null, '17', '0', '3');
INSERT INTO `tb_menu` VALUES ('62', '结算调整', 'settlementAdjustment', null, null, '17', '0', '3');
INSERT INTO `tb_menu` VALUES ('63', '费用调整', 'costAdjustment', null, null, '17', '0', '3');
INSERT INTO `tb_menu` VALUES ('64', '预付款调整', 'imprestAdjustment', null, null, '17', '0', '3');
INSERT INTO `tb_menu` VALUES ('65', '网点分支', 'dotBrance', '/humanOrganization/dotBranch.do', null, '20', '0', '3');
INSERT INTO `tb_menu` VALUES ('66', '员工信息', 'employeeInformation', '/humanOrganization/employeeInformation.do', null, '20', '0', '3');
INSERT INTO `tb_menu` VALUES ('67', '客户信息', 'customerInformation', '/customerManagement/customerInformation.do', null, '21', '0', '3');
INSERT INTO `tb_menu` VALUES ('68', '货场货位', 'goodsAllocation', '/siteManager/freight/yard/list.do', '', '22', '0', '3');
INSERT INTO `tb_menu` VALUES ('69', '站点信息', 'siteInformation', '/trainStation/list.do', null, '22', '0', '3');
INSERT INTO `tb_menu` VALUES ('70', '车辆授权', 'vehicleAuthorization', '/transport/anchord/an_list.do', null, '23', '0', '3');
INSERT INTO `tb_menu` VALUES ('71', '火车车型', 'trainType', '/trainType/list.do', null, '23', '0', '3');
INSERT INTO `tb_menu` VALUES ('72', '集装箱管理', 'boxManagement', '/container/list.do', null, '23', '0', '3');
INSERT INTO `tb_menu` VALUES ('73', '货物信息', 'cargoInformation', '/cargo/cargoList.do', null, '24', '0', '3');
INSERT INTO `tb_menu` VALUES ('74', '权限管理', 'authorityManagemen', '/systemManagement/authorityManagemen.do', null, '25', '0', '3');
INSERT INTO `tb_menu` VALUES ('75', '短信管理', 'messageManagement', '/system/sms/customer/list.do', null, '25', '0', '3');
INSERT INTO `tb_menu` VALUES ('76', '账户管理', 'accountManagement', '/account/accountList.do', null, '26', '0', '3');
INSERT INTO `tb_menu` VALUES ('77', '支付模式', 'paymentMode', '/payment/paymentList.do', null, '26', '0', '3');
INSERT INTO `tb_menu` VALUES ('78', '系统情景设定', 'artificialScenario', '/abnormal/abnormalList.do', null, '27', '0', '3');
INSERT INTO `tb_menu` VALUES ('79', '人工上报设定', 'personScenario', null, null, '27', '0', '3');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息通知id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `content` varchar(200) DEFAULT NULL COMMENT '消息通知内容',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8 COMMENT='消息通知';

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-22 18:03:44');
INSERT INTO `tb_notice` VALUES ('2', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-08 18:03:44');
INSERT INTO `tb_notice` VALUES ('3', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-03 18:03:44');
INSERT INTO `tb_notice` VALUES ('4', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-19 18:03:44');
INSERT INTO `tb_notice` VALUES ('5', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-24 18:03:44');
INSERT INTO `tb_notice` VALUES ('6', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-03 18:03:44');
INSERT INTO `tb_notice` VALUES ('7', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-19 18:03:44');
INSERT INTO `tb_notice` VALUES ('8', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-23 18:03:44');
INSERT INTO `tb_notice` VALUES ('9', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-23 18:03:44');
INSERT INTO `tb_notice` VALUES ('10', '82308', '您申请到合肥深合软件有限公司的消息已被处理。', '2017-11-01 18:03:44');
INSERT INTO `tb_notice` VALUES ('11', '5078', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-08 14:39:56');
INSERT INTO `tb_notice` VALUES ('12', '5078', '您申请到深合软件有限公司（杭州）分公司的消息已被处理。', '2017-11-01 18:03:25');
INSERT INTO `tb_notice` VALUES ('13', '5078', '您申请到深合软件有限公司（上海）分公司的消息已被处理。', '2017-11-11 18:03:44');
INSERT INTO `tb_notice` VALUES ('14', '5078', '您申请到深合软件有限公司（北京）分公司的消息已被处理。', '2017-11-01 18:03:44');
INSERT INTO `tb_notice` VALUES ('15', '5078', '您申请到深合软件有限公司（广州）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('16', '5078', '您申请到深合软件有限公司（香港）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('17', '5078', '您申请到深合软件有限公司（武汉）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('18', '5078', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('19', '5078', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-23 14:44:02');
INSERT INTO `tb_notice` VALUES ('20', '5077', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-08 14:39:56');
INSERT INTO `tb_notice` VALUES ('21', '5077', '您申请到深合软件有限公司（杭州）分公司的消息已被处理。', '2017-11-01 18:03:25');
INSERT INTO `tb_notice` VALUES ('22', '5077', '您申请到深合软件有限公司（上海）分公司的消息已被处理。', '2017-11-11 18:03:44');
INSERT INTO `tb_notice` VALUES ('23', '5077', '您申请到深合软件有限公司（北京）分公司的消息已被处理。', '2017-11-01 18:03:44');
INSERT INTO `tb_notice` VALUES ('24', '5077', '您申请到深合软件有限公司（广州）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('25', '5077', '您申请到深合软件有限公司（香港）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('26', '5077', '您申请到深合软件有限公司（武汉）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('27', '5077', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('28', '5077', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '2017-11-23 14:44:02');
INSERT INTO `tb_notice` VALUES ('30', '5079', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '3017-11-08 14:39:56');
INSERT INTO `tb_notice` VALUES ('31', '5079', '您申请到深合软件有限公司（杭州）分公司的消息已被处理。', '3017-11-01 18:03:35');
INSERT INTO `tb_notice` VALUES ('32', '5079', '您申请到深合软件有限公司（上海）分公司的消息已被处理。', '3017-11-11 18:03:44');
INSERT INTO `tb_notice` VALUES ('33', '5079', '您申请到深合软件有限公司（上海）分公司的消息已被处理。', '3017-11-11 18:03:44');
INSERT INTO `tb_notice` VALUES ('36', '5079', '您申请到深合软件有限公司（武汉）分公司的消息已被处理。', '3017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('37', '5079', '您申请到深合软件有限公司（深圳）分公司的消息已被处理。', '3017-11-01 18:03:14');
INSERT INTO `tb_notice` VALUES ('38', '1', '您有一个车队的挂靠申请,请及时处理', '2017-12-04 10:40:45');
INSERT INTO `tb_notice` VALUES ('39', '96510', '阿狗已经取消对本车队的挂靠', '2017-12-04 11:07:41');
INSERT INTO `tb_notice` VALUES ('40', '7', '您有一个车队的重新挂靠申请,请及时处理', '2017-12-04 11:53:20');
INSERT INTO `tb_notice` VALUES ('41', '33315', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-04 11:54:16');
INSERT INTO `tb_notice` VALUES ('52', '997963', '您有一个司机的挂靠申请,请及时处理', '2017-12-04 14:59:55');
INSERT INTO `tb_notice` VALUES ('53', '947995', '您对无敌风火轮车队的申请挂靠操作,已经被同意', '2017-12-04 15:00:08');
INSERT INTO `tb_notice` VALUES ('54', '947995', '您挂靠的无敌风火轮车队,已经取消您的挂靠', '2017-12-04 15:00:30');
INSERT INTO `tb_notice` VALUES ('55', '31001', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-04 15:00:43');
INSERT INTO `tb_notice` VALUES ('56', '997963', '您有一个司机的挂靠申请,请及时处理', '2017-12-04 15:16:21');
INSERT INTO `tb_notice` VALUES ('57', '31001', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-04 15:45:01');
INSERT INTO `tb_notice` VALUES ('58', '997963', '司机林二两已经取消对本车队的挂靠', '2017-12-04 15:52:20');
INSERT INTO `tb_notice` VALUES ('59', '31001', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-04 15:52:50');
INSERT INTO `tb_notice` VALUES ('60', null, '您有一个司机的挂靠申请,请及时处理', '2017-12-04 16:23:07');
INSERT INTO `tb_notice` VALUES ('61', null, '您有一个司机的挂靠申请,请及时处理', '2017-12-04 16:24:41');
INSERT INTO `tb_notice` VALUES ('62', '82308', '您有一个司机的挂靠申请,请及时处理', '2017-12-04 16:25:54');
INSERT INTO `tb_notice` VALUES ('63', null, '您有一个司机的挂靠申请,请及时处理', '2017-12-04 16:33:12');
INSERT INTO `tb_notice` VALUES ('64', '5079', '您对深合软件有限公司的申请挂靠操作,已被同意', '2017-12-07 17:14:39');
INSERT INTO `tb_notice` VALUES ('65', '947995', '您对深合软件有限公司的申请挂靠操作,已被同意', '2017-12-07 17:17:32');
INSERT INTO `tb_notice` VALUES ('66', '82308', '您对深合软件有限公司的申请挂靠操作,已被同意', '2017-12-07 17:18:32');
INSERT INTO `tb_notice` VALUES ('67', '82308', '您对深合软件有限公司的申请挂靠操作,已被同意', '2017-12-07 17:30:30');
INSERT INTO `tb_notice` VALUES ('68', '947995', '您对深合软件有限公司的申请挂靠操作,已被驳回', '2017-12-07 17:37:55');
INSERT INTO `tb_notice` VALUES ('69', '997964', '您有一个司机的挂靠申请,请及时处理', '2017-12-14 10:51:29');
INSERT INTO `tb_notice` VALUES ('70', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 10:51:50');
INSERT INTO `tb_notice` VALUES ('71', '997964', '司机gerena已经取消对本车队的挂靠', '2017-12-14 11:14:41');
INSERT INTO `tb_notice` VALUES ('72', '99862', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-14 11:40:25');
INSERT INTO `tb_notice` VALUES ('73', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 11:40:37');
INSERT INTO `tb_notice` VALUES ('74', '997964', '司机gerena已经取消对本车队的挂靠', '2017-12-14 11:41:29');
INSERT INTO `tb_notice` VALUES ('75', '99862', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-14 11:43:14');
INSERT INTO `tb_notice` VALUES ('76', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 11:43:32');
INSERT INTO `tb_notice` VALUES ('77', '997964', '司机gerena已经取消对本车队的挂靠', '2017-12-14 11:43:43');
INSERT INTO `tb_notice` VALUES ('78', '99862', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-14 11:47:44');
INSERT INTO `tb_notice` VALUES ('79', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 11:47:58');
INSERT INTO `tb_notice` VALUES ('80', '997964', '司机gerena已经取消对本车队的挂靠', '2017-12-14 11:52:49');
INSERT INTO `tb_notice` VALUES ('81', '99862', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-14 11:57:28');
INSERT INTO `tb_notice` VALUES ('82', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 11:57:38');
INSERT INTO `tb_notice` VALUES ('83', '997966', '您挂靠的车队A-test,已经取消您的挂靠', '2017-12-14 11:57:46');
INSERT INTO `tb_notice` VALUES ('84', '99862', '您有一个司机的重新挂靠申请,请及时处理', '2017-12-14 13:46:05');
INSERT INTO `tb_notice` VALUES ('85', '997966', '您对车队A-test的申请挂靠操作,已经被同意', '2017-12-14 13:46:49');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单编号',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目编码',
  `project_type` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目类型',
  `driver_id` int(11) DEFAULT NULL COMMENT '驾驶员id',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '每次运单状态变更的时间点',
  `user_dispatch_id` int(11) DEFAULT NULL COMMENT '调度员id',
  `user_dispatch_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '调度员姓名',
  `type` tinyint(4) DEFAULT NULL COMMENT '运单类型\n            1:集装箱\n            2:散堆装',
  `status` tinyint(4) DEFAULT NULL COMMENT '1:等待调度\n            2:等待发运\n            3:在途运载\n            4:货位引导\n            5:等待回单\n            6:等待确认\n            7:已完成',
  `exception_status` tinyint(4) DEFAULT NULL COMMENT '异常订单下的状态\n            0 非异常\n            1 异常',
  `exception_id` int(11) DEFAULT NULL COMMENT '人工上报异常关联表id',
  `exception_reoport_id` int(11) DEFAULT NULL COMMENT '异常提报人id',
  `exception_reoport_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '异常提报人姓名',
  `exception_time` datetime DEFAULT NULL COMMENT '异常提报时间',
  `exception_reoport_reason` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '异常原因',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驳回原因',
  `step_select_code` tinyint(4) DEFAULT NULL COMMENT '阶段选择：1接取  2：送达  3汽运',
  `step_select` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '阶段选择name',
  `branch_group_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分支机构',
  `send_company` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货单位',
  `pickup_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '取货地',
  `pickup_place_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '取货详细地址',
  `receipt_company` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货单位',
  `arrive_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵地',
  `arrive_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵地址详情',
  `arrive_freight_yrad` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵货场',
  `arrive_freight_site` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵货位',
  `carrier_vehicle_id` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '承运车辆id',
  `carrier_vehicle_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '承运车辆名称',
  `car_plate_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '承运车辆车牌号',
  `car_type` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车型',
  `driver_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '司机',
  `driver_phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员手机号',
  `container_number1_id` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '集装箱号1id',
  `container_number1` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '集装箱号1',
  `container_number2_id` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '集装箱号2id',
  `container_number2` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '集装箱号2',
  `cargo_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '货位品名',
  `specifications` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '规格',
  `short_barge_cost` decimal(10,2) DEFAULT NULL COMMENT '短驳费用',
  `subsidy` decimal(10,2) DEFAULT NULL COMMENT '补贴',
  `deduction_price` decimal(10,2) DEFAULT NULL COMMENT '扣损单价',
  `send_tare` decimal(10,2) DEFAULT NULL COMMENT '发货皮重',
  `send_gross` decimal(10,2) DEFAULT NULL COMMENT '发货毛重',
  `container_one_send_net` decimal(10,2) DEFAULT NULL COMMENT '第一个集装箱的发货净重       在散堆装时候；作为发货净重',
  `container_two_send_net` decimal(10,2) DEFAULT NULL COMMENT '第二个集装箱的发货净重',
  `test_indicators` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货:化验指标',
  `order_img` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运单图片地址',
  `distribution_cargo_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分配货场',
  `distribution_cargo_site` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分配货位',
  `receipt_tare` decimal(10,2) DEFAULT NULL COMMENT '回单-收货皮重',
  `receipt_gross` decimal(10,2) DEFAULT NULL COMMENT '回单-收货毛重',
  `container_one_receipt_net` decimal(10,2) DEFAULT NULL COMMENT '回单-第一个集装箱到货净重',
  `container_two_receipt_net` decimal(10,2) DEFAULT NULL COMMENT '回单-回单-第二个集装箱到货净重',
  `receipt_test_indicators` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到货:化验指标 ',
  `arrivered_img` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到货图片上传',
  `edit_date` datetime DEFAULT NULL,
  `piece_number` int(11) DEFAULT NULL COMMENT '件数',
  `valuation_unit_type` tinyint(4) DEFAULT NULL COMMENT '计价单位 1吨 2 件',
  `is_cancel` tinyint(4) DEFAULT NULL COMMENT '是否被取消 0未取消 1已取消',
  `carTeam_id` int(11) DEFAULT NULL COMMENT '车队id',
  `place_now_id` int(11) DEFAULT NULL COMMENT '运单位置关联id',
  `order_origin` tinyint(4) DEFAULT NULL COMMENT '1:pc端   2:APP端',
  `deletor_id` int(11) DEFAULT NULL COMMENT '删除人id',
  `delete_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除人姓名',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_reason` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '删除原因',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '逻辑删除\n            0:未删除\n            1:已删除\n            ',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`project_id`),
  KEY `FK_Reference_33` (`driver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COMMENT='短驳订单';

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('1', '100001', '1', '101', '6', '5078', '1', '2017-11-26 19:58:31', null, null, '小王二字', '1', '3', '0', null, null, null, null, null, null, '3', '1', 'A机构', '合肥市深合软件', '安徽省合肥市', '兴科大厦', '新疆秦龙矿业有限公司', '芜湖市', '弋江区码头', '长江1港口', '煤货场', 'AKJK123货位', '1', 'DSDA123456', '1', '吴二', '15136354635', '1', 'WTE-1234567', '2', 'WTE-1234568', '煤', '吨', '1000.00', '0.00', '100.00', '100.00', '100.00', '80.00', '80.00', '11414', null, '32', '1A', '90.00', '90.00', '75.00', '75.00', '10', null, '2017-11-25 16:17:27', '2', '2', '0', '33315', null, '1', null, null, '2017-12-22 18:13:04', '', '1');
INSERT INTO `tb_order` VALUES ('2', '100002', '2', '102', '6', '5078', '1', '2017-11-27 09:56:45', null, null, '萨达', '1', '3', '0', null, null, null, null, null, null, '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ACA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', null, '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', null, '2017-11-28 10:02:22', '2', '2', '0', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('3', '100003', '3', '103', '6', '5077', '1', '2017-11-27 11:42:03', null, null, '撒大声地', '1', '7', '0', null, null, null, null, null, '车辆故障', '2', '1', 'C机构', '深合软件', '合肥市', '兴科', '秦龙矿业', '乌鲁木齐', '阿坝货场', '24港口', '铁矿石货场', 'WEW14货位', '1', 'TSDA123456', '1', '张三', '15156564699', '1', 'RER-5456565', '2', 'SDFE-7845556', '铁矿石', '吨', '800.00', '0.00', '70.00', '70.00', '70.00', '60.00', '60.00', 'fdrty', null, '23', '2S', '65.00', '65.00', '55.00', '55.00', '757', null, '2017-11-27 14:57:20', '2', '2', '1', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('4', '100004', '1', '104', '1', '5077', '1', '2017-11-27 11:24:03', null, null, '小王', '1', '3', '0', null, null, null, null, null, '车辆故障', '2', '1', 'C机构', '深合软件', '合肥市', '兴科', '秦龙矿业', '乌鲁木齐', '阿坝货场', '24港口', '铁矿石货场', 'WEW14货位', '1', 'TTDA123456', '1', '张三', '15156564699', '1', 'RER-5456565', '2', 'SDFE-7845556', '铁矿石', '吨', '800.00', '0.00', '70.00', '70.00', '70.00', '60.00', '60.00', 'fdrty', '', '23', '2S', '65.00', '65.00', '55.00', '55.00', '757', '', '2017-11-27 14:57:20', '2', '2', '1', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('5', '100005', '14', '105', '1', '5078', '1', '2017-12-04 09:56:45', null, null, '小王', '1', '3', '1', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-11-20 10:02:22', '2', '2', '0', '33315', null, '2', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('6', '100006', '5', '106', '1', '5078', '1', '2017-12-05 09:56:45', null, null, '小王', '1', '7', '0', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-04 10:02:22', '2', '2', '0', '33315', null, '2', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('7', '100007', '1', '107', '1', '5078', '1', '2017-11-22 09:56:45', null, null, '小王', '1', '3', '0', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-05 10:02:22', '2', '2', '0', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('8', '100008', '10', '108', '1', '5078', '1', '2017-11-14 09:56:45', '2017-12-22 16:40:06', null, '小王', '1', '3', '0', null, null, null, null, null, '', '1', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '999.00', '999.00', '999.00', '99.00', 'FG-545', '311716.jpg', '2017-12-06 10:02:22', '2', '2', '0', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('9', '100009', '11', '109', '1', '5078', '1', '2017-10-18 09:56:45', null, null, '小王', '1', '4', '0', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'DASDSA555', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-08 10:02:22', '2', '2', '0', '33315', null, '1', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('10', '100010', '12', '110', '1', '5078', '1', '2017-10-18 09:56:45', '2017-12-22 13:52:12', null, '小王', '1', '3', '1', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '501055.jpg', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-10 10:02:22', '2', '2', '0', '33315', null, '2', null, null, null, null, '0');
INSERT INTO `tb_order` VALUES ('11', '100011', '1', '111', '1', '5078', '1', '2017-10-17 09:56:45', '2017-12-22 16:35:38', null, '小王', '1', '6', '0', null, null, null, null, null, '', '2', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '999.00', '999.00', '999.00', '999.00', '999', '696542.jpg', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-09 10:02:22', '2', '2', '0', '33315', null, '2', '1', null, '2017-12-22 10:49:46', null, '0');
INSERT INTO `tb_order` VALUES ('12', '100012', '1', '112', '3', '5078', '1', '2017-10-18 09:56:45', null, null, '小王', '1', '3', '0', null, null, null, null, null, '', '1', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '天怡国际', '新疆秦龙矿业有限公司', '新疆乌鲁木齐', '石河子货场', '2港口', '煤货场', 'AKJK547货位', '1', 'ASDA123456', '1', '李四', '18989599596', '1', 'WTE-5489959', '2', 'WTE-7887878', '煤', '吨', '2000.00', '0.00', '100.00', '100.00', '100.00', '90.00', '90.00', 'GSDF-441', '', '32', '2B', '95.00', '95.00', '85.00', '85.00', 'FG-545', '', '2017-12-10 10:02:22', '2', '2', '0', '33315', null, '2', null, null, '2017-12-04 21:35:09', '', '0');
INSERT INTO `tb_order` VALUES ('13', '100013', '36', '113', '2', '5078', '1', '2017-12-23 17:43:07', '2017-12-26 10:34:44', null, '小王', '2', '5', '0', null, null, null, null, null, null, '1', '1', 'B机构', '合肥市深合软件', '安徽省合肥市', '安徽省合肥市', '安徽省合肥市', '安徽省合肥市', '安徽省合肥市', '安徽省合肥市', '安徽省合肥市', 'AKJasdf88货位', '1', 'ASDA123456', '1', '李四', '15555555555', '1', null, null, null, '煤', '吨', '2000.00', '0.00', '100.00', '123123.00', '123123.00', '123132.00', null, '123132', '835198.jpg', 'A', '35', '999.00', '999.00', '999.00', null, null, '893930.jpg', null, null, null, null, null, null, null, '1', 'admin', '2017-12-23 19:48:21', '', '0');
INSERT INTO `tb_order` VALUES ('14', '', '1', 'QL00001', null, null, '1', null, '2017-12-25 18:23:28', '1', 'admin', '1', '5', '0', null, null, null, null, null, null, '1', '接取', '秦龙物流', null, null, null, null, 'NNNN', null, null, null, '997966', '个人-A测试', 'WB-666', '好吃', '个人-A测试', '18355196932', '25', '25', '24', 'DCQ8894655', '煤', null, '2.00', null, '2.00', '123.00', '123.00', '123.00', '123.00', '123', '114169.jpg', 'A', '10', null, null, null, null, null, null, null, null, null, null, null, null, '1', null, null, null, null, '0');

-- ----------------------------
-- Table structure for tb_order_operator
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_operator`;
CREATE TABLE `tb_order_operator` (
  `id` int(11) NOT NULL,
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `operator_date` datetime DEFAULT NULL COMMENT '操作时间',
  `order_id` int(11) DEFAULT NULL COMMENT '运单id',
  `order_status` tinyint(4) DEFAULT NULL COMMENT '运单状态\n            0:等待承认（等待调度）\n            1:等待装车\n            2:等待发运\n            3;在途运载\n            4:货位引导\n            5;等待卸货\n            6:等待回单\n            7:等待确认\n            ',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型\n            0:短驳\n            1:火运',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_29` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='订单操作记录表';

-- ----------------------------
-- Records of tb_order_operator
-- ----------------------------
INSERT INTO `tb_order_operator` VALUES ('1', '1', '2017-11-25 15:40:08', '1', '1', '0');
INSERT INTO `tb_order_operator` VALUES ('2', '1', '2017-11-28 10:07:16', '2', '7', '0');
INSERT INTO `tb_order_operator` VALUES ('3', '1', '2017-11-27 14:57:56', '3', '1', '0');

-- ----------------------------
-- Table structure for tb_payment
-- ----------------------------
DROP TABLE IF EXISTS `tb_payment`;
CREATE TABLE `tb_payment` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付方式';

-- ----------------------------
-- Records of tb_payment
-- ----------------------------
INSERT INTO `tb_payment` VALUES ('1', '现金');
INSERT INTO `tb_payment` VALUES ('2', '银行卡');

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单编号',
  `menu_parent_id` int(11) DEFAULT NULL COMMENT '二级菜单',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限描述',
  `delete_flag` tinyint(255) DEFAULT NULL COMMENT '删除标识',
  `range` tinyint(4) DEFAULT NULL COMMENT '范围 1 业务 2财务 3 设置 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1 COMMENT='权限表';

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
INSERT INTO `tb_permission` VALUES ('4', 'business:projectManagment', '28', '4', '项目管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('5', 'business:projectOperation', '29', '4', '项目运营管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('6', 'business:projectCheck', '30', '4', '项目核查', '0', '1');
INSERT INTO `tb_permission` VALUES ('7', 'business:distribution', '31', '5', '发布任务', '0', '1');
INSERT INTO `tb_permission` VALUES ('8', 'business:truckContainer', '32', '5', '集装箱管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('9', 'business:truckCbulkLoading', '33', '5', '散装箱管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('10', 'business:trainCcontainer', '34', '6', '集装箱管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('11', 'business:fireTrainCbulkLoading', '35', '6', '散装箱管理', '0', '1');
INSERT INTO `tb_permission` VALUES ('12', 'business:shortAccessStorage', '36', '7', '短驳出入库查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('13', 'business:trainAccessStorage', '37', '7', '火运出入库查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('14', 'business:inventoryCheck', '38', '7', '库存盘查', '0', '1');
INSERT INTO `tb_permission` VALUES ('15', 'business:inventoryBilling', '39', '7', '库存计费', '0', '1');
INSERT INTO `tb_permission` VALUES ('16', 'business:projectQuery', '40', '8', '项目查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('17', 'business:orderQuery', '41', '8', '运单查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('18', 'business:carQuery', '42', '8', '车辆查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('19', 'business:abnormalQuery', '43', '8', '异常查询', '0', '1');
INSERT INTO `tb_permission` VALUES ('20', 'finance:freightPayout', '44', '11', '运费支出', '0', '2');
INSERT INTO `tb_permission` VALUES ('21', 'finance:driverReconciliation', '45', '11', '司机对账', '0', '2');
INSERT INTO `tb_permission` VALUES ('22', 'finance:customerReconciliation', '46', '11', '客户对账', '0', '2');
INSERT INTO `tb_permission` VALUES ('24', 'finance:costReconciliation', '47', '11', '费用对账', '0', '2');
INSERT INTO `tb_permission` VALUES ('25', 'finance:dotReconciliation', '48', '11', '网点对账', '0', '2');
INSERT INTO `tb_permission` VALUES ('26', 'finance:driverAccounts', '49', '12', '司机结算', '0', '2');
INSERT INTO `tb_permission` VALUES ('27', 'finance:customerAccounts', '50', '12', '客户结算', '0', '2');
INSERT INTO `tb_permission` VALUES ('28', 'finance:dotAccountability', '51', '12', '网点交账', '0', '2');
INSERT INTO `tb_permission` VALUES ('38', 'manage:authorityManagemen', '74', '25', '权限管理', '0', '3');
INSERT INTO `tb_permission` VALUES ('42', 'manage:dotBrance', '65', '20', '员工信息', '0', '3');
INSERT INTO `tb_permission` VALUES ('43', 'manage:employeeInformation', '66', '20', '网点分支', '0', '3');
INSERT INTO `tb_permission` VALUES ('44', 'manage:customerInformation', '67', '21', '客户信息', '0', '3');
INSERT INTO `tb_permission` VALUES ('45', 'manage:goodsAllocation', '68', '22', '货场货位', '0', '3');
INSERT INTO `tb_permission` VALUES ('46', 'manage:siteInformation', '69', '22', '站点信息', '0', '3');
INSERT INTO `tb_permission` VALUES ('47', 'manage:vehicleAuthorization', '70', '23', '车辆授权', '0', '3');
INSERT INTO `tb_permission` VALUES ('48', 'manage:trainType', '71', '23', '火车车型', '0', '3');
INSERT INTO `tb_permission` VALUES ('49', 'manage:boxManagement', '72', '23', '集装箱管理', '0', '3');
INSERT INTO `tb_permission` VALUES ('50', 'manage:cargoInformation', '73', '24', '货物信息', '0', '3');
INSERT INTO `tb_permission` VALUES ('51', 'manage:messageManagement', '75', '25', '短信管理', '0', '3');
INSERT INTO `tb_permission` VALUES ('52', 'manage:accountManagement', '76', '26', '账户管理', '0', '3');
INSERT INTO `tb_permission` VALUES ('53', 'manage:paymentMode', '77', '26', '支付管理', '0', '3');
INSERT INTO `tb_permission` VALUES ('54', 'manage:artificialScenario', '78', '27', '系统情景设定', '0', '3');
INSERT INTO `tb_permission` VALUES ('55', 'manage:personScenario', '79', '27', '人工上报设定', '0', '3');
INSERT INTO `tb_permission` VALUES ('57', 'finance:imprestPayment', '52', '13', '预存款存入', '0', '2');
INSERT INTO `tb_permission` VALUES ('58', 'finance:imprestOffset', '53', '13', '预存款抵用', '0', '2');
INSERT INTO `tb_permission` VALUES ('59', 'finance:imprestDetail', '54', '13', '预存款支抵明细', '0', '2');
INSERT INTO `tb_permission` VALUES ('60', 'finance:invoiceManagement', '55', '14', '发票管理', '0', '2');
INSERT INTO `tb_permission` VALUES ('61', 'finance:balanceJournal', '56', '14', '收支序时帐', '0', '2');
INSERT INTO `tb_permission` VALUES ('62', 'finance:threePartiesReceivables', '57', '15', '三方应收款管理', '0', '2');
INSERT INTO `tb_permission` VALUES ('63', 'finance:enterpriseReceivables', '58', '15', '企业应收款', '0', '2');
INSERT INTO `tb_permission` VALUES ('64', 'finance:oilgasManagement', '59', '16', '油气卡管理', '0', '2');
INSERT INTO `tb_permission` VALUES ('65', 'finance:materialManagement', '60', '16', '物料管理', '0', '2');
INSERT INTO `tb_permission` VALUES ('66', 'finance:waybillAdjustment', '61', '17', '运单调整', '0', '2');
INSERT INTO `tb_permission` VALUES ('67', 'finance:settlementAdjustment', '62', '17', '结算调整', '0', '2');
INSERT INTO `tb_permission` VALUES ('68', 'finance:costAdjustment', '63', '17', '费用调整', '0', '2');
INSERT INTO `tb_permission` VALUES ('69', 'finance:imprestAdjustment', '64', '17', '预付款调整', '0', '2');

-- ----------------------------
-- Table structure for tb_point
-- ----------------------------
DROP TABLE IF EXISTS `tb_point`;
CREATE TABLE `tb_point` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `point_name` varchar(200) DEFAULT NULL COMMENT '主要指标',
  `point_min` int(11) DEFAULT NULL COMMENT '指标界定最小值',
  `point_max` int(11) DEFAULT NULL COMMENT '指标界定最大值',
  `type` tinyint(4) DEFAULT NULL COMMENT '指标类型\n            0：主要指标\n            1:其他指标',
  `cargo_id` int(11) DEFAULT NULL COMMENT '货物id',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_26` (`cargo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=176 DEFAULT CHARSET=utf8 COMMENT='指标表';

-- ----------------------------
-- Records of tb_point
-- ----------------------------
INSERT INTO `tb_point` VALUES ('1', 'aaa', '1', '10', '0', '1');
INSERT INTO `tb_point` VALUES ('3', 'yiban', '2', '6', '1', '1');
INSERT INTO `tb_point` VALUES ('102', 'A', '1', '2', '0', '3');
INSERT INTO `tb_point` VALUES ('103', 'B', '2', '3', '1', '3');
INSERT INTO `tb_point` VALUES ('104', 'C', '4', '6', '1', '3');
INSERT INTO `tb_point` VALUES ('109', 'zhibiao', '2', '5', '1', '2');
INSERT INTO `tb_point` VALUES ('110', 'dd', '3', '7', '0', '2');
INSERT INTO `tb_point` VALUES ('117', '主要', '2', '4', '0', '54');
INSERT INTO `tb_point` VALUES ('118', '次要', '3', '4', '1', '54');
INSERT INTO `tb_point` VALUES ('119', '劣质', '6', '8', '1', '54');
INSERT INTO `tb_point` VALUES ('123', '上等', '1', '2', '1', '55');
INSERT INTO `tb_point` VALUES ('124', '中等', '3', '5', '0', '55');
INSERT INTO `tb_point` VALUES ('125', '次等', '5', '6', '1', '55');
INSERT INTO `tb_point` VALUES ('145', 'ds', '4', '5', '1', '58');
INSERT INTO `tb_point` VALUES ('146', 'vv', '2', '3', '0', '58');
INSERT INTO `tb_point` VALUES ('147', 'yy', '6', '7', '1', '58');
INSERT INTO `tb_point` VALUES ('167', 'vvvb', '5', '6', '1', '62');
INSERT INTO `tb_point` VALUES ('168', 'bgff', '8', '9', '1', '62');
INSERT INTO `tb_point` VALUES ('169', 'rrrr', '2', '4', '0', '62');
INSERT INTO `tb_point` VALUES ('173', 'ffffe', '6', '7', '1', '65');
INSERT INTO `tb_point` VALUES ('174', 'jjk', '2', '3', '0', '65');
INSERT INTO `tb_point` VALUES ('175', 'kjn', '5', '6', '1', '65');

-- ----------------------------
-- Table structure for tb_project
-- ----------------------------
DROP TABLE IF EXISTS `tb_project`;
CREATE TABLE `tb_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '项目编号',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构id',
  `branch_group_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '分支机构名称',
  `project_type` tinyint(4) DEFAULT NULL COMMENT '项目类型\n            0 集装箱\n            1 散装',
  `transport_type` tinyint(4) DEFAULT NULL COMMENT '联运模式\n            0 汽运\n            1 接取\n            2 火运\n            3 送达\n            4 接取+火运\n            5 火运+送达\n            6 联运  7接取+送达',
  `cargo_id` int(11) DEFAULT NULL COMMENT '货物id',
  `cargo_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '货物名称',
  `cargo_specifications` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '规格',
  `cargo_price` decimal(10,0) DEFAULT NULL COMMENT '货物价格',
  `valuation_unit_name` tinyint(4) DEFAULT NULL COMMENT '计价单位\n            0 件\n            1 吨',
  `send_cargo_company_id` int(11) DEFAULT NULL COMMENT '发货企业id',
  `send_cargo_company_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货企业名称',
  `receive_cargo_company_id` int(11) DEFAULT NULL COMMENT '收货企业id',
  `receive_cargo_company_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货企业名称',
  `send_cargo_unit_id` int(11) DEFAULT NULL COMMENT '发货单位id',
  `send_cargo_unit_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货单位',
  `receive_cargo_site_id` int(11) DEFAULT NULL COMMENT '收货站点id',
  `receive_cargo_site` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货站点',
  `short_barge_carrier_mode` tinyint(4) DEFAULT '0' COMMENT '短驳承运方式\n            0 平台\n            1 自选',
  `short_barge_carrier_id` int(11) DEFAULT NULL COMMENT '短驳承运方id',
  `short_barge_carrier_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '短驳承运方',
  `send_short_barge_carrier_mode` tinyint(255) DEFAULT NULL COMMENT '送达 短驳承运方',
  `begin_center_site_id` int(11) DEFAULT NULL COMMENT '起运中心站id',
  `begin_center_site_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '起运中心站',
  `begin_site_id` int(11) DEFAULT NULL COMMENT '始发站点id',
  `begin_site_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '始发站点',
  `begin_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '始发地',
  `end_center_site_id` int(11) DEFAULT NULL COMMENT '到达中心站id',
  `end_center_site_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到达中心站',
  `end_site_id` int(11) DEFAULT NULL COMMENT '到站站点id',
  `end_site_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到达站点',
  `end_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到达地',
  `freight` decimal(10,0) DEFAULT NULL COMMENT '运费核计',
  `material_cost` decimal(10,0) DEFAULT NULL COMMENT '材料费',
  `tarpaulin_cost` decimal(10,0) DEFAULT NULL COMMENT '篷布使用费',
  `begin_stevedoring_cost` decimal(10,0) DEFAULT NULL COMMENT '发站装卸费',
  `end_stevedoring_cost` decimal(10,0) DEFAULT NULL COMMENT '到站装卸费',
  `freight_sum` decimal(10,0) DEFAULT NULL COMMENT '运费合计',
  `forwarding_site_id` int(11) DEFAULT NULL COMMENT '发货站点id',
  `forwarding_site_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货站点名称',
  `forwarding_unit_id` int(11) DEFAULT NULL COMMENT '送达:发货单位id ',
  `forwarding_unit_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '发货单位名称',
  `take_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '取货地',
  `take_place_detail` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '取货地址',
  `receiving_department_id` int(11) DEFAULT NULL COMMENT '收货单位id',
  `receiving_department_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '收货单位名称',
  `arrive_place` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵地',
  `arrive_place_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '运抵地址',
  `pick_up_price` decimal(10,0) DEFAULT NULL COMMENT '接取单价',
  `train_price` decimal(10,0) DEFAULT NULL COMMENT '火运单价',
  `arrive_price` decimal(10,0) DEFAULT NULL COMMENT '送达单价',
  `transport_price` decimal(10,0) DEFAULT NULL COMMENT '汽运单价',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `edit_date` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '项目状态\n            0  未过期取消(24小时之内可以在历史回收站可以看到的)\n            1 正在运行(已使用)\n            2 已完成  3 未使用',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `principal` tinyint(4) DEFAULT NULL COMMENT '委托方 0收货企业 1发货企业',
  `is__distribution` tinyint(4) DEFAULT NULL COMMENT '是否进行任务分配：  1开始  0暂停  2接取分配 送达暂停     3-接取暂停 送达分配',
  `delete_flag` tinyint(4) DEFAULT '0' COMMENT '删除标识  0未删除 1 删除',
  `finish_date` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  KEY `ref_bra_pro` (`branch_group_id`),
  KEY `ref_cus_pro` (`send_cargo_company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1 COMMENT='项目表';

-- ----------------------------
-- Records of tb_project
-- ----------------------------
INSERT INTO `tb_project` VALUES ('1', 'QL00001', '1', '秦龙物流', '1', '1', '1', '煤', '1', '1000', '1', '1', 'A地', '2', 'B地', '1', 'C地', '1', 'D地', '1', '0', '公司', null, '1', '合肥南', '1', '合肥', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASD', 'ASDASD', '1', '深合软件深合软件深合软件深合软件', 'NNNN', 'NNNNNNNN', null, null, null, null, '123123', '2017-11-23 19:56:41', '2017-11-23 19:56:44', '1', '1', null, '1', '0', null);
INSERT INTO `tb_project` VALUES ('2', 'QL00002', '1', '秦龙物流', '1', '0', '1', '煤', '1', '1000', '1', '1', 'A地', '2', 'B地', '1', 'C地', '1', 'D地', '1', '0', '公司', null, '1', '合肥南', '1', '合肥', '', null, '', null, '', '', null, null, null, null, null, null, null, '', null, '', 'ASD', 'ASDASD', '1', '深合软件深合软件深合软件深合软件', 'NNNN', 'NNNNNNNN', null, null, null, null, '123123', '2017-11-23 19:56:41', '2017-11-23 19:56:44', '1', '1', null, '0', '0', null);
INSERT INTO `tb_project` VALUES ('3', 'QL00003', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '10', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASD', 'ASDASD', null, null, 'NNNN', 'NNNNNNNN', null, null, null, '100', '需要速度 quick', null, null, null, null, '0', '0', '0', null);
INSERT INTO `tb_project` VALUES ('4', 'QL20171220143536', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASD', 'ASDASD', null, null, 'WWWW', 'WWWWWWWW', null, null, null, '1000', '123123', '2017-12-20 14:35:36', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('5', 'QL20171220163456', '1', '合肥分支中心', '0', '1', '1', '煤煤煤煤煤煤煤', '吨', '123', '0', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件', '2', '2222', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASD', 'ASDASD', null, null, 'WWWW', 'WWWWWWWW', null, null, null, null, '123123', '2017-12-20 16:34:57', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('6', 'QL20171220193407', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASD', 'ASDASD', '1', '深合软件深合软件深合软件深合软件', 'HHH', 'HHHH', null, null, null, null, '123123', '2017-12-20 19:34:07', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('7', 'QL20171220193646', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'ASDASD', 'ASDASD', '1', '深合软件深合软件深合软件深合软件', 'JJJJ', 'JJJJ', null, null, null, null, '123123', '2017-12-20 19:36:47', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('8', 'QL20171220193920', '1', '合肥分支中心', '0', '1', '1', '煤', '吨', '123', '0', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件深合软件深合软件深合软件', '1', '深合软件深合软件深合软件深合软件', '2', '2222', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', null, null, 'KKKK', 'LLLL', null, null, null, null, '123123', '2017-12-20 19:39:20', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('9', 'QL20171221213827', '1', '合肥分支中心', '0', '0', '3', '杯子', 'xxs', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件深合软件深合软件深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', '1', '深合软件深合软件深合软件深合软件', 'QQQ', 'QQQ', null, null, null, '100', '112456', '2017-12-21 21:38:27', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('10', 'QL20171221214018', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件深合软件深合软件深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', '1', '深合软件深合软件深合软件深合软件', 'EEE', 'EEEE', null, null, null, null, '123123', '2017-12-21 21:40:18', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('11', 'QL20171221224823', '1', '合肥分支中心', '0', '1', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', null, null, 'RRRR', 'TTTT', null, null, null, null, '123123', '2017-12-21 22:48:23', null, '2', '1', '0', '0', '0', '2017-12-22 18:31:22');
INSERT INTO `tb_project` VALUES ('12', 'QL20171222115110', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '15', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', '1', '深合软件', 'YYYY', 'UUUUU', null, null, null, '25', '123', '2017-12-22 11:51:11', null, '2', '1', '0', '0', '0', '2017-12-22 19:43:13');
INSERT INTO `tb_project` VALUES ('13', 'QL20171222144852', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'FWEQ', 'FWEQFWEQ', '1', '深合软件', 'UUUP', 'IIIII', null, null, null, null, '20171222', '2017-12-22 14:48:53', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('14', 'QL20171222144952', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '121212', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'UYTU', 'UYTUUYTU', '1', '深合软件', 'PPP', 'OOOOP', null, null, null, '12', '1222zhou wu', '2017-12-22 14:49:53', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('15', 'QL20171222151452', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '1000', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'UYTU', 'UYTUUYTU', '1', '深合软件', 'ASD', 'ZXCZX', null, null, null, '10', '平台', '2017-12-22 15:14:53', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('16', 'QL20171222160129', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '1223', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'UYTU', 'UYTUUYTU', '1', '深合软件', 'CZX', 'VXXC', null, null, null, '12', '4454', '2017-12-22 16:01:30', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('17', 'QL20171222160338', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '11', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'UYTU', 'UYTUUYTU', '1', '深合软件', 'FDSFSD', 'FSDFSD', null, null, null, '12', '312312312', '2017-12-22 16:03:38', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('18', 'QL20171222160549', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'UYTU', 'UYTUUYTU', '1', '深合软件', 'GSDGS', 'GSDG', null, null, null, '12', '123123', '2017-12-22 16:05:49', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('19', 'QL20171222214557', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '', '2', '三级站肥西,北京市', null, '23', '23', '23', '123', '123', '12', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, '11', null, '2017-12-22 21:45:58', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('20', 'QL20171222215510', '1', '合肥分支中心', '0', '5', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', null, null, null, null, '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '12', '12', '12', '12', '12', '12', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, '213', null, '2017-12-22 21:55:10', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('21', 'QL20171222220457', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '23', '12', '21', '11', '12', '11', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '2', '2', '2', null, '21312', '2017-12-22 22:04:57', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('22', 'QL20171222221541', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '11', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '1', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '2', '2', '1', '2', '1', '2', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '2', '2', '2', null, '123', '2017-12-22 22:15:42', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('23', 'QL20171222225220', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '12', '23', '23', '2', '12', '123', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '123', '23', '23', null, '123123', '2017-12-22 22:52:20', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('24', 'QL20171222225423', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '0', '1', '中心站合肥', '2', '三级站肥西', '北京市xx', '1', '中心站合肥', '2', '三级站肥西,北京市cc', null, '123', '213', '213', '123', '123', '123', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '1', '2', '3', '2', '12312321', '2017-12-22 22:54:23', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('25', 'QL20171223144353', '1', '合肥分支中心', '0', '7', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '0', '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '123', '123', '123', '213', '123', '123', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '123', '123', '121', '123', '12321', '2017-12-23 14:43:53', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('26', 'QL20171223153204', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-23 15:32:05', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('27', 'QL20171223153309', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '10', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, '10', '1010', '2017-12-23 15:33:09', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('28', 'QL20171223153611', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, '123', '123', '2017-12-23 15:36:12', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('29', 'QL20171223160745', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, '1', '123', '2017-12-23 16:07:45', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('30', 'QL20171223160936', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '1254', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, '', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, '4', '56', '2017-12-23 16:09:36', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('31', 'QL20171223161323', '1', '合肥分支中心', '0', '1', '1', '煤', '吨', '10', '0', '1', '深合软件', '1', '深合软件', '2', '秦龙矿业', '2', '三级站肥西', '1', null, '[{\"shortBargeId\":\"33315\",\"shortBargeName\":\"悠悠车队\",\"transportPrice\":\"10\",\"deductionRate\":\"1\",\"deductionPrice\":\"1\",\"payment\":\"0\"}]', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '10', '2017-12-23 16:13:24', null, '0', '1', '1', '0', '1', null);
INSERT INTO `tb_project` VALUES ('32', 'QL20171223162058', '1', '合肥分支中心', '0', '2', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, '12', '123213', '2017-12-23 16:20:59', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('33', 'QL20171223162254', '1', '合肥分支中心', '0', '2', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-23 16:22:55', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('34', 'QL20171223173946', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', '12', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, '123', '12', '2017-12-23 17:40:05', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('35', 'QL20171223174141', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', '888', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '1', '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '23', '12', '23', '23', '213', '123', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '12', '23', '23', '888', '123', '2017-12-23 17:41:46', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('36', 'QL20171223174755', '1', '合肥分支中心', '1', '6', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '0', '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, null, null, null, null, null, null, '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '111', '111', '111', '2', '', '2017-12-23 17:50:17', null, '2', '1', '0', '0', '0', '2017-12-23 18:39:20');
INSERT INTO `tb_project` VALUES ('37', 'QL20171223175209', '1', '合肥分支中心', '1', '6', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '0', '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, '123', '213', '213', '123', '123', '123', '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, '123', '123', '123', '11', '123213', '2017-12-23 17:52:18', null, '0', '1', '0', '0', '1', null);
INSERT INTO `tb_project` VALUES ('38', 'QL20171224101452', '1', '合肥分支中心', '0', '1', '1', '煤', '吨', '123', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '15', null, null, '22', '123', '2017-12-24 10:14:52', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('39', 'QL20171225120116', '1', '合肥分支中心', '0', '0', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-25 12:01:17', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('40', 'QL20171225125817', '1', '合肥分支中心', '1', '4', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '', '2017-12-25 12:58:17', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('41', 'QL20171225125830', '1', '合肥分支中心', '0', '5', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', null, null, null, null, '0', null, null, null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西,北京市', null, null, null, null, null, null, null, '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-25 12:58:31', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('42', 'QL20171225125851', '1', '合肥分支中心', '0', '7', '1', '煤', '吨', '500', '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '2', '三级站肥西', '0', null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '2', '三级站肥西', null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-25 12:58:52', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('43', 'QL20171225132337', '1', '合肥分支中心', '0', '3', '1', '煤', '吨', '1232', '0', '1', '深合软件', '1', '深合软件', null, null, null, null, null, null, '', null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西', null, '123', '123', '123', '123', '123', '123', null, null, null, null, null, null, null, null, null, null, null, '123', null, null, '1231', '2017-12-25 13:23:38', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('44', 'QL20171225133934', '1', '合肥分支中心', '0', '3', '1', '煤', '吨', '123123', '0', '1', '深合软件', '1', '深合软件', null, null, null, null, null, null, '', null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西', null, '11', '123', '23', '123', '123', '1231', null, null, null, null, null, null, null, null, null, null, null, '123', null, null, '123', '2017-12-25 13:39:34', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('45', 'QL20171225134232', '1', '合肥分支中心', '0', '3', '1', '煤', '吨', '99', '0', '1', '深合软件', '1', '深合软件', null, null, null, null, null, null, '', null, '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '中心站合肥', '2', '三级站肥西', '北京市', '1', '2', '3', '4', '5', '6', null, null, null, null, null, null, null, null, null, null, null, '7', null, null, '8', '2017-12-25 13:42:33', null, '1', '1', '0', '1', '0', null);
INSERT INTO `tb_project` VALUES ('46', 'QL20171226173812', '1', '合肥分支中心', '0', '6', '1', '煤', '吨', null, '0', '1', '深合软件', '1', '深合软件', '1', '深合软件', '5', 'CCCCC', '0', null, null, '0', '2', 'BBBBB', '5', 'CCCCC', '123', '2', 'BBBBB', '5', 'CCCCC,123', null, null, null, null, null, null, null, '5', 'CCCCC', null, null, null, null, '1', '深合软件', null, null, null, null, null, null, '', '2017-12-26 17:38:13', null, '1', '1', '0', '1', '0', null);

-- ----------------------------
-- Table structure for tb_project_distribution
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_distribution`;
CREATE TABLE `tb_project_distribution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `car_num` int(11) DEFAULT NULL COMMENT '每日车数',
  `single_weight` decimal(10,0) DEFAULT NULL COMMENT '单车重量',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `type` tinyint(4) DEFAULT NULL COMMENT '1:接去  2:送达',
  `already_rec_num` int(11) DEFAULT NULL COMMENT '已经被的领取分配数',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '逻辑删除  1删除',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`project_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COMMENT='项目每日分配表';

-- ----------------------------
-- Records of tb_project_distribution
-- ----------------------------
INSERT INTO `tb_project_distribution` VALUES ('1', '1', '5', '0', '1', '2017-12-21 14:09:27', '1', '2', '0');
INSERT INTO `tb_project_distribution` VALUES ('2', '1', '100', '0', '1', '2017-12-21 10:03:51', '2', '3', '0');
INSERT INTO `tb_project_distribution` VALUES ('3', '2', '7', '0', '1', '2017-12-21 15:47:07', '2', '5', '0');
INSERT INTO `tb_project_distribution` VALUES ('7', '1', '10', null, '1', '2017-12-22 13:47:24', '2', '0', '0');
INSERT INTO `tb_project_distribution` VALUES ('8', '19', '99', null, '1', '2017-12-25 19:31:10', '1', '0', '0');

-- ----------------------------
-- Table structure for tb_project_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_operation_log`;
CREATE TABLE `tb_project_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人id',
  `content` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型 0 删除 1修改 2新增 3还原',
  `operator_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `project_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_31` (`project_id`),
  CONSTRAINT `ref_pro_log` FOREIGN KEY (`project_id`) REFERENCES `tb_project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1 COMMENT='项目操作日志\r\n';

-- ----------------------------
-- Records of tb_project_operation_log
-- ----------------------------
INSERT INTO `tb_project_operation_log` VALUES ('1', '2017-12-20 19:31:52', '1', 'admin在2017年12月20日19时31分51秒删除编号为QL20171220163456的项目', '5', '0', 'admin', 'QL20171220163456');
INSERT INTO `tb_project_operation_log` VALUES ('2', '2017-12-20 19:31:58', '1', 'admin在2017年12月20日19时31分57秒删除编号为QL20171220163456的项目', '5', '0', 'admin', 'QL20171220163456');
INSERT INTO `tb_project_operation_log` VALUES ('3', '2017-12-20 19:38:07', '1', 'admin在2017年12月20日19时38分07秒删除编号为QL20171220193646的项目', '7', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('4', '2017-12-20 19:38:14', '1', 'admin在2017年12月20日19时38分14秒删除编号为QL20171220193407的项目', '6', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('5', '2017-12-21 14:17:24', '1', 'admin在2017年12月21日14时17分24秒删除编号为QL20171220193920的项目', '8', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('6', '2017-12-21 21:32:13', '1', '.text().text().text().text()', '4', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('7', '2017-12-22 16:17:39', '1', '作废', '16', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('8', '2017-12-22 16:17:54', '1', '作废2', '14', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('9', '2017-12-22 16:18:16', '1', '作废3', '13', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('10', '2017-12-22 16:18:25', '1', '4', '10', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('11', '2017-12-22 16:18:43', '1', '作废5', '9', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('12', '2017-12-22 17:22:28', '1', 'admin在2017年12月22日17时22分27秒设置编号为QL20171221224823的项目为完成状态', '11', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('13', '2017-12-22 18:10:18', '1', '未完成', '15', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('14', '2017-12-22 19:43:13', '1', 'admin在2017年12月22日19时43分13秒设置编号为QL20171222115110的项目为完成状态', '12', '0', 'admin', null);
INSERT INTO `tb_project_operation_log` VALUES ('15', '2017-12-23 18:35:53', '1', '没有理由', '37', '0', 'admin', 'QL20171223175209');
INSERT INTO `tb_project_operation_log` VALUES ('16', '2017-12-23 18:36:05', '1', null, '37', '3', 'admin', 'QL20171223175209');
INSERT INTO `tb_project_operation_log` VALUES ('17', '2017-12-23 18:37:32', '1', '123', '37', '0', 'admin', 'QL20171223175209');
INSERT INTO `tb_project_operation_log` VALUES ('18', '2017-12-23 18:37:37', '1', '1232', '36', '0', 'admin', 'QL20171223174755');
INSERT INTO `tb_project_operation_log` VALUES ('19', '2017-12-23 18:37:58', '1', null, '36', '3', 'admin', 'QL20171223174755');
INSERT INTO `tb_project_operation_log` VALUES ('20', '2017-12-23 18:39:20', '1', 'admin在2017年12月23日18时39分19秒设置编号为QL20171223174755的项目为完成状态', '36', '1', 'admin', 'QL20171223174755');
INSERT INTO `tb_project_operation_log` VALUES ('21', '2017-12-24 09:53:43', '1', '', '28', '0', 'admin', 'QL20171223153611');
INSERT INTO `tb_project_operation_log` VALUES ('22', '2017-12-24 09:53:50', '1', '', '29', '0', 'admin', 'QL20171223160745');
INSERT INTO `tb_project_operation_log` VALUES ('23', '2017-12-24 09:53:57', '1', '', '30', '0', 'admin', 'QL20171223160936');
INSERT INTO `tb_project_operation_log` VALUES ('24', '2017-12-24 11:22:52', '1', '', '31', '0', 'admin', 'QL20171223161323');

-- ----------------------------
-- Table structure for tb_project_update_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_project_update_record`;
CREATE TABLE `tb_project_update_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_name` varchar(200) DEFAULT NULL COMMENT '修改人',
  `update_msg` varchar(200) DEFAULT NULL COMMENT '修改信息',
  `project_id` int(11) DEFAULT NULL COMMENT '修改的项目id',
  PRIMARY KEY (`id`),
  KEY `ref_update_pro` (`project_id`),
  CONSTRAINT `ref_update_pro` FOREIGN KEY (`project_id`) REFERENCES `tb_project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='项目修改记录表';

-- ----------------------------
-- Records of tb_project_update_record
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '角色名',
  `is_default` tinyint(4) DEFAULT NULL COMMENT 'is_default',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标识  0为删除 1 删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'admin', '1', '0');
INSERT INTO `tb_role` VALUES ('2', 'responsibler', '1', '0');
INSERT INTO `tb_role` VALUES ('11', '撒大声地', '0', '0');
INSERT INTO `tb_role` VALUES ('12', '无权限管理', '0', '0');

-- ----------------------------
-- Table structure for tb_role_perssion
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_perssion`;
CREATE TABLE `tb_role_perssion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `perssion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_perssion_id` (`perssion_id`),
  KEY `FK_role_id` (`role_id`),
  CONSTRAINT `ref_permission_id` FOREIGN KEY (`perssion_id`) REFERENCES `tb_permission` (`id`),
  CONSTRAINT `ref_role_id` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=latin1 COMMENT='角色~权限中间表\r\n';

-- ----------------------------
-- Records of tb_role_perssion
-- ----------------------------
INSERT INTO `tb_role_perssion` VALUES ('1', '1', '4');
INSERT INTO `tb_role_perssion` VALUES ('2', '1', '5');
INSERT INTO `tb_role_perssion` VALUES ('3', '1', '6');
INSERT INTO `tb_role_perssion` VALUES ('4', '1', '7');
INSERT INTO `tb_role_perssion` VALUES ('5', '1', '8');
INSERT INTO `tb_role_perssion` VALUES ('6', '1', '9');
INSERT INTO `tb_role_perssion` VALUES ('7', '1', '10');
INSERT INTO `tb_role_perssion` VALUES ('8', '1', '11');
INSERT INTO `tb_role_perssion` VALUES ('9', '1', '12');
INSERT INTO `tb_role_perssion` VALUES ('10', '1', '13');
INSERT INTO `tb_role_perssion` VALUES ('11', '1', '14');
INSERT INTO `tb_role_perssion` VALUES ('12', '1', '15');
INSERT INTO `tb_role_perssion` VALUES ('13', '1', '16');
INSERT INTO `tb_role_perssion` VALUES ('14', '1', '17');
INSERT INTO `tb_role_perssion` VALUES ('15', '1', '18');
INSERT INTO `tb_role_perssion` VALUES ('16', '1', '19');
INSERT INTO `tb_role_perssion` VALUES ('17', '2', '4');
INSERT INTO `tb_role_perssion` VALUES ('74', '12', '4');
INSERT INTO `tb_role_perssion` VALUES ('75', '12', '5');
INSERT INTO `tb_role_perssion` VALUES ('82', '1', '38');
INSERT INTO `tb_role_perssion` VALUES ('83', '1', '42');
INSERT INTO `tb_role_perssion` VALUES ('84', '1', '43');
INSERT INTO `tb_role_perssion` VALUES ('85', '1', '44');
INSERT INTO `tb_role_perssion` VALUES ('86', '1', '45');
INSERT INTO `tb_role_perssion` VALUES ('87', '1', '46');
INSERT INTO `tb_role_perssion` VALUES ('88', '1', '47');
INSERT INTO `tb_role_perssion` VALUES ('89', '1', '48');
INSERT INTO `tb_role_perssion` VALUES ('90', '1', '49');
INSERT INTO `tb_role_perssion` VALUES ('91', '1', '50');
INSERT INTO `tb_role_perssion` VALUES ('92', '1', '51');
INSERT INTO `tb_role_perssion` VALUES ('94', '1', '52');
INSERT INTO `tb_role_perssion` VALUES ('95', '1', '53');
INSERT INTO `tb_role_perssion` VALUES ('96', '1', '54');
INSERT INTO `tb_role_perssion` VALUES ('97', '1', '55');
INSERT INTO `tb_role_perssion` VALUES ('98', '1', '20');
INSERT INTO `tb_role_perssion` VALUES ('99', '1', '21');
INSERT INTO `tb_role_perssion` VALUES ('100', '1', '22');
INSERT INTO `tb_role_perssion` VALUES ('107', '1', '24');
INSERT INTO `tb_role_perssion` VALUES ('108', '1', '25');
INSERT INTO `tb_role_perssion` VALUES ('109', '1', '26');
INSERT INTO `tb_role_perssion` VALUES ('110', '1', '27');
INSERT INTO `tb_role_perssion` VALUES ('111', '1', '28');
INSERT INTO `tb_role_perssion` VALUES ('112', '1', '57');
INSERT INTO `tb_role_perssion` VALUES ('113', '1', '58');
INSERT INTO `tb_role_perssion` VALUES ('114', '1', '59');
INSERT INTO `tb_role_perssion` VALUES ('115', '1', '60');
INSERT INTO `tb_role_perssion` VALUES ('116', '1', '61');
INSERT INTO `tb_role_perssion` VALUES ('117', '1', '62');
INSERT INTO `tb_role_perssion` VALUES ('118', '1', '63');
INSERT INTO `tb_role_perssion` VALUES ('119', '1', '64');
INSERT INTO `tb_role_perssion` VALUES ('120', '1', '65');
INSERT INTO `tb_role_perssion` VALUES ('121', '1', '66');
INSERT INTO `tb_role_perssion` VALUES ('122', '1', '67');
INSERT INTO `tb_role_perssion` VALUES ('123', '1', '68');
INSERT INTO `tb_role_perssion` VALUES ('124', '1', '69');
INSERT INTO `tb_role_perssion` VALUES ('125', '11', '4');
INSERT INTO `tb_role_perssion` VALUES ('126', '11', '5');
INSERT INTO `tb_role_perssion` VALUES ('127', '11', '6');
INSERT INTO `tb_role_perssion` VALUES ('128', '11', '20');
INSERT INTO `tb_role_perssion` VALUES ('129', '11', '21');
INSERT INTO `tb_role_perssion` VALUES ('130', '11', '22');
INSERT INTO `tb_role_perssion` VALUES ('131', '11', '38');
INSERT INTO `tb_role_perssion` VALUES ('132', '11', '42');
INSERT INTO `tb_role_perssion` VALUES ('133', '11', '43');

-- ----------------------------
-- Table structure for tb_role_systemuser
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_systemuser`;
CREATE TABLE `tb_role_systemuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`user_id`),
  KEY `FK_Reference_12` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COMMENT='用户~权限中间表';

-- ----------------------------
-- Records of tb_role_systemuser
-- ----------------------------
INSERT INTO `tb_role_systemuser` VALUES ('1', '1', '1');
INSERT INTO `tb_role_systemuser` VALUES ('2', '9', '13');
INSERT INTO `tb_role_systemuser` VALUES ('3', '10', '13');
INSERT INTO `tb_role_systemuser` VALUES ('4', '11', '1');
INSERT INTO `tb_role_systemuser` VALUES ('5', '12', '1');
INSERT INTO `tb_role_systemuser` VALUES ('6', '13', '1');
INSERT INTO `tb_role_systemuser` VALUES ('7', '14', '12');
INSERT INTO `tb_role_systemuser` VALUES ('8', '15', '1');
INSERT INTO `tb_role_systemuser` VALUES ('9', '16', '2');
INSERT INTO `tb_role_systemuser` VALUES ('10', '17', '1');
INSERT INTO `tb_role_systemuser` VALUES ('11', '18', '1');
INSERT INTO `tb_role_systemuser` VALUES ('12', '19', '2');
INSERT INTO `tb_role_systemuser` VALUES ('13', '20', '1');
INSERT INTO `tb_role_systemuser` VALUES ('14', '21', '1');

-- ----------------------------
-- Table structure for tb_short_barge
-- ----------------------------
DROP TABLE IF EXISTS `tb_short_barge`;
CREATE TABLE `tb_short_barge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `short_barge_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '短驳承运方',
  `short_barge_id` int(11) DEFAULT NULL COMMENT '短驳承运方id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `transport_price` decimal(10,0) DEFAULT NULL COMMENT '运输单价',
  `deduction_rate` float DEFAULT NULL COMMENT '扣损比率',
  `deduction_price` decimal(10,0) DEFAULT NULL COMMENT '扣损单价',
  `payment` int(4) DEFAULT NULL COMMENT '付费方式\n            0 现金',
  `type` tinyint(4) DEFAULT NULL COMMENT '短驳承运方式类型\n            0：接取\n            1：送达',
  PRIMARY KEY (`id`),
  KEY `ref_short_barge_pro` (`project_id`),
  CONSTRAINT `ref_short_barge_pro` FOREIGN KEY (`project_id`) REFERENCES `tb_project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1 COMMENT='项目~短驳承运方表';

-- ----------------------------
-- Records of tb_short_barge
-- ----------------------------
INSERT INTO `tb_short_barge` VALUES ('1', '1', 'ww', '99862', '2017-11-07 19:23:11', '2', '2', '2', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('2', '2', 'dd', '99862', '2017-12-14 11:15:18', '3', '3', '3', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('3', '3', '飞凌车队123', '66799', '2017-12-20 14:13:11', '1', '0.5', '12', '0', null);
INSERT INTO `tb_short_barge` VALUES ('4', '5', '悠悠车队', '33315', '2017-12-20 16:34:57', '123', '123', '213', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('5', '12', '悠悠车队', '33315', '2017-12-22 11:51:11', '1', '5', '3', '0', null);
INSERT INTO `tb_short_barge` VALUES ('6', '12', '飞凌车队123', '66799', '2017-12-22 11:51:11', '20', '1', '1', '0', null);
INSERT INTO `tb_short_barge` VALUES ('7', '17', '平台', '99862', '2017-12-22 16:03:39', '12', '23', '231', '0', null);
INSERT INTO `tb_short_barge` VALUES ('8', '18', '平台', '99862', '2017-12-22 16:07:06', '12', '123', '2312', '0', null);
INSERT INTO `tb_short_barge` VALUES ('9', null, '平台', '99862', '2017-12-22 21:45:58', '11', '11', '11', '0', null);
INSERT INTO `tb_short_barge` VALUES ('10', null, '平台', '99862', '2017-12-22 21:55:10', '213', '232', '123', '0', null);
INSERT INTO `tb_short_barge` VALUES ('11', null, '平台', '99862', '2017-12-22 22:04:57', '0', '0', '0', '0', null);
INSERT INTO `tb_short_barge` VALUES ('12', '22', '飞凌车队123', '66799', '2017-12-22 22:15:42', '12', '2', '2', '0', null);
INSERT INTO `tb_short_barge` VALUES ('13', '23', '平台', '99862', '2017-12-22 22:52:20', '0', '0', '0', '0', null);
INSERT INTO `tb_short_barge` VALUES ('14', '24', '平台', '99862', '2017-12-22 22:54:23', '2', '21', '213', '0', null);
INSERT INTO `tb_short_barge` VALUES ('15', '24', '平台', '99862', '2017-12-22 22:54:23', '2', '21', '213', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('16', '25', '平台', '99862', '2017-12-23 14:43:57', '123', '123', '123', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('17', '25', '平台', '99862', '2017-12-23 14:43:57', '123', '123', '123', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('18', '26', '平台', null, '2017-12-23 15:32:19', '0', '0', '0', '0', null);
INSERT INTO `tb_short_barge` VALUES ('19', '27', '平台', null, '2017-12-23 15:33:12', '10', '1', '10', '0', null);
INSERT INTO `tb_short_barge` VALUES ('20', '28', '平台', null, '2017-12-23 15:36:12', '123', '12', '23', '0', null);
INSERT INTO `tb_short_barge` VALUES ('21', '32', '平台', null, '2017-12-23 16:20:59', '12', '23', '23', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('22', '33', '平台', null, '2017-12-23 16:23:37', '0', '0', '0', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('23', '34', '平台', null, '2017-12-23 17:40:31', '123', '1', '1', '0', null);
INSERT INTO `tb_short_barge` VALUES ('24', '35', '平台', null, '2017-12-23 17:41:49', '888', '888', '888', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('25', '36', '平台', null, '2017-12-23 17:50:17', '2', '2', '2', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('26', '37', '平台', null, '2017-12-23 17:52:18', '11', '11', '11', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('27', '37', '平台', '99862', '2017-12-23 17:52:18', '123', '123', '123', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('28', '38', '平台', null, '2017-12-24 10:14:52', '22', '22', '22', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('29', '39', '平台', null, '2017-12-25 12:01:17', '0', '0', '0', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('30', '40', '平台', null, '2017-12-25 12:58:18', '0', '0', '0', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('31', '41', '平台', null, '2017-12-25 12:58:31', '0', '0', '0', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('32', '42', '平台', null, '2017-12-25 12:58:52', '0', '0', '0', '0', '0');
INSERT INTO `tb_short_barge` VALUES ('33', '42', '平台', null, '2017-12-25 12:58:52', '0', '0', '0', '0', '1');
INSERT INTO `tb_short_barge` VALUES ('34', '46', '平台', null, '2017-12-26 17:38:13', null, null, null, '0', '0');
INSERT INTO `tb_short_barge` VALUES ('35', '46', '平台', null, '2017-12-26 17:38:13', null, null, null, '0', '1');

-- ----------------------------
-- Table structure for tb_sms_code_model
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_code_model`;
CREATE TABLE `tb_sms_code_model` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '模板名称',
  `code` varchar(120) DEFAULT NULL COMMENT '模板code',
  `content` varchar(200) DEFAULT NULL COMMENT '模板内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_code_model
-- ----------------------------
INSERT INTO `tb_sms_code_model` VALUES ('1', '测试', 'SMS_CODE_ONE', '大家好，我是好人。嘻嘻嘿嘿');
INSERT INTO `tb_sms_code_model` VALUES ('2', '模板2', 'SMS_CODE_TWO', '我觉得我有必要抢救一下！');
INSERT INTO `tb_sms_code_model` VALUES ('3', '模板3', 'SMS_CODE_TRE', '我是一只消化道，嘻嘻嘻嘻');

-- ----------------------------
-- Table structure for tb_sms_plan
-- ----------------------------
DROP TABLE IF EXISTS `tb_sms_plan`;
CREATE TABLE `tb_sms_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `point` tinyint(4) NOT NULL COMMENT '发送节点\r\n（\r\n   1：   等待调度\r\n   2：等待发运\r\n   3  ：在途运载\r\n   4：货位引导\r\n   5 ：等待回单\r\n   6：等待计费\r\n   7：已完成\r\n）',
  `sms_model_id` int(11) DEFAULT NULL COMMENT '短信模板id',
  `sms_model_code` varchar(200) NOT NULL COMMENT '短信模板code',
  `receive_id` int(11) NOT NULL COMMENT '接受方id',
  `remark` varchar(200) DEFAULT NULL,
  `is_stop` tinyint(4) DEFAULT NULL COMMENT '0:未暂停  \r\n1:已暂停',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sms_plan
-- ----------------------------

-- ----------------------------
-- Table structure for tb_specifications
-- ----------------------------
DROP TABLE IF EXISTS `tb_specifications`;
CREATE TABLE `tb_specifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '规格名',
  `cargo_id` int(11) DEFAULT NULL COMMENT '货物id',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_25` (`cargo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='规格表';

-- ----------------------------
-- Records of tb_specifications
-- ----------------------------
INSERT INTO `tb_specifications` VALUES ('1', '吨', '1');
INSERT INTO `tb_specifications` VALUES ('3', 'g', '1');
INSERT INTO `tb_specifications` VALUES ('4', '121', '9');
INSERT INTO `tb_specifications` VALUES ('5', '23232', '9');
INSERT INTO `tb_specifications` VALUES ('6', '法定', '13');
INSERT INTO `tb_specifications` VALUES ('7', '得到看', '13');
INSERT INTO `tb_specifications` VALUES ('13', 'kg', '21');
INSERT INTO `tb_specifications` VALUES ('14', 'g', '21');
INSERT INTO `tb_specifications` VALUES ('15', 't', '21');
INSERT INTO `tb_specifications` VALUES ('30', 'g', '47');
INSERT INTO `tb_specifications` VALUES ('54', 'd', '46');
INSERT INTO `tb_specifications` VALUES ('55', 's', '46');
INSERT INTO `tb_specifications` VALUES ('56', '袋', '52');
INSERT INTO `tb_specifications` VALUES ('57', '散装', '52');
INSERT INTO `tb_specifications` VALUES ('58', 'xxs', '3');
INSERT INTO `tb_specifications` VALUES ('59', '大', '3');
INSERT INTO `tb_specifications` VALUES ('60', '小', '3');
INSERT INTO `tb_specifications` VALUES ('61', '中', '3');
INSERT INTO `tb_specifications` VALUES ('65', 'kg', '2');
INSERT INTO `tb_specifications` VALUES ('66', 't', '2');
INSERT INTO `tb_specifications` VALUES ('74', '吨', '54');
INSERT INTO `tb_specifications` VALUES ('75', '千克', '54');
INSERT INTO `tb_specifications` VALUES ('76', '袋装', '54');
INSERT INTO `tb_specifications` VALUES ('77', '散装', '54');
INSERT INTO `tb_specifications` VALUES ('80', '散装', '55');
INSERT INTO `tb_specifications` VALUES ('81', '袋', '55');
INSERT INTO `tb_specifications` VALUES ('100', 'fdf', '58');
INSERT INTO `tb_specifications` VALUES ('101', 'cc', '58');
INSERT INTO `tb_specifications` VALUES ('102', 'ku', '58');
INSERT INTO `tb_specifications` VALUES ('107', 'fdf', '63');
INSERT INTO `tb_specifications` VALUES ('108', 'ggg', '63');
INSERT INTO `tb_specifications` VALUES ('111', 'dsd', '64');
INSERT INTO `tb_specifications` VALUES ('112', 'ewe', '64');
INSERT INTO `tb_specifications` VALUES ('116', 'fff', '62');
INSERT INTO `tb_specifications` VALUES ('117', 'yy', '62');
INSERT INTO `tb_specifications` VALUES ('120', 'gg', '65');
INSERT INTO `tb_specifications` VALUES ('121', 'jj', '65');
INSERT INTO `tb_specifications` VALUES ('122', 'uu', '65');

-- ----------------------------
-- Table structure for tb_station_business
-- ----------------------------
DROP TABLE IF EXISTS `tb_station_business`;
CREATE TABLE `tb_station_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `relate_id` int(11) NOT NULL COMMENT '站点或客户id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '姓名',
  `department` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门',
  `job` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '职务',
  `phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '联系方式',
  `relate_project_id` int(11) DEFAULT NULL COMMENT '关联项目id',
  `area_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `location` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '位置',
  `type` tinyint(4) DEFAULT NULL COMMENT '联系人类别\n            1:车站业务联系人\n            2:客户业务联系人',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_19` (`relate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COMMENT='(站点或客户  -业务联系人表)';

-- ----------------------------
-- Records of tb_station_business
-- ----------------------------
INSERT INTO `tb_station_business` VALUES ('10', '2', '石吕飞', null, null, '18715083549', '1', '天津,河东区,null', '18715284343548', '2', '18715284343548');
INSERT INTO `tb_station_business` VALUES ('11', '2', '阿道夫', null, null, '123', '1', '山西,阳泉,城区', 'dsfsd', '2', 'sdf');
INSERT INTO `tb_station_business` VALUES ('12', '2', 'sdfsd', null, null, 'fsdfsdfs', '1', '辽宁,本溪,平山区', 'sdfsd', '2', 'qwwwwwwwww');
INSERT INTO `tb_station_business` VALUES ('14', '8', '1', null, null, '1', '2', '湖南,长沙,岳麓区', '1', '2', '1');
INSERT INTO `tb_station_business` VALUES ('15', '9', '23', null, null, '123', '2', '湖南,长沙,岳麓区', '123', '2', '1232132');
INSERT INTO `tb_station_business` VALUES ('16', '10', 'addUserModal', null, null, 'addUserModal', '2', '湖南,长沙,岳麓区', 'addUserModal', '2', 'addUserModal');
INSERT INTO `tb_station_business` VALUES ('17', '11', 'addUserModal', null, null, 'addUserModal', '1', '湖南,长沙,岳麓区', 'addUserModal', '2', 'addUserModal');
INSERT INTO `tb_station_business` VALUES ('19', '13', 'data.status==200', null, null, 'data.status==200', '1', '湖南,长沙,岳麓区', 'data.status==200', '2', 'data.status==200');
INSERT INTO `tb_station_business` VALUES ('21', '9', 'relateProjectId', null, null, 'relateProjectId', '1', '湖南,长沙,岳麓区', 'relateProjectId', '2', 'relateProjectId');
INSERT INTO `tb_station_business` VALUES ('22', '8', 'relateProjectId', null, null, 'relateProjectId', '2', '湖南,长沙,岳麓区', 'relateProjectId', '2', 'relateProjectId');
INSERT INTO `tb_station_business` VALUES ('23', '14', 'haoma', null, null, '1', '1', '湖南,长沙,岳麓区', '111111111111111', '2', '1');

-- ----------------------------
-- Table structure for tb_stock_charging
-- ----------------------------
DROP TABLE IF EXISTS `tb_stock_charging`;
CREATE TABLE `tb_stock_charging` (
  `id` int(11) NOT NULL,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `site` varchar(200) DEFAULT NULL COMMENT '站点',
  `freight_yard_id` int(11) DEFAULT NULL COMMENT '货场',
  `location_id` int(11) DEFAULT NULL COMMENT '货位',
  `charging_type` varchar(200) DEFAULT NULL COMMENT '计费模式\n            1:重\n            2:位\n            3:天',
  `deduction_pay_id` int(11) DEFAULT NULL COMMENT '扣费账户id',
  `deduction_pay_name` varchar(200) DEFAULT NULL COMMENT '扣费账户名',
  `charging_tonnage` float DEFAULT NULL COMMENT '计费吨位',
  `charging_time_type` tinyint(4) DEFAULT NULL COMMENT '计费时间单位\n            5:月\n            4:周\n            3:天\n            2:时\n            1:分',
  `unit_pay` decimal(10,0) DEFAULT NULL COMMENT '单位金额',
  `late_fee` decimal(10,0) DEFAULT NULL COMMENT '滞纳金',
  `state` tinyint(4) DEFAULT NULL COMMENT '计费状态\n            1:暂停\n            2:取消\n            3:进行中\n            4:完成',
  `operator_id` int(11) DEFAULT NULL COMMENT '操作人',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_30` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='(库存计费)';

-- ----------------------------
-- Records of tb_stock_charging
-- ----------------------------

-- ----------------------------
-- Table structure for tb_system_scene
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_scene`;
CREATE TABLE `tb_system_scene` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scene_name` varchar(200) DEFAULT NULL COMMENT '情景名称',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '关联分支机构id',
  `reason_type` tinyint(4) DEFAULT NULL COMMENT '原因类型',
  `reason_scale` varchar(200) DEFAULT NULL COMMENT '各种原因对应的数值比例',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注说明',
  `status` tinyint(4) DEFAULT NULL COMMENT '删除状态：0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system_scene
-- ----------------------------
INSERT INTO `tb_system_scene` VALUES ('1', 'sss', '1', '1', '6', 'sss', '0');
INSERT INTO `tb_system_scene` VALUES ('2', 'eee', '2', '3', '23', 'eee', '0');
INSERT INTO `tb_system_scene` VALUES ('3', 'fff', '1', null, null, null, '1');
INSERT INTO `tb_system_scene` VALUES ('4', 'dasds', '1', '2', '32', 'ffff', '0');
INSERT INTO `tb_system_scene` VALUES ('5', 'fffwe', '2', '1', '2', 'ffgeeggg', '0');
INSERT INTO `tb_system_scene` VALUES ('6', 'fffwe', '1', '2', '24', 'ffgeegggs', '0');
INSERT INTO `tb_system_scene` VALUES ('7', 'qqq', '2', '1', '3', 'ddd', '0');
INSERT INTO `tb_system_scene` VALUES ('8', 'qqq2', '1', '2', '26', 'ddde', '0');
INSERT INTO `tb_system_scene` VALUES ('9', 'hhhh', '2', '2', '25', 'gg', '1');
INSERT INTO `tb_system_scene` VALUES ('10', 'hhhh2', '2', '3', '54', 'vvvv', '0');

-- ----------------------------
-- Table structure for tb_system_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_user`;
CREATE TABLE `tb_system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL DEFAULT NULL,
  `account` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '账户',
  `passwd` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '密码',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别 1男  0女',
  `is_marry` tinyint(255) DEFAULT NULL,
  `education` tinyint(4) DEFAULT NULL COMMENT '学历',
  `phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构id',
  `email` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '邮箱',
  `area_code` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `work_status` tinyint(4) DEFAULT NULL COMMENT '工作状态 0在职 1 离职',
  `idcard` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '身份证号',
  `start_work_date` datetime DEFAULT NULL,
  `leave_office_date` datetime DEFAULT NULL COMMENT '离职时间',
  `last_login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `company_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ref_branch_group_id` (`branch_group_id`),
  KEY `ref_company_id` (`company_id`),
  CONSTRAINT `ref_company_id` FOREIGN KEY (`company_id`) REFERENCES `tb_anchored_company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1 COMMENT='公司内部用户';

-- ----------------------------
-- Records of tb_system_user
-- ----------------------------
INSERT INTO `tb_system_user` VALUES ('1', '2017-11-23 13:59:47', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '25', '1', '1', '4', '18715083549', '3', '', '安徽,合肥,蜀山区', '蜀山区信旺华府骏苑', null, '0', '340825199409201053', '2017-12-14 10:51:46', '2017-12-30 10:51:50', '2017-12-26 20:51:46', '1');
INSERT INTO `tb_system_user` VALUES ('9', '2017-11-23 13:59:47', '测试1', 'e10adc3949ba59abbe56e057f20f883e', '张三', '21', '1', '0', '1', '18860000000', '1', '', '河北,石家庄,长安区', '', '', '0', '', null, null, '2017-12-09 17:27:09', '1');
INSERT INTO `tb_system_user` VALUES ('10', '2017-11-23 13:59:47', '18715083549', 'e10adc3949ba59abbe56e057f20f883e', '王五', '21', '1', '0', '2', '18715083549', '2', '', '安徽,合肥,蜀山区', '', '', '0', '', null, null, '2017-12-09 17:27:09', '1');
INSERT INTO `tb_system_user` VALUES ('12', '2017-12-11 17:54:46', '18715083541', '18715083541', '石吕飞', '21', '1', '0', '1', '18715083541', '1', '1176525352@qq.com', '河北,石家庄,长安区', '蜀山区信旺华府骏苑', '我的', '1', null, null, null, '2017-12-11 18:36:37', '1');
INSERT INTO `tb_system_user` VALUES ('14', '2017-12-12 11:13:26', '18715083543', 'e10adc3949ba59abbe56e057f20f883e', 'dongsi', '21', '0', '0', '1', '18715083543', '1', '123', '河北,石家庄,长安区', '123', '123', '1', null, null, null, '2017-12-18 19:28:27', '1');
INSERT INTO `tb_system_user` VALUES ('15', '2017-12-16 13:37:41', '18715083548', 'd9483dfef193e4f2c57a7edadced5a91', '石吕飞', '21', '1', '0', '1', '18715083549', '1', '1176525352@qq', '河北,石家庄,长安区', '蜀山区信旺华府骏苑', '1232', '1', null, null, null, '2017-12-18 19:28:27', '1');
INSERT INTO `tb_system_user` VALUES ('16', '2017-12-18 15:06:03', '15956927211', '4e225b271253f5b701dd6e67739dee9f', 'dd', '21', '1', '0', '1', '15956927211', '1', '123', '河北,石家庄,长安区', '123', '123', '1', null, null, null, '2017-12-18 17:48:03', '1');
INSERT INTO `tb_system_user` VALUES ('17', '2017-12-18 18:08:22', '18715083548', 'd9483dfef193e4f2c57a7edadced5a91', '阿道夫', '21', '1', '0', '1', '18715083548', '1', '123', '河北,石家庄,长安区', '123123', '123213', '1', null, null, null, '2017-12-18 19:28:27', '1');
INSERT INTO `tb_system_user` VALUES ('18', '2017-12-18 19:30:30', '18715083547', '0f528f2a048c1808c2b7183baf6978b9', '石吕飞', '21', '1', '0', '1', '18715083547', '1', '1176525352@qq', '河北,石家庄,长安区', '蜀山区信旺华府骏苑', '1232', '0', '', '2017-12-27 00:00:00', null, '2017-12-13 11:36:54', '1');
INSERT INTO `tb_system_user` VALUES ('19', '2017-12-21 13:52:06', '18715085874', '7b1a177a1d7ee5cdf28cf53125b54edd', '石吕飞', '12', '1', '0', '1', '18715085871', '1', '123123', '安徽,合肥,蜀山区', '', null, '0', '123213', '2017-11-28 00:00:00', null, null, '1');
INSERT INTO `tb_system_user` VALUES ('20', '2017-12-25 11:10:06', '15956927213', 'af3a6e7966450d76620a84c9738b0bb1', '2', '20', '0', '0', '3', '15956938680', '20', '2', '安徽,合肥,蜀山区', '22222222222222222', null, '0', '2', '2017-12-25 11:10:06', null, null, '1');
INSERT INTO `tb_system_user` VALUES ('21', '2017-12-25 11:11:59', '15956927213', 'af3a6e7966450d76620a84c9738b0bb1', '3', null, '1', '1', '0', '15956927213', '1', '3', '安徽,合肥,蜀山区', '3333333333333333333333', null, '0', '333', '2018-01-01 00:00:00', null, null, '1');

-- ----------------------------
-- Table structure for tb_train_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_train_order`;
CREATE TABLE `tb_train_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `project_code` varchar(200) DEFAULT NULL COMMENT '项目编号',
  `project_type` tinyint(4) DEFAULT NULL COMMENT '项目类型',
  `branch_id` int(11) DEFAULT NULL COMMENT '分支id',
  `branch_name` varchar(200) DEFAULT NULL COMMENT '分支机构',
  `begin_site` varchar(200) DEFAULT NULL COMMENT '始发站点',
  `begin_place` varchar(200) DEFAULT NULL COMMENT '始发地',
  `end_site` varchar(200) DEFAULT NULL COMMENT '到达站点',
  `end_place` varchar(200) DEFAULT NULL COMMENT '运抵地',
  `please_car_num` varchar(200) DEFAULT NULL COMMENT '请车数量',
  `please_car_type_id` int(11) DEFAULT NULL COMMENT '请车类型id',
  `estimate_weight` varchar(200) DEFAULT NULL COMMENT '预计载重',
  `estimate_cost` varchar(200) DEFAULT NULL COMMENT '预计费用',
  `cargo_name` varchar(200) DEFAULT NULL COMMENT '货物品名',
  `cargo_specifications` varchar(200) DEFAULT NULL COMMENT '规格',
  `advance_charge_account` varchar(200) DEFAULT NULL COMMENT '预付款账户',
  `advance_charge` decimal(10,0) DEFAULT NULL COMMENT '预付款金额',
  `sure_car_num` int(11) DEFAULT NULL COMMENT '承认车辆数',
  `send_date` datetime DEFAULT NULL COMMENT '发运时间',
  `send_operator_id` varchar(200) DEFAULT NULL COMMENT '发运操作员',
  `update_location_id` int(11) DEFAULT NULL COMMENT '更新位置id',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型\n            0:集装箱\n            1:散堆装',
  `please_train_number` varchar(200) DEFAULT NULL COMMENT '请车单号',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '运单状态:0:取消,1:等待承认,2:等待装车,3:等待发运,4:在途运载,5:等待卸货,6:等待回单,7:已完成',
  `update_date` datetime DEFAULT NULL COMMENT '运单更新时间',
  `entruck_date` datetime DEFAULT NULL COMMENT '装车时间',
  `lose_car_num` int(11) DEFAULT NULL COMMENT '落车数',
  `is_exception` tinyint(4) DEFAULT NULL COMMENT '是否异常',
  `exception_reason` varchar(200) DEFAULT NULL COMMENT '异常原因',
  `exception_report_date` datetime DEFAULT NULL COMMENT '异常上报时间',
  `exception_report_person` varchar(200) DEFAULT NULL COMMENT '异常上报人',
  `delete_date` datetime DEFAULT NULL COMMENT '删除时间',
  `delete_person` varchar(200) DEFAULT NULL COMMENT '删除操作人',
  `delete_reason` varchar(200) DEFAULT NULL COMMENT '删除原因',
  `artificial_report_id` int(4) DEFAULT NULL COMMENT '人工上报异常id',
  `entruck_numbe` int(4) DEFAULT NULL COMMENT '装车数',
  `container_nums` int(4) DEFAULT NULL COMMENT '集装箱数',
  `entruck_weight` decimal(10,0) DEFAULT NULL COMMENT '装载吨位',
  `arrive_weight` decimal(10,0) DEFAULT NULL COMMENT '到货载重',
  `arrive_date` datetime DEFAULT NULL COMMENT '到货时间',
  `delete_flag` tinyint(4) DEFAULT NULL COMMENT '删除标志:1已删除,0:未删除',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_40` (`please_car_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='(火运订单表)';

-- ----------------------------
-- Records of tb_train_order
-- ----------------------------
INSERT INTO `tb_train_order` VALUES ('1', '1', '222', '1', '222', 'vvvv', 'fff', 'fvvv', 'ffvgg', ' vv vcc', '13', '1', '23', '24', 'vvv', 'ff', '233', '244', null, '2017-12-18 14:09:47', 'admin', '2', '0', '20', '2017-12-18 14:10:07', '1', '2017-12-22 11:00:21', '2017-12-22 11:00:32', null, '0', null, null, null, '2017-12-25 14:28:20', 'admin', '', null, null, null, null, null, null, '1');
INSERT INTO `tb_train_order` VALUES ('2', '1', '333', '1', '33', 'ddd', 'cvcvc', 'vcvc', 'bnbn', 'nbnb', '20', null, null, null, null, null, null, null, '18', '2017-12-24 15:16:50', 'admin', null, '0', '1000001', '2017-12-22 16:11:46', '4', '2017-12-24 15:17:42', '2017-12-24 15:16:03', null, '0', '错误', '2017-12-18 15:11:20', 'dafei', null, null, null, null, '1', '2', '1', null, null, '0');
INSERT INTO `tb_train_order` VALUES ('5', '21', 'QL20171222220457', '0', '1', '合肥分支中心', '三级站肥西', '北京市', '三级站肥西,北京市', '', '3', null, null, '', '煤', '吨', '2000', '123456789', '2', '2017-12-26 16:29:48', 'admin', null, '0', '111', '2017-12-25 13:18:20', '4', '2017-12-26 16:29:48', '2017-12-26 16:03:22', null, '0', null, null, null, null, null, null, null, '4', '24', '60', null, null, '0');
INSERT INTO `tb_train_order` VALUES ('6', '40', 'QL20171225125817', '1', '1', '合肥分支中心', '三级站肥西', '北京市', '三级站肥西,北京市', '', '3', null, null, '', '煤', '吨', '2000', '123456789', '2', '2017-12-25 18:06:47', 'admin', null, '1', 'ZXC987456', '2017-12-25 18:03:35', '4', '2017-12-25 19:20:32', '2017-12-25 18:06:12', null, '0', null, null, null, null, null, null, null, '2', null, '20', null, null, '0');
INSERT INTO `tb_train_order` VALUES ('7', '40', 'QL20171225125817', '1', '1', '合肥分支中心', '三级站肥西', '北京市', '三级站肥西,北京市', '', '44', null, null, '', '煤', '吨', '2000', '123456789', null, null, 'admin', null, '1', '123456', '2017-12-25 19:21:20', '1', null, null, null, '0', null, null, null, '2017-12-25 19:27:59', 'admin', '测试', null, null, null, null, null, null, '1');
INSERT INTO `tb_train_order` VALUES ('8', '24', 'QL20171222225423', '0', '1', '合肥分支中心', '三级站肥西', '北京市xx', '三级站肥西,北京市cc', '', '5', null, null, '', '煤', '吨', '123456789', '2000', '3', null, 'admin', null, '0', '654321QWE', '2017-12-26 16:05:27', '3', '2017-12-26 16:08:20', '2017-12-26 16:08:20', null, '0', null, null, null, null, null, null, null, '3', '32', '50', null, null, '0');
INSERT INTO `tb_train_order` VALUES ('9', '35', 'QL20171223174141', '0', '1', '合肥分支中心', '三级站肥西', '北京市', '三级站肥西,北京市', '', '3', null, null, '', '煤', '吨', '123456789', '2000', null, null, 'admin', null, '0', 'PLC201712261630192511', '2017-12-26 16:30:19', '1', null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for tb_train_order_cargo_palce
-- ----------------------------
DROP TABLE IF EXISTS `tb_train_order_cargo_palce`;
CREATE TABLE `tb_train_order_cargo_palce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `train_order_id` int(11) DEFAULT NULL COMMENT '火运订单id',
  `cargo_place_id` int(11) DEFAULT NULL COMMENT '货场id',
  `cargo_place_name` varchar(200) DEFAULT NULL COMMENT '货场',
  `cargo_site_id` int(11) DEFAULT NULL COMMENT '货位id',
  `cargo_site_name` varchar(200) DEFAULT NULL COMMENT '货位',
  `car_type_id` int(11) DEFAULT NULL COMMENT '车型id',
  `car_type` varchar(200) DEFAULT NULL COMMENT '车型',
  `car_number` varchar(200) DEFAULT NULL COMMENT '车牌号',
  `container_number1` varchar(200) DEFAULT NULL COMMENT '集装箱号1',
  `container_number2` varchar(200) DEFAULT NULL COMMENT '集装箱号2',
  `send_weight` float DEFAULT NULL COMMENT '发货载重',
  `unload_weight` float DEFAULT NULL COMMENT '到货载重',
  `send_img` varchar(200) DEFAULT NULL COMMENT '运单上传',
  `unload_img` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '到货运单上传',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`train_order_id`),
  KEY `FK_Reference_32` (`car_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 COMMENT='货场货位车辆信息和 火运订单关联表';

-- ----------------------------
-- Records of tb_train_order_cargo_palce
-- ----------------------------
INSERT INTO `tb_train_order_cargo_palce` VALUES ('1', '1', '2', 'hhh', '1', 'ggg', '1', 'bvbv', '33', '444', '333', '45', null, '', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('2', '2', '1236', '1234', '4568', '4567', null, 'AAAAA', '123445', 'ASDSDSDS', 'SDDSSASDD', '20', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('3', '2', '1236', '1234', '4568', '4567', null, 'AAAAA', '123456', 'SDASSDA', 'SDsdS', '20', null, '815388.png', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('4', '2', '1236', '1234', '4568', '4567', null, 'BBBBB', '5422412', 'VFVSDFS', 'DFSSD', '30', null, '466443.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('5', '2', '1236', '1234', '4568', '4567', null, 'AAAA', '12345', 'SWSSS', 'FDDD', '20', null, '689767.png', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('6', '2', '1236', '1234', '4568', '4567', null, 'AAAA', '4343', 'fdfsd', 'gfgds', '20', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('7', '2', '1236', '1234', '4568', '4567', null, 'BBBB', '5523243', 'FSFs', 'dfgsdf', '30', null, '505514.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('8', '2', '777', '7778', '6699', '6699', null, 'BBBB', '242342', 'ffddd', 'sds', '30', null, '338467.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('9', '2', '777', '7778', '6699', '6699', null, 'ccccc', '2152744', 'FGFG', 'RWERWE', '100', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('10', '2', '1236', '1234', '4568', '4567', null, 'SSS', '23123', 'DDSSS', 'DDSFC', '1', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('11', '6', '1236', '1234', '4568', '4567', null, 'AAA', '12345', null, null, '10', null, '857411.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('12', '6', '1236', '1234', '4568', '4567', null, 'BBB', '45321', null, null, '10', null, '298236.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('13', '5', '123', '123', '456', '456', null, 'ASD', '1111', 'AASS1', 'AASS2', '20', null, '538905.jpg', null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('14', '5', '123', '123', '456', '456', null, 'SDF', '222', 'SSDD1', 'SASD2', '20', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('15', '5', '123', '123', '456', '456', null, 'DFDF', '333', 'CCC1', 'CCC2', '20', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('16', '8', '123', '123', '456', '456', null, 'vvv', '77', 'yyyyy1', 'yyyyy2', '10', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('17', '8', '123', '123', '456', '456', null, 'bbb', '88', 'uuuu1', 'uuuu2', '10', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('18', '8', '123', '123', '456', '456', null, 'nnn', '99', 'iiii1', 'iiii2', '10', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('19', '8', '777', '7778', '6699', '6699', null, 'mmm', '44', 'ooo1', 'ooo2', '10', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('20', '8', '777', '7778', '6699', '6699', null, 'zzz', '55', 'ppp1', 'ppp2', '10', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('21', '2', '1236', '1234', '4568', '4567', null, 'SSS', '22', 'SDS', 'SDSS', '22', null, null, null);
INSERT INTO `tb_train_order_cargo_palce` VALUES ('22', '2', '1236', '1234', '4568', '4567', null, 'fgf', '4', 'gfg', 'fgd', '4', null, null, null);

-- ----------------------------
-- Table structure for tb_train_station
-- ----------------------------
DROP TABLE IF EXISTS `tb_train_station`;
CREATE TABLE `tb_train_station` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `station_name` varchar(200) DEFAULT NULL COMMENT '站点名称',
  `station_level` tinyint(4) DEFAULT NULL COMMENT '站点级别\n            0:铁路局\n            1:货运总局\n            2:营业厅',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级站点',
  `responsibler` varchar(200) DEFAULT NULL COMMENT '负责人',
  `adress_code` varchar(200) DEFAULT NULL COMMENT '企业地址',
  `detail_address` varchar(200) DEFAULT NULL COMMENT '企业详细地址',
  `station_contacts` varchar(200) DEFAULT NULL COMMENT '站点联系人',
  `department` varchar(200) DEFAULT NULL COMMENT '部门',
  `station_phone` varchar(200) DEFAULT NULL COMMENT '站点联系方式',
  `station_fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `bank_account` varchar(200) DEFAULT NULL COMMENT '银行账户',
  `account_name` varchar(200) DEFAULT NULL COMMENT '户名',
  `open_bank` varchar(200) DEFAULT NULL COMMENT '开户行',
  `open_bank_num` varchar(200) DEFAULT NULL COMMENT '行号',
  `duty_paragraph` varchar(200) DEFAULT NULL COMMENT '税号',
  `bank_last_amount` decimal(10,2) DEFAULT NULL COMMENT '余额',
  `delete_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='火车站点';

-- ----------------------------
-- Records of tb_train_station
-- ----------------------------
INSERT INTO `tb_train_station` VALUES ('1', 'AAAAA', '0', null, '1', '1', '1', '1', '财务', '11', '1', '1', '1', '1', '1', '1', '1', '1000.00', '0');
INSERT INTO `tb_train_station` VALUES ('2', 'BBBBB', '1', null, '2', '2', '北京市', '2', '2', '2', '2', '2', '2', '1', '2', '2', '2', '2000.00', '0');
INSERT INTO `tb_train_station` VALUES ('5', 'CCCCC', '2', null, '123', '012', '123', '123', '财务', '123', '123', '123', '123', '123', '123', '123', '123', null, '0');
INSERT INTO `tb_train_station` VALUES ('6', 'eeeeee', '0', null, 'eee', '上海黄浦区null', 'cccccccccccccccccccccc', 'eee', '财务', 'eee', 'eeee', 'eeee', 'WKGNAPDAJ15645453', '123456', '1234', '4513', '132', '0.00', '0');

-- ----------------------------
-- Table structure for tb_train_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_train_type`;
CREATE TABLE `tb_train_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `train_kind` varchar(200) DEFAULT NULL COMMENT '车种',
  `train_kind_code` varchar(200) DEFAULT NULL COMMENT '车种代码',
  `train_type_code` varchar(200) DEFAULT NULL COMMENT '车型代码',
  `self_weight` float DEFAULT NULL COMMENT '自重',
  `weight` float DEFAULT NULL COMMENT '载重',
  `volume` int(11) DEFAULT NULL COMMENT '容积',
  `load_price` float DEFAULT NULL COMMENT '计费载重',
  `length` varchar(200) DEFAULT NULL COMMENT '长度',
  `width` varchar(200) DEFAULT NULL COMMENT '宽度',
  `hight` varchar(200) DEFAULT NULL COMMENT '高度',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COMMENT='火车车型\r\n';

-- ----------------------------
-- Records of tb_train_type
-- ----------------------------
INSERT INTO `tb_train_type` VALUES ('1', 'dasd', 'das', 'da', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('3', 'ff', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5');
INSERT INTO `tb_train_type` VALUES ('4', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2');
INSERT INTO `tb_train_type` VALUES ('5', 'dd', '1', '1', '2', '3', '3', '3', '3', '3', '3', '3');
INSERT INTO `tb_train_type` VALUES ('6', '123', 'x', 'x', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('7', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8');
INSERT INTO `tb_train_type` VALUES ('9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9');
INSERT INTO `tb_train_type` VALUES ('10', '10', '10', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('11', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('12', null, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('13', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3');
INSERT INTO `tb_train_type` VALUES ('14', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3');
INSERT INTO `tb_train_type` VALUES ('15', 'AAA', 'AAA', 'AAA', '123', '456', '123', '456', '123', '456', '123', '456123');
INSERT INTO `tb_train_type` VALUES ('16', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3');
INSERT INTO `tb_train_type` VALUES ('17', 'sss', 'x', 'ss', '232', '2324', '241', '332', '324424', '321414', '32432432', '11111111111111111111');
INSERT INTO `tb_train_type` VALUES ('18', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5');
INSERT INTO `tb_train_type` VALUES ('19', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7');
INSERT INTO `tb_train_type` VALUES ('20', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8');
INSERT INTO `tb_train_type` VALUES ('21', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9');
INSERT INTO `tb_train_type` VALUES ('22', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `tb_train_type` VALUES ('23', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2');

-- ----------------------------
-- Table structure for tb_user_anchored_company
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_anchored_company`;
CREATE TABLE `tb_user_anchored_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `anchored_company_id` int(11) DEFAULT NULL COMMENT '挂靠公司id',
  `anchored_date` datetime DEFAULT NULL,
  `is_stop` tinyint(11) DEFAULT NULL COMMENT '0：默认开启    1：暂停接单',
  PRIMARY KEY (`id`),
  KEY `FK_company_id` (`anchored_company_id`),
  KEY `FK_user_id` (`user_id`),
  CONSTRAINT `FK_company_id` FOREIGN KEY (`anchored_company_id`) REFERENCES `tb_anchored_company` (`id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_login_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COMMENT='用户(车队和个人)~挂靠公司中间表';

-- ----------------------------
-- Records of tb_user_anchored_company
-- ----------------------------
INSERT INTO `tb_user_anchored_company` VALUES ('7', '61', '1', '2017-11-07 11:12:21', null);
INSERT INTO `tb_user_anchored_company` VALUES ('8', '82308', '1', '2017-11-07 11:12:21', null);
INSERT INTO `tb_user_anchored_company` VALUES ('9', '5077', '1', '2017-11-07 11:12:21', null);
INSERT INTO `tb_user_anchored_company` VALUES ('10', '5077', '1', '2017-11-07 11:12:21', null);
INSERT INTO `tb_user_anchored_company` VALUES ('11', '5077', '1', '2017-11-07 11:12:21', null);

-- ----------------------------
-- Table structure for tb_user_branch_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_branch_group`;
CREATE TABLE `tb_user_branch_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) DEFAULT NULL COMMENT '公司内部用户id',
  `branch_group_id` int(11) DEFAULT NULL COMMENT '分支机构id',
  `type` tinyint(4) DEFAULT '1' COMMENT '类型 1个人 2 负责人',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_15` (`branch_group_id`),
  KEY `FK_Reference_16` (`user_id`),
  CONSTRAINT `ref_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_system_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1 COMMENT='用户~分支机构中间表';

-- ----------------------------
-- Records of tb_user_branch_group
-- ----------------------------
INSERT INTO `tb_user_branch_group` VALUES ('3', '1', '2', '1');
INSERT INTO `tb_user_branch_group` VALUES ('12', '10', '2', '2');
INSERT INTO `tb_user_branch_group` VALUES ('13', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('14', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('15', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('16', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('17', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('18', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('19', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('20', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('21', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('22', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('23', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('24', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('25', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('26', null, null, '2');
INSERT INTO `tb_user_branch_group` VALUES ('27', null, '18', '2');
INSERT INTO `tb_user_branch_group` VALUES ('29', '19', '16', '2');
INSERT INTO `tb_user_branch_group` VALUES ('31', '19', '19', '2');
INSERT INTO `tb_user_branch_group` VALUES ('33', '19', '1', '2');
INSERT INTO `tb_user_branch_group` VALUES ('42', '19', '20', '2');

-- ----------------------------
-- Table structure for tb_user_information
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_information`;
CREATE TABLE `tb_user_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主姓名',
  `birthday` datetime DEFAULT NULL COMMENT '车主出生年月',
  `area_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主住址code',
  `address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主详细地址',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `bank_card_number` varchar(200) CHARACTER SET utf8 DEFAULT '' COMMENT '银行卡号',
  `id_card_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主身份证号',
  `id_card_img_front` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主身份证号正面图片',
  `id_card_img_back` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主身份证号反面图片',
  `phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车主手机号',
  `driver_name` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员姓名',
  `driver_birthday` datetime DEFAULT NULL COMMENT '驾驶员出生年月',
  `driver_area_code` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员住址',
  `driver_sex` tinyint(4) DEFAULT NULL COMMENT '驾驶员性别',
  `driver_address` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员详细地址',
  `driver_bank_card_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员银行卡号',
  `driver_id_card_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员身份证号',
  `driver_id_card_img_front` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员身份证正面图片',
  `driver_id_card_img_back` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员身份证反面图片',
  `driver_phone` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶员手机号',
  `travel_licence_img_front` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '行驶证正面\n            ',
  `travel_licence_img_back` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '行驶证副面',
  `driver_licence_type` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶证类型',
  `driver_age` int(11) DEFAULT NULL COMMENT '驾龄',
  `driver_licence_img_front` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶证正面图片',
  `driver_licence_img_back` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '驾驶证副面图片',
  `plate_number` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车牌号',
  `brand` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车的品牌',
  `model` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车的型号',
  `motorcycle_type` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车的车型',
  `car_photo1` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆照片1',
  `car_photo2` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆照片2',
  `buy_date` datetime DEFAULT NULL COMMENT '购置时间',
  `insurance_begin_date` datetime DEFAULT NULL COMMENT '保险开始时间',
  `insurance_end_date` datetime DEFAULT NULL COMMENT '保险结束时间',
  `insurance_file_photo1` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '保险资料1',
  `insurance_file_photo2` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '保险资料2',
  `car_state` int(11) DEFAULT NULL COMMENT '车辆状态',
  `car_property` int(11) DEFAULT NULL COMMENT '车辆性质\n            0 他人\n            1 自有',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `total_mileage` float DEFAULT NULL COMMENT '总里程',
  `total_time` float DEFAULT NULL COMMENT '总时间',
  `total_order` int(11) DEFAULT NULL COMMENT '总运单数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=992347 DEFAULT CHARSET=latin1 COMMENT='用户详情表';

-- ----------------------------
-- Records of tb_user_information
-- ----------------------------
INSERT INTO `tb_user_information` VALUES ('56', 'admin', '2017-11-23 10:49:57', '123', 'asd', '1', '', '342522155846484111', '535125.png', '610513.png', '1886465645', 'admin', '2017-11-07 00:00:00', '安徽省合肥市蜀山区', '1', '信旺华府骏苑', null, '345844555666699999', '884023.png', '693443.png', '15584446698', '699048.png', '483512.png', 'A1驾照', '0', '557531.png', '117260.png', '皖H78392', '解放牌', '1', '一汽解放J9L大卡', '898892.png', '840841.png', '2017-11-23 00:00:00', '2017-11-03 00:00:00', '2017-11-24 00:00:00', '246226.png', '425102.png', '1', '1', '2017-11-23 10:52:28', '12', '23', '1');
INSERT INTO `tb_user_information` VALUES ('57', '小李', '2017-11-23 10:49:57', '123', 'asd', '1', '', '342522155846484111', '/img/haha.jgp', '/img/hahaha.jgp', '1886465645', '小李', '2017-11-07 10:51:07', '安徽省合肥市蜀山区', '1', '信旺华府骏苑', null, '345844555666699999', '/img/font/x.jpg', '/img/font/y.jpg', '15584446698', '/img/font/x.jpg', '/img/font/x.jpg', 'A1驾照', '8', '/img/font/x.jpg', '/img/font/x.jpg', '皖H78396', '解放牌', '一汽解放J9L小卡', '1', '1', '1', '2017-11-23 10:52:08', '2017-11-03 10:52:16', '2017-11-24 10:52:18', '1ds564', 'das15', '0', '0', '2017-11-23 10:52:28', '13', '24', '2');
INSERT INTO `tb_user_information` VALUES ('58', '阿狗', '2017-11-23 10:49:57', '123', 'wwe', '1', '22344444445555551', '342522155846484111', '117260.png', '117260.png', '18110275652', '阿狗', '2012-10-05 00:00:00', '安徽省合肥市蜀山区', '1', '丁怡花园5幢', null, '35082519940852', '858008.png', '630775.png', '15355131788', '101492.png', '709332.png', 'A1驾照', '0', '557531.png', '117260.png', '皖H78394', '解放牌', '1', '一汽解放J9L大卡', '117260.png', '117260.png', '2017-11-23 00:00:00', '2017-11-03 00:00:00', '2017-11-24 00:00:00', '898892.png', '898892.png', '0', '1', '2017-11-23 10:52:28', '12', '23', '3');
INSERT INTO `tb_user_information` VALUES ('112053', '', null, '', '', '1', '', '', '887047.png', '322125.png', '', '', null, '', '1', '', null, '', '887047.png', '322125.png', '', '353439.png', '116116.png', '', '0', '371313.png', '109928.png', '', '', '', '', '122663.png', '540824.png', null, null, null, '634058.png', '797588.png', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('123342', '20171201', '2017-12-19 00:00:00', '安徽省/合肥市/蜀山区', '20171201,A1驾照', '1', '20171201', '20171201', '861959.png', '718585.png', '15155789632', '20171201', '2017-12-01 00:00:00', '安徽省/合肥市/蜀山区', '1', '20171201', null, '20171201', '561841.png', '307978.png', '20171201', '675446.png', '484256.png', null, '0', '516227.png', '589089.png', '20171201', '20171201', '20171201', '20171201', '233900.png', '102224.png', '2017-11-29 00:00:00', '2017-12-05 00:00:00', '2017-12-28 00:00:00', '101304.png', '555431.png', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('175328', '测试123', '2017-10-31 00:00:00', '安徽省/合肥市/瑶海区', '测试123,测试123', '1', '186746876874', '测试123', '234892.png', '812413.png', '18715085468', '测试123', '2017-10-31 00:00:00', '安徽省/合肥市/瑶海区', '1', '测试123,测试123', null, '测试123', '234892.png', '812413.png', '18715085468', '440848.png', '117025.png', null, '0', '576355.png', '852550.png', '测试123', '测试123', '测试123', '测试123', '273553.png', '862118.png', '2017-10-31 00:00:00', '2017-11-07 00:00:00', '2017-11-25 00:00:00', '435659.png', '804225.png', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('371025', '林大量', '2017-12-27 00:00:00', '广东省/江门市/恩平市', '芜湖路交叉口', '1', '132454654654654624664565', '342522166688554444', '383086.jpg', '809196.jpg', '18860486308', '林大量', '2017-12-27 00:00:00', '广东省/江门市/恩平市', '1', '芜湖路交叉口', null, '342522166688554444', '383086.jpg', '809196.jpg', '18860486308', '842167.jpg', '417591.jpg', 'B2', '0', '521883.jpg', '446350.png', '皖N:6844845', '宝马E', '001', '轿跑', '431544.jpg', '208412.jpg', '2017-12-07 00:00:00', '2017-11-29 00:00:00', '2017-12-23 00:00:00', '766640.jpg', '742575.jpg', null, '1', null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('411859', '18715083549', '2017-12-04 00:00:00', '安徽省/合肥市/瑶海区', '18715083549', '1', '18715083549', '18715083549', '770045.png', '150315.png', '18715083549', '18715083549', '2017-12-04 00:00:00', '安徽省/合肥市/瑶海区', '1', '18715083549', null, '18715083549', '770045.png', '150315.png', '18715083549', '500391.png', '604438.png', '18715083549', '0', '346062.png', '840384.png', '18715083549', '18715083549', '18715083549', '18715083549', '473025.png', '622430.png', '2017-11-07 00:00:00', '2017-12-03 00:00:00', '2017-12-06 00:00:00', '974924.png', '679124.png', null, '1', null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('574697', '驾驶员是我', '2017-11-21 00:00:00', '安徽省/合肥市/瑶海区', '火车站旁', '0', '351674684654845', '340825199409201052', '', '', '15755137199', '驾驶员是我', '2017-11-21 00:00:00', '安徽省/合肥市/瑶海区', '0', '火车站旁', null, '340825199409201052', '', '', '15755137199', null, null, null, null, '', ',', '皖H58497', '大奔', 'H1', '超大', '', '', '2017-11-07 00:00:00', '2012-06-05 00:00:00', '2017-11-10 00:00:00', '', '', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('722895', '1575578950', '2017-12-04 00:00:00', '安徽省/芜湖市/镜湖区', '1575578950', '1', '1575578950', '1575578950', '429972.png', '193662.png', '1575578950', '1575578950', '2017-12-04 00:00:00', '安徽省/芜湖市/镜湖区', '1', '1575578950', null, '1575578950', '429972.png', '193662.png', '1575578950', '740920.png', '662791.png', '1575578950', '0', '803982.png', '952483.png', '1575578950', '1575578950', '1575578950', '1575578950', '418556.png', '419776.png', '2017-12-05 00:00:00', '2017-11-28 00:00:00', '2017-12-04 00:00:00', '204979.png', '518800.png', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('897631', '18715083549', '2017-12-13 00:00:00', '安徽省/合肥市/瑶海区', '18715083549', '1', '18715083549', '18715083549', '172609.png', '234979.png', '18715083549', '18715083549', '2017-12-13 00:00:00', '安徽省/合肥市/瑶海区', '1', '18715083549', null, '18715083549', '172609.png', '234979.png', '18715083549', '128938.png', '356360.png', '18715083549', '0', '865999.png', '514907.png', '18715083549', '18715083549', '18715083549', '18715083549', '357074.png', '147231.png', '2017-11-28 00:00:00', '2017-11-30 00:00:00', '2017-12-14 00:00:00', '524454.png', '252052.png', null, '1', null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('992345', '我是车主', '2017-11-01 00:00:00', '甘肃省/兰州市/城关区', 'hahaha,A1驾照', '1', '15454875465', '154875498754454', '', '', '15755137194', '我是代驾', '2017-11-21 00:00:00', '安徽省/合肥市/蜀山区', '1', '信旺华府俊园', null, '15456487879', '', null, '15487879797', '', '', null, null, '', ',', '皖A112323', '保时捷', '7Y', 'H1', '', '', '2017-11-06 00:00:00', '2017-11-01 00:00:00', '2017-11-11 00:00:00', '', '', null, null, null, null, null, null);
INSERT INTO `tb_user_information` VALUES ('992346', '个人-A测试', '2017-12-14 00:00:00', '安徽省/芜湖市/弋江区', '小吃街', '1', '34251888888888888', '34251888888888888', '286053.jpg', '885101.jpg', '18355196932', '个人-A测试', '2017-12-14 00:00:00', '安徽省/芜湖市/弋江区', '1', '小吃街', null, '34251888888888888', '286053.jpg', '885101.jpg', '18355196932', '231081.png', '434032.jpg', 'C照', '0', '761493.jpg', '898776.jpg', 'WB-666', '宝马', '不知道', '好吃', '802627.jpg', '915746.jpg', '2017-12-06 00:00:00', '2017-11-28 00:00:00', '2017-11-29 00:00:00', '818297.jpg', '604646.jpg', null, '1', null, null, null, null);
