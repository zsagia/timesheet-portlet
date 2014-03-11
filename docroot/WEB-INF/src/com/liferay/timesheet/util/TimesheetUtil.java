package com.liferay.timesheet.util;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author Zsolt Szabo
*/

import javax.faces.context.FacesContext;

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

	public static ServiceContext createServiceContext() {
		LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay();
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());

		return serviceContext;
	}

	public static User getCurrentUser()
		throws PortalException, SystemException {

		long userId = getCurrentUserId();

		return UserLocalServiceUtil.getUser(userId);
	}

	public static long getCurrentUserId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		return userId;
	}

	public static Date getTodayWithoutTime() throws ParseException {
		DateFormat dateFormatWithoutTime =
			new SimpleDateFormat(DATE_FORMAT_WITHOUT_TIME);

		Date today = new Date();

		return dateFormatWithoutTime.parse(dateFormatWithoutTime.format(today));
	}

}