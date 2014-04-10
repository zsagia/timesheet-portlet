package com.liferay.timesheet.validator;

public interface TimeSheetValidator {
	public void validateStartTime(
			com.liferay.timesheet.model.TaskSession taskSession,
			java.util.Date startTime)
		throws java.text.ParseException,
			com.liferay.portal.kernel.exception.PortalException;

	public void validateEndTime(
			com.liferay.timesheet.model.TaskSession taskSession,
			java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateFutureStartTime(
			java.util.Date startTime, java.util.Date now)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateLatestEndTime(java.util.Date workStart)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateWorkStart(java.util.Date workStart)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateWorkDuration(long allWorkTimeToday)
		throws com.liferay.portal.kernel.exception.PortalException;
}