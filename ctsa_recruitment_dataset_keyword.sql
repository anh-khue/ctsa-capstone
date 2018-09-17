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
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_synonym` bit(1) NOT NULL,
  `main_keyword_id` int(11) DEFAULT NULL,
  `position_id` int(11) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `word` (`word`),
  KEY `main_keyword_id` (`main_keyword_id`),
  KEY `position_id` (`position_id`),
  KEY `skill_id` (`skill_id`),
  CONSTRAINT `keyword_ibfk_1` FOREIGN KEY (`main_keyword_id`) REFERENCES `keyword` (`id`),
  CONSTRAINT `keyword_ibfk_2` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `keyword_ibfk_3` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (1,'erp',_binary '\0',NULL,NULL,1),(2,'crm',_binary '\0',NULL,NULL,2),(3,'cmmi',_binary '\0',NULL,NULL,3),(4,'pmp',_binary '\0',NULL,NULL,4),(5,'pmi',_binary '\0',NULL,NULL,5),(6,'.net',_binary '\0',NULL,NULL,6),(7,'php',_binary '\0',NULL,NULL,7),(8,'mssql',_binary '\0',NULL,NULL,8),(9,'oracle',_binary '\0',NULL,NULL,9),(10,'mysql',_binary '\0',NULL,NULL,10),(11,'java',_binary '\0',NULL,NULL,12),(12,'ruby',_binary '\0',NULL,NULL,13),(13,'ssl',_binary '\0',NULL,NULL,15),(14,'mobile',_binary '\0',NULL,NULL,16),(15,'sql server',_binary '',8,NULL,NULL),(16,'angular',_binary '\0',NULL,NULL,19),(17,'html',_binary '\0',NULL,NULL,20),(18,'javascript',_binary '\0',NULL,NULL,21),(19,'css',_binary '\0',NULL,NULL,22),(20,'spring',_binary '\0',NULL,NULL,23),(21,'spring boot',_binary '\0',NULL,NULL,24),(22,'nosql',_binary '\0',NULL,NULL,26),(23,'apache kafka',_binary '\0',NULL,NULL,27),(24,'microservices',_binary '\0',NULL,NULL,28),(25,'maven',_binary '\0',NULL,NULL,29),(26,'git',_binary '\0',NULL,NULL,30),(27,'docker',_binary '\0',NULL,NULL,31),(28,'mesosphere',_binary '\0',NULL,NULL,32),(29,'elasticsearch',_binary '\0',NULL,NULL,33),(30,'apache spark',_binary '\0',NULL,NULL,34),(31,'android',_binary '\0',NULL,NULL,35),(32,'objective-c',_binary '\0',NULL,NULL,36),(33,'swift',_binary '\0',NULL,NULL,37),(34,'english',_binary '\0',NULL,NULL,38),(35,'kafka',_binary '',23,NULL,NULL),(36,'japanese',_binary '\0',NULL,NULL,39),(37,'ui/ux',_binary '\0',NULL,NULL,40),(38,'continuous interation',_binary '\0',NULL,NULL,41),(39,'back-end',_binary '\0',NULL,NULL,42),(40,'redis',_binary '\0',NULL,NULL,43),(41,'toefl ibt',_binary '\0',NULL,NULL,44),(42,'hibernate',_binary '\0',NULL,NULL,45),(43,'restful api',_binary '\0',NULL,NULL,46),(44,'design patterns',_binary '\0',NULL,NULL,47),(45,'tdd',_binary '\0',NULL,NULL,48),(46,'c#',_binary '\0',NULL,NULL,49),(47,'c++',_binary '\0',NULL,NULL,50),(48,'asp.net',_binary '\0',NULL,NULL,51),(49,'jquery',_binary '\0',NULL,NULL,52),(50,'wcf',_binary '\0',NULL,NULL,53),(51,'web api',_binary '\0',NULL,NULL,54),(52,'bdd',_binary '\0',NULL,NULL,55),(53,'linux',_binary '\0',NULL,NULL,56),(54,'nginx',_binary '\0',NULL,NULL,57),(55,'python',_binary '\0',NULL,NULL,58),(56,'ruby on rails',_binary '\0',NULL,NULL,59),(57,'mongodb',_binary '\0',NULL,NULL,60),(58,'jee',_binary '\0',NULL,NULL,61),(59,'relational databases',_binary '\0',NULL,NULL,62),(60,'orm',_binary '\0',NULL,NULL,63),(61,'jpa',_binary '\0',NULL,NULL,64),(62,'oop',_binary '\0',NULL,NULL,65),(63,'mvc',_binary '\0',NULL,NULL,66),(64,'aws',_binary '\0',NULL,NULL,67),(65,'cloud server',_binary '\0',NULL,NULL,68),(66,'c',_binary '\0',NULL,NULL,69),(67,'embedded',_binary '\0',NULL,NULL,70),(68,'xml',_binary '\0',NULL,NULL,71),(69,'opengl',_binary '\0',NULL,NULL,72),(70,'dbms',_binary '\0',NULL,NULL,73),(71,'photoshop',_binary '\0',NULL,NULL,74),(72,'agile',_binary '\0',NULL,NULL,75),(73,'scrum',_binary '\0',NULL,NULL,76),(74,'swiff',_binary '\0',NULL,NULL,77),(75,'bootstrap',_binary '\0',NULL,NULL,78),(76,'redmine',_binary '\0',NULL,NULL,79),(77,'zend',_binary '\0',NULL,NULL,80),(78,'laravel',_binary '\0',NULL,NULL,81),(79,'google cloud',_binary '\0',NULL,NULL,82),(80,'angularjs',_binary '\0',NULL,NULL,83),(81,'html5',_binary '\0',NULL,NULL,84),(82,'sass',_binary '\0',NULL,NULL,85),(83,'front-end',_binary '\0',NULL,NULL,86),(84,'multi-thread',_binary '\0',NULL,NULL,87),(85,'concurrent',_binary '\0',NULL,NULL,88),(86,'atomic',_binary '\0',NULL,NULL,89),(87,'entity framework',_binary '\0',NULL,NULL,90),(88,'wpf',_binary '\0',NULL,NULL,91),(89,'azure',_binary '\0',NULL,NULL,92),(90,'asp.net mvc',_binary '\0',NULL,NULL,93),(91,'.net core',_binary '\0',NULL,NULL,94),(92,'react',_binary '',111,NULL,NULL),(93,'vue.js',_binary '\0',NULL,NULL,96),(94,'yii',_binary '\0',NULL,NULL,97),(95,'ubuntu',_binary '\0',NULL,NULL,98),(96,'magento',_binary '\0',NULL,NULL,99),(97,'cms',_binary '\0',NULL,NULL,100),(98,'wordpress',_binary '\0',NULL,NULL,101),(99,'node.js',_binary '\0',NULL,NULL,102),(100,'ddd',_binary '\0',NULL,NULL,103),(101,'drupal',_binary '\0',NULL,NULL,104),(102,'joomla',_binary '\0',NULL,NULL,105),(103,'github',_binary '\0',NULL,NULL,106),(104,'atlassian',_binary '\0',NULL,NULL,107),(105,'apache velocity',_binary '\0',NULL,NULL,108),(106,'full-stack',_binary '\0',NULL,NULL,109),(107,'blockchain',_binary '\0',NULL,NULL,110),(108,'ai',_binary '\0',NULL,NULL,111),(109,'stored procedures',_binary '\0',NULL,NULL,112),(110,'trigger',_binary '\0',NULL,NULL,113),(111,'reactjs',_binary '\0',NULL,NULL,114),(112,'ci',_binary '\0',NULL,NULL,115),(113,'perl',_binary '\0',NULL,NULL,116),(115,'automation testing',_binary '\0',NULL,NULL,118),(116,'distributed',_binary '\0',NULL,NULL,119),(117,'big data',_binary '\0',NULL,NULL,120),(118,'manual testing',_binary '\0',NULL,NULL,121),(119,'wireframe',_binary '\0',NULL,NULL,122),(120,'illustrator',_binary '\0',NULL,NULL,123),(122,'cloud',_binary '\0',NULL,NULL,125),(123,'software architecture',_binary '\0',NULL,NULL,126),(124,'jsp',_binary '\0',NULL,NULL,127),(125,'coreldraw',_binary '\0',NULL,NULL,128),(126,'blackbox testing',_binary '\0',NULL,NULL,129),(127,'cd',_binary '\0',NULL,NULL,130),(128,'ux',_binary '\0',NULL,NULL,131),(129,'ui',_binary '\0',NULL,NULL,132),(130,'spark',_binary '',34,NULL,NULL),(136,'thực tập sinh',_binary '\0',NULL,12,NULL),(137,'developer',_binary '\0',NULL,1,NULL),(138,'engineer',_binary '\0',NULL,NULL,NULL),(139,'kỹ sư',_binary '\0',NULL,NULL,NULL),(140,'qa',_binary '\0',NULL,2,NULL),(141,'qc',_binary '\0',NULL,2,NULL),(142,'test engineer',_binary '\0',NULL,2,NULL),(143,'lập trình viên',_binary '\0',NULL,1,NULL),(144,'chuyên viên kiểm thử',_binary '\0',NULL,2,NULL),(145,'business analysis',_binary '\0',NULL,3,NULL),(146,'business analysis officer',_binary '\0',NULL,3,NULL),(147,'ba',_binary '\0',NULL,3,NULL),(148,'onsite analysis',_binary '\0',NULL,3,NULL),(149,'lead',_binary '\0',NULL,4,NULL),(150,'trưởng nhóm',_binary '\0',NULL,4,NULL),(151,'pm',_binary '\0',NULL,5,NULL),(152,'chief technology officer',_binary '\0',NULL,6,NULL),(153,'admin',_binary '\0',NULL,8,NULL),(154,'brse',_binary '\0',NULL,11,NULL),(155,'internship',_binary '\0',NULL,12,NULL),(156,'ojt',_binary '\0',NULL,12,NULL),(165,'professional',_binary '\0',NULL,14,NULL),(166,'dotnet',_binary '',6,NULL,NULL),(167,'asp',_binary '\0',NULL,NULL,247),(168,'react native',_binary '\0',NULL,NULL,250),(169,'nodejs',_binary '',99,NULL,NULL),(170,'angular 4',_binary '',16,NULL,NULL),(171,'angular 1',_binary '',80,NULL,NULL),(172,'backend',_binary '',39,NULL,NULL),(173,'frontend',_binary '',83,NULL,NULL),(174,'java 8',_binary '\0',NULL,NULL,251),(175,'chuyên viên phát triển ứng dụng',_binary '\0',NULL,1,NULL),(176,'test-driven development',_binary '',45,NULL,NULL),(177,'behavior driven development',_binary '',52,NULL,NULL),(178,'my sql',_binary '',10,NULL,NULL);
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-17 15:23:38
