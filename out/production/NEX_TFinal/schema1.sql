DROP DATABASE IF EXISTS `nex_t`;
CREATE DATABASE `nex_t`;

USE `nex_t`;

DROP TABLE IF EXISTS `drivers`;
CREATE TABLE `drivers` (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    licensePlate VARCHAR(255),
    timeIn VARCHAR(255),
    timeOut VARCHAR(255),
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

INSERT INTO `users` (id, username, password) VALUES ('1', 'admin', 'admin');

DROP TABLE IF EXISTS `parking_lot`;
CREATE TABLE `parking_lot` (
    id VARCHAR(255) PRIMARY KEY,
    slotNumber INT CHECK(slotNumber >= 1 AND slotNumber <= 5),
    isOccupied BOOLEAN DEFAULT FALSE,
    driverId VARCHAR(255),
    FOREIGN KEY (driverId) REFERENCES drivers(id) ON UPDATE CASCADE ON DELETE SET NULL
);

INSERT INTO `parking_lot` (id, slotNumber, driverId) VALUES (1, 1, null);
INSERT INTO `parking_lot` (id, slotNumber, driverId) VALUES (2, 2, null);
INSERT INTO `parking_lot` (id, slotNumber, driverId) VALUES (3, 3, null);
INSERT INTO `parking_lot` (id, slotNumber, driverId) VALUES (4, 4, null);
INSERT INTO `parking_lot` (id, slotNumber, driverId) VALUES (5, 5, null);