package provisio.db.model;

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
