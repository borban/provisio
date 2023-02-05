package provisio.db.model;

/* Methods used to get and set resAmenityId, amenityId, reservationId, and quantity 
 * based on amenities selected in the reservation form */

public class ReservationAmenity {
	private Integer resAmenityId;
	private Integer amenityId;
	private Integer reservationId;
	private Integer quantity;

	public Integer getResAmenityId() {
		return resAmenityId;
	}

	public void setResAmenityId(Integer resAmenityId) {
		this.resAmenityId = resAmenityId;
	}

	public Integer getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Integer amenityId) {
		this.amenityId = amenityId;
	}

	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
