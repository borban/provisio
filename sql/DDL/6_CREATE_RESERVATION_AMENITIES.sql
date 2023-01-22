/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.RESERVATION_AMENITIES;

CREATE TABLE provisio.RESERVATION_AMENITIES (
  Res_Amenity_Id integer(10) NOT NULL,
  Amenity_Id integer(10) NOT NULL,
  Reservation_Id integer(10) NOT NULL,
  Quantity integer(1),
  PRIMARY KEY (Res_Amenity_Id),
  KEY Res_Amenity_Id_Key (Res_Amenity_Id),
  CONSTRAINT FK_Amenity_Id FOREIGN KEY (Amenity_Id) REFERENCES Amenities(Amenity_Id),
  CONSTRAINT FK_Reservation_Id FOREIGN KEY (Reservation_Id) REFERENCES Reservation(Reservation_Id)
  );
