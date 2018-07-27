package by.htp.ticket.entity;

import java.util.ArrayList;
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
}
