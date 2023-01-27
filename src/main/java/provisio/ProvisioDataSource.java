package provisio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import provisio.db.model.Reservation;

public class ProvisioDataSource {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";

	public List<Reservation> getReservations() {
		List<Reservation> reservations = new ArrayList<>();
		Reservation reservation;
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM RESERVATION");) {
			while (rs.next()) {
				reservation = new Reservation();
				reservation.setReservationId(rs.getInt("Reservation_Id"));
				reservation.setCustomerId(rs.getInt("Customer_Id"));
				reservation.setHotelCode(rs.getInt("Hotel_Code"));
				reservation.setRoomId(rs.getInt("Room_Id"));
				reservation.setCheckInDate(rs.getDate("Check_In_Date"));
				
				reservations.add(reservation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reservations;
	}
	
	/* Customer added by A.R. */
	public List<Customer> getCustomer() {
		List<Customer> customer = new ArrayList<>();
		Customer customer;
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM RESERVATION");) {
			while (rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setfirstName(rs.getString("firstName"));
				customer.setlastName(rs.getString("lastName"));
				customer.setpassword(rs.getString("password"));
				
				customer.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
