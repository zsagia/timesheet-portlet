package com.liferay.timesheet.bean.view;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.DateTimeCalculatorUtil;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.primefaces.model.TreeNode;

public abstract class AbstractStatisticsBean implements Serializable{

	public AbstractStatisticsBean() {
		init();
	}

	public abstract void init();

	public String getFormattedDuration(boolean breaks) throws Exception {
		return DateTimeCalculatorUtil.getStringFromTime(
			DateTimeCalculatorUtil.summerizeTime(taskSessions, breaks));
	}

	public String getFormattedDurationForTask(Task task) throws Exception {
		Date[] interval = calculateInterval(currentDate, dateNumber);

		return task.getFormattedDuration(
			currentUser.getUserId(), interval[0], interval[1]);
	}

	public List<Task> getTasks() {
		List<Task> tasks = null;

		try {
			Date[] interval = calculateInterval(currentDate, dateNumber);

			tasks = TaskLocalServiceUtil.getTasksByC_U_I(
				TimeSheetUtil.getCompanyId(), currentUser.getUserId(),
				interval[0], interval[1]);
		} catch (Exception e) {
			logger.error(
				"Getting tasks for userId: " + currentUser.getUserId() +
					" is failed", e);
		}

		return tasks;
	}

	/**
	 *  Currently it gives back TaskSessions only for the current day.
	 *  TO DO: Passing date parameter from xhtml, so that it will be more
	 *  generic.
	 * @return
	 * @throws ParseException 
	 * @throws SystemException 
	 */
	public List<TaskSession> getTaskSessions() {
		try {
			Date[] interval = calculateInterval(currentDate, dateNumber);

			taskSessions = TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
				TimeSheetUtil.getCompanyId(), currentUser.getUserId(),
				interval[0], interval[1]);
		} catch (Exception e) {
			logger.error("Getting task sessions is failed!");
		}

		if (taskSessions == null) {
			taskSessions = Collections.emptyList();
		}

		return taskSessions;
	}

	private Date[] calculateInterval(Date currentDate, int dateNumber) {
		Date[] interval = new Date[2];

		if (dateNumber == TimeSheetConstants.DATE_DAY) {
			interval[0] = currentDate;
			interval[1] = DateTimeUtil.getIncrementedDay(currentDate);
		}
		else if (dateNumber == TimeSheetConstants.DATE_WEEK) {
			interval = getIntervalOfWeek(currentDate);
		}
		else if (dateNumber == TimeSheetConstants.DATE_MONTH) {
			interval = getIntervalOfMonth(currentDate);
		}

		return interval;
	}

	private Date[] getIntervalOfMonth(Date dateOfMonth) {
		Calendar calendarStart = Calendar.getInstance();
		Calendar calendarEnd = Calendar.getInstance();

		calendarStart.setTime(dateOfMonth);
		calendarEnd.setTime(dateOfMonth);

		int first = calendarStart.getMinimum(Calendar.DAY_OF_MONTH);
		int last = calendarStart.getActualMaximum(Calendar.DAY_OF_MONTH);

		calendarStart.set(Calendar.DAY_OF_MONTH, first);
		calendarEnd.set(Calendar.DAY_OF_MONTH, last);

		Date[] interval = new Date[2];

		interval[0] = calendarStart.getTime();
		interval[1] = calendarEnd.getTime();

		return interval;
	}


	private Date[] getIntervalOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();

		int firstDayOfWeek = Calendar.getInstance().getFirstDayOfWeek();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);

		List<Date> daysOfWeek = new ArrayList<Date>();

		for (int i = 0; i < 7; i++) {
			daysOfWeek.add(calendar.getTime());

			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		Date[] interval = new Date[2];

		interval[0] = daysOfWeek.get(0);
		interval[1] = daysOfWeek.get(6);

		return interval;
	}

	public void selectUserAction(User user) {
		currentUser = user;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public boolean isUserStatistics() {
		return userStatistics;
	}

	public void setUserStatistics(boolean userStatistics) {
		this.userStatistics = userStatistics;
	}

	public int getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(int dateNumber) {
		this.dateNumber = dateNumber;
	}

	public int getUserList() {
		return userList;
	}

	public void setUserList(int userList) {
		this.userList = userList;
	}

	public void setTaskSessions(List<TaskSession> taskSessions) {
		this.taskSessions = taskSessions;
	}

	private Date currentDate = null;
	private int currentLevel;
	private List<TaskSession> taskSessions = null;
	private User currentUser = null;
	private int dateNumber;
	private Date endDate = null;
	private TreeNode root = null;
	private Date startDate = null;
	private int userList;
	private boolean userStatistics = false;

	private static final long serialVersionUID = 4703017026672133740L;
	private static final Logger logger =
		LoggerFactory.getLogger(AbstractStatisticsBean.class);
}
