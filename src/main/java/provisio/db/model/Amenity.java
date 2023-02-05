package provisio.db.model;

import java.math.BigDecimal;

/* Methods used to get and set AmenityId, Description, and Price from the reservation form */

public class Amenity {
	private Long amenityId;
	private String description;
	private BigDecimal price;
	
	public Long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
