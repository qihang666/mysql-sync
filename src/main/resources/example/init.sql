 DROP DATABASE IF EXISTS `t1`;
CREATE DATABASE  IF NOT EXISTS `t1`;
use t1;
DROP TABLE IF EXISTS `data1`;
CREATE TABLE `data1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP DATABASE IF EXISTS `t2`;
CREATE DATABASE  IF NOT EXISTS `t2`;
use t2;
DROP TABLE IF EXISTS `data1`;
CREATE TABLE `data1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





 DROP DATABASE IF EXISTS `t3`;
CREATE DATABASE  IF NOT EXISTS `t3`;
use t3;
DROP TABLE IF EXISTS `data1`;
CREATE TABLE `data1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into t1.data1(name,pass,phone,age)values
("t1.name1","pass1","phone1","age1"),
("t1.name2","pass2","phone2","age2"),
("t1.name3","pass3","phone3","age3"),
("t1.name4","pass4","phone4","age4"),
("t1.name5","pass5","phone5","age5")
;

insert into t2.data1(name,pass,phone,age)values
("t2.name1","pass1","phone1","age1"),
("t2.name2","pass2","phone2","age2"),
("t2.name3","pass3","phone3","age3"),
("t2.name4","pass4","phone4","age4"),
("t2.name5","pass5","phone5","age5")
;

