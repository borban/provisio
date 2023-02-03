package provisio.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import provisio.db.model.Customer;
import provisio.util.HashClass;

public class RegisterDao {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private Customer customerRegister = new Customer();
	
	public boolean addCustomer(Customer customer) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		int i = 0;
		if (customer.getEmail() != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "INSERT INTO CUSTOMER(Customer_Id, First_Name, Last_Name, Email, Password, Total_Loyalty_Points, Member_Status) VALUES(?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, 0);
					ps.setString(1, customer.getFirstName());
					ps.setString(2, customer.getLastName());
					ps.setString(3, customer.getEmail());
					ps.setString(4, HashClass.hashValue(customer.getPassword()));
					ps.setInt(5, 0);
					ps.setString(6, "Active");
					i = ps.executeUpdate();
					System.out.println("Data Added Successfully");
				}
			}catch (Exception e) {
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
	public Customer getCustomerRegister(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		if (username != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT First_Name, Email, Password, Customer_Id from CUSTOMER WHERE Email = '" + username + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					if(rs.next()) {
						customerRegister.setFirstName(rs.getString("First_Name"));
						customerRegister.setEmail(rs.getString("Email"));
						customerRegister.setPassword(rs.getString("Password"));
						customerRegister.setCustomerId(rs.getInt("Customer_Id"));
					}
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return customerRegister;
	}
}
