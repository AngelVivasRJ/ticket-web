package by.htp.ticket.entity;

import java.util.Comparator;

public class AirTicketTwoWay {

	private AirTicket airTicketForward;
	private AirTicket airTicketBack;
	private float allCostTicket;
	
	private AirTicketTwoWay() {
		super();
	}

	public AirTicketTwoWay(AirTicket airTicketForward, AirTicket airTicketBack, float allCostTicket) {
		super();
		this.airTicketForward = airTicketForward;
		this.airTicketBack = airTicketBack;
		this.allCostTicket = allCostTicket;
	}

	public AirTicket getAirTicketForward() {
		return airTicketForward;
	}

	public void setAirTicketForward(AirTicket airTicketForward) {
		this.airTicketForward = airTicketForward;
	}

	public AirTicket getAirTicketBack() {
		return airTicketBack;
	}

	public void setAirTicketBack(AirTicket airTicketBack) {
		this.airTicketBack = airTicketBack;
	}

	public float getAllCostTicket() {
		return allCostTicket;
	}

	public void setAllCostTicket(float allCostTicket) {
		this.allCostTicket = allCostTicket;
	}

	@Override
	public String toString() {
		return airTicketForward.toString() + airTicketBack.toString() + "Cost: " + Float.toString(allCostTicket) + "EUR";
	}	
	
	public static class Comparators {

		public static Comparator<AirTicketTwoWay> COST = new Comparator<AirTicketTwoWay>() {

			@Override
			public int compare(AirTicketTwoWay arg0, AirTicketTwoWay arg1) {
				float fDif = arg0.allCostTicket - arg1.allCostTicket;
				if (fDif > 0) {
					return 1;
				} else if (fDif < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		public static Comparator<AirTicketTwoWay> DATE = new Comparator<AirTicketTwoWay>() {

			@Override
			public int compare(AirTicketTwoWay arg0, AirTicketTwoWay arg1) {
				if (arg0.airTicketForward.getDateTimeFly().after(arg1.airTicketBack.getDateTimeFly())) {
					return 1;
				} else if (arg0.airTicketForward.getDateTimeFly().before(arg1.airTicketForward.getDateTimeFly())) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}
}
