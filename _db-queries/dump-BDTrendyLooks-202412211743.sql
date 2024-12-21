-- MySQL dump 10.13  Distrib 8.3.0, for macos14.2 (arm64)
--
-- Host: localhost    Database: BDTrendyLooks
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DetalleCarrito`
--

DROP TABLE IF EXISTS `DetalleCarrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DetalleCarrito` (
  `codDetCarr` int NOT NULL AUTO_INCREMENT,
  `codCarr` int NOT NULL,
  `codProd` int NOT NULL,
  `canDetCarr` int NOT NULL,
  PRIMARY KEY (`codDetCarr`),
  UNIQUE KEY `codCarr` (`codCarr`,`codProd`),
  KEY `codProd` (`codProd`),
  CONSTRAINT `detallecarrito_ibfk_1` FOREIGN KEY (`codCarr`) REFERENCES `Carrito` (`codCarr`),
  CONSTRAINT `detallecarrito_ibfk_2` FOREIGN KEY (`codProd`) REFERENCES `Producto` (`codProd`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetalleCarrito`
--

LOCK TABLES `DetalleCarrito` WRITE;
/*!40000 ALTER TABLE `DetalleCarrito` DISABLE KEYS */;
INSERT INTO `DetalleCarrito` VALUES (1,1,1,5),(2,2,2,1),(3,3,3,1),(4,4,4,1),(5,5,5,1),(6,6,6,1),(7,7,7,1),(8,8,8,1),(9,9,9,1),(10,10,10,1);
/*!40000 ALTER TABLE `DetalleCarrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedido`
--

DROP TABLE IF EXISTS `Pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Pedido` (
  `codPed` int NOT NULL AUTO_INCREMENT,
  `contPed` varchar(255) DEFAULT NULL,
  `dirPed` varchar(255) DEFAULT NULL,
  `estPed` varchar(255) DEFAULT NULL,
  `fecPed` datetime(6) DEFAULT NULL,
  `telPed` varchar(255) DEFAULT NULL,
  `totPed` double DEFAULT NULL,
  `codUsu` int DEFAULT NULL,
  PRIMARY KEY (`codPed`),
  KEY `FK64jvbm78ikyl4e3t9velloael` (`codUsu`),
  CONSTRAINT `FK64jvbm78ikyl4e3t9velloael` FOREIGN KEY (`codUsu`) REFERENCES `Usuario` (`codUsu`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedido`
--

LOCK TABLES `Pedido` WRITE;
/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
INSERT INTO `Pedido` VALUES (1,'Juan Pérez','Av. Siempre Viva 123','En proceso','2024-12-18 19:00:00.000000','987654321',199.99,1),(2,'María García','Calle Ficticia 456','Enviado','2024-05-24 19:00:00.000000','963852741',350.5,2),(3,'Carlos López','Plaza Central 789','Entregado','2024-12-11 19:00:00.000000','951753486',50.75,3),(4,'Ana Martínez','Av. Libertad 101','Cancelado','2024-12-30 19:00:00.000000','789654123',80,4),(5,'Luis Fernández','Calle Real 202','En proceso','2024-12-30 19:00:00.000000','852147369',120.25,5),(6,'Juancito ekisde','av lima 215','Pendiente','1969-12-30 19:00:00.000000','19283912312',1000,2),(7,'Maria ekisde','av lima','Pendiente','2024-12-21 16:06:09.000000','919231',100,3);
/*!40000 ALTER TABLE `Pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Carrito`
--

DROP TABLE IF EXISTS `Carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Carrito` (
  `codCarr` int NOT NULL AUTO_INCREMENT,
  `codUsu` int NOT NULL,
  `estCarr` int DEFAULT NULL,
  `fecCarr` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codCarr`),
  KEY `codUsu` (`codUsu`),
  CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`codUsu`) REFERENCES `Usuario` (`codUsu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carrito`
--

LOCK TABLES `Carrito` WRITE;
/*!40000 ALTER TABLE `Carrito` DISABLE KEYS */;
INSERT INTO `Carrito` VALUES (1,1,1,'2024-12-21 11:06:19'),(2,2,1,'2024-12-21 11:06:19'),(3,3,1,'2024-12-21 11:06:19'),(4,4,1,'2024-12-21 11:06:19'),(5,5,1,'2024-12-21 11:06:19'),(6,6,1,'2024-12-21 11:06:19'),(7,7,1,'2024-12-21 11:06:19'),(8,8,1,'2024-12-21 11:06:19'),(9,9,1,'2024-12-21 11:06:19'),(10,10,1,'2024-12-21 11:06:19');
/*!40000 ALTER TABLE `Carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetallePedido`
--

DROP TABLE IF EXISTS `DetallePedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DetallePedido` (
  `codDetPed` int NOT NULL AUTO_INCREMENT,
  `canDetPed` int DEFAULT NULL,
  `codProd` int DEFAULT NULL,
  `totDetPed` double DEFAULT NULL,
  `codPed` int DEFAULT NULL,
  PRIMARY KEY (`codDetPed`),
  KEY `FK3aup9fswdqb6m9n9t35rx4urv` (`codPed`),
  CONSTRAINT `FK3aup9fswdqb6m9n9t35rx4urv` FOREIGN KEY (`codPed`) REFERENCES `Pedido` (`codPed`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetallePedido`
--

LOCK TABLES `DetallePedido` WRITE;
/*!40000 ALTER TABLE `DetallePedido` DISABLE KEYS */;
INSERT INTO `DetallePedido` VALUES (6,1,1,78.99,1),(7,1,2,469.89,2),(8,1,3,659.98,3),(9,1,4,39.95,4),(10,1,5,99.99,5);
/*!40000 ALTER TABLE `DetallePedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecarrito_producto`
--

DROP TABLE IF EXISTS `detallecarrito_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallecarrito_producto` (
  `codDetCarr` int NOT NULL,
  `codProd` int NOT NULL,
  KEY `FKdpah4uuic5dcbxko5fkskpoxc` (`codProd`),
  KEY `FKlf4u20nlvc1x31k5pfqfhs0yx` (`codDetCarr`),
  CONSTRAINT `FKdpah4uuic5dcbxko5fkskpoxc` FOREIGN KEY (`codProd`) REFERENCES `Producto` (`codProd`),
  CONSTRAINT `FKlf4u20nlvc1x31k5pfqfhs0yx` FOREIGN KEY (`codDetCarr`) REFERENCES `DetalleCarrito` (`codDetCarr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecarrito_producto`
--

LOCK TABLES `detallecarrito_producto` WRITE;
/*!40000 ALTER TABLE `detallecarrito_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallecarrito_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Producto`
--

DROP TABLE IF EXISTS `Producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Producto` (
  `codProd` int NOT NULL AUTO_INCREMENT,
  `nomProd` varchar(255) DEFAULT NULL,
  `descProd` varchar(255) DEFAULT NULL,
  `codCat` int NOT NULL,
  `preProd` double DEFAULT NULL,
  `stockProd` int NOT NULL,
  `imgProd` varchar(255) DEFAULT NULL,
  `estProd` bit(1) DEFAULT NULL,
  `fecProd` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codProd`),
  KEY `codCat` (`codCat`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`codCat`) REFERENCES `Categoria` (`codCat`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Producto`
--

LOCK TABLES `Producto` WRITE;
/*!40000 ALTER TABLE `Producto` DISABLE KEYS */;
INSERT INTO `Producto` VALUES (1,'Zapatillas Lomas Dinamic Black','Damas - 36',6,78.99,0,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734801906/gdixsfidw0ohebddmvcn.webp',_binary '\0','2024-12-09 19:00:00'),(2,'Nike Air Force 1 LV8','Zapatillas Nike Air Force Edición Limitada',6,469.89,10,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734802121/erfbe0dj6ahrnzv78k6i.webp',_binary '\0','2024-12-10 19:00:00'),(3,'Adidas Originals Forum PWR','Colaboracion adidas con Bad Bunny',6,659.98,5,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734802201/ojg6nkifz0falpdodi4e.avif',_binary '','2024-12-11 19:00:00'),(4,'Sudadera con Capucha','Sudadera con capucha y bolsillo delantero, varios colores',4,39.95,80,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804371/dmph49oipjrepgbwaqbo.jpg',_binary '','2024-12-10 19:00:00'),(5,'Chaqueta de Cuero','Chaqueta de cuero sintético, color negro',5,99.99,20,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804449/wcc0n8kjcwm0dlmmgy9o.jpg',_binary '','2024-12-11 19:00:00'),(6,'Zapatillas Deportivas','Zapatillas cómodas para actividades deportivas',6,59.99,60,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804481/ifhyxxbh4gotkqvaaope.jpg',_binary '','2024-12-11 19:00:00'),(7,'Sombrero de Paja','Sombrero de paja ideal para el verano',7,15.99,40,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804774/wglnadakonzskpp4w9ps.jpg',_binary '','2024-12-10 19:00:00'),(8,'Camisa Formal','Camisa formal para eventos importantes',8,25.99,70,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804798/petqowjgykvmw98obbfv.jpg',_binary '','2024-12-11 19:00:00'),(9,'Calcetines Deportivos','Calcetines de algodón para uso diario y deportes',9,6.99,200,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804742/dsihe9lywau9pi0y87n1.jpg',_binary '','2024-12-11 19:00:00'),(10,'Abrigo de Invierno','Abrigo grueso y cálido para el invierno',10,120.99,15,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804823/klcta3hl3xux42evfc5u.jpg',_binary '','2024-12-11 19:00:00'),(11,'Abrigo Largo','Abrigo largo de lana, color gris',5,130.99,25,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804845/dxcboefb6yjgezdyczro.jpg',_binary '','2024-12-10 19:00:00'),(12,'Abrigo Impermeable','Abrigo impermeable con capucha, color azul marino',5,110.99,30,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804858/pexmnphqiwrahwswwwul.jpg',_binary '','2024-12-10 19:00:00'),(13,'Abrigo Acolchado','Abrigo acolchado con cierre frontal, varios colores',5,115.99,20,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804877/xucivqbveeivtauk08gp.jpg',_binary '','2024-12-10 19:00:00'),(14,'Abrigo Trench','Abrigo estilo trench, color beige',5,105.99,22,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804902/zyeyhfzkuprditeuoegd.jpg',_binary '','2024-12-18 19:00:00'),(15,'Abrigo Militar','Abrigo con diseño militar, color verde oliva',5,125.99,18,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804916/tiycbsjs6xvgknukhhlj.jpg',_binary '','2024-12-18 19:00:00'),(16,'Cartera','Cartera',7,20.99,50,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804930/h8qd74efsytpaambovdh.jpg',_binary '','2024-12-17 19:00:00'),(17,'Correa de cuero','Correa de cuero, color negro',7,35.99,35,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804957/fp5onvh85qucnijlli0h.jpg',_binary '','2024-12-18 19:00:00'),(18,'Reloj de mano','Reloj de mano elegante',7,18.99,60,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734804974/hqw8jkasl7ir3p1vftzq.jpg',_binary '','2024-12-18 19:00:00'),(19,'Sandalias Elegantes','Sandalias de tacón medio, color beige',6,45.99,40,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734805004/pyhqfquiry6wgcqlv3ze.jpg',_binary '','2024-12-18 19:00:00'),(20,'Bolso gucci','bolso gucci',7,1000,2,'https://res.cloudinary.com/dwxrovwfn/image/upload/v1734816683/o3othn7nmcsxbgr6zkpl.jpg',_binary '','2024-12-21 16:31:23');
/*!40000 ALTER TABLE `Producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `codUsu` int NOT NULL AUTO_INCREMENT,
  `nomUsu` varchar(255) DEFAULT NULL,
  `apeUsu` varchar(255) DEFAULT NULL,
  `correoUsu` varchar(255) DEFAULT NULL,
  `contraUsu` varchar(255) DEFAULT NULL,
  `rolUsu` int NOT NULL DEFAULT '1',
  `estUsu` int NOT NULL DEFAULT '1',
  `fecUsu` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codUsu`),
  UNIQUE KEY `correoUsu` (`correoUsu`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'John -updated','Doe','john2doe@email.com','password123',1,1,'2024-12-14 14:08:35'),(2,'Juan','Pérez','juan.perez@example.com','password123',1,1,'2024-12-14 14:08:41'),(3,'María','González','maria.gonzalez@example.com','password123',0,0,'2024-12-14 14:08:41'),(4,'Carlos','Sánchez','carlos.sanchez@example.com','password123',0,1,'2024-12-14 14:08:41'),(5,'Ana','López','ana.lopez@example.com','password123',0,1,'2024-12-14 14:08:41'),(6,'Luis','Martínez','luis.martinez@example.com','password123',0,1,'2024-12-14 14:08:41'),(7,'Laura','Gómez','laura.gomez@example.com','password123',0,1,'2024-12-14 14:08:41'),(8,'Pedro','Rodríguez','pedro.rodriguez@example.com','password123',1,1,'2024-12-14 14:08:41'),(9,'Sofía','Fernández','sofia.fernandez@example.com','password123',0,1,'2024-12-14 14:08:41'),(10,'Jorge','Hernández','jorge.hernandez@example.com','password123',0,1,'2024-12-14 14:08:41'),(11,'Andrea','Ruiz','andrea.ruiz@example.com','password123',0,1,'2024-12-14 14:08:41'),(12,'Joseph','Infante','josephinfante.y@gmail.com','pass1234',1,1,'2024-12-14 14:08:41'),(13,'john','doe','john@doe.com','pass1234',0,1,'2024-12-14 14:08:47'),(14,'jhonny','pacheco','j@gmail.com','pass123',0,1,'2024-12-21 00:59:21'),(15,'Athenea','Doo','athenea@gmail.com',NULL,0,1,'2024-12-21 10:26:00'),(24,'juan','infante','juan@test.com','pass1234',0,1,'2024-12-21 16:29:26');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepedido_producto`
--

DROP TABLE IF EXISTS `detallepedido_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepedido_producto` (
  `codDetPed` int NOT NULL,
  `codProd` int NOT NULL,
  KEY `FKnvfymlwxrwpu3ks81aq0kel66` (`codProd`),
  KEY `FKj0wcsplulri3ccwwjpgdnkw42` (`codDetPed`),
  CONSTRAINT `FKj0wcsplulri3ccwwjpgdnkw42` FOREIGN KEY (`codDetPed`) REFERENCES `DetallePedido` (`codDetPed`),
  CONSTRAINT `FKnvfymlwxrwpu3ks81aq0kel66` FOREIGN KEY (`codProd`) REFERENCES `Producto` (`codProd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido_producto`
--

LOCK TABLES `detallepedido_producto` WRITE;
/*!40000 ALTER TABLE `detallepedido_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepedido_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Categoria` (
  `codCat` int NOT NULL,
  `nomCat` varchar(255) DEFAULT NULL,
  `estCat` bit(1) DEFAULT NULL,
  `fecCat` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`codCat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
INSERT INTO `Categoria` VALUES (1,'Ropa Casual',_binary '\0','2024-11-30 12:46:37'),(2,'Pantalones',_binary '','2024-11-30 12:46:37'),(3,'Vestidos',_binary '','2024-11-30 12:46:37'),(4,'Ropa Deportiva',_binary '','2024-11-30 12:46:37'),(5,'Abrigos',_binary '','2024-11-30 12:46:37'),(6,'Calzado - updated',_binary '','2024-11-30 12:46:37'),(7,'Accesorios',_binary '','2024-11-30 12:46:37'),(8,'Ropa Formal',_binary '','2024-11-30 12:46:37'),(9,'Ropa Interior',_binary '','2024-11-30 12:46:37'),(10,'Ropa de Invierno',_binary '','2024-11-30 12:46:37');
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-21 17:43:24
