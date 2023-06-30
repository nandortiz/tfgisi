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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biblioteca`
--

LOCK TABLES `biblioteca` WRITE;
/*!40000 ALTER TABLE `biblioteca` DISABLE KEYS */;
INSERT INTO `biblioteca` VALUES (38,'/bibliotecas/38','CEUEPS','Biblioteca de la Escuela Politécnica Superior de la Universidad San Pablo CEU, Campus Montepríncipe'),(39,'/bibliotecas/39','CEUMED','Biblioteca de la Facultad de Medicina de la Universidad San Pablo CEU, Campus Montepríncipe'),(40,'/bibliotecas/40','CEUDER','Biblioteca de la Facultad de Derecho de la Universidad San Pablo CEU, Campus Julián Romea'),(41,'/bibliotecas/41','CEUECO','Biblioteca de la Facultad de Económicas de la Universidad San Pablo CEU, Campus Julián Romea'),(42,'/bibliotecas/42','CEUFAR','Biblioteca de la Facultad de Farmacia de la Universidad San Pablo CEU, Campus Montepríncipe'),(43,'/bibliotecas/43','CEUHYC','Biblioteca de la Facultad de Hum. y Com. de la Universidad San Pablo CEU, Campus Julián Romea'),(45,'/bibliotecas/45','rr','rr');
/*!40000 ALTER TABLE `biblioteca` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=368 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementoreservable`
--

