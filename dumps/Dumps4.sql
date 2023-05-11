-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: gestion
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `biblioteca`
--

DROP TABLE IF EXISTS `biblioteca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biblioteca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(40) DEFAULT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `apertura` datetime DEFAULT NULL,
  `cierre` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biblioteca`
--

LOCK TABLES `biblioteca` WRITE;
/*!40000 ALTER TABLE `biblioteca` DISABLE KEYS */;
INSERT INTO `biblioteca` VALUES (23,NULL,'CEUEPS','Biblioteca de la Escuela Politécnica Superior de la Universidad San Pablo CEU, Campus Montepríncipe','2024-09-01 08:30:00','2024-12-23 20:30:00'),(24,NULL,'CEUMED','Biblioteca de la Facultad de Medicina de la Universidad San Pablo CEU, Campus Montepríncipe','2024-09-01 08:30:00','2024-12-23 20:30:00'),(25,NULL,'CEUDER','Biblioteca de la Facultad de Derecho de la Universidad San Pablo CEU, Campus Julián Romea','2024-09-01 08:30:00','2024-12-23 20:30:00'),(26,NULL,'CEUECO','Biblioteca de la Facultad de Económicas de la Universidad San Pablo CEU, Campus Julián Romea','2024-09-01 08:30:00','2024-12-23 20:30:00'),(27,NULL,'CEUFAR','Biblioteca de la Facultad de Farmacia de la Universidad San Pablo CEU, Campus Montepríncipe','2024-09-01 08:30:00','2024-12-23 20:30:00'),(29,NULL,'CEUHYC','Biblioteca de la Facultad de Hum. y Com. de la Universidad San Pablo CEU, Campus Julián Romea','2024-09-01 08:30:00','2024-12-23 20:30:00');
/*!40000 ALTER TABLE `biblioteca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidadelementoreservable`
--

DROP TABLE IF EXISTS `disponibilidadelementoreservable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disponibilidadelementoreservable` (
  `fecha` datetime NOT NULL,
  `elementoReservableID` int NOT NULL,
  PRIMARY KEY (`fecha`,`elementoReservableID`),
  KEY `elementoReservableID` (`elementoReservableID`),
  CONSTRAINT `disponibilidadelementoreservable_ibfk_1` FOREIGN KEY (`elementoReservableID`) REFERENCES `elementoreservable` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidadelementoreservable`
--

LOCK TABLES `disponibilidadelementoreservable` WRITE;
/*!40000 ALTER TABLE `disponibilidadelementoreservable` DISABLE KEYS */;
/*!40000 ALTER TABLE `disponibilidadelementoreservable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disponibilidadrecursoextra`
--

DROP TABLE IF EXISTS `disponibilidadrecursoextra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `disponibilidadrecursoextra` (
  `fecha` datetime NOT NULL,
  `recursoExtraID` int NOT NULL,
  PRIMARY KEY (`fecha`,`recursoExtraID`),
  KEY `recursoExtraID` (`recursoExtraID`),
  CONSTRAINT `disponibilidadrecursoextra_ibfk_1` FOREIGN KEY (`recursoExtraID`) REFERENCES `recursoextra` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disponibilidadrecursoextra`
--

LOCK TABLES `disponibilidadrecursoextra` WRITE;
/*!40000 ALTER TABLE `disponibilidadrecursoextra` DISABLE KEYS */;
/*!40000 ALTER TABLE `disponibilidadrecursoextra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elementoreservable`
--

DROP TABLE IF EXISTS `elementoreservable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elementoreservable` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) NOT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `bibliotecaID` int DEFAULT NULL,
  `aforoSala` int DEFAULT NULL,
  `infoPuesto` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bibliotecaID` (`bibliotecaID`),
  CONSTRAINT `elementoreservable_ibfk_1` FOREIGN KEY (`bibliotecaID`) REFERENCES `biblioteca` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementoreservable`
--

LOCK TABLES `elementoreservable` WRITE;
/*!40000 ALTER TABLE `elementoreservable` DISABLE KEYS */;
INSERT INTO `elementoreservable` VALUES (36,'/biblioteca/23/salas/36','Sala para trabajos en grupo','S',23,4,NULL),(37,'/biblioteca/23/salas/37','Sala para trabajos en grupo','S',23,4,NULL),(38,'/biblioteca/23/salas/38','Sala para trabajos en grupo','S',23,4,NULL),(39,'/biblioteca/23/salas/39','Sala para trabajos en grupo','S',23,4,NULL),(40,'/biblioteca/23/salas/40','Sala para trabajos en grupo','S',23,4,NULL),(41,'/biblioteca/23/salas/41','Sala para trabajos en grupo','S',23,4,NULL),(42,'/biblioteca/23/salas/42','Sala para trabajos en grupo','S',23,4,NULL),(43,'/biblioteca/23/salas/43','Sala para trabajos en grupo','S',23,4,NULL),(44,'/biblioteca/24/salas/44','Sala para trabajos en grupo','S',24,4,NULL),(45,'/biblioteca/24/salas/45','Sala para trabajos en grupo','S',24,4,NULL),(46,'/biblioteca/24/salas/46','Sala para trabajos en grupo','S',24,4,NULL),(47,'/biblioteca/24/salas/47','Sala para trabajos en grupo','S',24,4,NULL),(48,'/biblioteca/24/salas/48','Sala para trabajos en grupo','S',24,4,NULL),(49,'/biblioteca/24/salas/49','Sala para trabajos en grupo','S',24,4,NULL),(50,'/biblioteca/24/salas/50','Sala para trabajos en grupo','S',24,4,NULL),(51,'/biblioteca/25/salas/51','Sala para trabajos en grupo','S',25,4,NULL),(52,'/biblioteca/25/salas/52','Sala para trabajos en grupo','S',25,4,NULL),(53,'/biblioteca/25/salas/53','Sala para trabajos en grupo','S',25,4,NULL),(54,'/biblioteca/25/salas/54','Sala para trabajos en grupo','S',25,4,NULL),(55,'/biblioteca/25/salas/55','Sala para trabajos en grupo','S',25,4,NULL),(56,'/biblioteca/25/salas/56','Sala para trabajos en grupo','S',25,4,NULL),(57,'/biblioteca/25/salas/57','Sala para trabajos en grupo','S',25,4,NULL),(58,'/biblioteca/26/salas/58','Sala para trabajos en grupo','S',26,4,NULL),(59,'/biblioteca/26/salas/59','Sala para trabajos en grupo','S',26,4,NULL),(60,'/biblioteca/26/salas/60','Sala para trabajos en grupo','S',26,4,NULL),(61,'/biblioteca/26/salas/61','Sala para trabajos en grupo','S',26,4,NULL),(62,'/biblioteca/26/salas/62','Sala para trabajos en grupo','S',26,4,NULL),(63,'/biblioteca/26/salas/63','Sala para trabajos en grupo','S',26,4,NULL),(64,'/biblioteca/26/salas/64','Sala para trabajos en grupo','S',26,4,NULL),(65,'/biblioteca/27/salas/65','Sala para trabajos en grupo','S',27,4,NULL),(66,'/biblioteca/27/salas/66','Sala para trabajos en grupo','S',27,4,NULL),(67,'/biblioteca/27/salas/67','Sala para trabajos en grupo','S',27,4,NULL),(68,'/biblioteca/27/salas/68','Sala para trabajos en grupo','S',27,4,NULL),(69,'/biblioteca/27/salas/69','Sala para trabajos en grupo','S',27,4,NULL),(70,'/biblioteca/27/salas/70','Sala para trabajos en grupo','S',27,4,NULL),(71,'/biblioteca/27/salas/71','Sala para trabajos en grupo','S',27,4,NULL),(72,'/biblioteca/27/salas/72','Sala para trabajos en grupo','S',27,4,NULL),(73,'/biblioteca/29/salas/73','Sala para trabajos en grupo','S',29,4,NULL),(74,'/biblioteca/29/salas/74','Sala para trabajos en grupo','S',29,4,NULL),(75,'/biblioteca/29/salas/75','Sala para trabajos en grupo','S',29,4,NULL),(76,'/biblioteca/29/salas/76','Sala para trabajos en grupo','S',29,4,NULL),(77,'/biblioteca/29/salas/77','Sala para trabajos en grupo','S',29,4,NULL),(78,'/biblioteca/29/salas/78','Sala para trabajos en grupo','S',29,4,NULL),(79,'/biblioteca/29/salas/79','Sala para trabajos en grupo','S',29,4,NULL),(80,'/biblioteca/23/puestos/80','Puesto de estudio invididual','P',23,NULL,'Zona entrada'),(81,'/biblioteca/23/puestos/81','Puesto de estudio invididual','P',23,NULL,'Zona entrada'),(82,'/biblioteca/23/puestos/82','Puesto de estudio invididual','P',23,NULL,'Zona entrada'),(83,'/biblioteca/23/puestos/83','Puesto de estudio invididual','P',23,NULL,'Zona entrada'),(84,'/biblioteca/23/puestos/84','Puesto de estudio invididual','P',23,NULL,'Zona entrada'),(85,'/biblioteca/23/puestos/85','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(86,'/biblioteca/23/puestos/86','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(87,'/biblioteca/23/puestos/87','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(88,'/biblioteca/23/puestos/88','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(89,'/biblioteca/23/puestos/89','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(90,'/biblioteca/23/puestos/90','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(91,'/biblioteca/23/puestos/91','Puesto de estudio invididual','P',23,NULL,'Zona salas'),(92,'/biblioteca/23/puestos/92','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(93,'/biblioteca/23/puestos/93','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(94,'/biblioteca/23/puestos/94','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(95,'/biblioteca/23/puestos/95','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(96,'/biblioteca/23/puestos/96','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(97,'/biblioteca/23/puestos/97','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(98,'/biblioteca/23/puestos/98','Puesto de estudio invididual','P',23,NULL,'Zona inferior'),(99,'/biblioteca/24/puestos/99','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(100,'/biblioteca/24/puestos/100','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(101,'/biblioteca/24/puestos/101','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(102,'/biblioteca/24/puestos/102','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(103,'/biblioteca/24/puestos/103','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(104,'/biblioteca/24/puestos/104','Puesto de estudio invididual','P',24,NULL,'Zona entrada'),(105,'/biblioteca/24/puestos/105','Puesto de estudio invididual','P',24,NULL,'Zona salas'),(106,'/biblioteca/24/puestos/106','Puesto de estudio invididual','P',24,NULL,'Zona salas'),(107,'/biblioteca/24/puestos/107','Puesto de estudio invididual','P',24,NULL,'Zona salas'),(108,'/biblioteca/24/puestos/108','Puesto de estudio invididual','P',24,NULL,'Zona salas'),(109,'/biblioteca/24/puestos/109','Puesto de estudio invididual','P',24,NULL,'Zona salas'),(110,'/biblioteca/25/puestos/110','Puesto de estudio invididual','P',25,NULL,'Zona general'),(111,'/biblioteca/25/puestos/111','Puesto de estudio invididual','P',25,NULL,'Zona general'),(112,'/biblioteca/25/puestos/112','Puesto de estudio invididual','P',25,NULL,'Zona general'),(113,'/biblioteca/25/puestos/113','Puesto de estudio invididual','P',25,NULL,'Zona general'),(114,'/biblioteca/25/puestos/114','Puesto de estudio invididual','P',25,NULL,'Zona general'),(115,'/biblioteca/25/puestos/115','Puesto de estudio invididual','P',25,NULL,'Zona general'),(116,'/biblioteca/25/puestos/116','Puesto de estudio invididual','P',25,NULL,'Zona general'),(117,'/biblioteca/25/puestos/117','Puesto de estudio invididual','P',25,NULL,'Zona general'),(118,'/biblioteca/25/puestos/118','Puesto de estudio invididual','P',25,NULL,'Zona general'),(119,'/biblioteca/25/puestos/119','Puesto de estudio invididual','P',25,NULL,'Zona general'),(120,'/biblioteca/25/puestos/120','Puesto de estudio invididual','P',25,NULL,'Zona general'),(121,'/biblioteca/25/puestos/121','Puesto de estudio invididual','P',25,NULL,'Zona general'),(122,'/biblioteca/25/puestos/122','Puesto de estudio invididual','P',25,NULL,'Zona general'),(123,'/biblioteca/25/puestos/123','Puesto de estudio invididual','P',25,NULL,'Zona general'),(124,'/biblioteca/25/puestos/124','Puesto de estudio invididual','P',25,NULL,'Zona general'),(125,'/biblioteca/25/puestos/125','Puesto de estudio invididual','P',25,NULL,'Zona general'),(126,'/biblioteca/25/puestos/126','Puesto de estudio invididual','P',25,NULL,'Zona general'),(127,'/biblioteca/25/puestos/127','Puesto de estudio invididual','P',25,NULL,'Zona general'),(128,'/biblioteca/26/puestos/128','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(129,'/biblioteca/26/puestos/129','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(130,'/biblioteca/26/puestos/130','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(131,'/biblioteca/26/puestos/131','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(132,'/biblioteca/26/puestos/132','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(133,'/biblioteca/26/puestos/133','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(134,'/biblioteca/26/puestos/134','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(135,'/biblioteca/26/puestos/135','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(136,'/biblioteca/26/puestos/136','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(137,'/biblioteca/26/puestos/137','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(138,'/biblioteca/26/puestos/138','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(139,'/biblioteca/26/puestos/139','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(140,'/biblioteca/26/puestos/140','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(141,'/biblioteca/26/puestos/141','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(142,'/biblioteca/26/puestos/142','Puesto de estudio invididual','P',26,NULL,'Zona amplia'),(143,'/biblioteca/27/puestos/143','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(144,'/biblioteca/27/puestos/144','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(145,'/biblioteca/27/puestos/145','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(146,'/biblioteca/27/puestos/146','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(147,'/biblioteca/27/puestos/147','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(148,'/biblioteca/27/puestos/148','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(149,'/biblioteca/27/puestos/149','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(150,'/biblioteca/27/puestos/150','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(151,'/biblioteca/27/puestos/151','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(152,'/biblioteca/27/puestos/152','Puesto de estudio individual','P',27,NULL,'Zona izquierda'),(153,'/biblioteca/27/puestos/153','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(154,'/biblioteca/27/puestos/154','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(155,'/biblioteca/27/puestos/155','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(156,'/biblioteca/27/puestos/156','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(157,'/biblioteca/27/puestos/157','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(158,'/biblioteca/27/puestos/158','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(159,'/biblioteca/27/puestos/159','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(160,'/biblioteca/27/puestos/160','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(161,'/biblioteca/27/puestos/161','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(162,'/biblioteca/27/puestos/162','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(163,'/biblioteca/27/puestos/163','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(164,'/biblioteca/27/puestos/164','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(165,'/biblioteca/27/puestos/165','Puesto de estudio individual','P',27,NULL,'Zona derecha'),(166,'/biblioteca/29/puestos/166','Puesto de estudio individual','P',29,NULL,'Zona principal'),(167,'/biblioteca/29/puestos/167','Puesto de estudio individual','P',29,NULL,'Zona principal'),(168,'/biblioteca/29/puestos/168','Puesto de estudio individual','P',29,NULL,'Zona principal'),(169,'/biblioteca/29/puestos/169','Puesto de estudio individual','P',29,NULL,'Zona principal'),(170,'/biblioteca/29/puestos/170','Puesto de estudio individual','P',29,NULL,'Zona principal'),(171,'/biblioteca/29/puestos/171','Puesto de estudio individual','P',29,NULL,'Zona principal'),(172,'/biblioteca/29/puestos/172','Puesto de estudio individual','P',29,NULL,'Zona principal'),(173,'/biblioteca/29/puestos/173','Puesto de estudio individual','P',29,NULL,'Zona principal'),(174,'/biblioteca/29/puestos/174','Puesto de estudio individual','P',29,NULL,'Zona principal'),(175,'/biblioteca/29/puestos/175','Puesto de estudio individual','P',29,NULL,'Zona principal'),(176,'/biblioteca/29/puestos/176','Puesto de estudio individual','P',29,NULL,'Zona principal'),(177,'/biblioteca/29/puestos/177','Puesto de estudio individual','P',29,NULL,'Zona principal'),(178,'/biblioteca/29/puestos/178','Puesto de estudio individual','P',29,NULL,'Zona principal'),(179,'/biblioteca/29/puestos/179','Puesto de estudio individual','P',29,NULL,'Zona principal'),(180,'/biblioteca/29/puestos/180','Puesto de estudio individual','P',29,NULL,'Zona principal'),(181,'/biblioteca/29/puestos/181','Puesto de estudio individual','P',29,NULL,'Zona principal'),(182,'/biblioteca/29/puestos/182','Puesto de estudio individual','P',29,NULL,'Zona principal');
/*!40000 ALTER TABLE `elementoreservable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recursoextra`
--

DROP TABLE IF EXISTS `recursoextra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recursoextra` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `descripcion` varchar(100) NOT NULL,
  `tipo` varchar(1) DEFAULT NULL,
  `bibliotecaID` int DEFAULT NULL,
  `numSerie` varchar(30) DEFAULT NULL,
  `ISBN` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bibliotecaID` (`bibliotecaID`),
  CONSTRAINT `recursoextra_ibfk_1` FOREIGN KEY (`bibliotecaID`) REFERENCES `biblioteca` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recursoextra`
--

LOCK TABLES `recursoextra` WRITE;
/*!40000 ALTER TABLE `recursoextra` DISABLE KEYS */;
INSERT INTO `recursoextra` VALUES (3,'OrdenadorPortátil','/biblioteca/23/ordenadores/3','HP 83629J con cargador 29741D','O',23,'10856H',NULL),(4,'OrdenadorPortátil','/biblioteca/23/ordenadores/4','HP 42351T con cargador 65914K','O',23,'87236Z',NULL),(5,'OrdenadorPortátil','/biblioteca/23/ordenadores/5','HP 13580M con cargador 56297G','O',23,'97413B',NULL),(6,'OrdenadorPortátil','/biblioteca/23/ordenadores/6','HP 28469P con cargador 74026N','O',23,'61385F',NULL),(7,'OrdenadorPortátil','/biblioteca/23/ordenadores/7','HP 42918X con cargador 84650L','O',23,'31527R',NULL),(8,'OrdenadorFijo','/biblioteca/23/ordenadores/8','Apple iMAC 96124Y con ratón 50739E','O',23,'72854V',NULL),(9,'OrdenadorFijo','/biblioteca/23/ordenadores/9','Apple iMAC 28653A con ratón 62497C','O',23,'38562W',NULL),(10,'OrdenadorFijo','/biblioteca/23/ordenadores/10','Apple iMAC 94075I con ratón 25147Q','O',23,'78450O',NULL),(11,'OrdenadorPortátil','/biblioteca/24/ordenadores/11','HP 16028J con cargador 34689D','O',24,'57941H',NULL),(12,'OrdenadorPortátil','/biblioteca/24/ordenadores/12','HP 86523T con cargador 41295K','O',24,'70318Z',NULL),(13,'OrdenadorPortátil','/biblioteca/24/ordenadores/13','HP 19842M con cargador 93257G','O',24,'42713B',NULL),(14,'OrdenadorPortátil','/biblioteca/24/ordenadores/14','HP 51968P con cargador 64827N','O',24,'29315F',NULL),(15,'OrdenadorPortátil','/biblioteca/24/ordenadores/15','HP 85624X con cargador 17503L','O',24,'76089R',NULL),(16,'OrdenadorFijo','/biblioteca/24/ordenadores/16','Apple iMAC 23596Y con ratón 69710E','O',24,'81053V',NULL),(17,'OrdenadorPortátil','/biblioteca/25/ordenadores/17','HP 35762A con cargador 98416C','O',25,'51879W',NULL),(18,'OrdenadorPortátil','/biblioteca/25/ordenadores/18','HP 64302I con cargador 17649Q','O',25,'82957O',NULL),(19,'OrdenadorPortátil','/biblioteca/25/ordenadores/19','HP 49236J con cargador 10758D','O',25,'35914H',NULL),(20,'OrdenadorFijo','/biblioteca/25/ordenadores/20','Apple iMAC 13928R con ratón 60751L','O',25,'29403Y',NULL),(21,'OrdenadorPortátil','/biblioteca/26/ordenadores/21','HP 81567K con cargador 48206W','O',26,'92613F',NULL),(22,'OrdenadorPortátil','/biblioteca/26/ordenadores/22','HP 35249C con cargador 74968P','O',26,'16352G',NULL),(23,'OrdenadorPortátil','/biblioteca/26/ordenadores/23','HP 57096B con cargador 23541X','O',26,'96418J',NULL),(24,'OrdenadorFijo','/biblioteca/26/ordenadores/24','Apple iMAC 40726T con ratón 52139M','O',26,'78961Z',NULL),(25,'OrdenadorFijo','/biblioteca/26/ordenadores/25','Apple iMAC 24680A con ratón 63024D','O',26,'98537N',NULL),(26,'OrdenadorFijo','/biblioteca/26/ordenadores/26','Apple iMAC 71450H con ratón 37891V','O',26,'46953W',NULL),(27,'OrdenadorFijo','/biblioteca/26/ordenadores/27','Apple iMAC 75842J con ratón 31259G','O',26,'92710Y',NULL),(28,'OrdenadorPortátil','/biblioteca/27/ordenadores/28','HP 18547S con cargador 65487D','O',27,'84329L',NULL),(29,'OrdenadorFijo','/biblioteca/27/ordenadores/29','Apple iMAC 71602M con ratón 35948G','O',27,'48201B',NULL),(30,'OrdenadorPortátil','/biblioteca/27/ordenadores/30','HP 84106R con cargador 32715N','O',27,'56938E',NULL),(31,'OrdenadorPortátil','/biblioteca/27/ordenadores/31','HP 95126X con cargador 47832H','O',27,'63108T',NULL),(32,'OrdenadorPortátil','/biblioteca/27/ordenadores/32','HP 29456B con cargador 89560Z','O',27,'14025M',NULL),(33,'OrdenadorPortátil','/biblioteca/27/ordenadores/33','HP 57961C con cargador 94637K','O',27,'20184F',NULL),(34,'OrdenadorFijo','/biblioteca/27/ordenadores/34','Apple iMAC 75361Q con ratón 46230O','O',27,'83917I',NULL),(35,'OrdenadorFijo','/biblioteca/27/ordenadores/35','Apple iMAC 27590U con ratón 61529J','O',27,'78046D',NULL),(36,'OrdenadorPortátil','/biblioteca/29/ordenadores/36','HP 30274H con cargador 95638T','O',29,'63748R',NULL),(37,'OrdenadorPortátil','/biblioteca/29/ordenadores/37','HP 80243W con cargador 41975K','O',29,'52630F',NULL),(38,'OrdenadorPortátil','/biblioteca/29/ordenadores/38','HP 96103L con cargador 34890N','O',29,'28506X',NULL),(39,'OrdenadorPortátil','/biblioteca/29/ordenadores/39','HP 14379G con cargador 97052M','O',29,'51234B',NULL),(40,'OrdenadorFijo','/biblioteca/29/ordenadores/40','Apple iMAC 69420J con ratón 83157D','O',29,'24786H',NULL),(41,'OrdenadorFijo','/biblioteca/29/ordenadores/41','Apple iMAC 52361T con ratón 40897K','O',29,'97543Z',NULL),(42,'OrdenadorFijo','/biblioteca/29/ordenadores/42','Apple iMAC 15076R con ratón 69480F','O',29,'23875P',NULL),(43,'Diseño de estructuras de acero para edificios','/biblioteca/23/libros/43','Autor: Federico Mazzolani, Editorial: Alinea Editrice, Año: 2013','L',23,NULL,5873),(44,'Introducción a la ingeniería biomédica','/biblioteca/23/libros/44','Autor: John Enderle, Editorial: Pearson, Año: 2012','L',23,NULL,132),(45,'Programación en Python para ingeniería','/biblioteca/23/libros/45','Autor: Michael Dawson, Editorial: Anaya Multimedia, Año: 2017','L',23,NULL,7109),(46,'Ingeniería de sistemas de telecomunicaciones','/biblioteca/23/libros/46','Autor: Juan José Ramos, Editorial: Díaz de Santos, Año: 2011','L',23,NULL,9870),(47,'Ingeniería del software','/biblioteca/23/libros/47','Autor: Roger Pressman, Editorial: McGraw-Hill, Año: 2014','L',23,NULL,4265),(48,'Introducción a la arquitectura','/biblioteca/23/libros/48','Autor: Francis D. K. Ching, Editorial: Gustavo Gili, Año: 2014','L',23,NULL,1238),(49,'Biomecánica del movimiento humano','/biblioteca/23/libros/49','Autor: Benno M. Nigg, Editorial: Paidotribo, Año: 2010','L',23,NULL,3591),(50,'Sistemas digitales y microprocesadores','/biblioteca/23/libros/50','Autor: Francisco Javier Moreno Arboleda, Editorial: Garceta, Año: 2017','L',23,NULL,8754),(51,'Telecomunicaciones: transmisión de voz y datos','/biblioteca/23/libros/51','Autor: Sergio Benedetto, Editorial: Ediciones Díaz de Santos, Año: 2013','L',23,NULL,5964),(52,'Redes de computadoras: un enfoque descendente','/biblioteca/23/libros/52','Autor: James F. Kurose, Editorial: Pearson, Año: 2015','L',23,NULL,4321),(53,'Cálculo diferencial e integral','/biblioteca/23/libros/53','Autor: James Stewart, Editorial: Cengage Learning, Año: 2016','L',23,NULL,7092),(54,'Procesamiento de señales digitales','/biblioteca/23/libros/54','Autor: Richard G. Lyons, Editorial: Pearson, Año: 2012','L',23,NULL,6532),(55,'Introducción al análisis estructural','/biblioteca/23/libros/55','Autor: C. S. Reddy, Editorial: McGraw Hill, Año: 2016','L',23,NULL,7241),(56,'Inteligencia artificial: una aproximación moderna','/biblioteca/23/libros/56','Autor: Stuart Russell, Editorial: Pearson, Año: 2010','L',23,NULL,9212),(57,'Ecuaciones diferenciales','/biblioteca/23/libros/57','Autor: Dennis G. Zill, Editorial: Cengage Learning, Año: 2014','L',23,NULL,7352),(58,'Atlas de anatomía humana','/biblioteca/24/libros/58','Autor: Frank H. Netter, Editorial: Elsevier, Año: 2014','L',24,NULL,567),(59,'Fisiología médica','/biblioteca/24/libros/59','Autor: Arthur C. Guyton, Editorial: Elsevier, Año: 2016','L',24,NULL,2345),(60,'Manual de diagnóstico y estadístico de los trastornos mentales','/biblioteca/24/libros/60','Autor: American Psychiatric Association, Editorial: Panamericana, Año: 2014','L',24,NULL,1278),(61,'Fundamentos de enfermería','/biblioteca/24/libros/61','Autor: Patricia A. Potter, Editorial: Elsevier, Año: 2016','L',24,NULL,7524),(62,'Bioquímica médica','/biblioteca/24/libros/62','Autor: John W. Baynes, Editorial: Elsevier, Año: 2015','L',24,NULL,6342),(63,'Farmacología médica','/biblioteca/24/libros/63','Autor: Mario García-Pérez, Editorial: Panamericana, Año: 2017','L',24,NULL,3577),(64,'Cuidados intensivos en enfermería','/biblioteca/24/libros/64','Autor: Kathy L. McGilton, Editorial: Elsevier, Año: 2018','L',24,NULL,9113),(65,'Manual de terapéutica médica','/biblioteca/24/libros/65','Autor: Bartolomé Oliver, Editorial: Elsevier, Año: 2015','L',24,NULL,5698),(66,'Atlas de anatomía topográfica de los animales domésticos','/biblioteca/24/libros/66','Autor: Peter Popesko, Editorial: Elsevier, Año: 2017','L',24,NULL,4231),(67,'Patología estructural y funcional','/biblioteca/24/libros/67','Autor: Richard N. Mitchell, Editorial: Elsevier, Año: 2016','L',24,NULL,2598),(68,'Fisiopatología','/biblioteca/24/libros/68','Autor: Carol Porth, Editorial: Wolters Kluwer, Año: 2015','L',24,NULL,8132),(69,'Psicología médica','/biblioteca/24/libros/69','Autor: Francisco Alonso-Fernández, Editorial: Elsevier, Año: 2017','L',24,NULL,1008),(70,'Manual de enfermería médico-quirúrgica','/biblioteca/24/libros/70','Autor: Sharon L. Lewis, Editorial: Elsevier, Año: 2016','L',24,NULL,3732),(71,'Derecho Internacional Público','/biblioteca/25/libros/71','Autor: Juan Pérez, Editorial: Ediciones Jurídicas, Año: 2018','L',25,NULL,3125),(72,'Derecho Mercantil','/biblioteca/25/libros/72','Autor: Ana Gómez, Editorial: Legis, Año: 2020','L',25,NULL,7839),(73,'Derecho Procesal Civil','/biblioteca/25/libros/73','Autor: Luis García, Editorial: Thomson Reuters, Año: 2019','L',25,NULL,9324),(74,'Derecho Laboral','/biblioteca/25/libros/74','Autor: María Hernández, Editorial: Civitas, Año: 2017','L',25,NULL,1589),(75,'Derecho Fiscal','/biblioteca/25/libros/75','Autor: José Martínez, Editorial: Aranzadi, Año: 2022','L',25,NULL,5623),(76,'Derecho Administrativo','/biblioteca/25/libros/76','Autor: Juan González, Editorial: Dykinson, Año: 2021','L',25,NULL,8621),(77,'Derecho Penal','/biblioteca/25/libros/77','Autor: Carlos Sánchez, Editorial: Bosch, Año: 2019','L',25,NULL,2115),(78,'Derecho Civil','/biblioteca/25/libros/78','Autor: María Rodríguez, Editorial: Sepín, Año: 2018','L',25,NULL,6456),(79,'Derecho Constitucional','/biblioteca/25/libros/79','Autor: Alberto Pérez, Editorial: Tirant lo Blanch, Año: 2020','L',25,NULL,4723),(80,'Derecho de la Empresa','/biblioteca/25/libros/80','Autor: Luisa Fernández, Editorial: La Ley, Año: 2017','L',25,NULL,9234),(81,'Derecho del Trabajo','/biblioteca/25/libros/81','Autor: Antonio Ruiz, Editorial: Iustel, Año: 2019','L',25,NULL,1234),(82,'Derecho Tributario','/biblioteca/25/libros/82','Autor: Pablo Sánchez, Editorial: Tecnos, Año: 2018','L',25,NULL,5876),(83,'Derecho Marítimo','/biblioteca/25/libros/83','Autor: Rafael López, Editorial: Lex Nova, Año: 2022','L',25,NULL,3987),(84,'Derecho Penal Económico','/biblioteca/25/libros/84','Autor: Ana García, Editorial: Marcial Pons, Año: 2021','L',25,NULL,8751),(85,'Derecho Bancario','/biblioteca/25/libros/85','Autor: Pedro Fernández, Editorial: El Derecho, Año: 2019','L',25,NULL,7123),(86,'Economía de la empresa','/biblioteca/26/libros/86','Autor: Juan Pérez. Editorial: McGraw-Hill. Año: 2019','L',26,NULL,476019),(87,'Macroeconomía','/biblioteca/26/libros/87','Autor: Ana Gómez. Editorial: Pearson. Año: 2021','L',26,NULL,154708),(88,'Finanzas Corporativas','/biblioteca/26/libros/88','Autor: Carlos García. Editorial: Prentice Hall. Año: 2020','L',26,NULL,598470),(89,'Estadística para Economistas','/biblioteca/26/libros/89','Autor: Laura Rodríguez. Editorial: McGraw-Hill. Año: 2018','L',26,NULL,125345),(90,'Comercio Internacional','/biblioteca/26/libros/90','Autor: Miguel Álvarez. Editorial: Pearson. Año: 2022','L',26,NULL,328416),(91,'Marketing Estratégico','/biblioteca/26/libros/91','Autor: Marta Sánchez. Editorial: Prentice Hall. Año: 2019','L',26,NULL,942306),(92,'Microeconomía','/biblioteca/26/libros/92','Autor: Jorge Fernández. Editorial: McGraw-Hill. Año: 2021','L',26,NULL,751092),(93,'Contabilidad y Finanzas para no Financieros','/biblioteca/26/libros/93','Autor: Ana García. Editorial: Pearson. Año: 2020','L',26,NULL,264937),(94,'Análisis de Inversiones','/biblioteca/26/libros/94','Autor: Luis Martínez. Editorial: Prentice Hall. Año: 2018','L',26,NULL,372849),(95,'Farmacología y Terapéutica en Nutrición Clínica','/biblioteca/27/libros/95','Autor: Ernesto Pollitzer, Editorial: Médica Panamericana, Año: 2016','L',27,NULL,4327),(96,'Manual de Farmacología Práctica','/biblioteca/27/libros/96','Autor: Vicente Giner Burillo, Editorial: Elsevier, Año: 2018','L',27,NULL,16145),(97,'Introducción a la Farmacia','/biblioteca/27/libros/97','Autor: Luis Calvo Calvo, Editorial: Elsevier, Año: 2015','L',27,NULL,42106),(98,'Farmacología en Enfermería','/biblioteca/27/libros/98','Autor: Sonia Gómez Rey, Editorial: Elsevier, Año: 2019','L',27,NULL,8634),(99,'Farmacia Hospitalaria','/biblioteca/27/libros/99','Autor: Carmen Fernández Sabater, Editorial: Elsevier, Año: 2015','L',27,NULL,23179),(100,'Farmacología Básica y Clínica','/biblioteca/27/libros/100','Autor: Bertram G. Katzung, Editorial: McGraw-Hill, Año: 2018','L',27,NULL,51430),(101,'Farmacocinética y Farmacodinamia','/biblioteca/27/libros/101','Autor: Alberto García García, Editorial: Elsevier, Año: 2016','L',27,NULL,94810),(102,'Farmacoterapia en Medicina de Urgencias','/biblioteca/27/libros/102','Autor: Ana Ferrández Ocaña, Editorial: Elsevier, Año: 2017','L',27,NULL,7489),(103,'Manual de Técnicas de Laboratorio en Farmacia','/biblioteca/27/libros/103','Autor: Josep L. Andrés, Editorial: Elsevier, Año: 2019','L',27,NULL,34392),(104,'Farmacogenómica','/biblioteca/27/libros/104','Autor: Francisco Abad Santos, Editorial: Elsevier, Año: 2015','L',27,NULL,3819),(105,'Introducción a la Nutrición','/biblioteca/27/libros/105','Autor: Gloria Peres Paniagua, Editorial: Médica Panamericana, Año: 2019','L',27,NULL,22572),(106,'Manual de Farmacoterapia del Dolor','/biblioteca/27/libros/106','Autor: Juana Mª González González, Editorial: Elsevier, Año: 2017','L',27,NULL,32671),(107,'La comunicación en la era digital','/biblioteca/29/libros/107','Autor: Juan Pérez, Año: 2019, Editorial: Ediciones Comunicar','L',29,NULL,523),(108,'El periodismo de investigación','/biblioteca/29/libros/108','Autor: María López, Año: 2020, Editorial: La Voz del Sur','L',29,NULL,874),(109,'Marketing estratégico','/biblioteca/29/libros/109','Autor: Luis García, Año: 2018, Editorial: Pearson','L',29,NULL,1256),(110,'Comunicación corporativa y relaciones públicas','/biblioteca/29/libros/110','Autor: Ana Martínez, Año: 2017, Editorial: Planeta','L',29,NULL,789),(111,'La publicidad en la sociedad de consumo','/biblioteca/29/libros/111','Autor: Marta Sánchez, Año: 2019, Editorial: Ediciones Síntesis','L',29,NULL,2365),(112,'Periodismo deportivo','/biblioteca/29/libros/112','Autor: José Gómez, Año: 2021, Editorial: Marca','L',29,NULL,956),(113,'Marketing digital','/biblioteca/29/libros/113','Autor: Carolina Rodríguez, Año: 2018, Editorial: McGraw-Hill','L',29,NULL,1457),(114,'La comunicación política en las redes sociales','/biblioteca/29/libros/114','Autor: David Sánchez, Año: 2020, Editorial: Alianza Editorial','L',29,NULL,356),(115,'Periodismo cultural','/biblioteca/29/libros/115','Autor: Eva García, Año: 2019, Editorial: Ediciones B','L',29,NULL,3245),(116,'La publicidad en la era digital','/biblioteca/29/libros/116','Autor: Antonio Fernández, Año: 2021, Editorial: Planeta','L',29,NULL,874);
/*!40000 ALTER TABLE `recursoextra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `reservaID` int NOT NULL AUTO_INCREMENT,
  `url` varchar(40) DEFAULT NULL,
  `usuarioID` int DEFAULT NULL,
  `elementoReservableID` int DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`reservaID`),
  KEY `usuarioID` (`usuarioID`),
  KEY `elementoReservableID` (`elementoReservableID`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`usuarioID`) REFERENCES `usuario` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`elementoReservableID`) REFERENCES `elementoreservable` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservaextra`
--

DROP TABLE IF EXISTS `reservaextra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservaextra` (
  `RecursoExtraID` int NOT NULL,
  `reservaID` int NOT NULL,
  PRIMARY KEY (`reservaID`,`RecursoExtraID`),
  KEY `RecursoExtraID` (`RecursoExtraID`),
  CONSTRAINT `reservaextra_ibfk_1` FOREIGN KEY (`RecursoExtraID`) REFERENCES `recursoextra` (`id`) ON DELETE CASCADE,
  CONSTRAINT `reservaextra_ibfk_2` FOREIGN KEY (`reservaID`) REFERENCES `reserva` (`reservaID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservaextra`
--

LOCK TABLES `reservaextra` WRITE;
/*!40000 ALTER TABLE `reservaextra` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservaextra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url` varchar(40) DEFAULT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `grado` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,NULL,'Fernando','Ortiz de Pedro','GISIADE'),(2,NULL,'Pedro','Ortiz de Zúñiga','GISIADE'),(3,NULL,'Álvaro','Córdoba Moreno','GISITEL'),(4,NULL,'Ignacio','Goiriena Solana','GISIADE'),(5,NULL,'Javier','Sancerni Nozaleda','GISI');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-11 15:15:23
