package com.liferay.timesheet.validator;

import java.util.Date;
import java.util.List;

import com.liferay.timesheet.TSEndTimeException;
import com.liferay.timesheet.TSStartTimeException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.PortletPropsValues;

public class TaskSessionValidatorUtil {

	public static void validateAddTaskSession(
			List<TaskSession> taskSessions, Date newStartTime, Date newEndTime,
			Date currentDate)
		throws Exception {

		baseValidation(newStartTime, newEndTime, currentDate);

		int size = taskSessions.size();

		for (int i = 0; i < size; i++) {
			TaskSession taskSession = taskSessions.get(i);

			Date startTime = taskSession.getStartTime();
			Date endTime = taskSession.getEndTime();

			if (!newStartTime.before(startTime) &&
				newStartTime.before(endTime)) {

				throw new TSStartTimeException();
			}

			if (newEndTime.after(startTime) && newEndTime.before(endTime)) {
				throw new TSEndTimeException();
			}
		}
	}

	public static void validateUpdateTaskSession(
			List<TaskSession> taskSessions, TaskSession selectedTaskSession,
			Date startTime, Date endTime, Date currentDate)
		throws Exception {

		baseValidation(startTime, endTime, currentDate);

		int size = taskSessions.size();

		for (int i = 0; i < size; i++) {
			TaskSession taskSession = taskSessions.get(i);

			if (taskSession.getTaskSessionId() ==
					selectedTaskSession.getTaskSessionId()) {

				TaskSession previousTaskSession = null;
				TaskSession followingTaskSession = null;
				int h = i - 1;
				int j = i + 1;

				if (h >= 0) {
					previousTaskSession = taskSessions.get(h);
				}

				if (j < size) {
					followingTaskSession = taskSessions.get(j);
				}

				if (previousTaskSession != null) {
					TimeSheetValidatorUtil.validateStartTime(
						previousTaskSession.getStartTime(),
						previousTaskSession.getEndTime(), startTime);
				}

				if (followingTaskSession != null) {
					if (endTime.after(followingTaskSession.getStartTime())) {
						throw new TSEndTimeException();
					}
				}	
			}
		}
	}

	private static void baseValidation(
			Date startTime, Date endTime, Date currentDate)
		throws Exception {

		TimeSheetValidatorUtil.validateStartTime(startTime);
		TimeSheetValidatorUtil.validateEndTime(endTime);

		Date earliestStartRestriction =
			DateTimeUtil.getDateFromMilitaryTime(
				currentDate,
				PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);

		TimeSheetValidatorUtil.validateWorkStart(
			startTime, earliestStartRestriction);

		Date latestEndTimeRestriction =
			DateTimeUtil.getDateFromMilitaryTime(
				currentDate,
				PortletPropsValues.RESTRICTIONS_ENDTIME_LATEST);

		TimeSheetValidatorUtil.validateLatestEndTime(
			endTime, latestEndTimeRestriction);

		Date now = new Date();

		TimeSheetValidatorUtil.validateFutureTime(startTime, now);
		TimeSheetValidatorUtil.validateFutureTime(endTime, now);

		TimeSheetValidatorUtil.validateStartAndEndTime(startTime, endTime);
	}

}