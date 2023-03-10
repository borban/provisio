/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.HOTEL;

CREATE TABLE provisio.HOTEL (
  Hotel_Code int(10) NOT NULL,
  Name varchar(50) NOT NULL,
  Address varchar(50) NOT NULL,
  City varchar(50) NOT NULL,
  State varchar(2) NOT NULL,
  Zip varchar(10) Not NULL,
  Phone_Number varchar(20) Not NULL,
  PRIMARY KEY (Hotel_Code),
  KEY Hotel_Code_key (Hotel_Code)
  );
 
