package by.htp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TicketTest extends BaseTest {

	@DataProvider(name = "OneWayDates")
	public Iterator<Date> oneWayData() {
		Date dateToday = new Date();
		int stepDays = 30;
		List<Date> listDateDeparture = new ArrayList<Date>();
		Calendar calendarDeparture = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendarDeparture.set(Calendar.SECOND, 0);
		calendarDeparture.set(2018, 10, 1, 0, 0);
		finCalendar.setTime(dateToday);
		finCalendar.add(Calendar.DAY_OF_MONTH, 1);
		finCalendar.set(Calendar.SECOND, 0);
		finCalendar.set(Calendar.MINUTE, 0);
		finCalendar.set(Calendar.HOUR_OF_DAY, 0);
		while (finCalendar.before(calendarDeparture)) {
			listDateDeparture.add(calendarDeparture.getTime());
			calendarDeparture.add(Calendar.DATE, -stepDays);
		}
		for (Date date : listDateDeparture) {
			System.out.println("DataProvider OneWayDates:" + date.toString());
		}
		return listDateDeparture.iterator();
	}
	
	@DataProvider(name = "TwoWayDates")
	public Object[][] TwoWayData() {
		Date dateToday = new Date();
		int numMax = 10000;
		int stepDaysDeparture = 61;
		int stepDaysReturn = 60;
		Object[][] arrayDate = new Date[numMax][2];
		Calendar calendarDeparture = Calendar.getInstance();
		Calendar calendarReturn = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		finCalendar.setTime(dateToday);
		finCalendar.set(Calendar.HOUR_OF_DAY, 0);
		finCalendar.set(Calendar.MINUTE, 0);
		finCalendar.set(Calendar.SECOND, 0);
		calendarDeparture.set(2018, 9, 31, 0, 0);
		calendarDeparture.set(Calendar.SECOND, 0);;
		calendarReturn.set(2018, 10, 1, 0, 0);
		calendarReturn.set(Calendar.SECOND, 0);		
		int i = 0;
		while (finCalendar.before(calendarDeparture)) {			
			while(calendarDeparture.before(calendarReturn) & (i<numMax)) {
				arrayDate[i][0] = calendarDeparture.getTime();
				arrayDate[i][1] = calendarReturn.getTime();
				System.out.println("DataProvider TwoWayDates: " + arrayDate[i][0] + " " + arrayDate[i][1]);
				calendarReturn.add(Calendar.DATE, -stepDaysReturn);	
				i++;
			}		
			calendarDeparture.add(Calendar.DATE, -stepDaysDeparture);	
			calendarReturn.set(2018, 10, 1, 0, 0);
		}
		Object[][] arrayToReturn = Arrays.copyOfRange(arrayDate, 0, i);
		return arrayToReturn;
	}

	@Test(dataProvider = "OneWayDates")
	public void belaviaOneWayTest(Date dateDeparture) {
		System.out.println("BelaviaOneWayTest()");
		step.getTicketDate(false, dateDeparture, dateDeparture);
	}

	@Test(dataProvider = "TwoWayDates")
	public void belaviaTwoWayTest(Date dateDeparture, Date dateReturn) {
		System.out.println("BelaviaTwoWayTest()");
		step.getTicketDate(true,dateDeparture, dateReturn);
	}

	@Test
	public void listOneWay() {
		step.showListOneWay();
	}
	
	@Test
	public void listTwoWay() {
		step.showListTwoWay();
	}
}
