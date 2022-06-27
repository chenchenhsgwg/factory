-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: cloud
-- ------------------------------------------------------
-- Server version	5.7.34

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `receiver` varchar(100) NOT NULL,
  `telephone` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `address_FK` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='联系地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (8,1,'123123','123123','123123'),(10,1,'1231','121','121'),(11,1,'23','2323','23323'),(12,1,'qwe','qwe','123'),(13,1,'222','222','222');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `resource` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `component` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `parentId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'用户保存','user:save',NULL,NULL,NULL,45),(2,'用户删除','user:delete',NULL,NULL,NULL,45),(3,'用户编辑','user:update',NULL,NULL,NULL,45),(4,'用户列表','user:view',NULL,NULL,NULL,45),(5,'产品类型保存','ptype:add',NULL,NULL,NULL,46),(6,'产品类型删除','ptype:delete',NULL,NULL,NULL,46),(7,'产品类型编辑','ptype:update',NULL,NULL,NULL,46),(8,'产品类型列表','ptype:view',NULL,NULL,NULL,46),(9,'产品保存','product:add',NULL,NULL,NULL,47),(10,'产品删除','product:delete',NULL,NULL,NULL,47),(11,'产品编辑','product:update',NULL,NULL,NULL,47),(12,'产品列表','product:view',NULL,NULL,NULL,47),(13,'设备类型保存','etype:add',NULL,NULL,NULL,48),(14,'设备类型删除','etype:delete',NULL,NULL,NULL,48),(15,'设备类型编辑','etype:update',NULL,NULL,NULL,48),(16,'设备类型列表','etype:view',NULL,NULL,NULL,48),(17,'设备保存','equipment:add',NULL,NULL,NULL,49),(18,'设备删除','equipment:delete',NULL,NULL,NULL,49),(19,'设备编辑','equipment:update',NULL,NULL,NULL,49),(20,'设备列表','equipment:view',NULL,NULL,NULL,49),(21,'订单保存','order:add',NULL,NULL,NULL,50),(22,'订单删除','order:delete',NULL,NULL,NULL,50),(23,'订单编辑','order:update',NULL,NULL,NULL,50),(24,'订单列表','order:view',NULL,NULL,NULL,50),(25,'竞标保存','bid:add',NULL,NULL,NULL,51),(26,'竞标删除','bid:delete',NULL,NULL,NULL,51),(27,'竞标编辑','bid:update',NULL,NULL,NULL,51),(28,'竞标列表','bid:view',NULL,NULL,NULL,51),(29,'排产保存','manu:add',NULL,NULL,NULL,52),(30,'排产删除','manu:delete',NULL,NULL,NULL,52),(31,'排产编辑','manu:update',NULL,NULL,NULL,52),(32,'排产列表','manu:view',NULL,NULL,NULL,52),(33,'地址保存','address:add',NULL,NULL,NULL,53),(34,'地址删除','address:delete',NULL,NULL,NULL,53),(35,'地址编辑','address:update',NULL,NULL,NULL,53),(36,'地址列表','address:view',NULL,NULL,NULL,53),(37,'工厂保存','factory:add',NULL,NULL,NULL,54),(38,'工厂删除','factory:delete',NULL,NULL,NULL,54),(39,'工厂编辑','factory:update',NULL,NULL,NULL,54),(40,'工厂列表','factory:view',NULL,NULL,NULL,54),(41,'租用保存','rent:add',NULL,NULL,NULL,55),(42,'租用删除','rent:delete',NULL,NULL,NULL,55),(43,'租用编辑','rent:update',NULL,NULL,NULL,55),(44,'租用列表','rent:view',NULL,NULL,NULL,55),(45,'用户管理','user:view','/user','system/user/user','user',58),(46,'产品类型管理','ptype:view','/ptype','system/ptype/ptype','book',65),(47,'产品信息管理','product:view','/product','system/product/product','barcode',65),(48,'设备类型管理','etype:view','/etype','system/etype/etype','cluster',61),(49,'设备信息管理','equipment:view','/equip','system/equipment/equip','database',61),(50,'订单信息管理','order:view','/order','system/order/order','audit',62),(51,'竞标管理','bid:view','/bid','system/bid/bid','coffee',62),(52,'排产管理','manu:view','/manu','system/manu/manu','container',62),(53,'地址管理','address:view','/address','system/address/address','build',58),(54,'工厂管理','factory:view','/factory','system/factory/factory','bulb',59),(55,'租用管理','rent:view','/rent','system/rent/rent','bank',61),(57,'订单投标','order:bid','/bid','system/bid/bid','compass',62),(58,'系统设置','user:view','','page-view','appstore-o',0),(59,'云工厂','factory:view','','page-view','cloud',0),(60,'产能中心？','manu:view','','page-view',NULL,0),(61,'产能中心','equipment:view','','page-view','compass',0),(62,'订单管理','order:view','','page-view','border',0),(63,'设备产能','equipment:manu',NULL,NULL,NULL,49),(64,'设备租用','equipment:rent',NULL,NULL,NULL,49),(65,'产品管理','product:view','','page-view','form',0),(66,'订单投标','order:bid',NULL,NULL,NULL,50),(67,'订单派送','order:deliver',NULL,NULL,NULL,50),(68,'好友保存','friend:save',NULL,NULL,NULL,74),(69,'好友删除','friend:delete',NULL,NULL,NULL,72),(70,'好友编辑','friend:update',NULL,NULL,NULL,72),(72,'我的好友','friend:view','/friend','system/friend/friend','eye',73),(73,'好友管理','friend:view','','page-view','team',0),(74,'发现好友','friend:views','/find','system/friend/find','usergroup-add',73),(75,'我的信息','user:view','/person','system/person/person','edit',77),(76,'我的通知','user:view','/notify','system/person/notify','notification',77),(77,'个人中心','user:view','','page-view','user',0),(78,'订单统计','order:summary','/summary','system/order/summary','stock',62);
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `factoryId` int(11) NOT NULL,
  `price` float NOT NULL DEFAULT '0',
  `enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '未中标&中标',
  PRIMARY KEY (`id`),
  KEY `bid_FK` (`factoryId`),
  KEY `bid_FK_1` (`orderId`),
  CONSTRAINT `bid_FK` FOREIGN KEY (`factoryId`) REFERENCES `factory` (`id`),
  CONSTRAINT `bid_FK_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='竞标';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (1,1,1,300,1),(3,2,1,200,0),(4,4,1,200,0);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_manu`
--

DROP TABLE IF EXISTS `e_manu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e_manu` (
  `id` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `e_fact_FK_2` (`productId`),
  CONSTRAINT `e_fact_FK` FOREIGN KEY (`id`) REFERENCES `equipment` (`id`),
  CONSTRAINT `e_fact_FK_2` FOREIGN KEY (`productId`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备产能';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_manu`
