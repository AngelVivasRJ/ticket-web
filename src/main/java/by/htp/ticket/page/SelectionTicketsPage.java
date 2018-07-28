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
		actions.pause(500).perform();
		this.actions = new Actions(webDriverPage);
		this.webElement = webDriverPage.findElement(By.cssSelector(locatorElement));
		webElement.sendKeys(city);
		actions.pause(500).perform();
		actions.moveToElement(webElement, 170, 50).click().perform();
		actions.pause(500).perform();
	}

	public void initSelTask1() {
		selectCity("MSQ", "input[id='OriginLocation_Combobox']");
		selectCity("RIX", "input[id='DestinationLocation_Combobox']");
		this.webElement = webDriverPage.findElement(By.cssSelector("label[for='JourneySpan_Ow']"));
		webElement.click();
	}

	public void initSelTask2() {
		selectCity("MSQ", "input[id='OriginLocation_Combobox']");
		selectCity("RIX", "input[id='DestinationLocation_Combobox']");
		this.webElement = webDriverPage.findElement(By.cssSelector("input[value='Rt']"));
		webElement.click();
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

	public void setDateDepartureBack(Boolean flyWithBack, Date dateForward, Date dateBack) {
		int k1 = 0;
		String month[] = { dateForward.toString().substring(4, 7), dateBack.toString().substring(4, 7) };
		int day[] = { Integer.parseInt(dateForward.toString().substring(8, 10)),
				Integer.parseInt(dateBack.toString().substring(8, 10)) };
		this.actions = new Actions(webDriverPage);
		actions.pause(1000).perform();
		if (flyWithBack) {
			initSelTask2();
			k1 = 1;
		} else {
			initSelTask1();
		}
		WebElement iconCalendar = webDriverPage.findElement(By.cssSelector("i[class='icon-calendar']"));
		for (int n = 0; n <= k1; n++) {
			actions.pause(2000).perform();
			if (n == 0) {
				this.waitSmth = new WebDriverWait(webDriverPage, 10);
				this.webElement = waitSmth.until(ExpectedConditions.elementToBeClickable(iconCalendar));
				webElement.click();
			} else {
					//It's necesary calculate the month to click.
			}
			int i = numberClickMonth(month[n]);
			while (i > 0) {
				actions.pause(2000).perform();
				WebElement iconMonth = webDriverPage.findElement(By.cssSelector("i[class='icon-right-open']"));
				actions.moveToElement(iconMonth).click().perform();
				i--;
			}
			this.webElement = webDriverPage.findElement(By.linkText(Integer.toString(day[n])));
			webElement.click();
		}
		System.out.println("Before search!!!!!!");
		actions.pause(1000).perform();
		webDriverPage.findElement(By.cssSelector("button[onclick*='kupitnajtibilet']")).submit();
		System.out.println("Aftersearch!!!!!!!!");
	}
}
