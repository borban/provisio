package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.LoginDao;
import provisio.db.model.Customer;

@ManagedBean(name="login", eager= true)
@SessionScoped
public class LoginBean {
	static final String DB_URL = "jdbc:mysql://localhost:3306/provisio";
	static final String USER = "provisio";
	static final String PASS = "provisio";
	public Customer customer = new Customer();
	private LoginDao loginDao = new LoginDao();

	public String login() {
		Customer dbCustomerLogin = loginDao.getCustomerLogin(customer.getEmail());
		if (customer.getEmail().equals(dbCustomerLogin.getEmail()) && customer.getPassword().equals(dbCustomerLogin.getPassword())) {
			return "output";
		} else
			return "invalid";
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
