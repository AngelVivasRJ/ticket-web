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
		List<Date> listDateDeparture = new ArrayList<Date>();
		Calendar calendarDeparture = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendarDeparture.set(2018, 10, 1, 0, 0);
		finCalendar.set(2018, 7, 1, 0, 0);
		while (finCalendar.before(calendarDeparture)) {
			listDateDeparture.add(calendarDeparture.getTime());
			calendarDeparture.add(Calendar.DATE, -45);
		}
		for (Date date : listDateDeparture) {
			System.out.println("DataProvider:" + date.toString());
		}
		return listDateDeparture.iterator();
	}
	
	@DataProvider(name = "TwoWayDates")
	public Object[][] TwoWayData() {
		int numMax = 1000;
		Object[][] arrayDate = new Date[numMax][2];
		Calendar calendarDeparture = Calendar.getInstance();
		Calendar calendarReturn = Calendar.getInstance();
		Calendar finCalendar = Calendar.getInstance();
		calendarDeparture.set(2018, 10, 1, 0, 0);
		calendarReturn.set(2018, 10, 1, 0, 0);
		finCalendar.set(2018, 7, 1, 0, 0);
		int i = 0;
		while (finCalendar.before(calendarDeparture)) {			
			while(calendarDeparture.before(calendarReturn) & (i<numMax)) {
				arrayDate[i][0] = calendarDeparture.getTime();
				arrayDate[i][1] = calendarReturn.getTime();
				System.out.println("DataProvider: " + arrayDate[i][0] + " " + arrayDate[i][1]);
				calendarReturn.add(Calendar.DATE, -40);	
				i++;
			}		
			calendarDeparture.add(Calendar.DATE, -45);	
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

	//@Test(dataProvider = "TwoWayDates")
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
