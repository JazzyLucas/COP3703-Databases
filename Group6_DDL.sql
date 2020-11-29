-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema group6
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema group6
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `group6` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `group6` ;

-- -----------------------------------------------------
-- Table `group6`.`characteristics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`characteristics` (
  `CharacteristicsID` INT NOT NULL,
  `BT_Bus_Lines` TEXT NULL DEFAULT NULL,
  `Crime_Rates` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`CharacteristicsID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`location` (
  `LocationID` INT NOT NULL,
  `LocationName` VARCHAR(45) NULL DEFAULT NULL,
  `StreetAddress` VARCHAR(45) NULL DEFAULT NULL,
  `Zip` INT NULL DEFAULT NULL,
  `City` VARCHAR(45) NULL DEFAULT NULL,
  `State` VARCHAR(45) NULL DEFAULT NULL,
  `Neighborhood` VARCHAR(45) NULL DEFAULT NULL,
  `CharacteristicsID` INT NOT NULL,
  PRIMARY KEY (`LocationID`),
  INDEX `fk_LOCATION_CHARACTERISTICS1_idx` (`CharacteristicsID` ASC) VISIBLE,
  CONSTRAINT `fk_LOCATION_CHARACTERISTICS1`
    FOREIGN KEY (`CharacteristicsID`)
    REFERENCES `group6`.`characteristics` (`CharacteristicsID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`amenities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`amenities` (
  `AmenitiesID` INT NOT NULL AUTO_INCREMENT,
  `LocationID` INT NOT NULL,
  `Laundry` TINYINT(1) NULL DEFAULT NULL,
  `Parking` TINYINT(1) NULL DEFAULT NULL,
  `HardwoodFloors` TINYINT(1) NULL DEFAULT NULL,
  `Dishwasher` TINYINT(1) NULL DEFAULT NULL,
  `AirConditioner` TINYINT(1) NULL DEFAULT NULL,
  `Patio` TINYINT(1) NULL DEFAULT NULL,
  `Gym` TINYINT(1) NULL DEFAULT NULL,
  `Pool` TINYINT(1) NULL DEFAULT NULL,
  `PetsAllowed` TINYINT(1) NULL DEFAULT NULL,
  `GarbageDisposal` TINYINT(1) NULL DEFAULT NULL,
  `Refrigerator` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`AmenitiesID`),
  INDEX `loc_idx` (`LocationID` ASC) VISIBLE,
  CONSTRAINT `loc`
    FOREIGN KEY (`LocationID`)
    REFERENCES `group6`.`location` (`LocationID`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`apartment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`apartment` (
  `ApartmentID` INT NOT NULL,
  `ApartmentNumber` INT NULL DEFAULT NULL,
  `BuildingID` INT NULL DEFAULT NULL,
  `Bedrooms` INT NULL DEFAULT NULL,
  `Bathrooms` INT NULL DEFAULT NULL,
  `Price` DECIMAL(10,2) NULL DEFAULT NULL,
  `LocationID` INT NOT NULL,
  `LeaseOptions` VARCHAR(45) NULL DEFAULT NULL,
  `isLeased` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`ApartmentID`),
  INDEX `locid_idx` (`LocationID` ASC) VISIBLE,
  CONSTRAINT `locid`
    FOREIGN KEY (`LocationID`)
    REFERENCES `group6`.`location` (`LocationID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`house`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`house` (
  `HouseID` INT NOT NULL,
  `Bedrooms` INT NULL DEFAULT NULL,
  `Bathrooms` INT NULL DEFAULT NULL,
  `Price` DECIMAL(10,2) NULL DEFAULT NULL,
  `LeaseOptions` VARCHAR(45) NULL DEFAULT NULL,
  `LocationID` INT NOT NULL,
  `isLeased` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`HouseID`),
  INDEX `fk_HOUSE_LOCATION1_idx` (`LocationID` ASC) VISIBLE,
  CONSTRAINT `fk_HOUSE_LOCATION1`
    FOREIGN KEY (`LocationID`)
    REFERENCES `group6`.`location` (`LocationID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`user` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `FirstName` TEXT NOT NULL,
  `LastName` TEXT NOT NULL,
  `PhoneNumber` BIGINT NOT NULL,
  `Email` VARCHAR(40) NOT NULL,
  `City` TEXT NULL DEFAULT NULL,
  `State` TEXT NULL DEFAULT NULL,
  `Zip` INT NULL DEFAULT NULL,
  `StreetAddress` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(15) NULL DEFAULT NULL,
  `isAdmin` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `PhoneNumber_UNIQUE` (`PhoneNumber` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`application` (
  `ApplicationID` INT NOT NULL AUTO_INCREMENT,
  `MainUserID` INT NULL DEFAULT NULL,
  `Income` DECIMAL(10,2) NOT NULL,
  `ApartmentID` INT NULL DEFAULT NULL,
  `HouseID` INT NULL DEFAULT NULL,
  `ApplicationStatus` TINYINT(1) NULL DEFAULT NULL,
  `LeaseOption` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`ApplicationID`),
  INDEX `apartmentid_idx` (`ApartmentID` ASC) VISIBLE,
  INDEX `houseid_idx` (`HouseID` ASC) VISIBLE,
  INDEX `mainuser_idx` (`MainUserID` ASC) VISIBLE,
  CONSTRAINT `apartmentid`
    FOREIGN KEY (`ApartmentID`)
    REFERENCES `group6`.`apartment` (`ApartmentID`),
  CONSTRAINT `houseid`
    FOREIGN KEY (`HouseID`)
    REFERENCES `group6`.`house` (`HouseID`),
  CONSTRAINT `mainuser`
    FOREIGN KEY (`MainUserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`application_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`application_has_user` (
  `UserAppID` INT NOT NULL AUTO_INCREMENT,
  `ApplicationID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`UserAppID`),
  INDEX `user_idx` (`UserID` ASC) VISIBLE,
  INDEX `appid_idx` (`ApplicationID` ASC) VISIBLE,
  CONSTRAINT `appid`
    FOREIGN KEY (`ApplicationID`)
    REFERENCES `group6`.`application` (`ApplicationID`),
  CONSTRAINT `userapp`
    FOREIGN KEY (`UserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`bill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`bill` (
  `BillID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NULL DEFAULT NULL,
  `BillType` VARCHAR(45) NULL DEFAULT NULL,
  `PaymentStatus` TINYINT(1) NULL DEFAULT '0',
  `PenaltyFee` DECIMAL(10,2) NULL DEFAULT NULL,
  `Amount` DECIMAL(10,2) NULL DEFAULT NULL,
  `ApartmentID` INT NULL DEFAULT NULL,
  `HouseID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`BillID`),
  INDEX `bill_user_idx` (`UserID` ASC) VISIBLE,
  INDEX `billapartment_idx` (`ApartmentID` ASC) VISIBLE,
  INDEX `billhouse_idx` (`HouseID` ASC) VISIBLE,
  CONSTRAINT `bill_user`
    FOREIGN KEY (`UserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`contract`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`contract` (
  `ContractID` INT NOT NULL AUTO_INCREMENT,
  `Duration` VARCHAR(20) NULL DEFAULT NULL,
  `Cost` DECIMAL(10,2) NULL DEFAULT NULL,
  `UserID` INT NOT NULL,
  `ApartmentID` INT NULL DEFAULT NULL,
  `HouseID` INT NULL DEFAULT NULL,
  `LateRentPenalty` DOUBLE(10,2) NULL DEFAULT NULL,
  `EarlyTpenalty` DOUBLE(10,2) NULL DEFAULT NULL,
  `Status` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`ContractID`),
  INDEX `fk_CONTRACT_APARTMENT1_idx` (`ApartmentID` ASC) VISIBLE,
  INDEX `fk_CONTRACT_HOUSE1_idx` (`HouseID` ASC) VISIBLE,
  INDEX `user_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_CONTRACT_APARTMENT1`
    FOREIGN KEY (`ApartmentID`)
    REFERENCES `group6`.`apartment` (`ApartmentID`),
  CONSTRAINT `fk_CONTRACT_HOUSE1`
    FOREIGN KEY (`HouseID`)
    REFERENCES `group6`.`house` (`HouseID`),
  CONSTRAINT `user1`
    FOREIGN KEY (`UserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`landmarks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`landmarks` (
  `LandMarkID` INT NOT NULL,
  `LandMarkName` VARCHAR(40) NULL DEFAULT NULL,
  PRIMARY KEY (`LandMarkID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`dfromlandmark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`dfromlandmark` (
  `LandMarkID` INT NOT NULL,
  `Distance` DECIMAL(10,2) NULL DEFAULT NULL,
  `LocationID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`LandMarkID`),
  INDEX `locationid_idx` (`LocationID` ASC) VISIBLE,
  CONSTRAINT `landmarkid`
    FOREIGN KEY (`LandMarkID`)
    REFERENCES `group6`.`landmarks` (`LandMarkID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `locationid`
    FOREIGN KEY (`LocationID`)
    REFERENCES `group6`.`location` (`LocationID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`lease`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`lease` (
  `LeaseID` INT NOT NULL AUTO_INCREMENT,
  `LeaseOption` VARCHAR(45) NULL DEFAULT NULL,
  `ApartmentID` INT NULL DEFAULT NULL,
  `HouseID` INT NULL DEFAULT NULL,
  `rentAmount` DOUBLE(10,2) NULL DEFAULT NULL,
  `MainUserID` INT NULL DEFAULT NULL,
  `Status` TINYINT(1) NULL DEFAULT '1',
  PRIMARY KEY (`LeaseID`),
  INDEX `fk_LEASE_APARTMENT1_idx` (`ApartmentID` ASC) VISIBLE,
  INDEX `fk_LEASE_HOUSE1_idx` (`HouseID` ASC) VISIBLE,
  INDEX `mainuserid_idx` (`MainUserID` ASC) VISIBLE,
  CONSTRAINT `fk_LEASE_APARTMENT1`
    FOREIGN KEY (`ApartmentID`)
    REFERENCES `group6`.`apartment` (`ApartmentID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_LEASE_HOUSE1`
    FOREIGN KEY (`HouseID`)
    REFERENCES `group6`.`house` (`HouseID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `mainuserid`
    FOREIGN KEY (`MainUserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`lease_has_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`lease_has_user` (
  `UserLeaseID` INT NOT NULL AUTO_INCREMENT,
  `LeaseID` INT NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`UserLeaseID`),
  INDEX `fk_LEASE_has_USER_LEASE1_idx` (`LeaseID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `group6`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `group6`.`review` (
  `ReviewID` INT NOT NULL AUTO_INCREMENT,
  `Score` INT NULL DEFAULT NULL,
  `UserID` INT NOT NULL,
  `ApartmentID` INT NULL DEFAULT NULL,
  `HouseID` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ReviewID`),
  INDEX `fk_REVIEW_APARTMENT1_idx` (`ApartmentID` ASC) VISIBLE,
  INDEX `fk_REVIEW_HOUSE1_idx` (`HouseID` ASC) VISIBLE,
  INDEX `user_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `fk_REVIEW_APARTMENT1`
    FOREIGN KEY (`ApartmentID`)
    REFERENCES `group6`.`apartment` (`ApartmentID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_REVIEW_HOUSE1`
    FOREIGN KEY (`HouseID`)
    REFERENCES `group6`.`house` (`HouseID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user`
    FOREIGN KEY (`UserID`)
    REFERENCES `group6`.`user` (`UserID`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;