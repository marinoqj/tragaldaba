-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: tragaldaba
-- ------------------------------------------------------
-- Server version	5.7.28-log

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

USE tragaldaba;

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `ID_CLIENTE` int(11) NOT NULL AUTO_INCREMENT,
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
  `ID_CONSTANTE` int(11) NOT NULL AUTO_INCREMENT,
  `FAMILIA` longtext COLLATE utf8_unicode_ci,
  `ATRIBUTO` longtext COLLATE utf8_unicode_ci,
  `LITERAL` longtext COLLATE utf8_unicode_ci,
  `VALOR` int(11) DEFAULT NULL,
  `ACTIVA` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`ID_CONSTANTE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `constantes`
--

LOCK TABLES `constantes` WRITE;
/*!40000 ALTER TABLE `constantes` DISABLE KEYS */;
INSERT INTO `constantes` VALUES (3,'Desplegable','Entrega','Entrega a domicilio',1,'1'),(4,'Desplegable','Entrega','Recogida en tienda',2,'1'),(5,'Platos','Ensalada','Ensaladas',1,'1'),(6,'Platos','Pizza','Pizzas',2,'1'),(7,'Platos','Postre','Postres',3,'1');
/*!40000 ALTER TABLE `constantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT,
  `NUM_ARTICULOS` int(11) DEFAULT NULL,
  `TOTAL` double(4,2) DEFAULT NULL,
  `FECHA` date DEFAULT NULL,
  `TIPO_ENTREGA` int(11) DEFAULT NULL,
  `ID_CLIENTE` int(11) DEFAULT NULL,
  `ID_PROVEEDOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PEDIDO`),
  KEY `FK_PEDIDOS_CLIENTES` (`ID_CLIENTE`),
  KEY `FK_PEDIDOS_PROVEEDORES` (`ID_PROVEEDOR`),
  CONSTRAINT `FK_PEDIDOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `clientes` (`ID_CLIENTE`),
  CONSTRAINT `FK_PEDIDOS_PROVEEDORES` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `proveedores` (`ID_PROVEEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,3,30.25,'2021-08-26',1,1,1),(2,3,32.49,'2021-08-27',1,2,1),(3,3,31.99,'2021-08-27',1,2,1),(4,3,29.75,'2021-08-27',1,2,1);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platos`
--

DROP TABLE IF EXISTS `platos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platos` (
  `ID_PLATO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRECIO` double(4,2) DEFAULT NULL,
  `NOMBRE_FOTO` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TIPO` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVO` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID_PLATO`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platos`
--

LOCK TABLES `platos` WRITE;
/*!40000 ALTER TABLE `platos` DISABLE KEYS */;
INSERT INTO `platos` VALUES (3,'Lentejas',5.25,'lent',1,NULL,NULL),(4,'Calamares',6.25,'calamar',2,NULL,NULL),(5,'Acelgas',4.00,'acelgas',1,NULL,NULL),(6,'Escalope de ternera',7.30,'escalope',2,NULL,NULL),(7,'Piña en almibar',2.50,'pinya',3,NULL,NULL),(8,'Flan',1.95,'flan',3,NULL,NULL),(9,'Mixta',12.25,'ens-mixta.jpg',1,'Mezcla de lechugas, bonito del norte, aguacate, cebolla, maíz, tomate cherry, mozzarella de búfala y aceitunas','1'),(10,'Pollo',13.50,'ens-pollo.jpeg',1,'Mezcla de lechugas, bacon, pollo, tomates secos, setas, parmesano, mozzarella de bÃºfala y toque de pesto de albahaca','1'),(11,'Cabra',14.25,'ens-cabra.jpeg',1,'Mezcla de lechugas, tomates cherry, jamón de Parma, queso de cabra, cebolla caliente y mozzarella de búfala','1'),(12,'Aguacate',12.50,'pizza-aguacate.jpg',2,'Base de tomate, tomate cherry, cebolla roja, paté de aceitunas negras, salmón ahumado, aguacate y mozzarella burrata fresca','1'),(13,'Calabaza',13.75,'pizza-calabaza.jpg',2,'Base de crema de calabaza, gorgonzola, salchicha, trufa fresca y mozzarella burrata entera 125 gr.','1'),(14,'Carpaccia y Trufa',14.25,'pizza-carpaccia.jpg',2,'Tomate, mozzarella, rúcula, carpaccio de ternera, toque de crema suave al parmesano y tomate confit','1'),(15,'Taggelio e Pere',15.50,'pizza-taleggio_e_pere.jpg',2,'Base blanca de mozzarella Fior di Latte, fondue de queso taleggio, peras, speck (jamón ahumado) granella de nueces y miel trufada','1'),(16,'Al Vitello Tonnato',16.75,'pizza-vitello_tonato.jpg',2,'Con carne de ternera asada y salsa, rúcula y mozzarella de búfala.','1'),(17,'Tiramisú',5.99,'postre-tiramisu.jpg',3,'El tiramisú es un pastel frío que se monta en capas. No existe una receta única de elaboración, sino variantes a partir de una serie de ingredientes base que pueden ser representados por distintos productos.','1'),(18,'Pannacotta',4.50,'postre-pannacotta.jpg',3,'La panna cotta es un postre típico de la región italiana del Piamonte, elaborado a partir de crema de leche, azúcar y gelificantes, que se suele adornar con mermeladas de frutas rojas.','1'),(19,'Cannoli Siciliani',3.75,'postre-cannoli.jpg',3,'Canuto de hojaldre relleno de queso de repostería y chocolate','1');
/*!40000 ALTER TABLE `platos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platos_pedido`
--

DROP TABLE IF EXISTS `platos_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platos_pedido` (
  `ID_PEDIDO` int(11) NOT NULL,
  `ID_PLATO` int(11) NOT NULL,
  PRIMARY KEY (`ID_PEDIDO`,`ID_PLATO`),
  KEY `FK_PLAT_PED_PLATOS` (`ID_PLATO`),
  CONSTRAINT `FK_PLAT_PED_PEDIDOS` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `pedidos` (`ID_PEDIDO`),
  CONSTRAINT `FK_PLAT_PED_PLATOS` FOREIGN KEY (`ID_PLATO`) REFERENCES `platos` (`ID_PLATO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platos_pedido`
--

LOCK TABLES `platos_pedido` WRITE;
/*!40000 ALTER TABLE `platos_pedido` DISABLE KEYS */;
INSERT INTO `platos_pedido` VALUES (1,9),(2,9),(4,9),(3,10),(3,12),(4,13),(1,15),(2,15),(3,17),(2,18),(1,19),(4,19);
/*!40000 ALTER TABLE `platos_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `ID_PROVEEDOR` int(11) NOT NULL AUTO_INCREMENT,
  `RAZON_SOCIAL` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `DIRECCION` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `COD_POSTAL` int(11) NOT NULL,
  `LOCALIDAD` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `TELEFONO` varchar(9) COLLATE utf8_unicode_ci NOT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID_PROVEEDOR`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (1,'Casa Pepe','Avda. de la Albufera 245, 7º G',28038,'Madrid','654654654','casa.pepe@es.es'),(3,'Las acelgas','Calle Nueva 23, Bj',28018,'Madrid','654987321','prueba@es.es'),(4,'Las hermanas','Calle del Río 11',28051,'Madrid','916542121','hermanas@es.es'),(5,'Patater','Pza. de la Roca 5, Bj',28013,'Madrid','654321321','prueba2@es.es'),(6,'CafeRico','Calle Puerto del Mirador, 87',28038,'Madrid','654654222','mirador@es.es'),(7,'Tachán','Paseo de la Gloria 12, Entreplanta',28031,'Madrid','636545454','tachan@es.es');
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID_ROL` int(11) NOT NULL,
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
  `ID_USUARIO` int(11) NOT NULL,
  `ID_ROL` int(11) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`,`ID_ROL`),
  KEY `FK_ROLS_USR_ROLES` (`ID_ROL`),
  CONSTRAINT `FK_ROLS_USR_ROLES` FOREIGN KEY (`ID_ROL`) REFERENCES `roles` (`ID_ROL`),
  CONSTRAINT `FK_ROLS_USR_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuarios` (`ID_USUARIO`)
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
  `ID_USUARIO` int(11) NOT NULL,
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
INSERT INTO `usuarios` VALUES (1,'a','1','Admin','Admin','Admin','0');
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

-- Dump completed on 2021-09-09 12:45:13
