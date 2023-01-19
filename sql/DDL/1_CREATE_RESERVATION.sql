/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/
/* NEEDS ALL OTHER TABLES CREATED TO RUN */

DROP TABLE IF EXISTS provisio.RESERVATION;


CREATE TABLE provisio.RESERVATION (
  Reservation_ID timestamp NOT NULL,
  Customer_ID integer(10) NOT NULL,
  Hotel_Code integer(10) NOT NULL,
  Room_ID integer(10) NOT NULL,
  Check_In_Date date NOT NULL,
  Check_Out_Date date NOT NULL,
  Number_Of_Nights integer(2) NOT NULL,
  Number_Of_Guests integer(1) NOT NULL,
  Amount_Due numeric(19,0) NOT NULL,
  Loyalty_Points_Earned integer(100) NOT NULL,
  PRIMARY KEY (Reservation_ID),
  CONSTRAINT FK_Customer_ID FOREIGN KEY (Customer_ID)
  REFERENCES Customer(Customer_ID),
  CONSTRAINT FK_Hotel_Code FOREIGN KEY (Hotel_Code)
  REFERENCES Hotel(Hotel_Code),
  CONSTRAINT FK_Room_ID FOREIGN KEY (Room_ID)
  REFERENCES Room(Room_ID)
  );
