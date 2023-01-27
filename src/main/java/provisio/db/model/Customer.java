package provisio.db.model;

public class Customer {
	private Long customerId;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private Long totalLoyaltyPoints;
	private String memberStatus;
	
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
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", totalLoyaltyPoints=" + totalLoyaltyPoints + ", memberStatus="
				+ memberStatus + "]";
	}
	
}
