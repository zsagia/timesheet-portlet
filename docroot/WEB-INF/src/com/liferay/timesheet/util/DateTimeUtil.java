package com.liferay.timesheet.util;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @author Zsolt Szabo
*/

public class DateTimeUtil {

	public static String DATE_FORMAT_WITHOUT_TIME = "yyyy/MM/dd";
	public static String TIME_FORMAT_WITHOUT_DATE = "HH:mm";

	public static Date addDateToDate(Date date1, Date date2) {
		long timeMilis = date1.getTime();

		return addTimeToDate(date2, timeMilis);
	}

	public static Date addTimeToDate(Date date, long timeMilis) {
		long timeMilis2 = date.getTime();

		return new Date(timeMilis2 + timeMilis);
	}

	public static ServiceContext createServiceContext() {
		LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay();
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());

		return serviceContext;
	}

	public static long getCompanyId()
		throws PortalException, SystemException {

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getCompanyId();
	}

	public static User getCurrentUser()
		throws PortalException, SystemException {

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getUser();
	}

	public static long getCurrentUserId() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getUserId();
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
			new SimpleDateFormat(TIME_FORMAT_WITHOUT_DATE);

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

}