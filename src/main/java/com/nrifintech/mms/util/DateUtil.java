package com.nrifintech.mms.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateUtil {

	private final static ZoneId TZ = ZoneId.of("Asia/Calcutta");

	private final Locale locale;
	private final DayOfWeek firstDayOfWeek;
	private final DayOfWeek lastDayOfWeek;

	public DateUtil() {
		this.locale = Locale.FRANCE;
		this.firstDayOfWeek = WeekFields.of(locale).getFirstDayOfWeek();
		this.lastDayOfWeek = DayOfWeek.of(((this.firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);
	}

	public LocalDate getFirstDayOfMonth() {
		return LocalDate.now(TZ).with(TemporalAdjusters.firstDayOfMonth());
	}

	public LocalDate getLastDayOfMonth() {
		return LocalDate.now(TZ).with(TemporalAdjusters.lastDayOfMonth());
	}

	public LocalDate getFirstDayOfWeek() {
		return LocalDate.now(TZ).with(TemporalAdjusters.previousOrSame(this.firstDayOfWeek));
	}

	public LocalDate getLastDayOfWeek() {
		return LocalDate.now(TZ).with(TemporalAdjusters.nextOrSame(this.lastDayOfWeek));
	}

	public boolean isDateInCurrentWeek(LocalDate date) {
		LocalDate firstDay = this.getFirstDayOfWeek();
		LocalDate lastDay = this.getLastDayOfWeek();
		return firstDay.compareTo(date) * date.compareTo(lastDay) >= 0;
	}

	public boolean isDateInCurrentMonth(LocalDate date) {
		LocalDate firstDay = this.getFirstDayOfMonth();
		LocalDate lastDay = this.getLastDayOfMonth();
		return firstDay.compareTo(date) * date.compareTo(lastDay) >= 0;
	}

}
