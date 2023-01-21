/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.CUSTOMER;

CREATE TABLE provisio.CUSTOMER (
  Customer_Id integer(10) NOT NULL,
  Email varchar(50),
  First_Name varchar(255) NOT NULL,
  Last_Name varchar(255) NOT NULL,
  Password varchar(75) NOT NULL,
  Total_Loyalty_Points int(100) NOT NULL,
  Member_Status varchar(255) NOT NULL,
  PRIMARY KEY (Customer_Id),
  KEY Customer_Id_Key (Customer_Id)
  );

