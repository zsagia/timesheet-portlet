package com.liferay.timesheet.util;

import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DateTimeCalculatorUtil {

	public static String getStringFromTime(long time) {
		StringBuffer buffer = new StringBuffer();

		long hours = time / (60 * 60 * 1000);
		long minutes = (time % (60 * 60 * 1000)) / (60 * 1000);

		if (hours < 10) {
			buffer.append("0");
		}

		buffer.append(hours);
		buffer.append(":");

		if (minutes < 10) {
			buffer.append("0");
		}

		buffer.append(minutes);

		return buffer.toString();
	}

	public static long summerizeMonthTime(
			long companyId, Date dateOfMonth, long userId)
		throws Exception {

		long time = 0;

		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();

		calendarStart.setTime(dateOfMonth);
		calendarEnd.setTime(dateOfMonth);

		int first = calendarStart.getMinimum(Calendar.DAY_OF_MONTH);
		int last = calendarStart.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendarStart.set(Calendar.DAY_OF_MONTH, first);
		calendarEnd.set(Calendar.DAY_OF_MONTH, 1);
		calendarEnd.add(Calendar.DAY_OF_MONTH, 1);

		for (int i = first; i < last; i++) {
			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
					companyId, userId, calendarStart.getTime(),
					calendarEnd.getTime());

			time += summerizeTime(taskSessions);

			calendarStart.add(Calendar.DAY_OF_MONTH, 1);
			calendarEnd.add(Calendar.DAY_OF_MONTH, 1);
		}

		return time;
	}

	public static long summerizeTime(List<TaskSession> taskSessions)
		throws Exception {

		return summerizeTime(taskSessions, new Date());
	}

	public static long summerizeTime(
			List<TaskSession> taskSessions, boolean breaks)
		throws Exception {

		return summerizeTime(taskSessions, new Date(), breaks);
	}

	public static long summerizeTime(
			List<TaskSession> taskSessions, Date endTime)
		throws Exception {

		return summerizeTime(taskSessions, endTime, true);
	}

	public static long summerizeTime(
			List<TaskSession> taskSessions, Date endTime, boolean breaks)
		throws Exception {

		long time = 0;

		for (TaskSession taskSession : taskSessions) {
			Task task = taskSession.getTask();

			int type = task.getTaskType();

			if (!breaks && ((type != TimeSheetConstants.TASK_GENERAL) &&
				 (type != TimeSheetConstants.TASK_MANDATORY_BREAK))) {

				continue;
			}

			time += taskSession.getDuration(endTime);
		}

		return time;
	}

	public static long summerizeWeekTime(
			long companyId, Date dateOfWeek, long userId)
		throws Exception {

		long time = 0;

		List<Date> daysOfWeek = getDaysOfWeek(dateOfWeek);

		for (Date date : daysOfWeek) {
			Calendar date2 = Calendar.getInstance();

			date2.setTime(date);
			date2.add(Calendar.DAY_OF_WEEK, 1);

			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
					companyId, userId, date, date2.getTime());

			time += summerizeTime(taskSessions);
		}

		return time;
	}

	private static List<Date> getDaysOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();

		int firstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);

		List<Date> daysOfWeek = new ArrayList<Date>();

		for (int i = 0; i < 7; i++) {
			daysOfWeek.add(calendar.getTime());

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		return daysOfWeek;
	}

}