package by.htp.ticket.step;

import java.util.Date;
import org.openqa.selenium.WebDriver;

import by.htp.ticket.dao.DaoAirTicket;
import by.htp.ticket.dao.implement.DaoAirTicketImplement;
import by.htp.ticket.page.ListTicketsPage;
import by.htp.ticket.page.SelectionTicketsPage;

public class Step {

	private WebDriver webDriverStep;
	private SelectionTicketsPage selectionTicketsPage;
	private ListTicketsPage listTicketsPage;
	private DaoAirTicket daoAirTicket;

	@SuppressWarnings("unused")
	private Step() {
		// Method Step() is private.
	}

	public Step(WebDriver webDriver) {
		this.webDriverStep = webDriver;
		this.daoAirTicket = new DaoAirTicketImplement();
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
		System.out.println("Original list of Tickets one way");
		daoAirTicket.getListAirTicket().showListTicket();

		System.out.println("Sorted list of one way tickets by cost");
		daoAirTicket.getListAirTicket().sortByCostFly();
		daoAirTicket.getListAirTicket().showListTicket();

		System.out.println("Sorted list of one way tickets by date");
		daoAirTicket.getListAirTicket().sortByDateFly();
		daoAirTicket.getListAirTicket().showListTicket();
	}

	public void showListTwoWay() {
		System.out.println("Original list of two way tickets");
		daoAirTicket.getListAirTicketTwoWay().showListTicketTwoWay();

		System.out.println("Sorted list of two way tickets by date");
		daoAirTicket.getListAirTicketTwoWay().sortByDateFly();;
		daoAirTicket.getListAirTicketTwoWay().showListTicketTwoWay();
		
		System.out.println("Sorted list of two way tickets by cost");
		daoAirTicket.getListAirTicketTwoWay().sortByCostFly();
		daoAirTicket.getListAirTicketTwoWay().showListTicketTwoWay();
	}
}