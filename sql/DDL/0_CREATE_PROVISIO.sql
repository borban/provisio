/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/
DROP DATABASE IF EXISTS `provisio`;
CREATE DATABASE `provisio`;

DROP USER 'provisio'@'localhost';
CREATE USER 'provisio'@'localhost' IDENTIFIED BY 'provisio';
GRANT ALL PRIVILEGES ON provisio.* TO 'provisio'@'localhost';

FLUSH PRIVILEGES;