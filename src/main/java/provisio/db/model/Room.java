package provisio.db.model;

/* Methods used to get and set roomId, roomSize, and hotelCode based on selection from the reservation form */

public class Room {
	private Long roomId;
	private String roomSize;
	private Long hotelCode;
	
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public Long getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(Long hotelCode) {
		this.hotelCode = hotelCode;
	}
	
}
