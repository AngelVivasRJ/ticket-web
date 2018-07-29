package by.htp.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import by.htp.ticket.driver.DriverSingletone;
import by.htp.ticket.step.Step;

public class BaseTest {

	protected WebDriver driverSingletone;
	protected Step step;
	
	@BeforeSuite
	public void initDriver() {
		driverSingletone = DriverSingletone.getDriver();
		step = new Step(driverSingletone);
		step.openSelectionPage();
		step.initSelTicket();
		System.out.println("@BeforeSuite()");
	}
	
	@AfterSuite
	public void closeDriver() throws InterruptedException {
		Thread.sleep(5000); 
		DriverSingletone.closeWebDriver();
		System.out.println("@Aftersuite");
	}
}
