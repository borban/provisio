package provisio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProvisioDataSource {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	
	public void getReservations() {
	      // Open a connection
	      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT * FROM RESERVATION");
	      ) {		      
	         while(rs.next()){
	            //Display values
	            System.out.print("ID: " + rs.getInt("Reservation_Id"));
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } 
	   }
}
