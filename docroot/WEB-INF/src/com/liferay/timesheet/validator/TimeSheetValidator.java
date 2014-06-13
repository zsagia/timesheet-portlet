package com.liferay.timesheet.validator;

public interface TimeSheetValidator {

	public boolean validateAfter(
			java.util.Date endTime, java.util.Date newStartTime,
			java.util.Date newEndTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean validateBefore(
			java.util.Date startTime, java.util.Date newStartTime,
			java.util.Date newEndTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateBetween(
			java.util.Date startTime, java.util.Date endTime,
			java.util.Date newTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateEndTime(java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateEndTime(
			java.util.Date startTime, java.util.Date endTime,
			java.util.Date newEndTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateFutureTime(
			java.util.Date time, java.util.Date now)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateLatestEndTime(
			java.util.Date workStart, java.util.Date latestEndTimeRestriction)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateStartAndEndTime(
			java.util.Date startTime, java.util.Date endTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateStartTime(java.util.Date startTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateStartTime(
			java.util.Date startTime, java.util.Date endTime,
			java.util.Date newStartTime)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateWorkStart(
			java.util.Date workStart, java.util.Date earliestStartRestriction)
		throws com.liferay.portal.kernel.exception.PortalException;

	public void validateWorkDuration(long allWorkTimeToday)
		throws com.liferay.portal.kernel.exception.PortalException;
}