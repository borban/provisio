package provisio.ui.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import provisio.db.model.Customer;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {
	public Customer customer = new Customer();
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";

	public String add() {
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
					String sql = "INSERT INTO CUSTOMER(First_Name, Last_Name, Email, Password, Total_Loyalty_Points, Member_Status) VALUES(?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, customer.getFirstName());
					ps.setString(2, customer.getLastName());
					ps.setString(3, customer.getEmail());
					ps.setString(4, customer.getPassword());
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
		if (i > 0) {
			return "successful_Registration";
		} else
			return "unsuccessful_Registration";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}