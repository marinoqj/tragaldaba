
-- Create tables section -------------------------------------------------

-- Table PLATOS

CREATE TABLE `PLATOS`
(
  `ID_PLATO` Int NOT NULL AUTO_INCREMENT,
  `NOMBRE` Varchar(200),
  `PRECIO` Double(4,2),
  `NOMBRE_FOTO` Varchar(200),
  `TIPO` Int,
  PRIMARY KEY (`ID_PLATO`)
)
;

-- Table PEDIDOS

CREATE TABLE `PEDIDOS`
(
  `ID_PEDIDO` Int NOT NULL AUTO_INCREMENT,
  `NUM_ARTICULOS` Int,
  `TOTAL` Double(4,2),
  `FECHA` Date,
  `TIPO_ENTREGA` Int,
  `ID_CLIENTE` Int,
  `ID_PROVEEDOR` Int,
  PRIMARY KEY (`ID_PEDIDO`)
)
;

-- Table CLIENTES

CREATE TABLE `CLIENTES`
(
  `ID_CLIENTE` Int NOT NULL AUTO_INCREMENT,
  `NOMBRE` Varchar(200),
  `APELLIDOS` Varchar(200),
  `TELEFONO` Varchar(9),
  `DNI` Varchar(9),
  `DIRECCION` Varchar(300),
  PRIMARY KEY (`ID_CLIENTE`)
)
;

-- Table CONSTANTES

CREATE TABLE `CONSTANTES`
(
  `ID_CONSTANTE` Int NOT NULL,
  `FAMILIA` Longtext,
  `ATRIBUTO` Longtext,
  `LITERAL` Longtext,
  `VALOR` Int,
  `ACTIVA` Longtext
)
;

ALTER TABLE `CONSTANTES` ADD PRIMARY KEY (`ID_CONSTANTE`)
;

-- Table USUARIOS

CREATE TABLE `USUARIOS`
(
  `ID_USUARIO` Int NOT NULL,
  `LOGIN` Longtext NOT NULL,
  `PASSWORD` Longtext NOT NULL,
  `NOMBRE` Longtext NOT NULL,
  `APELLIDO1` Longtext NOT NULL,
  `APELLIDO2` Longtext,
  `CAMBIAR_PASSWORD` Longtext
)
;

ALTER TABLE `USUARIOS` ADD PRIMARY KEY (`ID_USUARIO`)
;

-- Table ROLES

CREATE TABLE `ROLES`
(
  `ID_ROL` Int NOT NULL,
  `NOMBRE_ROL` Longtext
)
;

ALTER TABLE `ROLES` ADD PRIMARY KEY (`ID_ROL`)
;

-- Table ROLES_USUARIO

CREATE TABLE `ROLES_USUARIO`
(
  `ID_USUARIO` Int NOT NULL,
  `ID_ROL` Int NOT NULL
)
;

ALTER TABLE `ROLES_USUARIO` ADD PRIMARY KEY (`ID_USUARIO`,`ID_ROL`)
;

-- Table PROVEEDORES

CREATE TABLE `PROVEEDORES`
(
  `ID_PROVEEDOR` Int NOT NULL AUTO_INCREMENT,
  `RAZON_SOCIAL` Varchar(200) NOT NULL,
  `DIRECCION` Varchar(200) NOT NULL,
  `COD_POSTAL` Int NOT NULL,
  `LOCALIDAD` Varchar(200) NOT NULL,
  `TELEFONO` Varchar(9) NOT NULL,
  `EMAIL` Varchar(100) NOT NULL,
  PRIMARY KEY (`ID_PROVEEDOR`)
)
;

-- Table PLATOS_PEDIDO

CREATE TABLE `PLATOS_PEDIDO`
(
  `ID_PEDIDO` Int NOT NULL,
  `ID_PLATO` Int NOT NULL
)
;

ALTER TABLE `PLATOS_PEDIDO` ADD PRIMARY KEY (`ID_PEDIDO`,`ID_PLATO`)
;

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE `ROLES_USUARIO` ADD CONSTRAINT `FK_ROLS_USR_USUARIOS` FOREIGN KEY (`ID_USUARIO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `ROLES_USUARIO` ADD CONSTRAINT `FK_ROLS_USR_ROLES` FOREIGN KEY (`ID_ROL`) REFERENCES `ROLES` (`ID_ROL`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PEDIDOS` ADD CONSTRAINT `FK_PEDIDOS_CLIENTES` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `CLIENTES` (`ID_CLIENTE`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PEDIDOS` ADD CONSTRAINT `FK_PEDIDOS_PROVEEDORES` FOREIGN KEY (`ID_PROVEEDOR`) REFERENCES `PROVEEDORES` (`ID_PROVEEDOR`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PLATOS_PEDIDO` ADD CONSTRAINT `FK_PLAT_PED_PEDIDOS` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `PEDIDOS` (`ID_PEDIDO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


ALTER TABLE `PLATOS_PEDIDO` ADD CONSTRAINT `FK_PLAT_PED_PLATOS` FOREIGN KEY (`ID_PLATO`) REFERENCES `PLATOS` (`ID_PLATO`) ON DELETE RESTRICT ON UPDATE RESTRICT
;


