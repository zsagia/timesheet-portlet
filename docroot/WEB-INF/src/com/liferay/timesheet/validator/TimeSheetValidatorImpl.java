package com.liferay.timesheet.validator;

import com.liferay.portal.kernel.exception.PortalException;

import com.liferay.timesheet.TSEarliestStartTimeException;
import com.liferay.timesheet.TSEndTimeException;
import com.liferay.timesheet.TSFutureStartTimeException;
import com.liferay.timesheet.TSNoCurrentTaskSessionException;
import com.liferay.timesheet.TSStartEndTimeException;
import com.liferay.timesheet.TSStartTimeException;
import com.liferay.timesheet.TSWorkDurationException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.util.DateTimeConverterUtil;
import com.liferay.timesheet.util.PortletPropsValues;
import com.liferay.timesheet.validator.TimeSheetValidator;

import java.text.ParseException;
import java.util.Date;

public class TimeSheetValidatorImpl implements TimeSheetValidator {

	@Override
	public void validateStartTime(TaskSession taskSession, Date startTime)
		throws ParseException, PortalException {

		Date previousStartTime = taskSession.getStartTime();
		Date endTime = taskSession.getEndTime();

		if (!previousStartTime.before(startTime) ||
				((endTime != null) && endTime.after(startTime))) {

			throw new TSStartTimeException();
		}
	}

	@Override
	public void validateEndTime(TaskSession taskSession, Date endDate)
		throws PortalException {

		if (taskSession == null) {
			throw new TSNoCurrentTaskSessionException();
		}

		Date endTime = taskSession.getEndTime();

		if (endTime != null) {
			throw new TSNoCurrentTaskSessionException();
		}

		Date startTime = taskSession.getStartTime();

		if (endDate.before(startTime)) {
			throw new TSEndTimeException();
		}

	}

	@Override
	public void validateFutureStartTime(Date startTime, Date now)
		throws PortalException {

		if (startTime.after(now)) {
			throw new TSFutureStartTimeException();
		}
	}

	@Override
	public void validateLatestEndTime(Date workStart) throws PortalException {
		Date latestEndTimeRestriction =
			DateTimeConverterUtil.getDateFromMilitaryTime(
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

		if (workStart.after(latestEndTimeRestriction)) {
			throw new TSStartEndTimeException();
		}
	}

	@Override
	public void validateWorkStart(Date workStart) throws PortalException {
		Date earliestStartRestriction =
			DateTimeConverterUtil.getDateFromMilitaryTime(
				PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

		if (workStart.before(earliestStartRestriction)) {
			throw new TSEarliestStartTimeException();
		}
	}

	@Override
	public void validateWorkDuration(long allWorkTimeToday)
		throws PortalException {

		long maxWorkRestriction = Long.valueOf(
			PortletPropsValues.RESTRICTIONS_WORKDURATIOIN_MAX) * 1000 * 60;

		if (allWorkTimeToday > maxWorkRestriction) {
			throw new TSWorkDurationException();
		}
	}

}