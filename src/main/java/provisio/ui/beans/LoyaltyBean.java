package provisio.ui.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.Customer;
import provisio.db.model.Reservation;

@ManagedBean(name = "loyaltyBean")
@RequestScoped
public class LoyaltyBean {
	@ManagedProperty(value = "#{loginBean.customer}")
	Customer customer;

	List<Reservation> customerReservations;
	ReservationLookupDao reservationLookupDao = new ReservationLookupDao();
	
	public LoyaltyBean() {
	}

	public List<Reservation> getCustomerReservations() {
		return reservationLookupDao.lookupReservationsByCustomerId(customer.getCustomerId());
	}

	public void setCustomerReservations(List<Reservation> customerReservations) {
		this.customerReservations = customerReservations;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
