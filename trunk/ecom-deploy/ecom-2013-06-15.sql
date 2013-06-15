-- MySQL dump 10.13  Distrib 5.5.31, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ecom
-- ------------------------------------------------------
-- Server version	5.5.31-0ubuntu0.12.04.1

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
-- Table structure for table `crm_customer`
--

DROP TABLE IF EXISTS `crm_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_customer` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `source` varchar(20) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `register_by` varchar(20) DEFAULT NULL,
  `own_by` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `contact_name` varchar(30) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `im` varchar(50) DEFAULT NULL,
  `desc_text` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_customer`
--

LOCK TABLES `crm_customer` WRITE;
/*!40000 ALTER TABLE `crm_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_customer_contact`
--

DROP TABLE IF EXISTS `crm_customer_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_customer_contact` (
  `customer_id` int(10) NOT NULL DEFAULT '0',
  `contact_type` varchar(30) NOT NULL DEFAULT '',
  `contact_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`customer_id`,`contact_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_customer_contact`
--

LOCK TABLES `crm_customer_contact` WRITE;
/*!40000 ALTER TABLE `crm_customer_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_customer_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_customer_followup`
--

DROP TABLE IF EXISTS `crm_customer_followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_customer_followup` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `customer_id` int(10) DEFAULT NULL,
  `comment` varchar(500) DEFAULT NULL,
  `method` varchar(30) DEFAULT NULL,
  `ref_text` varchar(500) DEFAULT NULL,
  `own_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_customer_followup`
--

LOCK TABLES `crm_customer_followup` WRITE;
/*!40000 ALTER TABLE `crm_customer_followup` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_customer_followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_hint`
--

DROP TABLE IF EXISTS `crm_hint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_hint` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `contact_name` varchar(30) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `im` varchar(50) DEFAULT NULL,
  `register_by` varchar(20) DEFAULT NULL,
  `own_by` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `desc_text` varchar(1000) DEFAULT NULL,
  `ref_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_hint`
--

LOCK TABLES `crm_hint` WRITE;
/*!40000 ALTER TABLE `crm_hint` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_hint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_hint_contact`
--

DROP TABLE IF EXISTS `crm_hint_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_hint_contact` (
  `hint_id` int(10) NOT NULL DEFAULT '0',
  `contact_type` varchar(20) NOT NULL DEFAULT '',
  `contact_info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hint_id`,`contact_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_hint_contact`
--

LOCK TABLES `crm_hint_contact` WRITE;
/*!40000 ALTER TABLE `crm_hint_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_hint_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_hint_followup`
--

DROP TABLE IF EXISTS `crm_hint_followup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_hint_followup` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `hint_id` int(10) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `ref_text` varchar(100) DEFAULT NULL,
  `own_by` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_hint_followup`
--

LOCK TABLES `crm_hint_followup` WRITE;
/*!40000 ALTER TABLE `crm_hint_followup` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_hint_followup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_sequence`
--

DROP TABLE IF EXISTS `crm_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_sequence` (
  `name` varchar(30) NOT NULL,
  `count` int(10) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_sequence`
--

LOCK TABLES `crm_sequence` WRITE;
/*!40000 ALTER TABLE `crm_sequence` DISABLE KEYS */;
INSERT INTO `crm_sequence` VALUES ('crm_customer',251),('crm_customer_followup',1),('crm_hint',451),('crm_hint_followup',1),('crm_wordsplit_wtn',1);
/*!40000 ALTER TABLE `crm_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_unique_item`
--

DROP TABLE IF EXISTS `crm_unique_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_unique_item` (
  `uuid` varchar(40) NOT NULL DEFAULT '',
  `category` varchar(20) NOT NULL DEFAULT '',
  `entry_key` varchar(50) NOT NULL DEFAULT '',
  `entry_value` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`uuid`,`category`,`entry_key`,`entry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_unique_item`
--

LOCK TABLES `crm_unique_item` WRITE;
/*!40000 ALTER TABLE `crm_unique_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_unique_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crm_wordsplit_wtn`
--

DROP TABLE IF EXISTS `crm_wordsplit_wtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crm_wordsplit_wtn` (
  `id` int(10) NOT NULL,
  `parent_id` int(10) DEFAULT NULL,
  `can_stop` int(1) DEFAULT NULL,
  `content` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crm_wordsplit_wtn`
--

LOCK TABLES `crm_wordsplit_wtn` WRITE;
/*!40000 ALTER TABLE `crm_wordsplit_wtn` DISABLE KEYS */;
/*!40000 ALTER TABLE `crm_wordsplit_wtn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_auth_category`
--

DROP TABLE IF EXISTS `meta_auth_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_auth_category` (
  `id` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_auth_category`
--

LOCK TABLES `meta_auth_category` WRITE;
/*!40000 ALTER TABLE `meta_auth_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_auth_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_auth_item`
--

DROP TABLE IF EXISTS `meta_auth_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_auth_item` (
  `id` varchar(40) NOT NULL,
  `category_id` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_auth_item`
--

LOCK TABLES `meta_auth_item` WRITE;
/*!40000 ALTER TABLE `meta_auth_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_auth_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_product`
--

DROP TABLE IF EXISTS `meta_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_product` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `desc_text` varchar(500) DEFAULT NULL,
  `weight` decimal(5,3) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_product`
--

LOCK TABLES `meta_product` WRITE;
/*!40000 ALTER TABLE `meta_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_product_set`
--

DROP TABLE IF EXISTS `meta_product_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_product_set` (
  `set_id` int(10) NOT NULL DEFAULT '0',
  `product_id` int(10) NOT NULL DEFAULT '0',
  `product_count` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`set_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_product_set`
--

LOCK TABLES `meta_product_set` WRITE;
/*!40000 ALTER TABLE `meta_product_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_product_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_role`
--

DROP TABLE IF EXISTS `meta_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_role` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_role`
--

LOCK TABLES `meta_role` WRITE;
/*!40000 ALTER TABLE `meta_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_role_auth`
--

DROP TABLE IF EXISTS `meta_role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_role_auth` (
  `role_id` int(10) NOT NULL DEFAULT '0',
  `auth_id` varchar(40) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_role_auth`
--

LOCK TABLES `meta_role_auth` WRITE;
/*!40000 ALTER TABLE `meta_role_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_sequence`
--

DROP TABLE IF EXISTS `meta_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_sequence` (
  `name` varchar(40) NOT NULL,
  `count` int(10) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_sequence`
--

LOCK TABLES `meta_sequence` WRITE;
/*!40000 ALTER TABLE `meta_sequence` DISABLE KEYS */;
INSERT INTO `meta_sequence` VALUES ('meta_product',151),('meta_role',1),('meta_user',1);
/*!40000 ALTER TABLE `meta_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_setting`
--

DROP TABLE IF EXISTS `meta_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_setting` (
  `setting_key` varchar(50) NOT NULL,
  `setting_value` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`setting_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_setting`
--

LOCK TABLES `meta_setting` WRITE;
/*!40000 ALTER TABLE `meta_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meta_user`
--

DROP TABLE IF EXISTS `meta_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meta_user` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  `contact_name` varchar(40) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `im` varchar(50) DEFAULT NULL,
  `enc_pass` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `meta_user_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meta_user`
--

LOCK TABLES `meta_user` WRITE;
/*!40000 ALTER TABLE `meta_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `meta_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_customer`
--

DROP TABLE IF EXISTS `order_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_customer` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `source` varchar(20) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_email` varchar(100) DEFAULT NULL,
  `contact_address` varchar(200) DEFAULT NULL,
  `contact_phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  UNIQUE KEY `order_customer_source` (`source`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_customer`
--

LOCK TABLES `order_customer` WRITE;
/*!40000 ALTER TABLE `order_customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_delivery`
--

DROP TABLE IF EXISTS `order_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_delivery` (
  `obj_id` int(10) NOT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `express_company` varchar(20) DEFAULT NULL,
  `express_no` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `delivery_site` int(10) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_email` varchar(100) DEFAULT NULL,
  `contact_address` varchar(200) DEFAULT NULL,
  `contact_phone` varchar(100) DEFAULT NULL,
  `send_missed` int(1) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_delivery`
--

LOCK TABLES `order_delivery` WRITE;
/*!40000 ALTER TABLE `order_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_delivery_item`
--

DROP TABLE IF EXISTS `order_delivery_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_delivery_item` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `delivery_id` int(10) DEFAULT NULL,
  `order_item_id` int(10) DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  `product_count` int(5) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_delivery_item`
--

LOCK TABLES `order_delivery_item` WRITE;
/*!40000 ALTER TABLE `order_delivery_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_delivery_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_delivery_site`
--

DROP TABLE IF EXISTS `order_delivery_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_delivery_site` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `is_default` int(1) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_delivery_site`
--

LOCK TABLES `order_delivery_site` WRITE;
/*!40000 ALTER TABLE `order_delivery_site` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_delivery_site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_purchase`
--

DROP TABLE IF EXISTS `order_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_purchase` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `obj_version` int(10) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `customer_remark` varchar(100) DEFAULT NULL,
  `csv_remark` varchar(100) DEFAULT NULL,
  `surcharge_name` varchar(100) DEFAULT NULL,
  `surcharge_amount` decimal(10,2) DEFAULT NULL,
  `ref_no` varchar(50) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_email` varchar(100) DEFAULT NULL,
  `contact_address` varchar(200) DEFAULT NULL,
  `contact_phone` varchar(100) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `customer` int(10) DEFAULT NULL,
  `ref_status` varchar(100) DEFAULT NULL,
  `delivery_status` varchar(20) DEFAULT NULL,
  `delivery_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_purchase`
--

LOCK TABLES `order_purchase` WRITE;
/*!40000 ALTER TABLE `order_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_purchase_ditem`
--

DROP TABLE IF EXISTS `order_purchase_ditem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_purchase_ditem` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `product_count` int(5) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `actual_price` decimal(10,2) DEFAULT NULL,
  `purchase_id` int(10) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_purchase_ditem`
--

LOCK TABLES `order_purchase_ditem` WRITE;
/*!40000 ALTER TABLE `order_purchase_ditem` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_purchase_ditem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_purchase_item`
--

DROP TABLE IF EXISTS `order_purchase_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_purchase_item` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  `product_count` int(5) DEFAULT NULL,
  `unit_price` decimal(10,2) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `purchase_id` int(10) DEFAULT NULL,
  `ref_id` varchar(20) DEFAULT NULL,
  `sent_count` int(5) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_purchase_item`
--

LOCK TABLES `order_purchase_item` WRITE;
/*!40000 ALTER TABLE `order_purchase_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_returnreq`
--

DROP TABLE IF EXISTS `order_returnreq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_returnreq` (
  `obj_id` int(10) NOT NULL,
  `obj_version` int(10) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `express_company` varchar(30) DEFAULT NULL,
  `express_no` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_returnreq`
--

LOCK TABLES `order_returnreq` WRITE;
/*!40000 ALTER TABLE `order_returnreq` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_returnreq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_returnreq_item`
--

DROP TABLE IF EXISTS `order_returnreq_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_returnreq_item` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `original_item_id` int(10) DEFAULT NULL,
  `product_id` int(10) DEFAULT NULL,
  `product_count` int(5) DEFAULT NULL,
  `request_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_returnreq_item`
--

LOCK TABLES `order_returnreq_item` WRITE;
/*!40000 ALTER TABLE `order_returnreq_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_returnreq_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_returnreq_log`
--

DROP TABLE IF EXISTS `order_returnreq_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_returnreq_log` (
  `obj_id` int(10) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `request_id` int(10) DEFAULT NULL,
  `operator_id` varchar(30) DEFAULT NULL,
  `from_status` varchar(20) DEFAULT NULL,
  `to_status` varchar(20) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_returnreq_log`
--

LOCK TABLES `order_returnreq_log` WRITE;
/*!40000 ALTER TABLE `order_returnreq_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_returnreq_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_sequence`
--

DROP TABLE IF EXISTS `order_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_sequence` (
  `name` varchar(50) NOT NULL,
  `count` int(10) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_sequence`
--

LOCK TABLES `order_sequence` WRITE;
/*!40000 ALTER TABLE `order_sequence` DISABLE KEYS */;
INSERT INTO `order_sequence` VALUES ('order_customer',50),('order_delivery',50),('order_delivery_item',0),('order_delivery_site',50),('order_product',150),('order_purchase',50),('order_purchase_ditem',0),('order_purchase_item',0),('order_returnreq',50),('order_returnreq_item',0),('order_returnreq_log',0);
/*!40000 ALTER TABLE `order_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-15 18:48:09
