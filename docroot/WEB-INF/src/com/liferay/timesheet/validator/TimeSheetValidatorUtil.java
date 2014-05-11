package com.liferay.timesheet.validator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.timesheet.model.TaskSession;
import java.util.Date;

/**
* @author Zsolt Szabo
* @author Istvan Sajtos
*/
public class TimeSheetValidatorUtil {

	public static void validateStartTime(
			TaskSession taskSession, Date startTime)
		throws Exception {

		getTimeSheetValidator().validateStartTime(taskSession, startTime);
	}

	public static void validateEndTime(TaskSession taskSession, Date endDate)
		throws PortalException {

		getTimeSheetValidator().validateEndTime(taskSession, endDate);
	}

	public static void validateFutureStartTime(Date startTime, Date now)
		throws PortalException {

		getTimeSheetValidator().validateFutureStartTime(startTime, now);
	}

	public static void validateLatestEndTime(Date workStart)
		throws Exception {

		getTimeSheetValidator().validateLatestEndTime(workStart);
	}

	public static void validateWorkStart(Date workStart)
		throws Exception {

		getTimeSheetValidator().validateWorkStart(workStart);
	}

	public static void validateWorkDuration(long allWorkToday)
		throws PortalException {

		getTimeSheetValidator().validateWorkDuration(allWorkToday);
	}

	public static TimeSheetValidator getTimeSheetValidator() {
		return timeSheetValidator;
	}

	public void setTimeSheetValidator(
		TimeSheetValidator validator) {

		timeSheetValidator = validator;
	}

	private static TimeSheetValidator timeSheetValidator;

}