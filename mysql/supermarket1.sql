/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.18 : Database - supermarket2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`supermarket2` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `supermarket2`;

/*Table structure for table `instore` */

DROP TABLE IF EXISTS `instore`;

CREATE TABLE `instore` (
  `inname` varchar(50) COLLATE utf8_bin NOT NULL,
  `inamount` int(11) DEFAULT NULL,
  `intime` date DEFAULT NULL,
  `inprice` float DEFAULT NULL,
  `infactory` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`inname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `instore` */

/*Table structure for table `outstore` */

DROP TABLE IF EXISTS `outstore`;

CREATE TABLE `outstore` (
  `outname` varchar(50) COLLATE utf8_bin NOT NULL,
  `outamount` int(11) DEFAULT NULL,
  `outtime` date DEFAULT NULL,
  `outprice` float DEFAULT NULL,
  `outreason` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `outfactory` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`outname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `outstore` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `productid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `productname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `orignalprice` float DEFAULT NULL,
  `promoteprice` float DEFAULT NULL,
  `factory` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  `guarantee` int(10) DEFAULT NULL,
  PRIMARY KEY (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `product` */

insert  into `product`(`productid`,`productname`,`orignalprice`,`promoteprice`,`factory`,`createdate`,`guarantee`) values 
('3c728736-1ff2-11eb-904a-54ee75c650f0','美好火腿肠',1.5,2.5,'成都希望食品有限公司','2020-10-09',180),
('40aa9a5c-1f49-11eb-904a-54ee75c650f0','农夫山泉',0.8,2,'农夫山泉股份有限公司','2020-10-17',720),
('40bf8761-1f49-11eb-904a-54ee75c650f0','营养快线',3,4.5,'娃哈哈集团','2020-10-18',270),
('40c70ccc-1f49-11eb-904a-54ee75c650f0','可口可乐(听装)',1.2,2,'可口可乐公司','2020-10-19',365),
('4d45ad10-1f46-11eb-904a-54ee75c650f0','雪碧(听装)',1.1,2,'可口可乐公司','2020-10-10',365),
('9334907d-1fe6-11eb-904a-54ee75c650f0','百事可乐(听装)',1.2,2,'百事集团','2020-09-10',365),
('b1d6cad3-1f48-11eb-904a-54ee75c650f0','伊利纯牛奶',1.5,3,'伊利实业集团','2020-09-16',180),
('dab7877b-1126-11eb-900b-54ee75c650f0','蒙牛纯牛奶',1.5,3,'蒙牛实业集团','2020-09-09',180),
('daf5550d-1126-11eb-900b-54ee75c650f0','老干妈',5.8,8.8,'老干妈风味食品有限公司','2020-09-30',365),
('daffbdc9-1126-11eb-900b-54ee75c650f0','暴下饭',4.6,7.9,'吉香居食品有限公司','2020-10-17',365),
('db173157-1126-11eb-900b-54ee75c650f0','红牛',4.2,6,'红牛维他命饮料有限公司','2020-10-01',540),
('e5c73f15-1125-11eb-900b-54ee75c650f0','康师傅牛肉面',2.5,4,'康师傅控股有限公司','2020-09-22',540),
('fbf4fd0f-1f49-11eb-904a-54ee75c650f0','汤达人',3,5,'统一集团','2020-09-13',540),
('fc30955c-1f49-11eb-904a-54ee75c650f0','子弟薯片',1.3,3,'子弟食品有限公司','2020-10-12',270),
('fc382026-1f49-11eb-904a-54ee75c650f0','大波浪薯片',1.5,3.5,'百事集团','2020-11-01',270);

/*Table structure for table `provider` */

DROP TABLE IF EXISTS `provider`;

CREATE TABLE `provider` (
  `providerid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `providername` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`providerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `provider` */

/*Table structure for table `singlesale` */

DROP TABLE IF EXISTS `singlesale`;

CREATE TABLE `singlesale` (
  `saleid` varchar(50) COLLATE utf8_bin NOT NULL,
  `saleuser` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `saletime` date DEFAULT NULL,
  `salename` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `saleamount` int(11) DEFAULT NULL,
  PRIMARY KEY (`saleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `singlesale` */

insert  into `singlesale`(`saleid`,`saleuser`,`saletime`,`salename`,`saleamount`) values 
('17954cb3-1ea9-11eb-904a-54ee75c650f0','1','2020-11-04','康师傅牛肉面',2),
('1f000633-1ea9-11eb-904a-54ee75c650f0','1','2020-11-04','伊利纯牛奶',2),
('2a4ea537-1ea9-11eb-904a-54ee75c650f0','2','2020-11-04','蒙牛纯牛奶',1),
('b5455ff5-1ea8-11eb-904a-54ee75c650f0','0','2020-11-04','老干妈',2),
('f31476a7-1ea8-11eb-904a-54ee75c650f0','0','2020-11-04','暴下饭',1);

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `storeid` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `storetime` date DEFAULT NULL,
  `storename` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `isup` int(1) DEFAULT '0',
  `amount` int(11) DEFAULT NULL,
  `badamount` int(11) DEFAULT '0',
  `upperlimit` int(11) DEFAULT '0',
  `lowerlimit` int(11) DEFAULT '1',
  PRIMARY KEY (`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `store` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '123456',
  `power` tinyint(1) DEFAULT '0',
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(4) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `testcode` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '-1',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`power`,`nickname`,`phone`,`sex`,`email`,`testcode`) values 
('123456','123123',0,'zss','17302293317','man','1250913438@qq.com',''),
('1250913438','123456',1,'mountious',NULL,NULL,NULL,'-1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
