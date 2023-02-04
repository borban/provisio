package provisio.ui.beans;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import provisio.db.dao.LoginDao;
import provisio.db.dao.RegisterDao;
import provisio.db.dao.ReservationDao;
import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.*;

@ManagedBean(name = "reservationBean", eager = true)
@ViewScoped
public class ReservationBean {
	public Reservation reservation = new Reservation();
	private ReservationDao reservationDao = new ReservationDao();
	private ReservationLookupDao resLookupDao = new ReservationLookupDao();
	private LoginDao customerDao = new LoginDao();
	public Integer[] resAmenitySelections;
	public java.util.Date reservationCheckInDate = null;
	public java.util.Date reservationCheckOutDate = null;
	private static Map<String, Object> locationValue;
	private static Map<String, Object> roomValue;
	private static Map<String, Object> numOfGuestsValue;
	private static Map<String, Object> numOfNightsValue;
	public String reservationEmail;
	private static final BigDecimal NIGHTLY_RATE_1 = new BigDecimal(115.00);
	private static final BigDecimal NIGHTLY_RATE_2 = new BigDecimal(150.00);
	private static final Integer WIFI_AMENITY = 1;
	private static final Integer BREAKFAST_AMENITY = 2;
	private static final Integer PAKRING_AMENITY = 3;
	private String WIFI_DESCRIPTION = "Wi-Fi";
	private String BREAKFAST_DESCRIPTION = "Breakfast";
	private String PARKING_DESCRIPTION = "Parking";

	public ReservationBean() {

	}

	public String bookReservation() {
		setupReservation();
		if (reservationDao.addReservation(getReservation())) {
			Integer reservationId = resLookupDao.lookupLastInsertedReservation();
			addAmenities(reservationId);
			setupDataForSummaryPage(reservationId);
			return "reservation-summary?faces-redirect=true";
		} else {
			System.out.println("Reservation insert failed");
			return "";
		}
	}

	private void setupDataForSummaryPage(Integer reservationId) {
		Reservation reservationResult = resLookupDao.lookupReservation(new Integer(reservationId), null, null);
		Hotel reservationHotel = resLookupDao.lookupHotel(reservationResult.getHotelCode());
		Integer customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(reservationResult.getCustomerId());
		String reservationRoomSize = resLookupDao.lookupRoomSize(reservationResult.getRoomId());
		List<ReservationAmenity> reservationAmenities = resLookupDao.lookupReservationAmenities(reservationId);
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
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationResult", reservationResult);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationHotel", reservationHotel);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("customerTotalPoints",
				customerTotalPoints);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("reservationRoomSize",
				reservationRoomSize);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("resAmDescriptions", resAmDescriptions);
	}

	private void setupReservation() {
		checkIfExistingCustomer();
		convertDates();
		calculateAmountDue();
		calculateLoyaltyPointsEarned();
	}

	private void addAmenities(Integer reservationId) {
		ReservationAmenity reservationAmenity = new ReservationAmenity();

		for (Integer amenity : resAmenitySelections) {
			if (WIFI_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(WIFI_AMENITY);
				reservationAmenity.setReservationId(reservationId);
				reservationDao.addReservationAmenity(reservationAmenity);
			}

			if (BREAKFAST_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(BREAKFAST_AMENITY);
				reservationAmenity.setReservationId(reservationId);
				reservationDao.addReservationAmenity(reservationAmenity);
			}

			if (PAKRING_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(PAKRING_AMENITY);
				reservationAmenity.setReservationId(reservationId);
				reservationDao.addReservationAmenity(reservationAmenity);
			}
		}
	}

	/**
	 * Check if customer exists and if not, register their email with generic mock
	 * data.
	 * 
	 * Once registered, retrieve the ID of the newly created customer and add it to
	 * the reservation.
	 */
	private void checkIfExistingCustomer() {
		Customer customer = customerDao.getCustomerLogin(reservationEmail);
		RegisterDao registerDao = new RegisterDao();

		if (customer.getCustomerId() != null && customer.getCustomerId() > 0) {
			reservation.setCustomerId(customer.getCustomerId());
		} else {
			customer.setEmail(reservationEmail);
			customer.setFirstName("Not registerd");
			customer.setLastName("Not registerd");
			customer.setPassword("None");
			customer.setTotalLoyaltyPoints(0);
			customer.setMemberStatus("Active");

			if (registerDao.addCustomer(customer)) {
				reservation.setCustomerId(customerDao.getCustomerLogin(reservationEmail).getCustomerId());
			}
		}

	}

	private void convertDates() {
		reservation.setCheckInDate(convertDate(reservationCheckInDate));
		reservation.setCheckOutDate(convertDate(reservationCheckOutDate));
	}

	private void calculateAmountDue() {
		Long numOfGuests = Long.valueOf(reservation.getNumberOfGuests());
		BigDecimal numOfNights = BigDecimal.valueOf(Long.valueOf(reservation.getNumberOfNights()));

		if (numOfGuests != null && numOfGuests < 3) {
			reservation.setAmountDue(numOfNights.multiply(NIGHTLY_RATE_1));
		} else {
			reservation.setAmountDue(numOfNights.multiply(NIGHTLY_RATE_2));
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

	static {
		numOfNightsValue = new LinkedHashMap<String, Object>();
		numOfNightsValue.put("One night", "1");
		numOfNightsValue.put("Two nights", "2");
		numOfNightsValue.put("Three nights", "3");
		numOfNightsValue.put("Four nights", "4");
		numOfNightsValue.put("Five or more nights", "5");
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

	public Map<String, Object> getNumOfNightsValue() {
		return numOfNightsValue;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation res) {
		this.reservation = res;
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

	public Integer[] getResAmenitySelections() {
		return resAmenitySelections;
	}

	public void setResAmenitySelections(Integer[] resAmenitySelections) {
		this.resAmenitySelections = resAmenitySelections;
	}

}
