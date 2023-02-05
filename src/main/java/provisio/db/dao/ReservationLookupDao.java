package provisio.db.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import provisio.db.model.*;

/*Methods used to connect to the database, 
 *look up / query a reservation based on the reservation ID,
 *look up / query the hotel, room size, amenities, and loyalty points for display to the user*/

public class ReservationLookupDao {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private Reservation reservation = new Reservation();
	private Hotel reservationHotel = new Hotel();

	public Reservation lookupReservation(Integer reservationId, String lastName, String emailAddress) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (reservationId != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Number_Of_Nights, Number_Of_Guests, Amount_Due,Loyalty_Points_Earned, Check_In_Date, Check_Out_Date FROM Reservation WHERE Reservation_Id = "
							+ reservationId;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						reservation.setReservationId(rs.getInt("Reservation_Id"));
						reservation.setCustomerId(rs.getInt("Customer_Id"));
						reservation.setHotelCode(rs.getInt("Hotel_Code"));
						reservation.setRoomId(rs.getInt("Room_Id"));
						reservation.setNumberOfNights(rs.getString("Number_Of_Nights"));
						reservation.setNumberOfGuests(rs.getString("Number_Of_Guests"));
						reservation.setAmountDue(new BigDecimal(rs.getString("Amount_Due")));
						reservation.setLoyaltyPointsEarned(rs.getInt("Loyalty_Points_Earned"));
						reservation.setCheckInDate(rs.getDate("Check_In_Date"));
						reservation.setCheckOutDate(rs.getDate("Check_Out_Date"));
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return reservation;
	}

	public Hotel lookupHotel(Integer hotelCode) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (hotelCode != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Name, Address, City, State, Zip, Phone_Number FROM Hotel WHERE Hotel_Code = "
							+ hotelCode;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						reservationHotel.setName(rs.getString("Name"));
						reservationHotel.setAddress(rs.getString("Address"));
						reservationHotel.setCity(rs.getString("City"));
						reservationHotel.setState(rs.getString("State"));
						reservationHotel.setZip(rs.getString("Zip"));
						reservationHotel.setPhoneNumber(rs.getString("Phone_Number"));
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return reservationHotel;
	}

	public Integer lookupTotalLoyaltyPoints(Integer customerId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (customerId != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Email, First_Name, Last_Name, Total_Loyalty_Points FROM Customer WHERE Customer_Id = "
							+ customerId;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						return rs.getInt("Total_Loyalty_Points");
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return null;
	}

	public String lookupRoomSize(Integer roomId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (roomId != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Room_Size FROM Room WHERE Room_Id = " + roomId;
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if (rs.next()) {
						return rs.getString("Room_Size");
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return null;
	}

	public Integer lookupLastInsertedReservation() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				String sql = "SELECT Reservation_Id FROM Reservation ORDER BY Reservation_Id DESC LIMIT 1";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					return rs.getInt("Reservation_Id");
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return null;
	}

	public List<ReservationAmenity> lookupReservationAmenities(Integer reservationId) {
		List<ReservationAmenity> resAmList = new ArrayList<ReservationAmenity>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				String sql = "SELECT Amenity_Id FROM Reservation_Amenities WHERE Reservation_Id=" + reservationId;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				ReservationAmenity resAm;
				while (rs.next()) {
					resAm = new ReservationAmenity();
					resAm.setAmenityId(rs.getInt("Amenity_Id"));
					resAmList.add(resAm);
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return resAmList;
	}
}
