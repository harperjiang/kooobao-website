-- MySQL dump 10.13  Distrib 5.1.59, for Win32 (ia32)
--
-- Host: localhost    Database: lm
-- ------------------------------------------------------
-- Server version	5.1.59-community

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
-- Table structure for table `lm_article_news`
--

DROP TABLE IF EXISTS `lm_article_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_article_news` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_article_news`
--

LOCK TABLES `lm_article_news` WRITE;
/*!40000 ALTER TABLE `lm_article_news` DISABLE KEYS */;
INSERT INTO `lm_article_news` VALUES (1,NULL,'酷宝图书馆正式开馆','所有图书五折');
/*!40000 ALTER TABLE `lm_article_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book`
--

DROP TABLE IF EXISTS `lm_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book` (
  `obj_id` int(10) NOT NULL,
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `list_price` decimal(10,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `category` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_book_category` (`category`),
  CONSTRAINT `FK_lm_book_category` FOREIGN KEY (`category`) REFERENCES `lm_book_category` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book`
--

LOCK TABLES `lm_book` WRITE;
/*!40000 ALTER TABLE `lm_book` DISABLE KEYS */;
INSERT INTO `lm_book` VALUES (1151,NULL,'2012-06-02 18:41:16','http://img.kooobao.cn/kooobook/do_mice_eat_rice/logo.jpg','10.00','Do Mice Eat Rice?老鼠也吃米',4,8),(1201,NULL,'2012-06-16 20:52:06','http://img.kooobao.cn/kooobook/oliver_who_would_not_sleep/oliver.jpg','20.00','Oliver Who Would Not Sleep',0,8);
/*!40000 ALTER TABLE `lm_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_attr`
--

DROP TABLE IF EXISTS `lm_book_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_attr` (
  `book_id` int(10) NOT NULL,
  `attr_key` varchar(20) NOT NULL DEFAULT '',
  `attr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`book_id`,`attr_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_attr`
--

