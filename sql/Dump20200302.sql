-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cleaningdb
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(70) NOT NULL,
  `users_login` varchar(45) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `idclient_UNIQUE` (`client_id`),
  KEY `fk_client_users1_idx` (`users_login`),
  CONSTRAINT `fk_client_users1` FOREIGN KEY (`users_login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (35,'Васенок','Альшевский','+375293638504','Горецкого 51,45','vasenok@mail.ru'),(36,'Турандот','Каблучкова','+375291855555','Шаранговича 25,36','trusik@mail.ru'),(37,'Лапухина','Евдакия','+375291548525','Габрилеевка 25,43','underdog@gmail.ru'),(41,'Ирина','Умелая','+375298521241','ул Горецкого 25, 45','nezabudka@gmail.ru'),(42,'Юлия ','Зюганова','+375297892564','ул Глебки 12,36','skripka@mail.ru'),(45,'Татьяна','Альшевская','+375291546225','ул Воронянского 1, 45','tttthanna@mail.ru'),(46,'Ирина','Романова','+375293698524','Петровщина 9,25','romanovairka@gmail.com'),(47,'Лев','Мышкин','+375291254121','пер. Охотский 14, 56','levmishka@mail.ru'),(48,'Лукьян','Лебедев','+375297892512','Притыцкого 12, 45','lukalucky@mail.ru'),(50,'Юлия ','Альшевская','+375291547825','ул Горецкого 51, 45','yulia.elf80lv@gmail.com');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL,
  `price` decimal(10,2) unsigned NOT NULL,
  `client_client_id` int(10) unsigned NOT NULL,
  `staff_staff_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `idorders_UNIQUE` (`order_id`),
  KEY `fk_orders_client1_idx` (`client_client_id`),
  KEY `fk_orders_staff1_idx` (`staff_staff_id`),
  CONSTRAINT `fk_orders_client1` FOREIGN KEY (`client_client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `fk_orders_staff1` FOREIGN KEY (`staff_staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (9,'2010-07-19 06:25:25',95.00,37,18),(10,'2010-09-19 10:25:25',130.00,36,19),(11,'2010-02-20 16:55:25',102.00,42,18),(12,'2020-01-08 16:55:25',89.00,37,18),(13,'2020-01-08 09:55:25',120.00,35,17),(15,'2020-01-11 05:30:25',156.00,45,22),(16,'2020-01-12 09:55:25',175.50,46,23),(17,'2020-01-15 09:00:00',200.00,47,24),(18,'2020-01-18 10:00:25',185.00,48,21),(19,'2020-01-20 10:55:00',210.35,45,23),(21,'2019-07-29 10:00:15',105.00,37,18),(22,'2019-07-29 10:00:15',105.00,37,18);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('administrator'),('cleaner'),('client');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_name` varchar(45) NOT NULL,
  `price_per_item` decimal(10,2) unsigned NOT NULL,
  `order_quantity` double unsigned NOT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id_UNIQUE` (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'Уборка квартиры',15.00,1),(9,'Генеральная уборка квартиры',25.00,1),(10,'уборка коттеджа',20.00,1),(11,'уборка офиса',15.00,1),(12,'уборка после пожара',30.00,1),(13,'эко-уборка',30.00,1),(14,'мытье окон',10.00,1),(17,'химчистка мебели',40.00,1),(19,'выгул домашнего питомца',7.00,1);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services_orders`
--

DROP TABLE IF EXISTS `services_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services_orders` (
  `services_service_id` int(10) unsigned NOT NULL,
  `orders_order_id` int(10) unsigned NOT NULL,
  KEY `fk_services_orders_services1_idx` (`services_service_id`),
  KEY `fk_services_orders_orders1_idx` (`orders_order_id`),
  CONSTRAINT `fk_services_orders_orders1` FOREIGN KEY (`orders_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `fk_services_orders_services1` FOREIGN KEY (`services_service_id`) REFERENCES `services` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services_orders`
--

LOCK TABLES `services_orders` WRITE;
/*!40000 ALTER TABLE `services_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `services_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(70) NOT NULL,
  `users_login` varchar(45) NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `idstaff_UNIQUE` (`staff_id`),
  KEY `fk_staff_users1_idx` (`users_login`),
  CONSTRAINT `fk_staff_users1` FOREIGN KEY (`users_login`) REFERENCES `users` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (14,'Юлия','Альшевская','+375293638504','Горецкого 51-45','julia.llove@mail.ru'),(17,'Ульяна','Куксик','Габрилеевка 25-43','+375291547825','kuksik1991@mail.ru'),(18,'Турандот','Тапочкин','+375291547825','Чайлытко 7-25','turandot@mail.ru'),(19,'Анатолий','Петров','+375291852324','Чайлытко 7-25','anattol@mail.ru'),(20,'Андрей','Иванов','+375291547825','Чайлытко 7-25','andrey1990@gmail.ru'),(21,'Елизаветта','Епанчина','+37529654573','ул Шагала 5, 36','epanchinaliss@gmail.ru'),(22,'Настасья','Барашкова','+375296548524','Габрилеевка 12-43','anastas@mail.ru'),(23,'Аглая','Епанчина','+375291258545','Горецкого 12, 56','lutik2504@gmail.com'),(24,'Гаврила','Иволгин','+375292568524','Сухаревская 25, 45','gavrila1991@mail.ru');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `roles_role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles1_idx` (`roles_role_name`),
  CONSTRAINT `fk_users_roles1` FOREIGN KEY (`roles_role_name`) REFERENCES `roles` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('anastas@mail.ru','62576c75633273794d444535','cleaner'),('anattol@mail.ru','62576c75633273794d444535','cleaner'),('andrey1990@gmail.ru','62576c75633273794d444535','cleaner'),('epanchinaliss@gmail.ru','62576c75633273794d444535','cleaner'),('gavrila1991@mail.ru','62576c75633273794d444535','cleaner'),('julia.llove@mail.ru','62576c75633273794d444535','administrator'),('kuksik1991@mail.ru','62576c75633273794d444535','cleaner'),('levmishka@mail.ru','62576c75633273794d444535','client'),('lukalucky@mail.ru','62576c75633273794d444535','client'),('lutik2504@gmail.com','62576c75633273794d444535','cleaner'),('nezabudka@gmail.ru','62576c75633273794d444535','client'),('romanovairka@gmail.com','62576c75633273794d444535','client'),('skripka@mail.ru','62576c75633273794d444535','client'),('trusik@mail.ru','62576c75633273794d444535','client'),('tttthanna@mail.ru','62576c75633273794d444535','client'),('turandot@mail.ru','62576c75633273794d444535','cleaner'),('underdog@gmail.ru','62576c75633273794d444535','client'),('vasenok@mail.ru','646d467a5a573576617a49774d54633d','client'),('yulia.elf80lv@gmail.com','62576c75633273794d444535','client');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cleaningdb'
--

--
-- Dumping routines for database 'cleaningdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-02 13:25:17
