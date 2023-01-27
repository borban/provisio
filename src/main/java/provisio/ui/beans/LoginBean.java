package provisio.ui.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.context.FacesContext;

import provisio.db.model.Customer;

public class LoginBean {
	private String db_customerEmail = null;
	private String db_customerPassword = null;
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";

	public void dbData(String uName) {
		if (uName != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "SELECT Email, Password from CUSTOMER WHERE Email = '" + uName + "'";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					rs.next();
					db_customerEmail = rs.getString("Email");
					db_customerPassword = rs.getString("Password");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public String login(Customer customer) {
		dbData(db_customerEmail);
		if (customer.getEmail().equals(db_customerEmail) && customer.getPassword().equals(db_customerPassword)) {
			return "output";
		} else
			return "invalid";
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
	}
}
