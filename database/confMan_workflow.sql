-- MariaDB dump 10.19  Distrib 10.5.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: confMan_workflow
-- ------------------------------------------------------
-- Server version	10.5.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `confMan_workflow`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `confMan_workflow` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `confMan_workflow`;

--
-- Table structure for table `Cambio`
--

DROP TABLE IF EXISTS `Cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cambio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FECHA` datetime DEFAULT NULL,
  `ID_SOLICITUD_CAMBIO` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Cambio_FK` (`ID_SOLICITUD_CAMBIO`),
  CONSTRAINT `Cambio_FK` FOREIGN KEY (`ID_SOLICITUD_CAMBIO`) REFERENCES `Solicitud_cambio` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cambio`
--

LOCK TABLES `Cambio` WRITE;
/*!40000 ALTER TABLE `Cambio` DISABLE KEYS */;
INSERT INTO `Cambio` VALUES (4,'2021-07-06 20:20:42',13),(5,'2021-07-06 22:02:54',1),(7,'2021-07-07 01:21:15',14),(8,'2021-07-07 02:54:31',16);
/*!40000 ALTER TABLE `Cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cargo_usuario`
--

DROP TABLE IF EXISTS `Cargo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cargo_usuario` (
  `ID` int(10) unsigned NOT NULL,
  `CARGO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cargo_usuario`
--

LOCK TABLES `Cargo_usuario` WRITE;
/*!40000 ALTER TABLE `Cargo_usuario` DISABLE KEYS */;
INSERT INTO `Cargo_usuario` VALUES (1,'admin'),(2,'usuario'),(3,'moderador');
/*!40000 ALTER TABLE `Cargo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Documentos`
--

DROP TABLE IF EXISTS `Documentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Documentos` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE_DOCUMENTO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TEXTO` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `FECHA_DOCUMENTO` datetime DEFAULT NULL,
  `ID_ESTADO_CAMBIO` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Documentos_FK` (`ID_ESTADO_CAMBIO`),
  CONSTRAINT `Documentos_FK` FOREIGN KEY (`ID_ESTADO_CAMBIO`) REFERENCES `Estado_cambio` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Documentos`
--

LOCK TABLES `Documentos` WRITE;
/*!40000 ALTER TABLE `Documentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `Documentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estado_cambio`
--

DROP TABLE IF EXISTS `Estado_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estado_cambio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ESTADO` int(10) unsigned DEFAULT NULL,
  `FECHA_INICIO` datetime DEFAULT NULL,
  `FECHA_TERMINO` datetime DEFAULT NULL,
  `ID_USUARIO` int(10) unsigned DEFAULT NULL,
  `ID_CAMBIO` int(10) unsigned DEFAULT NULL,
  `ID_RAMA_REPOSITORIO` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Estado_cambio_FK` (`ID_USUARIO`),
  KEY `Estado_cambio_FK_1` (`ID_RAMA_REPOSITORIO`),
  KEY `Estado_cambio_FK_2` (`ID_CAMBIO`),
  KEY `Estado_cambio_FK_3` (`ESTADO`),
  CONSTRAINT `Estado_cambio_FK` FOREIGN KEY (`ID_USUARIO`) REFERENCES `Usuario` (`ID`),
  CONSTRAINT `Estado_cambio_FK_1` FOREIGN KEY (`ID_RAMA_REPOSITORIO`) REFERENCES `Rama_repositorio` (`ID`),
  CONSTRAINT `Estado_cambio_FK_2` FOREIGN KEY (`ID_CAMBIO`) REFERENCES `Cambio` (`ID`),
  CONSTRAINT `Estado_cambio_FK_3` FOREIGN KEY (`ESTADO`) REFERENCES `Estados_workflow` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado_cambio`
--

LOCK TABLES `Estado_cambio` WRITE;
/*!40000 ALTER TABLE `Estado_cambio` DISABLE KEYS */;
INSERT INTO `Estado_cambio` VALUES (6,1,'2021-07-07 01:48:21','2021-07-07 06:17:35',7,5,1),(7,4,'2021-07-07 02:51:25','2021-07-07 02:52:01',6,7,1),(9,2,'2021-07-07 06:17:53','2021-07-07 06:17:53',6,5,7);
/*!40000 ALTER TABLE `Estado_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estado_solicitud_cambio`
--

DROP TABLE IF EXISTS `Estado_solicitud_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estado_solicitud_cambio` (
  `ID` smallint(5) unsigned NOT NULL,
  `ESTADO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estado_solicitud_cambio`
--

LOCK TABLES `Estado_solicitud_cambio` WRITE;
/*!40000 ALTER TABLE `Estado_solicitud_cambio` DISABLE KEYS */;
INSERT INTO `Estado_solicitud_cambio` VALUES (1,'Pendiente'),(2,'Aceptado'),(3,'Rechazado');
/*!40000 ALTER TABLE `Estado_solicitud_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Estados_workflow`
--

DROP TABLE IF EXISTS `Estados_workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estados_workflow` (
  `ID` int(10) unsigned NOT NULL,
  `ESTADO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Estados_workflow`
--

LOCK TABLES `Estados_workflow` WRITE;
/*!40000 ALTER TABLE `Estados_workflow` DISABLE KEYS */;
INSERT INTO `Estados_workflow` VALUES (1,'Evaluacion'),(2,'Planificacion'),(3,'Implementacion'),(4,'Auditoria');
/*!40000 ALTER TABLE `Estados_workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rama_repositorio`
--

DROP TABLE IF EXISTS `Rama_repositorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rama_repositorio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE_RAMA` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID_REPOSITORIO` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Rama_repositorio_FK` (`ID_REPOSITORIO`),
  CONSTRAINT `Rama_repositorio_FK` FOREIGN KEY (`ID_REPOSITORIO`) REFERENCES `Repositorio` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rama_repositorio`
--

LOCK TABLES `Rama_repositorio` WRITE;
/*!40000 ALTER TABLE `Rama_repositorio` DISABLE KEYS */;
INSERT INTO `Rama_repositorio` VALUES (1,'master',1),(2,'Develop',1),(3,'Release/moduloA',1),(4,'Release/ModuloB',1),(5,'Feature/ModuloA',1),(6,'Master',2),(7,'Develop',2),(8,'Feature/ModuloA',2),(9,'Feature/ModuloA',3);
/*!40000 ALTER TABLE `Rama_repositorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Repositorio`
--

DROP TABLE IF EXISTS `Repositorio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Repositorio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NOMBRE_REPOSITORIO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `URL_REPOSITORIO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Repositorio`
--

LOCK TABLES `Repositorio` WRITE;
/*!40000 ALTER TABLE `Repositorio` DISABLE KEYS */;
INSERT INTO `Repositorio` VALUES (1,'proyectoA','http://proyecto.com'),(2,'ProyectoB','https://github.com'),(3,'ProyectoC','https://gitlab.com');
/*!40000 ALTER TABLE `Repositorio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Solicitud_cambio`
--

DROP TABLE IF EXISTS `Solicitud_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Solicitud_cambio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TITULO_SOLICITUD` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TEXTO` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ESTADO_SOLICITUD` smallint(5) unsigned DEFAULT NULL,
  `ID_USUARIO` int(10) unsigned DEFAULT NULL,
  `ID_REPOSITORIO` int(10) unsigned DEFAULT NULL,
  `FECHA` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Solicitud_cambio_FK` (`ID_USUARIO`),
  KEY `Solicitud_cambio_FK_1` (`ID_REPOSITORIO`),
  KEY `Solicitud_cambio_FK_2` (`ESTADO_SOLICITUD`),
  CONSTRAINT `Solicitud_cambio_FK` FOREIGN KEY (`ID_USUARIO`) REFERENCES `Usuario` (`ID`),
  CONSTRAINT `Solicitud_cambio_FK_1` FOREIGN KEY (`ID_REPOSITORIO`) REFERENCES `Repositorio` (`ID`),
  CONSTRAINT `Solicitud_cambio_FK_2` FOREIGN KEY (`ESTADO_SOLICITUD`) REFERENCES `Estado_solicitud_cambio` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Solicitud_cambio`
--

LOCK TABLES `Solicitud_cambio` WRITE;
/*!40000 ALTER TABLE `Solicitud_cambio` DISABLE KEYS */;
INSERT INTO `Solicitud_cambio` VALUES (1,'cambio1','cambio1',2,6,1,'2021-07-06 00:05:44'),(2,'cambio2','cambio2',2,6,1,'2021-07-06 00:05:51'),(12,'prueba1','probando texto',3,6,1,'2021-07-06 20:16:14'),(13,'probando nueva solicitud','solicitud',2,6,1,'2021-07-06 20:20:39'),(14,'prueba de alex3','alex3',2,8,2,'2021-07-06 20:24:21'),(16,'blabla','blablabl',2,6,1,'2021-07-07 02:54:19');
/*!40000 ALTER TABLE `Solicitud_cambio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NOMBRE` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `APELLIDO` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ID_CARGO` int(10) unsigned DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Usuario_FK` (`ID_CARGO`),
  CONSTRAINT `Usuario_FK` FOREIGN KEY (`ID_CARGO`) REFERENCES `Cargo_usuario` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (6,'juanv','$2a$10$4oqhPgbUIJqxnsAg3DiSz.KWHgZtpzdM5ZPDjxCMzK/XoL0vtsV26','juan','diego',1,'juan@gmail.com'),(7,'alext','$2a$10$NPcVK/mTJ6ftYB5pr06DD.V35H7Itqzzq9scIur92lpfruFgdZT3y','juan','valenzuela',1,'alex@gmail.com'),(8,'alex3','$2a$10$l/BIoG7gOsfSn.x2Dgi1feNRvtJylFFAP1bfEEVtFAiq9ntUUfk7e','alexitrico','toro',2,'alex@google.cl'),(9,'eduardoc','$2a$10$Y95e4p0NuZ82dBMSm8eSJeSofQdcO3bLPu8MaXuTUFfX4Jn2DAdhS','eduardo','cabrera',2,'eduardo@gmail.com');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-07  7:22:41
