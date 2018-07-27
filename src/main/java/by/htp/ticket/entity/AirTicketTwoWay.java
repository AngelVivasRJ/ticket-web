package by.htp.ticket.entity;

public class AirTicketTwoWay {

	private AirTicket AirTicketForward;
	private AirTicket AirTicketBack;
	private float allCostTicket;
	
	private AirTicketTwoWay() {
		super();
	}

	public AirTicketTwoWay(AirTicket airTicketForward, AirTicket airTicketBack, float allCostTicket) {
		super();
		AirTicketForward = airTicketForward;
		AirTicketBack = airTicketBack;
		this.allCostTicket = allCostTicket;
	}

	public AirTicket getAirTicketForward() {
		return AirTicketForward;
	}

	public void setAirTicketForward(AirTicket airTicketForward) {
		AirTicketForward = airTicketForward;
	}

	public AirTicket getAirTicketBack() {
		return AirTicketBack;
	}

	public void setAirTicketBack(AirTicket airTicketBack) {
		AirTicketBack = airTicketBack;
	}

	public float getAllCostTicket() {
		return allCostTicket;
	}

	public void setAllCostTicket(float allCostTicket) {
		this.allCostTicket = allCostTicket;
	}

	@Override
	public String toString() {
		return "AirTicketTwoWay [AirTicketForward=" + AirTicketForward + ", AirTicketBack=" + AirTicketBack
				+ ", allCostTicket=" + allCostTicket + "]";
	}	
}
