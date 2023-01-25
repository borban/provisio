package provisio.db.model;

public class ReservationAmenities {
	private Long resAmenityId;
	private Long amenityId;
	private Long reservationId;
	private Long quantity;
	
	public Long getResAmenityId() {
		return resAmenityId;
	}
	public void setResAmenityId(Long resAmenityId) {
		this.resAmenityId = resAmenityId;
	}
	public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
