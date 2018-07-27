package by.htp.ticket.page;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectionTicketsPage extends AbstractPage {

	private static final String urlHomeBelavia = "https://belavia.by";
	
	private WebElement element;
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
		this.element = webDriverPage.findElement(By.cssSelector(locatorElement));
		element.sendKeys(city);
		actions.pause(500).perform();
		actions.moveToElement(element, 170, 50).click().perform();
		actions.pause(500).perform();
	}

	public void initSelTask1() {
		selectCity("MSQ", "input[id='OriginLocation_Combobox']");
		selectCity("RIX", "input[id='DestinationLocation_Combobox']");
		this.element = webDriverPage.findElement(By.cssSelector("label[for='JourneySpan_Ow']"));
		element.click();
	}

	public int numberClickMonth(String month) {
		switch (month) {
		case "Aug":
			return 1;
		case "Sep":
			return 2;
		case "Oct":
			return 3;
		case "Nov":
			return 4;
		case "Dec":
			return 5;
		default:
			return 0;
		}
	}

	public void setDateDeparture(Date dayFly) {
		String month = dayFly.toString().substring(4,7);
		int day = Integer.parseInt(dayFly.toString().substring(8,10));
		this.actions = new Actions(webDriverPage);
		actions.pause(1000).perform();
		initSelTask1();
		actions.pause(2000).perform();
		this.waitSmth = new WebDriverWait(webDriverPage, 10);
		this.element = waitSmth.until(ExpectedConditions.elementToBeClickable(By.cssSelector("i[class='icon-calendar']")));
		element.click();
		
		int i = numberClickMonth(month);
		while (i > 0) {
			actions.pause(2000).perform();
			this.element = waitSmth
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector("i[class='icon-right-open']")));
			element.click();
			i--;
		}		
		this.element = webDriverPage.findElement(By.linkText(Integer.toString(day)));
		element.click();
		this.element = waitSmth
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[onclick*='kupitnajtibilet']")));
		element.click();
	}
}
