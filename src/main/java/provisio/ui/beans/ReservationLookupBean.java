package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.Reservation;

@ManagedBean(name = "reservationLookupBean")
@ViewScoped
public class ReservationLookupBean {
	public Integer reservationId;
	public String lastName;
	public String emailAddress;
	public Reservation reservationResult = new Reservation();
	private ReservationLookupDao resLookupDao = new ReservationLookupDao();

	public ReservationLookupBean() {
	}

	public String search() {
		if (reservationId != null) {
			reservationResult = resLookupDao.lookupReservation(new Integer(getReservationId()), null, null);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationResult", reservationResult);
			return "reservation-summary?faces-redirect=true";
		}
		
		return "";
		
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Reservation getReservationResult() {
		return reservationResult;
	}

	public void setReservationResult(Reservation reservationResult) {
		this.reservationResult = reservationResult;
	}

}
