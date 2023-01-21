/*  Team 3: Alynna Rem, Jake Stevens, Mya Nanmoo, Benjamin Orban
	Bellevue University
	CSD-460 Capstone in Software Development
*/
insert into RESERVATION (Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights, Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) 
	values (1,1,1,1, STR_TO_DATE('01-March-2023', '%d-%M-%Y'), STR_TO_DATE('03-March-2023', '%d-%M-%Y'), 2,2, 150.00, 300);