package provisio.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.Hotel;
import provisio.db.model.Reservation;

@ManagedBean(name = "reservationLookupBean")
@ViewScoped
public class ReservationLookupBean {
	public Integer reservationId;
	public String lastName;
	public String emailAddress;
	
	public ReservationLookupBean() {
	}

	public String search() {
		if (reservationId != null) {
			ReservationLookupDao resLookupDao = new ReservationLookupDao();
			
			Reservation reservationResult = resLookupDao.lookupReservation(new Integer(getReservationId()), null, null);
			Hotel reservationHotel = resLookupDao.lookupHotel(reservationResult.getHotelCode());
			Integer customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(reservationResult.getCustomerId());
			String reservationRoomSize = resLookupDao.lookupRoomSize(reservationResult.getRoomId());
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationResult", reservationResult);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationHotel", reservationHotel);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customerTotalPoints", customerTotalPoints);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationRoomSize", reservationRoomSize);
			
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

}
