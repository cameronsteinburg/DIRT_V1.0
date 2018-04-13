package persistence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

/**
 * Initializes JDBC connection to the MySQL database
 *
 */
public class DBAccessor {

    private Connection conn;

    /**
     * default DBAccessor constructor
     */
    public DBAccessor() {
    }

    /**
     * creates MySQL connection and JDBCCommands instance
     * If the program detects that the DIRT database has not yet been created it calls the initializedatabase method and then continues to pass back a conn object as normal
     * 
     * @return true if no errors occur connecting to the database
     */
    public boolean connectToMySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIRT?user=Chuck&password=bLSxc4czdhmJ2BpLxrTY");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InstantiationException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            //Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            conn = initializeDatabase();
            if (conn != null) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * disconnects MySQL connection and JDBCCommands instance
     *
     * @return true if disconnect is successful
     */
    public boolean disconnectToMySQL() {

        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public Connection getConn() {
        return conn;
    }

    /**
     * creates the dirt database and the user that the user of this application will use for mysql
     * @return the created connection to the newly populated database
     */
    public Connection initializeDatabase() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password");
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE DIRT";
            stmt.executeUpdate(sql);
            
            
            //Create the Chuck User and grant permissions
            Statement stmtCreate = conn.createStatement();
            String createChuck = "create user 'Chuck'@'localhost' identified by 'bLSxc4czdhmJ2BpLxrTY'";
            stmtCreate.execute(createChuck);
            
            
            Statement stmtGrant = conn.createStatement();
            String grantChuck = "GRANT ALL ON DIRT.* TO 'Chuck'@'localhost'";
            stmtGrant.execute(grantChuck);

            //Make a directory and file in my documents
            copyStartupScript();

            Runtime rt = Runtime.getRuntime();
            String d1 = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe";
            String d2 = "-uChuck";
            String d3 = "-pbLSxc4czdhmJ2BpLxrTY";
            String d4 = "DIRT";
            String d5 = "<";
            StringBuilder path = new StringBuilder("");
            String myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
            path.append(myDocuments);
            path.append("\\DIRT\\startupdbscript.sql");
//                String d6 = "C:\\Users\\728918\\Documents\\DIRT\\startupdbscript.sql";
            String d6 = path.toString();
            Process proc2 = rt.exec(new String[]{"cmd.exe", "/C", d1, d2, d3, d4, d5, d6});
            int waitforme2 = proc2.waitFor();

//            if (waitforme2 == 0){
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIRT?user=Chuck&password=bLSxc4czdhmJ2BpLxrTY");

            return conn;
//            }

//            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (InstantiationException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (InterruptedException ex) {
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * saves a copy of the default tables and table data based on a previously generated mysql dump script.
     * this information is saved in the user's mydocuments folder so it can be accessed by the program back in initializedatabase
     * @return true if successful
     */
    public boolean copyStartupScript() {

        StringBuilder path = new StringBuilder("");
        String myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        path.append(myDocuments);
        path.append("\\DIRT");
        File dirtDir = new File(path.toString());

        if (!dirtDir.exists()) {
            dirtDir.mkdirs();
        }

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
//	ClassLoader classLoader = getClass().getClassLoader();
//        InputStream in = getClass().getResourceAsStream("/startupdbscript.sql"); 
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	File file = new File("res/startupdbscript.sql");
        try {
            String line = null;
//                do{
//                    line = reader.readLine();
//                    result.append(line).append("\n");
            line = "-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)\n--\n-- Host: localhost    Database: DIRT\n-- ------------------------------------------------------\n-- Server version	5.7.19-log\n\n/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n/*!40101 SET NAMES utf8 */;\n/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;\n/*!40103 SET TIME_ZONE='+00:00' */;\n/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;\n/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\n/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\n/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;\n\n--\n-- Table structure for table `bedworkorder`\n--\n\nDROP TABLE IF EXISTS `bedworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `bedworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estDepth` decimal(8,2) DEFAULT NULL,\n  `estReqYards` decimal(8,2) DEFAULT NULL,\n  `estHours` decimal(8,2) DEFAULT NULL,\n  `estLabour` decimal(8,2) DEFAULT NULL,\n  `aggCost` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actDepth` decimal(8,2) DEFAULT NULL,\n  `actReqYards` decimal(8,2) DEFAULT NULL,\n  `actHours` decimal(8,2) DEFAULT NULL,\n  `actLabour` decimal(8,2) DEFAULT NULL,\n  `aggregate` varchar(50) DEFAULT NULL,\n  KEY `fk_WorkOrderBed` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderBed` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `bedworkorder`\n--\n\nLOCK TABLES `bedworkorder` WRITE;\n/*!40000 ALTER TABLE `bedworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `bedworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `clients`\n--\n\nDROP TABLE IF EXISTS `clients`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `clients` (\n  `clientNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `fname` varchar(50) NOT NULL,\n  `lname` varchar(50) NOT NULL,\n  `company` varchar(50) DEFAULT NULL,\n  `description` varchar(5000) DEFAULT NULL,\n  `phone1` varchar(11) NOT NULL,\n  `phone2` varchar(11) DEFAULT NULL,\n  `email` varchar(30) DEFAULT NULL,\n  `address` varchar(50) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  PRIMARY KEY (`clientNum`)\n) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `clients`\n--\n\nLOCK TABLES `clients` WRITE;\n/*!40000 ALTER TABLE `clients` DISABLE KEYS */;\nINSERT INTO `clients` VALUES (1,'','',NULL,NULL,'0',NULL,NULL,NULL,0),(2,'John','Smith',NULL,NULL,'4035551234',NULL,NULL,NULL,1),(3,'Kevin','Brown','The Keg','cool dude','4035658452','546135679','kevin.brown@edu.sait.ca','2354 Plex Unit# 4',1),(4,'Yukiko','Amagi',NULL,NULL,'465813467',NULL,NULL,'The Amagi Inn',1),(5,'Dana','Longwang','newcompanywhodis',NULL,'2284568452',NULL,NULL,NULL,1),(6,'Mike','Hawk',NULL,NULL,'4038648645','5874586475',NULL,NULL,1),(7,'Sherlock','Holmes','Pet Detective','Elementary','4567895215','6457864359','sassybitch@whotmail.com','221B Baker st',1),(8,'Mike','Hunt',NULL,'Kind of a cunt','4038648645',NULL,NULL,NULL,1),(9,'James','Hetfield','SoundtrackBoyes','GIMME FUEL GIMME FIRE GIMME THAT WHICH I DESIRE OOOOH','5548761248','46537518495','james@metallica.com','Somewhere in colorado',1),(10,'Cameron','Steinburg',NULL,'Fly as hell','4035128991','4035546265','cameronsteinburg@live.ca','105 Aspen Ridge Pl SW',1),(11,'Jane','Jefferson',NULL,'Smells like eggs','123456789','987654321','gamergurrllll@gmail.com','Gutter #276965',1),(12,'Billy','Boyd','Is5companiesenough','No Yarbles','842657952',NULL,'ben@jerrys.com',NULL,1),(13,'Drew','Peacock',NULL,'Guy still owes me 50 bucks','4038648645','5874586475',NULL,NULL,1),(14,'Artful','Dodger',NULL,'Welcome','47642865','548672598','art@dodge.com',NULL,1),(15,'Gerard','Way',NULL,NULL,'461544286','645824654','g@way.com','58264 Rose Hill Dr SE',1),(16,'Alex','DeLarge',NULL,'Viddy Well, My Dear Brothers','4678154389',NULL,'iwascured@alright.com',NULL,1),(17,'Bill','gates',NULL,NULL,'55555555',NULL,NULL,'1234 Super Rich Rd',1),(18,'Oliver','Sykes',NULL,NULL,'64512437659',NULL,'welsh@dropdead.com','666 vegan st',1),(19,'Patrick','Stump',NULL,NULL,'5642568759','5556894258','fedora@fob.com','88 Coopersonte Way NE',1);\n/*!40000 ALTER TABLE `clients` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `customworkorder`\n--\n\nDROP TABLE IF EXISTS `customworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `customworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `multi` decimal(8,2) DEFAULT NULL,\n  `rate` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderCustom` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderCustom` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `customworkorder`\n--\n\nLOCK TABLES `customworkorder` WRITE;\n/*!40000 ALTER TABLE `customworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `customworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `excavationbyhandworkorder`\n--\n\nDROP TABLE IF EXISTS `excavationbyhandworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `excavationbyhandworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estDepth` decimal(8,2) DEFAULT NULL,\n  `estReqYards` decimal(8,2) DEFAULT NULL,\n  `estHours` decimal(8,2) DEFAULT NULL,\n  `estLabour` decimal(8,2) DEFAULT NULL,\n  `estTrucking` decimal(8,2) DEFAULT NULL,\n  `estDisposal` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actDepth` decimal(8,2) DEFAULT NULL,\n  `actReqYards` decimal(8,2) DEFAULT NULL,\n  `actHours` decimal(8,2) DEFAULT NULL,\n  `actLabour` decimal(8,2) DEFAULT NULL,\n  `actTrucking` decimal(8,2) DEFAULT NULL,\n  `actDisposal` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderExcByHand` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderExcByHand` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `excavationbyhandworkorder`\n--\n\nLOCK TABLES `excavationbyhandworkorder` WRITE;\n/*!40000 ALTER TABLE `excavationbyhandworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `excavationbyhandworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `excavationbyskidworkorder`\n--\n\nDROP TABLE IF EXISTS `excavationbyskidworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `excavationbyskidworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estDepth` decimal(8,2) DEFAULT NULL,\n  `estReqYards` decimal(8,2) DEFAULT NULL,\n  `estHours` decimal(8,2) DEFAULT NULL,\n  `estLabour` decimal(8,2) DEFAULT NULL,\n  `estTrucking` decimal(8,2) DEFAULT NULL,\n  `estDisposal` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actDepth` decimal(8,2) DEFAULT NULL,\n  `actReqYards` decimal(8,2) DEFAULT NULL,\n  `actHours` decimal(8,2) DEFAULT NULL,\n  `actLabour` decimal(8,2) DEFAULT NULL,\n  `actTrucking` decimal(8,2) DEFAULT NULL,\n  `actDisposal` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderExcBySkid` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderExcBySkid` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `excavationbyskidworkorder`\n--\n\nLOCK TABLES `excavationbyskidworkorder` WRITE;\n/*!40000 ALTER TABLE `excavationbyskidworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `excavationbyskidworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `irrigationworkorder`\n--\n\nDROP TABLE IF EXISTS `irrigationworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `irrigationworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estThreeQuarterLine` decimal(8,2) DEFAULT NULL,\n  `estHoseBibs` decimal(8,2) DEFAULT NULL,\n  `estOffValves` decimal(8,2) DEFAULT NULL,\n  `estRotaryHeads` decimal(8,2) DEFAULT NULL,\n  `estSprayHaeds` decimal(8,2) DEFAULT NULL,\n  `estDripLine` decimal(8,2) DEFAULT NULL,\n  `estDripEmitter` decimal(8,2) DEFAULT NULL,\n  `estTimerControl` decimal(8,2) DEFAULT NULL,\n  `estControlWire` decimal(8,2) DEFAULT NULL,\n  `estValveBox` decimal(8,2) DEFAULT NULL,\n  `estControlValve` decimal(8,2) DEFAULT NULL,\n  `actThreeQuarterLine` decimal(8,2) DEFAULT NULL,\n  `actHoseBibs` decimal(8,2) DEFAULT NULL,\n  `actOffValves` decimal(8,2) DEFAULT NULL,\n  `actRotaryHeads` decimal(8,2) DEFAULT NULL,\n  `actSprayHaeds` decimal(8,2) DEFAULT NULL,\n  `actDripLine` decimal(8,2) DEFAULT NULL,\n  `actDripEmitter` decimal(8,2) DEFAULT NULL,\n  `actTimerControl` decimal(8,2) DEFAULT NULL,\n  `actControlWire` decimal(8,2) DEFAULT NULL,\n  `actValveBox` decimal(8,2) DEFAULT NULL,\n  `actControlValve` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderIrrigation` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderIrrigation` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `irrigationworkorder`\n--\n\nLOCK TABLES `irrigationworkorder` WRITE;\n/*!40000 ALTER TABLE `irrigationworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `irrigationworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `labourers`\n--\n\nDROP TABLE IF EXISTS `labourers`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `labourers` (\n  `labourerNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `fname` varchar(30) DEFAULT NULL,\n  `lname` varchar(30) DEFAULT NULL,\n  `title` varchar(30) DEFAULT NULL,\n  `phone1` varchar(11) DEFAULT NULL,\n  `phone2` varchar(11) DEFAULT NULL,\n  `email` varchar(30) DEFAULT NULL,\n  `address` varchar(50) DEFAULT NULL,\n  `emergcontact` varchar(30) DEFAULT NULL,\n  `emergcontactphone1` varchar(11) DEFAULT NULL,\n  `emergcontactphone2` varchar(11) DEFAULT NULL,\n  `sin` varchar(9) DEFAULT NULL,\n  `wage` varchar(5) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  PRIMARY KEY (`labourerNum`)\n) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `labourers`\n--\n\nLOCK TABLES `labourers` WRITE;\n/*!40000 ALTER TABLE `labourers` DISABLE KEYS */;\nINSERT INTO `labourers` VALUES (1,'Eric','Stillman','FT Labourer','4035687426','4286452588','eric.still@gmail.com','344 Auburn St Unit #69','Phillip DeFranco','403568521','684525655','111222333','18.25',1),(2,'Keifer','Hicks','Contracter','587456852',NULL,NULL,'4242 Riverbend Rd SE','Shaizans Sister','5874456888','4036855477',NULL,NULL,1),(3,'Jeff','Jefferson',' PT Contracter','55526589525','4356526555',NULL,'Homeless Weaboo','Al Gore','5648264955',NULL,NULL,NULL,1),(4,'Sweaty','Taint',NULL,'4035428977',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,'Ryuji','Sakamoto',' PT Punk','55526589525',NULL,NULL,NULL,'Ann Takamaki',NULL,NULL,NULL,NULL,1),(6,'Johnny','Pottsmokr','PT Bitch','5876658942','4035688566','eatassandskatefast@hotmail.com','P.O Box #1433','Ethan Klein','4036754216','5874522588','596795325','15.00',1),(7,'Smoky','McPot','Contracter','4035428977',NULL,'blzitfgt@yahoo.ca','23 Bradberry Ln NW','Hila Klein','4036754216','5874522588',NULL,NULL,1);\n/*!40000 ALTER TABLE `labourers` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `projectlabourer`\n--\n\nDROP TABLE IF EXISTS `projectlabourer`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `projectlabourer` (\n  `projectNum` smallint(5) unsigned NOT NULL,\n  `labourerNum` smallint(5) unsigned NOT NULL,\n  KEY `fk_ProjectLabourerProject` (`projectNum`),\n  KEY `fk_ProjectLabourerLabourer` (`labourerNum`),\n  CONSTRAINT `fk_ProjectLabourerLabourer` FOREIGN KEY (`labourerNum`) REFERENCES `labourers` (`labourerNum`),\n  CONSTRAINT `fk_ProjectLabourerProject` FOREIGN KEY (`projectNum`) REFERENCES `projects` (`projectNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `projectlabourer`\n--\n\nLOCK TABLES `projectlabourer` WRITE;\n/*!40000 ALTER TABLE `projectlabourer` DISABLE KEYS */;\n/*!40000 ALTER TABLE `projectlabourer` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `projects`\n--\n\nDROP TABLE IF EXISTS `projects`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `projects` (\n  `projectNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `clientNum` smallint(5) unsigned DEFAULT NULL,\n  `projectName` varchar(50) DEFAULT NULL,\n  `description` varchar(5000) DEFAULT NULL,\n  `siteAddress` varchar(100) DEFAULT NULL,\n  `startDate` date DEFAULT NULL,\n  `endDate` date DEFAULT NULL,\n  `clientOwing` decimal(8,2) DEFAULT NULL,\n  `clientPaid` tinyint(1) DEFAULT NULL,\n  `allowanceCost` decimal(8,2) DEFAULT NULL,\n  `extraneousExpenses` decimal(8,2) DEFAULT NULL,\n  `quote` decimal(8,2) DEFAULT NULL,\n  `actualCost` decimal(8,2) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  `completed` tinyint(1) DEFAULT NULL,\n  PRIMARY KEY (`projectNum`),\n  KEY `fk_ProjectClient` (`clientNum`),\n  CONSTRAINT `fk_ProjectClient` FOREIGN KEY (`clientNum`) REFERENCES `clients` (`clientNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `projects`\n--\n\nLOCK TABLES `projects` WRITE;\n/*!40000 ALTER TABLE `projects` DISABLE KEYS */;\n/*!40000 ALTER TABLE `projects` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `retwallworkorder`\n--\n\nDROP TABLE IF EXISTS `retwallworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `retwallworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estLineFT` decimal(8,2) DEFAULT NULL,\n  `estHeight` decimal(8,2) DEFAULT NULL,\n  `estBaseDepth` decimal(8,2) DEFAULT NULL,\n  `estBaseWidth` decimal(8,2) DEFAULT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estBaseReqYards` decimal(8,2) DEFAULT NULL,\n  `estBaseSupply` decimal(8,2) DEFAULT NULL,\n  `estBaseHours` decimal(8,2) DEFAULT NULL,\n  `estBaseLabour` decimal(8,2) DEFAULT NULL,\n  `estBaseRowHours` decimal(8,2) DEFAULT NULL,\n  `estBaseRowLabour` decimal(8,2) DEFAULT NULL,\n  `estBlock` decimal(8,2) DEFAULT NULL,\n  `actLineFT` decimal(8,2) DEFAULT NULL,\n  `actHeight` decimal(8,2) DEFAULT NULL,\n  `actBaseDepth` decimal(8,2) DEFAULT NULL,\n  `actBaseWidth` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actBaseReqYards` decimal(8,2) DEFAULT NULL,\n  `actBaseSupply` decimal(8,2) DEFAULT NULL,\n  `actBaseHours` decimal(8,2) DEFAULT NULL,\n  `actBaseLabour` decimal(8,2) DEFAULT NULL,\n  `actBaseRowHours` decimal(8,2) DEFAULT NULL,\n  `actBaseRowLabour` decimal(8,2) DEFAULT NULL,\n  `actBlock` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderRetWall` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderRetWall` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `retwallworkorder`\n--\n\nLOCK TABLES `retwallworkorder` WRITE;\n/*!40000 ALTER TABLE `retwallworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `retwallworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `serviceconstants`\n--\n\nDROP TABLE IF EXISTS `serviceconstants`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `serviceconstants` (\n  `superService` varchar(30) NOT NULL,\n  `subService` varchar(200) NOT NULL,\n  `constantLow` decimal(5,2) NOT NULL,\n  `constantHigh` decimal(5,2) DEFAULT NULL\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `serviceconstants`\n--\n\nLOCK TABLES `serviceconstants` WRITE;\n/*!40000 ALTER TABLE `serviceconstants` DISABLE KEYS */;\nINSERT INTO `serviceconstants` VALUES ('excavation','trucking by hand /2 yards',78.00,NULL),('excavation','trucking by skid /2 yards',125.00,NULL),('excavation','disposal',113.00,NULL),('excavation','man hours by hand /yards',3.00,NULL),('excavation','man hours by skid /yards',0.50,NULL),('excavation','labour cost by hand /hours',55.00,NULL),('excavation','labour cost by skid /hours',150.00,NULL),('bed','hours /yards',1.00,NULL),('bed','install /hours',55.00,NULL),('stonewalkway','estimated man hours',4.00,NULL),('stonewalkway','install rate /hours',55.00,NULL),('geotextilewalkway','weed barrier cost /sq. ft',0.15,NULL),('geotextilewalkway','fabric staples qty /1/5 sq. ft',5.00,NULL),('geotextilewalkway','fabric staples cost /staple',0.15,NULL),('geotextilewalkway','fabric man hours /100 sq ft',200.00,NULL),('geotextilewalkway','fabric install rate /hours',55.00,NULL),('walkwaybase','crushed base sq. ft/inch/yard',243.00,NULL),('walkwaybase','crasued cost /yard',75.00,NULL),('walkwaybase','man hours /yard',3.00,NULL),('walkwaybase','install rate / hours',55.00,NULL),('screedsand','depth sq.ft / yard',324.00,NULL),('screedsand','cost /yard',85.00,NULL),('screedsand','man hours / yard',3.00,NULL),('screedsand','install /hours',55.00,NULL),('edgerestraint','cost /8 ft',20.00,NULL),('edgerestraint','nails /1nail',1.00,NULL),('edgerestraint','man hours',50.00,NULL),('edgerestraint','install /hours',55.00,NULL),('jointingsand','QTY (kg/sf) @ 1/4 inch',0.60,NULL),('jointingsand','cost /kg',1.50,NULL),('jointingsand','hours /kg',0.10,NULL),('jointingsand','install /hours',55.00,NULL),('materials','Crushed Rock',53.10,59.00),('materials','Pea Rock',58.50,65.00),('materials','River Rock',58.50,65.00),('materials','Mulch: Western Red Cedar',47.45,52.72),('materials','Top Soil: Premium mix',35.10,39.00),('materials','Crusher Dust',44.21,50.84),('materials','Red Shale',85.00,97.75),('materials','Sod (per 10 s.f.)',3.60,4.80),('retainingwall','crushed base cost / yard',45.00,NULL),('retainingwall','crushed base install hours / yard',2.00,NULL),('retainingwall','crushed base install rate / hour',55.00,NULL),('retainingwall','base row install hours /line feet',0.20,NULL),('retainingwall','base row install rate / hour',55.00,NULL),('retainingwall','block cost /line feet',13.37,NULL),('irrigation','3/4 lining',0.60,2.50),('irrigation','hose bibs',12.00,15.00),('irrigation','shut off valve',20.00,20.00),('irrigation','rotary head',42.00,30.00),('irrigation','spray head',20.00,30.00),('irrigation','drip 1/4inch /foot',1.00,1.00),('irrigation','drip emmiter',2.50,1.00),('irrigation','timer control',150.00,110.00),('irrigation','control wire /100 feet',50.00,55.00),('irrigation','valve box',40.00,25.00),('irrigation','control valve',51.00,15.00),('weedbarrier','man hours /500 sqft',1.00,NULL),('weedbarrier','staples /500 sqft',60.00,NULL),('weedbarrier','cost per staples',0.10,NULL),('weedbarrier','barrier supply /500 sqft',80.88,NULL),('weedbarrier','barrier install /500 sqft',55.00,NULL),('sod','supply /yard',0.60,NULL),('sod','man hours /10 sqft',0.12,NULL),('sod','install rate /hours',55.00,NULL),('snowremoval','monthly rate',105.00,NULL),('snowremoval','additional area',26.25,NULL),('tax','GST',0.05,NULL),('tax','PST',0.06,NULL);\n/*!40000 ALTER TABLE `serviceconstants` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `sodworkorder`\n--\n\nDROP TABLE IF EXISTS `sodworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `sodworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estSupplyCost` decimal(8,2) DEFAULT NULL,\n  `estManHours` decimal(8,2) DEFAULT NULL,\n  `estInstallCost` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actSupplyCost` decimal(8,2) DEFAULT NULL,\n  `actManHours` decimal(8,2) DEFAULT NULL,\n  `actInstallCost` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderSod` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderSod` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `sodworkorder`\n--\n\nLOCK TABLES `sodworkorder` WRITE;\n/*!40000 ALTER TABLE `sodworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `sodworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `topsoilworkorder`\n--\n\nDROP TABLE IF EXISTS `topsoilworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `topsoilworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estDepth` decimal(8,2) DEFAULT NULL,\n  `estReqYards` decimal(8,2) DEFAULT NULL,\n  `estSupplyCost` decimal(8,2) DEFAULT NULL,\n  `estManHours` decimal(8,2) DEFAULT NULL,\n  `estInstall` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actDepth` decimal(8,2) DEFAULT NULL,\n  `actReqYards` decimal(8,2) DEFAULT NULL,\n  `actSupplyCost` decimal(8,2) DEFAULT NULL,\n  `actManHours` decimal(8,2) DEFAULT NULL,\n  `actInstall` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderTopSoil` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderTopSoil` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `topsoilworkorder`\n--\n\nLOCK TABLES `topsoilworkorder` WRITE;\n/*!40000 ALTER TABLE `topsoilworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `topsoilworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `weedbarrierworkorder`\n--\n\nDROP TABLE IF EXISTS `weedbarrierworkorder`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `weedbarrierworkorder` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL,\n  `estSQFT` decimal(8,2) DEFAULT NULL,\n  `estLayers` decimal(8,2) DEFAULT NULL,\n  `estReqSQFT` decimal(8,2) DEFAULT NULL,\n  `estHours` decimal(8,2) DEFAULT NULL,\n  `estStaples` decimal(8,2) DEFAULT NULL,\n  `estStaplesSupply` decimal(8,2) DEFAULT NULL,\n  `estBarrierSupply` decimal(8,2) DEFAULT NULL,\n  `estLabour` decimal(8,2) DEFAULT NULL,\n  `actSQFT` decimal(8,2) DEFAULT NULL,\n  `actLayers` decimal(8,2) DEFAULT NULL,\n  `actReqSQFT` decimal(8,2) DEFAULT NULL,\n  `actHours` decimal(8,2) DEFAULT NULL,\n  `actStaples` decimal(8,2) DEFAULT NULL,\n  `actStaplesSupply` decimal(8,2) DEFAULT NULL,\n  `actBarrierSupply` decimal(8,2) DEFAULT NULL,\n  `actLabour` decimal(8,2) DEFAULT NULL,\n  KEY `fk_WorkOrderWeedBarrier` (`workOrderNum`),\n  CONSTRAINT `fk_WorkOrderWeedBarrier` FOREIGN KEY (`workOrderNum`) REFERENCES `workorders` (`workOrderNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `weedbarrierworkorder`\n--\n\nLOCK TABLES `weedbarrierworkorder` WRITE;\n/*!40000 ALTER TABLE `weedbarrierworkorder` DISABLE KEYS */;\n/*!40000 ALTER TABLE `weedbarrierworkorder` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `workorders`\n--\n\nDROP TABLE IF EXISTS `workorders`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `workorders` (\n  `workOrderNum` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,\n  `projectNum` smallint(5) unsigned NOT NULL,\n  `description` varchar(5000) DEFAULT NULL,\n  `quotedTotal` decimal(8,2) DEFAULT NULL,\n  `actualTotal` decimal(8,2) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  `workOrderType` varchar(100) DEFAULT NULL,\n  PRIMARY KEY (`workOrderNum`),\n  KEY `fk_WorkOrderProject` (`projectNum`),\n  CONSTRAINT `fk_WorkOrderProject` FOREIGN KEY (`projectNum`) REFERENCES `projects` (`projectNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `workorders`\n--\n\nLOCK TABLES `workorders` WRITE;\n/*!40000 ALTER TABLE `workorders` DISABLE KEYS */;\n/*!40000 ALTER TABLE `workorders` ENABLE KEYS */;\nUNLOCK TABLES;\n/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;\n\n/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;\n/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;\n/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;\n/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;\n\n-- Dump completed on 2018-04-13 10:31:35\nnull\n";
            result.append(line);
//                      System.out.print(result.toString());
//                } while (line != null);

            path.append("\\startupdbscript.sql");
            File newFile = new File(path.toString());
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(result.toString());
            if (writer != null) {
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
