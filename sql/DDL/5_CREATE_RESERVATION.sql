/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/
/* NEEDS ALL OTHER TABLES CREATED TO RUN */

DROP TABLE IF EXISTS provisio.RESERVATION;

CREATE TABLE provisio.RESERVATION (
  Reservation_Id integer(10) NOT NULL AUTO_INCREMENT,
  Customer_Id integer(10) NOT NULL,
  Hotel_Code integer(10) NOT NULL,
  Room_Id integer(10) NOT NULL,
  Res_Amenity_Id integer(10) NOT NULL,
  Check_In_Date date NOT NULL,
  Check_Out_Date date NOT NULL,
  Number_Of_Nights integer(2) NOT NULL,
  Number_Of_Guests integer(1) NOT NULL,
  Amount_Due numeric(19,2) NOT NULL,
  Loyalty_Points_Earned integer(100) NOT NULL,
  PRIMARY KEY (Reservation_Id), KEY Reservation_Id_key (Reservation_Id)
  CONSTRAINT FK_Customer_ID FOREIGN KEY (Customer_Id) REFERENCES Customer(Customer_Id),
  CONSTRAINT FK_Hotel_Code FOREIGN KEY(Hotel_Code) REFERENCES Hotel(Hotel_Code),
  CONSTRAINT FK_Room_ID FOREIGN KEY(Room_Id) REFERENCES Room(Room_ID)
  CONSTRAINT FK_Res_Amenity_ID FOREIGN KEY(Res_Amenity_ID) REFERENCES Reservation_Amenity (Res_Amenity_ID)	
  );
