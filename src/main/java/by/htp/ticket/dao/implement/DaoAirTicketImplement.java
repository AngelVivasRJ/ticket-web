package by.htp.ticket.dao.implement;

import java.util.List;

import by.htp.ticket.dao.DaoAirTicket;
import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.AirTicketTwoWay;
import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.entity.ListAirTicketTwoWay;

public class DaoAirTicketImplement implements DaoAirTicket {

	private ListAirTicket listAirTicket;
	private ListAirTicketTwoWay listAirTicketTwoWay;
	
	public DaoAirTicketImplement() {
		super();
		this.listAirTicket = new ListAirTicket();
		this.listAirTicketTwoWay = new ListAirTicketTwoWay();
	}
	
	public DaoAirTicketImplement(ListAirTicket listAirTicket, ListAirTicketTwoWay listAirTicketTwoWay) {
		super();
		this.listAirTicket = listAirTicket;
		this.listAirTicketTwoWay = listAirTicketTwoWay;
	}

	@Override
	public ListAirTicket getListAirTicket() {		
		return listAirTicket;
	}

	@Override
	public ListAirTicketTwoWay getListAirTicketTwoWay() {
		return listAirTicketTwoWay;
	}

	@Override
	public List<AirTicket> getListStandarTicket() {
		return listAirTicket.getListAirTicket();
	}

	@Override
	public List<AirTicketTwoWay> getListStandarTicketTwoWay() {
		return listAirTicketTwoWay.getListAirTicketTwoWay();
	}

	@Override
	public void setListAirTicket(ListAirTicket listAirTicket) {
		this.listAirTicket = listAirTicket;
	}

	@Override
	public void setListAirTicketTwoWay(ListAirTicketTwoWay listAirTicketTwoWay) {
		this.listAirTicketTwoWay = listAirTicketTwoWay;
		
	}

	@Override
	public void setListStandarTicket(List<AirTicket> listStandarAirTicket) {
		this.listAirTicket.setListAirTicket(listStandarAirTicket);
	}

	@Override
	public void setListStandarTicketTwoWay(List<AirTicketTwoWay> listStandarTicketTwoWay) {
		this.listAirTicketTwoWay.setListAirTicketTwoWay(listStandarTicketTwoWay);
		
	}

	@Override
	public void addOneWay(AirTicket airTicket) {
		this.listAirTicket.getListAirTicket().add(airTicket);		
	}

	@Override
	public void addTwoWay(AirTicketTwoWay airTicketTwoWay) {
		this.listAirTicketTwoWay.getListAirTicketTwoWay().add(airTicketTwoWay);
	}

	
	
}
