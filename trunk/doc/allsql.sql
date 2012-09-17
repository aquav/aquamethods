-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.23 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4170
-- Date/time:                    2012-09-17 01:12:34
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for fashbookdb
CREATE DATABASE IF NOT EXISTS `fashbookdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fashbookdb`;


-- Dumping structure for table fashbookdb.outfit
CREATE TABLE IF NOT EXISTS `outfit` (
  `OUTFIT_ID` int(5) NOT NULL AUTO_INCREMENT,
  `OUTFIT_PICTURE` varchar(50) NOT NULL,
  `OUTFIT_DESCRIPTION` varchar(100) DEFAULT NULL,
  `PERSON_ID` int(5) NOT NULL,
  PRIMARY KEY (`OUTFIT_ID`),
  KEY `PERSON_ID_FK1` (`PERSON_ID`),
  CONSTRAINT `PERSON_ID_FK1` FOREIGN KEY (`PERSON_ID`) REFERENCES `person` (`PERSON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fashbookdb.person
CREATE TABLE IF NOT EXISTS `person` (
  `PERSON_ID` int(5) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(50) NOT NULL,
  `LAST_NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `AGE` int(3) NOT NULL,
  PRIMARY KEY (`PERSON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table fashbookdb.tag
CREATE TABLE IF NOT EXISTS `tag` (
  `TAG_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TAG` varchar(50) NOT NULL,
  `OUTFIT_ID` int(10) NOT NULL,
  PRIMARY KEY (`TAG_ID`),
  KEY `OUTFIT_ID_FK1` (`OUTFIT_ID`),
  CONSTRAINT `OUTFIT_ID_FK1` FOREIGN KEY (`OUTFIT_ID`) REFERENCES `outfit` (`OUTFIT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
