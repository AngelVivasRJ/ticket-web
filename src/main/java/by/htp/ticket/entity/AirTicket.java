package by.htp.ticket.entity;

import java.util.Calendar;
import java.util.Comparator;

public class AirTicket implements Comparable<AirTicket> {
	// Encapsulation verification
	public static final String wayFlyReference[] = { "Minsk(MSQ)-Ryga(RIX)", "Ryga(RIX)-Minsk (MSQ)" };
	public static final String classFlyReference[] = { "Economy Promotion", "Economy Restricted",
			"Economic Semi-flexible", "Economy Flexible", "Business" };

	private Calendar dateTimeFly;
	private float costFly;
	private String classFly;
	private String wayFly;

	private AirTicket() {
		super(); // It is not used.
	}

	public AirTicket(Calendar dateTimeFly, float costFly, String classFly, String wayFly) {
		super();
		this.dateTimeFly = dateTimeFly;
		this.costFly = costFly;
		this.classFly = classFly;
		this.wayFly = wayFly;
	}

	public Calendar getDateTimeFly() {
		return dateTimeFly;
	}

	public void setDateTimeFly(Calendar dateTimeFly) {
		this.dateTimeFly = dateTimeFly;
	}

	public float getCostFly() {
		return costFly;
	}

	public void setCostFly(float costFly) {
		this.costFly = costFly;
	}

	public String getClassFly() {
		return classFly;
	}

	public void setClassFly(String classFly) {
		this.classFly = classFly;
	}

	public String getWayFly() {
		return wayFly;
	}

	public void setWayFly(String wayFly) {
		this.wayFly = wayFly;
	}

	public String toStringCalendar(Calendar calendar) {
		String stemp = calendar.getTime().toString();
		return stemp.substring(4, 7) + " " + stemp.substring(8, 10) + " "
				+ stemp.substring(stemp.length() - 4, stemp.length()) + " " + stemp.substring(11, 13) + ":"
				+ stemp.substring(14, 16);
	}

	@Override
	public String toString() {
		return wayFly + " " + toStringCalendar(dateTimeFly) + " " + String.format("%.2f", costFly) + " EUR" + " "
				+ classFly;
	}

	@Override
	public int compareTo(AirTicket airTicket) {
		return Comparators.COST.compare(this, airTicket);
	}

	public static class Comparators {

		public static Comparator<AirTicket> COST = new Comparator<AirTicket>() {

			@Override
			public int compare(AirTicket arg0, AirTicket arg1) {
				float fDif = arg0.costFly - arg1.costFly;
				if (fDif > 0) {
					return 1;
				} else if (fDif < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		};
		public static Comparator<AirTicket> DATE = new Comparator<AirTicket>() {

			@Override
			public int compare(AirTicket arg0, AirTicket arg1) {
				if (arg0.dateTimeFly.after(arg1.dateTimeFly)) {
					return 1;
				} else if (arg0.dateTimeFly.before(arg1.dateTimeFly)) {
					return -1;
				} else {
					return 0;
				}
			}
		};
	}
}
