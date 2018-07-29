package by.htp.ticket.page;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.ticket.dao.DaoAirTicket;
import by.htp.ticket.dao.implement.DaoAirTicketImplement;
import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.AirTicketTwoWay;

public class ListTicketsPage extends AbstractPage {

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

	private Calendar getTimeAirTicket(String dateDepart) {
		Calendar calTemp = Calendar.getInstance();
		calTemp.set(Calendar.YEAR, Integer.parseInt(dateDepart.substring(7, 11)));
		calTemp.set(Calendar.MONTH, Integer.parseInt(dateDepart.substring(0, 2)));
		calTemp.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateDepart.substring(3, 5)));
		calTemp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateDepart.substring(12, 14)));
		calTemp.set(Calendar.MINUTE, Integer.parseInt(dateDepart.substring(15, 17)));
		calTemp.set(Calendar.SECOND, 0);
		return calTemp;
	}

	private float getCostTicket(WebElement costWebElement) {
		this.waitSmth = new WebDriverWait(webDriverPage, 10);
		waitSmth.until(ExpectedConditions.elementToBeClickable(costWebElement));
		return Float.parseFloat(costWebElement.getText().replace(',', '.'));
	}

	public DaoAirTicket getAirTicketList(boolean flyWithReturn, DaoAirTicket daoAirTicketAcum) {
		Calendar dateTickDepart = Calendar.getInstance();
		Calendar dateTickReturn = Calendar.getInstance();
		AirTicket airTicketForward;
		AirTicket airTicketBack;
		AirTicketTwoWay airTicketTwoWay;
		DaoAirTicket daoAirTicket = new DaoAirTicketImplement();
		daoAirTicket = daoAirTicketAcum;
		float costTicket;
		this.waitSmth = new WebDriverWait(webDriverPage, 10);
		waitSmth.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span.amount")));
		List<WebElement> webCostTicket = webDriverPage.findElements(By.cssSelector("span.amount"));
		// 6 (5)
		WebElement costWebElement = webCostTicket.get(5);
		List<WebElement> webDateDeparture = webDriverPage.findElements(By.cssSelector("div[class='departure'] strong"));
		// 4 (1,3)
		if (!flyWithReturn) {
			dateTickDepart = getTimeAirTicket(webDateDeparture.get(1).getText());
		} else {
			dateTickReturn = getTimeAirTicket(webDateDeparture.get(3).getText());
		}
		List<WebElement> webSelTicket = webDriverPage
				.findElements(By.cssSelector("[class='fare-avail ui-corner-all'] div")); // 12 (1-5)(7-11)
		for (int i = 0; i < 5; i++) {
			if (webSelTicket.get(i + 1).getText() != "-") {
				this.waitSmth = new WebDriverWait(webDriverPage, 10);
				waitSmth.until(ExpectedConditions.elementToBeClickable(webSelTicket.get(i + 1)));
				webSelTicket.get(i + 1).click();
				if (!flyWithReturn) {
					costTicket = getCostTicket(costWebElement);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, AirTicket.classFlyReference[i],
							AirTicket.wayFlyReference[0]);
					daoAirTicket.addOneWay(airTicketForward);
				} else {
					for (int j = 7; j <= 11; j++) {
						if (webSelTicket.get(j).getText() != "-") {
							this.waitSmth = new WebDriverWait(webDriverPage, 10);
							waitSmth.until(ExpectedConditions.elementToBeClickable(webSelTicket.get(j)));
							webSelTicket.get(j).click();
							costTicket = getCostTicket(costWebElement);
							airTicketForward = new AirTicket(dateTickReturn, costTicket, AirTicket.classFlyReference[i],
									AirTicket.wayFlyReference[0]);
							airTicketBack = new AirTicket(dateTickDepart, costTicket,
									AirTicket.classFlyReference[j - 7], AirTicket.wayFlyReference[1]);
							airTicketTwoWay = new AirTicketTwoWay(airTicketForward, airTicketBack, costTicket);
							daoAirTicket.addTwoWay(airTicketTwoWay);
						}
					}
				}
			}
		}
		webDriverPage.navigate().refresh();
		return daoAirTicket;
	}
}
