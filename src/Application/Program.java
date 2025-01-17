package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		
		System.out.print("Check-in dade (dd/MM/yyyy): ");
		Date checkin = sdf.parse(sc.next());
		
		System.out.print("Check-out dade (dd/MM/yyyy): ");
		Date checkout = sdf.parse(sc.next());
		
		//after verifica se uma data é depois da outra
		if(!checkout.after(checkin)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date.");
		}
		else {
			Reservation reservation = new Reservation(number, checkin, checkout);
			System.out.println("Reservation: "+reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			
			System.out.print("Check-in dade (dd/MM/yyyy): ");
			checkin = sdf.parse(sc.next());
			
			System.out.print("Check-out dade (dd/MM/yyyy): ");
			checkout = sdf.parse(sc.next());
			
			Date now = new Date();
			
			//before verifica se uma data é anterior a outra
			if(checkin.before(now) || checkout.before(now)) {
				System.out.println("Error un reservation: Reservation dates for updates must be future dates.");
			}
			else if(!checkout.after(checkin)){
				System.out.println("Error in reservation: Check-out date must be after check-in date.");
			}
			else {
				reservation.updateDates(checkin, checkout);
				System.out.println("Reservation: "+reservation);
			}
		}
		
		sc.close();
	}

}
