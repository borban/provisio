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
			return "successful_Registration";
			}
			else {
			return "unsuccessful_Registration";
		}
		}
		return "unsuccessful_Registration";
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
			locationValue.put("1", "Littleton, CO"); //label, value
			locationValue.put("2", "Maplewood, NJ");
			locationValue.put("3", "Wappingers Falls, NY");
			locationValue.put("4", "Chatsworth, GA");
			locationValue.put("5", "Fuquay Varina, NC");
			locationValue.put("6", "Inman, SC");
			locationValue.put("7", "Sebastian, FL");
			locationValue.put("8", "New Philadelphia, OH");
			locationValue.put("9", "Herndon, VA");
			locationValue.put("10", "Atwater, CA");
			locationValue.put("11", "Waldorf, MD");
			locationValue.put("12", "Prior Lake, MN");
		}
		
		public Map<String,Object> getLocationValue() {
			return locationValue;
		}

}
