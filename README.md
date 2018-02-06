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
