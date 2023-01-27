package provisio.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import provisio.db.model.Customer;

public class LoginDao {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private Customer customerLogin = new Customer();
	
	public Customer getCustomerLogin(String username) {
		if (username != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Email, Password from CUSTOMER WHERE Email = '" + username + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					rs.next();
					customerLogin.setEmail(rs.getString("Email")); ;
					customerLogin.setPassword(rs.getString("Password"));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return customerLogin;
	}
}
