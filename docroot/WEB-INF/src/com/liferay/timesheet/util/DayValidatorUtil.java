package com.liferay.timesheet.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class DayValidatorUtil {

	public static Map<String, List<String>> dayIsValid(Date currentDate)
		throws Exception {

		Map<String, List<String>> dayValidationMap =
			new HashMap<String, List<String>>();

		List<String> errors = new ArrayList<String>();
		List<String> warnings = new ArrayList<String>();

		Task lunchTask = TaskLocalServiceUtil.getTaskByType(
			TimeSheetConstants.TASK_LUNCH);

		List<TaskSession> lunchSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByU_T_I(
				TimeSheetUtil.getCurrentUserId(), lunchTask.getTaskId(),
				currentDate, DateTimeUtil.getIncrementedDay(currentDate));

		if (!existLunchTask(lunchSessions)) {
			errors.add(TimeSheetConstants.ERROR_LUNCH_TASK_IS_NOT_EXIST);
		}

		if (!onlyOneLunchTimeExist(lunchSessions)) {
			errors.add(TimeSheetConstants.ERROR_MORE_THEN_ONE_LUNCH_TASK_EXIST);
		}

		if (!lunchTaskTimeCorrect(lunchSessions)) {
			errors.add(TimeSheetConstants.ERROR_LUNCH_TASK_TIME_IS_NOT_CORRECT);
		}

		List<TaskSession> workSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
				TimeSheetUtil.getCompanyId(), TimeSheetUtil.getCurrentUserId(),
				currentDate, DateTimeUtil.getIncrementedDay(currentDate));

		if (!youAreAfterBaseTimeStart(currentDate, workSessions)) {
			warnings.add(
				TimeSheetConstants.WARNING_YOU_ARE_AFTER_BASE_TIME_START);
		}

		if (errors.size() > 0) {
			dayValidationMap.put(
				TimeSheetConstants.VALIDATION_TYPE_ERROR, errors);
		}

		if (warnings.size() > 0) {
			dayValidationMap.put(
				TimeSheetConstants.VALIDATION_TYPE_WARNING, warnings);
		}

		return dayValidationMap;
	}

	protected static boolean existLunchTask(List<TaskSession> lunchSessions)
		throws SystemException {

		if (lunchSessions.size() == 0) {
			return false;
		}

		return true;
	}

	protected static boolean lunchTaskTimeCorrect(
		List<TaskSession> lunchSessions) throws Exception {

		boolean timeIsCorrect = true;

		for (TaskSession lunchSession :lunchSessions) {
			if (lunchSession.getDuration() <
					DateTimeUtil.getMillisFromMilitaryTime(
						PortletPropsValues.RESTRICTIONS_LUNCHTIME_MIN_DURATION)) {

				timeIsCorrect = false;
			}
		}

		return timeIsCorrect;
	}

	protected static boolean onlyOneLunchTimeExist(
		List<TaskSession> lunchSessions) {

		if (lunchSessions.size() > 1) {
			return false;
		}

		return true;
	}

	protected static boolean youAreAfterBaseTimeStart(
		Date currentDate, List<TaskSession> workSessions) throws Exception {

		boolean isBefore = false;

		for (TaskSession workSession :workSessions) {
			if (!workSession.getStartTime().after(
					DateTimeUtil.getDateFromMilitaryTime(
						currentDate,
						PortletPropsValues.RESTRICTIONS_BASETIME_START))) {

				isBefore = true;
			}
		}

		return isBefore;
	}

}