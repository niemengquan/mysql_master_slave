DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `item_id` bigint(20) DEFAULT NULL COMMENT '商品ID',
  `item_title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `item_image` varchar(200) DEFAULT NULL COMMENT '商品主图',
  `item_price` bigint(20) DEFAULT NULL COMMENT '商品价格，单位为：分',
  `num` int(10) DEFAULT NULL COMMENT '购买数量',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId_itemId` (`user_id`,`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='购物车模块';

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('5','10','981821','new——苹果(Apple) iPhone 4s 8GB 白色 联通3G手机','http://image.taotao.com/jd/13a8213124d4489aa411bca04ec3e68f.jpg','1999000','1','2015-05-24 15:13:34','2015-05-24 15:13:34');
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('6','10','981821','魅族 魅蓝note6 高通八核心处理器，极速美拍','http://item.jd.com/5369026.html','1299','1',NULL,NULL);
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('11','10','4331185','Apple MacBook Pro 13.3英寸笔记本电脑 深空灰色（2017新款Core i5处理器/8GB内存/256GB硬盘 MPXT2CH/A）','https://item.jd.com/4331185.html','11688','1','2017-11-10 14:43:12','2017-11-10 14:43:12');
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('12','10','4734101','魅族 PRO 7 4GB 64GB 全网通公开版 静谧黑 移动联通电信4G手机 双卡双待 11月1日至12日，下单返现100！享12期免息！限量抢购，机不可失哦！10.25-11.12全程购机享价保，买贵补差价！不用等到11.11','https://item.jd.com/4734101.html','2499','2','2017-11-10 14:46:30','2017-11-10 14:46:30');
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('13','10','4734102','魅族 PRO 7 Plus 6GB+64GB 全网通公开版 静谧黑 移动联通电信4G手机 双卡双待','https://item.jd.com/4560477.html','3299','1','2017-11-10 14:55:07','2017-11-10 14:55:07');
insert into `tb_cart` (`id`, `user_id`, `item_id`, `item_title`, `item_image`, `item_price`, `num`, `created`, `updated`) values('14','10','4734102','魅族 PRO 7 Plus 6GB+64GB 全网通公开版 静谧黑 移动联通电信4G手机 双卡双待','https://item.jd.com/4560477.html','3299','1','2017-11-10 14:56:29','2017-11-10 14:56:29');

-- ----------------------------