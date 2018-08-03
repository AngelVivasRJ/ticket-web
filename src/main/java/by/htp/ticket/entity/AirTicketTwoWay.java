package by.htp.ticket.entity;

import java.util.Calendar;
import java.util.Comparator;

public class AirTicketTwoWay implements Comparable<AirTicketTwoWay> {

	private AirTicket airTicketForward;
	private AirTicket airTicketBack;
	private float allCostTicket;
	private String currency;

	private AirTicketTwoWay() {
		super();
		this.airTicketForward = new AirTicket();
		this.airTicketBack = new AirTicket();
		this.allCostTicket = 0;
		this.currency = "BYN";
	}

	public AirTicketTwoWay(AirTicket airTicketForward, AirTicket airTicketBack, float allCostTicket, String currency) {
		super();
		this.airTicketForward = airTicketForward;
		this.airTicketBack = airTicketBack;
		this.allCostTicket = allCostTicket;
		this.currency = currency;
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
	
	public AirTicketTwoWay(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return airTicketForward.toString() + System.lineSeparator() + airTicketBack.toString() + System.lineSeparator()
				+ "Cost: " + Float.toString(allCostTicket) + " " + currency;
	}

	public int compareTo(AirTicketTwoWay o1, AirTicketTwoWay o2) {

		return 0;
	}

	@Override
	public int compareTo(AirTicketTwoWay airTicketTwoWay) {

		return Comparators.COSTTW.compare(this, airTicketTwoWay);
	}

	public static class Comparators {

		public static Comparator<AirTicketTwoWay> COSTTW = new Comparator<AirTicketTwoWay>() {

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
		public static Comparator<AirTicketTwoWay> DATETW = new Comparator<AirTicketTwoWay>() {

			@Override
			public int compare(AirTicketTwoWay arg0, AirTicketTwoWay arg1) {
				if (arg0.airTicketForward.getDateTimeFly().after(arg1.airTicketBack.getDateTimeFly())) {
					return 1;
				} else if (arg0.airTicketForward.getDateTimeFly().before(arg1.airTicketBack.getDateTimeFly())) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}
}
