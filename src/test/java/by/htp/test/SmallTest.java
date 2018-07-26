package by.htp.test;

import java.util.Calendar;

import org.testng.annotations.Test;

import by.htp.ticket.entity.AirTicket;

public class SmallTest {

	/*
	 * @Test public void smallTest() { Calendar dayFly = Calendar.getInstance();
	 * dayFly.set(2018, 8, 3, 4, 25, 30);
	 * 
	 * System.out.println("all Calendar:" + dayFly.getTime().toString()+":");
	 * System.out.println("day:" + dayFly.getTime().toString().substring(8, 10) +
	 * ":"); System.out.println("month:" + dayFly.getTime().toString().substring(4,
	 * 7) + ":");
	 * 
	 * 
	 * String month = dayFly.getTime().toString().substring(4,7); int day =
	 * Integer.parseInt(dayFly.getTime().toString().substring(8,10)); }
	 */

	@Test
	public void smallTest1() {
		Calendar calendarFly = Calendar.getInstance();
		calendarFly.set(2018, 8, 1, 15, 25);
		System.out.println(":" + calendarFly.getTime().toString() + ":");
		AirTicket airTicketTemp = new AirTicket(calendarFly, 25.8f, AirTicket.classFlyReference[0], AirTicket.wayFlyReference[0]);
		System.out.println(airTicketTemp.toString());
	}

}
