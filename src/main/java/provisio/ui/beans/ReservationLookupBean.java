package provisio.ui.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.*;

/* Methods used to get and set variable values based on reservation ID entered in the manage reservation page,
 * search for reservation,
 * and re-direct the user to the reservation confirmation page*/

@ManagedBean(name = "reservationLookupBean")
@ViewScoped
public class ReservationLookupBean {
	public Integer reservationId;
	public String lastName;
	public String emailAddress;

	private String WIFI_DESCRIPTION = "Wi-Fi";
	private String BREAKFAST_DESCRIPTION = "Breakfast";
	private String PARKING_DESCRIPTION = "Parking";

	@ManagedProperty(value = "#{reservationBean}")
	ReservationBean reservationBean;

	public ReservationLookupBean() {
	}

	public String search() {

		ReservationLookupDao resLookupDao = new ReservationLookupDao();

		Reservation reservationResult = null;

		if (reservationId != null) {
			reservationResult = resLookupDao.lookupReservationById(new Integer(getReservationId()));
		}

		if (reservationResult == null && lastName != null) {
			reservationResult = resLookupDao.lookupReservationByLastName(lastName);
		}
		
		if ((reservationResult == null || (reservationResult != null && reservationResult.getReservationId() == null)) && emailAddress != null) {
			reservationResult = resLookupDao.lookupReservationByEmailAddress(emailAddress);
		}

		if (reservationResult != null) {

			reservationBean.setReservation(reservationResult);

			Hotel reservationHotel = resLookupDao.lookupHotel(reservationResult.getHotelCode());
			reservationBean.setReservationHotel(reservationHotel);

			Integer customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(reservationResult.getCustomerId());
			reservationBean.setCustomerTotalPoints(customerTotalPoints);

			String reservationRoomSize = resLookupDao.lookupRoomSize(reservationResult.getRoomId());
			reservationBean.setReservationRoomSize(reservationRoomSize);

			List<ReservationAmenity> reservationAmenities = resLookupDao
					.lookupReservationAmenities(reservationResult.getReservationId());
			List<String> resAmDescriptions = new ArrayList<>();

			for (ReservationAmenity resAmenity : reservationAmenities) {
				if (resAmenity.getAmenityId() == 1) {
					resAmDescriptions.add(WIFI_DESCRIPTION);
				}

				if (resAmenity.getAmenityId() == 2) {
					resAmDescriptions.add(BREAKFAST_DESCRIPTION);
				}

				if (resAmenity.getAmenityId() == 3) {
					resAmDescriptions.add(PARKING_DESCRIPTION);
				}
			}
			reservationBean.setResAmDescriptions(resAmDescriptions);
		}
		
		return "reservation-confirmation?faces-redirect=true";

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

	public ReservationBean getReservationBean() {
		return reservationBean;
	}

	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}

}
