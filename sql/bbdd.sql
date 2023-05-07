-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gestor_banco
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gestor_banco
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gestor_banco` DEFAULT CHARACTER SET utf8mb4 ;
USE `gestor_banco` ;

-- -----------------------------------------------------
-- Table `gestor_banco`.`divisa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`divisa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `simbolo` VARCHAR(10) NOT NULL,
  `ratio_de_cambio` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`cuenta_bancaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`cuenta_bancaria` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `saldo` FLOAT NOT NULL,
  `moneda` VARCHAR(20) NOT NULL,
  `sospechosa` TINYINT(4) NOT NULL DEFAULT 0,
  `activo` TINYINT(4) NOT NULL,
  `divisa_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cuenta_bancaria_divisa1_idx` (`divisa_id` ASC) VISIBLE,
  CONSTRAINT `fk_cuenta_bancaria_divisa1`
    FOREIGN KEY (`divisa_id`)
    REFERENCES `gestor_banco`.`divisa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`usuario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `identificacion` VARCHAR(11) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `apellido` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(50) NOT NULL,
  `contrasena` VARCHAR(50) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `subrol` VARCHAR(45) NULL DEFAULT NULL,
  `direccion` VARCHAR(45) NULL DEFAULT NULL,
  `telefono` VARCHAR(12) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`asignacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`asignacion` (
  `cuenta_bancaria_id` INT(11) NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  PRIMARY KEY (`cuenta_bancaria_id`, `usuario_id`),
  INDEX `fk_cuenta_bancaria_has_usuario_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_cuenta_bancaria_has_usuario_cuenta_bancaria1_idx` (`cuenta_bancaria_id` ASC) VISIBLE,
  CONSTRAINT `fk_cuenta_bancaria_has_usuario_cuenta_bancaria1`
    FOREIGN KEY (`cuenta_bancaria_id`)
    REFERENCES `gestor_banco`.`cuenta_bancaria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cuenta_bancaria_has_usuario_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gestor_banco`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`empleado` (
  `id_gestor` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_gestor`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`conversacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`conversacion` (
  `id_conver` INT(11) NOT NULL AUTO_INCREMENT,
  `numero_mensaje` INT(11) NULL DEFAULT NULL,
  `empleado_id_gestor` INT(11) NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  `fecha_apertura` TIMESTAMP NULL DEFAULT NULL,
  `fecha_cierre` TIMESTAMP NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_conver`),
  INDEX `fk_conversacion_empleado1_idx` (`empleado_id_gestor` ASC) VISIBLE,
  INDEX `fk_conversacion_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_conversacion_empleado1`
    FOREIGN KEY (`empleado_id_gestor`)
    REFERENCES `gestor_banco`.`empleado` (`id_gestor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conversacion_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gestor_banco`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`mensaje` (
  `id_mensaje` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NULL DEFAULT NULL,
  `longitud` INT(11) NULL DEFAULT NULL,
  `texto` VARCHAR(500) NULL DEFAULT NULL,
  `sender` VARCHAR(500) NULL DEFAULT NULL,
  `conversacion_id_conver` INT(11) NOT NULL,
  PRIMARY KEY (`id_mensaje`),
  INDEX `fk_mensaje_conversacion1_idx` (`conversacion_id_conver` ASC) VISIBLE,
  CONSTRAINT `fk_mensaje_conversacion1`
    FOREIGN KEY (`conversacion_id_conver`)
    REFERENCES `gestor_banco`.`conversacion` (`id_conver`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`operacion_bancaria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`operacion_bancaria` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL,
  `cantidad` FLOAT NOT NULL,
  `id_cuenta_origen` INT(11) NOT NULL,
  `id_cuenta_destino` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_cuenta_origen` (`id_cuenta_origen` ASC) VISIBLE,
  INDEX `id_cuenta_destino` (`id_cuenta_destino` ASC) VISIBLE,
  CONSTRAINT `operacion_bancaria_ibfk_1`
    FOREIGN KEY (`id_cuenta_origen`)
    REFERENCES `gestor_banco`.`cuenta_bancaria` (`id`),
  CONSTRAINT `operacion_bancaria_ibfk_2`
    FOREIGN KEY (`id_cuenta_destino`)
    REFERENCES `gestor_banco`.`cuenta_bancaria` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`solicitud_activacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`solicitud_activacion` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_solicitud` TIMESTAMP NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  `empleado_id_gestor` INT(11) NOT NULL,
  `cuenta_bancaria_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_solicitud_activacion_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_solicitud_activacion_empleado1_idx` (`empleado_id_gestor` ASC) VISIBLE,
  INDEX `fk_solicitud_activacion_cuenta_bancaria1_idx` (`cuenta_bancaria_id` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud_activacion_cuenta_bancaria1`
    FOREIGN KEY (`cuenta_bancaria_id`)
    REFERENCES `gestor_banco`.`cuenta_bancaria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitud_activacion_empleado1`
    FOREIGN KEY (`empleado_id_gestor`)
    REFERENCES `gestor_banco`.`empleado` (`id_gestor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitud_activacion_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gestor_banco`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `gestor_banco`.`solicitud_alta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestor_banco`.`solicitud_alta` (
  `id_solicitud` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_solicitud` DATE NOT NULL,
  `id_gestor` INT(11) NOT NULL,
  `usuario_id` INT(11) NOT NULL,
  `divisa_id` INT(11) NOT NULL,
  PRIMARY KEY (`id_solicitud`),
  INDEX `id_gestor` (`id_gestor` ASC) VISIBLE,
  INDEX `fk_solicitud_alta_usuario1_idx` (`usuario_id` ASC) VISIBLE,
  INDEX `fk_solicitud_alta_divisa1_idx` (`divisa_id` ASC) VISIBLE,
  CONSTRAINT `fk_solicitud_alta_divisa1`
    FOREIGN KEY (`divisa_id`)
    REFERENCES `gestor_banco`.`divisa` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_solicitud_alta_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `gestor_banco`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `solicitud_alta_ibfk_3`
    FOREIGN KEY (`id_gestor`)
    REFERENCES `gestor_banco`.`empleado` (`id_gestor`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
