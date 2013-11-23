package com.liferay.timesheet.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimesheetUtil {

	public static String DATE_FORMAT_WITHOUT_TIME = "yyyy/MM/dd";

	public static Date addDateToDate(Date date1, Date date2) {
		long timeMilis = date1.getTime();

		return addTimeToDate(date2, timeMilis);
	}

	public static Date addTimeToDate(Date date, long timeMilis) {
		long timeMilis2 = date.getTime();

		return new Date(timeMilis2 + timeMilis);
	}

	public static Date getTodayWithoutTime() throws ParseException {
		DateFormat dateFormatWithoutTime =
			new SimpleDateFormat(DATE_FORMAT_WITHOUT_TIME);

		Date today = new Date();

		return dateFormatWithoutTime.parse(dateFormatWithoutTime.format(today));
	}

}