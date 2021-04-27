/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.5.62 : Database - chatpro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`chatpro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `chatpro`;

/*Table structure for table `group` */

DROP TABLE IF EXISTS `group`;

CREATE TABLE `group` (
  `gID` int(4) NOT NULL,
  `group` varchar(15) NOT NULL,
  PRIMARY KEY (`gID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `group` */

/*Table structure for table `linkman` */

DROP TABLE IF EXISTS `linkman`;

CREATE TABLE `linkman` (
  `ID` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `telephone` varchar(20) NOT NULL,
  `QQ` varchar(15) NOT NULL,
  `email` varchar(20) NOT NULL,
  `birthday` datetime DEFAULT NULL,
  `Fname` varchar(5) DEFAULT NULL,
  `SortID` varchar(4) DEFAULT NULL,
  `group` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `telephone` (`telephone`,`QQ`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `linkman` */

insert  into `linkman`(`ID`,`name`,`sex`,`telephone`,`QQ`,`email`,`birthday`,`Fname`,`SortID`,`group`) values (18,'王俊钦','男','17819566267','446991363','446991363@qq.com','1999-01-21 00:00:00','w','0','家人'),(21,'王俊','男','13411903978','1532154121','1532154121@qq.com','1999-01-21 00:00:00','w','0','家人'),(22,'王俊','男','1341194522978','15321452121','15321541452@qq.com','1999-01-21 00:00:00','w','0','家人');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
