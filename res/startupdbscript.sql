-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: DIRT
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `clientNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `company` varchar(50) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `phone1` varchar(11) NOT NULL,
  `phone2` varchar(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`clientNum`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'John','Smith',NULL,NULL,'4035551234',NULL,NULL,NULL,1),(2,'Kevin','Brown','The Keg','cool dude','4035658452','546135679','kevin.brown@edu.sait.ca','2354 Plex Unit# 4',1),(3,'Yukiko','Amagi',NULL,NULL,'465813467',NULL,NULL,'The Amagi Inn',1),(4,'Dana','Longwang','newcompanywhodis',NULL,'2284568452',NULL,NULL,NULL,1),(5,'Mike','Hawk',NULL,NULL,'4038648645','5874586475',NULL,NULL,1),(6,'Sherlock','Holmes','Pet Detective','Elementary','4567895215','6457864359','sassybitch@whotmail.com','221B Baker st',1),(7,'Mike','Hunt',NULL,'Kind of a cunt','4038648645',NULL,NULL,NULL,1),(8,'James','Hetfield','SoundtrackBoyes','GIMME FUEL GIMME FIRE GIMME THAT WHICH I DESIRE OOOOH','5548761248','46537518495','james@metallica.com','Somewhere in colorado',1),(9,'Cameron','Steinburg',NULL,'Fly as hell','4035128991','4035546265','cameronsteinburg@live.ca','105 Aspen Ridge Pl SW',1),(10,'Jane','Jefferson',NULL,'Smells like eggs','123456789','987654321','gamergurrllll@gmail.com','Gutter #276965',1),(11,'Billy','Boyd','Is5companiesenough','No Yarbles','842657952',NULL,'ben@jerrys.com',NULL,1),(12,'Drew','Peacock',NULL,'Guy still owes me 50 bucks','4038648645','5874586475',NULL,NULL,1),(13,'Artful','Dodger',NULL,'Welcome','47642865','548672598','art@dodge.com',NULL,1),(14,'Gerard','Way',NULL,NULL,'461544286','645824654','g@way.com','58264 Rose Hill Dr SE',1),(15,'Alex','DeLarge',NULL,'Viddy Well, My Dear Brothers','4678154389',NULL,'iwascured@alright.com',NULL,1),(16,'Bill','gates',NULL,NULL,'55555555',NULL,NULL,'1234 Super Rich Rd',1),(17,'Oliver','Sykes',NULL,NULL,'64512437659',NULL,'welsh@dropdead.com','666 vegan st',1),(18,'Patrick','Stump',NULL,NULL,'5642568759','5556894258','fedora@fob.com','88 Coopersonte Way NE',1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labourers`
--

