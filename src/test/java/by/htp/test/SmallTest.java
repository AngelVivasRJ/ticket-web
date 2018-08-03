package by.htp.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import by.htp.ticket.driver.DriverSingletone;
import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.step.Step;

public class SmallTest {

	protected WebDriver driverSingletone;
	protected Step step;

	@BeforeSuite
	public void initDriver() {
		driverSingletone = DriverSingletone.getDriver();
		step = new Step(driverSingletone);
		step.openSelectionPage();
		// step.initSelTicket();
		System.out.println("@BeforeSuite()");
	}

	@AfterSuite
	public void closeDriver() throws InterruptedException {
		Thread.sleep(5000);
		DriverSingletone.closeWebDriver();
		System.out.println("@Aftersuite");
	}

	// @Test(groups = "Off")
	public void smallTest() {
		Calendar dayFly = Calendar.getInstance();
		dayFly.set(2018, 8, 3, 4, 25, 30);

		System.out.println("all Calendar:" + dayFly.getTime().toString() + ":");
		System.out.println("day:" + dayFly.getTime().toString().substring(8, 10) + ":");
		System.out.println("month:" + dayFly.getTime().toString().substring(4, 7) + ":");
	}

	// @Test(groups = "Off")
	public void smallTest1() {
		Calendar calendarFly = Calendar.getInstance();
		calendarFly.set(2018, 8, 1, 15, 25);
		System.out.println(":" + calendarFly.getTime().toString() + ":");
		AirTicket airTicketTemp = new AirTicket(calendarFly, 25.8f, AirTicket.classFlyReference[0],
				AirTicket.wayFlyReference[0], "BYN");
		System.out.println(airTicketTemp.toString());
	}

	// @Test(groups = "now")
	public void javascriptTest() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driverSingletone;
		Actions actions = new Actions(driverSingletone);
		actions.pause(8000).perform();

		Object value = jsExecutor.executeScript("var x = 3;" + "return x;");

		// Object value = jsExecutor.executeAsyncScript("var x = 3;" + "return x;");
		// Object value = ();

		System.out.println("Javascrip, testvalue:" + value);
		System.out.println("Javascrip, testvalue:" + value);
		System.out.println("Javascrip, testvalue:" + value);
	}

	@Test
	public void regularExpresionTest() {
		String costWebElement = "1 236,56";
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
		regExp = "(\\w)(\\s+])(\\.,)";
		String strCost1 = costWebElement;
		strCost1 = strCost1.replaceAll(regExp, "$1$3");
		strCost1 = "";
	}
}
