package by.htp.ticket.step;

import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.WebDriver;

import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.page.ListTicketsPage;
import by.htp.ticket.page.SelectionTicketsPage;

public class Step {

	private WebDriver webDriverStep;
	private SelectionTicketsPage selectionTicketsPage;
	private ListTicketsPage listTicketsPage;
	private ListAirTicket listAirTicket;	
	
	@SuppressWarnings("unused")
	private Step() {
		// Method Step() is private.
	}
	
	public Step(WebDriver webDriver) {
		this.webDriverStep = webDriver;
	}
	
	public void openSelection() {
		this.selectionTicketsPage = new SelectionTicketsPage(webDriverStep);
		this.listTicketsPage = new ListTicketsPage(webDriverStep);
		selectionTicketsPage.openPage();		
	}

	public void initSelTask1() {
		openSelection();
		selectionTicketsPage.initSelTask1();
		this.listAirTicket = new ListAirTicket();
		
	}
	
	public void getTicketDate(Calendar day){
		selectionTicketsPage.setDateDeparture(day); 		
		this.listAirTicket = listTicketsPage.getListAirTicketsOneWay(listAirTicket, day); 
		
	}
}