package com.liferay.timesheet.validator;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.timesheet.TSEarliestStartTimeException;
import com.liferay.timesheet.TSEndTimeException;
import com.liferay.timesheet.TSFutureStartTimeException;
import com.liferay.timesheet.TSStartEndTimeException;
import com.liferay.timesheet.TSStartTimeException;
import com.liferay.timesheet.TSWorkDurationException;
import com.liferay.timesheet.util.PortletPropsValues;

import java.util.Date;
public class TimeSheetValidatorImpl implements TimeSheetValidator {

	@Override
	public boolean validateAfter(
			Date endTime, Date newStartTime, Date newEndTime)
		throws PortalException {

		if (newEndTime.after(endTime)) {
			if (!newStartTime.before(endTime)) {
				return true;
			}

			throw new TSStartTimeException();
		}

		return false;
	}

	@Override
	public boolean validateBefore(
			Date startTime, Date newStartTime, Date newEndTime)
		throws PortalException {

		if (newStartTime.before(startTime)) {
			if (!newEndTime.after(startTime)) {
				return true;
			}

			throw new TSEndTimeException();
		}

		return false;
	}

	@Override
	public void validateBetween(
			java.util.Date startTime, java.util.Date endTime,
			java.util.Date newTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		if (!newTime.before(startTime) && !newTime.after(endTime)) {
			throw new TSStartEndTimeException();
		}
	}

	@Override
	public void validateEndTime(Date endTime) throws PortalException {

		if (Validator.isNull(endTime)) {
			throw new TSEndTimeException();
		}
	}

	@Override
	public void validateEndTime(Date startTime, Date endTime, Date newEndTime)
		throws PortalException {

		if (newEndTime.before(startTime)) {
			throw new TSEndTimeException();
		}
	}

	@Override
	public void validateFutureTime(Date time, Date now) throws PortalException {
		if (time.after(now)) {
			throw new TSFutureStartTimeException();
		}
	}

	@Override
	public void validateLatestEndTime(
			Date workStart, Date latestEndTimeRestriction)
		throws PortalException {

		if (workStart.after(latestEndTimeRestriction)) {
			throw new TSStartEndTimeException();
		}
	}

	@Override
	public void validateStartAndEndTime(Date startTime, Date endTime)
		throws com.liferay.portal.kernel.exception.PortalException {

		if (!startTime.before(endTime)) {
			throw new TSStartEndTimeException();
		}
	}

	@Override
	public void validateStartTime(Date startTime) throws PortalException {
		if (Validator.isNull(startTime)) {
			throw new TSStartTimeException();
		}
	}

	@Override
	public void validateStartTime(
			Date startTime, Date endTime, Date newStartTime)
		throws PortalException {

		if (!startTime.before(newStartTime) ||
			((endTime != null) && endTime.after(newStartTime))) {

			throw new TSStartTimeException();
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

	@Override
	public void validateWorkStart(Date workStart, Date earliestStartRestriction)
		throws PortalException {

		if (workStart.before(earliestStartRestriction)) {
			throw new TSEarliestStartTimeException();
		}
	}

}