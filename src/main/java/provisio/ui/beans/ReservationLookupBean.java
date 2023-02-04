package provisio.ui.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.*;

@ManagedBean(name = "reservationLookupBean")
@ViewScoped
public class ReservationLookupBean {
	public Integer reservationId;
	public String lastName;
	public String emailAddress;
	
	private String WIFI_DESCRIPTION = "Wi-Fi";
	private String BREAKFAST_DESCRIPTION = "Breakfast";
	private String PARKING_DESCRIPTION = "Parking";
	
	public ReservationLookupBean() {
	}

	public String search() {
		if (reservationId != null) {
			ReservationLookupDao resLookupDao = new ReservationLookupDao();
			
			Reservation reservationResult = resLookupDao.lookupReservation(new Integer(getReservationId()), null, null);
			Hotel reservationHotel = resLookupDao.lookupHotel(reservationResult.getHotelCode());
			Integer customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(reservationResult.getCustomerId());
			String reservationRoomSize = resLookupDao.lookupRoomSize(reservationResult.getRoomId());
			List<ReservationAmenity> reservationAmenities = resLookupDao.lookupReservationAmenities(new Integer(getReservationId()));
			List<String> resAmDescriptions = new ArrayList<>();
			
			for(ReservationAmenity resAmenity : reservationAmenities)
			{
				if(resAmenity.getAmenityId() == 1)
				{
					resAmDescriptions.add(WIFI_DESCRIPTION);
				}
				
				if(resAmenity.getAmenityId() == 2)
				{
					resAmDescriptions.add(BREAKFAST_DESCRIPTION);
				}
				
				if(resAmenity.getAmenityId() == 3)
				{
					resAmDescriptions.add(PARKING_DESCRIPTION);
				}
			}
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationResult", reservationResult);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationHotel", reservationHotel);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customerTotalPoints", customerTotalPoints);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationRoomSize", reservationRoomSize);
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("resAmDescriptions", resAmDescriptions);
			
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
