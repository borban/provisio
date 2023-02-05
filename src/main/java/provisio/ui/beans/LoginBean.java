package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.LoginDao;
import provisio.db.model.Customer;
import provisio.util.HashClass;

/*Methods used to get get customer email and password from the login form, 
 *validate if the email entered exists in the db, 
 *validate if the password entered is valid, 
 *redirect to login or login error pages based on validation,
 *and invalidate session upon logout.*/

@ManagedBean(name = "loginBean", eager = true)
@SessionScoped
public class LoginBean {
	public Customer customer = new Customer();
	private LoginDao loginDao = new LoginDao();

	public String login() {
		Customer dbCustomerLogin = loginDao.getCustomerLogin(customer.getEmail());
		if (isValidMember(dbCustomerLogin)) {
			customer.setFirstName(dbCustomerLogin.getFirstName());
			customer.setCustomerId(dbCustomerLogin.getCustomerId());
			return "login";
		} else
			return "login_error";
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
	}

	private boolean isValidMember(Customer dbCustomerLogin) {
		if (dbCustomerLogin.getEmail() != null) {
			return customer.getEmail().equals(dbCustomerLogin.getEmail())
					&& HashClass.hashValue(customer.getPassword()).equals(dbCustomerLogin.getPassword());
		}

		return false;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
