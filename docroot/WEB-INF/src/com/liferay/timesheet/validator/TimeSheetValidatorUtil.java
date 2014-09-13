package com.liferay.timesheet.validator;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

/**
* @author Zsolt Szabo
* @author Istvan Sajtos
*/
public class TimeSheetValidatorUtil {

	public static TimeSheetValidator getTimeSheetValidator() {
		return timeSheetValidator;
	}

	public static boolean validateAfter(
			Date endTime, Date newStartTime, Date newEndTime)
		throws PortalException {

		return getTimeSheetValidator().validateAfter(
			endTime, newStartTime, newEndTime);
	}

	public static boolean validateBefore(
			Date startTime, Date newStartTime, Date newEndTime)
		throws PortalException {

		return getTimeSheetValidator().validateBefore(
			startTime, newStartTime, newEndTime);
	}

	public static void validateBetween(
			Date startTime, Date endTime, Date newTime)
		throws PortalException {

		getTimeSheetValidator().validateBetween(startTime, endTime, newTime);
	}

	public static void validateEndTime(Date endTime) throws PortalException {
		getTimeSheetValidator().validateEndTime(endTime);
	}

	public static void validateEndTime(
			Date startTime, Date endTime, Date newEndTime)
		throws PortalException {

		getTimeSheetValidator().validateEndTime(startTime, endTime, newEndTime);
	}

	public static void validateFutureTime(Date time, Date now)
		throws PortalException {

		getTimeSheetValidator().validateFutureTime(time, now);
	}

	public static void validateLatestEndTime(
			Date workStart, Date latestEndTimeRestriction)
		throws PortalException {

		getTimeSheetValidator().validateLatestEndTime(
			workStart, latestEndTimeRestriction);
	}

	public static void validateStartAndEndTime(Date startTime, Date endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		getTimeSheetValidator().validateStartAndEndTime(startTime, endTime);
	}

	public static void validateStartTime(Date startTime)
		throws PortalException {

		getTimeSheetValidator().validateStartTime(startTime);
	}

	public static void validateStartTime(
			Date startTime, Date endTime, Date newStartTime)
		throws Exception {

		getTimeSheetValidator().validateStartTime(
			startTime, endTime, newStartTime);
	}

	public static void validateWorkDuration(long allWorkToday)
		throws PortalException {

		getTimeSheetValidator().validateWorkDuration(allWorkToday);
	}

	public static void validateWorkStart(
			Date workStart, Date earliestStartRestriction)
		throws PortalException {

		getTimeSheetValidator().validateWorkStart(
			workStart, earliestStartRestriction);
	}

	public void setTimeSheetValidator(TimeSheetValidator validator) {
		timeSheetValidator = validator;
	}

	private static TimeSheetValidator timeSheetValidator;

}