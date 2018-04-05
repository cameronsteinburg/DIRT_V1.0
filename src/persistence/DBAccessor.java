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
     *
     * @return true if no errors occur connecting to the database
     */
    public boolean connectToMySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIRT?user=root&password=password");
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
            Logger.getLogger(DBAccessor.class.getName()).log(Level.SEVERE, null, ex);
            conn = initializeDatabase();
            if (conn != null) return true;
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

    public Connection initializeDatabase(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "password");
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE DIRT";
            stmt.executeUpdate(sql);
            
            //Make a directory and file in my documents
            copyStartupScript();
            
            Runtime rt = Runtime.getRuntime();
                String d1 = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe";
                String d2 = "-uroot";
                String d3 = "-ppassword";
                String d4 = "DIRT";
                String d5 = "<";
                StringBuilder path = new StringBuilder("");
                String myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
                path.append(myDocuments);
                path.append("\\DIRT\\startupdbscript.sql");
//                String d6 = "C:\\Users\\728918\\Documents\\DIRT\\startupdbscript.sql";
                String d6 = path.toString();
                Process proc2 = rt.exec(new String[]{"cmd.exe","/C",d1,d2,d3,d4,d5,d6});
                int waitforme2 = proc2.waitFor();
            
//            if (waitforme2 == 0){
                
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DIRT?user=root&password=password");
                
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
    
    public boolean copyStartupScript(){
        
        StringBuilder path = new StringBuilder("");
        String myDocuments = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        path.append(myDocuments);
        path.append("\\DIRT");
        File dirtDir = new File(path.toString());
        
        if (!dirtDir.exists())
        {
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
//                    result.append(line).append("\\n");
                      line = "-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)\n--\n-- Host: localhost    Database: DIRT\n-- ------------------------------------------------------\n-- Server version	5.7.19-log\n\n/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n/*!40101 SET NAMES utf8 */;\n/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;\n/*!40103 SET TIME_ZONE='+00:00' */;\n/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;\n/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\n/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\n/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;\n\n--\n-- Table structure for table `clients`\n--\n\nDROP TABLE IF EXISTS `clients`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `clients` (\n  `clientNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `fname` varchar(50) NOT NULL,\n  `lname` varchar(50) NOT NULL,\n  `company` varchar(50) DEFAULT NULL,\n  `description` varchar(5000) DEFAULT NULL,\n  `phone1` varchar(11) NOT NULL,\n  `phone2` varchar(11) DEFAULT NULL,\n  `email` varchar(30) DEFAULT NULL,\n  `address` varchar(50) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  PRIMARY KEY (`clientNum`)\n) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `clients`\n--\n\nLOCK TABLES `clients` WRITE;\n/*!40000 ALTER TABLE `clients` DISABLE KEYS */;\nINSERT INTO `clients` VALUES (1,'John','Smith',NULL,NULL,'4035551234',NULL,NULL,NULL,1),(2,'Kevin','Brown','The Keg','cool dude','4035658452','546135679','kevin.brown@edu.sait.ca','2354 Plex Unit# 4',1),(3,'Yukiko','Amagi',NULL,NULL,'465813467',NULL,NULL,'The Amagi Inn',1),(4,'Dana','Longwang','newcompanywhodis',NULL,'2284568452',NULL,NULL,NULL,1),(5,'Mike','Hawk',NULL,NULL,'4038648645','5874586475',NULL,NULL,1),(6,'Sherlock','Holmes','Pet Detective','Elementary','4567895215','6457864359','sassybitch@whotmail.com','221B Baker st',1),(7,'Mike','Hunt',NULL,'Kind of a cunt','4038648645',NULL,NULL,NULL,1),(8,'James','Hetfield','SoundtrackBoyes','GIMME FUEL GIMME FIRE GIMME THAT WHICH I DESIRE OOOOH','5548761248','46537518495','james@metallica.com','Somewhere in colorado',1),(9,'Cameron','Steinburg',NULL,'Fly as hell','4035128991','4035546265','cameronsteinburg@live.ca','105 Aspen Ridge Pl SW',1),(10,'Jane','Jefferson',NULL,'Smells like eggs','123456789','987654321','gamergurrllll@gmail.com','Gutter #276965',1),(11,'Billy','Boyd','Is5companiesenough','No Yarbles','842657952',NULL,'ben@jerrys.com',NULL,1),(12,'Drew','Peacock',NULL,'Guy still owes me 50 bucks','4038648645','5874586475',NULL,NULL,1),(13,'Artful','Dodger',NULL,'Welcome','47642865','548672598','art@dodge.com',NULL,1),(14,'Gerard','Way',NULL,NULL,'461544286','645824654','g@way.com','58264 Rose Hill Dr SE',1),(15,'Alex','DeLarge',NULL,'Viddy Well, My Dear Brothers','4678154389',NULL,'iwascured@alright.com',NULL,1),(16,'Bill','gates',NULL,NULL,'55555555',NULL,NULL,'1234 Super Rich Rd',1),(17,'Oliver','Sykes',NULL,NULL,'64512437659',NULL,'welsh@dropdead.com','666 vegan st',1),(18,'Patrick','Stump',NULL,NULL,'5642568759','5556894258','fedora@fob.com','88 Coopersonte Way NE',1);\n/*!40000 ALTER TABLE `clients` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `labourers`\n--\n\nDROP TABLE IF EXISTS `labourers`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `labourers` (\n  `labourerNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `fname` varchar(30) DEFAULT NULL,\n  `lname` varchar(30) DEFAULT NULL,\n  `title` varchar(30) DEFAULT NULL,\n  `phone1` varchar(11) DEFAULT NULL,\n  `phone2` varchar(11) DEFAULT NULL,\n  `email` varchar(30) DEFAULT NULL,\n  `address` varchar(50) DEFAULT NULL,\n  `emergcontact` varchar(30) DEFAULT NULL,\n  `emergcontactphone1` varchar(11) DEFAULT NULL,\n  `emergcontactphone2` varchar(11) DEFAULT NULL,\n  `sin` varchar(9) DEFAULT NULL,\n  `wage` varchar(5) DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  PRIMARY KEY (`labourerNum`)\n) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `labourers`\n--\n\nLOCK TABLES `labourers` WRITE;\n/*!40000 ALTER TABLE `labourers` DISABLE KEYS */;\nINSERT INTO `labourers` VALUES (1,'Eric','Stillman','FT Labourer','4035687426','4286452588','eric.still@gmail.com','344 Auburn St Unit #69','Phillip DeFranco','403568521','684525655','111222333','18.25',1),(2,'Keifer','Hicks','Contracter','587456852',NULL,NULL,'4242 Riverbend Rd SE','Shaizans Sister','5874456888','4036855477',NULL,NULL,1),(3,'Jeff','Jefferson',' PT Contracter','55526589525','4356526555',NULL,'Homeless Weaboo','Al Gore','5648264955',NULL,NULL,NULL,1),(4,'Sweaty','Taint',NULL,'4035428977',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1),(5,'Ryuji','Sakamoto',' PT Punk','55526589525',NULL,NULL,NULL,'Ann Takamaki',NULL,NULL,NULL,NULL,1),(6,'Johnny','Pottsmokr','PT Bitch','5876658942','4035688566','eatassandskatefast@hotmail.com','P.O Box #1433','Ethan Klein','4036754216','5874522588','596795325','15.00',1),(7,'Smoky','McPot','Contracter','4035428977',NULL,'blzitfgt@yahoo.ca','23 Bradberry Ln NW','Hila Klein','4036754216','5874522588',NULL,NULL,1);\n/*!40000 ALTER TABLE `labourers` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `projectlabourer`\n--\n\nDROP TABLE IF EXISTS `projectlabourer`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `projectlabourer` (\n  `projectNum` smallint(5) unsigned NOT NULL,\n  `labourerNum` smallint(5) unsigned NOT NULL,\n  KEY `fk_ProjectLabourerProject` (`projectNum`),\n  KEY `fk_ProjectLabourerLabourer` (`labourerNum`),\n  CONSTRAINT `fk_ProjectLabourerLabourer` FOREIGN KEY (`labourerNum`) REFERENCES `labourers` (`labourerNum`),\n  CONSTRAINT `fk_ProjectLabourerProject` FOREIGN KEY (`projectNum`) REFERENCES `projects` (`projectNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `projectlabourer`\n--\n\nLOCK TABLES `projectlabourer` WRITE;\n/*!40000 ALTER TABLE `projectlabourer` DISABLE KEYS */;\n/*!40000 ALTER TABLE `projectlabourer` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `projects`\n--\n\nDROP TABLE IF EXISTS `projects`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `projects` (\n  `projectNum` smallint(5) unsigned NOT NULL AUTO_INCREMENT,\n  `clientNum` smallint(5) unsigned NOT NULL,\n  `projectName` varchar(50) DEFAULT NULL,\n  `description` varchar(5000) DEFAULT NULL,\n  `siteAddress` varchar(100) DEFAULT NULL,\n  `startDate` date DEFAULT NULL,\n  `estimatedEndDate` date DEFAULT NULL,\n  `clientOwing` decimal(8,2) DEFAULT NULL,\n  `clientPaid` tinyint(1) DEFAULT NULL,\n  `estimatedShoppingCost` decimal(8,2) DEFAULT NULL,\n  `estimatedLabourCost` decimal(8,2) DEFAULT NULL,\n  `estimatedDeliveryCost` decimal(8,2) DEFAULT NULL,\n  `allowanceCost` decimal(8,2) DEFAULT NULL,\n  `actualShoppingCost` decimal(8,2) DEFAULT NULL,\n  `actualLabourCost` decimal(8,2) DEFAULT NULL,\n  `actualDeliveryCost` decimal(8,2) DEFAULT NULL,\n  `extraneousExpenses` decimal(8,2) DEFAULT NULL,\n  `estimatedProfit` decimal(8,2) DEFAULT NULL,\n  `actualProfit` decimal(8,2) DEFAULT NULL,\n  `actualEndDate` date DEFAULT NULL,\n  `isActive` tinyint(1) NOT NULL,\n  PRIMARY KEY (`projectNum`),\n  KEY `fk_ProjectClient` (`clientNum`),\n  CONSTRAINT `fk_ProjectClient` FOREIGN KEY (`clientNum`) REFERENCES `clients` (`clientNum`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `projects`\n--\n\nLOCK TABLES `projects` WRITE;\n/*!40000 ALTER TABLE `projects` DISABLE KEYS */;\n/*!40000 ALTER TABLE `projects` ENABLE KEYS */;\nUNLOCK TABLES;\n\n--\n-- Table structure for table `serviceconstants`\n--\n\nDROP TABLE IF EXISTS `serviceconstants`;\n/*!40101 SET @saved_cs_client     = @@character_set_client */;\n/*!40101 SET character_set_client = utf8 */;\nCREATE TABLE `serviceconstants` (\n  `superService` varchar(30) NOT NULL,\n  `subService` varchar(200) NOT NULL,\n  `constantLow` decimal(5,2) NOT NULL,\n  `constantHigh` decimal(5,2) DEFAULT NULL\n) ENGINE=InnoDB DEFAULT CHARSET=utf8;\n/*!40101 SET character_set_client = @saved_cs_client */;\n\n--\n-- Dumping data for table `serviceconstants`\n--\n\nLOCK TABLES `serviceconstants` WRITE;\n/*!40000 ALTER TABLE `serviceconstants` DISABLE KEYS */;\nINSERT INTO `serviceconstants` VALUES ('excavation','trucking /2 yards',78.00,NULL),('excavation','disposal',60.00,NULL),('excavation','man hours by hand /yards',3.00,NULL),('excavation','man hours by skid /yards',0.50,NULL),('excavation','labour cost by hand /hours',55.00,NULL),('excavation','labour cost by skid /hours',150.00,NULL),('bed','hours /yards',1.00,NULL),('bed','install /hours',55.00,NULL),('stonewalkway','estimated man hours',4.00,NULL),('stonewalkway','install rate /hours',55.00,NULL),('geotextilewalkway','weed barrier cost /sq. ft',0.15,NULL),('geotextilewalkway','fabric staples qty /1/5 sq. ft',5.00,NULL),('geotextilewalkway','fabric staples cost /staple',0.15,NULL),('geotextilewalkway','fabric man hours /100 sq ft',200.00,NULL),('geotextilewalkway','fabric install rate /hours',55.00,NULL),('walkwaybase','crushed base sq. ft/inch/yard',243.00,NULL),('walkwaybase','crasued cost /yard',75.00,NULL),('walkwaybase','man hours /yard',3.00,NULL),('walkwaybase','install rate / hours',55.00,NULL),('screedsand','depth sq.ft / yard',324.00,NULL),('screedsand','cost /yard',85.00,NULL),('screedsand','man hours / yard',3.00,NULL),('screedsand','install /hours',55.00,NULL),('edgerestraint','cost /8 ft',20.00,NULL),('edgerestraint','nails /1nail',1.00,NULL),('edgerestraint','man hours',50.00,NULL),('edgerestraint','install /hours',55.00,NULL),('jointingsand','QTY (kg/sf) @ 1/4 inch',0.60,NULL),('jointingsand','cost /kg',1.50,NULL),('jointingsand','hours /kg',0.10,NULL),('jointingsand','install /hours',55.00,NULL),('materials','Crushed Rock',53.10,59.00),('materials','Pea Rock',58.50,65.00),('materials','River Rock',58.50,65.00),('materials','Mulch: Western Red Cedar',47.45,52.72),('materials','Top Soil: Premium mix',35.10,39.00),('materials','Crusher Dust',44.21,50.84),('materials','Red Shale',85.00,97.75),('materials','Sod (per 10 s.f.)',3.60,4.80);\n/*!40000 ALTER TABLE `serviceconstants` ENABLE KEYS */;\nUNLOCK TABLES;\n/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;\n\n/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;\n/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;\n/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;\n/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;\n\n-- Dump completed on 2018-04-04 15:50:22\nnull\n";
                      result.append(line);
                      System.out.print(result.toString());
//                } while (line != null);

                path.append("\\startupdbscript.sql");
                File newFile = new File(path.toString());
                if (!newFile.exists()){
                    newFile.createNewFile();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		writer.write(result.toString());
                if (writer != null) writer.close();

	} catch (IOException e) {
		e.printStackTrace();
	}
        
        return true;
    }
    
}
