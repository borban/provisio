/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.AMENITIES;

CREATE TABLE provisio.AMENITIES (
  Amenity_Id int(10) NOT NULL,
  Description varchar(100),
  Price numeric(19,2),
  PRIMARY KEY (Amenity_Id),
  KEY Amenity_Id_Key (Amenity_Id)
  );
