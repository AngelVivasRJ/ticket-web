package by.htp.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketTest extends BaseTest {

	@DataProvider(name = "OneWayDates")
	public Iterator<Date> oneWayData() {
		List<Date> listCalendar = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendar.set(2018, 10, 1, 0, 0);
		finCalendar.set(2018, 7, 1, 0, 0); 
		while (finCalendar.before(calendar)) {
			listCalendar.add(calendar.getTime());
			calendar.add(Calendar.DATE, -29);
		}
		for(Date date: listCalendar) {
			System.out.println("DataProvider:" + date.toString());
		}
		return listCalendar.iterator();
	}

	@Test(dataProvider = "OneWayDates")
	public void belaviaOneWayTest(Date date) {
		System.out.println("BelaviaOneWayTest()");
		step.getTicketDate(date);	
	}
	
	@Test
	public void listOneWay() {
		step.showListOneWay();
	}
	
	/*@Test(dataProvider = "OneWayDates")
	public void belaviaTwoWayTest(Date date) {
		System.out.println("BelaviaTwoWayTest()");
		
		List<Date> listCalendar = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendar.set(2018, 10, 1, 0, 0);
		finCalendar.setTime(date);; 
		while (finCalendar.before(calendar)) {
			listCalendar.add(calendar.getTime());
			calendar.add(Calendar.DATE, -25);
		}
		for(Date dateBack: listCalendar) {
			System.out.println("DataProvider: DateForward:" + date.toString() + " DateBack:" + dateBack.toString());
			//step.getTicketDateTwoWay(date, dateBack);
		}
		
		step.getTicketDateTwoWay(date, finCalendar.getTime());
	}*/
	
/*	@Test
	public void listTwoWay() {
		step.showListTwoWay();
	}*/
}
