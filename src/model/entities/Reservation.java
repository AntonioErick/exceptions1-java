package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	public Reservation() {
	}
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); //pega a diferença entre as duas datas em mile segundos
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); //converte o valor diff que esta em mile seconds para dias
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		
		//before verifica se uma data é anterior a outra
		if(checkIn.before(now) || checkOut.before(now)) {
			return "Error un reservation: Reservation dates for updates must be future dates.";
		}
		if(!checkOut.after(checkIn)){
			return "Error in reservation: Check-out date must be after check-in date.";
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
		return null;
	}
	
	@Override
	public String toString() {
		return "Room "+roomNumber+
				", check-in: "+sdf.format(checkIn)+
				", check-out: "+sdf.format(checkOut)+
				", "+duration()+" nights";
	}
}
