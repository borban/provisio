package provisio.ui.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.ReservationDao;
import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.*;

@ManagedBean(name = "reservationBean", eager=true)
@ViewScoped
public class ReservationBean {
	public Reservation reservation = new Reservation();
	private ReservationDao reservationDao = new ReservationDao();
	public ReservationAmenities reservationAmenities = new ReservationAmenities();
	public String[] resAmArray;
	public java.util.Date reservationCheckInDate = new java.util.Date();
	public java.util.Date reservationCheckOutDate = new java.util.Date();
	private static Map<String, Object> locationValue;
	private static Map<String, Object> roomValue;
	private static Map<String, Object> numOfGuestsValue;
	public String reservationEmail;

	public String bookReservation() {
		setupReservation();
		if (reservationDao.addReservation(getReservation())) {
			setupDataForSummaryPage();
			return "reservation-summary?faces-redirect=true";
		} else {
			System.out.println("Reservation insert failed");
			return "";
		}
	}

	private void setupDataForSummaryPage() {
		ReservationLookupDao resLookupDao = new ReservationLookupDao();
		Integer reservationId = resLookupDao.lookupLastInsertedReservation();
		Reservation reservationResult = resLookupDao.lookupReservation(new Integer(reservationId), null, null);
		Hotel reservationHotel = resLookupDao.lookupHotel(reservationResult.getHotelCode());
		Integer customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(reservationResult.getCustomerId());
		String reservationRoomSize = resLookupDao.lookupRoomSize(reservationResult.getRoomId());
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationResult", reservationResult);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationHotel", reservationHotel);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customerTotalPoints", customerTotalPoints);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationRoomSize", reservationRoomSize);
	}

	private void setupReservation()
	{
		convertDates();
		calculateNumOfNights();
		calculateAmountDue();
		calculateLoyaltyPointsEarned();
		reservation.setCustomerId(1);
	}

	private void convertDates() {
		reservation.setCheckInDate(convertDate(reservationCheckInDate));
		reservation.setCheckOutDate(convertDate(reservationCheckOutDate));
	}

	private void calculateNumOfNights() {
		LocalDate resCheckInLocal = reservationCheckInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate reservationCheckOutDate = reservationCheckInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		reservation.setNumberOfNights(String.valueOf(ChronoUnit.DAYS.between(resCheckInLocal,reservationCheckOutDate)));
	}
	
	private void calculateAmountDue() {
		Long numOfGuests = Long.valueOf(reservation.getNumberOfGuests());
		BigDecimal rate1 = new BigDecimal(115.00);
		BigDecimal rate2 = new BigDecimal(150.00);
		BigDecimal numOfNights = BigDecimal.valueOf(Long.valueOf(reservation.getNumberOfNights()));
		
		if(numOfGuests != null && numOfGuests < 3)
		{
			reservation.setAmountDue(numOfNights.multiply(rate1));
		}
		else {
			reservation.setAmountDue(numOfNights.multiply(rate2));
		}
		
	}
	
	private void calculateLoyaltyPointsEarned() {
		reservation.setLoyaltyPointsEarned("150");
	}
	
	private Date convertDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

	static {
		locationValue = new LinkedHashMap<String, Object>();
		locationValue.put("Littleton, CO", 1);
		locationValue.put("Maplewood, NJ", 2);
		locationValue.put("Wappingers Falls, NY", 3);
		locationValue.put("Chatsworth, GA", 4);
		locationValue.put("Fuquay Varina, NC", 5);
		locationValue.put("Inman, SC", 6);
		locationValue.put("Sebastian, FL", 7);
		locationValue.put("New Philadelphia, OH", 8);
		locationValue.put("Herndon, VA", 9);
		locationValue.put("Atwater, CA", 10);
		locationValue.put("Waldorf, MD", 11);
		locationValue.put("Prior Lake, MN", 12);
	}
	
	static {
		roomValue = new LinkedHashMap<String, Object>();
		roomValue.put("Double Full Beds", 1);
		roomValue.put("Queen Suite", 2);
		roomValue.put("Double Queen Beds", 3);
		roomValue.put("King Suite", 4);

	}
	
	static {
		numOfGuestsValue = new LinkedHashMap<String, Object>();
		numOfGuestsValue.put("1 - 2 Guests", "1");
		numOfGuestsValue.put("3 - 5 Guests", "3");
	}

	public Map<String, Object> getLocationValue() {
		return locationValue;
	}

	public Map<String, Object> getRoomValue() {
		return roomValue;
	}
	
	public Map<String, Object> getNumOfGuestsValue() {
		return numOfGuestsValue;
	}

	public String[] getResAmArray() {
		return resAmArray;
	}

	public void setResAmArray(String[] resAmArray) {
		this.resAmArray = resAmArray;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation res) {
		this.reservation = res;
	}

	public ReservationAmenities getReservationAmenities() {
		return reservationAmenities;
	}

	public void setReservationAmenities(ReservationAmenities resAm) {
		this.reservationAmenities = resAm;
	}

	public java.util.Date getReservationCheckInDate() {
		return reservationCheckInDate;
	}

	public void setReservationCheckInDate(java.util.Date reservationCheckInDate) {
		this.reservationCheckInDate = reservationCheckInDate;
	}

	public java.util.Date getReservationCheckOutDate() {
		return reservationCheckOutDate;
	}

	public void setReservationCheckOutDate(java.util.Date reservationCheckOutDate) {
		this.reservationCheckOutDate = reservationCheckOutDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
}
