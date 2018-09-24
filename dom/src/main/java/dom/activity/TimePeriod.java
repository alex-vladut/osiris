package dom.activity;

import java.util.Date;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class TimePeriod implements Comparable<TimePeriod> {
	private Date date;
	private int hourStart;
	private int minutesStart;
	private int hourEnd;
	private int minutesEnd;

	public TimePeriod(
			final Date date, 
			final int hourStart, 
			final int minutesStart,
			final int hourEnd,
			final int minutesEnd) {
		setDate(date);
		setHourStart(hourStart);
		setHourEnd(hourEnd);
		setMinutesStart(minutesStart);
		setMinutesEnd(minutesEnd);
	}

	@Column(allowsNull = "true")
	public Date getDate() {
		return date;
	}

	private void setDate(Date date) {
		this.date = date;
	}

	@Column(allowsNull = "true")
	public int getHourStart() {
		return hourStart;
	}

	private void setHourStart(int hourStart) {
		this.hourStart = hourStart;
	}

	@Column(allowsNull = "true")
	public int getMinutesStart() {
		return minutesStart;
	}

	private void setMinutesStart(int minutesStart) {
		this.minutesStart = minutesStart;
	}

	@Column(allowsNull = "true")
	public int getHourEnd() {
		return hourEnd;
	}

	private void setHourEnd(int hourEnd) {
		this.hourEnd = hourEnd;
	}

	@Column(allowsNull = "true")
	public int getMinutesEnd() {
		return minutesEnd;
	}

	private void setMinutesEnd(int minutesEnd) {
		this.minutesEnd = minutesEnd;
	}
	
	@Override
	public int compareTo(final TimePeriod o) {
		return 0;
	}
}
