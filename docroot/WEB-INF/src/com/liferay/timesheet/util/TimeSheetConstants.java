package com.liferay.timesheet.util;

/**
 * @author Istvan Sajtos
 */
public class TimeSheetConstants {

	public static int DATE_DAY = 0;

	public static int DATE_WEEK = 1;

	public static int DATE_MONTH = 2;

	public static final int TASK_GENERAL = 0;

	public static final int TASK_LUNCH = 1;

	public static final int TASK_BREAK = 2;

	public static final int TASK_MANDATORY_BREAK = 3;
	
	public static final int TASK_ASSIGNED = 4;

	public static final int USER_LIST_ALL = 0;

	public static final int USER_LIST_GROUPPED = 1;

	public static String DATE_FORMAT_WITHOUT_TIME = "yyyy/MM/dd";

	public static String TIME_FORMAT_WITHOUT_DATE = "HH:mm";

	public static String VIEW_DAY_REMAKE = "/views/task/day-remake-view.xhtml";

	public static String VIEW_TASK = "/views/task/view.xhtml";

	public static String ERROR_LUNCH_TASK_IS_NOT_EXIST =
		"lunch-task-is-not-exist";

	public static String ERROR_MORE_THEN_ONE_LUNCH_TASK_EXIST =
		"more-then-one-lunch-task-exist";

	public static String ERROR_LUNCH_TASK_TIME_IS_NOT_CORRECT =
		"lunch-task-time-is-not-correct";
	
	public static String WARNING_YOU_ARE_AFTER_BASE_TIME_START =
		"you-are-after-base-time-start";

	public static String VALIDATION_TYPE_ERROR = "TYPE_ERROR";

	public static String VALIDATION_TYPE_WARNING = "TYPE_WARNING";

}