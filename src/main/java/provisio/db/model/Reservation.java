package provisio.db.model;

import java.math.BigDecimal;

public class Reservation {
	private Long reservationId;
	private Long customerId;
	private Long hotelCode;
	private Long roomId;
	private String checkInDate;
	private String checkOutDate;
	private String numberOfNights;
	private String numberOfGuests;
	private BigDecimal amountDue;
	private String loyaltyPointsEarned;
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(Long hotelCode) {
		this.hotelCode = hotelCode;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getNumberOfNights() {
		return numberOfNights;
	}
	public void setNumberOfNights(String numberOfNights) {
		this.numberOfNights = numberOfNights;
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
	public String getLoyaltyPointsEarned() {
		return loyaltyPointsEarned;
	}
	public void setLoyaltyPointsEarned(String loyaltyPointsEarned) {
		this.loyaltyPointsEarned = loyaltyPointsEarned;
	}
	
	
}
