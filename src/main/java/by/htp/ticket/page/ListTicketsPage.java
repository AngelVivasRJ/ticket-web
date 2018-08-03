package by.htp.ticket.page;

import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		String strCost = "";
		int indexBegin = 0;
		String regExp = "[^\\w,]";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(costWebElement);
		while (matcher.find()) {
			strCost += costWebElement.substring(indexBegin, matcher.start());
			indexBegin = matcher.end();
		}
		if (indexBegin == 0) {
			strCost = costWebElement;
		} else {
			strCost += costWebElement.substring(indexBegin, costWebElement.length());
		}
		return Float.parseFloat(strCost.replace(',', '.'));
	}

	public String getInnerHTML(WebElement webElement) {
		String jScript = "return arguments[0].innerHTML;";
		return (String) ((JavascriptExecutor) webDriverPage).executeScript(jScript, webElement);
	}

	public DaoAirTicket getAirTicketList(boolean flyWithReturn, DaoAirTicket daoAirTicketAcum) {
		this.actions = new Actions(webDriverPage);
		long k = 0, m = 0;
		DaoAirTicket daoAirTicket = new DaoAirTicketImplement();
		daoAirTicket = daoAirTicketAcum;
		this.waitSmth = new WebDriverWait(webDriverPage, 60);
		waitSmth.until(ExpectedConditions
				.or(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.container.content div"))));
		List<WebElement> webDateDeparture = webDriverPage.findElements(By.cssSelector("div.container.content div"));
		String strTemp = webDateDeparture.get(0).getAttribute("class"); // if "clear", then may read tickets
		if (strTemp.equals("panel") | (strTemp.equals("clear"))) { 
			if (strTemp.equals("panel")) {
				actions.pause(40000).perform(); // captcha
			}
			JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriverPage;
			String jScript = "";
			String classFlyDepart = "";
			String classFlyReturn = "";
			String currency = "";
			Calendar dateTickDepart = Calendar.getInstance();
			Calendar dateTickReturn = Calendar.getInstance();
			AirTicket airTicketForward;
			AirTicket airTicketBack;
			AirTicketTwoWay airTicketTwoWay;
			float costTicket = 2;
			actions.pause(1000).perform();
			webDateDeparture = webDriverPage.findElements(By.cssSelector("div[class='departure'] strong"));
			jScript = "return arguments[0].innerHTML;";
			if (!flyWithReturn) {
				dateTickDepart = getTimeAirTicket((String) jsExecutor.executeScript(jScript, webDateDeparture.get(1)));
			} else {
				dateTickDepart = getTimeAirTicket((String) jsExecutor.executeScript(jScript, webDateDeparture.get(1)));
				dateTickReturn = getTimeAirTicket((String) jsExecutor.executeScript(jScript, webDateDeparture.get(3)));
			}
			jScript = "var x = document.querySelectorAll('div#outbound div div div div label')[0].textContent.length;"
					+ "return document.querySelectorAll('div#outbound div div div div label')[0].textContent.substr(x-3,x);";
			currency = (String) jsExecutor.executeScript(jScript);
			jScript = "return document.querySelectorAll('div#outbound div div div div input').length";
			k = (long) jsExecutor.executeScript(jScript);
			for (int i = 0; i < k; i++) {
				jScript = "var i = arguments[0];" + "var cssSelector = 'div#outbound div div div div input';"
						+ "var l = document.querySelectorAll(cssSelector)[i].id.length;"
						+ "var str = document.querySelectorAll(cssSelector)[i].id.substr(l-2,l);"
						+ "switch(str){ case 'EP': var out = 'Economy Promotion'; break;"
						+ "case 'ER': var out = 'Economy Restricted'; break;"
						+ "case 'SF': var out = 'Economic Semi-flexible'; break;"
						+ "case 'EF': var out = 'Economy Flexible'; break;" + "case 'BC': var out = 'Business'; break; "
						+ "default: var out = 'Business'; break;};"
						+ "document.querySelectorAll(cssSelector)[i].click();" + "return out;";
				classFlyDepart = (String) jsExecutor.executeScript(jScript, i);
				if (!flyWithReturn) {
					actions.pause(500).perform();
					jScript = "return document.querySelectorAll('div.total span.amount')[0].textContent;";
					strTemp = (String) jsExecutor.executeScript(jScript);
					costTicket = getCostTicket(strTemp);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, classFlyDepart,
							AirTicket.wayFlyReference[0], currency);
					daoAirTicket.addOneWay(airTicketForward);
				} else {
					actions.pause(500).perform();
					jScript = "var x = document.querySelectorAll('div#outbound div div div div label')[arguments[0]].textContent.length;"
							+ "return document.querySelectorAll('div#outbound div div div div label')[arguments[0]].textContent.substr(0,x-4);";
					strTemp = (String) jsExecutor.executeScript(jScript, i);
					costTicket = getCostTicket(strTemp);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, classFlyDepart,
							AirTicket.wayFlyReference[0], currency);
					jScript = "return document.querySelectorAll('div#inbound div div div div input').length";
					m = (long) jsExecutor.executeScript(jScript);
					for (int j = 0; j < m; j++) {
						jScript = "var i = arguments[0];" + "var cssSelector = 'div#inbound div div div div input';"
								+ "var l = document.querySelectorAll(cssSelector)[i].id.length;"
								+ "var str = document.querySelectorAll(cssSelector)[i].id.substr(l-2,l);"
								+ "switch(str){ case 'EP': var out = 'Economy Promotion'; break;"
								+ "case 'ER': var out = 'Economy Restricted'; break;"
								+ "case 'SF': var out = 'Economic Semi-flexible'; break;"
								+ "case 'EF': var out = 'Economy Flexible'; break;"
								+ "case 'BC': var out = 'Business'; break; " + "default: var out = 'Business'; break;};"
								+ "document.querySelectorAll(cssSelector)[i].click();" + "return out;";
						classFlyReturn = (String) jsExecutor.executeScript(jScript, j);
						actions.pause(500).perform();
						jScript = "var x = document.querySelectorAll('div#inbound div div div div label')[arguments[0]].textContent.length;"
								+ "return document.querySelectorAll('div#inbound div div div div label')[arguments[0]].textContent.substr(0,x-4);";
						strTemp = (String) jsExecutor.executeScript(jScript, j);
						costTicket = getCostTicket(strTemp);
						airTicketBack = new AirTicket(dateTickReturn, costTicket, classFlyReturn,
								AirTicket.wayFlyReference[1], currency);
						actions.pause(500).perform();
						jScript = "return document.querySelectorAll('div.total span.amount')[0].textContent;";
						strTemp = (String) jsExecutor.executeScript(jScript);
						costTicket = getCostTicket(strTemp);
						airTicketTwoWay = new AirTicketTwoWay(airTicketForward, airTicketBack, costTicket, currency);
						daoAirTicket.addTwoWay(airTicketTwoWay);
					}
				}
			}
		} else {
			actions.pause(3000).perform();
		}
		webDriverPage.get(SelectionTicketsPage.urlHomeBelavia);
		return daoAirTicket;
	}

}
