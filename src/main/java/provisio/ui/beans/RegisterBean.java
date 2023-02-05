package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import provisio.db.dao.RegisterDao;
import provisio.db.model.Customer;

/* Methods used to validate if customer already exists in db to prevent duplicate registration,
 * re-direct to success_Registration and unsuccessfule_Registration pages based on validation results,
 * and get and set customer.*/

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {
	public Customer customer = new Customer();
	private RegisterDao registerDao = new RegisterDao();

	public String register() {
		Customer dbCustomerRegister = registerDao.getCustomerRegister(customer.getEmail());
		if(!doesExist(dbCustomerRegister)) {
			if (registerDao.addCustomer(customer)) {
			return "successful_Registration";
			}
			else {
			return "unsuccessful_Registration";
		}
		}
		return "unsuccessful_Registration";
	}
	
	private boolean doesExist(Customer dbCustomerRegister) {
		if (dbCustomerRegister.getEmail() != null) {
			return customer.getEmail().equals(dbCustomerRegister.getEmail());
		}
		// TODO Auto-generated method stub
		return false;
		}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