LOCK TABLES `lm_book_attr` WRITE;
/*!40000 ALTER TABLE `lm_book_attr` DISABLE KEYS */;
INSERT INTO `lm_book_attr` VALUES (1151,'AUTHOR','Al Wight, Roger Clarke'),(1151,'NET_WEIGHT','1.2'),(1151,'PUBLISHER','Tuttling Publish'),(1201,'AUTHOR','Mara Bergman'),(1201,'NET_WEIGHT','1.1'),(1201,'PUBLISHER','Arthur A. Levine Books');
/*!40000 ALTER TABLE `lm_book_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_category`
--

DROP TABLE IF EXISTS `lm_book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_category` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_book_category_parent` (`parent`),
  CONSTRAINT `FK_lm_book_category_parent` FOREIGN KEY (`parent`) REFERENCES `lm_book_category` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_category`
--

LOCK TABLES `lm_book_category` WRITE;
/*!40000 ALTER TABLE `lm_book_category` DISABLE KEYS */;
INSERT INTO `lm_book_category` VALUES (1,NULL,'0-3岁',NULL),(2,NULL,'字母和单词',1),(3,NULL,'触摸书',1),(4,NULL,'纸板书',1),(5,NULL,'翻翻书(Lift the flap)',1),(6,NULL,'绘画书',1),(7,NULL,'中文绘本',1),(8,NULL,'英文绘本',1),(9,NULL,'3-6岁',NULL),(10,NULL,'中文绘本',9),(11,NULL,'英文绘本',9),(12,NULL,'童话故事',9),(13,NULL,'有声读物',9),(14,NULL,'分级读物',9),(15,NULL,'自然科学',9),(16,NULL,'社会交往',9),(17,NULL,'6-12岁',NULL),(18,NULL,'语言',17),(19,NULL,'数学',17),(20,NULL,'科学',17),(21,NULL,'社会',17),(22,NULL,'文学',17),(23,NULL,'故事',17),(24,NULL,'分级读物',17),(25,NULL,'家长必读',NULL),(26,NULL,'家庭教育(Homeschool)',25),(27,NULL,'亲子关系',25),(28,NULL,'儿童生理',25),(29,NULL,'儿童心理',25);
/*!40000 ALTER TABLE `lm_book_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_comment`
--

DROP TABLE IF EXISTS `lm_book_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_comment` (
  `obj_id` int(10) NOT NULL,
  `book_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `visitor_id` varchar(50) DEFAULT NULL,
  `rating` int(1) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_comment`
--

LOCK TABLES `lm_book_comment` WRITE;
/*!40000 ALTER TABLE `lm_book_comment` DISABLE KEYS */;
INSERT INTO `lm_book_comment` VALUES (251,1151,'2012-06-10 23:32:22','harperjiang@msn.com',4,'很好的书，我喜欢');
/*!40000 ALTER TABLE `lm_book_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_content`
--

DROP TABLE IF EXISTS `lm_book_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_content` (
  `book_id` int(10) NOT NULL,
  `content_key` varchar(20) NOT NULL DEFAULT '',
  `content` text,
  PRIMARY KEY (`book_id`,`content_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_content`
--

LOCK TABLES `lm_book_content` WRITE;
/*!40000 ALTER TABLE `lm_book_content` DISABLE KEYS */;
INSERT INTO `lm_book_content` VALUES (1151,'ABSTRACT',''),(1151,'BRIEF','<p>\r\n	<em><span style=\"font-size: 14px;\">&quot;Have you ever seen an <strong>antelope </strong>eat <strong>cantaloupe</strong>? Or a <strong>stallion </strong>eat a <strong>scallion</strong>? Or a <strong>puffin </strong>eat a <strong>muffin</strong>?&quot; </span></em></p>\r\n<p>\r\n	<span style=\"font-size: 14px;\"><strong><span style=\"color: rgb(255, 0, 0);\">Do Mice Eat Rice</span></strong> encourages children to consider what animals might or might not eat, and by extension, what other people in different parts of the world from different cultures eat that is very different from what we do. Children will learn about lots of new foods and animals in a fun and entertaining way.</span></p>\r\n'),(1151,'EDITOR_COMMENT',''),(1151,'OTHER',''),(1151,'TEXT_CONTENT',''),(1201,'ABSTRACT',''),(1201,'BRIEF','<p>\r\n	<span style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\">Oliver Donnington Rimington-Sneep<span class=\"Apple-converted-space\">&nbsp;</span></span><br style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\" />\r\n	<span style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\"><strong>COULDN&#39;T </strong>and <strong>DIDN&#39;T </strong>and <strong>WOULD NOT SLEEP</strong>!<span class=\"Apple-converted-space\">&nbsp;</span></span><br style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\" />\r\n	<br style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\" />\r\n	<span style=\"font: small/normal verdana, arial, helvetica, sans-serif; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; float: none; display: inline !important; white-space: normal; orphans: 2; widows: 2; font-size-adjust: none; font-stretch: normal; background-color: rgb(255, 255, 255); -webkit-text-size-adjust: auto; -webkit-text-stroke-width: 0px;\">And why should he? As soon as his parents shut his door, Oliver&#39;s off on all kinds of explorations: painting and reading, drawing and racing -- and a little trip to Mars for good measure. But his explorations end where all explorations should: safe at home, in a warm bed . . . asleep.<span class=\"Apple-converted-space\">&nbsp;</span></span></p>\r\n'),(1201,'EDITOR_COMMENT',''),(1201,'OTHER',''),(1201,'TEXT_CONTENT','');
/*!40000 ALTER TABLE `lm_book_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_ratesum`
--

DROP TABLE IF EXISTS `lm_book_ratesum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_ratesum` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `book_id` int(10) DEFAULT NULL,
  `rate1` int(10) DEFAULT NULL,
  `rate2` int(10) DEFAULT NULL,
  `rate3` int(10) DEFAULT NULL,
  `rate4` int(10) DEFAULT NULL,
  `rate5` int(10) DEFAULT NULL,
  `merge_mark` int(1) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_ratesum`
--

LOCK TABLES `lm_book_ratesum` WRITE;
/*!40000 ALTER TABLE `lm_book_ratesum` DISABLE KEYS */;
INSERT INTO `lm_book_ratesum` VALUES (1151,'2011-12-15 00:00:00',1151,0,0,0,1,0,0);
/*!40000 ALTER TABLE `lm_book_ratesum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_relation`
--

DROP TABLE IF EXISTS `lm_book_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_relation` (
  `book_from` int(10) NOT NULL DEFAULT '0',
  `book_to` int(10) NOT NULL DEFAULT '0',
  `score` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`book_from`,`book_to`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_relation`
--

LOCK TABLES `lm_book_relation` WRITE;
/*!40000 ALTER TABLE `lm_book_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_book_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_stock`
--

DROP TABLE IF EXISTS `lm_book_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_stock` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `stock_count` int(11) DEFAULT NULL,
  `avail_count` int(11) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `book_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `uk_stock_book_id` (`book_id`),
  CONSTRAINT `FK_lm_book_stock_book_id` FOREIGN KEY (`book_id`) REFERENCES `lm_book` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_stock`
--

LOCK TABLES `lm_book_stock` WRITE;
/*!40000 ALTER TABLE `lm_book_stock` DISABLE KEYS */;
INSERT INTO `lm_book_stock` VALUES (1,'2012-06-05 00:09:03',1,1,'7',1151),(2351,'2012-06-16 20:52:06',1,1,'1',1201);
/*!40000 ALTER TABLE `lm_book_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_book_tag`
--

DROP TABLE IF EXISTS `lm_book_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_book_tag` (
  `book_id` int(10) NOT NULL,
  `tag` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_book_tag`
--

LOCK TABLES `lm_book_tag` WRITE;
/*!40000 ALTER TABLE `lm_book_tag` DISABLE KEYS */;
INSERT INTO `lm_book_tag` VALUES (1,'AAA'),(1,'CCC'),(2,'AAA'),(2,'BBB'),(1,'AAA'),(1,'CCC'),(2,'AAA'),(2,'BBB');
/*!40000 ALTER TABLE `lm_book_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_operator`
--

DROP TABLE IF EXISTS `lm_operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_operator` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `id` varchar(30) NOT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `lm_operator_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_operator`
--

LOCK TABLES `lm_operator` WRITE;
/*!40000 ALTER TABLE `lm_operator` DISABLE KEYS */;
INSERT INTO `lm_operator` VALUES (1,'2012-06-04 23:42:06','harper');
/*!40000 ALTER TABLE `lm_operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_optlog_borrowcount`
--

DROP TABLE IF EXISTS `lm_optlog_borrowcount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_optlog_borrowcount` (
  `borrow_count` int(11) DEFAULT NULL,
  `book_id` int(10) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  CONSTRAINT `FK_lm_optlog_borrowcount_book_id` FOREIGN KEY (`book_id`) REFERENCES `lm_book` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_optlog_borrowcount`
--

LOCK TABLES `lm_optlog_borrowcount` WRITE;
/*!40000 ALTER TABLE `lm_optlog_borrowcount` DISABLE KEYS */;
INSERT INTO `lm_optlog_borrowcount` VALUES (0,1151,'2012-06-02 23:36:08'),(0,1201,'2012-06-16 20:52:06');
/*!40000 ALTER TABLE `lm_optlog_borrowcount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_optlog_search`
--

DROP TABLE IF EXISTS `lm_optlog_search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_optlog_search` (
  `obj_id` int(10) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `keyword` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2252 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_optlog_search`
--

LOCK TABLES `lm_optlog_search` WRITE;
/*!40000 ALTER TABLE `lm_optlog_search` DISABLE KEYS */;
INSERT INTO `lm_optlog_search` VALUES (1351,NULL,'mice'),(1401,NULL,'mice'),(1451,NULL,'mice'),(1501,NULL,'mice'),(1502,NULL,'mice'),(1551,NULL,'mice'),(1601,NULL,'mice'),(1602,NULL,'mice'),(1651,NULL,'mice'),(1701,NULL,'mice'),(1702,NULL,'mice'),(1703,NULL,'mice'),(1704,NULL,'mice'),(1751,NULL,'mice'),(1801,NULL,'mice'),(1802,NULL,'mice'),(1803,NULL,'mice'),(1851,NULL,'mice'),(1852,NULL,'mice'),(1901,NULL,'mice'),(1951,NULL,'mice'),(2001,NULL,'good'),(2002,NULL,'mice'),(2051,NULL,'</div>'),(2052,NULL,'a\');drop table lm_optlog;'),(2101,NULL,'dwfd'),(2151,NULL,'safdsadfdsa'),(2201,NULL,'mice'),(2202,NULL,'mice'),(2203,NULL,'mice'),(2204,NULL,'mice'),(2251,NULL,'mice');
/*!40000 ALTER TABLE `lm_optlog_search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_optlog_searchsum`
--

DROP TABLE IF EXISTS `lm_optlog_searchsum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_optlog_searchsum` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `keyword` varchar(50) DEFAULT NULL,
  `search_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_optlog_searchsum`
--

LOCK TABLES `lm_optlog_searchsum` WRITE;
/*!40000 ALTER TABLE `lm_optlog_searchsum` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_optlog_searchsum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_purchase`
--

DROP TABLE IF EXISTS `lm_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_purchase` (
  `obj_id` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `visitor_id` int(10) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `delivery_method` varchar(20) DEFAULT NULL,
  `total_price` decimal(10,2) DEFAULT NULL,
  `net_weight` decimal(10,2) DEFAULT NULL,
  `discount` decimal(10,2) DEFAULT NULL,
  `delivery_fee` decimal(10,2) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `addr_name` varchar(50) DEFAULT NULL,
  `addr_loc` varchar(500) DEFAULT NULL,
  `addr_phone` varchar(100) DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_purchase`
--

LOCK TABLES `lm_purchase` WRITE;
/*!40000 ALTER TABLE `lm_purchase` DISABLE KEYS */;
INSERT INTO `lm_purchase` VALUES (2,'2012-06-17 23:29:39',2301,'SUBMIT','SELF_PICK','300.00','300.00','0.00','0.00','这是一段留言','李小三','上海市浦东新区浦建路1284弄','1231231234',1);
/*!40000 ALTER TABLE `lm_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_purchase_item`
--

DROP TABLE IF EXISTS `lm_purchase_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_purchase_item` (
  `obj_id` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `header` int(10) DEFAULT NULL,
  `book_id` int(10) DEFAULT NULL,
  `book_count` int(5) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_purchase_item`
--

LOCK TABLES `lm_purchase_item` WRITE;
/*!40000 ALTER TABLE `lm_purchase_item` DISABLE KEYS */;
INSERT INTO `lm_purchase_item` VALUES (2,NULL,2,1151,10),(3,NULL,2,1201,10);
/*!40000 ALTER TABLE `lm_purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_purchase_log`
--

DROP TABLE IF EXISTS `lm_purchase_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_purchase_log` (
  `obj_id` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `operator_id` varchar(30) DEFAULT NULL,
  `from_state` varchar(20) DEFAULT NULL,
  `to_state` varchar(20) DEFAULT NULL,
  `desc_text` varchar(500) DEFAULT NULL,
  `header` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_purchase_log`
--

LOCK TABLES `lm_purchase_log` WRITE;
/*!40000 ALTER TABLE `lm_purchase_log` DISABLE KEYS */;
INSERT INTO `lm_purchase_log` VALUES (2,'2012-06-17 23:29:39',NULL,NULL,'SUBMIT','您的订单已经提交。null',2);
/*!40000 ALTER TABLE `lm_purchase_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_sequence`
--

DROP TABLE IF EXISTS `lm_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_sequence` (
  `name` varchar(50) NOT NULL,
  `count` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_sequence`
--

LOCK TABLES `lm_sequence` WRITE;
/*!40000 ALTER TABLE `lm_sequence` DISABLE KEYS */;
INSERT INTO `lm_sequence` VALUES ('lm_address','0'),('lm_article_news','50'),('lm_book','1250'),('lm_book_category','200'),('lm_book_comment','300'),('lm_book_stock','2400'),('lm_optlog_search','2300'),('lm_optlog_searchsum','1650'),('lm_purchase','51'),('lm_purchase_item','51'),('lm_purchase_log','51'),('lm_tran','1950'),('lm_tran_comment','150'),('lm_tran_expire_rec','1150'),('lm_tran_fav_rec','950'),('lm_tran_opt','700'),('lm_visitor','2350'),('lm_visitor_addr','300'),('lm_visitor_balancelog','201'),('lm_visitor_info','50'),('lm_visitor_instinfo','1');
/*!40000 ALTER TABLE `lm_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran`
--

DROP TABLE IF EXISTS `lm_tran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran` (
  `obj_id` int(10) NOT NULL,
  `due_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `delivery_mthd` varchar(10) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `book_id` int(10) DEFAULT NULL,
  `visitor` int(10) DEFAULT NULL,
  `addr_phone` varchar(20) DEFAULT NULL,
  `addr_loc` varchar(200) DEFAULT NULL,
  `addr_name` varchar(10) DEFAULT NULL,
  `delivery_fee` decimal(10,2) DEFAULT NULL,
  `flag` int(4) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `rating` int(1) DEFAULT '0',
  `return_time` datetime DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_tran_visitor` (`visitor`),
  KEY `FK_lm_tran_book_id` (`book_id`),
  CONSTRAINT `FK_lm_tran_book_id` FOREIGN KEY (`book_id`) REFERENCES `lm_book` (`obj_id`),
  CONSTRAINT `FK_lm_tran_visitor` FOREIGN KEY (`visitor`) REFERENCES `lm_visitor` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran`
--

LOCK TABLES `lm_tran` WRITE;
/*!40000 ALTER TABLE `lm_tran` DISABLE KEYS */;
INSERT INTO `lm_tran` VALUES (1701,'2012-06-24 14:20:05','2012-06-04 16:21:36','RETURN_RECEIVED','SELF_PICK','8',1151,2101,'1233223342332323','上海市浦东新区浦建路12384弄233号','张三',NULL,2,'好书，真是好书，我喜欢死了',5,'2012-06-08 22:58:11'),(1801,'2012-06-29 08:54:05','2012-06-09 08:54:05','CANCELLED','SELF_PICK','2',1151,2101,'1233223342332323','上海市浦东新区浦建路12384弄233号','张三',NULL,0,NULL,0,NULL),(1851,'2012-06-29 09:48:56','2012-06-09 09:48:56','RETURN_RECEIVED','SELF_PICK','8',1151,2101,'1233223342332323','上海市浦东新区浦建路12384弄233号','张三',NULL,2,'很好的书，我喜欢',4,'2012-06-10 23:26:32'),(1901,'2012-07-03 00:54:33','2012-06-13 00:54:33','CANCELLED','SELF_PICK','4',1151,2101,'1233223342332323','上海市浦东新区浦建路12384弄233号','张三',NULL,2,NULL,0,NULL);
/*!40000 ALTER TABLE `lm_tran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran_comment`
--

DROP TABLE IF EXISTS `lm_tran_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran_comment` (
  `obj_id` int(10) NOT NULL DEFAULT '0',
  `tran_id` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `content` text,
  `operator_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran_comment`
--

LOCK TABLES `lm_tran_comment` WRITE;
/*!40000 ALTER TABLE `lm_tran_comment` DISABLE KEYS */;
INSERT INTO `lm_tran_comment` VALUES (1,1801,'2012-06-09 08:54:05','这是买家留言',NULL),(51,1851,'2012-06-09 09:48:56','这是用户留言',NULL),(101,1901,'2012-06-13 00:54:33','',NULL);
/*!40000 ALTER TABLE `lm_tran_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran_expire_rec`
--

DROP TABLE IF EXISTS `lm_tran_expire_rec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran_expire_rec` (
  `obj_id` int(10) NOT NULL,
  `due_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `penalty` decimal(38,0) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `transaction` int(10) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `uk_expire_record_tran` (`transaction`),
  CONSTRAINT `FK_lm_tran_expire_rec_transaction` FOREIGN KEY (`transaction`) REFERENCES `lm_tran` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran_expire_rec`
--

LOCK TABLES `lm_tran_expire_rec` WRITE;
/*!40000 ALTER TABLE `lm_tran_expire_rec` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_tran_expire_rec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran_fav_rec`
--

DROP TABLE IF EXISTS `lm_tran_fav_rec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran_fav_rec` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `fav_book` int(10) DEFAULT NULL,
  `visitor` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_tran_fav_rec_visitor` (`visitor`),
  KEY `FK_lm_tran_fav_rec_fav_book` (`fav_book`),
  CONSTRAINT `FK_lm_tran_fav_rec_fav_book` FOREIGN KEY (`fav_book`) REFERENCES `lm_book` (`obj_id`),
  CONSTRAINT `FK_lm_tran_fav_rec_visitor` FOREIGN KEY (`visitor`) REFERENCES `lm_visitor` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran_fav_rec`
--

LOCK TABLES `lm_tran_fav_rec` WRITE;
/*!40000 ALTER TABLE `lm_tran_fav_rec` DISABLE KEYS */;
INSERT INTO `lm_tran_fav_rec` VALUES (901,'2012-06-04 16:58:10','1',1151,2101);
/*!40000 ALTER TABLE `lm_tran_fav_rec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran_lm_tran_opt`
--

DROP TABLE IF EXISTS `lm_tran_lm_tran_opt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran_lm_tran_opt` (
  `Transaction_obj_id` int(10) NOT NULL,
  `operations_obj_id` int(10) NOT NULL,
  PRIMARY KEY (`Transaction_obj_id`,`operations_obj_id`),
  KEY `FK_lm_tran_lm_tran_opt_operations_obj_id` (`operations_obj_id`),
  CONSTRAINT `FK_lm_tran_lm_tran_opt_operations_obj_id` FOREIGN KEY (`operations_obj_id`) REFERENCES `lm_tran_opt` (`obj_id`),
  CONSTRAINT `FK_lm_tran_lm_tran_opt_Transaction_obj_id` FOREIGN KEY (`Transaction_obj_id`) REFERENCES `lm_tran` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran_lm_tran_opt`
--

LOCK TABLES `lm_tran_lm_tran_opt` WRITE;
/*!40000 ALTER TABLE `lm_tran_lm_tran_opt` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_tran_lm_tran_opt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_tran_opt`
--

DROP TABLE IF EXISTS `lm_tran_opt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_tran_opt` (
  `obj_id` int(10) NOT NULL,
  `from_state` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `to_state` varchar(20) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `desc_text` varchar(200) DEFAULT NULL,
  `tran_id` int(10) DEFAULT NULL,
  `operator_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_tran_opt`
--

LOCK TABLES `lm_tran_opt` WRITE;
/*!40000 ALTER TABLE `lm_tran_opt` DISABLE KEYS */;
INSERT INTO `lm_tran_opt` VALUES (1,NULL,'2012-06-04 14:20:05','BORROW_REQUESTED',NULL,'您的请求已经提交，正在处理中',1701,NULL),(51,'BORROW_REQUESTED','2012-06-05 00:17:37','BORROW_APPROVED',NULL,'您的订单已经通过审核，正在出库中',1701,'harper'),(101,'BORROW_APPROVED','2012-06-08 21:36:47','BORROW_SENT','顺丰12344221','您的订单已经出库,跟踪编号 顺丰12344221',1701,'harper'),(151,'BORROW_SENT','2012-06-08 22:14:05','RETURN_WAIT',NULL,'您的订单已经签收',1701,NULL),(201,'RETURN_WAIT','2012-06-08 22:21:42','RETURN_SENT','顺丰12345678','您已经登记了归还书籍 顺丰12345678',1701,'harper'),(251,'RETURN_RECEIVED','2012-06-08 22:28:09','RETURN_RECEIVED','','您归还的书籍已经收到 ',1701,'harper'),(351,NULL,'2012-06-09 08:54:05','BORROW_REQUESTED',NULL,'您的请求已经提交，正在处理中',1801,NULL),(401,'BORROW_REQUESTED','2012-06-09 09:26:39','CANCELLED','我不想要了:随便说点什么原因','您已经选择取消订单，原因:我不想要了:随便说点什么原因',1801,NULL),(451,NULL,'2012-06-09 09:48:56','BORROW_REQUESTED',NULL,'您的请求已经提交，正在处理中',1851,NULL),(501,'BORROW_REQUESTED','2012-06-09 15:08:32','BORROW_APPROVED',NULL,'您的订单已经通过审核，正在出库中',1851,'harper'),(551,'BORROW_APPROVED','2012-06-10 20:01:55','BORROW_SENT','顺丰524423324','您的订单已经出库,跟踪编号 顺丰524423324',1851,'harper'),(552,'BORROW_SENT','2012-06-10 20:02:51','RETURN_WAIT',NULL,'您的订单已经签收',1851,NULL),(601,'RETURN_WAIT','2012-06-10 23:17:07','RETURN_SENT','顺丰r33433523','您已经登记了归还书籍 顺丰r33433523',1851,NULL),(602,'RETURN_RECEIVED','2012-06-10 23:26:32','RETURN_RECEIVED',NULL,'您归还的书籍已经收到 ',1851,'harper'),(651,NULL,'2012-06-13 00:54:33','BORROW_REQUESTED',NULL,'您的请求已经提交，正在处理中',1901,NULL),(652,'BORROW_REQUESTED','2012-06-13 00:57:09','BORROW_APPROVED',NULL,'您的订单已经通过审核，正在出库中',1901,'harper'),(653,'BORROW_APPROVED','2012-06-13 00:57:30','CANCELLED','我不想要了:','您已经选择取消订单，原因:我不想要了:',1901,NULL);
/*!40000 ALTER TABLE `lm_tran_opt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor`
--

DROP TABLE IF EXISTS `lm_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor` (
  `obj_id` int(10) NOT NULL,
  `id` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `deposit` decimal(10,2) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `default_addr` int(10) DEFAULT NULL,
  `info` int(10) DEFAULT NULL,
  `visitor_type` varchar(20) DEFAULT NULL,
  `recommend_by` varchar(30) DEFAULT NULL,
  `inst_info` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `lm_visitor_id` (`id`),
  KEY `FK_lm_visitor_default_addr` (`default_addr`),
  KEY `FK_lm_visitor_info` (`info`),
  CONSTRAINT `FK_lm_visitor_default_addr` FOREIGN KEY (`default_addr`) REFERENCES `lm_visitor_addr` (`obj_id`),
  CONSTRAINT `FK_lm_visitor_info` FOREIGN KEY (`info`) REFERENCES `lm_visitor_info` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor`
--

LOCK TABLES `lm_visitor` WRITE;
/*!40000 ALTER TABLE `lm_visitor` DISABLE KEYS */;
INSERT INTO `lm_visitor` VALUES (2101,'harperjiang@msn.com','2012-05-31 02:37:06',3,'ACTIVE','135.00',NULL,'10',51,1,'PERSON',NULL,NULL),(2301,'harperjiang@gmail.com','2012-06-14 01:26:03',1,'ACTIVE','20.00',NULL,'5',251,NULL,'INSTITUTE','harperjiang@msn.com',2301);
/*!40000 ALTER TABLE `lm_visitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_actrecord`
--

DROP TABLE IF EXISTS `lm_visitor_actrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_actrecord` (
  `visitor_id` varchar(100) NOT NULL,
  `activation_id` varchar(100) NOT NULL,
  PRIMARY KEY (`visitor_id`,`activation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_actrecord`
--

LOCK TABLES `lm_visitor_actrecord` WRITE;
/*!40000 ALTER TABLE `lm_visitor_actrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_visitor_actrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_addr`
--

DROP TABLE IF EXISTS `lm_visitor_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_addr` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `visitor` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_visitor_addr_visitor` (`visitor`),
  CONSTRAINT `FK_lm_visitor_addr_visitor` FOREIGN KEY (`visitor`) REFERENCES `lm_visitor` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_addr`
--

LOCK TABLES `lm_visitor_addr` WRITE;
/*!40000 ALTER TABLE `lm_visitor_addr` DISABLE KEYS */;
INSERT INTO `lm_visitor_addr` VALUES (51,NULL,'1233223342332323','上海市浦东新区浦建路12384弄233号','张三',2101),(251,NULL,'1231231234','上海市浦东新区浦建路1284弄','李小三',2301);
/*!40000 ALTER TABLE `lm_visitor_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_balancelog`
--

DROP TABLE IF EXISTS `lm_visitor_balancelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_balancelog` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `operator_id` varchar(30) DEFAULT NULL,
  `visitor_id` int(10) DEFAULT NULL,
  `reason` varchar(150) DEFAULT NULL,
  `bal_change` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_balancelog`
--

LOCK TABLES `lm_visitor_balancelog` WRITE;
/*!40000 ALTER TABLE `lm_visitor_balancelog` DISABLE KEYS */;
INSERT INTO `lm_visitor_balancelog` VALUES (2,'2012-06-09 15:07:08','harper',2101,'测试','15.00'),(52,'2012-06-09 15:08:32','harper',2101,'Borrow Book','-10.00'),(102,'2012-06-13 00:54:50','harper',2101,'','0.00'),(103,'2012-06-13 00:54:59','harper',2101,'','120.00'),(104,'2012-06-13 00:57:09','harper',2101,'Borrow Book 1901','-10.00'),(152,'2012-06-14 01:26:05',NULL,2301,'Registration','20.00'),(153,'2012-06-14 01:31:33',NULL,2101,'Recommend Reward:harperjiang@gmail.com','20.00');
/*!40000 ALTER TABLE `lm_visitor_balancelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_info`
--

DROP TABLE IF EXISTS `lm_visitor_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_info` (
  `obj_id` int(10) NOT NULL,
  `first_child_year` varchar(5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `born_year` varchar(5) DEFAULT NULL,
  `last_child_year` varchar(5) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `like_area` int(11) DEFAULT NULL,
  `kid_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_info`
--

LOCK TABLES `lm_visitor_info` WRITE;
/*!40000 ALTER TABLE `lm_visitor_info` DISABLE KEYS */;
INSERT INTO `lm_visitor_info` VALUES (1,'1994',NULL,'1969','2001','F','1',7,1);
/*!40000 ALTER TABLE `lm_visitor_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_instinfo`
--

DROP TABLE IF EXISTS `lm_visitor_instinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_instinfo` (
  `obj_id` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `phone` varchar(120) DEFAULT NULL,
  `desc_text` varchar(500) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_instinfo`
--

LOCK TABLES `lm_visitor_instinfo` WRITE;
/*!40000 ALTER TABLE `lm_visitor_instinfo` DISABLE KEYS */;
INSERT INTO `lm_visitor_instinfo` VALUES (2301,NULL,'AAA','BBB','CCC','DDDDFS','FFFFFFFFFFFFFFFFFSDFDSFDSFDSFDSFfasfasdfasdfdsafdasfasd','EEE');
/*!40000 ALTER TABLE `lm_visitor_instinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-30  9:17:20
