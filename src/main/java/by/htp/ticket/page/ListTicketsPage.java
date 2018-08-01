package by.htp.ticket.page;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		calTemp.set(Calendar.YEAR, Integer.parseInt(dateDepart.substring(6, 10)));
		calTemp.set(Calendar.MONTH, Integer.parseInt(dateDepart.substring(3, 5)) - 1);
		calTemp.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateDepart.substring(0, 2)));
		calTemp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateDepart.substring(11, 13)));
		calTemp.set(Calendar.MINUTE, Integer.parseInt(dateDepart.substring(14, 16)));
		calTemp.set(Calendar.SECOND, 0);
		return calTemp;
	}

	private float getCostTicket(String costWebElement) {
		return Float.parseFloat(costWebElement.replace(',', '.'));
	}

	public String getInnerHTML(WebElement webElement) {
		String jScript = "return arguments[0].innerHTML;";
		return (String) ((JavascriptExecutor) webDriverPage).executeScript(jScript, webElement);
	}

	public DaoAirTicket getAirTicketList(boolean flyWithReturn, DaoAirTicket daoAirTicketAcum) {
		long k = 0, l = 0;
		this.actions = new Actions(webDriverPage);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriverPage;
		String jScript = "";
		String classFly = "";
		String strTemp = "";
		Calendar dateTickDepart = Calendar.getInstance();
		Calendar dateTickReturn = Calendar.getInstance();
		AirTicket airTicketForward;
		AirTicket airTicketBack;
		AirTicketTwoWay airTicketTwoWay;
		DaoAirTicket daoAirTicket = new DaoAirTicketImplement();
		daoAirTicket = daoAirTicketAcum;
		List<WebElement> webCostTicket;
		float costTicket = 2;
		this.waitSmth = new WebDriverWait(webDriverPage, 10);
		waitSmth.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("span.amount")));
		List<WebElement> webDateDeparture = webDriverPage.findElements(By.cssSelector("div[class='departure'] strong"));
		// 4 (1,3)
		jScript = "return arguments[0].innerHTML;";
		actions.pause(1000).perform();
		if (!flyWithReturn) {
			dateTickDepart = getTimeAirTicket((String) jsExecutor.executeScript(jScript, webDateDeparture.get(1)));
		} else {
			dateTickDepart = getTimeAirTicket((String) jsExecutor.executeScript(jScript, webDateDeparture.get(3)));
		}
		webCostTicket = webDriverPage.findElements(By.cssSelector("span.amount"));
		jScript = "return arguments[0].innerHTML;";
		strTemp = (String) jsExecutor.executeScript(jScript, webCostTicket.get(5));
		jScript = "return document.querySelectorAll('div.fare-avail > div input').length";
		k = (long) jsExecutor.executeScript(jScript);		
		for (int i = 0; i < k; i++) {


				//jScript = "document.querySelectorAll('div.fare-avail > div input')[arguments[0]].click();";
			jScript = "var i = arguments[0]; var l = document.querySelectorAll('div.fare-avail > div input')[i].id.length;" +
					"var str = document.querySelectorAll('div.fare-avail > div input')[i].id.substr(l-2,l);" +
					"switch(str){ case 'EP': var out = 'Economy Promotion'; break; case 'ER': var out = 'Economy Restricted'; break; case 'SF': var out = 'Economic Semi-flexible'; break; case 'EF': var out = 'Economy Flexible'; break; case 'BC': var out = 'Business'; break; default: var out = \"Business\"; break;};" +
					"document.querySelectorAll('div.fare-avail > div input')[i].click();" + "return out;";	
			classFly = (String) jsExecutor.executeScript(jScript, i);
				

				if (!flyWithReturn) {
					actions.pause(500).perform();
					jScript = "return document.querySelectorAll('div.total span.amount')[0].innerHTML;";
					strTemp = (String) jsExecutor.executeScript(jScript);
					costTicket = getCostTicket(strTemp);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, AirTicket.classFlyReference[i],
							AirTicket.wayFlyReference[0]);
					daoAirTicket.addOneWay(airTicketForward);
					
				} else {
					actions.pause(500).perform();
					webCostTicket = webDriverPage.findElements(By.cssSelector("span.amount"));
					
					jScript = "return document.querySelectorAll('div.fare-avail > div input').length";
					l = (long) jsExecutor.executeScript(jScript);
					
					for (int j = 7; j <= l; j++) {
						/*if (!(objInterface.get(i + 1).getText().substring(0, 1).equals("-"))) {

							this.waitSmth = new WebDriverWait(webDriverPage, 10);
							waitSmth.until(ExpectedConditions.elementToBeClickable(objInterface.get(j)));
							objInterface.get(j).click();

							actions.pause(500);
							webCostTicket = webDriverPage.findElements(By.cssSelector("span.amount"));
							this.waitSmth = new WebDriverWait(webDriverPage, 10);
							waitSmth.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.amount")));

							// costTicket = getCostTicket(webCostTicket.get(5));

							airTicketForward = new AirTicket(dateTickReturn, costTicket, AirTicket.classFlyReference[i],
									AirTicket.wayFlyReference[0]);
							airTicketBack = new AirTicket(dateTickDepart, costTicket,
									AirTicket.classFlyReference[j - 7], AirTicket.wayFlyReference[1]);
							airTicketTwoWay = new AirTicketTwoWay(airTicketForward, airTicketBack, costTicket);
							daoAirTicket.addTwoWay(airTicketTwoWay);
							//objInterface = webDriverPage
							//		.findElements(By.cssSelector("[class='fare-avail ui-corner-all'] div")); // 12
																												// (1-5)(7-11)
						}*/
					}
				}
			
		}
		webDriverPage.navigate().back();
		return daoAirTicket;
	}

}
