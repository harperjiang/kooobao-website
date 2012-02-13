-- MySQL dump 10.13  Distrib 5.1.58, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: web_test
-- ------------------------------------------------------
-- Server version	5.1.58-1ubuntu1

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
-- Table structure for table `fr_actor`
--

DROP TABLE IF EXISTS `fr_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_actor` (
  `id` varchar(15) NOT NULL DEFAULT '',
  `obj_version` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_actor`
--

LOCK TABLES `fr_actor` WRITE;
/*!40000 ALTER TABLE `fr_actor` DISABLE KEYS */;
INSERT INTO `fr_actor` VALUES ('fr_manager','1'),('fr_operato','1');
/*!40000 ALTER TABLE `fr_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_actor_role`
--

DROP TABLE IF EXISTS `fr_actor_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_actor_role` (
  `actor_id` varchar(15) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_actor_role`
--

LOCK TABLES `fr_actor_role` WRITE;
/*!40000 ALTER TABLE `fr_actor_role` DISABLE KEYS */;
INSERT INTO `fr_actor_role` VALUES ('fr_manager','MANAGER'),('fr_operato','OPERATOR'),('fr_manager','TELLER');
/*!40000 ALTER TABLE `fr_actor_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_attachment`
--

DROP TABLE IF EXISTS `fr_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_attachment` (
  `obj_id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_fr_attachment_file_id` (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_attachment`
--

LOCK TABLES `fr_attachment` WRITE;
/*!40000 ALTER TABLE `fr_attachment` DISABLE KEYS */;
INSERT INTO `fr_attachment` VALUES (1,'141-160.xls','2012-02-12 05:23:48',37888,1);
/*!40000 ALTER TABLE `fr_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_customer`
--

DROP TABLE IF EXISTS `fr_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_customer` (
  `obj_id` bigint(20) NOT NULL,
  `customer` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_customer`
--

LOCK TABLES `fr_customer` WRITE;
/*!40000 ALTER TABLE `fr_customer` DISABLE KEYS */;

/*!40000 ALTER TABLE `fr_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_file_storage`
--

DROP TABLE IF EXISTS `fr_file_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_file_storage` (
  `obj_id` bigint(20) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `content_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_file_storage`
--

LOCK TABLES `fr_file_storage` WRITE;
/*!40000 ALTER TABLE `fr_file_storage` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_file_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_record`
--

DROP TABLE IF EXISTS `fr_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_record` (
  `obj_id` bigint(20) NOT NULL,
  `type` varchar(15) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `create_by` varchar(30) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `follow_up` varchar(30) DEFAULT NULL,
  `record_desc` text,
  `record_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `adjust_amount` decimal(10,2) DEFAULT NULL,
  `attachment` bigint(20) DEFAULT NULL,
  `with_name` varchar(20) DEFAULT NULL,
  `with_company` varchar(100) DEFAULT NULL,
  `with_account` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_fr_record_attachment` (`attachment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_record`
--

LOCK TABLES `fr_record` WRITE;
/*!40000 ALTER TABLE `fr_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_record_history`
--

DROP TABLE IF EXISTS `fr_record_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_record_history` (
  `obj_id` bigint(20) NOT NULL,
  `operation` varchar(255) DEFAULT NULL,
  `operate_date` datetime DEFAULT NULL,
  `desc_text` varchar(255) DEFAULT NULL,
  `operator` varchar(255) DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_fr_record_history_record_id` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_record_history`
--

LOCK TABLES `fr_record_history` WRITE;
/*!40000 ALTER TABLE `fr_record_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `fr_record_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fr_sequence`
--

DROP TABLE IF EXISTS `fr_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fr_sequence` (
  `name` varchar(50) NOT NULL,
  `count` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fr_sequence`
--

LOCK TABLES `fr_sequence` WRITE;
/*!40000 ALTER TABLE `fr_sequence` DISABLE KEYS */;
INSERT INTO `fr_sequence` VALUES ('fr_attachment','0'),('fr_record','0'),('fr_record_history','0'),('fr_customer','0'),('fr_file_storage','0');
/*!40000 ALTER TABLE `fr_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-02-12  5:56:14