--

LOCK TABLES `e_manu` WRITE;
/*!40000 ALTER TABLE `e_manu` DISABLE KEYS */;
INSERT INTO `e_manu` VALUES (1,3,100),(6,2,100),(11,4,50),(21,1,1000);
/*!40000 ALTER TABLE `e_manu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_rent`
--

DROP TABLE IF EXISTS `e_rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e_rent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equipmentId` int(11) NOT NULL,
  `factoryId` int(11) NOT NULL,
  `rentDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `rentTime` int(11) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `rent_FK` (`factoryId`),
  KEY `rent_FK_1` (`equipmentId`),
  CONSTRAINT `rent_FK` FOREIGN KEY (`factoryId`) REFERENCES `factory` (`id`),
  CONSTRAINT `rent_FK_1` FOREIGN KEY (`equipmentId`) REFERENCES `equipment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='租用设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_rent`
--

LOCK TABLES `e_rent` WRITE;
/*!40000 ALTER TABLE `e_rent` DISABLE KEYS */;
INSERT INTO `e_rent` VALUES (1,1,1,'2021-07-02 12:36:35',10,1),(2,16,2,'2021-07-02 12:36:35',11,1),(3,21,3,'2021-07-02 12:36:35',12,1),(4,26,4,'2021-07-02 12:37:35',11,1);
/*!40000 ALTER TABLE `e_rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `e_type`
--

DROP TABLE IF EXISTS `e_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `e_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='设备类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `e_type`
--

LOCK TABLES `e_type` WRITE;
/*!40000 ALTER TABLE `e_type` DISABLE KEYS */;
INSERT INTO `e_type` VALUES (1,'高科技设备',NULL),(2,'工业设备',NULL),(3,'农业设备',NULL),(4,'机床',2),(5,'拖拉机',3);
/*!40000 ALTER TABLE `e_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL DEFAULT '空闲',
  `factoryId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `equipment_FK_1` (`factoryId`),
  KEY `equipment_FK` (`typeId`),
  CONSTRAINT `equipment_FK` FOREIGN KEY (`typeId`) REFERENCES `e_type` (`id`),
  CONSTRAINT `equipment_FK_1` FOREIGN KEY (`factoryId`) REFERENCES `factory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='设备';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (1,4,'衣服机床','租用',5,0),(2,3,'洒水机','空闲',3,1),(6,4,'宝石机床','空闲',1,1),(11,3,'拖拉机','空闲',2,1),(16,1,'机械臂','租用',5,1),(21,2,'流水线','租用',5,1),(26,3,'种子机','租用',5,1),(27,3,'设备1','空闲',1,1),(28,1,'垃圾袋','空闲',6,1),(29,2,'ggg','空闲',5,1);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factory`
--

DROP TABLE IF EXISTS `factory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `factory_FK` (`userId`),
  CONSTRAINT `factory_FK` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='工厂';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factory`
--

LOCK TABLES `factory` WRITE;
/*!40000 ALTER TABLE `factory` DISABLE KEYS */;
INSERT INTO `factory` VALUES (1,'工厂1','云工厂1',3,1),(2,'工厂2','云工厂2',4,1),(3,'工厂3','云工厂3',5,1),(4,'工厂4','云工厂4',6,1),(5,'设备中心','可出租',7,1),(6,'123','2321321',9,1);
/*!40000 ALTER TABLE `factory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manu_detail`
--

DROP TABLE IF EXISTS `manu_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manu_detail` (
  `id` int(11) NOT NULL,
  `equipmentId` int(11) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manu_detail_FK` (`equipmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单排产明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manu_detail`
--

LOCK TABLES `manu_detail` WRITE;
/*!40000 ALTER TABLE `manu_detail` DISABLE KEYS */;
INSERT INTO `manu_detail` VALUES (6,21,'2021-07-30 01:04:34','2021-08-26 17:36:33'),(9,2,'2021-07-30 01:04:34','2021-08-26 17:36:33'),(11,2,'2021-07-31 01:20:45','2021-08-26 16:37:47');
/*!40000 ALTER TABLE `manu_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manu_main`
--

DROP TABLE IF EXISTS `manu_main`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manu_main` (
  `detailId` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `factoryId` int(11) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`detailId`),
  KEY `manu_main_FK_1` (`orderId`),
  CONSTRAINT `manu_main_FK` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`id`),
  CONSTRAINT `manu_main_FK_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='订单排产主';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manu_main`
--

LOCK TABLES `manu_main` WRITE;
/*!40000 ALTER TABLE `manu_main` DISABLE KEYS */;
INSERT INTO `manu_main` VALUES (6,1,1,1),(9,2,1,1),(11,4,1,1);
/*!40000 ALTER TABLE `manu_main` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `p_type`
--

DROP TABLE IF EXISTS `p_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `parentId` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='产品类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `p_type`
--

LOCK TABLES `p_type` WRITE;
/*!40000 ALTER TABLE `p_type` DISABLE KEYS */;
INSERT INTO `p_type` VALUES (1,'电子产品',NULL),(2,'手工艺品',NULL),(3,'日用品',NULL),(4,'文具',3);
/*!40000 ALTER TABLE `p_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeId` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_FK` (`typeId`),
  CONSTRAINT `product_FK` FOREIGN KEY (`typeId`) REFERENCES `p_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,4,'笔','书写用具'),(2,2,'香囊','香气馥郁'),(3,3,'肥皂','薄荷香型'),(4,4,'草稿','数学用');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'经销商'),(2,'生产厂家'),(3,'超级管理员'),(4,'物流人员'),(5,'个体商户');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_auth`
--

DROP TABLE IF EXISTS `role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `authId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_auth_FK` (`roleId`),
  KEY `role_auth_FK_1` (`authId`),
  CONSTRAINT `role_auth_FK` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `role_auth_FK_1` FOREIGN KEY (`authId`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_auth`
--

LOCK TABLES `role_auth` WRITE;
/*!40000 ALTER TABLE `role_auth` DISABLE KEYS */;
INSERT INTO `role_auth` VALUES (1,3,1),(2,3,2),(3,3,3),(4,3,4),(5,3,5),(6,3,6),(7,3,7),(8,3,8),(9,3,9),(10,3,10),(11,3,11),(12,3,12),(13,3,13),(14,3,14),(15,3,15),(16,3,16),(17,3,17),(18,3,18),(19,3,19),(20,3,20),(21,3,24),(22,1,21),(23,1,22),(24,1,23),(25,1,24),(26,1,33),(27,1,34),(28,1,35),(29,1,36),(30,2,17),(31,2,18),(32,2,19),(33,2,20),(34,2,25),(35,2,26),(36,2,27),(37,2,28),(39,1,50),(40,1,62),(41,1,53),(42,2,63),(43,2,64),(44,2,57),(45,3,46),(46,3,47),(47,3,48),(48,3,49),(49,3,50),(50,3,54),(51,3,59),(52,3,61),(53,2,49),(55,2,52),(58,2,57),(61,2,61),(64,3,58),(65,1,58),(67,3,45),(68,3,53),(69,3,65),(70,1,32),(71,1,12),(72,2,41),(73,2,42),(74,2,43),(76,2,62),(77,2,16),(78,1,28),(80,3,62),(81,3,28),(82,3,39),(83,3,40),(84,1,66),(85,2,29),(86,2,30),(87,2,31),(88,2,32),(89,5,21),(90,5,22),(91,5,23),(92,5,24),(93,5,33),(94,5,34),(95,5,35),(96,5,36),(97,5,50),(98,5,62),(99,5,53),(100,5,58),(101,5,32),(102,5,12),(103,5,28),(104,5,66),(105,4,67),(106,4,50),(107,4,58),(108,4,62),(109,4,23),(110,4,24),(111,1,68),(112,1,69),(113,1,70),(115,2,68),(116,2,69),(117,2,70),(119,3,68),(120,3,69),(121,3,70),(123,4,68),(124,4,69),(125,4,70),(127,5,68),(128,5,69),(129,5,70),(131,2,58),(132,2,4),(133,1,72),(134,1,73),(135,2,72),(136,2,73),(137,3,72),(138,3,73),(139,4,72),(140,4,73),(141,5,72),(142,5,73),(143,1,74),(144,2,74),(145,3,74),(146,4,74),(147,5,74),(148,1,75),(149,1,76),(150,1,77),(151,2,75),(152,2,76),(153,2,77),(154,3,75),(155,3,76),(156,3,77),(157,4,75),(158,4,76),(159,4,77),(160,5,75),(161,5,76),(162,5,77),(163,1,78),(164,2,78),(165,3,78),(166,4,78),(167,5,78);
/*!40000 ALTER TABLE `role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_friend`
--

DROP TABLE IF EXISTS `t_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `friendId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_friend`
--

LOCK TABLES `t_friend` WRITE;
/*!40000 ALTER TABLE `t_friend` DISABLE KEYS */;
INSERT INTO `t_friend` VALUES (1,1,2),(2,2,1),(5,7,2),(6,2,7),(7,7,3),(8,3,7),(9,7,4),(10,4,7),(11,7,5),(12,5,7),(13,7,6),(14,6,7),(15,7,8),(16,8,7),(17,7,9),(18,9,7),(19,7,10),(20,10,7);
/*!40000 ALTER TABLE `t_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `productId` int(11) NOT NULL COMMENT '产品编号',
  `userId` int(11) NOT NULL COMMENT '用户编号',
  `addressId` int(11) DEFAULT NULL COMMENT '地址编号',
  `quantity` int(11) NOT NULL COMMENT '订购数量',
  `state` varchar(10) NOT NULL COMMENT '订单状态',
  `endDate` datetime DEFAULT NULL COMMENT '订单截止时间',
  `bidDate` datetime DEFAULT NULL COMMENT '竞标截止时间',
  PRIMARY KEY (`id`),
  KEY `order_FK` (`userId`),
  KEY `order_FK_1` (`productId`),
  KEY `order_FK_2` (`addressId`),
  CONSTRAINT `order_FK` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `order_FK_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `order_FK_2` FOREIGN KEY (`addressId`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (1,3,1,8,123,'已收货','2021-07-08 08:56:32','2021-08-20 08:56:32'),(2,2,1,10,121,'已收货','2021-07-24 03:02:59','2021-08-18 06:33:58'),(3,4,1,11,123,'已发布','2021-07-24 03:02:59','2021-08-18 06:33:58'),(4,3,1,12,123,'已收货','2021-07-23 01:20:26','2021-08-05 14:35:26'),(5,2,1,13,222,'未发布','2022-05-27 14:06:51','2022-06-17 14:06:51');
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `realname` varchar(100) NOT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'work1','PNqJILB9eAsU1kGSQObsiw==','商人1','12233345675'),(2,'work2','pBzR2JHiujbe1GGIwc4lrw==','商人2','13322455684'),(3,'mana1','cxB%2B4lrjnLyfeHSboGj9SQ==','管理1','12345656786'),(4,'mana2','H%U83jp/JEFLeyttBrZuSg==','管理2','45612315646'),(5,'mana3','fWXbvYf%RFaKwwUCZ7Hsvw==','管理3','78945612312'),(6,'mana4','Gf0df0EUBKFhBFjKKyzVzw==','管理4','45789645789'),(7,'admin','fJMAPUO9faymPq3mKErq7A==','狗管理','12335345345'),(8,'1234r','oGA1vwrHsnxLMVgI35aNFw==','123','1234567895'),(9,'mana5','q4nKCJApl0Ef4Rf%2B4Kve9A==','mana5y','1234567895'),(10,'dell5','wVxyTk6nKIKKr43N491Irw==','123','123');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_FK` (`userId`),
  KEY `user_role_FK_1` (`roleId`),
  CONSTRAINT `user_role_FK` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`),
  CONSTRAINT `user_role_FK_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,1),(3,3,2),(4,4,2),(5,5,2),(6,6,2),(7,8,1),(8,9,2),(9,7,3),(11,10,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'cloud'
--
/*!50003 DROP PROCEDURE IF EXISTS `get_manu` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_manu`(
in fId int,
out detailId int,
out factoryId int,
out orderId int,
out equipmentId int,
out quantity int,
out startDate timestamp,
out endDate timestamp,
out enabled bool
)
begin
	select mm.detailId,
        mm.factoryId,
        mm.orderId,
        md.equipmentId,
        o.quantity,
        md.startDate,
        md.endDate,
        mm.enabled 
        from manu_main mm
            left join manu_detail md on md.id = mm.detailId
            left join factory f on f.id = mm.factoryId
            left join equipment e on e.id = md.equipmentId
            left join t_order o on mm.orderId = o.id
            left join product p on o.productId = p.id
        where 1 = 1 AND f.id = fId
        group by mm.detailId;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `manage_detail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `manage_detail`(
in detail_id int,
in equipment_id int,
in start_date timestamp,
in end_date timestamp,
out message varchar(100)
)
begin
	declare detail_num int;
	declare s_date timestamp;
	declare e_date timestamp;
	declare equip_num int;
	declare now_date timestamp;
	if start_date < end_date then
	select count(`mn_id`),`start_date`,`end_date` into detail_num,s_date,e_date from `manu_detail` md where md.`mn_id` = detail_id;
	if detail_num = 0 then
		select count(`e_id`) into equip_num from `equipment` e where e.`e_id` = equipment_id and e.`e_state` = '空闲';
		if equip_num = 0 then
			set message = 'failure';
		elseif equip_num = 1 then
			insert into `manu_detail` values(null,equipment_id,start_date,end_date);
			set message = 'success';
		end if;
	elseif detail_num = 1 then
		set now_date = UNIX_TIMESTAMP(CAST(SYSDATE()AS DATE));
		if s_date > now_date then
			update `manu_detail` md set md.`start_date` = start_date and md.`end_date` = end_date where md.`md_id` = detail_id;
			set message = 'success';
		elseif start_date <= now_date then
			if e_date > now_date then
				update `manu_detail` md set md.`end_date` = end_date where md.`md_id` = detail_id;
				set message = 'success';
			else
				set message = 'failure';
			end if;
		end if;
	else
		set message = 'failure';
	end if;
	else
		set message = 'failure';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `regist` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `regist`(
in account varchar(100),
in passwd varchar(100),
in realname varchar(100),
in telephone varchar(100),
in regitype INT,
in facname varchar(100),
in facdes varchar(100),
out message varchar(100)
)
begin
	declare user_id INT;
	if regitype = 1 then
		insert into `user` values (null,account,passwd,realname,telephone);
		select u.`u_id` into user_id from `user` u where u.`account` = account;
		insert into `user_role` values(user_id,regitype);
		set message = 'success';
	elseif regitype = 2 then
		insert into `user` values (null,account,passwd,realname,telephone);
		select `u_id` into user_id from `user` u where u.`account` = account;
		insert into `cloud.factory` values (null,facname,facdes,user_id,null);
		insert into `cloud.user_role` values(user_id,regitype);
		set message = 'success';
	else
		set message = 'failure';
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `rent_equipment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `rent_equipment`(
in equipment_id int,
in start_date timestamp,
in rent_time int,
in factory_id int,
out message varchar(100)
)
begin
	declare state varchar(100);
	select `e_state` into state from `equipment` e where e.`e_id` = equipment_id;
	if state = '空闲' then
		insert into `rent` values(null,equipment_id,factory_id,start_date,rent_time,null);
		update `equipment` e set e.`e_state` = '租用' where e.`e_id` = equipment_id;
		set message = 'success';
	elseif state = '租用' then
		set message = 'failure';
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `select_bid` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `select_bid`(
in bid_id int,
in detail_id int,
out message varchar(100)
)
begin
	declare bid_num int;
	declare order_id int;
	declare factory_id int;
	select count(`b_id`),`order_id`,`factory_id` into bid_num,order_id,factory_id from `bid` b where b.`bid_id` = bid_id;
	if bid_num = 1 then
		insert into `manu_main` values(order_id,factory_id,detail_id);
		set message = 'success';
	else
		set message = 'failure';
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `take_order` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `take_order`(
in order_id int,
in user_id int,
in product_id int,
in order_num int,
in order_date timestamp,
in bid_date timestamp,
in receiver varchar(100),
in address varchar(100),
in telephone varchar(100),
out message varchar(100)
)
begin
	declare address_num int;
	declare address_id int;
	select count(`a_id`) into address_num from `address` a where a.`a_rece` = receiver and a.`a_addr` = address and a.`a_tele` = telephone;
	if address_num = 0 then
		insert into `address` values(null,receiver,address,telephone);
		select `a_id` into address_id from `address` a where a.`a_rece` = receiver and a.`a_addr` = address and a.`a_tele` = telephone;
		insert into `order` values(null,product_id,user_id,address_id,order_num,null,order_date,bid_date);
		set message = 'success';
	elseif address_num > 0 then
		select `a_id` into address_id from `address` a where a.`a_rece` = receiver and a.`a_addr` = address and a.`a_tele` = telephone;
		insert into `order` values(null,product_id,user_id,address_id,order_num,null,order_date,bid_date);
		set message = 'success';
	else
		set message = 'failure';
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_facility` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_facility`(
in equipment_id int,
in product_id int,
in facility int,
out message varchar(100)
)
begin
	declare rent_id varchar(100);
	declare factory_id varchar(100);
	declare fac_num int;
	select `r_id`,`f_id` into rent_id,factory_id from `e_rent` er where er.`e_id` = equipment_id;
	select count(`m_num`) into fac_num from `e_manu` em where em.`e_id` = equipment_id;
	if fac_num > 0 then
		update `e_manu` em set em.`m_num` = facility where em.`e_id`= equipment_id and em.`p_id` = product_id;
		set message = 'success';
	elseif fac_num = 0 then
		insert into `e_manu` values(equipment_id,product_id,facility);
		set message = 'success';
	else
		set message = 'failure';
	end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-16 23:39:44
