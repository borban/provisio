/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/
INSERT INTO RESERVATION (Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Res_Amenity_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights, Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) 
	VALUE (1,1,1,1,1, STR_TO_DATE('01-March-2023', '%d-%M-%Y'), STR_TO_DATE('03-March-2023', '%d-%M-%Y'), 2,2, 150.00, 300);    
INSERT INTO RESERVATION (Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Res_Amenity_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights, Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) 
	VALUE (2,2,2,1,1, STR_TO_DATE('01-September-2023', '%d-%M-%Y'), STR_TO_DATE('03-September-2023', '%d-%M-%Y'), 2,2, 279.99, 750);    
INSERT INTO RESERVATION (Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Res_Amenity_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights, Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) 
	VALUE (3,3,1,2,1, STR_TO_DATE('02-March-2023', '%d-%M-%Y'), STR_TO_DATE('04-March-2023', '%d-%M-%Y'), 2,2, 500.00, 450);    
INSERT INTO RESERVATION (Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Res_Amenity_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights, Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) 
	VALUE (4,4,3,1,1, STR_TO_DATE('01-January-2023', '%d-%M-%Y'), STR_TO_DATE('03-January-2023', '%d-%M-%Y'), 2,2, 250.00, 20);   
