package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import provisio.db.dao.RegisterDao;
import provisio.db.model.Customer;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean {
	public Customer customer = new Customer();
	private RegisterDao registerDao = new RegisterDao();

	public String register() {
		if(registerDao.addCustomer(customer)) {
			return "successful_Registration";
		}
		else {
			return "unsuccessful_Registration";
		}
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
