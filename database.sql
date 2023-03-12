CREATE DATABASE `gestor_banco` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;CREATE TABLE `autorizados` (
  `id_autorizado` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `id_empresa` int(11) NOT NULL,
  `estado` varchar(15) DEFAULT 'pendiente',
  PRIMARY KEY (`id_autorizado`),
  UNIQUE KEY `email` (`email`),
  KEY `id_empresa` (`id_empresa`),
  CONSTRAINT `autorizados_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `activo` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cuentas_bancarias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `moneda` varchar(20) NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `cuentas_bancarias_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `divisa` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `simbolo` varchar(10) NOT NULL,
  `ratio_de_cambio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `empresas` (
  `id_empresa` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `estado` varchar(15) DEFAULT 'pendiente',
  PRIMARY KEY (`id_empresa`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `gestores` (
  `id_gestor` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id_gestor`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `operaciones_bancarias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cuenta_origen` int(11) NOT NULL,
  `id_cuenta_destino` int(11) NOT NULL,
  `tipo_operacion` varchar(20) NOT NULL,
  `fecha` timestamp NOT NULL,
  `monto` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cuenta_origen` (`id_cuenta_origen`),
  KEY `id_cuenta_destino` (`id_cuenta_destino`),
  CONSTRAINT `operaciones_bancarias_ibfk_1` FOREIGN KEY (`id_cuenta_origen`) REFERENCES `cuentas_bancarias` (`id`),
  CONSTRAINT `operaciones_bancarias_ibfk_2` FOREIGN KEY (`id_cuenta_destino`) REFERENCES `cuentas_bancarias` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `socios` (
  `id_socio` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `id_empresa` int(11) NOT NULL,
  `estado` varchar(15) DEFAULT 'pendiente',
  PRIMARY KEY (`id_socio`),
  UNIQUE KEY `email` (`email`),
  KEY `id_empresa` (`id_empresa`),
  CONSTRAINT `socios_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `solicitudes_activacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `fecha_solicitud` timestamp NOT NULL,
  `aprobada` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `solicitudes_activacion_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `solicitudes_alta` (
  `id_solicitud` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_socio` int(11) NOT NULL,
  `id_gestor` int(11) NOT NULL,
  `fecha_solicitud` date NOT NULL,
  `estado` varchar(15) DEFAULT 'pendiente',
  PRIMARY KEY (`id_solicitud`),
  KEY `id_empresa` (`id_empresa`),
  KEY `id_socio` (`id_socio`),
  KEY `id_gestor` (`id_gestor`),
  CONSTRAINT `solicitudes_alta_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id_empresa`),
  CONSTRAINT `solicitudes_alta_ibfk_2` FOREIGN KEY (`id_socio`) REFERENCES `socios` (`id_socio`),
  CONSTRAINT `solicitudes_alta_ibfk_3` FOREIGN KEY (`id_gestor`) REFERENCES `gestores` (`id_gestor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `solicitudes_alta_autorizado` (
  `id_solicitud` int(11) NOT NULL,
  `id_autorizado` int(11) NOT NULL,
  `id_empresa` int(11) NOT NULL,
  `id_gestor` int(11) NOT NULL,
  `fecha_solicitud` date NOT NULL,
  `estado` varchar(15) DEFAULT 'pendiente',
  PRIMARY KEY (`id_solicitud`),
  KEY `id_autorizado` (`id_autorizado`),
  KEY `id_empresa` (`id_empresa`),
  KEY `id_gestor` (`id_gestor`),
  CONSTRAINT `solicitudes_alta_autorizado_ibfk_1` FOREIGN KEY (`id_autorizado`) REFERENCES `autorizados` (`id_autorizado`),
  CONSTRAINT `solicitudes_alta_autorizado_ibfk_2` FOREIGN KEY (`id_empresa`) REFERENCES `empresas` (`id_empresa`),
  CONSTRAINT `solicitudes_alta_autorizado_ibfk_3` FOREIGN KEY (`id_gestor`) REFERENCES `gestores` (`id_gestor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