LOCK TABLES `elementoreservable` WRITE;
/*!40000 ALTER TABLE `elementoreservable` DISABLE KEYS */;
INSERT INTO `elementoreservable` VALUES (212,'/biblioteca/38/salas/212','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,4,NULL),(213,'/biblioteca/38/salas/213','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,4,NULL),(214,'/biblioteca/38/salas/214','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,4,NULL),(215,'/biblioteca/38/salas/215','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,4,NULL),(216,'/biblioteca/38/salas/216','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,4,NULL),(217,'/biblioteca/38/salas/217','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',38,6,NULL),(218,'/biblioteca/39/salas/218','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',39,4,NULL),(219,'/biblioteca/39/salas/219','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',39,4,NULL),(220,'/biblioteca/39/salas/220','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',39,4,NULL),(221,'/biblioteca/39/salas/221','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',39,8,NULL),(222,'/biblioteca/40/salas/222','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(223,'/biblioteca/40/salas/223','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(224,'/biblioteca/40/salas/224','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(225,'/biblioteca/40/salas/225','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(226,'/biblioteca/40/salas/226','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(227,'/biblioteca/40/salas/227','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,4,NULL),(228,'/biblioteca/40/salas/228','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,6,NULL),(229,'/biblioteca/40/salas/229','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,6,NULL),(230,'/biblioteca/40/salas/230','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',40,6,NULL),(231,'/biblioteca/41/salas/231','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',41,4,NULL),(232,'/biblioteca/41/salas/232','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',41,4,NULL),(233,'/biblioteca/41/salas/233','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',41,4,NULL),(234,'/biblioteca/42/salas/234','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,4,NULL),(235,'/biblioteca/42/salas/235','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,4,NULL),(236,'/biblioteca/42/salas/236','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,4,NULL),(237,'/biblioteca/42/salas/237','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,6,NULL),(238,'/biblioteca/42/salas/238','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,6,NULL),(239,'/biblioteca/42/salas/239','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',42,6,NULL),(240,'/biblioteca/43/salas/240','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(241,'/biblioteca/43/salas/241','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(242,'/biblioteca/43/salas/242','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(243,'/biblioteca/43/salas/243','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(244,'/biblioteca/43/salas/244','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(245,'/biblioteca/43/salas/245','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,4,NULL),(246,'/biblioteca/43/salas/246','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,6,NULL),(247,'/biblioteca/43/salas/247','Sala de trabajos en grupo. Extras: enchufes, A/C, TV','S',43,6,NULL),(258,'/biblioteca/38/puestos/258','Puesto de estudio individual. Zona: piso inferior. Extras: enchufes, ventana','P',38,NULL,'Zona: entrada.'),(259,'/biblioteca/38/puestos/259','Puesto de estudio individual. Zona: piso inferior. Extras: enchufes, ventana','P',38,NULL,'Zona: entrada.'),(260,'/biblioteca/38/puestos/260','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: entrada.'),(261,'/biblioteca/38/puestos/261','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: entrada.'),(262,'/biblioteca/38/puestos/262','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: anexa.'),(263,'/biblioteca/38/puestos/263','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: anexa.'),(264,'/biblioteca/38/puestos/264','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: anexa.'),(265,'/biblioteca/38/puestos/265','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: anexa.'),(266,'/biblioteca/38/puestos/266','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: anexa.'),(267,'/biblioteca/38/puestos/267','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: inferior.'),(268,'/biblioteca/38/puestos/268','Puesto de estudio individual. Zona: anexa. Extras: enchufes, ventana','P',38,NULL,'Zona: inferior.'),(269,'/biblioteca/38/puestos/269','Puesto de estudio individual.','P',38,NULL,'Zona: inferior.'),(270,'/biblioteca/38/puestos/270','Puesto de estudio individual.','P',38,NULL,'Zona: inferior.'),(271,'/biblioteca/38/puestos/271','Puesto de estudio individual.','P',38,NULL,'Zona: inferior.'),(272,'/biblioteca/39/puestos/272','Puesto de estudio individual.','P',39,NULL,'Zona: entrada.'),(273,'/biblioteca/39/puestos/273','Puesto de estudio individual.','P',39,NULL,'Zona: entrada.'),(274,'/biblioteca/39/puestos/274','Puesto de estudio individual.','P',39,NULL,'Zona: entrada.'),(275,'/biblioteca/39/puestos/275','Puesto de estudio individual.','P',39,NULL,'Zona: entrada.'),(276,'/biblioteca/39/puestos/276','Puesto de estudio individual.','P',39,NULL,'Zona: entrada.'),(277,'/biblioteca/39/puestos/277','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(278,'/biblioteca/39/puestos/278','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(279,'/biblioteca/39/puestos/279','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(280,'/biblioteca/39/puestos/280','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(281,'/biblioteca/39/puestos/281','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(282,'/biblioteca/39/puestos/282','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(283,'/biblioteca/39/puestos/283','Puesto de estudio individual.','P',39,NULL,'Zona: anexa.'),(284,'/biblioteca/39/puestos/284','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(285,'/biblioteca/39/puestos/285','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(286,'/biblioteca/39/puestos/286','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(287,'/biblioteca/39/puestos/287','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(288,'/biblioteca/39/puestos/288','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(289,'/biblioteca/39/puestos/289','Puesto de estudio individual.','P',39,NULL,'Zona: inferior.'),(290,'/biblioteca/40/puestos/290','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(291,'/biblioteca/40/puestos/291','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(292,'/biblioteca/40/puestos/292','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(293,'/biblioteca/40/puestos/293','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(294,'/biblioteca/40/puestos/294','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(295,'/biblioteca/40/puestos/295','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(296,'/biblioteca/40/puestos/296','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(297,'/biblioteca/40/puestos/297','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(298,'/biblioteca/40/puestos/298','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(299,'/biblioteca/40/puestos/299','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(300,'/biblioteca/40/puestos/300','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(301,'/biblioteca/40/puestos/301','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(302,'/biblioteca/40/puestos/302','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(303,'/biblioteca/40/puestos/303','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(304,'/biblioteca/40/puestos/304','Puesto de estudio individual.','P',40,NULL,'Zona: entrada.'),(305,'/biblioteca/41/puestos/305','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(306,'/biblioteca/41/puestos/306','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(307,'/biblioteca/41/puestos/307','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(308,'/biblioteca/41/puestos/308','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(309,'/biblioteca/41/puestos/309','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(310,'/biblioteca/41/puestos/310','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(311,'/biblioteca/41/puestos/311','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(312,'/biblioteca/41/puestos/312','Puesto de estudio individual.','P',41,NULL,'Zona: entrada.'),(313,'/biblioteca/41/puestos/313','Puesto de estudio individual.','P',41,NULL,'Zona: estanterías.'),(314,'/biblioteca/41/puestos/314','Puesto de estudio individual.','P',41,NULL,'Zona: estanterías.'),(315,'/biblioteca/41/puestos/315','Puesto de estudio individual.','P',41,NULL,'Zona: estanterías.'),(316,'/biblioteca/41/puestos/316','Puesto de estudio individual.','P',41,NULL,'Zona: estanterías.'),(317,'/biblioteca/41/puestos/317','Puesto de estudio individual.','P',41,NULL,'Zona: estanterías.'),(318,'/biblioteca/42/puestos/318','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(319,'/biblioteca/42/puestos/319','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(320,'/biblioteca/42/puestos/320','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(321,'/biblioteca/42/puestos/321','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(322,'/biblioteca/42/puestos/322','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(323,'/biblioteca/42/puestos/323','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(324,'/biblioteca/42/puestos/324','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(325,'/biblioteca/42/puestos/325','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(326,'/biblioteca/42/puestos/326','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(327,'/biblioteca/42/puestos/327','Puesto de estudio individual.','P',42,NULL,'Zona: derecha.'),(328,'/biblioteca/42/puestos/328','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(329,'/biblioteca/42/puestos/329','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(330,'/biblioteca/42/puestos/330','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(331,'/biblioteca/42/puestos/331','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(332,'/biblioteca/42/puestos/332','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(333,'/biblioteca/42/puestos/333','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(334,'/biblioteca/42/puestos/334','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(335,'/biblioteca/42/puestos/335','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(336,'/biblioteca/42/puestos/336','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(337,'/biblioteca/42/puestos/337','Puesto de estudio individual.','P',42,NULL,'Zona: izquierda.'),(338,'/biblioteca/43/puestos/338','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.'),(339,'/biblioteca/43/puestos/339','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.'),(340,'/biblioteca/43/puestos/340','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.'),(341,'/biblioteca/43/puestos/341','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.'),(342,'/biblioteca/43/puestos/342','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.'),(343,'/biblioteca/43/puestos/343','Puesto de estudio individual.','P',43,NULL,'Zona: entrada.');
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
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recursoextra`
--

LOCK TABLES `recursoextra` WRITE;
/*!40000 ALTER TABLE `recursoextra` DISABLE KEYS */;
INSERT INTO `recursoextra` VALUES (126,'Apple iMac.','/biblioteca/38/ordenadores/126','Torre con teclado y ratón.','O',38,'f34d45',NULL),(127,'Apple iMac.','/biblioteca/38/ordenadores/127','Torre con teclado y ratón.','O',38,'874yr2u',NULL),(128,'Apple iMac.','/biblioteca/38/ordenadores/128','Torre con teclado y ratón.','O',38,'y4vw6b',NULL),(129,'Apple iMac.','/biblioteca/39/ordenadores/129','Torre con teclado y ratón.','O',39,'7h8bfd',NULL),(130,'Apple iMac.','/biblioteca/39/ordenadores/130','Torre con teclado y ratón.','O',39,'9k7086',NULL),(131,'Apple iMac.','/biblioteca/40/ordenadores/131','Torre con teclado y ratón.','O',40,'4g53qc',NULL),(132,'Apple iMac.','/biblioteca/40/ordenadores/132','Torre con teclado y ratón.','O',40,'5f345b',NULL),(133,'Apple iMac.','/biblioteca/40/ordenadores/133','Torre con teclado y ratón.','O',40,'5f3k8b',NULL),(134,'Apple iMac.','/biblioteca/40/ordenadores/134','Torre con teclado y ratón.','O',40,'86h5gg',NULL),(135,'Apple iMac.','/biblioteca/41/ordenadores/135','Torre con teclado y ratón.','O',41,'g64v5c',NULL),(136,'Apple iMac.','/biblioteca/41/ordenadores/136','Torre con teclado y ratón.','O',41,'2s3x36',NULL),(137,'Apple iMac.','/biblioteca/41/ordenadores/137','Torre con teclado y ratón.','O',41,'0gi58t',NULL),(138,'Apple iMac.','/biblioteca/41/ordenadores/138','Torre con teclado y ratón.','O',41,'65ef4cx',NULL),(139,'Apple iMac.','/biblioteca/42/ordenadores/139','Torre con teclado y ratón.','O',42,'475vs2',NULL),(140,'Apple iMac.','/biblioteca/42/ordenadores/140','Torre con teclado y ratón.','O',42,'35xeq3',NULL),(141,'Apple iMac.','/biblioteca/43/ordenadores/141','Torre con teclado y ratón.','O',43,'cqxx2',NULL),(142,'Apple iMac.','/biblioteca/43/ordenadores/142','Torre con teclado y ratón.','O',43,'c53b5',NULL),(143,'Apple iMac.','/biblioteca/43/ordenadores/143','Torre con teclado y ratón.','O',43,'08n76',NULL),(144,'HP Pavilion.','/biblioteca/38/ordenadores/144','Portátil con cargador.','O',38,'b4wvw65',NULL),(145,'HP Pavilion.','/biblioteca/38/ordenadores/145','Portátil con cargador.','O',38,'58hwbef',NULL),(146,'HP Pavilion.','/biblioteca/38/ordenadores/146','Portátil con cargador.','O',38,'857f34',NULL),(147,'HP Pavilion.','/biblioteca/38/ordenadores/147','Portátil con cargador.','O',38,'98nt6',NULL),(148,'HP Pavilion.','/biblioteca/38/ordenadores/148','Portátil con cargador.','O',38,'x35f4ccxq',NULL),(149,'HP Pavilion.','/biblioteca/39/ordenadores/149','Portátil con cargador.','O',39,'67b4wv6',NULL),(150,'HP Pavilion.','/biblioteca/39/ordenadores/150','Portátil con cargador.','O',39,'m896n7',NULL),(151,'HP Pavilion.','/biblioteca/39/ordenadores/151','Portátil con cargador.','O',39,'m54vbv',NULL),(152,'HP Pavilion.','/biblioteca/39/ordenadores/152','Portátil con cargador.','O',39,'vcv643',NULL),(153,'HP Pavilion.','/biblioteca/39/ordenadores/153','Portátil con cargador.','O',39,'0y7j6',NULL),(154,'HP Pavilion.','/biblioteca/40/ordenadores/154','Portátil con cargador.','O',40,'7v765b',NULL),(155,'HP Pavilion.','/biblioteca/40/ordenadores/155','Portátil con cargador.','O',40,'n689j6',NULL),(156,'HP Pavilion.','/biblioteca/40/ordenadores/156','Portátil con cargador.','O',40,'646wbv',NULL),(157,'HP Pavilion.','/biblioteca/40/ordenadores/157','Portátil con cargador.','O',40,'n65b4',NULL),(158,'HP Pavilion.','/biblioteca/40/ordenadores/158','Portátil con cargador.','O',40,'c42ddx',NULL),(159,'HP Pavilion.','/biblioteca/40/ordenadores/159','Portátil con cargador.','O',40,'g423fc',NULL),(160,'HP Pavilion.','/biblioteca/41/ordenadores/160','Portátil con cargador.','O',41,'n67b5',NULL),(161,'HP Pavilion.','/biblioteca/41/ordenadores/161','Portátil con cargador.','O',41,'nfb785',NULL),(162,'HP Pavilion.','/biblioteca/41/ordenadores/162','Portátil con cargador.','O',41,'c436f3',NULL),(163,'HP Pavilion.','/biblioteca/41/ordenadores/163','Portátil con cargador.','O',41,'697vrs',NULL),(164,'HP Pavilion.','/biblioteca/41/ordenadores/164','Portátil con cargador.','O',41,'5g6e6',NULL),(165,'HP Pavilion.','/biblioteca/42/ordenadores/165','Portátil con cargador.','O',42,'b8467',NULL),(166,'HP Pavilion.','/biblioteca/42/ordenadores/166','Portátil con cargador.','O',42,'n968h5',NULL),(167,'HP Pavilion.','/biblioteca/42/ordenadores/167','Portátil con cargador.','O',42,'nj68rk',NULL),(168,'HP Pavilion.','/biblioteca/42/ordenadores/168','Portátil con cargador.','O',42,'6975g4',NULL),(169,'HP Pavilion.','/biblioteca/42/ordenadores/169','Portátil con cargador.','O',42,'cx2464',NULL),(170,'HP Pavilion.','/biblioteca/42/ordenadores/170','Portátil con cargador.','O',42,'c356cv',NULL),(171,'HP Pavilion.','/biblioteca/43/ordenadores/171','Portátil con cargador.','O',43,'mn786',NULL),(172,'HP Pavilion.','/biblioteca/43/ordenadores/172','Portátil con cargador.','O',43,'b756vb',NULL),(173,'HP Pavilion.','/biblioteca/43/ordenadores/173','Portátil con cargador.','O',43,'v457b',NULL),(174,'HP Pavilion.','/biblioteca/43/ordenadores/174','Portátil con cargador.','O',43,'bn86b4w',NULL),(176,'Arquitectura e interiorismo','/biblioteca/38/libros/176','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,3234),(177,'Aprende de estructuras','/biblioteca/38/libros/177','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,5323),(178,'Sistemas operativos','/biblioteca/38/libros/178','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,8453),(179,'Matemáticas discretas I','/biblioteca/38/libros/179','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,9083),(180,'Matemáticas discretas II','/biblioteca/38/libros/180','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,9834),(181,'Ingeniería biomédica para principiantes','/biblioteca/38/libros/181','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',38,NULL,8348),(182,'Los mitos de Anatomía de Grey','/biblioteca/39/libros/182','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',39,NULL,4359),(183,'Tejidos des cuerpo humano','/biblioteca/39/libros/183','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',39,NULL,6584),(184,'Ligamentos, tendones y musculatura','/biblioteca/39/libros/184','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',39,NULL,8357),(185,'Fisioterapia básica','/biblioteca/39/libros/185','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',39,NULL,3720),(186,'Derecho penal','/biblioteca/40/libros/186','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',40,NULL,5236),(187,'Derecho laboral','/biblioteca/40/libros/187','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',40,NULL,1231),(188,'Derecho fiscal','/biblioteca/40/libros/188','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',40,NULL,8695),(189,'La ética en el derecho','/biblioteca/40/libros/189','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',40,NULL,3624),(190,'Cómo convertirse en abogado','/biblioteca/40/libros/190','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',40,NULL,4251),(191,'Fundamentos de la economía','/biblioteca/41/libros/191','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,3251),(192,'Macroeconomía','/biblioteca/41/libros/192','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,7322),(193,'Microeconomía','/biblioteca/41/libros/193','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,8534),(194,'Matemáticas financieras','/biblioteca/41/libros/194','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,9854),(195,'Aprender a invertir','/biblioteca/41/libros/195','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,9858),(196,'En Bolsa, no es oro todo lo que reluce','/biblioteca/41/libros/196','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',41,NULL,2325),(197,'La vida en la farmacia','/biblioteca/42/libros/197','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',42,NULL,4722),(198,'Farmacia y nutrición','/biblioteca/42/libros/198','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',42,NULL,5311),(199,'Biología general','/biblioteca/42/libros/199','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',42,NULL,2241),(200,'Laboratorios de farmacia','/biblioteca/42/libros/200','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',42,NULL,9458),(201,'Farmacia: casos prácticos','/biblioteca/42/libros/201','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',42,NULL,4462),(202,'Periodismo: nociones básicas','/biblioteca/43/libros/202','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',43,NULL,1174),(203,'Sociología I','/biblioteca/43/libros/203','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',43,NULL,3523),(204,'Sociología II','/biblioteca/43/libros/204','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',43,NULL,7553),(205,'Publicidad y relaciones públicas','/biblioteca/43/libros/205','Autor: Xxxxxx Xxxxx. Editorial: Yyyyyy Yyyyy.','L',43,NULL,9088),(217,'ttt','/biblioteca/38/libros/217','ttt','L',38,NULL,3333);
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (52,'/reservas/52',23,270,'2023-06-23 08:00:00'),(53,'/reservas/53',23,270,'2000-01-01 08:00:00'),(61,'/reservas/61',24,343,'2023-05-12 12:00:00'),(62,'/reservas/62',23,342,'2023-05-12 12:00:00'),(63,'/reservas/63',24,343,'2023-06-18 20:00:00'),(64,'/reservas/64',29,217,'2023-06-05 10:00:00'),(65,'/reservas/65',30,260,'2023-06-05 10:00:00');
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
INSERT INTO `reservaextra` VALUES (130,63),(162,62),(190,63),(190,65);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (13,'/usuarios/13','Fernando','Ortiz de Pedro','GISI+ADE'),(14,'/usuarios/14','Ignacio','Goiriena Solana','GISI+ADE'),(15,'/usuarios/15','Javier','Sancerni Nozaleda','GISI'),(16,'/usuarios/16','Alejandro','Gutiérrez Vivancos','GISI'),(17,'/usuarios/17','Miguel','Ramos López-Quesada','GISI'),(18,'/usuarios/18','Álvaro','Córdoba Moreno','GISI+TEL'),(19,'/usuarios/19','Rodrigo','Llorente Morales','GADE'),(23,'/usuarios/23','usuario','preuba','ISI'),(24,'/usuarios/24','aaaa','eeee','ISI'),(27,'/usuarios/27','Luis','sdsddsd','qwe'),(28,'/usuarios/28','Luis','sdsddsd','qwe'),(29,'/usuarios/29','Luis','Pepe','SYGIC'),(30,'/usuarios/30','rr','rr','tt');
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

-- Dump completed on 2023-06-30 10:32:38
