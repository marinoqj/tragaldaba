DROP DATABASE IF EXISTS `tragaldaba`;

CREATE DATABASE tragaldaba DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER 'tragaldaba'@'localhost' IDENTIFIED BY 'tragaldaba';

GRANT ALL PRIVILEGES ON * . * TO 'tragaldaba'@'localhost';

ALTER USER 'tragaldaba'@'localhost' IDENTIFIED WITH mysql_native_password BY 'tragaldaba';

--GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,CREATE TEMPORARY TABLES,DROP,INDEX,ALTER ON tragaldaba.* TO tragaldaba@localhost IDENTIFIED BY 'tragaldaba';