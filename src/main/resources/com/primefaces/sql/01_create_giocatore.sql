CREATE TABLE `gestione_asta`.`giocatore` (
  `idgiocatore` INT NOT NULL AUTO_INCREMENT,
  `codice` INT NULL,
  `nome` VARCHAR(45) NULL,
  `ruolo` VARCHAR(45) NULL,
  `club` VARCHAR(45) NULL,
  `fantasquadra` VARCHAR(45) NULL,
  `fantavalore` INT NULL,
  PRIMARY KEY (`idgiocatore`));