DROP TABLE IF EXISTS `labourers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `labourers` (
  `labourerNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(30) DEFAULT NULL,
  `lname` varchar(30) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `phone1` varchar(11) DEFAULT NULL,
  `phone2` varchar(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `emergcontact` varchar(30) DEFAULT NULL,
  `emergcontactphone1` varchar(11) DEFAULT NULL,
  `emergcontactphone2` varchar(11) DEFAULT NULL,
  `sin` varchar(9) DEFAULT NULL,
  `wage` varchar(5) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`labourerNum`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labourers`
--

LOCK TABLES `labourers` WRITE;
/*!40000 ALTER TABLE `labourers` DISABLE KEYS */;
INSERT INTO `labourers` VALUES (1,'Eric','Stillman','FT Labourer','4035687426','4286452588','eric.still@gmail.com','344 Auburn St Unit #69','Phillip DeFranco','403568521','684525655','111222333','18.25',1),(2,'Keifer','Hicks','Contracter','587456852',NULL,NULL,'4242 Riverbend Rd SE','Shaizans Sister','5874456888','4036855477',NULL,NULL,1),(3,'Jeff','Jefferson',' PT Contracter','55526589525','4356526555',NULL,'Homeless Weaboo','Al Gore','5648264955',NULL,NULL,NULL,1),(4,'Sweaty','Taint',NULL,'4035428977',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,'Ryuji','Sakamoto',' PT Punk','55526589525',NULL,NULL,NULL,'Ann Takamaki',NULL,NULL,NULL,NULL,1),(6,'Johnny','Pottsmokr','PT Bitch','5876658942','4035688566','eatassandskatefast@hotmail.com','P.O Box #1433','Ethan Klein','4036754216','5874522588','596795325','15.00',1),(7,'Smoky','McPot','Contracter','4035428977',NULL,'blzitfgt@yahoo.ca','23 Bradberry Ln NW','Hila Klein','4036754216','5874522588',NULL,NULL,1);
/*!40000 ALTER TABLE `labourers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectlabourer`
--

DROP TABLE IF EXISTS `projectlabourer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projectlabourer` (
  `projectNum` smallint(5) unsigned NOT NULL,
  `labourerNum` smallint(5) unsigned NOT NULL,
  KEY `fk_ProjectLabourerProject` (`projectNum`),
  KEY `fk_ProjectLabourerLabourer` (`labourerNum`),
  CONSTRAINT `fk_ProjectLabourerLabourer` FOREIGN KEY (`labourerNum`) REFERENCES `labourers` (`labourerNum`),
  CONSTRAINT `fk_ProjectLabourerProject` FOREIGN KEY (`projectNum`) REFERENCES `projects` (`projectNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectlabourer`
--

LOCK TABLES `projectlabourer` WRITE;
/*!40000 ALTER TABLE `projectlabourer` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectlabourer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `projectNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,
  `clientNum` smallint(5) unsigned NOT NULL,
  `projectName` varchar(50) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `siteAddress` varchar(100) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `estimatedEndDate` date DEFAULT NULL,
  `clientOwing` decimal(8,2) DEFAULT NULL,
  `clientPaid` tinyint(1) DEFAULT NULL,
  `estimatedShoppingCost` decimal(8,2) DEFAULT NULL,
  `estimatedLabourCost` decimal(8,2) DEFAULT NULL,
  `estimatedDeliveryCost` decimal(8,2) DEFAULT NULL,
  `allowanceCost` decimal(8,2) DEFAULT NULL,
  `actualShoppingCost` decimal(8,2) DEFAULT NULL,
  `actualLabourCost` decimal(8,2) DEFAULT NULL,
  `actualDeliveryCost` decimal(8,2) DEFAULT NULL,
  `extraneousExpenses` decimal(8,2) DEFAULT NULL,
  `estimatedProfit` decimal(8,2) DEFAULT NULL,
  `actualProfit` decimal(8,2) DEFAULT NULL,
  `actualEndDate` date DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`projectNum`),
  KEY `fk_ProjectClient` (`clientNum`),
  CONSTRAINT `fk_ProjectClient` FOREIGN KEY (`clientNum`) REFERENCES `clients` (`clientNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceconstants`
--

DROP TABLE IF EXISTS `serviceconstants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviceconstants` (
  `superService` varchar(30) NOT NULL,
  `subService` varchar(200) NOT NULL,
  `constantLow` decimal(5,2) NOT NULL,
  `constantHigh` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceconstants`
--

LOCK TABLES `serviceconstants` WRITE;
/*!40000 ALTER TABLE `serviceconstants` DISABLE KEYS */;
INSERT INTO `serviceconstants` VALUES ('excavation','trucking /2 yards',78.00,NULL),('excavation','disposal',60.00,NULL),('excavation','man hours by hand /yards',3.00,NULL),('excavation','man hours by skid /yards',0.50,NULL),('excavation','labour cost by hand /hours',55.00,NULL),('excavation','labour cost by skid /hours',150.00,NULL),('bed','hours /yards',1.00,NULL),('bed','install /hours',55.00,NULL),('stonewalkway','estimated man hours',4.00,NULL),('stonewalkway','install rate /hours',55.00,NULL),('geotextilewalkway','weed barrier cost /sq. ft',0.15,NULL),('geotextilewalkway','fabric staples qty /1/5 sq. ft',5.00,NULL),('geotextilewalkway','fabric staples cost /staple',0.15,NULL),('geotextilewalkway','fabric man hours /100 sq ft',200.00,NULL),('geotextilewalkway','fabric install rate /hours',55.00,NULL),('walkwaybase','crushed base sq. ft/inch/yard',243.00,NULL),('walkwaybase','crasued cost /yard',75.00,NULL),('walkwaybase','man hours /yard',3.00,NULL),('walkwaybase','install rate / hours',55.00,NULL),('screedsand','depth sq.ft / yard',324.00,NULL),('screedsand','cost /yard',85.00,NULL),('screedsand','man hours / yard',3.00,NULL),('screedsand','install /hours',55.00,NULL),('edgerestraint','cost /8 ft',20.00,NULL),('edgerestraint','nails /1nail',1.00,NULL),('edgerestraint','man hours',50.00,NULL),('edgerestraint','install /hours',55.00,NULL),('jointingsand','QTY (kg/sf) @ 1/4 inch',0.60,NULL),('jointingsand','cost /kg',1.50,NULL),('jointingsand','hours /kg',0.10,NULL),('jointingsand','install /hours',55.00,NULL),('materials','Crushed Rock',53.10,59.00),('materials','Pea Rock',58.50,65.00),('materials','River Rock',58.50,65.00),('materials','Mulch: Western Red Cedar',47.45,52.72),('materials','Top Soil: Premium mix',35.10,39.00),('materials','Crusher Dust',44.21,50.84),('materials','Red Shale',85.00,97.75),('materials','Sod (per 10 s.f.)',3.60,4.80);
/*!40000 ALTER TABLE `serviceconstants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-04 15:50:22
