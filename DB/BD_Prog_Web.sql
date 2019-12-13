-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema progweb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema progweb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `progweb` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `progweb` ;

-- -----------------------------------------------------
-- Table `progweb`.`pesquisador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Pesquisador` (
  `PES_email` VARCHAR(60) NOT NULL,
  `PES_id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`PES_id`),
  UNIQUE INDEX `PES_id_UNIQUE` (`PES_id` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`teste`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Teste` (
  `TES_idTeste` INT(11) NOT NULL AUTO_INCREMENT,
  `TES_descricao` VARCHAR(1000) NOT NULL,
  `TES_titulo` VARCHAR(50) NOT NULL,
  `Pesquisador_PES_id` INT(11) NULL,
  `TES_visibilidade` TINYINT(4) NOT NULL,
  `TES_once_visible` TINYINT(4) NOT NULL DEFAULT 0,
  `TES_createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TES_updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TES_idTeste`),
  UNIQUE INDEX `TES_idTeste_UNIQUE` (`TES_idTeste` ASC) ,
  UNIQUE INDEX `TES_titulo_UNIQUE` (`TES_titulo` ASC) ,
  INDEX `fk_teste_pesquisador1_idx` (`Pesquisador_PES_id` ASC) ,
  CONSTRAINT `fk_teste_pesquisador1`
    FOREIGN KEY (`Pesquisador_PES_id`)
    REFERENCES `progweb`.`Pesquisador` (`PES_id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`codigounico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`CodigoUnico` (
  `idCodigoUnico` VARCHAR(10) NOT NULL,
  `Teste_TES_idTeste` INT(11) NOT NULL,
  `indice` INT(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`Teste_TES_idTeste`, `idCodigoUnico`),
  UNIQUE INDEX `idCodigoUnico_UNIQUE` (`idCodigoUnico` ASC) ,
  INDEX `fk_CodigoUnico_Teste1_idx` (`Teste_TES_idTeste` ASC) ,
  CONSTRAINT `fk_CodigoUnico_Teste1`
    FOREIGN KEY (`Teste_TES_idTeste`)
    REFERENCES `progweb`.`Teste` (`TES_idTeste`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Imagem` (
  `IMG_idImagem` INT(11) NOT NULL AUTO_INCREMENT,
  `IMG_endereco` VARCHAR(150) NOT NULL,
  `IMG_tag` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IMG_idImagem`),
  UNIQUE INDEX `IMG_idImagem_UNIQUE` (`IMG_idImagem` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`pergunta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Pergunta` (
  `PER_descricao` VARCHAR(200) NULL DEFAULT NULL,
  `PER_codigo` TINYINT(4) NOT NULL,
  `PER_tipo` TINYINT(4) NOT NULL,
  `PER_idPergunta` INT(11) NOT NULL AUTO_INCREMENT,
  `Teste_TES_idTeste` INT(11) NOT NULL,
  `PER_indice` INT(11) NOT NULL,
  `PER_updatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`PER_idPergunta`),
  UNIQUE INDEX `PER_idPergunta_UNIQUE` (`PER_idPergunta` ASC) ,
  INDEX `fk_Pergunta_Teste1_idx` (`Teste_TES_idTeste` ASC) ,
  CONSTRAINT `fk_Pergunta_Teste1`
    FOREIGN KEY (`Teste_TES_idTeste`)
    REFERENCES `progweb`.`Teste` (`TES_idTeste`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`pergunta_has_imagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Pergunta_has_Imagem` (
  `Pergunta_PER_idPergunta` INT(11) NOT NULL,
  `Imagem_IMG_idImagem` INT(11) NOT NULL,
  `PHI_indice` INT(11) NOT NULL,
  PRIMARY KEY (`Pergunta_PER_idPergunta`, `Imagem_IMG_idImagem`, `PHI_indice`),
  INDEX `fk_Pergunta_has_Imagem_Imagem1_idx` (`Imagem_IMG_idImagem` ASC) ,
  INDEX `fk_Pergunta_has_Imagem_Pergunta_idx` (`Pergunta_PER_idPergunta` ASC) ,
  CONSTRAINT `fk_Pergunta_has_Imagem_Imagem1`
    FOREIGN KEY (`Imagem_IMG_idImagem`)
    REFERENCES `progweb`.`Imagem` (`IMG_idImagem`)
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pergunta_has_Imagem_Pergunta`
    FOREIGN KEY (`Pergunta_PER_idPergunta`)
    REFERENCES `progweb`.`Pergunta` (`PER_idPergunta`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Usuario` (
  `USU_idUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `USU_contato` CHAR(15) NOT NULL,
  `USU_email` VARCHAR(60) NOT NULL,
  `USU_idade` INT(11) NOT NULL,
  `USU_sexo` CHAR(1) NOT NULL,
  `USU_cep` CHAR(9) NOT NULL,
  `USU_cor` VARCHAR(15) NOT NULL,
  `USU_enfermidade` VARCHAR(200) NULL DEFAULT NULL,
  `Teste_TES_idTeste` INT(11) NOT NULL,
  `USU_codigo_unico_teste` VARCHAR(10) NULL,
  PRIMARY KEY (`USU_idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`USU_idUsuario` ASC) ,
  INDEX `fk_Usuario_Teste1_idx` (`Teste_TES_idTeste` ASC) ,
  CONSTRAINT `fk_Usuario_Teste1`
    FOREIGN KEY (`Teste_TES_idTeste`)
    REFERENCES `progweb`.`Teste` (`TES_idTeste`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `progweb`.`resposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `progweb`.`Resposta` (
  `RES_idResposta` INT(11) NOT NULL AUTO_INCREMENT,
  `RES_respostaContinua` DECIMAL(4,2) NULL DEFAULT NULL,
  `RES_respostaOrdinal` INT(1) NULL DEFAULT NULL,
  `RES_descricao` VARCHAR(200) NULL DEFAULT NULL,
  `Usuario_USU_idUsuario` INT(11) NOT NULL,
  `Pergunta_PER_idPergunta` INT(11) NOT NULL,
  `RES_createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RES_idResposta`, `Usuario_USU_idUsuario`, `Pergunta_PER_idPergunta`),
  UNIQUE INDEX `RES_idResposta_UNIQUE` (`RES_idResposta` ASC) ,
  INDEX `fk_Resposta_Usuario1_idx` (`Usuario_USU_idUsuario` ASC) ,
  INDEX `fk_Resposta_Pergunta1_idx` (`Pergunta_PER_idPergunta` ASC) ,
  CONSTRAINT `fk_Resposta_Pergunta1`
    FOREIGN KEY (`Pergunta_PER_idPergunta`)
    REFERENCES `progweb`.`Pergunta` (`PER_idPergunta`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Resposta_Usuario1`
    FOREIGN KEY (`Usuario_USU_idUsuario`)
    REFERENCES `progweb`.`Usuario` (`USU_idUsuario`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


USE progweb;


INSERT INTO `Imagem` (`IMG_endereco`,`IMG_tag`) VALUES ("./storage/default.png",'default');

# ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'eduardoigorjoao';
