CREATE DATABASE  IF NOT EXISTS `gestion` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestion`;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biblioteca`
--

LOCK TABLES `biblioteca` WRITE;
/*!40000 ALTER TABLE `biblioteca` DISABLE KEYS */;
INSERT INTO `biblioteca` VALUES (1,'/bibliotecas/1','MTPEPS','Biblioteca de la EPS en campus Monteprincipe','2023-09-04 08:25:00','2023-09-08 20:35:00'),(2,'/bibliotecas/2','MEDEPS','Biblioteca de la Facultad de Medicina en campus Monteprincipe','2023-09-04 08:25:00','2023-09-08 20:35:00');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementoreservable`
--

LOCK TABLES `elementoreservable` WRITE;
/*!40000 ALTER TABLE `elementoreservable` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recursoextra`
--

LOCK TABLES `recursoextra` WRITE;
/*!40000 ALTER TABLE `recursoextra` DISABLE KEYS */;
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

-- Dump completed on 2023-02-24 11:05:31
