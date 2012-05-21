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
INSERT INTO `lm_sequence` VALUES ('lm_visitor','0'),('lm_visitor_addr','0'),('lm_visitor_info','0');
/*!40000 ALTER TABLE `lm_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor`
--

DROP TABLE IF EXISTS `lm_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor` (
  `obj_id` int(10) NOT NULL,
  `id` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `level` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `deposit` decimal(20,0) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `obj_version` decimal(10,0) DEFAULT NULL,
  `default_addr` int(10) DEFAULT NULL,
  `info` int(10) DEFAULT NULL,
  PRIMARY KEY (`obj_id`),
  KEY `FK_lm_visitor_default_addr` (`default_addr`),
  KEY `FK_lm_visitor_info` (`info`),
  CONSTRAINT `FK_lm_visitor_info` FOREIGN KEY (`info`) REFERENCES `lm_visitor_info` (`obj_id`),
  CONSTRAINT `FK_lm_visitor_default_addr` FOREIGN KEY (`default_addr`) REFERENCES `lm_visitor_addr` (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor`
--

LOCK TABLES `lm_visitor` WRITE;
/*!40000 ALTER TABLE `lm_visitor` DISABLE KEYS */;
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
  `phone` varchar(30) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
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
/*!40000 ALTER TABLE `lm_visitor_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lm_visitor_info`
--

DROP TABLE IF EXISTS `lm_visitor_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lm_visitor_info` (
  `obj_id` int(10) NOT NULL,
  `first_child_year` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `born_year` varchar(10) DEFAULT NULL,
  `last_child_year` varchar(10) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `education` varchar(20) DEFAULT NULL,
  `like_area` int(11) DEFAULT NULL,
  `kid_count` int(1) DEFAULT NULL,
  PRIMARY KEY (`obj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lm_visitor_info`
--

LOCK TABLES `lm_visitor_info` WRITE;
/*!40000 ALTER TABLE `lm_visitor_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `lm_visitor_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-20 19:47:41
