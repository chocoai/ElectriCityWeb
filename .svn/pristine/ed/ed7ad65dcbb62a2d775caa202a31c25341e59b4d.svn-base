CREATE DATABASE  IF NOT EXISTS `osc_jblade` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `osc_jblade`;
-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: osc_jblade
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tfw_role`
--

DROP TABLE IF EXISTS `tfw_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NUM` int(11) DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `DEPTID` int(11) DEFAULT NULL,
  `TIPS` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_role`
--

LOCK TABLES `tfw_role` WRITE;
/*!40000 ALTER TABLE `tfw_role` DISABLE KEYS */;
INSERT INTO `tfw_role` VALUES (1,1,NULL,'超级管理员',1,'administrator',0),(2,1,1,'管理员',7,'admin',3);
/*!40000 ALTER TABLE `tfw_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_worktype`
--

DROP TABLE IF EXISTS `dg_worktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_worktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '工种名称',
  `check` int(1) NOT NULL DEFAULT '0' COMMENT '工种审核需要，0不需要审核，1需要审核\n',
  `mark` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '工种描述,100个汉字以内',
  `salary` double DEFAULT '0' COMMENT '工种每天酬劳，整数表示每天多少钱，如果是百分比表示占总额比例',
  `over_money` double DEFAULT '0' COMMENT '加班酬劳',
  `order_flag` int(11) DEFAULT '0',
  `times` int(11) DEFAULT '0',
  `version` int(11) DEFAULT '1' COMMENT '版本记录号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='1. 工种管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_worktype`
--

LOCK TABLES `dg_worktype` WRITE;
/*!40000 ALTER TABLE `dg_worktype` DISABLE KEYS */;
INSERT INTO `dg_worktype` VALUES (1,'工种A',0,'haha',201,51,1,2,4),(2,'工种B',0,NULL,300,60,1,2,3),(3,'装饰工',0,'',380,100,0,1,1),(4,'空调安装工',0,'',300,100,1,1,1),(5,'泥水匠工',0,'',3500,150,0,0,1),(6,'建筑经理',0,'',600,200,0,1,1);
/*!40000 ALTER TABLE `dg_worktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_attach`
--

DROP TABLE IF EXISTS `tfw_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_attach` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `URL` text,
  `STATUS` int(11) DEFAULT NULL,
  `CREATER` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_attach`
--

LOCK TABLES `tfw_attach` WRITE;
/*!40000 ALTER TABLE `tfw_attach` DISABLE KEYS */;
INSERT INTO `tfw_attach` VALUES (303,NULL,'2 (14).jpg','/upload\\20160930\\1475222724826.jpg',1,1,'2016-09-30 16:05:25'),(304,NULL,'2.jpg','/upload\\20160930\\1475222803894.jpg',1,1,'2016-09-30 16:06:44'),(305,NULL,'3 (11).jpg','/upload\\20160930\\1475222803929.jpg',1,1,'2016-09-30 16:06:44'),(306,NULL,'3 (12).jpg','/upload\\20160930\\1475222804029.jpg',1,1,'2016-09-30 16:06:44'),(307,'8855682','313.jpg','/upload\\20161011\\1476171059172.jpg',1,1,'2016-10-11 15:30:59');
/*!40000 ALTER TABLE `tfw_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_user`
--

DROP TABLE IF EXISTS `dg_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称，大小写字母加汉字下划线组成',
  `phoneno` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '注册手机号码',
  `userpwd` varchar(45) COLLATE utf8_unicode_ci NOT NULL COMMENT '注册密码，大小写字母加下划线，特殊字符组成，最少长度8位，不能大于12位',
  `salt` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT 'default.png' COMMENT '头像地址',
  `address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '居住地址',
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户注册时间，当前系统时间',
  `status` int(11) DEFAULT NULL,
  `gender` int(1) DEFAULT '1' COMMENT '性别(0:女; 1:男)',
  `intro_id` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '推荐人id，与register_code一样',
  `code` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '符合身份证验证信息',
  `balance` double DEFAULT '0' COMMENT '账户余额，以元为单位',
  `customs` int(11) DEFAULT '0' COMMENT '点评总人数',
  `inro_count` int(11) DEFAULT NULL COMMENT '记录当前用户推荐人的奖励次数，只有推荐人不为空有效',
  `total` int(1) DEFAULT NULL COMMENT '点评总分数，5星评分',
  `mark` int(11) DEFAULT '0',
  `version` int(11) DEFAULT '1' COMMENT '加密盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='1. 电工用户数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_user`
--

LOCK TABLES `dg_user` WRITE;
/*!40000 ALTER TABLE `dg_user` DISABLE KEYS */;
INSERT INTO `dg_user` VALUES (1,'18823762328','18823762328','716a65ddcf01e06cc5b61eae2afd85ba','98ixi',NULL,NULL,'2017-10-08 15:11:29',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(2,'13869312326','13869312326','5c74cd7633256289fe24affb079b9815','hg4p3',NULL,NULL,'2017-10-17 21:17:50',0,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(3,'13537738395','13537738395','95f9c38468a34411cc37cfec436eab1c','x6113','c77ed94618dc49ff8b8c9b28e902c68a.png',NULL,'2017-10-28 18:29:30',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,12),(4,'13537738395','13537738395','78ef4bb093bf72086fa86fea467d56f7','ql4jd',NULL,NULL,'2017-10-30 05:33:15',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,'13537738396','13537738396','f6591cc430396c7cbd7a059b6623f05a','uslye',NULL,NULL,'2017-11-01 05:00:33',3,NULL,'3',NULL,NULL,NULL,NULL,NULL,NULL,2),(6,'13537738397','13537738397','812bfac3a25d482e3d567e23442a2aa7','8rxqh','aeb8429552334dfe9f601ec3d0cd2ed7.png','哦热狗王','2017-11-01 05:27:06',0,1,'3','431024198805163615',NULL,NULL,NULL,NULL,NULL,18),(7,'15818602161','15818602161','f720ef59f0a5e6ffc15c61daa3e4aef4','wf7f5','fd1f775c56f14c459a11379f8c05fe93.png',NULL,'2017-11-01 20:42:23',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2),(8,'13530897682','13530897682','2aa53975d270c2261f98a37ef85af2d3','byp1d','616358fdc87c48358d02ded39d067c14.png','云南省曲靖县富源县','2017-11-02 21:59:40',0,1,NULL,'532225197102200376',NULL,NULL,NULL,NULL,NULL,11),(9,'13659512363','13659512363','fb4083c6a1ea344276ac9e6db3c72d70','w2g38',NULL,NULL,'2017-11-11 05:15:08',0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `dg_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tfw_tzgg`
--

DROP TABLE IF EXISTS `tb_tfw_tzgg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tfw_tzgg` (
  `F_IT_XL` int(11) NOT NULL AUTO_INCREMENT,
  `F_VC_BT` varchar(255) DEFAULT NULL,
  `F_IT_LX` int(11) DEFAULT NULL,
  `F_TX_NR` text,
  `F_DT_FBSJ` datetime DEFAULT NULL,
  `F_DT_CJSJ` datetime DEFAULT NULL,
  `F_IT_CJR` int(11) DEFAULT NULL,
  `F_IT_TP` int(11) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`F_IT_XL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tfw_tzgg`
--

LOCK TABLES `tb_tfw_tzgg` WRITE;
/*!40000 ALTER TABLE `tb_tfw_tzgg` DISABLE KEYS */;
INSERT INTO `tb_tfw_tzgg` VALUES (2,'漂亮的大风车',10,'<p>\r\n啊啊啊\r\n</p>\r\n<p>\r\n<img src=\"/kindeditor/renderFile/303\" title=\"303\" alt=\"303\" />\r\n</p>','2016-09-30 00:00:00',NULL,NULL,NULL,0),(3,'好多图',1,'<img src=\"/kindeditor/renderFile/304\" title=\"304\" alt=\"304\" /><img src=\"/kindeditor/renderFile/305\" title=\"305\" alt=\"305\" /><img src=\"/kindeditor/renderFile/306\" title=\"306\" alt=\"306\" />','2016-09-30 00:00:00',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `tb_tfw_tzgg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_items_reject`
--

DROP TABLE IF EXISTS `dg_items_reject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_items_reject` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `work_order_id` bigint(22) NOT NULL,
  `dg_userid` bigint(22) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `mark` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_items_reject`
--

LOCK TABLES `dg_items_reject` WRITE;
/*!40000 ALTER TABLE `dg_items_reject` DISABLE KEYS */;
/*!40000 ALTER TABLE `dg_items_reject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_user_worktype`
--

DROP TABLE IF EXISTS `dg_user_worktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_user_worktype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `dg_userid` bigint(20) DEFAULT NULL COMMENT '电工ID',
  `worktype_id` bigint(20) DEFAULT NULL COMMENT '工种ID',
  `status` int(1) DEFAULT NULL COMMENT '审核状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电工用户工种表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_user_worktype`
--

LOCK TABLES `dg_user_worktype` WRITE;
/*!40000 ALTER TABLE `dg_user_worktype` DISABLE KEYS */;
INSERT INTO `dg_user_worktype` VALUES (1,3,1,1,'2017-10-31 07:29:32',1),(2,3,3,0,'2017-10-31 07:31:43',1),(3,3,2,1,'2017-10-31 07:38:59',1),(4,3,2,1,'2017-10-31 07:39:21',1),(5,3,-1,0,'2017-11-01 04:46:07',1),(6,5,1,1,'2017-11-01 05:01:51',1),(7,5,2,1,'2017-11-01 05:04:31',1),(8,5,3,0,'2017-11-01 05:06:06',1),(9,6,1,2,'2017-11-01 05:27:29',2),(10,6,3,0,'2017-11-01 05:31:57',1),(11,6,2,2,'2017-11-01 05:32:27',2),(12,8,3,0,'2017-11-02 22:02:09',1),(13,7,3,0,'2017-11-05 05:03:23',1),(14,6,4,2,'2017-11-06 23:30:06',6),(15,6,6,0,'2017-11-10 00:08:26',1);
/*!40000 ALTER TABLE `dg_user_worktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_user_head_ico`
--

DROP TABLE IF EXISTS `dg_user_head_ico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_user_head_ico` (
  `ico_id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '头像ID',
  `pic_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片名',
  `pic_path` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图片地址',
  `user_id` bigint(22) DEFAULT NULL COMMENT '电工用户Id',
  `status` int(1) DEFAULT NULL COMMENT '图片状态(0:启用,1:待审核)',
  `create_tm` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`ico_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户头像记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_user_head_ico`
--

LOCK TABLES `dg_user_head_ico` WRITE;
/*!40000 ALTER TABLE `dg_user_head_ico` DISABLE KEYS */;
INSERT INTO `dg_user_head_ico` VALUES (5,'88f4e00d9f13408bb729d2e32332903e.png','',3,0,'2017-10-28 18:31:09',1),(6,'57335f7eeb914df2ba679188b2fa1183.png','',3,0,'2017-10-28 18:36:21',1),(7,'55349638694d4ba19775540b66177aea.png','',3,0,'2017-10-28 19:10:29',1),(8,'7bf6a20174c74fe99cf4e4569972629c.png','',3,0,'2017-10-28 19:11:11',1),(9,'fa64e7018c884d5299eb5272bf4d33e1.png','',3,0,'2017-10-30 05:35:55',1),(10,'701596ba4c5b41b2a8df249c9fa4f8b7.png','',3,0,'2017-10-30 06:18:31',1),(11,'e5501e6ccc21489c9725884e7d578b62.png','',3,0,'2017-10-30 17:31:21',1),(12,'e6b2ddf2fd0a416bbe13f579767091b3.png','',3,0,'2017-10-30 22:25:24',1),(13,'fe509d000d104185b779e2046ec0c0a5.png','',3,0,'2017-10-31 01:02:06',1),(14,'0bfc55fe2b3e487fa5e2de53aaebf5c5.png','',3,0,'2017-10-31 01:02:28',1),(15,'c77ed94618dc49ff8b8c9b28e902c68a.png','',3,0,'2017-10-31 22:32:06',1),(16,'112b8ca88b90460f83e4ea0696d5bd9c.png','',6,0,'2017-11-01 20:37:08',1),(17,'e63e9aa3cfa746af91e6585651c3cf36.png','',6,0,'2017-11-02 00:08:38',1),(18,'f0db92aadcc64dcb85ed109d6bf7a4c3.png','',6,0,'2017-11-02 00:10:25',1),(19,'8b2483a105994a76bfab26c87bda0127.png','',6,0,'2017-11-02 00:18:23',1),(20,'a6b3455b3f274377ac754cf1fca174f0.png','',6,0,'2017-11-02 00:31:03',1),(21,'ffcd60de820849d4a62cd87efbeb5475.png','',6,0,'2017-11-02 00:37:36',1),(22,'0eb3b65cd37c455f83324ae56b5dc24e.png','',6,0,'2017-11-02 06:50:38',1),(23,'616358fdc87c48358d02ded39d067c14.png','',8,0,'2017-11-02 22:00:30',1),(24,'fd1f775c56f14c459a11379f8c05fe93.png','',7,0,'2017-11-07 20:01:30',1),(25,'c993931fa6334976916074ca0406bb1b.png','',6,0,'2017-11-12 03:49:54',1),(26,'aeb8429552334dfe9f601ec3d0cd2ed7.png','',6,0,'2017-11-12 03:50:09',1);
/*!40000 ALTER TABLE `dg_user_head_ico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_order_apply`
--

DROP TABLE IF EXISTS `dg_order_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_order_apply` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(22) DEFAULT NULL COMMENT '创建电工用户id',
  `work_order_id` bigint(22) DEFAULT NULL COMMENT '工种订单id',
  `type` int(1) DEFAULT NULL COMMENT '申请类型，0空，1招标申请，2订单终止申请，3订单加班申请',
  `status` int(1) DEFAULT NULL COMMENT '状态，0申请中，1同意，2拒绝，3挂起',
  `mark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '招标说明，100汉字以内',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_order_apply`
--

LOCK TABLES `dg_order_apply` WRITE;
/*!40000 ALTER TABLE `dg_order_apply` DISABLE KEYS */;
INSERT INTO `dg_order_apply` VALUES (1,6,15,3,0,'你居然xl码','2017-11-11 06:04:22',1),(2,6,15,3,0,'你居然xl码','2017-11-11 06:04:25',1),(3,6,15,3,0,'你居然xl码','2017-11-11 06:04:26',1),(4,6,15,3,0,'你居然xl码','2017-11-11 06:04:27',1),(5,6,15,2,0,'牛津鞋裙','2017-11-11 06:04:35',1),(6,6,15,2,0,'牛津鞋裙','2017-11-11 06:04:37',1),(7,6,15,2,0,'牛津鞋裙','2017-11-11 06:04:38',1),(8,6,15,2,0,'骨头咯五十','2017-11-11 17:43:10',1),(9,6,16,2,0,'舒筋健腰丸','2017-11-12 04:19:44',1),(10,6,16,1,0,'后来秀色田园','2017-11-12 05:20:17',1),(11,6,17,1,1,'鼎鑫华庭申请','2017-11-12 05:20:17',1),(12,6,17,1,0,'147','2017-11-12 06:09:43',1),(13,6,17,1,0,'147','2017-11-12 06:09:49',1),(14,6,17,1,0,'147','2017-11-12 06:09:53',1),(15,6,17,1,0,'147','2017-11-12 06:09:56',1);
/*!40000 ALTER TABLE `dg_order_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_work_order`
--

DROP TABLE IF EXISTS `dg_work_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_work_order` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(22) NOT NULL COMMENT '创建订单的电工用户id',
  `order_id` bigint(22) NOT NULL COMMENT '订单id',
  `order_date` int(11) NOT NULL COMMENT '订单周期，以天为单位',
  `worktype_id` int(11) DEFAULT NULL COMMENT '工种类型id',
  `mark` varchar(500) COLLATE utf8_unicode_ci NOT NULL COMMENT '项目描述，长度不能大于500',
  `money` double NOT NULL DEFAULT '0' COMMENT '项目金额',
  `total_money` double DEFAULT '0' COMMENT '总金额',
  `status` int(11) DEFAULT '0' COMMENT '项目状态，0待审，1招标中，2已招标，3项目进行中， 4完成，5终止',
  `accept_user_id` bigint(22) DEFAULT NULL COMMENT '如果状态为已接单，该项填入接单人id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `biding_time` timestamp NULL DEFAULT NULL COMMENT '招标中时间',
  `accpet_time` timestamp NULL DEFAULT NULL COMMENT '订单接收时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `subsidy` double DEFAULT NULL COMMENT '补贴金额，具体金额数，按天计算',
  `subsidy_mark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '补贴说明',
  `reject_times` int(11) DEFAULT NULL COMMENT '拒绝次数',
  `version` int(11) DEFAULT '1' COMMENT '版本记录号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电工用户订单管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_work_order`
--

LOCK TABLES `dg_work_order` WRITE;
/*!40000 ALTER TABLE `dg_work_order` DISABLE KEYS */;
INSERT INTO `dg_work_order` VALUES (12,6,5,12,1,'你继续',2412,2424,2,NULL,'2017-11-04 23:39:38',NULL,NULL,NULL,NULL,12,'你有老婆',NULL,1),(13,6,6,36,2,'你赢了8月',10800,10813,2,NULL,'2017-11-05 23:16:59',NULL,NULL,NULL,NULL,13,'你可以芦笛岩',NULL,1),(14,6,6,36,1,'芈月',7236,7272,2,NULL,'2017-11-05 23:16:59',NULL,NULL,NULL,NULL,36,'喉阻塞',NULL,1),(15,6,7,5,4,'来咯额',1500,1700,2,NULL,'2017-11-07 19:54:55',NULL,NULL,NULL,NULL,200,'啊',NULL,1),(16,6,8,45,6,'后颈肉痒',27000,27015,2,NULL,'2017-11-12 03:07:12',NULL,NULL,NULL,NULL,15,'GO联系人',NULL,1),(17,8,9,80,6,'科苑鼎鑫华庭CBD',27000,27015,2,6,'2017-11-12 03:07:12',NULL,NULL,'2017-11-12 06:01:12',NULL,15,'科苑鼎鑫华庭CBD',NULL,1);
/*!40000 ALTER TABLE `dg_work_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_order_progress`
--

DROP TABLE IF EXISTS `dg_order_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_order_progress` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(22) DEFAULT NULL COMMENT '创建电工用户id',
  `order_id` bigint(22) DEFAULT NULL COMMENT '子项目id',
  `work_order_id` bigint(22) DEFAULT NULL,
  `longitude` double DEFAULT NULL COMMENT '经度坐标',
  `latitude` double DEFAULT NULL COMMENT '纬度坐标',
  `location` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(1) DEFAULT NULL COMMENT '状态，0提交，1确认，2默认',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '进度提交时间',
  `mark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电工用户订单进度表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_order_progress`
--

LOCK TABLES `dg_order_progress` WRITE;
/*!40000 ALTER TABLE `dg_order_progress` DISABLE KEYS */;
INSERT INTO `dg_order_progress` VALUES (1,6,NULL,NULL,113.82389567057292,22.73492350260417,'null',0,'2017-11-09 05:13:34','第一季热',1),(2,6,NULL,NULL,113.82382,22.735337,'null',0,'2017-11-09 05:39:17','',1),(3,6,NULL,NULL,113.823791,22.735343,'null',0,'2017-11-09 05:50:16','',1),(4,6,NULL,NULL,113.823791,22.735343,'null',0,'2017-11-09 05:50:23','后有空',1),(5,6,NULL,NULL,113.922014,22.500124,'null',0,'2017-11-09 18:00:34','',1),(6,6,NULL,NULL,0,0,'null',0,'2017-11-10 01:28:47','',1),(7,6,NULL,NULL,0,0,'null',0,'2017-11-10 01:28:54','',1);
/*!40000 ALTER TABLE `dg_order_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_dept`
--

DROP TABLE IF EXISTS `tfw_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_dept` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NUM` int(11) DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `SIMPLENAME` varchar(45) DEFAULT NULL,
  `FULLNAME` varchar(255) DEFAULT NULL,
  `TIPS` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_dept`
--

LOCK TABLES `tfw_dept` WRITE;
/*!40000 ALTER TABLE `tfw_dept` DISABLE KEYS */;
INSERT INTO `tfw_dept` VALUES (1,0,0,'IKKONG','IKKONG',NULL,2);
/*!40000 ALTER TABLE `tfw_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_message`
--

DROP TABLE IF EXISTS `dg_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_message` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `dg_id` bigint(22) DEFAULT NULL COMMENT '电工用户ID',
  `sys_id` bigint(22) DEFAULT NULL COMMENT '系统管理员id',
  `status` int(1) DEFAULT NULL COMMENT '消息状态0未读，1已读',
  `type` int(1) DEFAULT NULL COMMENT '消息类型0用户消息，1系统消息',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `read_time` timestamp NULL DEFAULT NULL COMMENT '阅读时间',
  `content` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '消息内容,150个字符之内',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='系统消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_message`
--

LOCK TABLES `dg_message` WRITE;
/*!40000 ALTER TABLE `dg_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `dg_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tfw_blog`
--

DROP TABLE IF EXISTS `tb_tfw_blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_tfw_blog` (
  `f_it_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `f_it_del` int(11) DEFAULT '0' COMMENT '状态',
  `f_it_seq` int(11) DEFAULT '0' COMMENT '排序',
  `f_it_content` text COMMENT '内容',
  `f_it_title` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '标题',
  `f_it_createtime` date DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`f_it_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tfw_blog`
--

LOCK TABLES `tb_tfw_blog` WRITE;
/*!40000 ALTER TABLE `tb_tfw_blog` DISABLE KEYS */;
INSERT INTO `tb_tfw_blog` VALUES (1,1,1,'jfinalblade go','jfinalblade','2016-10-08',2),(3,1,2,'<p style=\"text-align: center;\">112</p>','测试','2016-10-10',1),(4,1,3,'<p><img src=\"/upload/image/20161010/1476091111051005215.jpg\" style=\"\" title=\"1476091111051005215.jpg\"/></p><p><img src=\"/upload/image/20161010/1476091111054037337.jpg\" style=\"\" title=\"1476091111054037337.jpg\"/></p><p><img src=\"/upload/image/20161010/1476091111051097841.jpg\" style=\"\" title=\"1476091111051097841.jpg\"/></p><p><br/></p>','测试1','2016-10-10',0),(5,1,4,'<p><img src=\"http://ikkong.qiniudn.com/upload/image/20161011/1476170956158098341.jpg\" title=\"1476170956158098341\" alt=\"3 (12).jpg\"/></p>','测试qiniu','2016-10-11',0);
/*!40000 ALTER TABLE `tb_tfw_blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_relation`
--

DROP TABLE IF EXISTS `tfw_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_relation` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENUID` int(11) DEFAULT NULL,
  `ROLEID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8642 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_relation`
--

LOCK TABLES `tfw_relation` WRITE;
/*!40000 ALTER TABLE `tfw_relation` DISABLE KEYS */;
INSERT INTO `tfw_relation` VALUES (1384,72,6),(1385,73,6),(1386,74,6),(1387,75,6),(1388,76,6),(1389,77,6),(1390,78,6),(1391,79,6),(1392,80,6),(1393,81,6),(1394,82,6),(1395,84,6),(1396,85,6),(1397,86,6),(1398,87,6),(1399,83,6),(1400,88,6),(1401,89,6),(1402,90,6),(1403,91,6),(1524,1,25),(1525,62,25),(1526,64,25),(1527,72,25),(1528,73,25),(1529,74,25),(1530,75,25),(1531,76,25),(1532,77,25),(1533,78,25),(1534,79,25),(1535,80,25),(1668,81,5),(1669,82,5),(1670,84,5),(1671,85,5),(1672,86,5),(1673,87,5),(1980,1,4),(1981,2,4),(1982,3,4),(1983,4,4),(1984,5,4),(1985,6,4),(1986,7,4),(1987,81,4),(1988,82,4),(1989,84,4),(1990,85,4),(1991,86,4),(1992,87,4),(1993,83,4),(1994,88,4),(1995,89,4),(1996,90,4),(1997,91,4),(3429,92,8),(3430,93,8),(3431,94,8),(3432,95,8),(3433,96,8),(3434,97,8),(3435,1,8),(3436,8,8),(3437,9,8),(3438,10,8),(3439,11,8),(3440,12,8),(3441,13,8),(3442,14,8),(3443,15,8),(3444,16,8),(3445,17,8),(3446,18,8),(3447,19,8),(3448,20,8),(3449,21,8),(3450,22,8),(3451,23,8),(3452,24,8),(3453,39,8),(3454,40,8),(3455,41,8),(3456,42,8),(3457,43,8),(3458,44,8),(3459,45,8),(3460,46,8),(3461,47,8),(3462,48,8),(3463,49,8),(3464,118,8),(3465,119,8),(3466,120,8),(3467,121,8),(3468,122,8),(3469,123,8),(3470,124,8),(3471,125,8),(3472,126,8),(3473,127,8),(3474,128,8),(3475,129,8),(3476,130,8),(3477,131,8),(3478,132,8),(3479,133,8),(3480,134,8),(3481,135,8),(3482,136,8),(3483,137,8),(3484,138,8),(3485,139,8),(3486,140,8),(3487,141,8),(3488,142,8),(3489,143,8),(3490,144,8),(3491,145,8),(3492,146,8),(3493,147,8),(3494,148,8),(3495,149,8),(3496,150,8),(3497,151,8),(3498,152,8),(3499,153,8),(3500,154,8),(3501,155,8),(3502,156,8),(3503,157,8),(3504,158,8),(3505,159,8),(3506,160,8),(3507,161,8),(3508,162,8),(3509,163,8),(7367,1,3),(7368,8,3),(7369,9,3),(7370,10,3),(7371,11,3),(7372,12,3),(7373,13,3),(7374,14,3),(7375,15,3),(7376,16,3),(7377,17,3),(7378,18,3),(7379,19,3),(7380,20,3),(7381,21,3),(7382,22,3),(7383,23,3),(7384,24,3),(7385,2,3),(7386,3,3),(7387,4,3),(7388,5,3),(7389,6,3),(7390,7,3),(7391,25,3),(7392,26,3),(7393,27,3),(7394,28,3),(7395,29,3),(7396,30,3),(7397,31,3),(7398,32,3),(7399,33,3),(7400,34,3),(7401,35,3),(7402,36,3),(7403,37,3),(7404,38,3),(7405,62,3),(7406,64,3),(7407,98,3),(7408,99,3),(7409,100,3),(7410,101,3),(7411,102,3),(7412,103,3),(7413,104,3),(7414,164,3),(7415,119,3),(7416,120,3),(7417,121,3),(7418,122,3),(7419,123,3),(7420,169,3),(7421,181,3),(7422,165,3),(7423,124,3),(7424,125,3),(7425,126,3),(7426,127,3),(7427,128,3),(7428,129,3),(7429,170,3),(7430,171,3),(7431,172,3),(7432,173,3),(7433,180,3),(7434,174,3),(7435,175,3),(7436,176,3),(7437,177,3),(7438,178,3),(7439,167,3),(7440,139,3),(7441,140,3),(7442,141,3),(7443,142,3),(7444,143,3),(7445,182,3),(7446,183,3),(7447,185,3),(7448,187,3),(7449,168,3),(7450,159,3),(7451,160,3),(7452,161,3),(7453,162,3),(7454,163,3),(8452,1,1),(8453,8,1),(8454,9,1),(8455,10,1),(8456,11,1),(8457,12,1),(8458,13,1),(8459,14,1),(8460,15,1),(8461,16,1),(8462,17,1),(8463,18,1),(8464,23,1),(8465,2,1),(8466,3,1),(8467,4,1),(8468,5,1),(8469,6,1),(8470,7,1),(8471,25,1),(8472,26,1),(8473,27,1),(8474,28,1),(8475,29,1),(8476,30,1),(8477,31,1),(8478,32,1),(8479,33,1),(8480,34,1),(8481,35,1),(8482,36,1),(8483,37,1),(8484,38,1),(8485,56,1),(8486,57,1),(8487,58,1),(8488,59,1),(8489,60,1),(8490,61,1),(8491,62,1),(8492,63,1),(8493,64,1),(8494,81,1),(8495,82,1),(8496,84,1),(8497,85,1),(8498,86,1),(8499,87,1),(8500,83,1),(8501,88,1),(8502,89,1),(8503,90,1),(8504,91,1),(8505,164,1),(8506,119,1),(8507,120,1),(8508,121,1),(8509,122,1),(8510,123,1),(8511,169,1),(8512,188,1),(8513,189,1),(8514,190,1),(8515,191,1),(8516,192,1),(8517,193,1),(8518,194,1),(8519,195,1),(8520,196,1),(8521,165,1),(8522,124,1),(8523,125,1),(8524,126,1),(8525,127,1),(8526,128,1),(8527,129,1),(8528,170,1),(8529,171,1),(8530,172,1),(8531,173,1),(8532,180,1),(8533,174,1),(8534,175,1),(8535,176,1),(8536,177,1),(8537,178,1),(8538,167,1),(8539,139,1),(8540,140,1),(8541,141,1),(8542,142,1),(8543,143,1),(8544,183,1),(8545,185,1),(8546,187,1),(8547,168,1),(8548,159,1),(8549,160,1),(8550,161,1),(8551,162,1),(8552,163,1),(8579,1,2),(8580,2,2),(8581,3,2),(8582,4,2),(8583,5,2),(8584,6,2),(8585,7,2),(8586,34,2),(8587,35,2),(8588,36,2),(8589,37,2),(8590,38,2),(8591,81,2),(8592,82,2),(8593,84,2),(8594,85,2),(8595,86,2),(8596,87,2),(8597,83,2),(8598,88,2),(8599,89,2),(8600,90,2),(8601,91,2),(8602,164,2),(8603,119,2),(8604,120,2),(8605,121,2),(8606,122,2),(8607,123,2),(8608,169,2),(8609,188,2),(8610,189,2),(8611,190,2),(8612,191,2),(8613,192,2),(8614,193,2),(8615,194,2),(8616,195,2),(8617,196,2),(8618,165,2),(8619,124,2),(8620,125,2),(8621,126,2),(8622,127,2),(8623,128,2),(8624,129,2),(8625,170,2),(8626,171,2),(8627,172,2),(8628,173,2),(8629,180,2),(8630,167,2),(8631,139,2),(8632,140,2),(8633,141,2),(8634,142,2),(8635,143,2),(8636,183,2),(8637,185,2),(8638,187,2);
/*!40000 ALTER TABLE `tfw_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_audit_files`
--

DROP TABLE IF EXISTS `dg_audit_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_audit_files` (
  `id` bigint(22) NOT NULL COMMENT '自增长ID',
  `file_name` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件名',
  `file_postfix` varchar(16) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件后缀',
  `file_path` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件路径',
  `object_id` bigint(22) DEFAULT NULL COMMENT '所属对象id',
  `object_type` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文件所属类别',
  `create_tm` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电工上传审核文件(文档或证件图片)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_audit_files`
--

LOCK TABLES `dg_audit_files` WRITE;
/*!40000 ALTER TABLE `dg_audit_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `dg_audit_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_operation_log`
--

DROP TABLE IF EXISTS `dg_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_operation_log` (
  `id` int(65) NOT NULL AUTO_INCREMENT,
  `log_name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `method` text,
  `create_time` datetime DEFAULT NULL,
  `succeed` varchar(255) DEFAULT NULL,
  `message` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_operation_log`
--

LOCK TABLES `dg_operation_log` WRITE;
/*!40000 ALTER TABLE `dg_operation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `dg_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_dict`
--

DROP TABLE IF EXISTS `tfw_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_dict` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `NUM` int(11) DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TIPS` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_dict`
--

LOCK TABLES `tfw_dict` WRITE;
/*!40000 ALTER TABLE `tfw_dict` DISABLE KEYS */;
INSERT INTO `tfw_dict` VALUES (1,'101',-1,0,'性别',NULL,1),(2,'101',1,1,'男',NULL,1),(3,'101',0,1,'女',NULL,2),(5,'901',0,0,'账号状态',NULL,0),(6,'901',1,5,'启用',NULL,0),(7,'901',2,5,'冻结',NULL,0),(8,'901',3,5,'待审核',NULL,0),(9,'901',4,5,'审核拒绝',NULL,0),(10,'901',5,5,'已删除',NULL,0),(11,'902',-1,0,'状态',NULL,1),(12,'902',1,11,'启用',NULL,0),(13,'902',2,11,'禁用',NULL,0),(14,'102',0,0,'公告类型',NULL,0),(15,'102',10,14,'通知公告',NULL,0),(16,'102',9,14,'发布计划',NULL,0),(17,'903',0,0,'审核状态',NULL,0),(18,'903',1,17,'待审核',NULL,0),(19,'903',2,17,'审核失败',NULL,2),(20,'903',3,17,'审核通过',NULL,0),(41,'102',6,16,'测试',NULL,0),(44,'102',1,14,'发布测试',NULL,0),(45,'102',2,16,'测试222',NULL,1),(46,'201',-1,0,'申请类型','',0),(47,'201',0,46,'空','',0),(48,'201',1,46,'招标申请','',0),(49,'201',2,46,'终止申请','',0),(50,'201',3,46,'加班申请','',0),(51,'202',-1,0,'申请状态','',0),(52,'202',0,51,'申请中','',0),(53,'202',1,51,'同意','',0),(54,'202',2,51,'拒绝','',0),(55,'202',3,51,'挂起','',0),(56,'203',-1,0,'项目状态','',0),(57,'203',0,56,'保存','',0),(58,'203',1,56,'发布中','',0),(59,'203',2,56,'招标中','',0),(60,'203',3,56,'已招标','',0),(61,'203',4,56,'进行中','',0),(62,'203',5,56,'完成','',0),(63,'203',6,56,'终止','',0),(64,'203',7,56,'撤销','',0),(65,'203',8,56,'挂起','',0),(66,'204',-1,0,'进度状态','',0),(67,'204',0,66,'提交','',0),(68,'204',1,66,'确认','',0),(69,'204',2,66,'默认','',0),(70,'205',-1,0,'交易类型','',0),(71,'205',0,70,'无','',0),(72,'205',1,70,'充值','',0),(73,'205',2,70,'提现','',0),(74,'205',3,70,'付款','',0),(75,'205',4,70,'收款','',0),(76,'205',5,70,'罚款','',0),(77,'205',6,70,'奖励','',0),(78,'206',-1,0,'消息状态','',0),(79,'206',0,78,'未读','',0),(80,'206',1,78,'已读','',0),(81,'207',-1,0,'消息类型','',0),(82,'207',0,81,'用户消息','',0),(83,'207',1,81,'系统消息','',0),(84,'208',-1,0,'电工状态','',0),(85,'208',0,84,'正常','',0),(86,'208',1,84,'挂起','',0),(87,'208',2,84,'冻结','',0),(88,'209',-1,0,'工种审核状态','',0),(89,'209',0,88,'待审核','',0),(90,'209',1,88,'审核通过','',0),(91,'209',2,88,'审核失败','',0),(92,'210',-1,47,'工种是否认证','',0),(93,'210',0,92,'需要','',0),(94,'210',1,92,'不需要','',0),(95,'211',-1,0,'发单权限','',0),(96,'211',0,95,'有权限发单','',0),(97,'211',1,95,'无权限发单','',0),(98,'212',-1,0,'电工工种审核状态','用于表dg_user_worktype',0),(99,'212',0,98,'正常','',0),(100,'212',1,98,'待审核','',0),(101,'212',2,98,'审核中','',0),(102,'212',3,98,'审核通过','',0),(103,'212',4,98,'审核失败','',0),(104,'904',-1,0,'时效配置类型','用于配置时效的类型选择',2),(105,'904',1,104,'天数','按天算,每天24小时记',1),(106,'904',2,104,'小时数','按多少小时记',2),(107,'904',6,104,'分钟数','按多少分钟记',4),(108,'904',3,104,'现金额度','以具体现金额度来记',0),(109,'904',4,104,'百分比','根据具体的数据按百分比来计算',0),(110,'904',5,104,'次数','设定按多少次为上限',0),(111,'208',3,84,'注销','账户注销状态',0);
/*!40000 ALTER TABLE `tfw_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_login_log`
--

DROP TABLE IF EXISTS `dg_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_login_log` (
  `ID` int(65) NOT NULL AUTO_INCREMENT,
  `LOGNAME` varchar(255) DEFAULT NULL,
  `USERID` varchar(255) DEFAULT NULL,
  `CLASSNAME` varchar(255) DEFAULT NULL,
  `METHOD` text,
  `CREATETIME` datetime DEFAULT NULL,
  `SUCCEED` varchar(255) DEFAULT NULL,
  `MESSAGE` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_login_log`
--

LOCK TABLES `dg_login_log` WRITE;
/*!40000 ALTER TABLE `dg_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `dg_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_user`
--

DROP TABLE IF EXISTS `tfw_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `SALT` varchar(45) DEFAULT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `SEX` int(11) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `PHONE` varchar(45) DEFAULT NULL,
  `ROLEID` varchar(255) DEFAULT NULL,
  `DEPTID` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_user`
--

LOCK TABLES `tfw_user` WRITE;
/*!40000 ALTER TABLE `tfw_user` DISABLE KEYS */;
INSERT INTO `tfw_user` VALUES (1,'admin','956ea2516aaf6c4cf428278680e54894','admin','管理员','2015-09-08 00:00:00',1,'admin@tonbusoft.com.cn','111111','1',9,1,'2016-01-29 08:49:53',26),(24,'wangtao','76d2464e43115a2fb07fae74e68c26a1','wog6s','wangtao','2004-03-18 00:00:00',1,'mike@126.com','13899623638','2',6,1,'2017-08-16 15:01:54',3),(27,'bababa','6b194c02e568c0181fd006e34f16e5de','d3xcv','abc','2017-11-21 00:00:00',0,'','',NULL,0,2,'2017-11-15 22:12:54',NULL);
/*!40000 ALTER TABLE `tfw_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_worktype_check`
--

DROP TABLE IF EXISTS `dg_worktype_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_worktype_check` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `dg_user_id` bigint(22) NOT NULL COMMENT '电工用户id',
  `work_id` int(11) NOT NULL COMMENT '工种类型id',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '̬01;\n\n\n;\n;\n',
  `user_worktype_id` bigint(22) DEFAULT NULL COMMENT '用户工种Id',
  `id_code` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `idcard_up` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证正面',
  `idcard_down` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '身份证反面',
  `qualif_cert` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '资格证书',
  `mark` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '审核失败说明',
  `admin_id` int(11) DEFAULT NULL COMMENT '审核管理员id',
  `create_tm` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_tm` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) DEFAULT '1' COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_worktype_check`
--

LOCK TABLES `dg_worktype_check` WRITE;
/*!40000 ALTER TABLE `dg_worktype_check` DISABLE KEYS */;
INSERT INTO `dg_worktype_check` VALUES (1,3,1,2,NULL,'431024198805163615','c1f5168825224323a0a124400c2f92b4.png','4a5d1cc0715049f58765e386ed522249.png','cfa1563d6f3249b8b2a5b361fac7cb55.png',NULL,24,'2017-10-30 06:21:02',NULL,2),(2,3,1,1,NULL,'431024198805163615','f8bbe11651b945688cd554c829bf15d5.png','1c4752dfc796424493f7d358d2a0f9c3.png','d0017ea7d70c4d01b480a141bb067e43.png',NULL,24,'2017-10-30 06:23:14',NULL,2),(3,6,9,2,NULL,'431024198805163615','b0208ccee3564c498605e400669f6971.png','1f7e79ec7cb34a9cb3ae7a67cb50d985.png','3860e0a8bbbb4209b30d09cff0fc7867.png',NULL,24,'2017-11-02 05:18:30',NULL,2),(4,6,11,0,NULL,'431024198805163615','0ae8212637ac41428171e024e8209e89.png','7e35821dca6c43b1b79a24ba19ef2315.png',NULL,NULL,NULL,'2017-11-02 06:49:22',NULL,1),(5,6,2,1,11,'431024198805163615','0f9d388584884bda82c104c802ba3d7e.png','4f6f196db43c4a03a127662747652071.png',NULL,NULL,24,'2017-11-06 04:39:52',NULL,2),(6,6,4,1,14,'431024198805163615','775df78cb7f74bb28be271b790465c60.png','be9d5dfa10f44b53b5756e0405040a0b.png','8d97d7d4b94743638291d721e639f990.png',NULL,24,'2017-11-10 00:03:37',NULL,2),(7,6,4,1,14,'431024198805163615','8c7bc34d6650428f8061ff62501d7f83.png','b9c9ed007a904aeea010fc35e3315836.png','c83887c911204aab831eb61bd152a22a.png',NULL,24,'2017-11-10 00:03:43',NULL,2),(8,6,4,1,14,'431024198805163615','baf663e3ce66442eb58988e46279bd88.png','af8da631bec04fd0a8cd925fb8918862.png','db0d92a28cf34504bfcf50a3a96a063b.png',NULL,24,'2017-11-10 00:03:49',NULL,2),(9,6,4,1,14,'431024198805163615','8b6abef79edd4c1a924fa890b877ad34.png','d50686730feb49e8878cbf6e497fd0ea.png','7e58b194e04c456f9fb19922b7ba4bb3.png',NULL,24,'2017-11-10 00:04:00',NULL,2),(10,6,4,1,14,'431024198805163615','16445a248cbb42c78df5b7eed0144889.png','0827bcf775434c0c846d8e0a065c3fcb.png','da347d4499214bb99f2d05339f865f6a.png',NULL,24,'2017-11-10 00:04:09',NULL,2),(11,6,1,0,9,'431024198805163615','f9d59fe43bd14a30ac38e7f0ff986f4e.png','59ceb3153d034d20ad5c04da667a058d.png','eea4c62cea93470ca65051838573daa0.png',NULL,NULL,'2017-11-11 22:30:27',NULL,1);
/*!40000 ALTER TABLE `dg_worktype_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_role_ext`
--

DROP TABLE IF EXISTS `tfw_role_ext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_role_ext` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(255) DEFAULT NULL,
  `ROLEIN` text,
  `ROLEOUT` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_role_ext`
--

LOCK TABLES `tfw_role_ext` WRITE;
/*!40000 ALTER TABLE `tfw_role_ext` DISABLE KEYS */;
INSERT INTO `tfw_role_ext` VALUES (27,'66','1,44,49','45'),(47,'2','0','8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24'),(48,'63','0','0'),(49,'72','0','0'),(50,'74','0','0'),(67,'1','118,119,120,121','118,119,120,121'),(87,'168','92,103,104,105,106,107','109,110,111,112,113,114,115,116,117,118,119,120,121,122'),(107,'189','108,109,110,111,112,113,114,115,116,117,118,119,120,121,122','0'),(127,'21','92,1,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,39,40,41,42,43,98,99,100,101,102,103,104','0'),(128,'25','0','0'),(129,'24','92,105,106,107,108,109,1,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,2,3,4,5,6,7','0');
/*!40000 ALTER TABLE `tfw_role_ext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_menu`
--

DROP TABLE IF EXISTS `tfw_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_menu` (
  `ID` int(65) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `PCODE` varchar(255) DEFAULT NULL,
  `ALIAS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `ICON` varchar(255) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `NUM` int(65) DEFAULT NULL,
  `LEVELS` int(65) DEFAULT NULL,
  `SOURCE` text,
  `PATH` varchar(255) DEFAULT NULL,
  `TIPS` varchar(255) DEFAULT NULL,
  `STATUS` int(65) DEFAULT NULL,
  `ISOPEN` varchar(255) DEFAULT NULL,
  `ISTEMPLATE` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_menu`
--

LOCK TABLES `tfw_menu` WRITE;
/*!40000 ALTER TABLE `tfw_menu` DISABLE KEYS */;
INSERT INTO `tfw_menu` VALUES (1,'system','0',NULL,'系统管理','fa-cog',NULL,9,1,NULL,NULL,NULL,1,'1','0',3),(2,'role','system',NULL,'角色管理','fa-key','/role/',2,2,NULL,NULL,NULL,1,'0',NULL,1),(3,'role_add','role','addex','角色新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/role/add',1,3,NULL,'role_add.html','800*340',1,'0',NULL,2),(4,'role_edit','role','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/role/edit',2,3,NULL,'role_edit.html','800*340',1,'0','0',1),(5,'role_remove','role','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/role/remove',3,3,NULL,NULL,NULL,1,'0',NULL,1),(6,'role_view','role','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/role/view',4,3,NULL,'role_view.html','800*340',1,NULL,NULL,1),(7,'role_authority','role','authority','权限配置','btn btn-xs btn-white | fa fa-wrench  bigger-120','/role/authority',5,3,NULL,'role_authority.html','350*660',1,'0',NULL,2),(8,'user','system',NULL,'用户管理','fa-user','/user/',1,2,NULL,NULL,NULL,1,NULL,NULL,0),(9,'user_add','user','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/user/add',1,3,NULL,'user_add.html','800*430',1,NULL,NULL,0),(10,'user_edit','user','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/user/edit',2,3,NULL,'user_edit.html','800*430',1,NULL,NULL,0),(11,'user_remove','user','remove','删除','btn btn-xs btn-white | fa fa fa-times bigger-120','/user/remove',3,3,NULL,NULL,NULL,1,NULL,NULL,0),(12,'user_view','user','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/user/view',4,3,NULL,'user_view.html','800*390',1,NULL,NULL,0),(13,'user_audit','user','audit','审核','btn btn-xs btn-white | fa fa-user  bigger-120','{\"status\":\"3\"}',5,3,NULL,NULL,NULL,1,NULL,NULL,0),(14,'user_audit_ok','user_audit','ok','通过','btn btn-xs btn-white | fa fa-check  bigger-120','/user/auditOk',1,4,NULL,NULL,NULL,1,NULL,NULL,0),(15,'user_audit_refuse','user_audit','refuse','拒绝','btn btn-xs btn-white | fa fa-times  bigger-120','/user/auditRefuse',2,4,NULL,NULL,NULL,1,NULL,NULL,0),(16,'user_audit_back','user_audit','back','返回','btn btn-xs btn-white | fa fa-undo  bigger-120',NULL,3,4,NULL,NULL,NULL,1,NULL,NULL,0),(17,'user_reset','user','reset','重置密码','btn btn-xs btn-white | fa fa-key  bigger-120','/user/reset',6,3,NULL,NULL,NULL,1,NULL,NULL,0),(18,'user_ban','user','frozen','冻结','btn btn-xs btn-white | fa fa-ban  bigger-120','/user/ban',7,3,NULL,NULL,NULL,1,NULL,NULL,0),(23,'user_roleAssign','user','assign','角色分配','btn btn-xs btn-white | fa fa-users bigger-120','/user/roleAssign',9,3,NULL,'user_roleAssign.html','350*500',1,NULL,NULL,0),(24,'user_extrole','user','agent','权限代理','btn btn-xs btn-white | fa fa-wrench  bigger-120','/user/extrole',10,3,NULL,'user_extrole.html',NULL,1,NULL,NULL,0),(25,'menu','system',NULL,'菜单管理','fa-tasks','/menu/',3,2,NULL,NULL,NULL,1,NULL,NULL,0),(26,'menu_add','menu','addex','菜单新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/menu/add',1,3,NULL,'menu_add.html','800*430',1,'0','0',1),(27,'menu_edit','menu','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/menu/edit',2,3,NULL,'menu_edit.html','800*430',1,'0','0',1),(28,'menu_remove','menu','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/menu/remove',3,3,NULL,NULL,NULL,1,'0',NULL,1),(29,'menu_view','menu','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/menu/view',4,3,NULL,'menu_view.html','800*430',1,'0','0',1),(30,'menu_recycle','menu','recycle','回收站','btn btn-xs btn-white | fa fa-recycle  bigger-120','{\"status\":\"2\"}',5,3,NULL,NULL,NULL,1,NULL,NULL,0),(31,'menu_recycle_restore','menu_recycle','restore','还原','btn btn-xs btn-white | fa fa-refresh  bigger-120','/menu/restore',1,4,NULL,NULL,NULL,1,NULL,NULL,0),(32,'menu_recycle_remove','menu_recycle','remove','彻底删除','btn btn-xs btn-white  btn-danger | fa fa fa-times bigger-120','/menu/remove',2,4,NULL,NULL,NULL,1,'0',NULL,1),(33,'menu_recycle_back','menu_recycle','back','返回','btn btn-xs btn-white | fa fa-undo  bigger-120',NULL,3,4,NULL,NULL,NULL,1,NULL,NULL,0),(34,'dict','system',NULL,'字典管理','fa fa-book','/dict/',4,2,NULL,NULL,NULL,1,NULL,NULL,0),(35,'dict_add','dict','addex','字典新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/dict/add',1,3,NULL,'dict_add.html','800*390',1,NULL,NULL,0),(36,'dict_edit','dict','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/dict/edit',2,3,NULL,'dict_edit.html','800*390',1,NULL,NULL,0),(37,'dict_remove','dict','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/dict/remove',3,3,NULL,NULL,NULL,1,NULL,NULL,0),(38,'dict_view','dict','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/dict/view',4,3,NULL,'dict_view.html','800*390',1,NULL,NULL,0),(39,'dept','system',NULL,'部门管理','fa fa-users','/dept/',5,2,NULL,NULL,NULL,1,NULL,NULL,0),(40,'dept_add','dept','addex','部门新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/dept/add',1,3,NULL,'dept_add.html','800*340',1,NULL,NULL,0),(41,'dept_edit','dept','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/dept/edit',2,3,NULL,'dept_edit.html','800*340',1,NULL,NULL,0),(42,'dept_remove','dept','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/dept/remove',3,3,NULL,NULL,NULL,1,NULL,NULL,0),(43,'dept_view','dept','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/dept/view',4,3,NULL,'dept_view.html','800*340',1,'0','0',0),(44,'attach','system',NULL,'附件管理','fa fa-paperclip','/attach/',6,2,NULL,'attach.html',NULL,1,'0','0',0),(45,'attach_add','attach','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/attach/add',1,3,NULL,'attach_add.html','800*450',1,'0','0',0),(46,'attach_edit','attach','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/attach/edit',2,3,NULL,'attach_edit.html','800*290',1,'0',NULL,0),(47,'attach_remove','attach','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/attach/remove',3,3,NULL,NULL,NULL,1,NULL,NULL,0),(48,'attach_view','attach','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/attach/view',4,3,NULL,'attach_view.html','800*450',1,'0','0',1),(49,'attach_download','attach','download','下载','btn btn-xs btn-white | fa fa-paperclip bigger-120','/attach/download',5,3,NULL,NULL,NULL,1,NULL,NULL,0),(56,'parameter','system',NULL,'参数化管理','fa-tags','/parameter/',9,2,NULL,'parameter.html',NULL,1,'0','1',0),(57,'parameter_add','parameter','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/parameter/add',1,3,NULL,'parameter_add.html',NULL,1,'0','0',0),(58,'parameter_edit','parameter','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/parameter/edit',2,3,NULL,'parameter_edit.html',NULL,1,'0','0',0),(59,'parameter_remove','parameter','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/parameter/remove',3,3,NULL,NULL,NULL,1,'0','0',1),(60,'parameter_view','parameter','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/parameter/view',4,3,NULL,'parameter_view.html',NULL,1,'0','0',0),(61,'parameter_recycle','parameter','recycle','回收站','btn btn-xs btn-white | fa fa-recycle  bigger-120','{\"status\":\"5\"}',5,3,NULL,'parameter_recycle.html',NULL,1,'0','0',0),(62,'parameter_recycle_restore','parameter_recycle','restore','还原','btn btn-xs btn-white | fa fa-refresh  bigger-120','/parameter/restore',1,4,NULL,NULL,NULL,1,'0','0',0),(63,'parameter_recycle_remove','parameter_recycle','remove','彻底删除','btn btn-xs btn-white  btn-danger | fa fa fa-times bigger-120','/parameter/remove',2,4,NULL,NULL,NULL,1,'0','0',1),(64,'parameter_recycle_back','parameter_recycle','back','返回','btn btn-xs btn-white | fa fa-undo  bigger-120',NULL,3,4,NULL,NULL,NULL,1,'0','0',0),(65,'druid','system',NULL,'连接池监视','fa-arrows-v','/druid',10,2,NULL,NULL,NULL,1,'0',NULL,2),(81,'log','system',NULL,'日志管理','fa-tasks',NULL,11,2,NULL,NULL,NULL,1,'0','0',1),(82,'olog','log',NULL,'操作日志','fa-database','/olog/',1,3,NULL,'olog.html',NULL,1,'0','0',0),(83,'llog','log',NULL,'登录日志','fa-sign-in','/llog/',2,3,NULL,'llog.html',NULL,1,'0','1',0),(84,'olog_add','olog','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/olog/add',1,4,NULL,'olog_add.html',NULL,1,'0','0',0),(85,'olog_edit','olog','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/olog/edit',2,4,NULL,'olog_edit.html',NULL,1,'0','0',0),(86,'olog_remove','olog','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/olog/remove',3,4,NULL,NULL,NULL,1,'0','0',0),(87,'olog_view','olog','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/olog/view',4,4,NULL,'olog_view.html',NULL,1,'0','0',0),(88,'llog_add','llog','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/llog/add',1,4,NULL,'llog_add.html',NULL,1,'0','0',0),(89,'llog_edit','llog','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/llog/edit',2,4,NULL,'llog_edit.html',NULL,1,'0','0',0),(90,'llog_remove','llog','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/llog/remove',3,4,NULL,NULL,NULL,1,'0','0',0),(91,'llog_view','llog','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/llog/view',4,4,NULL,'llog_view.html',NULL,1,'0','0',0),(98,'online','system','','在线开发','fa-rocket',NULL,12,2,NULL,NULL,'800*520',1,'0',NULL,1),(99,'generate','online',NULL,'代码生成','fa-gavel','/generate/',1,3,NULL,NULL,'800*520',1,'0',NULL,1),(100,'generate_add','generate','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/generate/add',1,4,NULL,NULL,'800*420',1,'0',NULL,3),(101,'generate_edit','generate','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/generate/edit',2,4,NULL,NULL,'800*420',1,'0',NULL,3),(102,'generate_remove','generate','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/generate/remove',3,4,NULL,NULL,'800*520',1,'0',NULL,0),(103,'generate_view','generate','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/generate/view',4,4,NULL,NULL,'800*420',1,'0',NULL,3),(104,'generate_gencode','generate','gencode','代码生成','btn btn-xs btn-white | fa fa-gavel bigger-120','/generate/gencode',5,4,NULL,NULL,'800*520',1,'0',NULL,1),(119,'dgUser','dg-user','','电工用户','fa-user','/dgUser/',1,2,NULL,NULL,'',1,'0',NULL,1),(120,'dgUser_view','dgUser','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/dgUser/view',1,3,NULL,NULL,'800*540',1,'0',NULL,2),(121,'dgUser_add','dgUser','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/dgUser/add',2,3,NULL,NULL,'800*580',1,'0',NULL,1),(122,'dgUser_edit','dgUser','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/dgUser/edit',3,3,NULL,NULL,'800*520',1,'0',NULL,1),(123,'dgUser_remove','dgUser','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/dgUser/remove',4,3,NULL,NULL,'800*520',1,'0',NULL,1),(124,'workType','work-type','','工种管理','fa-user','/workType/',2,2,NULL,NULL,'',1,'0',NULL,1),(125,'workType_view','workType','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/workType/view',1,3,NULL,NULL,'800*350',1,'0',NULL,2),(126,'workType_add','workType','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/workType/add',2,3,NULL,NULL,'800*350',1,'0',NULL,1),(127,'workType_edit','workType','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/workType/edit',3,3,NULL,NULL,'800*350',1,'0',NULL,1),(128,'workType_remove','workType','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/workType/remove',4,3,NULL,NULL,'800*350',1,'0',NULL,1),(129,'workTypeCheck','work-type','','审核管理','fa-user','/workTypeCheck/',3,2,NULL,NULL,'',1,'0',NULL,1),(139,'dgOrder','dg-order','','订单管理','fa-user','/dgOrder/',5,2,NULL,NULL,'',1,'0',NULL,1),(140,'dgOrder_view','dgOrder','view','查看','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/dgOrder/view',1,3,NULL,NULL,'800*540',1,'0',NULL,1),(141,'dgOrder_add','dgOrder','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/dgOrder/add',2,3,NULL,NULL,'800*540',1,'0',NULL,1),(142,'dgOrder_edit','dgOrder','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/dgOrder/edit',3,3,NULL,NULL,'800*520',1,'0',NULL,1),(143,'dgOrder_remove','dgOrder','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/dgOrder/remove',4,3,NULL,NULL,'800*520',1,'0',NULL,1),(159,'messages','dg-message','','消息管理','fa-user','/messages/',9,2,NULL,NULL,'',1,'0',NULL,1),(160,'messages_view','messages','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/messages/view',1,3,NULL,NULL,'800*540',1,'0',NULL,2),(161,'messages_add','messages','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/messages/add',2,3,NULL,NULL,'800*540',1,'0',NULL,1),(162,'messages_edit','messages','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/messages/edit',3,3,NULL,NULL,'800*520',1,'0',NULL,1),(163,'messages_remove','messages','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/messages/remove',4,3,NULL,NULL,'800*520',1,'0',NULL,1),(164,'dg-user','0','','电工用户','fa-users','',11,1,NULL,NULL,'',1,'1',NULL,1),(165,'work-type','0','','工种管理','fa-child','',12,1,NULL,NULL,'',1,'1',NULL,5),(167,'dg-order','0','','项目管理','fa-shopping-cart','',14,1,NULL,NULL,'',1,'1',NULL,1),(168,'dg-message','0','','消息管理','fa-comment','',15,1,NULL,NULL,'',1,'1',NULL,2),(169,'dgUser_relieve','dgUser','relieve','解冻','btn btn-xs btn-white | fa fa-retweet bigger-120','/dgUser/relieve',5,3,NULL,NULL,'800*520',1,'0',NULL,7),(170,'workTypeCheck_add','workTypeCheck','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/workTypeCheck/add',1,3,NULL,NULL,'800*520',1,'0',NULL,0),(171,'workTypeCheck_edit','workTypeCheck','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/workTypeCheck/edit',2,3,NULL,NULL,'800*520',1,'0',NULL,0),(172,'workTypeCheck_view','workTypeCheck','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/workTypeCheck/view',3,3,NULL,NULL,'800*520',1,'0',NULL,1),(173,'workTypeCheck_remove','workTypeCheck','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/workTypeCheck/remove',4,3,NULL,NULL,'800*520',1,'0',NULL,0),(174,'userWorktype','work-type','','电工工种','fa-user','/userWorktype/',4,2,NULL,NULL,'800*520',1,'0',NULL,2),(175,'userWorktype_add','userWorktype','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/userWorktype/add',1,3,NULL,NULL,'800*520',1,'0',NULL,0),(176,'userWorktype_edit','userWorktype','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/userWorktype/edit',2,3,NULL,NULL,'800*520',1,'0',NULL,0),(177,'userWorktype_view','userWorktype','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/userWorktype/view',3,3,NULL,NULL,'800*520',1,'0',NULL,0),(178,'userWorktype_remove','userWorktype','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/userWorktype/remove',4,3,NULL,NULL,'800*520',1,'0',NULL,0),(180,'workTypeCheck_auditCheck','workTypeCheck','auditCheck','审核','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/workTypeCheck/auditCheck',5,3,NULL,NULL,'800*520',1,'0',NULL,0),(183,'workOrder_Audit','dgOrder','workOrderAudit','审核','btn btn-xs btn-white | fa fa-user  bigger-120','/workOrder/workOrderAudit',5,3,NULL,NULL,'400*320',1,'0',NULL,1),(185,'workOrder_Process','dgOrder','workOrderProcess','进度','btn btn-xs btn-white | fa fa-battery-three-quarters bigger-120','/workOrder/workOrderProcess',6,3,NULL,NULL,'800*500',1,'0',NULL,2),(187,'workOrder_Apply','dgOrder','workOrderApply','申请','btn btn-xs btn-white | fa fa-user-circle-o bigger-120','/workOrder/workOrderApply',7,3,NULL,NULL,'800*520',1,'0',NULL,2),(188,'dgUser_hangup','dgUser','dgUser_hangup','挂起','btn btn-xs btn-white | fa fa-rebel bigger-120','/dgUser/hangup',6,3,NULL,NULL,'800*520',1,'0',NULL,1),(189,'dgUser_freeze','dgUser','dgUser_frozen','冻结','btn btn-xs btn-white | fa fa-snowflake-o  bigger-120','/dgUser/freeze',7,3,NULL,NULL,'800*520',1,'0',NULL,1),(190,'dgUser_disable','dgUser','dgUser_disable','注销','btn btn-xs btn-white | fa fa-university bigger-120','/dgUser/disable',8,3,NULL,NULL,'800*520',1,'0',NULL,1),(191,'dgUser_userworktype','dgUser','userworktype','电工工种','btn btn-xs btn-white | fa fa-address-card-o bigger-120','/dgUser/userworktype',9,3,NULL,NULL,'800*520',1,'0',NULL,1),(192,'dgUser_business','dgUser','dgUser_business','交易记录','btn btn-xs btn-white | fa fa-briefcase bigger-120','/dgUser/business',10,3,NULL,NULL,'800*520',1,'0',NULL,0),(193,'dgUser_businessView','dgUser_business','view','查看','btn btn-xs btn-white | fa fa-eye bigger-120','/business/view',1,4,NULL,NULL,'700*380',1,'0',NULL,2),(194,'dgUser_businessAdd','dgUser_business','add','新增','btn btn-xs btn-white | fa fa-floppy-o bigger-120','/business/add',2,4,NULL,NULL,'700*380',1,'0',NULL,2),(195,'dgUser_businessEdit','dgUser_business','edit','修改','btn btn-xs btn-white | fa fa-pencil  bigger-120','/business/edit',3,4,NULL,NULL,'700*380',1,'0',NULL,2),(196,'dgUser_businessRemove','dgUser_business','remove','删除','btn btn-xs btn-white | fa fa-times  bigger-120','/business/remove',4,4,NULL,NULL,'800*520',1,'0',NULL,1);
/*!40000 ALTER TABLE `tfw_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_business`
--

DROP TABLE IF EXISTS `dg_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_business` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `dg_id` bigint(22) NOT NULL COMMENT '电工用户ID',
  `money` double NOT NULL DEFAULT '0' COMMENT '交易金额',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '交易类型0无，1充值，2提现，3付款，4收款，5罚款，6奖励',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `mark` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '交易说明,150个字符之内\n',
  `version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='交易记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_business`
--

LOCK TABLES `dg_business` WRITE;
/*!40000 ALTER TABLE `dg_business` DISABLE KEYS */;
INSERT INTO `dg_business` VALUES (1,1,6000,1,'2017-10-20 07:35:20',NULL,1);
/*!40000 ALTER TABLE `dg_business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_parameter`
--

DROP TABLE IF EXISTS `tfw_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_parameter` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `NUM` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PARA` text,
  `TIPS` varchar(255) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `AGINGTYPE` int(11) DEFAULT NULL COMMENT '时效类型',
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_parameter`
--

LOCK TABLES `tfw_parameter` WRITE;
/*!40000 ALTER TABLE `tfw_parameter` DISABLE KEYS */;
INSERT INTO `tfw_parameter` VALUES (1,'101',100,'是否开启记录日志','1','1:是  2:否',1,NULL,9),(2,'801',100,'自动变更招标中时效','2','按小时数计算,主要是在工种订单发布后, 未及时做招标中处理',1,2,1),(3,'802',100,'自动变更已招标时效','1','按天来记,招标中工种单, 未及时做已招标处理',1,1,3),(4,'803',100,'自动变更进行中时效','1','按天来记,工种订单招标后, 未及时做工种单项目进行中处理',1,1,3),(5,'804',100,'项目自动撤销时效','1','按天记, 项目在发布后,无人接单,系统判断在超时后自动撤销',1,1,2),(6,'805',100,'发单拒接次数','3','发单人拒绝接单人的次数',1,5,8),(7,'806',100,'接单人拒绝招标单次数','3','接单人超过这个拒绝已招标的单后系统进行相应的惩罚策略',1,5,1),(8,'807',100,'招标单自动进行时效','2','按天记, 将超过该时效的已招标单设定为进行中..',1,1,2),(9,'808',100,'佣金获取次数','3','推荐人次的佣金获取次数',1,5,0),(10,'809',100,'佣金额占比','20','佣金额度占工种单的成交比率',1,4,0),(11,'810',100,'加班确认变更时效','3','加班确认变更时效以小时计算',1,2,0),(12,'811',100,'终止项目次数','3','发单人终止项目的次数',1,5,0),(13,'812',100,'消息过期时效','2','消息过期时效设置',1,1,0);
/*!40000 ALTER TABLE `tfw_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dg_order`
--

DROP TABLE IF EXISTS `dg_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dg_order` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT,
  `create_id` bigint(22) NOT NULL COMMENT '创建订单的电工用户id',
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单标题，长度不能大于100',
  `content` varchar(800) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '项目头信息说明内容',
  `createtm` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `total_money` double DEFAULT NULL COMMENT '项目总额',
  `address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '项目地址',
  `overflag` char(1) COLLATE utf8_unicode_ci DEFAULT 'F',
  `version` int(11) DEFAULT '1' COMMENT '版本记录号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='电工用户订单管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dg_order`
--

LOCK TABLES `dg_order` WRITE;
/*!40000 ALTER TABLE `dg_order` DISABLE KEYS */;
INSERT INTO `dg_order` VALUES (1,6,'我的产期','购物网','2017-11-03 23:25:07',0,NULL,'F',1),(2,6,'钱周转','6曲你自己','2017-11-04 00:14:57',0,NULL,'F',1),(3,6,'钱周转','6曲你自己','2017-11-04 00:15:47',0,NULL,'F',1),(4,6,'后有空','购买力也','2017-11-04 00:20:47',0,NULL,'F',1),(5,6,'测试项目','休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛苦了休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛苦了休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛苦了休息了啊呜小破孩一颗童心XP密钥军哥啊休息了啊呜小破孩一颗童心XP密钥军哥啊我去外婆辛苦了辛苦了苦了苦了','2017-11-04 23:39:38',2424,NULL,'F',1),(6,6,'测试项目','你在客厅9所','2017-11-05 23:16:59',18085,NULL,'F',1),(7,6,'看看','阿册','2017-11-07 19:54:55',1700,NULL,'F',1),(8,6,'测试项目你最近热','自己做肉文','2017-11-12 03:07:11',27015,NULL,'F',1),(9,6,'科苑鼎鑫华庭','科苑鼎鑫华庭CBD','2017-11-12 04:07:11',27015,NULL,'F',1);
/*!40000 ALTER TABLE `dg_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tfw_generate`
--

DROP TABLE IF EXISTS `tfw_generate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tfw_generate` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `REALPATH` varchar(255) DEFAULT NULL,
  `PACKAGENAME` varchar(255) DEFAULT NULL,
  `MODELNAME` varchar(255) DEFAULT NULL,
  `TABLENAME` varchar(255) DEFAULT NULL,
  `PKNAME` varchar(255) DEFAULT NULL,
  `TIPS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tfw_generate`
--

LOCK TABLES `tfw_generate` WRITE;
/*!40000 ALTER TABLE `tfw_generate` DISABLE KEYS */;
INSERT INTO `tfw_generate` VALUES (3,'dgOrder','D:\\Users','com.ikkong.dg','DgOrder','sys_dg_order','id',NULL),(4,'dgUser','D:\\Users','com.ikkong.dg','DgUser','sys_dg_user','id',NULL),(5,'workType','D:\\Users','com.ikkong.dg','WorkType','sys_dg_worktype','id',NULL),(9,'managerUser','D:\\Users','com.ikkong.dg','ManagerUser','sys_manager_user','id',NULL),(10,'workTypeCheck','D:\\Users','com.ikkong.dg','WorkTypeCheck','sys_worktype_check','id',NULL),(11,'business','D:\\Users','com.ikkong.dg','Business','sys_business','id',NULL),(12,'auditFiles','D:\\Users','com.ikkong.dg','AuditFiles','sys_dg_audit_files','id',NULL),(13,'messages','D:\\Users','com.ikkong.dg','Messages','sys_message','id',NULL),(14,'ordeProgress','D:\\Users','com.ikkong.dg','OrdeProgress','sys_orde_progress','id',NULL),(15,'orderApply','D:\\Users','com.ikkong.dg','OrderApply','sys_order_apply','id',NULL),(16,'workOrder','D:\\Users','com.ikkong.dg','WorkOrder','sys_work_order','id',NULL),(17,'userWorktype','D:\\Users','com.ikkong.dg','UserWorktype','sys_user_worktype','id',NULL);
/*!40000 ALTER TABLE `tfw_generate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-17 19:09:06
