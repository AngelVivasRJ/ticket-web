package by.htp.ticket.dao;

import java.util.List;

import by.htp.ticket.entity.AirTicket;
import by.htp.ticket.entity.AirTicketTwoWay;
import by.htp.ticket.entity.ListAirTicket;
import by.htp.ticket.entity.ListAirTicketTwoWay;

public interface DaoAirTicket {
	
	public ListAirTicket getListAirTicket();
	
	public ListAirTicketTwoWay getListAirTicketTwoWay();
	
	public List<AirTicket> getListStandarTicket();
	
	public List<AirTicketTwoWay> getListStandarTicketTwoWay();
	
	public void setListAirTicket(ListAirTicket listAirTicket);
	
	public void setListAirTicketTwoWay(ListAirTicketTwoWay listAirTicketTwoWay);
	
	public void setListStandarTicket(List<AirTicket> listStandarAirTicket);
	
	public void setListStandarTicketTwoWay(List<AirTicketTwoWay> listStandarTicketTwoWay);
	
	public void addOneWay(AirTicket airTicket);
	
	public void addTwoWay(AirTicketTwoWay airTicketTwoWay);

}
