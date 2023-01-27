package provisio.db.model;

import java.sql.Connection; //A.R.
import java.sql.DriverManager; //A.R.
import java.sql.PreparedStatement; //A.R.
import java.sql.ResultSet; //A.R.
import java.sql.SQLException; //A.R.

import javax.faces.bean.ManagedBean; //A.R.
import javax.faces.bean.RequestScoped; //A.R.
import javax.faces.context.FacesContext; //A.R.
import javax.naming.Context; //A.R.
import javax.naming.InitialContext; //A.R.
import javax.naming.NamingException; //A.R.
import javax.sql.DataSource; //A.R.

public class Customer {
	private Long customerId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private Long totalLoyaltyPoints;
	private String memberStatus;
	private String db_customerEmail = null;
	private String db_customerPassword = null;
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getTotalLoyaltyPoints() {
		return totalLoyaltyPoints;
	}
	public void setTotalLoyaltyPoints(Long totalLoyaltyPoints) {
		this.totalLoyaltyPoints = totalLoyaltyPoints;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	
	
	/******************dbData validate login password added by A.R.**********************/
	
	public void dbData(String uName) {
        if (uName != null) {
            PreparedStatement ps = null;
            Connection conn = null;
            ResultSet rs = null;

            try {
            	conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    if (conn != null) {
                        String sql = "SELECT Email, Password from CUSTOMER WHERE Email = '"
                                + uName + "'";
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
	
	/*******method to login added  by A.R***********/

	public String login() {
		dbData(db_customerEmail);
		if (email.equals(db_customerEmail) && password.equals(db_customerPassword)) {
			return "output";
		} else
			return "invalid";
	}
	
	/*******method to logout added by A.R.************/

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext()
		.invalidateSession();
		FacesContext.getCurrentInstance()
		.getApplication().getNavigationHandler()
		.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", totalLoyaltyPoints=" + totalLoyaltyPoints + ", memberStatus="
				+ memberStatus + ", db_customerEmail=" + db_customerEmail + ", db_customerPassword="
				+ db_customerPassword + "]";
	}
}
