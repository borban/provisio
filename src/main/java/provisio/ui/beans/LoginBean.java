package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.LoginDao;
import provisio.db.model.Customer;
import provisio.util.HashClass;

@ManagedBean(name="loginBean", eager= true)
@SessionScoped
public class LoginBean {
	public Customer customer = new Customer();
	private LoginDao loginDao = new LoginDao();

	public String login() {
		Customer dbCustomerLogin = loginDao.getCustomerLogin(customer.getEmail());
		if (customer.getEmail().equals(dbCustomerLogin.getEmail()) && HashClass.hashValue(customer.getPassword()).equals(dbCustomerLogin.getPassword())) {
			customer.setFirstName(dbCustomerLogin.getFirstName());
			customer.setCustomerId(dbCustomerLogin.getCustomerId());
			return "member_welcome";
		} else
			return "register";
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
