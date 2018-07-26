package by.htp.ticket.page;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.ListAirTicket;

public class ListTicketsPage extends AbstractPage {

	private Calendar calendarFly = Calendar.getInstance();
	private AirTicket airTicketTemp;
	private List<AirTicket> listStandarAirTicket;
	private List<AirTicket> listStandarAirTicketBack;
	private ListAirTicket listAirTicket;
	private ListAirTicket listAirTicketBack;
	private WebElement webElement;
	private Actions actions = new Actions(webDriverPage);

	public ListTicketsPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public void openPage() {
		// It is not necessary open any website.
	}

	public AirTicket getAirTicket(int i) {
		String textElement = "";
		this.airTicketTemp = new AirTicket(calendarFly, -8.5f, AirTicket.classFlyReference[0],
				AirTicket.wayFlyReference[0]);
		this.webElement = webDriverPage.findElement(By.cssSelector("a[data-rel='fare-rules-EP']"));

		actions.moveToElement(webElement, 60 + i * 109, 60).perform();
		actions.click().perform();
		actions.pause(2000).perform();

		// get timeFly and costFly
		List<WebElement> listWebElement = webDriverPage.findElements(By.cssSelector("span.amount"));
		int j = 0;
		for (WebElement iWebElement : listWebElement) {
			textElement = iWebElement.getText();
			System.out.println(Integer.toString(j) + ": Cost: " + textElement);
			j++;
		}
		j = 0;
		listWebElement = webDriverPage.findElements(By.cssSelector("div strong"));
		for (WebElement iWebElement : listWebElement) {
			textElement = iWebElement.getText();
			System.out.println(Integer.toString(j) + ": Data Departure: " + textElement);
			j++;
		}

			
		/*
		 * if () {
		 * 
		 * }
		 */

		// this.airTicket = new AirTicket(calendarFly, 20.5,
		// AirTicket.classFlyReference[0], AirTicket.wayFlyReference[0]);
		return airTicketTemp;
	}

	public ListAirTicket getListAirTicketsOneWay(ListAirTicket listAirTicketAcumulated, Calendar calendar) {
		this.calendarFly = calendar;
		this.listAirTicket = listAirTicketAcumulated;
		this.listStandarAirTicket = listAirTicket.getListAirTicket();
		actions.pause(3000).perform();

		for (int i = 0; i < 5; i++) {
			getAirTicket(i);

			if (!(airTicketTemp.getCostFly() < 0)) {
				this.listStandarAirTicket.add(airTicketTemp); // Create newAirTicket
			}
			webDriverPage.navigate().refresh();
		}
		webDriverPage.navigate().back();
		return listAirTicket;
	}
}
