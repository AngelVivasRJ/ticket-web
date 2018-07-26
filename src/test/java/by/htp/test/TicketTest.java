package by.htp.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketTest extends BaseTest {

	@DataProvider(name = "OneWayDates")
	public Iterator<Calendar> oneWayData() throws Throwable {		
		List<Calendar> listCalendar = new ArrayList<Calendar>();
		Calendar calendar = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendar.set(2018, 8, 1, 0, 0);
		finCalendar.set(2018, 8, 3, 0, 0);
		do {
			listCalendar.add(calendar);
			calendar.add(Calendar.DATE, 1);
		} while (calendar.before(finCalendar));		
		return listCalendar.iterator();
	}

	@Test(dataProvider = "OneWayDates")
	public void BelaviaOneWayTest(Calendar date) {
		step.getTicketDate(date); 
		System.out.println("BelaviaOneWayTest()");
	}
}
