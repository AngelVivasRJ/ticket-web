package by.htp.ticket.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingletone {


	private static final String CHROME = "webdriver.chrome.driver";
	private static final String CHROME_PATH = "d:\\d\\selenium\\driver\\chrome\\chromedriver.exe";
	
	private static WebDriver webDriver;

	static {
		System.setProperty(CHROME, CHROME_PATH);
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}

	private DriverSingletone() {
		//Method: DriverSingletone() is private.
	}

	public static WebDriver getDriver() {
		return webDriver;
	}
	
	public static void closeWebDriver() {
		webDriver.close();
		webDriver = null;
	}
}
