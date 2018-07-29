package by.htp.ticket.page;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectionTicketsPage extends AbstractPage {

	private static final String urlHomeBelavia = "https://belavia.by";

	private WebElement webElement;
	private Actions actions;
	private WebDriverWait waitSmth;

	public SelectionTicketsPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public void openPage() {
		webDriverPage.get(urlHomeBelavia);
	}

	public void selectCity(String city, String locatorElement) {
		this.actions = new Actions(webDriverPage);		
		this.waitSmth = new WebDriverWait(webDriverPage, 10);
		//waitSmth.until(ExpectedConditions.elementToBeClickable(webElement));
		
		this.waitSmth.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorElement)));
		this.webElement = webDriverPage.findElement(By.cssSelector(locatorElement));
		webElement.sendKeys(city);		
		actions.pause(500).perform();
		actions.moveToElement(webElement, 170, 50).click().perform();
	}

	public void initSelOneWay() {
		selectCity("MSQ", "input[id='OriginLocation_Combobox']");
		selectCity("RIX", "input[id='DestinationLocation_Combobox']");
	}

	public void initSelTwoWay() {
		selectCity("MSQ", "input[id='OriginLocation_Combobox']");
		selectCity("RIX", "input[id='DestinationLocation_Combobox']");
	}

	public int monthToInt(String month) {
		switch (month) {
		case "Jan":
			return 0;
		case "Feb":
			return 1;
		case "Mar":
			return 2;
		case "Apr":
			return 3;
		case "May":
			return 4;
		case "Jun":
			return 5;
		case "Jul":
			return 6;
		case "Aug":
			return 7;
		case "Sep":
			return 8;
		case "Oct":
			return 9;
		case "Nov":
			return 10;
		case "Dec":
			return 11;
		default:
			return 0;
		}
	}

	public int numberClickMonth(boolean flyWithReturn, String monthToSet[]) {
		Calendar cal = Calendar.getInstance();
		int monthToday = monthToInt(cal.getTime().toString().substring(4, 7));
		if (flyWithReturn) {
			return monthToInt(monthToSet[1]) - monthToInt(monthToSet[0]);
		} else {
			return monthToInt(monthToSet[0]) - monthToday;
		}
	}

	public void setDateDepartureReturn(Boolean flyWithReturn, Date dateDeparture, Date dateReturn) {
		int k1 = 0, i = 0;
		String month[] = { dateDeparture.toString().substring(4, 7), dateReturn.toString().substring(4, 7) };
		int day[] = { Integer.parseInt(dateDeparture.toString().substring(8, 10)),
				Integer.parseInt(dateReturn.toString().substring(8, 10)) };
		this.actions = new Actions(webDriverPage);
		//actions.pause(1000).perform();
		if (flyWithReturn) {
			initSelTwoWay();
			k1 = 1;
		} else {
			initSelOneWay();
		}
		for (int n = 0; n <= k1; n++) {
			actions.pause(500).perform();
			if (n == 0) {
				i = numberClickMonth(false, month);
			} else {
				i = numberClickMonth(flyWithReturn, month);
			}
			System.out.println(n + ": " + " monthly clicks: " + Integer.toString(i));
			while (i > 0) {						
				this.webElement = webDriverPage.findElement(By.cssSelector("i[class='icon-right-open']"));
				this.waitSmth = new WebDriverWait(webDriverPage, 10);
				waitSmth.until(ExpectedConditions.elementToBeClickable(webElement));
				webElement.click();
				i--;
			}
			this.webElement = webDriverPage.findElement(By.linkText(Integer.toString(day[n])));
			webElement.click();
			if (!flyWithReturn) {
				this.webElement = webDriverPage.findElement(By.cssSelector("label[for='JourneySpan_Ow']"));
				webElement.click();
			}
		}
		System.out.println("Before search!!!!!!");
		actions.pause(500).perform(); // With high velocity this button don't work with this method.
		this.waitSmth = new WebDriverWait(webDriverPage,10);
		//waitSmth.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick*='kupitnajtibilet']")));
		webDriverPage.findElement(By.cssSelector("button[onclick*='kupitnajtibilet']")).submit();
		System.out.println("Aftersearch!!!!!!!!");
	}
}
