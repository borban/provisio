package provisio.db.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/* Methods used to get and set reservationID, customerId, hotelCode, roomId, checkInDate, checkOutDate, numberOfNights,
 * numberOfGuests, amountDue, loyaltyPointsEarned, and amenities based on what was entered in the reservation form. */

public class Reservation {
	private Integer reservationId;
	private Integer customerId;
	private Integer hotelCode;
	private Integer roomId;
	private Date checkInDate;
	private Date checkOutDate;
	private Integer numberOfNights;
	private String numberOfGuests;
	private BigDecimal amountDue;
	private Integer loyaltyPointsEarned;
	private List<ReservationAmenity> amenities;

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(Integer hotelCode) {
		this.hotelCode = hotelCode;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Integer getNumberOfNights() {
		return numberOfNights;
	}

	public void setNumberOfNights(Integer diffDays) {
		this.numberOfNights = diffDays;
	}

	public String getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(String numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public BigDecimal getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(BigDecimal amountDue) {
		this.amountDue = amountDue;
	}

	public Integer getLoyaltyPointsEarned() {
		return loyaltyPointsEarned;
	}

	public void setLoyaltyPointsEarned(Integer loyaltyPointsEarned) {
		this.loyaltyPointsEarned = loyaltyPointsEarned;
	}

	public List<ReservationAmenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<ReservationAmenity> amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", customerId=" + customerId + ", hotelCode=" + hotelCode
				+ ", roomId=" + roomId + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", numberOfNights=" + numberOfNights + ", numberOfGuests=" + numberOfGuests + ", amountDue="
				+ amountDue + ", loyaltyPointsEarned=" + loyaltyPointsEarned + ", amenities=" + amenities + "]";
	}

}
