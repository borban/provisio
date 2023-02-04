package provisio.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import provisio.db.model.Reservation;
import provisio.db.model.ReservationAmenity;


public class ReservationDao {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private Reservation customerReservation = new Reservation();
	
	public boolean addReservation(Reservation res) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		if (res.getReservationId() == null) {
			PreparedStatement ps = null;
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "INSERT INTO RESERVATION(Customer_Id, Hotel_Code, Room_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights,"
							+"Number_Of_Guests, Amount_Due, Loyalty_Points_Earned) VALUES(?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, res.getCustomerId());
					ps.setInt(2, res.getHotelCode());
					ps.setInt(3, res.getRoomId());
					ps.setDate(4, res.getCheckInDate());
					ps.setDate(5, res.getCheckOutDate());
					ps.setString(6, res.getNumberOfNights());
					ps.setString(7, res.getNumberOfGuests());
					ps.setBigDecimal(8, res.getAmountDue());
					ps.setString(9, res.getLoyaltyPointsEarned());
					i = ps.executeUpdate();
					System.out.println("Reservation Added Successfully");
				}
			}catch (SQLException e) {
				System.out.println(e);
				} finally {
					try {
				conn.close();
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
					}
			}
		}
		return i > 0;
			
	}
	
	public boolean addReservationAmenity(ReservationAmenity resAm) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		if (resAm.getReservationId() != null) {
			
			PreparedStatement ps1 = null;
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
				}
				String sql2 = "INSERT INTO reservation_amenities(Amenity_Id, Reservation_Id, Quantity) VALUES(?,?,?)";
				ps1 = conn.prepareStatement(sql2);;
				ps1.setInt(1, resAm.getAmenityId());
				ps1.setInt(2, resAm.getReservationId());
				ps1.setInt(3, 1);
				i = ps1.executeUpdate();
				System.out.println("Reservation_Amenities Added Successfully");
			
			}
			catch (SQLException e) {
				System.out.println(e);
			}finally {
				try {
					conn.close();
					ps1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	return i > 0;
		
	}
	
	public Reservation getCustomerReservation(int reservationId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (reservationId != 0) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Reservation_Id, Customer_Id, Hotel_Code, Room_Id, Check_In_Date, Check_Out_Date, Number_Of_Nights,"
							+"Number_Of_Guests, Amount_Due, Loyalty_Points_Earned from RESERVATION WHERE Reservation_Id = '" + reservationId + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next()) {
						customerReservation.setReservationId(rs.getInt("Reservation_Id"));
						customerReservation.setCustomerId(rs.getInt("Customer_Id"));
						customerReservation.setHotelCode(rs.getInt("Hotel_Code"));
						customerReservation.setRoomId(rs.getInt("Room_Id"));
						customerReservation.setCheckInDate(rs.getDate("Check_In_Date"));
						customerReservation.setCheckOutDate(rs.getDate("Check_Out_Date"));
						customerReservation.setNumberOfNights(rs.getString("Number_Of_Nights"));
						customerReservation.setNumberOfGuests(rs.getString("Number_Of_Guests"));
						customerReservation.setAmountDue(rs.getBigDecimal("Amount_Due"));
						customerReservation.setLoyaltyPointsEarned(rs.getString("Loyalty_Points_Earned"));
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return customerReservation;
	}
}
