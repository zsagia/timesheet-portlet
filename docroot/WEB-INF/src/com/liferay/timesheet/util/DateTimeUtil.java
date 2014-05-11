package com.liferay.timesheet.util;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.convert.ConverterException;

public class DateTimeUtil {

	public static Date addDateToDate(Date date1, Date date2) {
		long timeMilis = date1.getTime();

		return addTimeToDate(date2, timeMilis);
	}

	public static Date addTimeToDate(Date date, long timeMilis) {
		long timeMilis2 = date.getTime();

		return new Date(timeMilis2 + timeMilis);
	}
	
	public static Date getTodayWithoutTime() throws ParseException {
		Date today = new Date();

		return getDayWithoutTime(today);
	}

	public static Date getDayWithoutTime(Date date) throws ParseException {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static long getTimeWithoutDate(Date date) throws ParseException {
		DateFormat timeFormatWithoutDate =
			new SimpleDateFormat(TimeSheetConstants.TIME_FORMAT_WITHOUT_DATE);

		return timeFormatWithoutDate.parse(
			timeFormatWithoutDate.format(date)).getTime();
	}

	public static Date getIncrementedDay(Date day) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);

		return calendar.getTime();
	}

	public static Date getDateFromMilitaryTime(String value) {
		Date date = null;

		if (Validator.isNotNull(value)) {
			try {
				User user = TimeSheetUtil.getCurrentUser();
				TimeZone userTimeZone = user.getTimeZone();

				Calendar calendar = CalendarFactoryUtil.getCalendar(
					userTimeZone);

				if (Validator.isNotNull(value)) {
					int hour = Integer.valueOf(value.substring(0, 2));
					int minute = Integer.valueOf(
						value.substring(2, value.length()));

					calendar.set(Calendar.HOUR_OF_DAY, hour);
					calendar.set(Calendar.MINUTE, minute);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);
				}

				date = calendar.getTime();

			} catch (Exception e) {
				logger.error("date_conversion_is_failed", e);

				throw new ConverterException();
			}
		}

		return date;
	}

	private static final Logger logger =
			LoggerFactory.getLogger(DateTimeUtil.class);

}