package provisio.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import provisio.db.model.Hotel;

/*Methods used to connect to the database, query all data from the HOTEL table,
 * and add to array list for display on locations page */

@ManagedBean(name="HotelDao")
@SessionScoped

public class HotelDao {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private DataSource ds;

	public HotelDao(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mkyongdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public List<Hotel> gethotelList1() { 
		List<Hotel> hotelList = new ArrayList<Hotel>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				String sql = "SELECT * from HOTEL where Hotel_Code in ('1', '2', '3', '4')";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				Hotel h;

				while (rs.next()) {
					h = new Hotel();
					h.setHotelCode(rs.getLong("Hotel_Code"));
					h.setName(rs.getString("Name"));
					h.setAddress(rs.getString("Address"));
					h.setCity(rs.getString("City"));
					h.setState(rs.getString("State"));
					h.setZip(rs.getString("Zip"));
					h.setPhoneNumber(rs.getString("Phone_Number"));
					hotelList.add(h);
				} 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Database query error");
		}
		return hotelList;
	}
	public List<Hotel> gethotelList2() { 
		List<Hotel> hotelList = new ArrayList<Hotel>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				String sql = "SELECT * from HOTEL where Hotel_Code in ('5', '6', '7', '8')";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				Hotel h;

				while (rs.next()) {
					h = new Hotel();
					h.setHotelCode(rs.getLong("Hotel_Code"));
					h.setName(rs.getString("Name"));
					h.setAddress(rs.getString("Address"));
					h.setCity(rs.getString("City"));
					h.setState(rs.getString("State"));
					h.setZip(rs.getString("Zip"));
					h.setPhoneNumber(rs.getString("Phone_Number"));
					hotelList.add(h);
				} 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Database query error");
		}
		return hotelList;
	}

	public List<Hotel> gethotelList3() { 
		List<Hotel> hotelList = new ArrayList<Hotel>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			if (conn != null) {
				String sql = "SELECT * from HOTEL where Hotel_Code in ('9', '10', '11', '12')";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				Hotel h;

				while (rs.next()) {
					h = new Hotel();
					h.setHotelCode(rs.getLong("Hotel_Code"));
					h.setName(rs.getString("Name"));
					h.setAddress(rs.getString("Address"));
					h.setCity(rs.getString("City"));
					h.setState(rs.getString("State"));
					h.setZip(rs.getString("Zip"));
					h.setPhoneNumber(rs.getString("Phone_Number"));
					hotelList.add(h);
				} 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Database query error");
		}
		return hotelList;
	}
}