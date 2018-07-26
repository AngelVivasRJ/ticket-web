package by.htp.ticket.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListAirTicket {

	private List<AirTicket> listAirTicket;

	public ListAirTicket() {
		super();
		this.listAirTicket = new ArrayList<AirTicket>(); //constructor
	}

	public ListAirTicket(List<AirTicket> listAirTicket) {
		super();
		this.listAirTicket = listAirTicket;
	}

	public List<AirTicket> getListAirTicket() {
		return listAirTicket;
	}

	public void setListAirTicket(List<AirTicket> listAirTicket) {
		this.listAirTicket = listAirTicket;
	}

	@Override
	public String toString() {
		return "ListAirTicket [listAirTicket=" + listAirTicket + "]";
	}
	
	public void showListTicket() {
		System.out.println("----------------------");
		for (AirTicket airTicket : listAirTicket) {
			System.out.println(airTicket.toString());
		}
		System.out.println("----------------------");
	}
	
	public void sortByCostFly() {
		Collections.sort(this.listAirTicket, AirTicket.Comparators.COST);
	}
	
	public void sortByDateFly() {
		Collections.sort(this.listAirTicket, AirTicket.Comparators.DATE);
	}
}
