package provisio.ui.beans;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import provisio.db.dao.ReservationDao;
import provisio.db.model.Reservation;

@ManagedBean(name = "reservationBean")
@RequestScoped
public class ReservationBean {
	public Reservation res = new Reservation();
	private ReservationDao resDao = new ReservationDao();
	

	public String reservation() {
		Reservation dbCustomerReservation = resDao.getCustomerReservation(res.getReservationId());
		if(!doesExist(dbCustomerReservation)) {
			if (resDao.addReservation(res)) {
			return "successful_Reservation";
			}
			else {
			return "unsuccessful_Reservation";
		}
		}
		return "unsuccessful_Reservation";
	}
	
	private boolean doesExist(Reservation dbCustomerReservation) {
		if (dbCustomerReservation.getReservationId() != 0) {
			return res.getReservationId().equals(dbCustomerReservation.getReservationId());
		}
		// TODO Auto-generated method stub
		return false;
		}

	public Reservation getReservation() {
		return res;
	}

	public void setReservation(Reservation res) {
		this.res = res;
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
