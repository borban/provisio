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

@ManagedBean(name = "Customer") //A.R.
@RequestScoped //A.R.

public class Customer {
	private Long customerId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private Long totalLoyaltyPoints;
	private String memberStatus;
	
	/* added additional variables - A.R.*/
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	private String db_customerEmail = null;
	private String db_customerPassword = null;
	
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
	
	/*********method to add customer via registration form to DB added by A.R. ****************/
	public String add() {
		int i = 0;
		if (email != null) {
			PreparedStatement ps = null;
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				if (conn != null) {
					String sql = "INSERT INTO CUSTOMER(First_Name, Last_Name, Email, Password) VALUES(?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, firstName);
					ps.setString(2, lastName);
					ps.setString(3, email);
					ps.setString(4, password);
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
}
