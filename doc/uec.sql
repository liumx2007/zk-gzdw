/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50150
Source Host           : 127.0.0.1:3306
Source Database       : uec

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2018-10-31 11:44:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_answer
-- ----------------------------
DROP TABLE IF EXISTS `tb_answer`;
CREATE TABLE `tb_answer` (
  `id` varchar(50) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `is_right` int(2) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `subject_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK406FB6AFC1EA2A1D` (`subject_id`),
  CONSTRAINT `FK_SUBJECT` FOREIGN KEY (`subject_id`) REFERENCES `tb_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_answer
-- ----------------------------

-- ----------------------------
-- Table structure for tb_arrange_date
-- ----------------------------
DROP TABLE IF EXISTS `tb_arrange_date`;
CREATE TABLE `tb_arrange_date` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `arrange_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_arrange_date
-- ----------------------------
INSERT INTO `tb_arrange_date` VALUES ('01dc8b8e-e56b-48c7-9f99-f8ba94217b7d', '2018-10-14');
INSERT INTO `tb_arrange_date` VALUES ('313f0d46-c53b-4516-881f-e4a981c4b70a', '2018-10-09');
INSERT INTO `tb_arrange_date` VALUES ('373fb24d-4d74-4be7-a05a-b74dbf04dbba', '2018-10-18');
INSERT INTO `tb_arrange_date` VALUES ('43dfd7f3-a5d6-4c75-b25a-432c3be13085', '2018-10-17');
INSERT INTO `tb_arrange_date` VALUES ('485e2577-5767-4246-997a-2634b0d5e2ef', '2018-10-13');
INSERT INTO `tb_arrange_date` VALUES ('6ad90cb5-3819-4870-8f74-1aa89bcc7013', '2018-10-15');
INSERT INTO `tb_arrange_date` VALUES ('79c7d938-ba63-4675-8227-fe66f9763b84', '2018-10-08');
INSERT INTO `tb_arrange_date` VALUES ('7aa5c7a8-101b-4b09-bb87-e7314e106d62', '2018-10-12');
INSERT INTO `tb_arrange_date` VALUES ('8134c34a-bb24-46bf-b3e1-9932c28196d1', '2018-10-16');
INSERT INTO `tb_arrange_date` VALUES ('9e00d9e6-4354-4ea7-8599-191e1297a945', '2018-10-11');
INSERT INTO `tb_arrange_date` VALUES ('b749ea29-055f-4c66-869c-7d5abed06c29', '2018-10-10');

-- ----------------------------
-- Table structure for tb_arrange_date_detials
-- ----------------------------
DROP TABLE IF EXISTS `tb_arrange_date_detials`;
CREATE TABLE `tb_arrange_date_detials` (
  `tb_arrange_date` varchar(255) NOT NULL,
  `detials` varchar(255) NOT NULL,
  PRIMARY KEY (`tb_arrange_date`,`detials`),
  UNIQUE KEY `detials` (`detials`),
  KEY `FK76C81A858116AE6F` (`tb_arrange_date`),
  KEY `FK76C81A8524486D02` (`detials`),
  CONSTRAINT `FK76C81A8524486D02` FOREIGN KEY (`detials`) REFERENCES `tb_arrange_detial` (`id`),
  CONSTRAINT `FK76C81A858116AE6F` FOREIGN KEY (`tb_arrange_date`) REFERENCES `tb_arrange_date` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_arrange_date_detials
-- ----------------------------

-- ----------------------------
-- Table structure for tb_arrange_detial
-- ----------------------------
DROP TABLE IF EXISTS `tb_arrange_detial`;
CREATE TABLE `tb_arrange_detial` (
  `id` varchar(50) NOT NULL,
  `person_id` varchar(500) DEFAULT NULL,
  `position` int(8) DEFAULT NULL,
  `arrange_date_id` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `updata_time` timestamp NULL DEFAULT NULL,
  `part` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF29CB456B2B9996` (`arrange_date_id`),
  CONSTRAINT `FKCF29CB456B2B9996` FOREIGN KEY (`arrange_date_id`) REFERENCES `tb_arrange_date` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_arrange_detial
-- ----------------------------
INSERT INTO `tb_arrange_detial` VALUES ('00d99cf4-8db4-4403-b673-acfb27bfdd3b', '0ea91b02-042e-403f-803c-205809d4f97a', '1', '79c7d938-ba63-4675-8227-fe66f9763b84', '2018-10-08 16:44:24', null, '1', '');
INSERT INTO `tb_arrange_detial` VALUES ('27b26f19-8ad2-464e-bcbc-bb74e8014c32', '20267e0e-5112-4e5b-a346-84e3b9809b95', '2', '79c7d938-ba63-4675-8227-fe66f9763b84', '2018-10-08 16:44:40', null, '2', '');
INSERT INTO `tb_arrange_detial` VALUES ('8a5a6550-f3fa-4f21-96cd-725b492540f6', '0683efa6-e133-4e86-bd3c-9e7aa6dd6b35', '0', '79c7d938-ba63-4675-8227-fe66f9763b84', '2018-10-08 16:42:41', null, '0', '');

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `folder_id` varchar(50) DEFAULT NULL,
  `sort` int(8) DEFAULT NULL COMMENT '排序',
  `serial_number` int(8) DEFAULT NULL COMMENT '编号',
  `title` varchar(100) DEFAULT NULL,
  `file_name` varchar(1000) DEFAULT NULL COMMENT '文件名或者路径名',
  `sub_file_name` varchar(1000) DEFAULT NULL COMMENT '附属文件，如果存在的话，缩略图等',
  `mapping_name` varchar(30) DEFAULT NULL COMMENT '可执行文件的映像名称',
  `way` tinyint(4) unsigned DEFAULT NULL COMMENT '文件上传方式',
  `type` varchar(30) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `sub_file_size` bigint(20) DEFAULT NULL,
  `suffix` varchar(30) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_system` tinyint(2) DEFAULT NULL COMMENT '系统文件',
  PRIMARY KEY (`id`),
  KEY `FK_FOLDER` (`folder_id`),
  CONSTRAINT `FK_FOLDER` FOREIGN KEY (`folder_id`) REFERENCES `tb_folder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
INSERT INTO `tb_content` VALUES ('17792bc7-5c5c-405d-83c7-ca680027d531', '1', '2', '3', '33', '科教文卫交四.pptx', '科教文卫交四.swf', '', '1', '演示文档', '1008193', '546087', '.pptx', '2018-05-18 17:04:06', '2018-05-18 17:04:06', '0');
INSERT INTO `tb_content` VALUES ('47ec3a5e-9414-4e98-abc7-c20ed0d5c28a', '223abd1c-42d6-4c71-bf3c-b9c5a297afa2', '1', '1', '123', '10S.mp4', '10S_th.jpg', '', '1', '视频文件', '4376002', '0', '.mp4', '2018-01-25 17:24:04', '2018-01-25 17:24:04', '0');
INSERT INTO `tb_content` VALUES ('9c2b43f3-ba0a-4a96-940f-b2dd2421db83', '1', '1', '2', '123', '科教文卫交三.pptx', '科教文卫交三.swf', '', '1', '演示文档', '589213', '396674', '.pptx', '2018-05-18 17:03:58', '2018-05-18 17:10:59', '0');

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT '',
  `js_code` varchar(20) DEFAULT '',
  `name` varchar(255) DEFAULT '',
  `js_name` varchar(255) DEFAULT '',
  `type` varchar(20) DEFAULT '',
  `address` varchar(255) DEFAULT '',
  `bank_account` varchar(50) DEFAULT '',
  `cf_phone` varchar(50) DEFAULT '',
  `bz_phone` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_customer
-- ----------------------------

-- ----------------------------
-- Table structure for tb_dkh
-- ----------------------------
DROP TABLE IF EXISTS `tb_dkh`;
CREATE TABLE `tb_dkh` (
  `id` varchar(50) NOT NULL,
  `role` int(2) DEFAULT NULL,
  `job_num` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `personnel_id` varchar(255) DEFAULT NULL,
  `dkh_photo` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dkh
-- ----------------------------
INSERT INTO `tb_dkh` VALUES ('1436f63a-3460-4157-9a6d-8dc1c2f6e389', '1', 'D00280043', '王迪', 'UploadImages\\wd.jpg', '677ff2ad-d97d-4b00-ad2e-ae4793210899', null);
INSERT INTO `tb_dkh` VALUES ('a0191823-1aae-47ac-bc89-a7128979a99c', '1', 'D00280015', '姚燕斐', 'UploadImages\\31146792052875036.jpg', '2a8f1fb2-f713-49f8-8c19-9460e27645e9', 'UploadImages/201708301641031.jpg.jpg');

-- ----------------------------
-- Table structure for tb_folder
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder`;
CREATE TABLE `tb_folder` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `parent_id` varchar(50) DEFAULT NULL,
  `root_id` varchar(50) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `folder_name` varchar(30) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `is_system` tinyint(2) DEFAULT NULL COMMENT '系统目录',
  `add_time` datetime DEFAULT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_folder
-- ----------------------------
INSERT INTO `tb_folder` VALUES ('1', '0', '1', '根目录', 'root', 'files/root/', '1', null);
INSERT INTO `tb_folder` VALUES ('223abd1c-42d6-4c71-bf3c-b9c5a297afa2', '1', '223abd1c-42d6-4c71-bf3c-b9c5a297afa2', '素材', 'SC', 'files/root/SC/', '0', '2017-12-16 22:42:28');

-- ----------------------------
-- Table structure for tb_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_group`;
CREATE TABLE `tb_group` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(100) DEFAULT NULL COMMENT '设备组名称',
  `code_name` varchar(100) DEFAULT NULL COMMENT '设备组代号',
  `server_node_mac` varchar(50) DEFAULT NULL COMMENT '设备组服务器mac地址',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_group
-- ----------------------------
INSERT INTO `tb_group` VALUES ('6daa4c79-c0bf-4e35-9f6d-156665263f38', '体验厅一', 'TYT1', '', '2017-04-13 11:37:05');

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `item` varchar(100) DEFAULT NULL,
  `value` varchar(3000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('027d8eea-831c-4dc7-97c2-bd42dce4b2f2', 'qmbt', '温州移动');
INSERT INTO `tb_item` VALUES ('1', 'hyc', '欢迎光临');
INSERT INTO `tb_item` VALUES ('100', 'led', '欢迎光临');
INSERT INTO `tb_item` VALUES ('420e1dc2-c1ef-4f2f-8dd7-244720be0d83', 'wendu', '25');
INSERT INTO `tb_item` VALUES ('5', 'n', 'f3LSsjPSCu8VNWzJoL159A==');
INSERT INTO `tb_item` VALUES ('748124f8-6c4e-4c5e-836c-65ff7152b4cc', 'liangdu', '1000');

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` varchar(50) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `type` int(8) DEFAULT NULL,
  `statu` int(8) DEFAULT '1',
  `watch_code` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `edit_time` varchar(50) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `ordertime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES ('000111', '调度信息', '请xxx到前台', '6', '1', '192.168.1.93', '2018-10-14 16:29:13', null, '192.168.1.93', '2018-10-14 16:29:13');
INSERT INTO `tb_message` VALUES ('04352ba4-6338-481d-bb67-ee532f34e5e8', '日常信息', '姚燕斐工号D00280015今日工作岗位为综合导览台-1', '6', '0', '192.168.1.94', '2017-09-09 11:00:05', '2017-09-09 11:37:32', '192.168.1.94', '2017-09-09 11:00:05');
INSERT INTO `tb_message` VALUES ('1efaa42f-e141-4efa-b9b1-491af24ac35d', '意见簿留言信息', '意见簿提交了新的“表扬”内容。', '99', '1', '192.168.1.99', '2017-12-01 18:53:40', null, 'admin', '2017-12-01 18:53:40');
INSERT INTO `tb_message` VALUES ('44f5bd38-3255-40c4-8df9-54b611e56eec', '意见簿留言信息', '意见簿提交了新的“意见”内容。', '99', '1', '192.168.1.99', '2017-12-01 17:27:16', null, 'admin', '2017-12-01 17:27:16');
INSERT INTO `tb_message` VALUES ('584e858c-f8e8-4baa-b53c-a55a2dfe3dc3', '意见簿留言信息', '意见簿提交了新的“表扬”内容。', '99', '1', '192.168.1.99', '2017-12-01 18:50:48', null, 'admin', '2017-12-01 18:50:48');
INSERT INTO `tb_message` VALUES ('5a0931d8-c1f2-464a-a0f4-4b32ec507b26', '日常信息', '王迪工号D00280043今日工作岗位为个人业务办理-1', '6', '0', '192.168.1.97', '2017-09-09 11:35:34', '2017-09-09 11:39:01', '192.168.1.97', '2017-09-09 11:35:34');
INSERT INTO `tb_message` VALUES ('686c8f9f-3872-4330-b76e-975d49a67364', null, '机动(章慧芸工号D002800)呼叫您。', '9', '2', '192.168.1.99', '2017-09-09 11:38:43', '2017-09-09 11:38:50', '192.168.1.91', '2017-09-09 11:38:43');
INSERT INTO `tb_message` VALUES ('6d4c3dde-e279-4071-9ecc-1d5fda32c10d', '调度信息', '请曾伟峰工号15-03221到大堂-1', '3', '1', '', '2017-12-07 12:09:55', null, 'admin', '2017-12-07 12:09:55');
INSERT INTO `tb_message` VALUES ('92d235e8-77a3-4810-8e46-8e7285b79e3c', '调度信息', '请潘慧珠工号15-03114到咨询台-1', '3', '1', '', '2017-12-06 19:11:20', null, 'admin', '2017-12-06 19:11:20');
INSERT INTO `tb_message` VALUES ('b3c714ba-9f00-4c48-a0a6-f6b856152bb8', '日常信息', '章慧芸工号D002800今日工作岗位为机动', '6', '0', '192.168.1.91', '2017-09-09 11:36:11', '2017-09-09 11:38:22', '192.168.1.91', '2017-09-09 11:36:11');
INSERT INTO `tb_message` VALUES ('b6719261-6c72-4fe4-ab50-5b69de77ab30', '日常信息', '占杭工号D00280045今日工作岗位为收费业务-1', '6', '0', '192.168.1.96', '2017-09-09 11:00:31', '2017-09-09 11:37:46', '192.168.1.96', '2017-09-09 11:00:31');
INSERT INTO `tb_message` VALUES ('ba9d2138-5462-4e5e-9983-8bf10fc8027b', null, '个人业务办理-1(王迪工号D00280043)呼叫您。', '9', '2', '192.168.1.99', '2017-09-09 11:39:07', '2017-09-09 11:39:11', '192.168.1.97', '2017-09-09 11:39:07');
INSERT INTO `tb_message` VALUES ('c78df3d3-524f-4340-ab52-6326b531ae52', '日常信息', '吴申楠工号P00234417今日工作岗位为值长', '6', '0', '192.168.1.99', '2017-09-09 11:35:55', '2017-09-09 11:38:30', '192.168.1.99', '2017-09-09 11:35:55');
INSERT INTO `tb_message` VALUES ('ecff6fef-5d52-4575-b437-c624fbca3d85', '日常信息', '李彦裕工号D00280046今日工作岗位为开放式VIP', '6', '0', '192.168.1.93', '2017-09-09 11:01:13', '2017-09-09 11:38:05', '192.168.1.93', '2017-09-09 11:01:13');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `status` tinyint(4) DEFAULT NULL COMMENT '参观状态',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `number` int(8) DEFAULT NULL COMMENT '参观人数',
  `date` date DEFAULT NULL COMMENT '预约参观时间',
  `add_time` datetime DEFAULT NULL COMMENT '信息提交时间',
  `apm` varchar(10) DEFAULT NULL COMMENT '上午/下午',
  `qrcode` varchar(100) DEFAULT NULL COMMENT '生成二维码存放路径',
  `operator` varchar(100) DEFAULT NULL COMMENT '添加人',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('8e5e9ef2-7652-47e2-9a35-1a9fdd58d78c', '2', 'ADMIN', '18888888888', '1', '2016-09-25', '2016-09-25 15:47:43', '上午', 'files/code/201609251547431.png', '管理员', '');

-- ----------------------------
-- Table structure for tb_personnel
-- ----------------------------
DROP TABLE IF EXISTS `tb_personnel`;
CREATE TABLE `tb_personnel` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT NULL,
  `age` varchar(8) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `watch_code` varchar(50) DEFAULT NULL,
  `work_position` varchar(50) DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `work_status` int(12) DEFAULT NULL,
  `work_time` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `onduty` varchar(500) DEFAULT NULL,
  `job_num` varchar(32) DEFAULT NULL COMMENT '人员工号',
  `bind_status` int(11) DEFAULT '1' COMMENT '绑定状态（0：已绑定，未绑定）',
  `change_time` datetime DEFAULT NULL,
  `pass_work` varchar(255) DEFAULT NULL,
  `my_work` int(50) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `work` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_personnel
-- ----------------------------
INSERT INTO `tb_personnel` VALUES ('0683efa6-e133-4e86-bd3c-9e7aa6dd6b35', '潘慧珠', null, '2', '', null, 'UploadImages/201712061906491.jpg', '1', '0', null, null, '15-03114', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('0709d3de-cc88-4498-916d-bddd058d65f3', '曾伟峰', null, '1', '', null, 'UploadImages/201712061906571.jpg', '1', '0', null, null, '15-03221', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('0ea91b02-042e-403f-803c-205809d4f97a', '郭楚毅', null, '1', '', null, 'UploadImages/201712061907071.jpg', '1', '0', null, null, '15-03116', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('1d416940-f544-485b-97b9-421e002fcb12', '黄志宏', null, '1', '', null, 'UploadImages/201712061907171.jpg', '1', '0', null, null, '15-03118', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('20267e0e-5112-4e5b-a346-84e3b9809b95', '冯彦瑜', null, '2', '', null, 'UploadImages/201712061907261.jpg', '1', '0', null, null, '15-03109', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('257bdc8a-3272-49e8-bb4b-499875643967', '张燕竹', null, '2', '', null, 'UploadImages/201712061907351.jpg', '1', '0', null, null, '15-03121', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('3ebd2b5a-ba4e-4215-a169-459720137cbb', '黄诗敏', null, '2', '', null, 'UploadImages/201712061907471.jpg', '1', '0', null, null, '15-03115', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('3fd4302a-ee9b-4368-b4ff-b22f94713377', '劳海澄', null, '1', '', null, 'UploadImages/201712061907541.jpg', '1', '0', null, null, '15-03119', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('47fdcebd-3629-4c8b-af22-171b47aa8f89', '袁绮梅', null, '2', '', null, 'UploadImages/201712061908021.jpg', '1', '0', null, null, '15-03107', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('49862598-fadd-45fd-8eb1-58c45a9ab568', '黄耀芬', null, '2', '', null, 'UploadImages/201712061908191.jpg', '1', '0', null, null, '15-03113', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('49d55730-e2ca-4981-b82e-3c757d72b200', '陈东茹', null, '2', '', null, 'UploadImages/201712061908251.jpg', '1', '0', null, null, '15-03103', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('9210bad2-7d5e-4aa9-9bf2-66357c1aeb69', '陈潼', null, '1', '', null, 'UploadImages/201712061908331.jpg', '1', '0', null, null, '15-03132', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('a5b08e91-bd2b-4bd2-9073-cc72597fa2a7', '陈丽霞', null, '2', '', null, 'UploadImages/201712061908391.jpg', '1', '0', null, null, '15-03112', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('a906b1ae-d73a-48bf-a4d8-7ab8bed501de', '钟凤冰', null, '2', '', null, 'UploadImages/201712061908451.jpg', '1', '0', null, null, '15-03108', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('b7222943-4f9a-4cab-bffd-8f5c2918e3bf', '潘绮玲', null, '2', '', null, 'UploadImages/201712061908511.jpg', '1', '0', null, null, '15-01023', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('cd037b58-7d6c-4c73-b672-0fa1ad7127cc', '任科桦', null, '2', '', null, 'UploadImages/201712061909031.jpg', '1', '0', null, null, '15-03111', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('d2aa2483-eaac-408b-ac00-6b2ab2f953cd', '黄靖仪', null, '2', '', null, 'UploadImages/201712061909121.jpg', '1', '0', null, null, '15-03133', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('dce91c9e-3199-4499-abf1-1e7640c7f89e', '李颖丝', null, '2', '', null, 'UploadImages/201712061909191.jpg', '1', '0', null, null, '15-03125', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('e2579223-b290-423c-baaf-13e457018837', '黄星亮', null, '1', '', null, 'UploadImages/201712061909271.jpg', '1', '0', null, null, '15-03106', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('f82fa9b6-bba1-472e-97c9-04583aee4d66', '温志卿', null, '2', '', null, 'UploadImages/201712061909351.jpg', '1', '0', null, null, '15-03124', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('f9c1ed69-bc69-4c3a-a1a3-5af51356e177', '黄皓怡', null, '1', '', null, 'UploadImages/201712061909451.jpg', '1', '0', null, null, '15-03701', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);
INSERT INTO `tb_personnel` VALUES ('facc4dc8-d11c-42ba-ad77-bf50a53afce9', '贺婷', null, '2', '', null, 'UploadImages/201712061909591.jpg', '1', '0', null, null, '15-03128', '1', '2018-05-12 10:20:20', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null);

-- ----------------------------
-- Table structure for tb_picture
-- ----------------------------
DROP TABLE IF EXISTS `tb_picture`;
CREATE TABLE `tb_picture` (
  `id` varchar(50) NOT NULL DEFAULT '',
  `order_id` varchar(50) DEFAULT NULL,
  `type` int(8) DEFAULT NULL,
  `file_name` varchar(30) DEFAULT NULL,
  `file_path` varchar(100) DEFAULT NULL,
  `sub_file_name` varchar(30) DEFAULT NULL,
  `sub_file_path` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ORDER` (`order_id`),
  CONSTRAINT `FK_ORDER` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_picture
-- ----------------------------
INSERT INTO `tb_picture` VALUES ('8a602e05-7d74-4ac5-9e6d-d91833a4a54e', '8e5e9ef2-7652-47e2-9a35-1a9fdd58d78c', '0', '201709022001071.jpg', 'files/picture/201709022001071.jpg', '201709022001071_th.jpg', 'files/picture/201709022001071_th.jpg', '2017-09-02 20:01:08');

-- ----------------------------
-- Table structure for tb_power_failure
-- ----------------------------
DROP TABLE IF EXISTS `tb_power_failure`;
CREATE TABLE `tb_power_failure` (
  `id` varchar(50) NOT NULL,
  `line` varchar(255) DEFAULT '',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  `blackout_range` varchar(5000) DEFAULT '',
  `status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_power_failure
-- ----------------------------
INSERT INTO `tb_power_failure` VALUES ('68e5ec1f-06ec-404a-829d-b32e12111908', '许村镇', '2017-07-08 09:00:00', '2017-07-08 15:00:00', '2017-07-07 15:20:01', '组织维修。', '1');

-- ----------------------------
-- Table structure for tb_queue
-- ----------------------------
DROP TABLE IF EXISTS `tb_queue`;
CREATE TABLE `tb_queue` (
  `id` varchar(255) NOT NULL,
  `queue_no` varchar(255) DEFAULT NULL,
  `cons_name` varchar(255) DEFAULT NULL,
  `cons_no` varchar(255) DEFAULT NULL,
  `busi_code` varchar(255) DEFAULT NULL,
  `cons_tag` varchar(255) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_queue
-- ----------------------------
INSERT INTO `tb_queue` VALUES ('1', '1', '1', '1', '1', 'a,b,c', 'aaa', '2017-10-28 14:50:38');
INSERT INTO `tb_queue` VALUES ('2', '2', '2', '2', '2', 'd', 'vvv', '2017-10-27 15:33:57');
INSERT INTO `tb_queue` VALUES ('3', '3', '3', '3', '3', 'b,f', 'ads', '2017-10-27 15:34:12');
INSERT INTO `tb_queue` VALUES ('4', '4', '4', '4', '4', 'h', 'sfd', '2017-10-27 15:34:24');

-- ----------------------------
-- Table structure for tb_rfid
-- ----------------------------
DROP TABLE IF EXISTS `tb_rfid`;
CREATE TABLE `tb_rfid` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键id',
  `rfid` varchar(100) DEFAULT NULL COMMENT '卡号',
  `name` varchar(100) DEFAULT NULL COMMENT '来访者姓名',
  `company_name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `job` varchar(100) DEFAULT NULL COMMENT '职位',
  `phone_number` varchar(100) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rfid
-- ----------------------------

-- ----------------------------
-- Table structure for tb_sales_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_sales_data`;
CREATE TABLE `tb_sales_data` (
  `id` varchar(50) NOT NULL,
  `sshs` int(8) DEFAULT NULL,
  `ssje` double(16,2) DEFAULT NULL,
  `dzqdsshs` int(8) DEFAULT NULL,
  `dzqdssje` double(16,2) DEFAULT NULL,
  `ykbzhs` int(8) DEFAULT NULL,
  `dzqdhs` int(8) DEFAULT NULL,
  `this_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sales_data
-- ----------------------------
INSERT INTO `tb_sales_data` VALUES ('0168e577-f655-47fd-8df2-7ed53270cd0b', '1174', '10230917.47', '682', '673124.20', '96', '57', '2017-11-20');
INSERT INTO `tb_sales_data` VALUES ('02022a1c-d57c-4f11-974f-c800d74a54e1', '1345', '2719101.40', '831', '376758.12', '140', '109', '2017-12-15');
INSERT INTO `tb_sales_data` VALUES ('03190a95-fa6a-4761-ac81-6a85c926bbd2', '961', '1012219.60', '667', '429937.19', '87', '45', '2017-12-11');
INSERT INTO `tb_sales_data` VALUES ('03360847-926c-46cf-9856-70307695d0e8', '1332', '3767422.05', '1006', '665197.60', '145', '103', '2017-11-15');
INSERT INTO `tb_sales_data` VALUES ('0605b15c-5ccd-47da-91a8-0ac4b78560ab', '272', '16391.28', '56', '16391.28', '20', '19', '2018-01-06');
INSERT INTO `tb_sales_data` VALUES ('06f66a7c-9d2a-4a24-a904-c95eac72a7ca', '95', '418397.34', '83', '22194.71', '106', '22', '2018-01-30');
INSERT INTO `tb_sales_data` VALUES ('076ca317-e4b8-4ea8-b098-f97f38791cb2', '1223', '3944254.32', '796', '1354055.29', '109', '44', '2017-10-18');
INSERT INTO `tb_sales_data` VALUES ('0f0f72f7-6b44-4963-8b73-4dd7cfaca250', '60', '17165.00', '58', '17165.00', '20', '16', '2018-02-03');
INSERT INTO `tb_sales_data` VALUES ('0fe66bc8-ee8a-432a-960c-56ab6dc8a1fd', '1181', '512812.49', '1155', '477533.84', '33', '33', '2018-02-10');
INSERT INTO `tb_sales_data` VALUES ('12b23589-e032-4c36-b62c-767bf9416d66', '304', '237973.23', '280', '207046.58', '28', '20', '2017-11-18');
INSERT INTO `tb_sales_data` VALUES ('15744840-d8de-482c-b681-8f705bf8ecd9', '116', '42572.32', '115', '41572.32', '32', '29', '2018-01-27');
INSERT INTO `tb_sales_data` VALUES ('1684a048-ddd0-420a-93d7-a194de0ae777', '931', '974316.37', '440', '337779.62', '100', '32', '2018-02-13');
INSERT INTO `tb_sales_data` VALUES ('1926e233-25b5-4984-90f5-76210c6ce1f9', '973', '1288861.44', '674', '327052.45', '78', '32', '2018-02-11');
INSERT INTO `tb_sales_data` VALUES ('193652f8-f0f4-430e-bda1-15852e632096', '70', '86075.31', '70', '86075.31', '26', '22', '2017-11-26');
INSERT INTO `tb_sales_data` VALUES ('1a4f9909-e1a6-4e10-a990-a09e85b2f107', '104', '20419.85', '60', '2409.68', '24', '13', '2017-11-05');
INSERT INTO `tb_sales_data` VALUES ('1d2f51ef-06ca-471b-95e6-80def536b7f1', '1308', '1403559.16', '908', '347246.98', '147', '65', '2018-01-10');
INSERT INTO `tb_sales_data` VALUES ('24d44604-5635-4488-bd0d-da9272fb693c', '364', '4360660.67', '255', '373568.59', '172', '63', '2018-01-17');
INSERT INTO `tb_sales_data` VALUES ('2915083c-ba75-4b0f-aac4-81d14c6dc1f2', '1323', '2086068.08', '1008', '804384.92', '94', '64', '2017-11-10');
INSERT INTO `tb_sales_data` VALUES ('2b43af02-84ca-4d7f-9bf8-a6742a245861', '484', '1026418.76', '447', '235623.86', '124', '64', '2017-11-28');
INSERT INTO `tb_sales_data` VALUES ('2c54698a-e423-4615-a568-e8064e7ef5e5', '500', '3595024.43', '258', '467423.90', '99', '48', '2017-11-14');
INSERT INTO `tb_sales_data` VALUES ('2de11c22-c7a5-4e6f-9eed-a3b8426a3ae4', '71', '76593.67', '54', '17713.69', '119', '34', '2018-02-02');
INSERT INTO `tb_sales_data` VALUES ('3026ca65-8e58-4318-a248-28c48993fa44', '11', '4254.32', '11', '4254.32', '30', '21', '2017-12-31');
INSERT INTO `tb_sales_data` VALUES ('31550e75-008c-40c1-a3cc-ef9ab51ea9b0', '1618', '953251.71', '977', '576810.92', '99', '51', '2017-11-09');
INSERT INTO `tb_sales_data` VALUES ('31f6d9c6-783e-41f9-a838-5b5df3e11079', '311', '112550.31', '309', '112287.19', '39', '37', '2017-12-24');
INSERT INTO `tb_sales_data` VALUES ('3248ed1c-1d34-45bb-a7ec-d33a95776285', '845', '3213317.59', '299', '139989.03', '56', '17', '2018-02-14');
INSERT INTO `tb_sales_data` VALUES ('331ebd84-53ec-4c5b-87a3-6fc2e8f4557a', '1493', '1363983.20', '704', '688592.58', '81', '33', '2017-11-08');
INSERT INTO `tb_sales_data` VALUES ('38c50350-5b6e-4fc1-92eb-3e5c1efd8a8f', '1049', '638456.26', '870', '393476.59', '124', '55', '2018-01-09');
INSERT INTO `tb_sales_data` VALUES ('39d9e9ec-b18f-4005-8b8a-e58f68df6995', '282', '2890558.99', '142', '131504.48', '23', '29', '2018-02-22');
INSERT INTO `tb_sales_data` VALUES ('3afa9dd4-775a-4fb0-9428-b182cda68006', '101', '31149.20', '101', '31149.20', '36', '33', '2018-01-28');
INSERT INTO `tb_sales_data` VALUES ('3cb9c323-1d61-401f-84b9-8681eae17ac4', '55', '470141.39', '36', '11262.66', '155', '103', '2017-12-29');
INSERT INTO `tb_sales_data` VALUES ('3d657642-ed20-45dc-a3a9-c6c8ce2334be', '77', '24333.75', '77', '24333.75', '2', '2', '2018-02-18');
INSERT INTO `tb_sales_data` VALUES ('3ebd9144-3ae3-41d0-aec6-683e3d5a574d', '173', '76264.44', '172', '75903.09', '31', '24', '2017-12-03');
INSERT INTO `tb_sales_data` VALUES ('3f6f5743-8c97-445f-a897-fed6604ce817', '995', '5902273.59', '546', '568805.52', '129', '95', '2017-11-17');
INSERT INTO `tb_sales_data` VALUES ('408e3495-0510-4f23-9167-7b94b8470d49', '626', '346415.82', '608', '239547.37', '161', '47', '2018-01-24');
INSERT INTO `tb_sales_data` VALUES ('409a5a8d-c653-415a-9ab1-e244eae71182', '87', '349738.01', '61', '330076.94', '182', '90', '2017-11-03');
INSERT INTO `tb_sales_data` VALUES ('4102c4ee-1dc1-467e-b702-0c1d3bd55a4c', '303', '992678.09', '196', '180185.47', '134', '57', '2018-01-23');
INSERT INTO `tb_sales_data` VALUES ('4240349d-0168-4952-a0c5-443b4062b4a6', '203', '141091.12', '123', '128805.17', '84', '48', '2017-10-27');
INSERT INTO `tb_sales_data` VALUES ('42473200-b2fe-491a-a0bb-dd456a2980fa', '1202', '8663850.58', '795', '4478425.90', '109', '48', '2017-10-19');
INSERT INTO `tb_sales_data` VALUES ('429284d5-9f84-4d5a-84fe-566a8c7aab11', '606', '750768.19', '428', '314401.24', '129', '39', '2017-11-21');
INSERT INTO `tb_sales_data` VALUES ('42ab3dda-8617-47a2-a535-74f93ec9f711', '71', '39242.28', '70', '39122.27', '44', '35', '2017-11-25');
INSERT INTO `tb_sales_data` VALUES ('4403e16c-dd25-4b89-b6cc-50ad90e1bff1', '193', '107846.16', '190', '105359.88', '28', '27', '2018-02-25');
INSERT INTO `tb_sales_data` VALUES ('45cd4e23-4678-4857-81d9-2f214ad5e79a', '1488', '403406.98', '1079', '401840.16', '50', '32', '2017-12-09');
INSERT INTO `tb_sales_data` VALUES ('4a77fa97-ed45-4a57-a92c-43ff4ebde129', '895', '2823051.58', '411', '182904.71', '89', '47', '2018-02-27');
INSERT INTO `tb_sales_data` VALUES ('4c0577d2-7a08-41f5-948c-af8900c4eee9', '810', '419341.19', '679', '400299.31', '38', '27', '2017-11-11');
INSERT INTO `tb_sales_data` VALUES ('4d01bd61-7e85-47be-8fb7-6384f6c5aa2f', '19', '20926.85', '16', '3926.85', '103', '56', '2018-01-02');
INSERT INTO `tb_sales_data` VALUES ('4d84d2ed-370c-4b5c-9f94-a21e827bc633', '100', '409498.04', '46', '157919.13', '186', '77', '2017-10-31');
INSERT INTO `tb_sales_data` VALUES ('4fdffef9-b83e-4e52-9129-be5ca18dfc85', '130', '45869.84', '130', '45869.84', '73', '11', '2018-02-21');
INSERT INTO `tb_sales_data` VALUES ('59373a41-b0e9-4652-a570-5cb4803fbdda', '71', '179556.98', '58', '12922.97', '81', '31', '2018-01-31');
INSERT INTO `tb_sales_data` VALUES ('5d762cfc-554f-432c-8759-8fdeb7eb3c9d', '1598', '2933120.83', '232', '230385.13', '93', '65', '2017-10-24');
INSERT INTO `tb_sales_data` VALUES ('623dbb40-2cd2-4159-8832-002e6889c41b', '115', '246816.37', '72', '140671.22', '197', '55', '2017-10-30');
INSERT INTO `tb_sales_data` VALUES ('62873810-c6e1-472b-8d8c-1fc908f61af9', '204', '268920.52', '196', '260871.48', '46', '40', '2018-01-20');
INSERT INTO `tb_sales_data` VALUES ('62e78ad8-59de-4fab-8c7b-9fa1d72ca0d1', '1155', '4871810.79', '554', '598569.37', '70', '35', '2018-02-12');
INSERT INTO `tb_sales_data` VALUES ('64e2d32b-6f6a-4c15-a5f2-a374df0d1259', '174', '350517.00', '141', '46278.20', '104', '43', '2018-01-26');
INSERT INTO `tb_sales_data` VALUES ('67fc533d-1c67-4371-8258-7b825ab2b671', '475', '879376.67', '398', '190124.95', '109', '66', '2017-12-25');
INSERT INTO `tb_sales_data` VALUES ('680f7c5c-e698-4027-b09f-606dbeead486', '274', '915176.49', '182', '105494.56', '142', '53', '2018-01-22');
INSERT INTO `tb_sales_data` VALUES ('685d85d3-2712-4a43-a978-952b28c18faf', '2234', '4973158.36', '1678', '696126.46', '93', '42', '2018-02-09');
INSERT INTO `tb_sales_data` VALUES ('6ddde46d-7379-412b-909f-ce54e89e7127', '861', '2664479.56', '414', '1443086.23', '124', '38', '2017-10-20');
INSERT INTO `tb_sales_data` VALUES ('6fdae2d8-4697-42de-a14d-8ab6f66f49fb', '96', '85246.24', '96', '82546.24', '3', '3', '2018-02-20');
INSERT INTO `tb_sales_data` VALUES ('700279f4-268d-43cf-94d8-778e4a03c082', '120', '107524.10', '108', '70288.16', '60', '58', '2018-01-21');
INSERT INTO `tb_sales_data` VALUES ('70449adf-fd35-4d4e-97ae-59d7a7e158a6', '83', '55298.70', '83', '55298.70', '6', '6', '2018-02-19');
INSERT INTO `tb_sales_data` VALUES ('70d45174-e9bc-476d-bea3-3a1a84187c68', '191', '201628.00', '103', '44389.03', '102', '56', '2017-12-05');
INSERT INTO `tb_sales_data` VALUES ('72beddb4-2bc6-4b94-abd3-614182934ba8', '75', '148447.07', '67', '19618.36', '96', '27', '2018-02-01');
INSERT INTO `tb_sales_data` VALUES ('74456728-6454-4738-9944-d14b67cfe015', '183', '254201.88', '124', '207350.10', '61', '35', '2017-10-22');
INSERT INTO `tb_sales_data` VALUES ('79c0bed0-a710-49af-b57a-f34ea7e10a1f', '234', '400697.24', '157', '340806.04', '47', '37', '2017-10-21');
INSERT INTO `tb_sales_data` VALUES ('7c4add49-3657-41e0-bd8b-e37ac30b4123', '259', '773900.67', '135', '152729.96', '23', '16', '2018-02-23');
INSERT INTO `tb_sales_data` VALUES ('7c4d710d-f6b8-4940-8823-e9a89701abc4', '307', '211141.82', '102', '83415.32', '90', '28', '2017-12-06');
INSERT INTO `tb_sales_data` VALUES ('7db13009-d5d2-4f02-a7cc-db77d34844b2', '558', '733368.95', '294', '193657.84', '99', '58', '2017-11-22');
INSERT INTO `tb_sales_data` VALUES ('7ff61ba6-2103-4227-8a11-8c15bc9c78be', '254', '493406.94', '170', '108905.43', '69', '33', '2017-11-23');
INSERT INTO `tb_sales_data` VALUES ('81c75c0f-dbb5-4937-997c-a9dd30af24c4', '1801', '2257116.17', '1393', '839671.64', '102', '79', '2017-12-20');
INSERT INTO `tb_sales_data` VALUES ('8408f803-375f-4059-b680-3d34c423f83e', '1566', '1710074.76', '1328', '642758.76', '90', '30', '2018-02-08');
INSERT INTO `tb_sales_data` VALUES ('86d8a97f-41e6-4cb7-aacc-efa324f5ac83', '126', '293348.20', '33', '9618.47', '125', '23', '2018-02-05');
INSERT INTO `tb_sales_data` VALUES ('8730f53d-a90d-4044-b471-bacb318141eb', '1623', '1504836.00', '1167', '572780.58', '142', '102', '2017-12-14');
INSERT INTO `tb_sales_data` VALUES ('881bd5ac-b710-4c2f-ba62-c81aeec91be2', '1107', '567171.24', '650', '296016.89', '75', '46', '2017-12-08');
INSERT INTO `tb_sales_data` VALUES ('88572583-7087-440b-a509-3190908de98d', '870', '1331593.10', '683', '454559.76', '132', '75', '2017-11-16');
INSERT INTO `tb_sales_data` VALUES ('897b63ee-22bc-47cd-9970-579f4e584e11', '44', '19386.07', '15', '10348.52', '37', '33', '2017-11-04');
INSERT INTO `tb_sales_data` VALUES ('89dc3728-af93-4a3e-a89c-f98d6d81dea4', '799', '587117.88', '436', '153764.17', '106', '45', '2017-12-07');
INSERT INTO `tb_sales_data` VALUES ('8ffc8383-98d3-493d-a52d-8c1acbe57ae2', '38', '303458.38', '14', '6458.38', '88', '37', '2018-01-04');
INSERT INTO `tb_sales_data` VALUES ('92743dc2-e51f-4810-b9a2-b6a9ceb0b01f', '1240', '442272.19', '765', '294052.62', '110', '33', '2018-01-08');
INSERT INTO `tb_sales_data` VALUES ('95bc4c92-ef82-492a-80ce-152687b3d4ae', '721', '378815.66', '673', '316947.82', '182', '109', '2017-11-29');
INSERT INTO `tb_sales_data` VALUES ('965561c0-a47e-41bb-8d46-d990d3f775f2', '232', '662622.11', '139', '671815.64', '71', '36', '2017-10-26');
INSERT INTO `tb_sales_data` VALUES ('97c2751b-23a2-4df4-8e64-aec91729feb6', '159', '398136.49', '127', '52919.52', '96', '31', '2018-01-29');
INSERT INTO `tb_sales_data` VALUES ('97d035ff-da9c-42c5-a700-eb3179c1c085', '84', '74390.11', '84', '74390.11', '5', '5', '2018-02-16');
INSERT INTO `tb_sales_data` VALUES ('9bd45847-b26c-4745-a3ca-939fec6173b2', '739', '125506.33', '441', '125506.33', '34', '26', '2018-01-07');
INSERT INTO `tb_sales_data` VALUES ('9e5c5f70-606f-4399-af4a-94f7b8efcaf9', '90', '534919.10', '91', '182597.86', '133', '54', '2017-11-01');
INSERT INTO `tb_sales_data` VALUES ('aaedcae3-5464-4fff-a3c8-c4afb1f1790c', '95', '138324.69', '72', '17341.44', '160', '92', '2017-12-28');
INSERT INTO `tb_sales_data` VALUES ('aee362fe-8ba3-4663-973d-e0be47708fd7', '779', '2496041.58', '364', '178567.44', '111', '65', '2017-10-25');
INSERT INTO `tb_sales_data` VALUES ('afd126db-a661-425e-bdcf-4b8df3d6e023', '426', '486876.05', '329', '181289.37', '66', '37', '2017-12-01');
INSERT INTO `tb_sales_data` VALUES ('b0e7d9f9-e96f-404f-8b98-02701c9fb2b1', '31', '7662.90', '31', '7662.90', '44', '39', '2017-12-30');
INSERT INTO `tb_sales_data` VALUES ('b145011f-8b58-4c0e-a111-eb2897581521', '70', '26907.83', '70', '26907.83', '5', '5', '2018-02-17');
INSERT INTO `tb_sales_data` VALUES ('b24cb657-2ab5-4d12-85ea-c04ec09fa57e', '729', '3006344.24', '561', '410485.70', '101', '72', '2017-12-12');
INSERT INTO `tb_sales_data` VALUES ('b2ba8968-9c86-4db2-889b-e6fb43af43fb', '534', '1035630.83', '357', '430066.67', '184', '63', '2018-01-16');
INSERT INTO `tb_sales_data` VALUES ('b4ed062f-93c1-46e9-ac94-25ebb12949ae', '481', '569755.38', '481', '569755.38', '6', '6', '2018-02-15');
INSERT INTO `tb_sales_data` VALUES ('b5367f51-935d-45bc-8db1-8a76b95e0db6', '777', '542556.66', '742', '406003.02', '96', '72', '2017-11-30');
INSERT INTO `tb_sales_data` VALUES ('b7c77793-c044-4a18-963b-4d472695a457', '629', '936072.65', '490', '165535.54', '187', '65', '2018-01-11');
INSERT INTO `tb_sales_data` VALUES ('b825e38f-311c-4c2f-883f-653761b88436', '786', '381968.76', '317', '143740.30', '56', '32', '2017-11-06');
INSERT INTO `tb_sales_data` VALUES ('b8a79741-fe59-4671-b8f5-242961d20f0d', '560', '1024444.00', '384', '293749.66', '119', '65', '2017-12-13');
INSERT INTO `tb_sales_data` VALUES ('bd3022db-58b0-4c17-af89-93a8d93eb87c', '236', '119399.11', '234', '115029.15', '36', '32', '2018-01-13');
INSERT INTO `tb_sales_data` VALUES ('bda41263-7e5a-4bf0-9f15-58d49c23103e', '486', '339903.90', '482', '334268.43', '50', '38', '2017-11-12');
INSERT INTO `tb_sales_data` VALUES ('bdfe942e-7768-41ab-94c4-0bf7e3824ae7', '118', '519959.54', '21', '2998.70', '100', '48', '2018-01-05');
INSERT INTO `tb_sales_data` VALUES ('bea1e36f-0491-4b8f-8a33-68d0c49b266c', '353', '139936.71', '347', '137413.08', '54', '43', '2017-12-23');
INSERT INTO `tb_sales_data` VALUES ('bf31be04-bf02-4eea-91c7-2ffab6124037', '686', '5182218.05', '329', '127218.69', '188', '76', '2017-12-26');
INSERT INTO `tb_sales_data` VALUES ('c1208ae4-9efa-4988-806a-1647f767ffcc', '1244', '2741111.16', '284', '105702.29', '127', '45', '2018-01-25');
INSERT INTO `tb_sales_data` VALUES ('c64a783e-f532-4895-aeb6-8ef6aad5fac2', '665', '724084.29', '389', '210689.70', '110', '55', '2018-01-12');
INSERT INTO `tb_sales_data` VALUES ('c7c8699a-243b-435f-b2b8-400e39aa4d1f', '17', '2956.73', '17', '2956.73', '22', '22', '2018-01-01');
INSERT INTO `tb_sales_data` VALUES ('cb550bed-0762-45cb-9366-70ace9ba97ff', '2046', '2771378.97', '1156', '594830.77', '110', '70', '2017-12-19');
INSERT INTO `tb_sales_data` VALUES ('cb5c541d-fe97-4018-80e0-90aa5cac4490', '262', '118535.35', '256', '114721.00', '58', '55', '2017-12-17');
INSERT INTO `tb_sales_data` VALUES ('cebf5146-0e53-4c1c-8db2-3183961164fd', '643', '333340.25', '623', '267699.51', '50', '35', '2017-12-10');
INSERT INTO `tb_sales_data` VALUES ('cf67d52b-c0d2-44b6-a7ba-789b9aa395d3', '37', '9420.84', '36', '8297.02', '15', '14', '2018-02-04');
INSERT INTO `tb_sales_data` VALUES ('d1847b79-b1e1-4cda-a298-8d7eee2bd710', '815', '860251.60', '711', '286964.18', '94', '68', '2017-12-22');
INSERT INTO `tb_sales_data` VALUES ('d19147e7-19b5-481a-a2ed-f4555d98f9e0', '207', '87011.03', '403', '84768.26', '38', '33', '2018-01-14');
INSERT INTO `tb_sales_data` VALUES ('d3d9308c-0876-464b-abab-d52fab15bf7b', '393', '493196.84', '376', '484875.88', '17', '12', '2017-11-19');
INSERT INTO `tb_sales_data` VALUES ('d3e9ac2e-fa15-4f4a-94d7-f9e415eb9330', '62', '29569.10', '22', '9607.66', '92', '77', '2017-10-29');
INSERT INTO `tb_sales_data` VALUES ('d4ae726b-484c-41f8-afb0-7eefab274e06', '622', '643959.98', '555', '295682.88', '78', '53', '2018-02-26');
INSERT INTO `tb_sales_data` VALUES ('d4fe96e9-15ef-4042-bd84-d48927557d87', '264', '1366494.17', '208', '65710.20', '120', '55', '2017-12-27');
INSERT INTO `tb_sales_data` VALUES ('d5dec5d6-e68f-4721-af09-136f76ab64f5', '430', '448379.48', '231', '123660.11', '60', '43', '2018-02-24');
INSERT INTO `tb_sales_data` VALUES ('d7525572-8ced-4f18-a827-9cd95d15fcb1', '1620', '10320716.86', '580', '1316022.07', '85', '44', '2017-10-16');
INSERT INTO `tb_sales_data` VALUES ('d7cebccf-d237-48ef-a6e1-bdc721034ea1', '55', '34895.34', '40', '27202.84', '53', '34', '2017-10-28');
INSERT INTO `tb_sales_data` VALUES ('d7d1551e-6d44-4a23-a830-489e8ef84564', '1337', '5415727.42', '443', '1011111.14', '79', '40', '2017-10-17');
INSERT INTO `tb_sales_data` VALUES ('d8fe156e-e5ed-4d7a-8ee0-89b1e983f6c2', '416', '486990.48', '375', '174417.31', '46', '44', '2017-12-16');
INSERT INTO `tb_sales_data` VALUES ('e016e68e-fd1f-4ff7-8bee-b497c7c4333c', '394', '3978066.48', '237', '288727.51', '114', '35', '2018-01-18');
INSERT INTO `tb_sales_data` VALUES ('e01a61a3-2dda-4994-a035-81fc5fc928af', '796', '2704010.86', '363', '171143.64', '100', '61', '2017-11-27');
INSERT INTO `tb_sales_data` VALUES ('e14cff32-a5aa-4e74-b9cc-fe5d43b38b7b', '178', '69336.00', '177', '69336.00', '36', '33', '2017-12-02');
INSERT INTO `tb_sales_data` VALUES ('e1b39c37-f6eb-4867-8e5b-2eedeef7f356', '101', '110966.98', '89', '105002.07', '146', '65', '2017-11-02');
INSERT INTO `tb_sales_data` VALUES ('e215060b-f92e-4f7a-9082-47d3b33b0cd1', '660', '2559468.31', '469', '447658.80', '155', '76', '2018-01-15');
INSERT INTO `tb_sales_data` VALUES ('e5224ed6-88f9-4a2f-adaa-3471d2a95b98', '177', '206092.58', '70', '21353.48', '91', '27', '2018-02-06');
INSERT INTO `tb_sales_data` VALUES ('e7194a85-270e-4fc3-aef0-3d66200c8bd4', '752', '895536.61', '506', '221319.92', '66', '30', '2018-02-07');
INSERT INTO `tb_sales_data` VALUES ('e9df2914-d15c-4aff-9ced-9baa8323b511', '1271', '513189.53', '499', '331953.41', '81', '42', '2017-11-07');
INSERT INTO `tb_sales_data` VALUES ('eb34c55b-821b-40e9-9af0-476b71615bcb', '625', '878140.00', '421', '373480.42', '94', '51', '2017-11-13');
INSERT INTO `tb_sales_data` VALUES ('ebfba9d7-4cad-4f6a-81b3-56ae932ef29e', '650', '4105448.65', '352', '646060.00', '116', '56', '2018-01-19');
INSERT INTO `tb_sales_data` VALUES ('f2a47f2b-801d-4fff-aa2d-0dbd7f998e81', '205', '187711.25', '191', '101616.46', '109', '48', '2017-12-04');
INSERT INTO `tb_sales_data` VALUES ('f5403046-4be2-49d4-86d1-e8ae1f7da8a7', '510', '1192110.21', '266', '473861.94', '126', '32', '2017-10-23');
INSERT INTO `tb_sales_data` VALUES ('f7b2a339-f92c-46cb-8f1f-dd3eabdb4c8a', '1665', '2365164.42', '1073', '370290.97', '99', '73', '2017-12-21');
INSERT INTO `tb_sales_data` VALUES ('fbde8158-e383-4058-9199-50fe7488badb', '1228', '8095418.00', '720', '600816.26', '101', '62', '2017-12-18');
INSERT INTO `tb_sales_data` VALUES ('fec0a698-deef-428c-90f5-92a995206432', '45', '21610.82', '26', '6637.23', '140', '62', '2018-01-03');
INSERT INTO `tb_sales_data` VALUES ('ffa7ef73-6394-43bc-82f0-a4177da3a4ce', '136', '213133.12', '106', '72578.72', '61', '26', '2017-11-24');

-- ----------------------------
-- Table structure for tb_stat
-- ----------------------------
DROP TABLE IF EXISTS `tb_stat`;
CREATE TABLE `tb_stat` (
  `id` varchar(50) NOT NULL,
  `stat_day` varchar(20) NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `busy_time` timestamp NULL DEFAULT NULL,
  `free_time` timestamp NULL DEFAULT NULL,
  `leave_time` timestamp NULL DEFAULT NULL,
  `person_id` varchar(255) DEFAULT NULL,
  `statu_busy` int(11) DEFAULT NULL,
  `statu_free` int(11) DEFAULT NULL,
  `statu_leave` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `biz_code` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stat
-- ----------------------------
INSERT INTO `tb_stat` VALUES ('0247f4da-0653-44b2-a555-ede256ba3c8d', '2017-07-31', '2017-07-31 21:31:07', '2017-07-31 00:14:40', null, '2017-07-31 21:31:07', null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '48267', '6763', '21557');
INSERT INTO `tb_stat` VALUES ('05d87402-3e61-44a8-b107-cd65a5f637f4', '2017-08-02', '2017-08-02 20:31:19', '2017-08-02 20:25:26', null, '2017-08-02 20:31:19', null, '9424aecb-eb48-4bad-aaf3-c151e059d4e1', '160', '190', '3');
INSERT INTO `tb_stat` VALUES ('0695d1e7-6e31-474b-a6f3-386f5a448387', '2017-08-02', '2017-08-02 20:26:48', '2017-08-02 12:25:37', null, '2017-08-02 20:26:48', null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '168', '28703', '0');
INSERT INTO `tb_stat` VALUES ('07c0646f-6cac-4747-ac2a-9ce332188dd3', '2017-08-27', '2017-08-27 16:02:01', '2017-08-27 16:01:56', null, '2017-08-27 16:02:01', null, '88458096-3241-4ff4-81e6-d2d1009eb392', '5', '0', '0');
INSERT INTO `tb_stat` VALUES ('0a379feb-2f4d-416b-8e3f-ef4844e668bb', '2017-08-22', '2017-08-22 16:14:53', '2017-08-22 16:14:49', null, '2017-08-22 16:14:53', null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '3', '0', '1');
INSERT INTO `tb_stat` VALUES ('0b10c40c-f875-4489-8230-0a986f001e26', '2017-06-27', '2017-06-27 15:32:35', '2017-06-27 15:32:35', null, '2017-06-27 15:32:35', null, 'e501f6e0-e643-4300-ad24-390fbb664219', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('1', '2017-01-01', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('10', '2017-01-05', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('100', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('101', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('102', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('103', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('104', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('105', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('106', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('107', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('108', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('109', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('11', '2017-01-06', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('110', '2017-01-26', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('111', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('112', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('113', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('114', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('115', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('116', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('117', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('118', '2017-01-27', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('119', '2017-01-28', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('12', '2017-01-07', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('120', '2017-01-28', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('121', '2017-01-28', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('122', '2017-01-29', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('123', '2017-01-29', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('124', '2017-01-29', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('125', '2017-01-29', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('126', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('127', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('128', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('129', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('13', '2017-01-07', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('130', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('131', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('132', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('133', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('134', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('135', '2017-01-30', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('136', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('137', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('138', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('139', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('14', '2017-01-07', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('140', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('141', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('142', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('143', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('144', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('145', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('146', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('147', '2017-01-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('15', '2017-01-08', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('16', '2017-01-08', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('16aab5bc-df2f-427f-8863-10daceb3d24f', '2017-08-31', '2017-08-31 21:13:28', '2017-08-31 11:26:40', '2017-08-31 21:13:28', null, null, 'f10b5288-e85d-4b86-a663-fd8d9d9c874f', '1984', '10766', '22458');
INSERT INTO `tb_stat` VALUES ('17', '2017-01-08', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('18', '2017-01-08', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('183491b5-6cfd-464b-a48c-2051dcd2349c', '2017-07-30', '2017-07-30 23:04:09', '2017-07-30 22:57:02', null, '2017-07-30 23:04:09', null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '427', '0', '0');
INSERT INTO `tb_stat` VALUES ('184', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('185', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('186', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('187', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('188', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('189', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('19', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('190', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('191', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('192', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('193', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('194', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('195', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('196', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('197', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('198', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('199', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('1d2c7ad5-520e-4742-ab2c-81d88f921571', '2017-07-28', '2017-07-28 14:51:36', '2017-07-28 14:51:36', '2017-07-28 14:51:36', null, null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('1ed21651-af13-4367-98af-88f039c891c7', '2017-08-23', '2017-08-23 16:48:19', '2017-08-23 16:36:28', null, '2017-08-23 16:48:19', null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '0', '457', '254');
INSERT INTO `tb_stat` VALUES ('2', '2017-01-01', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('20', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('200', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('201', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('202', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('203', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('204', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('205', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('206', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('207', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('208', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('209', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('21', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('210', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('211', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('212', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('213', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('214', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('215', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('216', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('217', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('218', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('219', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('22', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('220', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('221', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('222', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('223', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('224', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('224fa159-bb2c-4053-b5ff-eae3af26979b', '2017-07-29', '2017-07-29 19:02:29', '2017-07-29 11:12:20', '2017-07-29 19:02:29', null, null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '24256', '3788', '165');
INSERT INTO `tb_stat` VALUES ('225', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('226', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('227', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('228', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('229', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('22c7905e-1981-481c-800e-833a4218c3f7', '2017-08-28', '2017-08-28 09:31:26', '2017-08-28 09:31:26', '2017-08-28 09:31:26', null, null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('23', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('230', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('231', '2017-03-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('24', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('25', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('25d645a3-71ec-43d7-af67-e18fc0064d0d', '2017-07-27', '2017-07-27 13:51:26', '2017-07-27 13:14:13', '2017-07-27 13:51:26', null, null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '937', '1296', '0');
INSERT INTO `tb_stat` VALUES ('26', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('264', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('265', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('266', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('266c090f-9d7a-431a-b9ea-e41eb6febd15', '2017-08-28', '2017-08-28 03:41:14', '2017-08-28 01:24:05', '2017-08-28 03:41:14', null, null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '113', '8116', '0');
INSERT INTO `tb_stat` VALUES ('267', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('268', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('269', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('27', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('270', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('271', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('272', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('273', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('274', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('275', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('276', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('277', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('278', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('279', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('28', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('280', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('281', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('282', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('283', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('284', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('285', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('286', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('287', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('288', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('289', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('29', '2017-01-09', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('290', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('291', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('292', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('293', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('294', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('295', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('296', '2017-05-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('2e38f4c9-2b29-46f3-a677-55beda795597', '2017-07-30', '2017-07-30 23:34:05', '2017-07-30 23:34:05', '2017-07-30 23:34:05', null, null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('3', '2017-01-02', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('30', '2017-01-10', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('31', '2017-01-10', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('32', '2017-01-10', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('329', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('33', '2017-01-11', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('330', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('331', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('332', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('333', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('334', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('335', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('336', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('337', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('338', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('339', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('34', '2017-01-11', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('340', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('341', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('342', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('343', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('344', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('345', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('346', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('347', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('348', '2017-07-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('349', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('35', '2017-01-11', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('350', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('351', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('352', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('353', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('354', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('355', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('356', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('357', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('358', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('359', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('35ed6809-c8cb-473f-b334-cb29c85b77f3', '2017-07-11', '2017-07-11 17:55:00', '2017-07-11 17:55:00', '2017-07-11 17:55:00', null, null, '9c2de2e7-03ae-4b86-9a71-a1792873dadd', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('36', '2017-01-12', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('360', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('361', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('362', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('363', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('364', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('365', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('366', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('367', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('368', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('369', '2017-08-31', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('37', '2017-01-12', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('38', '2017-01-12', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('386fc80c-f7bf-4245-b18e-55140fce029f', '2017-07-27', '2017-07-27 13:48:49', '2017-07-27 12:41:58', '2017-07-27 13:48:49', null, null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '191', '3817', '3');
INSERT INTO `tb_stat` VALUES ('38e33aad-4679-4922-b986-50b4ca48464c', '2017-08-23', '2017-08-23 16:43:52', '2017-08-23 16:36:45', null, '2017-08-23 16:43:52', null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '427', '0', '0');
INSERT INTO `tb_stat` VALUES ('38f57ff6-4022-4ac3-b582-15c3dbe300e1', '2017-07-28', '2017-07-28 14:52:36', '2017-07-28 14:52:36', '2017-07-28 14:52:36', null, null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('39', '2017-01-13', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('4', '2017-01-02', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('40', '2017-01-13', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('40b86495-e651-4ade-9b9a-af403802d06e', '2017-07-29', '2017-07-29 19:29:20', '2017-07-29 18:38:41', null, '2017-07-29 19:29:20', null, '9424aecb-eb48-4bad-aaf3-c151e059d4e1', '589', '147', '2303');
INSERT INTO `tb_stat` VALUES ('41', '2017-01-13', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('413b63e2-b4f1-41d0-bdb0-70ae9f00510d', '2017-09-08', '2017-09-08 21:04:56', '2017-09-08 20:47:13', null, null, '2017-09-08 21:04:56', '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '46', '34', '983');
INSERT INTO `tb_stat` VALUES ('41d0fa68-4af5-435f-9e47-72ae8ad3341f', '2017-07-27', '2017-07-27 14:03:41', '2017-07-27 12:47:26', null, '2017-07-27 14:03:41', null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '1397', '3165', '13');
INSERT INTO `tb_stat` VALUES ('42', '2017-01-13', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('43', '2017-01-13', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('44', '2017-01-14', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('45', '2017-01-14', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('46', '2017-01-14', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('47', '2017-01-15', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('47a2037b-80e7-4a1f-ae35-c0e1c9ed9d64', '2017-09-09', '2017-09-09 11:37:29', '2017-09-09 11:37:29', null, '2017-09-09 11:37:29', null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('48', '2017-01-15', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('49', '2017-01-15', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('4a430f5a-6143-4c93-b984-0648f4cd2248', '2017-08-31', '2017-08-31 22:40:14', '2017-08-31 22:40:14', null, null, '2017-08-31 22:40:14', 'aee023ad-7b41-4441-ab3d-db3b782af177', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('4b4db05f-fcc7-4eed-a892-6bada1207beb', '2017-06-23', '2017-06-23 15:09:26', '2017-06-23 12:15:59', '2017-06-23 15:09:26', null, null, 'e501f6e0-e643-4300-ad24-390fbb664219', '5', '17', '10385');
INSERT INTO `tb_stat` VALUES ('4cd85cdf-9e0a-4d9f-a87d-83f97f6ab558', '2017-07-30', '2017-07-30 23:29:00', '2017-07-30 23:29:00', '2017-07-30 23:29:00', null, null, '9424aecb-eb48-4bad-aaf3-c151e059d4e1', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('5', '2017-01-03', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('50', '2017-01-15', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('51', '2017-01-16', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('52', '2017-01-16', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('53', '2017-01-16', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('53e1c2ea-e250-4426-9ee3-a713d0581fba', '2017-07-31', '2017-07-31 21:55:58', '2017-07-31 00:13:34', null, '2017-07-31 21:55:58', null, '9424aecb-eb48-4bad-aaf3-c151e059d4e1', '56', '78080', '8');
INSERT INTO `tb_stat` VALUES ('54', '2017-01-17', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('55', '2017-01-17', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('554cdd87-2e1a-427d-a2f7-649f88723642', '2017-09-06', '2017-09-06 19:22:34', '2017-09-06 19:22:28', null, '2017-09-06 19:22:34', null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '6', '0', '0');
INSERT INTO `tb_stat` VALUES ('56', '2017-01-18', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('57', '2017-01-18', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('58', '2017-01-18', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('59', '2017-01-19', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('5c9cc468-dcfe-42ea-a191-fd4c3cfbb64c', '2017-09-07', '2017-09-07 11:08:18', '2017-09-07 10:43:35', null, '2017-09-07 11:08:18', null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '0', '0', '1483');
INSERT INTO `tb_stat` VALUES ('6', '2017-01-03', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('60', '2017-01-19', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('61', '2017-01-19', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('616dbae7-0942-4d66-ad09-4af034430f1b', '2017-08-25', '2017-08-25 10:16:41', '2017-08-25 10:16:35', null, '2017-08-25 10:16:41', null, '2843d174-18cb-49ff-84c3-de10e58b4a8c', '6', '0', '0');
INSERT INTO `tb_stat` VALUES ('62', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('63', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('64', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('65', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('66', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('67', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('6751fd0f-338a-4635-b7c9-7c579f4fa5d4', '2017-08-27', '2017-08-27 16:31:42', '2017-08-27 16:29:57', null, '2017-08-27 16:31:42', null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '102', '0', '3');
INSERT INTO `tb_stat` VALUES ('68', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('68f0a9af-304e-4440-aefa-ff5f3ebc35bd', '2017-08-31', '2017-08-31 22:40:00', '2017-08-31 22:40:00', null, null, '2017-08-31 22:40:00', 'eda370ed-90a0-4679-928d-a386c7fb7342', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('69', '2017-01-20', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('6fb64094-91ca-400e-9363-a9d7f948b124', '2017-07-27', '2017-07-27 13:58:03', '2017-07-27 13:14:54', '2017-07-27 13:58:03', null, null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '975', '1611', '3');
INSERT INTO `tb_stat` VALUES ('7', '2017-01-04', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('70', '2017-01-21', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('70aa633d-a0c1-44ca-af70-f67b72644ec7', '2017-09-07', '2017-09-07 10:51:35', '2017-09-07 10:51:35', null, null, '2017-09-07 10:51:35', '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('71', '2017-01-21', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('71a1d799-99df-4daa-aa88-ecb64a8c7357', '2017-08-28', '2017-08-28 11:21:48', '2017-08-28 03:43:22', '2017-08-28 11:21:48', null, null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '4484', '23019', '3');
INSERT INTO `tb_stat` VALUES ('72', '2017-01-21', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('73', '2017-01-21', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('74', '2017-01-22', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('75', '2017-01-22', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('76', '2017-01-22', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('760f51a1-79cb-43f2-94bc-0f7c6c0cb08d', '2017-09-07', '2017-09-07 10:59:47', '2017-09-07 10:59:47', null, null, '2017-09-07 10:59:47', 'aee023ad-7b41-4441-ab3d-db3b782af177', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('77', '2017-01-22', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('78', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('79', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('7a785a65-c5cf-4393-8bc6-f3ee5d879d5d', '2017-09-08', '2017-09-08 22:21:49', '2017-09-08 22:21:47', null, '2017-09-08 22:21:49', null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '2', '0', '0');
INSERT INTO `tb_stat` VALUES ('7ffcfa39-8ad6-4f4d-82ef-bfeddee8e73b', '2017-08-23', '2017-08-23 16:33:42', '2017-08-23 14:35:00', '2017-08-23 16:33:42', null, null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '7121', '1', '0');
INSERT INTO `tb_stat` VALUES ('8', '2017-01-04', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('80', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('81', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('82', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('83', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('84', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('85', '2017-01-23', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('86', '2017-01-24', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('87', '2017-01-24', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('88', '2017-01-24', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('89', '2017-01-24', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('8c9bcc61-db77-4f66-9c11-36d25ef60da1', '2017-07-28', '2017-07-28 14:49:04', '2017-07-28 14:49:04', '2017-07-28 14:49:04', null, null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('9', '2017-01-05', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('90', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('91', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('92', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('93', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('94', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('95', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('96', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('97', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('978301c7-45be-45a3-989c-85461f3bcb6e', '2017-07-27', '2017-07-27 13:58:33', '2017-07-27 13:29:59', '2017-07-27 13:58:33', null, null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '993', '721', '0');
INSERT INTO `tb_stat` VALUES ('98', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('99', '2017-01-25', null, null, null, null, null, null, '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('9c9f501e-bb0a-45ad-b428-1e932238c08a', '2017-09-07', '2017-09-07 10:56:06', '2017-09-07 10:56:06', null, null, '2017-09-07 10:56:06', '7ee01c5f-d394-4090-9725-254fdd5e72a5', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('a464e098-0d4c-4282-9749-f9f46bdb58ef', '2017-07-25', '2017-07-25 13:31:39', '2017-07-25 13:31:39', '2017-07-25 13:31:39', null, null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('aa6ae57d-0ff9-4c9c-9538-f462b5b21ad3', '2017-09-06', '2017-09-06 19:26:01', '2017-09-06 18:48:31', null, '2017-09-06 19:26:01', null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '3', '2242', '5');
INSERT INTO `tb_stat` VALUES ('b249d23a-b295-4e26-86cc-85fe7d246fb5', '2017-08-23', '2017-08-23 16:37:18', '2017-08-23 16:33:43', null, '2017-08-23 16:37:18', null, '2a8f1fb2-f713-49f8-8c19-9460e27645e9', '19', '174', '22');
INSERT INTO `tb_stat` VALUES ('b4b201b8-0b71-41e7-89f1-26105ff15a5b', '2017-08-17', '2017-08-17 13:20:48', '2017-08-17 13:20:46', null, '2017-08-17 13:20:48', null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '2', '0', '0');
INSERT INTO `tb_stat` VALUES ('be567bcf-a389-4b8c-9b1b-8c72633bd763', '2017-07-11', '2017-07-11 17:20:15', '2017-07-11 17:13:01', null, '2017-07-11 17:20:15', null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '54', '380', '0');
INSERT INTO `tb_stat` VALUES ('c04547d8-ecaf-4267-afd0-d9dc7630847d', '2017-07-31', '2017-07-31 00:16:53', '2017-07-31 00:15:33', null, '2017-07-31 00:16:53', null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '80', '0', '0');
INSERT INTO `tb_stat` VALUES ('c0dbadac-ab88-4903-906f-6f265e5be252', '2017-09-09', '2017-09-09 11:39:00', '2017-09-09 11:36:46', null, '2017-09-09 11:39:00', null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '0', '0', '134');
INSERT INTO `tb_stat` VALUES ('c0de376a-2bf3-48af-b880-5fc76a440126', '2017-09-06', '2017-09-06 20:09:39', '2017-09-06 20:09:39', null, '2017-09-06 20:09:39', null, 'eda370ed-90a0-4679-928d-a386c7fb7342', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('c8af9ccc-ae12-4e0c-bfea-8b1cc4cc1d50', '2017-08-27', '2017-08-27 16:34:40', '2017-08-27 16:26:01', null, '2017-08-27 16:34:40', null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '0', '2', '517');
INSERT INTO `tb_stat` VALUES ('c9593edf-5591-4197-b5bc-33344bef4757', '2017-08-07', '2017-08-07 14:29:22', '2017-08-07 14:29:22', null, null, '2017-08-07 14:29:22', '9424aecb-eb48-4bad-aaf3-c151e059d4e1', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('cc7e3710-719b-4f76-911f-180e40d36dd8', '2017-08-23', '2017-08-23 16:44:28', '2017-08-23 15:15:09', null, '2017-08-23 16:44:28', null, 'aee023ad-7b41-4441-ab3d-db3b782af177', '126', '10', '5223');
INSERT INTO `tb_stat` VALUES ('cdd0276e-7a90-4a23-a044-787766eb6111', '2017-09-06', '2017-09-06 19:28:01', '2017-09-06 19:28:01', '2017-09-06 19:28:01', null, null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('d0a25b18-f03f-44f9-aab4-5f0fd74ca072', '2017-09-07', '2017-09-07 10:43:21', '2017-09-07 10:43:21', null, null, '2017-09-07 10:43:21', '677ff2ad-d97d-4b00-ad2e-ae4793210899', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('d27ada6d-897d-4cdb-adc4-352e7ef9bd15', '2017-07-25', '2017-07-25 18:54:11', '2017-07-25 15:33:11', null, '2017-07-25 18:54:11', null, '9c2de2e7-03ae-4b86-9a71-a1792873dadd', '2492', '2657', '6911');
INSERT INTO `tb_stat` VALUES ('d4e3eb37-9a75-4f22-a6e0-a66c653cd9c3', '2017-07-28', '2017-07-28 14:50:34', '2017-07-28 14:50:34', '2017-07-28 14:50:34', null, null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('d64550fd-ee8a-4fb9-bb07-e7e54fcad0bd', '2017-08-23', '2017-08-23 16:37:15', '2017-08-23 16:36:48', null, '2017-08-23 16:37:15', null, '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '27', '0', '0');
INSERT INTO `tb_stat` VALUES ('db8afee4-1755-450a-9602-2cea0e2247ba', '2017-09-07', '2017-09-07 23:30:53', '2017-09-07 10:43:46', null, null, '2017-09-07 23:30:53', 'eda370ed-90a0-4679-928d-a386c7fb7342', '1077', '44950', '0');
INSERT INTO `tb_stat` VALUES ('dd86363a-a054-4669-bc24-8c3a471db83a', '2017-07-27', '2017-07-27 14:04:05', '2017-07-27 12:44:42', null, '2017-07-27 14:04:05', null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '1305', '3427', '31');
INSERT INTO `tb_stat` VALUES ('df58c694-04b4-4f40-9539-8a7a3a8d005a', '2017-07-25', '2017-07-25 17:55:41', '2017-07-25 17:34:29', null, null, '2017-07-25 17:55:41', '7ee01c5f-d394-4090-9725-254fdd5e72a5', '1006', '266', '0');
INSERT INTO `tb_stat` VALUES ('e3eee742-e271-4766-aa2c-12db1c5fa504', '2017-08-30', '2017-08-30 11:31:08', '2017-08-30 11:31:08', null, '2017-08-30 11:31:08', null, '677ff2ad-d97d-4b00-ad2e-ae4793210899', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('f0988eed-b211-4312-975f-0721e1e36d50', '2017-09-06', '2017-09-06 19:24:54', '2017-09-06 19:24:54', null, '2017-09-06 19:24:54', null, 'f10b5288-e85d-4b86-a663-fd8d9d9c874f', '0', '0', '0');
INSERT INTO `tb_stat` VALUES ('f2ca33e8-2578-4c88-8e75-a4e1b1b0f6d0', '2017-07-31', '2017-07-31 21:49:12', '2017-07-31 21:28:36', null, null, '2017-07-31 21:49:12', '6c284c9c-49d7-46aa-a003-4f2f79f47e2a', '468', '761', '7');
INSERT INTO `tb_stat` VALUES ('f529ec15-a51d-4695-b20e-7fdd01648d71', '2017-07-29', '2017-07-29 18:37:02', '2017-07-29 11:59:31', '2017-07-29 18:37:02', null, null, '7ee01c5f-d394-4090-9725-254fdd5e72a5', '0', '23851', '0');
INSERT INTO `tb_stat` VALUES ('f855f524-c437-47c2-81d9-82dc01e76e35', '2017-07-29', '2017-07-29 19:05:07', '2017-07-29 19:05:07', '2017-07-29 19:05:07', null, null, '30e40e94-0d79-4148-8d08-65eecb8cd1a7', '0', '0', '0');

-- ----------------------------
-- Table structure for tb_station
-- ----------------------------
DROP TABLE IF EXISTS `tb_station`;
CREATE TABLE `tb_station` (
  `id` varchar(50) NOT NULL,
  `station_id` varchar(255) DEFAULT NULL,
  `station_code` varchar(255) DEFAULT NULL,
  `station_name` varchar(255) DEFAULT NULL,
  `is_gs` varchar(255) DEFAULT NULL,
  `isthird` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `point_status` tinyint(2) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `cdz_num` int(11) DEFAULT '0',
  `cdz_used_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_station
-- ----------------------------
INSERT INTO `tb_station` VALUES ('099ec3dd-03ee-4bf1-ae22-ce17242f16e0', '2588', '33402', '浙江省嘉兴市南湖区城北路充电站', '0', '0', '1', '0', '120.738772', '30.779251', '8', '8');
INSERT INTO `tb_station` VALUES ('227aadbd-5173-4cbc-8920-cbed969a1103', '2439', '33402', '沪昆高速嘉兴服务区充电站（昆明方向）', '0', '0', '1', '0', '120.675044', '30.620893', '4', '4');
INSERT INTO `tb_station` VALUES ('3e0e9eab-04f0-41bb-9f0f-60c58aac7e2e', '2428', '33402', '沪昆高速嘉兴服务区充电站（上海方向）', '0', '0', '1', '0', '120.677244', '30.620723', '4', '4');
INSERT INTO `tb_station` VALUES ('3f123c83-f667-47a2-a6b7-2a6418a4c492', '2593', '33402', '浙江省嘉兴市桐乡市乌镇东栅景区停车场充电站', '0', '0', '1', '0', '120.49724758', '30.73823477', '8', '8');
INSERT INTO `tb_station` VALUES ('3f292791-858f-4b64-a274-67a4e5f4cf7f', '2595', '33402', '浙江省嘉兴市平湖市案山晓翠景区停车场充电站', '0', '0', '1', '0', '121.030496', '30.695243', '8', '8');
INSERT INTO `tb_station` VALUES ('46dc8719-ff1f-43fa-a163-41964b06488d', '2502', '33402', '浙江省嘉兴市南湖区南湖红船电力服务队充电站', '0', '0', '1', '0', '120.77461', '30.729539', '8', '8');
INSERT INTO `tb_station` VALUES ('4b8f834d-f0f6-479e-9811-b39f7718d49a', '2594', '33402', '浙江省嘉兴市海盐县海兴东路配套停车场充电站', '0', '0', '1', '0', '120.953153', '30.534686', '8', '8');
INSERT INTO `tb_station` VALUES ('5e76ba28-104d-44f5-bf75-61b6a10029eb', '2599', '33402', '浙江省嘉兴市南湖区会景园停车场充电站', '0', '0', '1', '0', '120.765016', '30.749847', '8', '8');
INSERT INTO `tb_station` VALUES ('71115c9e-3ae6-44c8-8309-5cf817bd99d3', '2597', '33402', '浙江省嘉兴市嘉善县善文化主题公园充电站', '0', '0', '1', '0', '120.920087', '30.835732', '8', '8');
INSERT INTO `tb_station` VALUES ('82b9d29f-4071-440d-98d6-16f978b1b963', '2510', '33402', '浙江省嘉兴市秀洲区秀洲供电营业厅充电站', '0', '0', '1', '0', '120.706718', '30.763472', '8', '8');
INSERT INTO `tb_station` VALUES ('94e2ff4a-3b84-4cb5-852c-c04535268df2', '2601', '33402', '浙江省嘉兴市港区国家级综合保税区充电站', '0', '0', '1', '0', '121.059221', '30.623868', '8', '8');
INSERT INTO `tb_station` VALUES ('9963ba04-6727-4e68-a149-305c66d75cec', '2586', '33402', '浙江省嘉兴市秀洲区洪合毛衫城充电站', '0', '0', '1', '0', '120.665083', '30.677637', '8', '8');
INSERT INTO `tb_station` VALUES ('f5f95111-4699-4381-a65c-fa50ee1b1a7a', '2598', '33402', '浙江省嘉兴市海宁市体育馆充电站', '0', '0', '1', '0', '120.6732287955', '30.5079098687', '8', '8');

-- ----------------------------
-- Table structure for tb_subject
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject`;
CREATE TABLE `tb_subject` (
  `id` varchar(50) NOT NULL,
  `content` varchar(2000) DEFAULT '',
  `explain_detail` varchar(2000) DEFAULT '',
  `type` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject
-- ----------------------------
INSERT INTO `tb_subject` VALUES ('103d4387-5c4c-4f80-a5da-5c8b3c820346', '居委会等无组织机构代码证的至少维护一条客户类型为非法人组织机构 的客户联系信息，至少维护客户关系包含（    ）', null, '2');
INSERT INTO `tb_subject` VALUES ('197654df-57a4-451a-a4ae-5e62ff19865f', '居民新装无工程（  ）内装表接电；有工程（  ）内装表接电。', null, '1');
INSERT INTO `tb_subject` VALUES ('1a49ac16-84d7-4917-a825-59b9aee01190', '客户在意见箱（簿）反映的意见和建议要求在(   )个工作日内填写反馈意见', null, '1');
INSERT INTO `tb_subject` VALUES ('1df11f30-6b72-45fb-8120-1080b3921322', '实名充电卡密码连续（）次错误，充电卡将锁卡，需到营业厅解锁。非实名充电卡无密码。', null, '1');
INSERT INTO `tb_subject` VALUES ('26a2618c-a3db-4cc0-888d-2f65c64fc5ae', '嘉兴地区目前已于5个高速公路服务区，建成高速公路快充站(   )座，共计40个桩', null, '1');
INSERT INTO `tb_subject` VALUES ('272fa9fe-527e-4acc-a7c3-08c6e638fb07', '用户5月20日申请基本电价计费方式从容量改需量，按照相关规定，用户将在 （    ）月份按照需量计算基本电费', null, '1');
INSERT INTO `tb_subject` VALUES ('29727d9f-b636-4d62-b12e-7a4c9295b3bc', '低压充电桩答复供电方案工作时限：在受理之日起低压客户（）个工作日', null, '1');
INSERT INTO `tb_subject` VALUES ('2db4641f-1604-407d-b06b-56376c956082', '低压居民过户流程合并哪几个环节至新的“业务受理”环节(    )', null, '2');
INSERT INTO `tb_subject` VALUES ('341a112d-73af-493a-9d7d-57e2ba3e79a3', '充电桩答复供电方案工作时限：在受理之日起低压客户不超过1个工作日）高压客户不超过（  ）', null, '1');
INSERT INTO `tb_subject` VALUES ('3c99a4fa-3e9d-4ee6-9b86-487d03a21556', '全国文明单位创建（  ）年为学习巩固年', null, '1');
INSERT INTO `tb_subject` VALUES ('3cc87d06-3f7d-458f-a044-efe11a8df43b', '增值税专用发票由国网电动汽车公司统一开具并于（  ）个工作日内免费邮寄。', null, '1');
INSERT INTO `tb_subject` VALUES ('422019fe-b9a4-41fc-8bc3-4fa6b29864af', '充电卡有效期为（）年，自办理之日起计算。每次充值后将自动从充值之日起重新计算。', null, '1');
INSERT INTO `tb_subject` VALUES ('4356a71f-bbcc-4700-b11e-d1b3233ef21d', '党政机关、企事业单位和社会公共停车场中设置的非经营性充电设施用电，执行（   ）价格。', null, '1');
INSERT INTO `tb_subject` VALUES ('4b8b4a01-9d61-4fbb-b578-d8e1629ba425', '客户通过网站申请发票，平台在（  ）个工作日内审核。', null, '1');
INSERT INTO `tb_subject` VALUES ('4ddd8ff1-0095-44f1-80cf-e9cd2f3610c0', '居民客户申请校表，现场拆回的电能计量装置应在表库至少存放(   ) 个月，以便客户提出异议时进行复核', null, '1');
INSERT INTO `tb_subject` VALUES ('50b5de8a-04fa-4a55-ae0f-ad9cd5b6866e', '基本电价计费方式以三个月为一个变更周期。需提前（   ）个工作日向电网企业申请变更下一周期的基本电价计费方式', null, '1');
INSERT INTO `tb_subject` VALUES ('6509368b-7a65-4333-bbbc-252c922096d7', '电子资料可通过（       ）等渠道，以传真、电子文件上传的方式收取。对线上受理所缺的资料，服务调度人员通过电话告知客户具体缺件内容，由业务人员上门统一收资', null, '2');
INSERT INTO `tb_subject` VALUES ('67486cbb-2ecc-4f21-9489-7cb113b50fd4', '居民家庭“一户多人口”阶梯电价自办理后（  ）月内有效', null, '1');
INSERT INTO `tb_subject` VALUES ('67fb43f5-3db7-451c-86be-dc9f38f25dd1', '充电卡有效期为（）年，自办理之日起计算。每次充值后将自动从充值之日起重新计算。', null, '1');
INSERT INTO `tb_subject` VALUES ('6a1667c6-e50f-458d-b94e-0a273218d1ca', '客户通过网站申请发票，平台在（  ）个工作日内审核。', null, '1');
INSERT INTO `tb_subject` VALUES ('6bcf1b1f-7be8-422e-9c55-c2d54a302c5e', '从2016年(   )起开始改变电费通知方式，取消纸质账单投递', null, '1');
INSERT INTO `tb_subject` VALUES ('7195380b-8cc8-4bc5-bfc7-96547a9caa4a', '非实名充电卡不可换卡、挂失、销卡退费，卡内余额不能超过（）元。非实名充电卡可在营业厅转换为实名卡。', null, '1');
INSERT INTO `tb_subject` VALUES ('73bd314a-f28d-477b-a53b-e8a3a5eb7485', '（）年是公司创建全国文明单位关键时期', null, '1');
INSERT INTO `tb_subject` VALUES ('8c06e539-8959-43e0-8703-a8e5a36c9179', '电力用户（含新装、增容用户）可根据用电需求变化情况，提前（   ）个工作日向电网企业申请减容、暂停、减容恢复、暂停恢复用电', null, '1');
INSERT INTO `tb_subject` VALUES ('8f0d821e-6200-4ad5-a397-0fcf8dafc4db', '低压居民新装时限:无工程2个工作日 ，有工程(    )', null, '1');
INSERT INTO `tb_subject` VALUES ('9183d956-fc64-44e4-b6b3-a1cb42cf1f97', '增值税专用发票由国网电动汽车公司统一开具并于(  )工作日内免费邮寄。', null, '1');
INSERT INTO `tb_subject` VALUES ('94662121-ced2-4357-ab13-b49d00dcaf8a', '高压充电桩答复供电方案工作时限:（    ）个工作日', null, '1');
INSERT INTO `tb_subject` VALUES ('9b17a3a2-0e2b-48ed-9cbe-2440ae15aa5f', '客户身份证明包括： (     )', null, '2');
INSERT INTO `tb_subject` VALUES ('9d678014-0d5f-4595-8bc7-d5746b836ad9', '低压充电桩答复供电方案工作时限：在受理之日起低压客户（）个工作日', null, '1');
INSERT INTO `tb_subject` VALUES ('a3891160-2ec1-4327-80c5-7fe98ef0c2d6', '充电卡最多支持(  ）次灰锁记录，累计达到（  ）次，充电卡将无法使用。', null, '1');
INSERT INTO `tb_subject` VALUES ('a3c26c40-0262-4d9f-8fe8-65eb9fbd0771', '本次差别化电价执行时间从（   ）起，具体按电价表中相应的电价每千瓦时加价0.1元、0.2元、0.3元', null, '1');
INSERT INTO `tb_subject` VALUES ('a3f10bb8-d616-4c0f-9e7b-6382371da8af', '采用充电卡方式充电需先到指定营业厅办理国家电网公司充电卡，\n充电卡分为实名制和非实名制两种，开卡不收取押金，首次充值不少于（  ）元。', null, '1');
INSERT INTO `tb_subject` VALUES ('aa1d5794-69cd-47df-9bf1-1eb3303a6495', '对于单独报装接电的经营性集中式充换电设施用电，执行（）价格，2020年前暂免收基本电费', null, '1');
INSERT INTO `tb_subject` VALUES ('ae49f243-ec10-42c6-a0cd-79d411eef0d8', '将(         )设定为公司电动汽车充电服务业务市场推广期', null, '1');
INSERT INTO `tb_subject` VALUES ('b999a410-179f-4d1a-870a-e9ad2d265e44', '高压充电桩答复供电方案工作时限:（    ）个工作日', null, '1');
INSERT INTO `tb_subject` VALUES ('be15ca0c-5743-4ca9-b895-bda237365f0a', '全国文明单位创建（  ）年为申报考评年。', null, '1');
INSERT INTO `tb_subject` VALUES ('c29c96b7-e884-4d66-9d5a-1d64cbd4b293', '实名充电卡密码连续（）次错误，充电卡将锁卡，需到营业厅解锁。非实名充电卡无密码。', null, '1');
INSERT INTO `tb_subject` VALUES ('c628c3ba-7b9b-43f3-85cc-71f4ba34f577', '居民新装无工程（  ）个工作日内装表接电；有工程（   ）个工作日内装表接电', null, '1');
INSERT INTO `tb_subject` VALUES ('c79fb1f6-22fa-4ab2-8ba8-9178abe15ff2', '改类适用于客户在同一受电装置内，电力用途发生变化等原因而引起', null, '2');
INSERT INTO `tb_subject` VALUES ('c7e9c472-096e-43ce-aa0d-446c438ea16b', '实名充电卡可换卡、补卡、销卡退费，可设置密码，\n可与“e充电”电子账户进行关联，卡内余额不能超过（  ）元；', null, '1');
INSERT INTO `tb_subject` VALUES ('dbcbc3dd-df39-436f-a00a-b8f7a71a5b90', '以下哪些可以选择分时电价并在12个月内保持不变（    ）', null, '2');
INSERT INTO `tb_subject` VALUES ('e04415d9-ca56-45b0-a27d-40427c49748e', '增值税专用发票由国网电动汽车公司统一开具并于(  )工作日内免费邮寄。', null, '1');
INSERT INTO `tb_subject` VALUES ('e1271d12-c603-4f70-8491-37ce8af17e92', '个人客户充值时不开具发票，实际充电后（  )内可申请开具增值税普通发票或增值税专用发票，', null, '1');
INSERT INTO `tb_subject` VALUES ('e9d8a4b9-c98e-42cf-a150-51979a06d016', '高压充电桩竣工检验工作时限:（  ）个工作日', null, '1');
INSERT INTO `tb_subject` VALUES ('f0fec926-834a-430a-bdd0-b4a23c80e9de', '经水利局认定为农村圩区用电的用电类别污水处理(**污水处理公司来办理)：用电类别：（    ）', null, '1');
INSERT INTO `tb_subject` VALUES ('f659eb4a-94fe-4fa5-953f-2bac55c8a18e', '高压充电桩竣工检验工作时限:（  ）个工作日', null, '1');

-- ----------------------------
-- Table structure for tb_subject_wrong
-- ----------------------------
DROP TABLE IF EXISTS `tb_subject_wrong`;
CREATE TABLE `tb_subject_wrong` (
  `id` varchar(50) NOT NULL,
  `subject_id` varchar(50) DEFAULT NULL,
  `personnel_id` varchar(50) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_subject_wrong
-- ----------------------------

-- ----------------------------
-- Table structure for tb_terminal
-- ----------------------------
DROP TABLE IF EXISTS `tb_terminal`;
CREATE TABLE `tb_terminal` (
  `id` varchar(50) NOT NULL,
  `status` varchar(10) DEFAULT '' COMMENT '运行状态',
  `serial_number` int(8) DEFAULT NULL COMMENT '编号',
  `name` varchar(50) DEFAULT '' COMMENT '终端名称',
  `code_name` varchar(50) DEFAULT '' COMMENT '别名',
  `ip` varchar(50) DEFAULT '' COMMENT 'ip地址',
  `mac` varchar(50) DEFAULT '' COMMENT '物理地址',
  `group_id` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_GROUP` (`group_id`),
  CONSTRAINT `FK_GROUP` FOREIGN KEY (`group_id`) REFERENCES `tb_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_terminal
-- ----------------------------
INSERT INTO `tb_terminal` VALUES ('e43bdf81-0891-4b58-8aa2-e0c4c58b82ba', 'false', '103', '', 'TYT1-103', '192.168.1.103', 'AC-2B-6E-17-50-16', '6daa4c79-c0bf-4e35-9f6d-156665263f38', null);

-- ----------------------------
-- Table structure for tb_terminal_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_terminal_content`;
CREATE TABLE `tb_terminal_content` (
  `id` varchar(50) NOT NULL,
  `terminal_id` varchar(50) NOT NULL COMMENT '终端id',
  `content_id` varchar(50) NOT NULL COMMENT '内容id',
  `model` tinyint(4) DEFAULT NULL COMMENT '播放模式',
  `sort` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TERMINAL` (`terminal_id`),
  KEY `FK_CONTENT` (`content_id`),
  CONSTRAINT `FK_CONTENT` FOREIGN KEY (`content_id`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_TERMINAL` FOREIGN KEY (`terminal_id`) REFERENCES `tb_terminal` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_terminal_content
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `real_name` varchar(50) DEFAULT NULL,
  `role` tinyint(4) DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('0', 'admin', '21232F297A57A5A743894A0E4A801FC3', '管理员', '0');

-- ----------------------------
-- Table structure for tb_work_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_work_position`;
CREATE TABLE `tb_work_position` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `position_name` varchar(50) DEFAULT NULL,
  `position` int(8) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_work_position
-- ----------------------------
INSERT INTO `tb_work_position` VALUES ('0', '咨询台-1', '0', '0');
INSERT INTO `tb_work_position` VALUES ('1', '咨询台-2', '1', '1');
INSERT INTO `tb_work_position` VALUES ('10', '大客户室', '10', '10');
INSERT INTO `tb_work_position` VALUES ('11', '值长', '11', '11');
INSERT INTO `tb_work_position` VALUES ('2', '大堂-1', '2', '2');
INSERT INTO `tb_work_position` VALUES ('3', '大堂-2', '3', '3');
INSERT INTO `tb_work_position` VALUES ('4', '业务窗口-1', '4', '4');
INSERT INTO `tb_work_position` VALUES ('5', '业务窗口-2', '5', '5');
INSERT INTO `tb_work_position` VALUES ('6', '业务窗口-3', '6', '6');
INSERT INTO `tb_work_position` VALUES ('7', '业务窗口-4', '7', '7');
INSERT INTO `tb_work_position` VALUES ('8', '业务窗口-5', '8', '8');
INSERT INTO `tb_work_position` VALUES ('9', '业务窗口-6', '9', '9');

-- ----------------------------
-- Table structure for tb_yj
-- ----------------------------
DROP TABLE IF EXISTS `tb_yj`;
CREATE TABLE `tb_yj` (
  `id` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `type` int(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT '',
  `bh` varchar(255) DEFAULT '',
  `content` varchar(3000) DEFAULT '',
  `add_time` datetime DEFAULT NULL,
  `flag_time` datetime DEFAULT NULL,
  `is_show` int(2) DEFAULT NULL,
  `flag` int(2) DEFAULT NULL,
  `feedback` varchar(3000) DEFAULT '',
  `has_fk` int(2) DEFAULT NULL,
  `feedback_time` datetime DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_yj
-- ----------------------------

-- ----------------------------
-- Table structure for tb_ykjd
-- ----------------------------
DROP TABLE IF EXISTS `tb_ykjd`;
CREATE TABLE `tb_ykjd` (
  `id` varchar(50) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `work_order_num` varchar(100) DEFAULT NULL,
  `dy_type` varchar(100) DEFAULT NULL,
  `elec_type` varchar(100) DEFAULT NULL,
  `xzrl` varchar(100) DEFAULT NULL,
  `zrl` varchar(100) DEFAULT NULL,
  `part1start_date` date DEFAULT NULL,
  `part1end_date` date DEFAULT NULL,
  `part1remain_day` int(11) DEFAULT NULL,
  `part2start_date` date DEFAULT NULL,
  `part2end_date` date DEFAULT NULL,
  `part2remain_day` int(11) DEFAULT NULL,
  `part3start_date` date DEFAULT NULL,
  `part3end_date` date DEFAULT NULL,
  `part3remain_day` int(11) DEFAULT NULL,
  `part4start_date` date DEFAULT NULL,
  `part4end_date` date DEFAULT NULL,
  `part4remain_day` int(11) DEFAULT NULL,
  `part5start_date` date DEFAULT NULL,
  `part5end_date` date DEFAULT NULL,
  `part5remain_day` int(11) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `process` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_ykjd
-- ----------------------------
INSERT INTO `tb_ykjd` VALUES ('022bbf4c-466e-40b6-896a-6abb1cbc4724', '广东珠江纺织博览中心有限公司', '08000010000008874250', '单电源', '高压增容', '14500', '40500', '2016-12-15', '2017-01-02', '0', '2017-07-12', '2017-07-25', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('06ebe02b-0117-4039-81ef-96216bb64dd7', '广州市海珠区华洲街小洲经济联合社', '08000010000006084733', '单电源', '高压临时用电', '250', '250', '2016-07-11', '2016-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('07678c8a-858c-49db-9d24-312386d274b6', '广州市海珠区人民政府南石头街道办事处', '08000010000004993974', '单电源', '高压新装', '250', '250', '2016-06-07', '2016-06-23', '0', '2016-07-26', '2016-08-08', '0', '2016-09-12', '2016-09-14', '0', '2016-10-25', '2016-10-27', '0', '2016-10-27', '2016-11-02', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('0abf62e8-b799-4125-93eb-5d9fbfdfdf8c', '广州市海珠区江海街红卫第十二经济合作社', '08000010000010742535', '单电源', '高压新装', '400', '400', '2017-06-28', '2017-07-14', '0', '2017-08-23', '2017-09-05', '0', '2017-12-11', '2017-12-13', '0', '2017-12-13', '2017-12-15', '0', '2017-12-15', '2017-12-21', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('0c336e9f-8d88-475c-9711-2acc53e6fe5b', '国家海洋局南海分局', '08000010000025631128', '单电源', '高压增容', '2620', '3880', '2017-11-29', '2017-12-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('0cc196c6-8eaf-48b9-9e05-66b0e8086637', '广州海运（集团）房地产开发有限公司', '08000010000010915175', '单电源', '高压新装', '17600', '17600', '2017-07-10', '2017-07-26', '0', '2018-01-26', '2018-02-08', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('0d3fb934-d853-4448-a2ea-63448ad1887c', '广东百富城房地产开发有限公司', '08000010000023980833', '单电源', '高压增容', '0', '630', '2017-09-08', '2017-09-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('0e61ec70-6037-4353-89de-2af70359ec63', '广东工业大学', '08000010000026543226', '双电源', '高压增容', '400', '17090', '2018-02-11', '2018-05-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('0f06ddd2-0285-427e-b3a9-31339d74b890', '中国人民解放军92218部队', '08000010000024714358', '单电源', '高压增容', '485', '800', '2017-10-16', '2017-11-01', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('1224b60a-a3e7-45b2-92f3-9f53fb2fca71', '广州市长江企业集团有限公司长江百货交易城市场经营管理分公司', '08000010000006015761', '单电源', '高压增容', '550', '630', '2016-07-07', '2016-07-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('12a38a6c-8f5c-4f05-b274-0f36a3981926', '广州大学', '08000010000026369236', '单电源', '高压临时用电', '630', '630', '2018-01-22', '2018-02-07', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('148b9d0c-de13-4064-8f63-603e36700976', '广州市超级分布式能源投资有限公司', '08000010000006082527', '单电源', '高压临时用电', '400', '400', '2016-07-11', '2016-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('14ae032f-bb53-4724-adcc-0b3bacbbacd5', '广州供电局有限公司', '08000010000010467392', '单电源', '高压新装', '1630', '1630', '2017-06-02', '2017-06-20', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('15500ef6-6cdd-4d01-95d5-05959126c6cc', '广东省中医院', '08000010000010375263', '双电源', '高压新装', '8200', '8200', '2017-05-22', '2017-06-16', '0', '2017-12-27', '2018-01-10', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('17300055-af0a-4ad0-8566-2b8cfa751b96', '中铁广州工程局集团城轨工程有限公司', '08000010000025609439', '单电源', '高压临时用电', '315', '315', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('19f383df-73bf-4eb0-9cf6-759246695f61', '国家海洋局南海分局', '08000010000005726268', '单电源', '高压增容', '620', '2250', '2016-06-30', '2016-07-18', '0', '2016-09-06', '2016-09-19', '0', '2017-10-13', '2017-10-17', '0', '2017-10-16', '2017-10-18', '0', '2017-10-18', '2017-10-24', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('1a8ee16b-6621-4cd2-a597-b341a6fd5f22', '广州市交通委员会', '08000010000026220037', '单电源', '高压临时用电', '80', '80', '2018-01-09', '2018-01-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('1b3fda8c-df6d-48fa-89b3-eddb5a36b906', '广州市海珠区瑞宝街瑞宝第二经济合作社', '08000010000010975797', '单电源', '高压新装', '1250', '1250', '2017-07-13', '2017-07-31', '0', '2017-08-28', '2017-09-08', '0', '2017-11-01', '2017-11-03', '0', '2017-11-03', '2017-11-07', '0', '2017-11-07', '2017-11-13', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('1be5dc8c-51fc-4cb0-9796-403438ee8b27', '广州市海珠区河涌管理所', '08000010000006131469', '单电源', '高压新装', '160', '160', '2016-07-14', '2016-08-01', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('1c9e1c93-51b6-456a-a324-58739ef66d42', '广东省农业生产资料总公司', '08000010000004822850', '单电源', '高压增容', '300', '800', '2016-05-23', '2016-06-08', '0', '2016-07-06', '2016-07-19', '0', '2016-09-13', '2016-09-15', '0', '2017-04-11', '2017-04-13', '0', '2017-04-13', '2017-04-19', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('1d9acbfd-2163-453c-a6af-284dc82e6880', '广州市新光快速路有限公司', '08000010000006097780', '单电源', '高压增容', '115', '315', '2016-07-19', '2016-08-04', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('205d2256-4959-4340-8403-21675c6aca4a', '广东第二师范学院', '08000010000005757476', '单电源', '高压增容', '5800', '7300', '2016-07-19', '2016-08-04', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('207ae57d-854e-41a4-9f86-55da13ba9d11', '广东药学院', '08000010000010000181', '单电源', '高压增容', '1250', '3140', '2017-04-12', '2017-04-28', '0', '2017-07-13', '2017-07-26', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('211b2cd5-0887-4d65-924f-07da109736b5', '广州市海珠区华洲街小洲第九经济合作社', '08000010000026353845', '单电源', '高压新装', '1250', '1250', '2018-01-19', '2018-02-06', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('227e1512-d59a-4d0c-9629-3860d4b32c72', '中铁三局集团广东建设工程有限公司', '08000010000025506165', '单电源', '高压临时用电', '500', '500', '2017-11-22', '2017-12-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('2494b543-6963-4861-9814-8b7590afa897', '广州烟尘治理专业有限公司', '08000010000005994297', '双电源', '高压增容', '0', '11350', '2016-07-13', '2016-08-09', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('268e8c2b-c4a3-4a2f-8cab-f2466f02cc67', '保利房地产(集团)股份有限公司', '08000010000026592202', '单电源', '高压增容', '4000', '19500', '2018-02-27', '2018-05-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('289e46ca-4179-4144-8072-f69ef8fc1927', '广州市海珠区人民法院', '08000010000004712519', '单电源', '高压增容', '0', '4800', '2016-05-12', '2016-05-30', '0', '2016-11-14', '2016-11-25', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('2ba23878-256c-4d03-b0d5-279066324414', '广州开发区环卫美化服务中心 ', '08000010000006095031', '单电源', '高压新装', '630', '630', '2016-07-11', '2016-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('2cc4e406-ef75-4303-a056-4c4d93b54ffc', '广州市海珠区河涌管理所', '08000010000009770163', '单电源', '高压新装', '50', '50', '2017-03-20', '2017-04-05', '0', '2017-08-09', '2017-08-22', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('2cf86611-a167-48fb-933f-7a5447da2e9d', '广州市海珠区凤阳街凤和鹭江第五经济合作社', '08000010000025228381', '单电源', '高压新装', '1600', '1600', '2017-11-08', '2017-11-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('2d22bb45-fff9-4c09-a640-efe2ed8e787e', '广东省社会福利服务中心', '08000010000005821310', '单电源', '高压临时用电', '315', '315', '2016-07-05', '2016-07-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('2f5bd537-c3e7-4f37-867b-40ef51fd3b9e', '广东工业大学 ', '08000010000006036432', '单电源', '高压临时用电', '315', '315', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('30236035-9c36-4681-9083-b04ddf93748e', '广州市海珠区新港中路小学', '08000010000008178491', '单电源', '高压新装', '315', '315', '2016-10-31', '2016-11-16', '0', '2017-04-24', '2017-05-05', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('303576fd-2bde-4b32-9999-6732b9224a35', '广州市海珠区河涌管理所', '08000010000023290632', '单电源', '高压新装', '250', '250', '2017-08-04', '2017-08-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('30acdfed-2dd2-4b7e-8c88-7e6085e04c7c', '广州万力集团资产管理有限公司、广州万力集团资产管理有限公司钻石车胎厂', '08000010000024571059', '单电源', '高压新装', '6400', '6400', '2017-10-10', '2017-10-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('336ed65f-e5f5-4ef7-8d0e-f973b6d7ca7c', '广州市城市建设开发集团有限公司', '08000010000006044102', '单电源', '高压新装', '500', '500', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('344be783-97d0-4038-80c0-ad2fecf16642', '南方医科大学中西医结合医院', '08000010000005732549', '双电源', '高压增容', '4000', '8000', '2016-06-30', '2016-07-27', '0', '2017-05-24', '2017-06-06', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('3464d9f7-596b-4460-9ffd-deffe34afda3', '广州市交通站场建设管理中心', '08000010000008396262', '单电源', '高压增容', '4800', '8100', '2016-11-15', '2016-12-01', '0', '2017-01-18', '2017-01-31', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('35a8b2e6-0b1e-4e37-9b81-5c5ddd31cf15', '广州市番禺区小谷围街南亭村民委员会', '08000010000006117190', '单电源', '高压新装', '750', '750', '2016-07-13', '2016-07-29', '0', '2016-11-23', '2016-12-06', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('36022335-f5ab-4e0a-872a-95cb67f6a164', '广州开发区市政工程公司', '08000010000007928100', '单电源', '高压新装', '250', '250', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('365455b0-6edd-4fe2-8081-bb0ad602667e', '广州市海珠区瑞宝街石溪第七经济合作社', '08000010000024698495', '单电源', '高压新装', '630', '630', '2017-10-16', '2017-11-01', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3683e481-7ddc-490b-8fab-6e7aaee2bbb6', '广州开发区环卫美化服务中心 ', '08000010000006041715', '单电源', '高压新装', '200', '200', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('369d6f5f-ce8f-4aa9-a5f3-3b65f18fe9cd', '广州开发区市政工程公司', '08000010000007945143', '单电源', '高压新装', '250', '250', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('3804a322-cb6a-4bfb-b296-687a5cd30003', '广州市水务投资集团有限公司', '08000010000008838486', '单电源', '高压新装', '3200', '3200', '2016-12-13', '2016-12-29', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('393bd62e-149b-47e1-9113-3501c6cc878a', '广州市第二工人文化宫', '08000010000008851761', '双电源', '高压增容', '8397', '8750', '2016-12-14', '2017-01-10', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3a7b1ade-f1d7-4577-a6cb-a2d29efa4c52', '广州奥立得企业管理有限公司', '08000010000025021231', '单电源', '高压新装', '630', '630', '2017-10-27', '2017-11-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3aca1a02-e2f7-410a-bfbb-06769938e9e8', '广州市黄埔区教育局', '08000010000009872720', '单电源', '高压新装', '1250', '1250', '2017-03-28', '2017-04-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3b661fde-941b-4db0-bd96-055468b46f23', '中铁三局集团广东建设工程有限公司', '08000010000024914100', '单电源', '高压临时用电', '3000', '3000', '2017-10-23', '2017-11-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3d6ddd96-a559-4ec3-a1da-c60274a901e7', '广州市海珠区河涌管理所', '08000010000004649566', '单电源', '高压新装', '250', '250', '2016-05-04', '2016-05-20', '0', '2016-07-05', '2016-07-18', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('3eeb0dbb-cee0-4415-b378-8ebeec0ff173', '广州市海珠区官洲街赤沙经济联合社', '08000010000025623623', '单电源', '高压新装', '630', '630', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3f05d51a-7cb3-40b9-b5dc-7e80291467ff', '中铁广州工程局集团城轨工程有限公司', '08000010000026502775', '单电源', '高压临时用电', '2800', '2800', '2018-02-06', '2018-02-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3f2c4aa1-c71c-4fde-b7bf-b7492adedb14', '广东南传广场开发有限公司', '08000010000025348931', '单电源', '高压临时用电', '630', '630', '2017-11-14', '2017-11-30', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('3f74e601-7b18-45ac-b8ee-080502e87db2', '中铁三局集团广东建设工程有限公司', '08000010000024914037', '单电源', '高压临时用电', '500', '500', '2017-10-23', '2017-11-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('4093b7c9-8366-4d10-98bf-530e9b2178dd', '广州市海珠区河涌管理所', '08000010000006135009', '单电源', '高压新装', '315', '315', '2016-07-14', '2016-08-01', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('411d4da5-12ed-4628-a36f-1ff8538d5963', '广州地铁集团有限公司', '08000010000023697563', '单电源', '高压临时用电', '3150', '3150', '2017-08-24', '2017-09-11', '0', '2018-03-01', '2018-05-14', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('417b0af4-c284-4175-907e-9f8a85ecfd8b', '广州市公安局萝岗区分局', '08000010000006012212', '单电源', '高压新装', '250', '250', '2016-08-09', '2016-08-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('42583f30-467d-4942-8160-9763f09e3308', '广州市海珠区河涌管理所', '08000010000009546635', '单电源', '高压新装', '160', '160', '2017-02-23', '2017-03-13', '0', '2017-06-27', '2017-07-10', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('42c11139-2bb4-4b89-bf0e-58249c08fbe1', '广州云杉新能源技术有限公司', '08000010000025633536', '单电源', '高压新装', '1250', '1250', '2017-11-29', '2017-12-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('443dd890-b01f-4e44-aae9-232c3447a672', '广州市番禺区小谷围街穗石村民委员会', '08000010000006158627', '单电源', '高压新装', '750', '750', '2016-07-15', '2016-08-02', '0', '2016-11-10', '2016-11-23', '0', '2016-12-20', '2016-12-22', '0', '2016-12-23', '2016-12-27', '0', '2016-12-27', '2017-01-02', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('44636994-0c1b-40cd-8a64-9b40a13b0dbf', '广州市海珠区官洲街赤沙经济联合社', '08000010000025619191', '单电源', '高压新装', '630', '630', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('447ce1cb-5ff9-441e-928c-3c55638e8545', '广州开发区市政工程公司', '08000010000007928449', '单电源', '高压新装', '250', '250', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('4481e05d-a6f6-483b-bd57-dc8010a6afbd', '冯国昌', '08000010000026655667', '单电源', '高压新装', '630', '630', '2018-03-06', null, '4', null, null, null, null, null, null, null, null, null, null, null, null, '0', '1');
INSERT INTO `tb_ykjd` VALUES ('45126839-e03f-4e0c-b4b5-83222e4199a1', '中铁三局集团广东建设工程有限公司', '08000010000024912876', '单电源', '高压临时用电', '500', '500', '2017-10-23', '2017-11-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('4650109c-a601-4083-a835-e987bdb10ab1', '中铁隧道集团三处有限公司', '08000010000026115531', '单电源', '高压临时用电', '800', '800', '2017-12-28', '2018-01-16', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('465f424b-c614-4c5c-bd7f-d53d5c80ab6d', '广州大学城市政管理所', '08000010000004909273', '单电源', '高压新装', '630', '630', '2016-05-31', '2016-06-16', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('46be2d5f-346f-4992-8ae5-5b0979846c04', '广州科音信息科技有限公司', '08000010000010543178', '单电源', '高压临时用电', '630', '630', '2017-06-09', '2017-06-27', '0', '2018-01-02', '2018-01-15', '0', '2018-01-02', '2018-01-04', '0', '2018-01-04', '2018-01-08', '0', '2018-01-08', '2018-01-12', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('47dfb4e6-7660-4477-990d-d7b5e106aaef', '香格里拉大酒店(广州琶洲)有限公司', '08000010000025148882', '单电源', '高压增容', '2000', '8000', '2017-11-02', '2017-11-20', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('482190a3-b534-4353-8d63-87b684acd6b7', '广州地铁集团有限公司', '08000010000009663286', '单电源', '高压临时用电', '630', '630', '2017-03-08', '2017-03-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('4c10b733-9054-43fb-9651-0ffbfd6e6c9b', '广州供电局有限公司路灯管理所', '08000010000023487186', '单电源', '高压新装', '250', '250', '2017-08-16', '2017-09-01', '0', '2017-09-25', '2017-10-12', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('4fd51418-5dbf-4fb4-a40c-a556efd50b95', '广东开华经济发展有限公司 ', '08000010000005735646', '单电源', '高压新装', '250', '250', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('52ebbc2d-eb71-4a4e-bc85-0e0d7b4a6c3b', '广州市海珠区瑞宝街瑞宝第三经济合作社', '08000010000025609484', '单电源', '高压增容', '1036', '1250', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5459ab23-f9ac-417c-9c9a-17d789ee5626', '中铁三局集团广东建设工程有限公司', '08000010000024906765', '单电源', '高压临时用电', '6390', '6390', '2017-10-23', '2017-11-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('55afcffe-1a81-48fe-a2f5-fc32852c3805', '广州市文化广电新闻出版局', '08000010000004794817', '单电源', '高压新装', '2000', '2000', '2016-05-20', '2016-06-07', '0', '2016-05-30', '2016-06-10', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('562b2326-9b4e-4c5a-962a-fc727a48a5e3', '曾苏芬', '08000010000025851424', '单电源', '高压新装', '160', '160', '2017-12-13', '2017-12-29', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5991ce71-4020-4180-be27-2c3d283a080f', '广州地铁集团有限公司', '08000010000026242825', '单电源', '高压临时用电', '630', '630', '2018-01-10', '2018-01-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5adccdce-dce3-44e5-8127-8e81ef15f7d9', '广州市福泰置业有限公司', '08000010000026462301', '单电源', '高压增容', '0', '3200', '2018-02-01', '2018-02-23', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5b13aa17-89dd-4c77-8406-a055bf0b456d', '中国人民解放军第四八0一工厂', '08000010000024460156', '单电源', '高压增容', '1850', '5720', '2017-09-28', '2017-10-20', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5bac5599-c6d6-4648-99f8-291dcf866f2a', '广州市黄埔区教育局', '08000010000006018731', '单电源', '高压临时用电', '400', '400', '2016-07-07', '2016-07-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5df0885c-0aab-4b7a-bb03-348b185affd6', '广州地铁集团有限公司', '08000010000023697536', '单电源', '高压临时用电', '1260', '1260', '2017-08-24', '2017-09-11', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5e196241-d8e4-4169-b87f-d4af8c9277f8', '广州市海珠区官洲街北山第二经济合作社（仓库）', '08000010000024601465', '单电源', '高压增容', '235', '315', '2017-10-11', '2017-10-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('5e5e3dad-3aac-419a-8afe-180cc3b8da74', '广州珠江侨都房地产有限公司 ', '08000010000006026670', '单电源', '高压新装', '500', '500', '2016-07-07', '2016-07-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('606ef9c3-9b15-419d-b493-52d12f5b9a70', '广州鼎尚股份有限公司', '08000010000023341686', '双电源', '高压新装', '16400', '16400', '2017-08-08', '2017-09-04', '0', '2018-03-07', null, '2', null, null, null, null, null, null, null, null, null, '0', '3');
INSERT INTO `tb_ykjd` VALUES ('60a44c37-32ad-4ba2-8fef-b9ecbdfe5476', '中铁三局集团广东建设工程有限公司', '08000010000024913840', '单电源', '高压临时用电', '500', '500', '2017-10-23', '2017-11-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('611bbb80-1a45-4603-ba0a-60ffdf492ec8', '广州市海珠区水务工程设施养护所', '08000010000010522257', '单电源', '高压新装', '160', '160', '2017-06-08', '2017-06-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('612d670c-546a-47bf-a7ba-38c956061e7f', '瑞宝村第七经济合作社', '08000010000023804440', '单电源', '高压增容', '485', '800', '2017-08-30', '2017-09-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('64b44bdd-8a57-41c3-ab20-105d730f2607', '广州华晟房地产开发有限公司  ', '08000010000006022166', '单电源', '高压临时用电', '500', '500', '2016-07-07', '2016-07-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('64e78745-b5fe-4333-9e29-538eb1d8140f', '中铁二局工程有限公司', '08000010000024729907', '单电源', '高压临时用电', '11400', '11400', '2017-10-17', '2017-11-02', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('658e1416-ddad-4efb-83b8-20467ceec253', '广州市第六中学', '08000010000025848814', '单电源', '高压临时用电', '630', '630', '2017-12-13', '2017-12-29', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('659b6247-eab6-40c0-abd1-ca57e5564218', '广州市海珠区晓港湾小学', '08000010000025476261', '单电源', '高压新装', '315', '315', '2017-11-21', '2017-12-07', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('665630bf-b507-46cc-8046-2d10a0d0a4b7', '广州海事法院', '08000010000010143666', '双电源', '高压增容', '0', '2000', '2017-04-26', '2017-05-23', '0', '2017-08-31', '2017-09-13', '0', '2018-01-17', '2018-01-19', '0', '2018-01-23', '2018-01-29', '0', '2018-01-29', '2018-02-02', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('6858603b-fe9a-49e9-88bb-22e3f350c264', '广州市番禺区人民政府小谷围街道办事处', '08000010000010995853', '单电源', '高压增容', '0', '1600', '2017-07-14', '2017-08-01', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('69967368-45a3-49ad-8e8c-9db78afb3d5b', '广州地铁集团有限公司', '08000010000025119930', '单电源', '高压临时用电', '4500', '4500', '2017-11-01', '2017-11-17', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('69d4eba7-f80d-43a3-aef7-85f4e9fb84cd', '广州地铁集团有限公司', '08000010000010527221', '单电源', '高压临时用电', '1130', '1130', '2017-06-08', '2017-06-26', '0', '2018-01-04', '2018-01-17', '0', '2018-01-09', '2018-01-11', '0', '2018-01-11', '2018-01-15', '0', '2018-01-15', '2018-01-19', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('6a340b9a-86c8-4875-84d6-7c41cbee17d8', '广州市浩诚物业管理发展有限公司', '08000010000024027335', '单电源', '高压减容', '500', '1260', '2017-09-11', '2017-09-27', '0', '2018-02-02', '2018-02-14', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('6a9f049d-a081-45b1-87ff-3042e3624cd7', '广东省第二人民医院', '08000010000006635604', '双电源', '高压增容', '3870', '10130', '2016-08-08', '2016-09-02', '0', '2016-08-12', '2016-08-25', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('6bdf7bb7-44ba-495d-a379-853a7f221111', '劬劳中学', '08000010000024207674', '单电源', '高压增容', '430', '630', '2017-09-18', '2017-10-10', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('6d3c2c5d-7e02-4e3e-971d-1f10ec03c20f', '广州大学城市政管理所', '08000010000004853150', '单电源', '高压新装', '630', '630', '2016-11-17', '2016-12-05', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('6d910df4-9e3a-41f1-9c05-4bba88c3bb62', '广州骏怡经济发展有限公司', '08000010000007224994', '单电源', '高压增容', '6400', '8650', '2016-09-08', '2016-09-26', '0', '2017-06-23', '2017-07-06', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('6da36379-e3a9-427b-80ce-c96bc6206084', '广东广州日报传媒股份有限公司', '08000010000025746815', '单电源', '高压临时用电', '630', '630', '2017-12-07', '2017-12-25', '0', null, null, null, null, null, null, '2018-02-09', '2018-02-12', '0', '2018-02-12', '2018-02-23', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('6ee5c4f2-a106-49cc-86db-195faace8913', '广州创泽投资有限公司', '08000010000025622881', '单电源', '高压临时用电', '800', '800', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('6f78753b-13a6-4374-9f7c-ac378d875881', '广州地铁集团有限公司', '08000010000008652029', '单电源', '高压临时用电', '1260', '1260', '2016-12-01', '2016-12-19', '0', '2017-03-16', '2017-03-29', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('708f10bc-4233-4593-aa8a-45edfd3c6634', '中化广东有限公司', '08000010000026381494', '单电源', '高压增容', '2815', '3130', '2018-01-23', '2018-02-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('729ed451-b1ff-4c4c-8f9a-394efd67414b', '广州市黄埔区长洲街长洲股份经济联合社 ', '08000010000006098495', '单电源', '高压新装', '500', '500', '2016-07-11', '2016-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('72cb78e5-99d1-4842-b05b-c9b317abba18', '广州市竑富贸易有限公司', '08000010000025981049', '单电源', '高压临时用电', '500', '500', '2017-12-21', '2018-01-09', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('7303e4a3-5053-4a15-bde2-9a447318578a', '广州供电局有限公司变电管理一所', '08000010000023348412', '单电源', '高压增容', '0', '1260', '2017-08-08', '2017-08-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('74a39e15-9fea-41c6-9426-ecd50874cb56', '中铁广州工程局集团城轨工程有限公司', '08000010000025609459', '单电源', '高压临时用电', '315', '315', '2017-11-28', '2017-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('76a2b1d1-63c3-41ba-a623-98f4d4f12295', '广东省标准化研究院', '08000010000023332070', '单电源', '高压增容', '1100', '1600', '2017-08-07', '2017-08-23', '0', '2017-11-03', '2017-11-16', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('79af6687-8efe-40b7-a77e-0f0683a2a838', '中山大学(教学科研)', '08000010000010914392', '双电源', '高压增容', '2000', '22000', '2017-07-10', '2017-08-04', '0', '2017-10-24', '2017-11-06', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('79ccd3c3-3ff8-43f2-b3f4-46da16e1efda', '陈定方', '08000010000006091952', '单电源', '高压新装', '160', '160', '2016-07-11', '2016-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('7e61785f-e34f-4b13-b759-d1e7f6fad9ca', '广州珠江侨都房地产有限公司', '08000010000023703927', '单电源', '高压新装', '500', '500', '2017-08-24', '2017-09-11', '0', '2017-11-13', '2017-11-24', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('830e9f5f-be6c-4976-b4d9-0240b0f1fd68', '广州国际生物岛科技投资开发有限公司', '08000010000024600344', '双电源', '高压增容', '700', '6800', '2017-10-11', '2017-11-07', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('83797daf-cd22-42e5-888a-37dd560d0afd', '广州市海珠区凤阳街凤和经济联合社', '08000010000026153007', '单电源', '高压增容', '760', '1250', '2018-01-02', '2018-01-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('85b44759-cad5-4bfe-b946-be88581ec15e', '曾仲华 何统山 廖建球 廖勇球', '08000010000024578115', '单电源', '高压增容', '0', '400', '2017-10-10', '2017-10-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('87d87eac-4883-41c3-b394-6821b8844ce9', '广州市海珠区华洲街龙潭第三经济合作社', '08000010000006035810', '单电源', '高压增容', '380', '630', '2016-07-19', '2016-08-04', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('88845959-5415-4093-ba73-9d88e1b5bbf1', '中交第四航务工程局有限公司', '08000010000023977495', '单电源', '高压新装', '19400', '19400', '2017-09-08', '2017-09-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('88b57ab0-6dac-4688-b9a7-a93950f28e1c', '广州开发区市政工程公司', '08000010000007927474', '单电源', '高压新装', '250', '250', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('8a884739-beb7-4467-a21a-2b89d3fa3bbf', '广州开发区环卫美化服务中心', '08000010000006053634', '单电源', '高压新装', '200', '200', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('8ce9ffcd-9c26-4cd0-9b76-80459120c5c3', '中国人民解放军空军房地产管理局广州房地产管理处广州办事处', '08000010000010584650', '单电源', '高压增容', '215', '315', '2017-06-14', '2017-06-30', '0', '2017-09-14', '2017-09-27', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('9141682f-9451-437a-9697-63e24f831e16', '广州市番禺区教育局', '08000010000009734612', '单电源', '高压新装', '1260', '1260', '2017-03-16', '2017-04-03', '0', '2017-07-10', '2017-07-21', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('91c337a1-f558-4d44-87fe-0a345a7a9ceb', '广州市海珠区瑞宝街瑞宝第四经济合作社', '08000010000024230896', '单电源', '高压新装', '1250', '1250', '2017-09-19', '2017-10-11', '0', '2017-12-12', '2017-12-25', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('92175c56-e655-409e-91fe-7bdf130d23a9', '中国人民解放军第四二一医院', '08000010000010271357', '双电源', '高压增容', '0', '4200', '2017-05-11', '2017-06-07', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('924f0d61-de0b-4f47-a901-8c181ac891ae', '广州市海珠区凤阳街五凤经济合作社', '08000010000024430091', '单电源', '高压临时用电', '200', '200', '2017-09-27', '2017-10-19', '0', '2018-02-01', '2018-02-13', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('928939ba-76de-4e05-a865-22d8ee27d4f5', '中国电信股份有限公司广州分公司', '08000010000010200444', '双电源', '高压增容', '2870', '4000', '2017-05-04', '2017-05-31', '0', '2017-11-13', '2017-11-24', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('931bc5da-4f59-47f4-9d7a-64a78e087666', '广东兴国资产管理有限公司', '08000010000025088108', '单电源', '高压新装', '2520', '2520', '2017-10-31', '2017-11-16', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9586dd37-47c3-4c93-bdcd-630b899918d7', '广州市海珠区南洲街东风第四经济合作社', '08000010000009488974', '单电源', '高压增容', '1285', '1600', '2017-02-20', '2017-03-08', '0', '2017-03-30', '2017-04-12', '0', '2017-11-03', '2017-11-07', '0', '2017-11-08', '2017-11-10', '0', '2017-11-10', '2017-11-16', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('95ea8d65-7b80-4930-ab47-c8d80fba7f7b', '广州市海珠区人民政府', '08000010000023186917', '双电源', '高压增容', '0', '6450', '2017-07-31', '2017-08-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('962484ff-4e7f-4e54-b4c3-57cacd61b4b1', '中国人民解放军92557部队', '08000010000011248690', '单电源', '高压增容', '300', '800', '2017-07-28', '2017-08-15', '0', '2017-12-18', '2017-12-29', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('97402a4a-5903-4f0e-ac81-b467961a332e', '中国海员广州疗养院', '08000010000007163049', '双电源', '高压增容', '0', '2000', '2016-09-05', '2016-09-30', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('98d7b2c8-b2d3-40a5-bd60-962604e756df', '广州开发区市政工程公司', '08000010000007922713', '单电源', '高压新装', '400', '400', '2016-10-18', '2016-11-03', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('99008196-d236-4423-87a2-b1d94437c40f', '广州坤信商场经营有限公司', '08000010000006039404', '单电源', '高压增容', '970', '1600', '2016-07-20', '2016-08-05', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9953a13d-5b80-453e-b5d2-1d724b5c8133', '广州万力集团房地产有限公司', '08000010000024495340', '单电源', '高压新装', '5200', '5200', '2017-09-30', '2017-10-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9a69cae4-14e9-487b-8952-0d2b3831c659', '广州造船厂有限公司', '08000010000005995705', '单电源', '高压新装', '630', '630', '2016-07-06', '2016-07-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9b70f831-435e-48d3-9a2a-f631b43ac702', '广州市番禺区小谷围街贝岗村民委员会', '08000010000006156795', '单电源', '高压新装', '750', '750', '2016-07-15', '2016-08-02', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9b755b82-4b28-41ab-807d-5ee588218fd3', '广州市富基房地产开发有限公司', '08000010000025821217', '单电源', '高压增容', '0', '1430', '2017-12-12', '2017-12-28', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('9bf5ca68-e004-4231-a10a-04e7f976cff5', '广州市海珠区瑞宝街石溪第二经济合作社', '08000010000009604560', '单电源', '高压新装', '630', '630', '2017-03-01', '2017-03-17', '0', '2017-07-13', '2017-07-26', '0', '2017-12-14', '2017-12-18', '0', '2017-12-15', '2017-12-19', '0', '2017-12-19', '2017-12-25', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('9c058148-caa9-416f-af5f-5aef2370c259', '广州市岭南画派纪念中学', '08000010000004639368', '单电源', '高压新装', '400', '400', '2016-05-03', '2016-05-19', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a043c747-9601-4c22-9128-02c55a4634f3', '广州市海珠区土地储备整理中心', '08000010000005731000', '单电源', '高压临时用电', '200', '200', '2016-06-30', '2016-07-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a3d379e3-46c3-4d06-a12c-241226901a5c', '张文曦、张文华、张文昭', '08000010000005809506', '单电源', '高压新装', '200', '200', '2016-07-05', '2016-07-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a3f2d262-65b9-423d-bad4-9f995e4ad23c', '广州市海珠区瑞宝街石溪第一经济合作社', '08000010000026441187', '单电源', '高压增容', '630', '1260', '2018-01-30', '2018-02-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a68f6b39-042a-4572-9a02-da7520c6e926', '广州市土地房产管理学校', '08000010000009990686', '单电源', '高压增容', '1170', '2600', '2017-04-11', '2017-04-27', '0', '2017-06-15', '2017-06-28', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('a6a79737-c12c-4b8f-a1c1-f62728ded4f1', '广州地铁集团有限公司', '08000010000010530347', '单电源', '高压临时用电', '1260', '1260', '2017-06-08', '2017-06-26', '0', '2017-09-05', '2017-09-18', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('a7210b41-de71-4b34-9bca-8d9c46589c63', '广州市海珠区凤阳街凤和康乐第五经济合作社', '08000010000026395770', '单电源', '高压增容', '1750', '2000', '2018-01-24', '2018-02-09', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a724ec84-e6a6-4bc3-b3fa-1d6bb5571333', '广州市海珠区瑞宝街石溪第六经济合作社', '08000010000025507832', '单电源', '高压新装', '1000', '1000', '2017-11-22', '2017-12-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('a940187f-a5ab-49e0-85b7-2147cf997bc6', '广东警官学院（广东省公安司法管理干部学院）', '08000010000005734290', '单电源', '高压增容', '3020', '3650', '2016-06-30', '2016-07-18', '0', '2018-01-04', '2018-01-17', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('afa12e9d-511e-4cb0-b183-ea462d6dc6ce', '广州瑞嘉贸易有限公司', '08000010000006017892', '单电源', '高压新装', '1000', '1000', '2016-07-06', '2016-07-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b00b574c-a94f-4d3f-8f55-32fc1be8eaaf', '广州美术学院', '08000010000011241674', '单电源', '高压增容', '5350', '14950', '2017-07-28', '2017-08-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b05338b4-0070-419f-bdc8-8bcc9acb126a', '广州建和发展有限公司', '08000010000026382179', '单电源', '高压新装', '8000', '8000', '2018-01-23', '2018-02-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b1e5ce2f-df87-4596-9664-256737fef3e4', '广州市文化广电新闻出版局', '08000010000004903211', '单电源', '高压新装', '5690', '5690', '2016-11-28', '2016-12-14', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b4480db2-2b98-4902-b758-8614876acb81', '广州广昊房地产开发有限公司', '08000010000005058297', '单电源', '高压新装', '7200', '7200', '2016-06-13', '2016-06-29', '0', '2016-10-17', '2016-10-28', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('b6264f45-d6e1-4e14-8e49-20de6e65b62b', '广州市海珠区江海街红卫第一经济合作社、第三经济合作社', '08000010000007368673', '单电源', '高压增容', '320', '400', '2016-09-14', '2016-09-30', '0', '2017-08-18', '2017-08-31', '0', '2017-12-27', '2017-12-29', '0', '2018-01-02', '2018-01-04', '0', '2018-01-04', '2018-01-10', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('b6e68ac8-ee76-4542-82fe-b78d61406619', '保利房地产（集团）股份有限公司', '08000010000010232853', '双电源', '高压增容', '800', '11300', '2017-05-08', '2017-06-02', '0', '2017-06-19', '2017-06-30', '0', '2017-06-28', '2017-06-30', '0', '2017-08-04', '2017-08-10', '0', '2017-08-10', '2017-08-16', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('b862748a-c0cc-4b4d-be86-8141ee735a3d', '广州市交通站场建设管理中心', '08000010000023808783', '单电源', '高压新装', '800', '800', '2017-08-30', '2017-09-15', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b8d9a0d3-16bb-4782-86b8-32fa86b7c294', '广东工业大学', '08000010000006040016', '单电源', '高压临时用电', '315', '315', '2016-07-08', '2016-07-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('b9ccac5c-933f-4641-ba15-35aa9eab90a0', '广东电网公司广州供电局路灯管理所', '08000010000006125795', '单电源', '高压新装', '125', '125', '2016-07-13', '2016-07-29', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ba1c9206-9fc7-4805-ac97-1a11a12c1536', '广州欢聚电子商务有限公司', '08000010000005101033', '单电源', '高压临时用电', '630', '630', '2016-08-16', '2016-09-01', '0', '2017-11-15', '2017-11-28', '0', '2018-01-18', '2018-01-22', '0', '2018-01-23', '2018-01-25', '0', '2018-01-25', '2018-01-31', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('bacf55f6-e35a-4c90-9f20-ca165557064b', '广州市海珠区南洲街沥滘第一经济合作社', '08000010000023729188', '单电源', '高压增容', '240', '400', '2017-08-25', '2017-09-12', '0', '2017-11-08', '2017-11-21', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('bcdac623-fba9-4a0c-8900-4cbea1f4346b', '广州市公安局海珠区分局', '08000010000010167497', '双电源', '高压增容', '0', '1250', '2017-04-28', '2017-05-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('bdbdfcae-7c00-4d77-86c1-b64575a4d208', '广州供电局有限公司', '08000010000010497591', '单电源', '高压新装', '3200', '3200', '2017-06-06', '2017-06-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('bec57700-92c6-40ef-a28b-598a458c044b', '广州市建设投资发展有限公司', '08000010000006125009', '单电源', '高压新装', '250', '250', '2016-07-13', '2016-07-29', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('befe6238-902c-417b-a594-279219899f75', '广州摩托集团有限公司', '08000010000009859638', '单电源', '高压增容', '1600', '2000', '2017-03-28', '2017-04-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c1cb0558-8df4-4f12-9d15-d45a4e771fea', '广东省生物资源应用研究所', '08000010000010933284', '单电源', '高压新装', '2500', '2500', '2017-07-11', '2017-07-27', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c2d33fee-8f8d-4458-a539-129e318f76b1', '中国人民解放军92390部队、广州市宇田房地产开发有限公司', '08000010000006009102', '单电源', '高压临时用电', '315', '315', '2016-07-06', '2016-07-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c3cef78a-e6c3-46dc-b66c-482a8d88cc1e', '广州国际生物岛建设办公室  ', '08000010000005736058', '单电源', '高压新装', '100', '100', '2016-06-30', '2016-07-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c3fe329a-6548-464a-b253-0fe67e2d8f8b', '广州市海珠区人民政府江南中街道办事处', '08000010000004783885', '单电源', '高压新装', '500', '500', '2016-05-19', '2016-06-06', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c4b430ee-6eb2-453d-9746-3c3064c7c399', '广州市海珠区江海街红卫经济联合社', '08000010000026223479', '单电源', '高压增容', '2000', '2500', '2018-01-09', '2018-01-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('c8e2db8e-9cbb-4322-8b9f-fef263772c3c', '广州电力工业局工程公司', '08000010000025067415', '双电源', '高压增容', '0', '2500', '2017-10-30', '2017-11-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('cbeb23f4-eae6-4ce7-a7db-020317bcf7ca', '广州供电局有限公司基建部', '08000010000005398079', '单电源', '高压新装', '945', '945', '2016-06-22', '2016-07-08', '0', '2016-06-22', '2016-07-05', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('cc7bb075-a97a-4a0c-aa8c-0b41f808c4ed', '广州市番禺区小谷围街南亭村民委员会', '08000010000006112480', '单电源', '高压新装', '750', '750', '2016-07-13', '2016-07-29', '0', '2016-11-23', '2016-12-06', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('cd061cbc-ef18-41d3-977e-012753b259a5', '市红十字会医院', '08000010000006978117', '双电源', '高压增容', '6000', '14000', '2016-10-21', '2016-11-17', '0', '2017-08-18', '2017-08-31', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('cd39ed97-c64b-4f40-8ffb-eb4bc3b9cf54', '李少勤', '08000010000009311545', '单电源', '高压新装', '100', '100', '2017-01-23', '2017-02-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('cec70f93-3c3b-4a1b-8466-c5697064581d', '广州朝亿物业管理有限公司', '08000010000005823986', '单电源', '高压新装', '1000', '1000', '2016-07-05', '2016-07-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('cf69143d-de78-4efa-b37a-280ac8e9ec39', '广州市第二十六中学', '08000010000006898740', '单电源', '高压新装', '250', '250', '2016-08-22', '2016-09-07', '0', '2017-05-10', '2017-05-23', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('d00d6910-fa77-49c3-8743-1333d704d665', '广州化学试剂厂', '08000010000009115021', '单电源', '高压增容', '2290', '2850', '2017-01-05', '2017-01-23', '0', '2017-06-21', '2017-07-04', '0', '2017-10-31', '2017-11-02', '0', '2017-11-01', '2017-11-03', '0', '2017-11-03', '2017-11-09', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('d1f5bc67-ea28-49a1-87d0-bf05867d0007', '广东广信通信服务有限公司', '08000010000026125189', '单电源', '高压增容', '630', '1260', '2017-12-29', '2018-01-17', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d1f628e4-5785-4a4e-b942-53a6aba4ff17', '广州市海珠区瑞宝街瑞宝第二经济合作社', '08000010000011005681', '单电源', '高压新装', '630', '630', '2017-07-14', '2017-08-01', '0', '2017-10-19', '2017-11-01', '0', '2017-11-10', '2017-11-14', '0', '2017-11-13', '2017-11-15', '0', '2017-11-15', '2017-11-21', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('d21097f1-1963-48b5-aa31-ae3344f172c6', '中铁广州工程局集团城轨工程有限公司', '08000010000026411208', '单电源', '高压临时用电', '5130', '5130', '2018-01-26', '2018-02-12', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d252303b-dfaa-4970-9c43-23f51014556d', '广州市海珠区白云电器成套设备厂', '08000010000026210618', '单电源', '高压增容', '80', '160', '2018-01-08', '2018-01-24', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d28284cd-b795-4230-8219-5b7fc0038fbe', '广州市海珠区瑞宝街石溪第三经济合作社', '08000010000025653439', '单电源', '高压增容', '600', '1000', '2017-11-30', '2017-12-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d5bb9ab0-9420-4e7e-abdc-59f34c822437', '广州市展汇房地产开发有限公司', '08000010000023329742', '单电源', '高压增容', '0', '17600', '2017-08-07', '2017-08-23', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d62ed9e6-24cd-4c6d-91bd-c33c88e1fa46', '广州市番禺区教育局', '08000010000009415269', '单电源', '高压新装', '800', '800', '2017-02-13', '2017-03-01', '0', '2017-04-27', '2017-05-10', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('d722e9fa-6f9e-4581-ae55-94ecfc1215ab', '广州地铁集团有限公司', '08000010000009660037', '单电源', '高压临时用电', '630', '630', '2017-03-08', '2017-03-24', '0', '2017-05-15', '2017-05-26', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('d96ac713-4e8d-4e1d-aca7-1bf5b963eb2e', '广州市第九十七中学', '08000010000005833085', '单电源', '高压增容', '0', '1250', '2016-07-05', '2016-07-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('d9d562a7-f8ad-4dc1-8425-a4c8682735f9', '广州市电车公司', '08000010000005831526', '单电源', '高压新装', '400', '400', '2016-07-06', '2016-07-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e0200fb9-f107-4b17-a579-3b80f9634da3', '广州市海珠区凤阳街五凤凤凰第一经济合作社', '08000010000010834365', '单电源', '高压增容', '1100', '1600', '2017-07-03', '2017-07-19', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e0af2047-886b-4e7a-9097-bc2828c8302f', '广州供电局有限公司路灯管理所', '08000010000025085252', '单电源', '高压新装', '250', '250', '2017-10-31', '2017-11-16', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e1e20c68-dc26-4a58-9f4b-816c591e98c1', '广州珠江啤酒股份有限公司', '08000010000026374166', '双电源', '高压增容', '2400', '14400', '2018-01-22', '2018-02-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e3e364e7-92c5-4889-b76a-600cf134c6a9', '广州市科技新委员会 ', '08000010000005757073', '单电源', '高压临时用电', '1000', '1000', '2016-07-01', '2016-07-19', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e52ebeeb-450b-4fd6-8cff-2b8ed09b12c4', '中国船舶工业物资华南有限公司', '08000010000025769999', '单电源', '高压增容', '2000', '3600', '2017-12-08', '2017-12-26', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e6009fe2-85b6-43f7-85c6-a55d817b5342', '广州市海珠区凤阳街凤和康乐第三经济合作社', '08000010000024415048', '单电源', '高压增容', '1285', '1600', '2017-09-26', '2017-10-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e78b428b-ed0d-4ba7-bafe-049aa38bad76', '广州市海珠区昌岗街联星经济联合社', '08000010000025159003', '单电源', '高压新装', '630', '630', '2017-11-03', '2017-11-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e79942f2-c643-497e-a3b6-8ded754124ae', '中国人民解放军91592部队', '08000010000011208209', '单电源', '高压增容', '448', '1260', '2017-07-26', '2017-08-11', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e7c1aa9a-6baa-4dba-8fbd-8fecad0e9ed7', '广州市超算分布式能源投资有限公司', '08000010000025588569', '单电源', '高压增容', '0', '4000', '2017-11-26', '2017-12-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e807bc4b-d57e-4b20-8550-439a1fd87607', '广州市海珠区瑞宝街瑞宝村第八经济合作社', '08000010000025131906', '单电源', '高压增容', '2250', '3050', '2017-11-02', '2017-11-20', '0', null, null, null, null, null, null, '2018-02-07', '2018-02-09', '0', '2018-02-09', '2018-02-14', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('e93d401d-a07c-4d30-96cc-56ccddae1d81', '广州市海珠区江海街红卫经济联合社', '08000010000005832279', '单电源', '高压新装', '630', '630', '2016-07-06', '2016-07-22', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e945c253-06f6-47f7-b112-01dafba3bc7e', '广州市瑞兴贸易服务公司 ', '08000010000006098198', '单电源', '高压新装', '630', '630', '2016-07-21', '2016-08-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('e9d4267f-33ae-4dc6-84a2-d3f11657d7ba', '广州鼎尚股份有限公司', '08000010000008701808', '双电源', '高压新装', '16400', '16400', '2016-12-06', '2017-01-02', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ea878d73-2e1a-4730-ba78-9ba175042578', '广州市海珠区土地储备整理中心 ', '08000010000005727197', '单电源', '高压临时用电', '100', '100', '2016-06-30', '2016-07-18', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('eac85404-0a85-48fd-ac9d-ff1e20652cfc', '广州仲源房地产开发有限公司', '08000010000006130884', '单电源', '高压增容', '1250', '3250', '2016-08-31', '2016-09-16', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ebe44415-5d6a-4167-8f2c-729b058b9321', '广州市海珠区瑞宝街瑞宝经济联合社', '08000010000025603617', '单电源', '高压新装', '3200', '3200', '2017-11-27', '2017-12-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ed2e73ed-5d3e-412b-95a7-b8259ee5f362', '广州市海珠区机关事务管理局', '08000010000006098583', '双电源', '高压增容', '450', '1250', '2016-08-14', '2016-09-09', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ee4ec28b-f693-4e73-81fa-a7a811b511bd', '广东珠江投资股份有限公司', '08000010000006746157', '单电源', '高压新装', '800', '800', '2016-08-12', '2016-08-30', '0', '2017-04-01', '2017-04-14', '0', '2017-07-25', '2017-07-27', '0', '2017-07-27', '2017-07-31', '0', '2017-07-31', '2017-08-04', '0', '2', '9');
INSERT INTO `tb_ykjd` VALUES ('f02dbc69-40b5-45e9-ba42-c37bc272349b', '鹰仕达（中国）有限公司', '08000010000026341746', '单电源', '高压新装', '7200', '7200', '2018-01-18', '2018-02-05', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('f357eefe-27a9-4bad-9dfd-cd0bde38f2ac', '广州市海珠区河涌管理所', '08000010000006023917', '单电源', '高压新装', '250', '250', '2016-07-07', '2016-07-25', '0', '2016-10-27', '2016-11-09', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('f59c5e01-067a-4a22-b294-a543835f92f8', '广州市海珠科技产业园有限公司', '08000010000005824707', '单电源', '高压临时用电', '315', '315', '2016-07-05', '2016-07-21', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('f67e980a-71d2-44fd-af61-f6a513fc1339', '广州开发区市政工程公司', '08000010000007941261', '单电源', '高压新装', '400', '400', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('f6faf3d9-b426-4473-a706-23c79b74ee14', '广州市海珠区凤阳街凤和经济联合社', '08000010000006015144', '单电源', '高压新装', '8600', '8600', '2016-07-07', '2016-07-25', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('f79747f5-46df-4a71-b550-d86402b2029d', '广州市五中滨江学校', '08000010000010694721', '双电源', '高压新装', '630', '630', '2017-06-23', '2017-07-20', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('f7cc2fd0-29d6-47e3-aaf7-fe5684e7b43f', '广州市文化广电新闻出版局（美术馆）', '08000010000004800899', '单电源', '高压新装', '8000', '8000', '2016-05-20', '2016-06-07', '0', '2017-07-31', '2017-08-11', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('f94eac8d-b38e-4b0c-916f-4a072d370f89', '广州地铁集团有限公司', '08000010000026274788', '单电源', '高压临时用电', '10750', '10750', '2018-01-12', '2018-01-30', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('fa561b1c-489f-4446-99f7-015a1d1933c1', '广州地铁集团有限公司', '08000010000010529677', '单电源', '高压临时用电', '1890', '1890', '2017-06-08', '2017-06-26', '0', '2017-09-15', '2017-09-28', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('fab24b1f-5357-4d4d-a5e5-b79cee6e2a23', '广州市瑞宝城商业发展有限公司', '08000010000026383543', '单电源', '高压增容', '620', '1250', '2018-01-23', '2018-02-08', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('fbb1d8b3-316c-4fe8-a29b-843223c2e498', '广州市海珠区南洲街沥滘第五经济合作社', '08000010000010572810', '单电源', '高压新装', '80', '80', '2017-06-12', '2017-06-28', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('fd1bde86-abfe-4403-a4cb-aa636363ffe0', '广州开发区市政工程公司', '08000010000007927103', '单电源', '高压新装', '250', '250', '2016-10-19', '2016-11-04', '0', '2016-12-19', '2016-12-30', '0', null, null, null, null, null, null, null, null, null, '1', '4');
INSERT INTO `tb_ykjd` VALUES ('fd43a0a0-c20b-49a4-b3e3-214749ab38f2', '广州市海珠区瑞宝街瑞宝第六经济合作社', '08000010000026432146', '单电源', '高压新装', '1250', '1250', '2018-01-29', '2018-02-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('ffa8e66b-c7ec-46ed-8080-b5e8fd2cf156', '广州军区联勤部广州越秀军职以上退休干部休养所', '08000010000009859887', '单电源', '高压新装', '500', '500', '2017-03-28', '2017-04-13', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
INSERT INTO `tb_ykjd` VALUES ('fffae5f8-2a17-4f2d-beee-3e789273e8cd', '广州市建设投资发展有限公司、广州电力工业局路灯管理所', '08000010000023348622', '双电源', '高压增容', '0', '2000', '2017-08-08', '2017-09-04', '0', null, null, null, null, null, null, null, null, null, null, null, null, '1', '2');
