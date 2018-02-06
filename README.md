# o2o

CREATE TABLE `tb_area` (
  `area_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `area_name` varchar(200) NOT NULL COMMENT '区域名称',
  `area_desc` varchar(1000) DEFAULT NULL COMMENT '区域描述',
  `priority` int(5) NOT NULL DEFAULT '0' COMMENT '权重',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`area_id`),
  UNIQUE KEY `UK_AREA` (`area_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '区域表';

CREATE TABLE `tb_person_info` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `birthday` datetime DEFAULT NULL COMMENT '用户生日',
  `gender` varchar(2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户性别',
  `phone` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户电话',
  `email` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `profile_img` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像地址',
  `user_type` int(2) NOT NULL DEFAULT '1' COMMENT '用户类型 1.顾客 2.店家 3.超级管理员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_edit_time` datetime DEFAULT NULL COMMENT '更新时间',
  `enable_status` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态 0.禁止使用本商城  1.允许使用本商城',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT '用户信息表';
