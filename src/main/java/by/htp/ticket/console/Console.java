package by.htp.ticket.console;

import by.htp.ticket.dao.DaoAirTicket;
import by.htp.ticket.dao.implement.DaoAirTicketImplement;

public class Console {

	private DaoAirTicket daoAirTicket;
	
	public Console() {
		super();
		this.daoAirTicket = new DaoAirTicketImplement();
	}

	public Console(DaoAirTicket daoAirTicket) {
		super();
		this.daoAirTicket = daoAirTicket;
	}

	public void showListTicket() {
	System.out.println("List of Tickets one way");
		daoAirTicket.showListTicket();
	}

	public void showListTicketTwoWay() {
	System.out.println("List of Tickets two way");
		daoAirTicket.showListTicketTwoWay();
	}
}
