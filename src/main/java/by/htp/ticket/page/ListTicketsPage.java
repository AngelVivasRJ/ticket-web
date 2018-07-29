package by.htp.ticket.page;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.AirTicketTwoWay;
import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.entity.ListAirTicketTwoWay;

public class ListTicketsPage extends AbstractPage {

	private Calendar calendarFly = Calendar.getInstance();
	private List<AirTicket> listStandarAirTicket;
	private List<AirTicketTwoWay> listStandarAirTicketTwoWay;
	private WebElement webElement;
	private Actions actions;
	private WebDriverWait waitSmth;

	public ListTicketsPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public void openPage() {
		// It is not necessary open any website.
	}

	public Calendar getTimeAirTicket(int i, String locatorDate, Date dateDeparture) {
		String textElement;
		Calendar calTemp = Calendar.getInstance();
		calTemp.setTime(dateDeparture);
		; // default Calendar
		//this.actions = new Actions(webDriverPage);
		//actions.pause(1000).perform();
		this.waitSmth = new WebDriverWait(webDriverPage,10);
		waitSmth.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locatorDate)));
		List<WebElement> listWebElement = webDriverPage.findElements(By.cssSelector(locatorDate));
		/*
		 * int j = 0; for (WebElement iWebElement : listWebElement) { textElement =
		 * iWebElement.getText(); System.out.println(Integer.toString(j) + ": Cost: " +
		 * textElement); j++; }
		 */
		textElement = listWebElement.get(0).getText();
		calTemp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(textElement.substring(0, 2)));
		calTemp.set(Calendar.MINUTE, Integer.parseInt(textElement.substring(3, 5)));
		calTemp.set(Calendar.SECOND, 0);
		// System.out.println("Calendar:" + calTemp.getTime().toString());
		return calTemp;
	}

	/*public float getAirTicketCost(int i, String locatorTicket, String locatorAmount) {
		String textElement = "";
		float fTemp = -7.7f; // default value, if there is not ticket for any class
		this.actions = new Actions(webDriverPage);
		this.webElement = webDriverPage.findElement(By.cssSelector(locatorTicket));
		// System.out.println(Integer.toString(i) + ": " +
		// this.webElement.getLocation().toString());
		// click on ticket
		actions.pause(1000);
		actions.moveToElement(this.webElement, 525 + i * 120, 30).click().perform();
		actions.pause(1000).perform();
		// get costFly
		List<WebElement> listWebElement = webDriverPage.findElements(By.cssSelector(locatorAmount));
		textElement = listWebElement.get(listWebElement.size() - 1).getText();
		if (!(textElement.substring(0, 1).equals("-"))) {
			textElement = textElement.replace(',', '.');
			fTemp = Float.parseFloat(textElement);
		}
		
		 * int j = 0; for (WebElement iWebElement : listWebElement) { textElement =
		 * iWebElement.getText(); System.out.println(Integer.toString(j) + ": Cost: " +
		 * textElement); j++; }
		 
		webDriverPage.navigate().refresh();
		return fTemp;
	}*/
	
	public float getAirTicketCost(int i, String locatorTicket, String locatorAmount) {
		
		String textElement = "";
		float fTemp = -7.7f; // default value, if there is not ticket for any class
		this.actions = new Actions(webDriverPage);
		
		this.webElement = webDriverPage.findElement(By.cssSelector(locatorTicket));
		
		
		// System.out.println(Integer.toString(i) + ": " +
		// this.webElement.getLocation().toString());
		
		
		// click on ticket
		actions.pause(1000);
		actions.moveToElement(this.webElement, 525 + i * 120, 30).click().perform();
		actions.pause(1000).perform();
		
		
		// get costFly
		List<WebElement> listWebElement = webDriverPage.findElements(By.cssSelector(locatorAmount));
		textElement = listWebElement.get(listWebElement.size() - 1).getText();
		if (!(textElement.substring(0, 1).equals("-"))) {
			textElement = textElement.replace(',', '.');
			fTemp = Float.parseFloat(textElement);
		}
		/*
		 * int j = 0; for (WebElement iWebElement : listWebElement) { textElement =
		 * iWebElement.getText(); System.out.println(Integer.toString(j) + ": Cost: " +
		 * textElement); j++; }
		 */
		webDriverPage.navigate().refresh();
		return fTemp;
	}

	public List<AirTicket> getListAirTicketsOneWay(boolean flyWithReturn, List<AirTicket> listAirTicketAcum,
			Date dateDeparture) {
		float fTem;
		this.listStandarAirTicket = listAirTicketAcum;
		this.actions = new Actions(webDriverPage);
		//actions.pause(3000).perform();
		//this.calendarFly = getTimeAirTicket(1, "div strong", dateDeparture);
		for (int i = 0; i < 5; i++) {
			fTem = getAirTicketCost(i, "div[class='t-row']", "span.amount");
			if (fTem > 0) {
				AirTicket airTicketTemp = new AirTicket(calendarFly, fTem, AirTicket.classFlyReference[i],
						AirTicket.wayFlyReference[0]);
				System.out.println(airTicketTemp.toString()); // Show ticket
				this.listStandarAirTicket.add(airTicketTemp); // Create newAirTicket
			}
		}		
		webDriverPage.navigate().back();
		return listStandarAirTicket;
	}

	public List<AirTicketTwoWay> getListAirTicketsTwoWay(boolean flyWithReturn,
			List<AirTicketTwoWay> listAirTicketTwoWayAcum, Date dateDeparture, Date dateReturn) {

		float fTem;
		this.listStandarAirTicketTwoWay = listAirTicketTwoWayAcum;
		//this.actions = new Actions(webDriverPage);
		//actions.pause(3000).perform();

		/*
		 * this.calendarFly = getTimeAirTicket(1, "div strong", dateDeparture); for (int
		 * i = 0; i < 5; i++) { fTem = getAirTicketCost(i, "div[class='t-row']",
		 * "span.amount"); if (fTem > 0) { AirTicket airTicketTemp = new
		 * AirTicket(calendarFly, fTem, AirTicket.classFlyReference[i],
		 * AirTicket.wayFlyReference[0]); System.out.println(airTicketTemp.toString());
		 * // Show ticket this.listStandarAirTicket.add(airTicketTemp); // Create
		 * newAirTicket } }
		 */

		webDriverPage.navigate().back();
		return listStandarAirTicketTwoWay;
	}
}
