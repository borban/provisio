/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/

DROP TABLE IF EXISTS provisio.ROOM;

CREATE TABLE provisio.ROOM (
  Room_Id int(10) NOT NULL,
  Room_Size varchar(50) NOT NULL,
  Hotel_Code int(10),
  PRIMARY KEY (Room_Id), KEY Room_Id_Key (Room_Id),
  CONSTRAINT FK_Hotel_Code FOREIGN KEY (Hotel_Code) REFERENCES Hotel(Hotel_Code)
  );
