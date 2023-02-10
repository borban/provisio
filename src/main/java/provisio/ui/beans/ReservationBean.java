package provisio.ui.beans;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import provisio.db.dao.ReservationDao;
import provisio.db.dao.ReservationLookupDao;
import provisio.db.model.*;

/* Methods used to re-direct the customer to the reservation summary page to submit or cancel the reservation,
 * re-direct the customer back to the reservation page if they click cancel,
 * calculate amount due and loyalty points,
 * convert date,
 * and get and set variable values based on input from the reservation form*/

@ManagedBean(name = "reservationBean", eager = true)
@SessionScoped
public class ReservationBean {
	@ManagedProperty(value = "#{loginBean.customer}")
	Customer customer;

	public Reservation reservation = new Reservation();
	private ReservationDao reservationDao = new ReservationDao();
	private ReservationLookupDao resLookupDao = new ReservationLookupDao();
	public Integer[] resAmenitySelections;
	public java.util.Date reservationCheckInDate = null;
	public java.util.Date reservationCheckOutDate = null;
	private static Map<String, Object> locationValue;
	private static Map<String, Object> roomValue;
	private static Map<String, Object> numOfGuestsValue;
	private static Map<String, Object> numOfNightsValue;
	private static final BigDecimal NIGHTLY_RATE_1 = new BigDecimal(115.00);
	private static final BigDecimal NIGHTLY_RATE_2 = new BigDecimal(150.00);
	private static final Integer WIFI_AMENITY = 1;
	private static final Integer BREAKFAST_AMENITY = 2;
	private static final Integer PARKING_AMENITY = 3;
	private String WIFI_DESCRIPTION = "Wi-Fi";
	private String BREAKFAST_DESCRIPTION = "Breakfast";
	private String PARKING_DESCRIPTION = "Parking";
	Hotel reservationHotel;
	Integer customerTotalPoints;
	String reservationRoomSize;
	List<String> resAmDescriptions;

	public ReservationBean() {
	}

	public String reviewBooking() {
		setupReservation();
		return "reservation-summary";
	}

	public String bookReservation() {
		if (reservationDao.addReservation(getReservation())) {
			Integer reservationId = resLookupDao.lookupLastInsertedReservation();
			reservation.setReservationId(reservationId);
			addAmenities(reservationId);
			return "reservation-confirmation?faces-redirect=true";
		} else {
			System.out.println("Reservation insert failed");
			return "";
		}
		
		
	}

	public void reset() {
		reservation = new Reservation();
		reservationCheckInDate = null;
		reservationCheckOutDate = null;
		resAmenitySelections = null;
		
	}
	
	public String cancel() {
		return "booking?faces-redirect=true";
	}

	private void setupReservation() {
		reservation.setCustomerId(customer.getCustomerId());
		reservationHotel = resLookupDao.lookupHotel(reservation.getHotelCode());
		customerTotalPoints = resLookupDao.lookupTotalLoyaltyPoints(customer.getCustomerId());
		reservationRoomSize = resLookupDao.lookupRoomSize(reservation.getRoomId());
		convertDates();
		calculateAmountDue();
		calculateLoyaltyPointsEarned();
		populateAmenitiesDescription();
	}

	private void populateAmenitiesDescription() {
		resAmDescriptions = new ArrayList<>();
		for (Integer amenity : resAmenitySelections) {
			if (WIFI_AMENITY.equals(amenity)) {
				resAmDescriptions.add(WIFI_DESCRIPTION);
			}

			if (BREAKFAST_AMENITY.equals(amenity)) {
				resAmDescriptions.add(BREAKFAST_DESCRIPTION);
			}

			if (PARKING_AMENITY.equals(amenity)) {
				resAmDescriptions.add(PARKING_DESCRIPTION);
			}
		}
	}

	private void addAmenities(Integer reservationId) {
		ReservationAmenity reservationAmenity = new ReservationAmenity();
		Integer numOfNights = Integer.valueOf(reservation.getNumberOfNights());
		List<ReservationAmenity> resAm = new ArrayList<>();

		for (Integer amenity : resAmenitySelections) {
			if (WIFI_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(WIFI_AMENITY);
				reservationAmenity.setQuantity(1);
			}

			if (BREAKFAST_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(BREAKFAST_AMENITY);
				reservationAmenity.setQuantity(numOfNights);
			}

			if (PARKING_AMENITY.equals(amenity)) {
				reservationAmenity.setAmenityId(PARKING_AMENITY);
				reservationAmenity.setQuantity(numOfNights);
			}

			reservationAmenity.setReservationId(reservationId);
			reservationDao.addReservationAmenity(reservationAmenity);
			resAm.add(reservationAmenity);
		}

		reservation.setAmenities(resAm);
	}

	private void convertDates() {
		reservation.setCheckInDate(convertDate(reservationCheckInDate));
		reservation.setCheckOutDate(convertDate(reservationCheckOutDate));
	}

	private void calculateAmountDue() {
		Long numOfGuests = Long.valueOf(reservation.getNumberOfGuests());
		BigDecimal numOfNights = BigDecimal.valueOf(Long.valueOf(reservation.getNumberOfNights()));
		BigDecimal roomCost;
		BigDecimal amenityCost = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);

		if (numOfGuests != null && numOfGuests < 3) {
			roomCost = numOfNights.multiply(NIGHTLY_RATE_1);
		} else {
			roomCost = numOfNights.multiply(NIGHTLY_RATE_2);
		}

		if (resAmenitySelections != null) {
			for (Integer amenity : resAmenitySelections) {
				if (WIFI_AMENITY.equals(amenity)) {
					amenityCost = amenityCost.add(new BigDecimal(12.99));
				}

				if (BREAKFAST_AMENITY.equals(amenity)) {
					amenityCost =amenityCost.add(new BigDecimal(8.99).multiply(numOfNights));
				}

				if (PARKING_AMENITY.equals(amenity)) {
					amenityCost =amenityCost.add(new BigDecimal(19.99).multiply(numOfNights));
				}
			}
		}

		reservation.setAmountDue(roomCost.add(amenityCost.setScale(2, RoundingMode.HALF_UP)));
	}

	private void calculateLoyaltyPointsEarned() {
		Integer numOfNights = Integer.valueOf(reservation.getNumberOfNights());
		Integer points = numOfNights * 150;
		reservation.setLoyaltyPointsEarned(points);
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

	public Integer[] getResAmenitySelections() {
		return resAmenitySelections;
	}

	public void setResAmenitySelections(Integer[] resAmenitySelections) {
		this.resAmenitySelections = resAmenitySelections;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Hotel getReservationHotel() {
		return reservationHotel;
	}

	public void setReservationHotel(Hotel reservationHotel) {
		this.reservationHotel = reservationHotel;
	}

	public Integer getCustomerTotalPoints() {
		return customerTotalPoints;
	}

	public void setCustomerTotalPoints(Integer customerTotalPoints) {
		this.customerTotalPoints = customerTotalPoints;
	}

	public String getReservationRoomSize() {
		return reservationRoomSize;
	}

	public void setReservationRoomSize(String reservationRoomSize) {
		this.reservationRoomSize = reservationRoomSize;
	}

	public List<String> getResAmDescriptions() {
		return resAmDescriptions;
	}

	public void setResAmDescriptions(List<String> resAmDescriptions) {
		this.resAmDescriptions = resAmDescriptions;
	}

}
