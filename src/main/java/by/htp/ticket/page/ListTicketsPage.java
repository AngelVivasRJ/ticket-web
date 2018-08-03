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
	private DaoAirTicket daoAirTicket;
	private JavascriptExecutor jsExecutor;
	private Calendar dateTickDepart = Calendar.getInstance();
	private Calendar dateTickReturn = Calendar.getInstance();
	private String currency = "";

	public ListTicketsPage(WebDriver webDriver, DaoAirTicket daoAirTicket) {
		super(webDriver);
		this.daoAirTicket = daoAirTicket;
		this.jsExecutor = (JavascriptExecutor) webDriverPage;
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

	private String clickGetClass(String cssSelector, int i) {
		return (String) jsExecutor.executeScript("var i = arguments[0];" + "var cssSelector = " + cssSelector + ";"
				+ "var l = document.querySelectorAll(cssSelector)[i].id.length;"
				+ "var str = document.querySelectorAll(cssSelector)[i].id.substr(l-2,l);"
				+ "switch(str){ case 'EP': var out = 'Economy Promotion'; break;"
				+ "case 'ER': var out = 'Economy Restricted'; break;"
				+ "case 'SF': var out = 'Economic Semi-flexible'; break;"
				+ "case 'EF': var out = 'Economy Flexible'; break;" + "case 'BC': var out = 'Business'; break; "
				+ "default: var out = 'Business'; break;};" + "document.querySelectorAll(cssSelector)[i].click();"
				+ "return out;", i);
	}

	private float getCostTicket(Boolean subString, String cssSelector, int i) {
		String costWebElement = "";
		if (!subString) {
			costWebElement = (String) jsExecutor
					.executeScript("return document.querySelectorAll(" + cssSelector + ")[0].textContent;");
		} else {
			costWebElement = (String) jsExecutor.executeScript("var x = document.querySelectorAll(" + cssSelector
					+ ")[arguments[0]].textContent.length;" + "return document.querySelectorAll(" + cssSelector
					+ ")[arguments[0]].textContent.substr(0,x-4);", i);
		}
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

	private void getDateCurrency(boolean flyWithReturn) {
		List<WebElement> webDateDeparture = webDriverPage.findElements(By.cssSelector("div[class='departure'] strong"));
		if (!flyWithReturn) {
			this.dateTickDepart = getTimeAirTicket(
					(String) jsExecutor.executeScript("return arguments[0].innerHTML;", webDateDeparture.get(1)));
		} else {
			this.dateTickDepart = getTimeAirTicket(
					(String) jsExecutor.executeScript("return arguments[0].innerHTML;", webDateDeparture.get(1)));
			this.dateTickReturn = getTimeAirTicket(
					(String) jsExecutor.executeScript("return arguments[0].innerHTML;", webDateDeparture.get(3)));
		}
		this.currency = (String) jsExecutor.executeScript(
				"var x = document.querySelectorAll('div#outbound div div div div label')[0].textContent.length;"
						+ "return document.querySelectorAll('div#outbound div div div div label')[0].textContent.substr(x-3,x);");
	}

	public void getAirTicketList(boolean flyWithReturn) {
		String classFlyDepart = "";
		String classFlyReturn = "";
		AirTicket airTicketForward;
		AirTicket airTicketBack;
		AirTicketTwoWay airTicketTwoWay;
		float costTicket = 2;
		this.actions = new Actions(webDriverPage);
		long k = 0, m = 0;
		this.waitSmth = new WebDriverWait(webDriverPage, 60);
		waitSmth.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.container.content div")));
		List<WebElement> idPageOpened = webDriverPage.findElements(By.cssSelector("div.container.content div"));
		String strIdPageOpened = idPageOpened.get(0).getAttribute("class"); // if "clear", then to read tickets
		if (strIdPageOpened.equals("panel") | (strIdPageOpened.equals("clear"))) {
			if (strIdPageOpened.equals("panel")) {
				actions.pause(30000).perform(); // captcha 30 seg
			}
			actions.pause(1000).perform();
			getDateCurrency(flyWithReturn);
			k = (long) jsExecutor
					.executeScript("return document.querySelectorAll('div#outbound div div div div input').length");
			for (int i = 0; i < k; i++) {
				classFlyDepart = clickGetClass("'div#outbound div div div div input'", i);
				if (!flyWithReturn) {
					actions.pause(500).perform();
					costTicket = getCostTicket(false, "'div.total span.amount'", i);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, classFlyDepart,
							AirTicket.wayFlyReference[0], currency);
					daoAirTicket.addOneWay(airTicketForward);
				} else {
					actions.pause(500).perform();
					costTicket = getCostTicket(true, "'div#outbound div div div div label'", i);
					airTicketForward = new AirTicket(dateTickDepart, costTicket, classFlyDepart,
							AirTicket.wayFlyReference[0], currency);
					m = (long) jsExecutor.executeScript(
							"return document.querySelectorAll('div#inbound div div div div input').length");
					for (int j = 0; j < m; j++) {
						classFlyReturn = clickGetClass("'div#inbound div div div div input'", j);
						actions.pause(500).perform();
						costTicket = getCostTicket(true, "'div#inbound div div div div label'", j);
						airTicketBack = new AirTicket(dateTickReturn, costTicket, classFlyReturn,
								AirTicket.wayFlyReference[1], currency);
						actions.pause(500).perform();
						costTicket = getCostTicket(false, "'div.total span.amount'", i);
						airTicketTwoWay = new AirTicketTwoWay(airTicketForward, airTicketBack, costTicket, currency);
						daoAirTicket.addTwoWay(airTicketTwoWay);
					}
				}
			}
		} else {
			actions.pause(3000).perform();
		}
		webDriverPage.get(SelectionTicketsPage.urlHomeBelavia);
	}

}
