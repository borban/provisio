/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.RESERVATION_AMENTITIES;

CREATE TABLE provisio.RESERVATION_AMENTITIES (
  Res_Amentity_Id integer(10) NOT NULL,
  Amentity_Id integer(10) NOT NULL,
  Reservation_Id integer(10) NOT NULL,
  Quantity integer(1),
  PRIMARY KEY (Res_Amentity_Id),
  KEY Amentity_Id_Key (Res_Amentity_Id),
  CONSTRAINT FK_Amentity_Id FOREIGN KEY (Amentity_Id) REFERENCES Amentities(Amentity_Id),
  CONSTRAINT FK_Reservation_Id FOREIGN KEY (Reservation_Id) REFERENCES Reservation(Reservation_Id)
  );