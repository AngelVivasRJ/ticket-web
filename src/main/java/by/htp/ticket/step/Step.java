package by.htp.ticket.step;

import java.util.Date;
import org.openqa.selenium.WebDriver;

import by.htp.ticket.console.Console;
import by.htp.ticket.dao.DaoAirTicket;
import by.htp.ticket.dao.implement.DaoAirTicketImplement;
import by.htp.ticket.page.ListTicketsPage;
import by.htp.ticket.page.SelectionTicketsPage;

public class Step {

	private WebDriver webDriverStep;
	private SelectionTicketsPage selectionTicketsPage;
	private ListTicketsPage listTicketsPage;
	private DaoAirTicket daoAirTicket;
	private Console console;

	@SuppressWarnings("unused")
	private Step() {
		// Method Step() is private.
	}

	public Step(WebDriver webDriver) {
		this.webDriverStep = webDriver;
		this.daoAirTicket = new DaoAirTicketImplement();
		this.console = new Console(daoAirTicket);
	}

	public void openSelectionPage() {
		this.selectionTicketsPage = new SelectionTicketsPage(webDriverStep);
		this.listTicketsPage = new ListTicketsPage(webDriverStep);
		selectionTicketsPage.openPage();
	}

	public void initSelTicket() {
		openSelectionPage();
	}

	public void getTicketDate(boolean flyWithReturn, Date dateDeparture, Date dateReturn) {
		selectionTicketsPage.setDateDepartureReturn(flyWithReturn, dateDeparture, dateReturn);
		this.daoAirTicket = listTicketsPage.getAirTicketList(flyWithReturn, daoAirTicket);
	}

	public void showListOneWay() {
		System.out.println("Original list");
		console.showListTicket();

		System.out.println("Sorted list by cost");
		daoAirTicket.sortByCostFly();
		console.showListTicket();

		System.out.println("Sorted list by date");
		daoAirTicket.sortByDateFly();
		console.showListTicket();
	}

	public void showListTwoWay() {
		System.out.println("Original list");
		console.showListTicketTwoWay();
		

		System.out.println("Sorted list by date");
		daoAirTicket.sortByDateFly();;
		console.showListTicketTwoWay();
		
		System.out.println("Sorted list by cost");
		daoAirTicket.sortByCostFly();
		console.showListTicketTwoWay();
	}
}