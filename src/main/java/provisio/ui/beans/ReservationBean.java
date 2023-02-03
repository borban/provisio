package provisio.ui.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import provisio.db.dao.ReservationDao;
import provisio.db.model.*;

@ManagedBean(name = "reservationBean")
@SessionScoped
public class ReservationBean {
	public Reservation reservation = new Reservation();
	private ReservationDao reservationDao = new ReservationDao();
	public ReservationAmenities reservationAmenities = new ReservationAmenities();
	public String[] resAmArray;
	
	public String reservation() {
		for(int i = 0; i < resAmArray.length; i++) {
			reservationAmenities.setAmenityId(resAmArray[i]);
			}
		if(reservationDao.addReservation(getReservation())) {
			System.out.println("Reservation Added");
		}else {
			System.out.println("Reservation insert failed");
			return "Reservation insert failed";
		}
		if(reservationDao.addReservationAmenities(getReservationAmenities())) {
			System.out.println("Reservation_Amenities Added");
		}else {
			System.out.println("Reservation_Amenities insert failed");
			return "Reservation_Amenities insert failed";
		}
		System.out.println("Successful Reservation");
		return "Successful Reservation";
	}
	
	public String[] getResAmArray() {
		return resAmArray;
	}
	public void setResAmArray(String[] resAmArray) {
		this.resAmArray= resAmArray;
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
	
	//Generate Locations Map
		private static Map<String,Object> locationValue;
		static{
			locationValue = new LinkedHashMap<String,Object>();
			locationValue.put("Littleton, CO", 1); //label, value
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
		
		public Map<String,Object> getLocationValue() {
			return locationValue;
		}
		//Generate Room Map
				private static Map<String,Object> roomValue;
				static{
					roomValue = new LinkedHashMap<String,Object>();
					roomValue.put("Double Full Beds", 1); //label, value
					roomValue.put("Queen Suite", 2);
					roomValue.put("Double Queen Beds", 3);
					roomValue.put("King Suite", 4);
					
				}
				
				public Map<String,Object> getRoomValue() {
					return roomValue;
				}

}
