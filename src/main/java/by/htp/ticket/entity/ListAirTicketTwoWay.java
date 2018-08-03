package by.htp.ticket.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListAirTicketTwoWay {

	private List<AirTicketTwoWay> listAirTicketTwoWay;

	public ListAirTicketTwoWay() {
		super();
		this.listAirTicketTwoWay = new ArrayList<AirTicketTwoWay>();
	}

	public ListAirTicketTwoWay(List<AirTicketTwoWay> listAirTicketTwoWay) {
		super();
		this.listAirTicketTwoWay = listAirTicketTwoWay;
	}

	public List<AirTicketTwoWay> getListAirTicketTwoWay() {
		return listAirTicketTwoWay;
	}

	public void setListAirTicketTwoWay(List<AirTicketTwoWay> listAirTicketTwoWay) {
		this.listAirTicketTwoWay = listAirTicketTwoWay;
	}

	@Override
	public String toString() {
		return "ListAirTicketTwoWay [listAirTicketTwoWay=" + listAirTicketTwoWay + "]";
	}

	public void showListTicketTwoWay() {
		System.out.println("----------------------");
		int i = 1;
		for (AirTicketTwoWay airTicketTwoWay : listAirTicketTwoWay) {
			System.out.println(i + ": " + airTicketTwoWay.toString());
			i++;
		}
		System.out.println("----------------------");
	}
	
	public void sortByCostFly() {
		Collections.sort(this.listAirTicketTwoWay, AirTicketTwoWay.Comparators.COSTTW);
	}
	
	public void sortByDateFly() {
		Collections.sort(this.listAirTicketTwoWay, AirTicketTwoWay.Comparators.DATETW);
	}	
}
