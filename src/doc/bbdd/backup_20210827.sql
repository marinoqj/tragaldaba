-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tragaldaba
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APELLIDOS` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TELEFONO` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DNI` varchar(9) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DIRECCION` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENTE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Manuela','Para Papam','654654654','222333Q','Calle del Amparo 35, 2º B'),(2,'Coco','Colorado Pérez','612634656','1234567C','Avda. de la Albufera 245, 7º G');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `constantes`
--

DROP TABLE IF EXISTS `constantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `constantes` (
  `ID_CONSTANTE` int NOT NULL AUTO_INCREMENT,
  `FAMILIA` longtext COLLATE utf8_unicode_ci,
  `ATRIBUTO` longtext COLLATE utf8_unicode_ci,
  `LITERAL` longtext COLLATE utf8_unicode_ci,
  `VALOR` int DEFAULT NULL,
  `ACTIVA` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`ID_CONSTANTE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `constantes`
--

LOCK TABLES `constantes` WRITE;
/*!40000 ALTER TABLE `constantes` DISABLE KEYS */;
INSERT INTO `constantes` VALUES (1,'Desplegable','Entrega','Entrega a Domicilio',1,'1'),(2,'Platos','Ensalada','Ensaladas',1,'1'),(3,'Platos','Pizza','Pizzas',2,'1'),(4,'Platos','Postre','Postres',3,'1');
/*!40000 ALTER TABLE `constantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `ID_PEDIDO` int NOT NULL AUTO_INCREMENT,
  `NUM_ARTICULOS` int DEFAULT NULL,
  `TOTAL` double(4,2) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `TIPO_ENTREGA` int DEFAULT NULL,
  `ID_CLIENTE` int DEFAULT NULL,
  `ID_PROVEEDOR` int DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PEDIDOS_CLIENTES` (`ID_CLIENTE`),
  KEY `FK_PEDIDOS_PROVEEDORES` (`ID_PROVEEDOR`),
  CONSTRAINT `FK_PEDIDOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PEDIDOS_PROVEEDORES` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedores` (`ID_PROVEEDOR`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,3,30.25,'2021-08-26',1,1,1),(2,3,32.49,'2021-08-27',1,2,1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platos`
--

DROP TABLE IF EXISTS `platos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platos` (
  `ID_PLATO` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `NOMBRE_FOTO` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TIPO` int DEFAULT NULL,
  `DESCRIPCION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVO` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PLATO`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platos`
--

LOCK TABLES `platos` WRITE;
/*!40000 ALTER TABLE `platos` DISABLE KEYS */;
INSERT INTO `platos` VALUES (1,'Mixta',12.25,'ens-mixta.jpg',1,'Mezcla de lechugas, bonito del norte, aguacate, cebolla, maíz, tomate cherry, mozzarella de búfala y aceitunas','1'),(2,'Pollo',13.50,'ens-pollo.jpeg',1,'Mezcla de lechugas, bacon, pollo, tomates secos, setas, parmesano, mozzarella de búfala y toque de pesto de albahaca','1'),(3,'Cabra',14.25,'ens-cabra.jpeg',1,'Mezcla de lechugas, tomates cherry, jamón de Parma, queso de cabra, cebolla caliente y mozzarella de búfala','1'),(4,'Aguacate',12.50,'pizza-aguacate.jpg',2,'Base de tomate, tomate cherry, cebolla roja, paté de aceitunas negras, salmón ahumado, aguacate y mozzarella burrata fresca','1'),(5,'Calabaza',13.75,'pizza-calabaza.jpg',2,'Base de crema de calabaza, gorgonzola, salchicha, trufa fresca y mozzarella burrata entera 125 gr.','1'),(6,'Carpaccia y Trufa',14.25,'pizza-carpaccia.jpg',2,'Tomate, mozzarella, rúcula, carpaccio de ternera, toque de crema suave al parmesano y tomate confit','1'),(7,'Taggelio e Pere',15.50,'pizza-taleggio_e_pere.jpg',2,'Base blanca de mozzarella Fior di Latte, fondue de queso taleggio, peras, speck (jamón ahumado) granella de nueces y miel trufada','0'),(8,'Al Vitello Tonnato',16.75,'pizza-vitello_tonato.jpg',2,'Con carne de ternera asada y salsa, rúcula y mozzarella de búfala.','0'),(9,'Tiramisú',5.99,'postre-tiramisu.jpg',3,'El tiramisú es un pastel frío que se monta en capas. No existe una receta única de elaboración, sino variantes a partir de una serie de ingredientes base que pueden ser representados por distintos productos.','1'),(10,'Pannacotta',4.50,'postre-pannacotta.jpg',3,'La panna cotta es un postre típico de la región italiana del Piamonte, elaborado a partir de crema de leche, azúcar y gelificantes, que se suele adornar con mermeladas de frutas rojas.','1'),(11,'Cannoli Siciliani',3.75,'postre-cannoli.jpg',3,'Canuto de hojaldre relleno de queso de repostería y chocolate','1');
/*!40000 ALTER TABLE `platos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platos_pedido`
--

DROP TABLE IF EXISTS `platos_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platos_pedido` (
  `ID_PEDIDO` int NOT NULL,
  `ID_PLATO` int NOT NULL,
  PRIMARY KEY (`ID_PEDIDO`,`ID_PLATO`),
  KEY `FK_PLAT_PED_PLATOS` (`ID_PLATO`),
  CONSTRAINT `FK_PLAT_PED_PEDIDOS` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `pedidos` (`ID_PEDIDO`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_PLAT_PED_PLATOS` FOREIGN KEY (`ID_PLATO`) REFERENCES `platos` (`ID_PLATO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platos_pedido`
--

LOCK TABLES `platos_pedido` WRITE;
/*!40000 ALTER TABLE `platos_pedido` DISABLE KEYS */;
INSERT INTO `platos_pedido` VALUES (1,1),(2,1),(1,6),(2,6),(2,9),(1,11);
/*!40000 ALTER TABLE `platos_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `ID_PROVEEDOR` int NOT NULL AUTO_INCREMENT,
  `RAZON_SOCIAL` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DIRECCION` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `COD_POSTAL` int NOT NULL,
  `LOCALIDAD` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TELEFONO` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID_PROVEEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'Monsters, Inc.','Calle Donde viven los Monstruos, nº 1',28031,'Madrid','654654653','info@monsters.inc');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID_ROL` int NOT NULL,
  `NOMBRE_ROL` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`ID_ROL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Administrador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_usuario`
--

DROP TABLE IF EXISTS `roles_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_usuario` (
  `ID_USUARIO` int NOT NULL,
  `ID_ROL` int NOT NULL,
  PRIMARY KEY (`ID_USUARIO`,`ID_ROL`),
  KEY `FK_ROLS_USR_ROLES` (`ID_ROL`),
  CONSTRAINT `FK_ROLS_USR_ROLES` FOREIGN KEY (`ID_ROL`) REFERENCES `roles` (`ID_ROL`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ROLS_USR_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_usuario`
--

LOCK TABLES `roles_usuario` WRITE;
/*!40000 ALTER TABLE `roles_usuario` DISABLE KEYS */;
INSERT INTO `roles_usuario` VALUES (1,1);
/*!40000 ALTER TABLE `roles_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID_USUARIO` int NOT NULL,
  `LOGIN` longtext COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` longtext COLLATE utf8_unicode_ci NOT NULL,
  `NOMBRE` longtext COLLATE utf8_unicode_ci NOT NULL,
  `APELLIDO1` longtext COLLATE utf8_unicode_ci NOT NULL,
  `APELLIDO2` longtext COLLATE utf8_unicode_ci,
  `CAMBIAR_PASSWORD` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'a','1','Koji','Kabuto','MazinGo','0');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-27  8:40:45
