package com.liferay.timesheet.validator;

import com.liferay.timesheet.CurrentTaskSessionIsAlreadyEndedException;
import com.liferay.timesheet.EarliestStartTimeException;
import com.liferay.timesheet.EndTimeException;
import com.liferay.timesheet.FutureStartTimeException;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.StartEndTimeException;
import com.liferay.timesheet.StartTimeException;
import com.liferay.timesheet.WorkDurationException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.util.DateTimeConverterUtil;
import com.liferay.timesheet.util.PortletPropsValues;

import java.text.ParseException;
import java.util.Date;

/**
* @author Zsolt Szabo
* @author Istvan Sajtos
*/
public class DateTimeValidatorUtil {

	public static void validateStartTime(
			TaskSession taskSession, Date startTime)
		throws ParseException, StartTimeException, EarliestStartTimeException {

		Date previousStartTime = taskSession.getStartTime();
		Date endTime = taskSession.getEndTime();

		if (!previousStartTime.before(startTime) ||
				((endTime != null) && endTime.after(startTime))) {

			throw new StartTimeException();
		}
	}

	public static void validateEndTime(
			TaskSession taskSession, Date endDate)
		throws CurrentTaskSessionIsAlreadyEndedException, EndTimeException,
			NoCurrentTaskSessionException {

		if (taskSession == null) {
			throw new NoCurrentTaskSessionException();
		}

		Date endTime = taskSession.getEndTime();

		if (endTime != null) {
			throw new CurrentTaskSessionIsAlreadyEndedException();
		}

		Date startTime = taskSession.getStartTime();

		if (endDate.before(startTime)) {
			throw new EndTimeException();
		}
	}

	public static void validateFutureStartTime(
			Date startTime, Date now)
		throws FutureStartTimeException {

		if (startTime.after(now)) {
			throw new FutureStartTimeException();
		}
	}

	public static void validateLatestEndTime(Date workStart)
		throws StartEndTimeException {

		Date latestEndTimeRestriction =
			DateTimeConverterUtil.getDateFromMilitaryTime(
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

		if (workStart.after(latestEndTimeRestriction)) {
			throw new StartEndTimeException();
		}
	}

	public static void validateWorkStart(Date workStart)
		throws EarliestStartTimeException {

		Date earliestStartRestriction =
			DateTimeConverterUtil.getDateFromMilitaryTime(
				PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

		if (workStart.before(earliestStartRestriction)) {
			throw new EarliestStartTimeException();
		}
	}

	public static void validateWorkDuration(long allWorkToday)
		throws WorkDurationException {
	
		long maxWorkRestriction = Long.valueOf(
			PortletPropsValues.RESTRICTIONS_WORKDURATIOIN_MAX) * 1000 * 60;

		if (allWorkToday > maxWorkRestriction) {
			throw new WorkDurationException();
		}
	}

}