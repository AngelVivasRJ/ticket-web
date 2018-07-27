package by.htp.ticket.step;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;

import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.AirTicketTwoWay;
import by.htp.ticket.page.ListTicketsPage;
import by.htp.ticket.page.SelectionTicketsPage;

public class Step {

	private WebDriver webDriverStep;
	private SelectionTicketsPage selectionTicketsPage;
	private ListTicketsPage listTicketsPage;
	private ListAirTicket listAirTicket;
	private AirTicketTwoWay listAirTicketTwoWay;
	
	@SuppressWarnings("unused")
	private Step() {
		// Method Step() is private.
	}
	//------------
	
	//------------
	public Step(WebDriver webDriver) {
		this.webDriverStep = webDriver;
	}
	
	public void openSelectionPage() {
		this.selectionTicketsPage = new SelectionTicketsPage(webDriverStep);
		this.listTicketsPage = new ListTicketsPage(webDriverStep);
		selectionTicketsPage.openPage();		
	}

	public void initSelTask1() {
		openSelectionPage();
		this.listAirTicket = new ListAirTicket();
		
	}
	
	public void getTicketDate(Date day){
		selectionTicketsPage.setDateDeparture(day); 		
		this.listAirTicket = listTicketsPage.getListAirTicketsOneWay(listAirTicket,day); 
		
	}
	
	public void showListOneWay() {
		System.out.println("Original List of Tickets one way");
		listAirTicket.showListTicket();
		System.out.println("Sorted List of Tickets one way by cost");
		listAirTicket.sortByCostFly();
		listAirTicket.showListTicket();
		System.out.println("Sorted List of Tickets one way by date");
		listAirTicket.sortByDateFly();
		listAirTicket.showListTicket();
	}
}