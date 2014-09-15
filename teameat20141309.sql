-- MySQL dump 10.13  Distrib 5.5.23, for Win64 (x86)
--
-- Host: localhost    Database: teameat
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `groupname` varchar(45) NOT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `supervisor` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_sender` varchar(45) DEFAULT NULL,
  `u_receiver` varchar(45) DEFAULT NULL,
  `u_date` date DEFAULT NULL,
  `u_category` varchar(45) DEFAULT NULL,
  `u_name` varchar(45) DEFAULT NULL,
  `u_quantity` double DEFAULT NULL,
  `u_price` varchar(45) DEFAULT NULL,
  `invoice_id` varchar(45) DEFAULT NULL,
  `zakaz_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meat_category`
--

DROP TABLE IF EXISTS `meat_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meat_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meat_category`
--

LOCK TABLES `meat_category` WRITE;
/*!40000 ALTER TABLE `meat_category` DISABLE KEYS */;
INSERT INTO `meat_category` VALUES (1,'говядина'),(2,'конина'),(3,'баранина'),(4,'утка'),(5,'курица'),(6,'гуси'),(7,'рыба'),(8,'прочее');
/*!40000 ALTER TABLE `meat_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meat_types`
--

DROP TABLE IF EXISTS `meat_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meat_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `cMeat_Category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `_idx` (`cMeat_Category`),
  CONSTRAINT `` FOREIGN KEY (`cMeat_Category`) REFERENCES `meat_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meat_types`
--

LOCK TABLES `meat_types` WRITE;
/*!40000 ALTER TABLE `meat_types` DISABLE KEYS */;
INSERT INTO `meat_types` VALUES (1,'бон филе',1),(2,'вырезка',1),(3,'грудинка',1),(4,'гуляш',1),(5,'жир',1),(6,'карбонат',1),(7,'копыто',1),(8,'легкое',1),(9,'ляжка',1),(10,'мякоть',1),(11,'мясо',1),(12,'мясо на кости',1),(13,'печень',1),(14,'ребра',1),(15,'сердце',1),(16,'тибон',1),(17,'туша',1),(18,'фарш',1),(19,'язык',1),(20,'жая',2),(21,'жилик',2),(22,'казы',2),(23,'карта',2),(24,'шек',2),(25,'шеке',2),(26,'шужык',2),(27,'голова',3),(28,'корейка',3),(29,'коробка',3),(30,'ляжка',3),(31,'сбой',3),(32,'утка (дом)',4),(33,'бедро(Россия)',5),(34,'бедро(Усть-Кам)',5),(35,'голень(Россия)',5),(36,'голень(США)',5),(37,'голень(Усть-Кам)',5),(38,'грудка(Ардагер)',5),(39,'крылья(Россия)',5),(40,'крылья(Усть-Кам)',5),(41,'куры(Ардагер)',5),(42,'куры(домашние)',5),(43,'куры(Усть-Кам)',5),(44,'окорочка(США)',5),(45,'табака(Усть-Кам)',5),(46,'фарш(куриный)',5),(47,'филе(Россия)',5),(48,'филе(Усть-Кам)',5),(49,'гуси',6),(50,'индейка',6),(51,'морской язык',7),(52,'селедка',7),(53,'скумбрия копч(с головой)',7),(54,'филе минтая',7);
/*!40000 ALTER TABLE `meat_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receiver`
--

DROP TABLE IF EXISTS `receiver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receiver`
--

LOCK TABLES `receiver` WRITE;
/*!40000 ALTER TABLE `receiver` DISABLE KEYS */;
INSERT INTO `receiver` VALUES (1,'ТОО \"Альтемир-Group\"'),(2,'ТОО \"Lebensraumn\"'),(3,'Прочее');
/*!40000 ALTER TABLE `receiver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `beginTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cUser_idx` (`username`),
  CONSTRAINT `cUser` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `group` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `s_idx` (`group`),
  CONSTRAINT `cGroup` FOREIGN KEY (`group`) REFERENCES `groups` (`groupname`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-13 20:19:26
