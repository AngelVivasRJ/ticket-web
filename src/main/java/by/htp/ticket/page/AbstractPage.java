package by.htp.ticket.page;

import javax.naming.OperationNotSupportedException;

import org.openqa.selenium.WebDriver;


public abstract class AbstractPage {

	protected WebDriver webDriverPage;

	public AbstractPage(WebDriver webDriver)
	{
		this.webDriverPage = webDriver;
	}
	
	public abstract void openPage() throws OperationNotSupportedException;	
}
