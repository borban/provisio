/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.AMENTITIES;

CREATE TABLE provisio.AMENTITIES (
  Amentity_Id int(10) NOT NULL,
  Reservation_Id int(10) NOT NULL,
  Description varchar(100),
  Quantity int(1),
  Price_Each numeric(19,2),
  PRIMARY KEY (Amentity_Id),
  KEY Amentity_Id_Key (Amentity_Id)
  );