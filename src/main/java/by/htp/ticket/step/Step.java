package by.htp.ticket.step;

import java.util.Date;
import org.openqa.selenium.WebDriver;

import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.entity.ListAirTicketTwoWay;
import by.htp.ticket.page.ListTicketsPage;
import by.htp.ticket.page.SelectionTicketsPage;

public class Step {

	private WebDriver webDriverStep;
	private SelectionTicketsPage selectionTicketsPage;
	private ListTicketsPage listTicketsPage;
	private ListAirTicket listAirTicket;
	private ListAirTicketTwoWay listAirTicketTwoWay;

	@SuppressWarnings("unused")
	private Step() {
		// Method Step() is private.
	}

	public Step(WebDriver webDriver) {
		this.webDriverStep = webDriver;
	}

	public void openSelectionPage() {
		this.selectionTicketsPage = new SelectionTicketsPage(webDriverStep);
		this.listTicketsPage = new ListTicketsPage(webDriverStep);
		selectionTicketsPage.openPage();
	}

	public void initSelTicket() {
		openSelectionPage();
		this.listAirTicket = new ListAirTicket();
		this.listAirTicketTwoWay = new ListAirTicketTwoWay();
	}

	public void getTicketDate(boolean flyWithReturn, Date dateDeparture, Date dateReturn) {
		if (flyWithReturn) {
			selectionTicketsPage.setDateDepartureReturn(flyWithReturn, dateDeparture, dateReturn);
			this.listAirTicketTwoWay.setListAirTicketTwoWay(listTicketsPage.getListAirTicketsTwoWay(flyWithReturn,
					listAirTicketTwoWay.getListAirTicketTwoWay(), dateDeparture, dateReturn));
		} else {
			selectionTicketsPage.setDateDepartureReturn(flyWithReturn, dateDeparture, dateReturn);
			this.listAirTicket.setListAirTicket(listTicketsPage.getListAirTicketsOneWay(flyWithReturn,
					listAirTicket.getListAirTicket(), dateDeparture));
		}
	}

	public void showListOneWay() {
		System.out.println("Original list of Tickets one way");
		listAirTicket.showListTicket();
		System.out.println("Sorted list of one way tickets by cost");
		listAirTicket.sortByCostFly();
		listAirTicket.showListTicket();
		System.out.println("Sorted list of one way tickets by date");
		listAirTicket.sortByDateFly();
		listAirTicket.showListTicket();
	}

	public void showListTwoWay() {
		System.out.println("Original list of two way tickets");
		listAirTicketTwoWay.showListTicketTwoWay();
		/*
		 * System.out.println("Sorted list of two way tickets by date");
		 * listAirTicket.sortByDateFly(); listAirTicket.showListTicket();
		 * System.out.println("Sorted list of two way tickets by cost");
		 * listAirTicket.sortByCostFly(); listAirTicket.showListTicket();
		 */
	}
}