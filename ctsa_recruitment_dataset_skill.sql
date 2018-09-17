-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: ctsa_recruitment_dataset
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `skill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (6,'.NET'),(94,'.Net Core'),(75,'Agile'),(111,'AI'),(35,'Android'),(19,'Angular'),(83,'AngularJS'),(27,'Apache Kafka'),(34,'Apache Spark'),(108,'Apache Velocity'),(247,'ASP'),(51,'ASP.NET'),(93,'ASP.NET MVC'),(107,'Atlassian'),(89,'Atomic'),(118,'Automation Testing'),(67,'AWS'),(92,'Azure'),(42,'Back-end'),(55,'BDD'),(120,'Big Data'),(129,'Blackbox Testing'),(110,'Blockchain'),(78,'Bootstrap'),(69,'C'),(49,'C#'),(50,'C++'),(130,'CD'),(115,'CI'),(125,'Cloud'),(68,'Cloud Server'),(3,'CMMI'),(100,'CMS'),(88,'Concurrent'),(41,'Continuous interation'),(128,'CorelDraw'),(2,'CRM'),(22,'CSS'),(73,'DBMS'),(103,'DDD'),(47,'Design Patterns'),(119,'Distributed'),(31,'Docker'),(104,'Drupal'),(33,'Elasticsearch'),(70,'Embedded'),(38,'English'),(90,'Entity Framework'),(1,'ERP'),(86,'Front-end'),(109,'Full-Stack'),(30,'Git'),(106,'Github'),(82,'Google Cloud'),(45,'Hibernate'),(20,'HTML'),(84,'HTML5'),(123,'Illustrator'),(39,'Japanese'),(12,'Java'),(251,'Java 8\r\n'),(21,'JavaScript'),(61,'Jee'),(105,'Joomla'),(64,'JPA'),(52,'Jquery'),(127,'JSP'),(248,'Kotlin'),(81,'Laravel'),(56,'Linux'),(99,'Magento'),(121,'Manual Testing'),(29,'Maven'),(32,'Mesosphere'),(28,'Microservices'),(16,'Mobile'),(60,'MongoDB'),(8,'MSSQL'),(87,'Multi-thread'),(66,'MVC'),(10,'MySQL'),(57,'NGINX'),(102,'Node.js'),(26,'NoSQL'),(36,'Objective-C'),(65,'OOP'),(72,'OpenGL'),(9,'Oracle'),(63,'ORM'),(116,'Perl'),(74,'Photoshop'),(7,'PHP'),(5,'PMI'),(4,'PMP'),(124,'Professional'),(58,'Python'),(250,'React Native'),(114,'ReactJS'),(43,'Redis'),(79,'Redmine'),(62,'Relational Databases'),(46,'RESTful API'),(13,'Ruby'),(59,'Ruby on Rails'),(85,'SASS'),(76,'Scrum'),(126,'Software Architecture'),(23,'Spring'),(24,'Spring Boot'),(17,'SQL Server'),(15,'SSL'),(112,'Stored Procedures'),(77,'Swiff'),(37,'Swift'),(48,'TDD'),(44,'TOEFL iBT'),(113,'Trigger'),(98,'Ubuntu'),(132,'UI'),(40,'UI/UX'),(131,'UX'),(96,'Vue.js'),(53,'WCF'),(54,'Web API'),(122,'Wireframe'),(101,'WordPress'),(91,'WPF'),(71,'XML'),(97,'Yii'),(80,'Zend');
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-17 15:23:39
