DROP DATABASE IF EXISTS `nex_t`;
CREATE DATABASE `nex_t`;

USE `nex_t`;

DROP TABLE IF EXISTS `drivers`;
CREATE TABLE `drivers` (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    licensePlate VARCHAR(255),
    timeIn VARCHAR(255),
    timeOut VARCHAR(255)
)
