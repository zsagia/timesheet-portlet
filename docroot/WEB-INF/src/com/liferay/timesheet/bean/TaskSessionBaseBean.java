package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class TaskSessionBaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private TaskSession currentTaskSession;

	private Date endTime;

	private long selectedTaskId;

	private Date startTime;

	public TaskSessionBaseBean() {
		init();
	}

	private void init() {
		long userId = TimesheetUtil.getCurrentUserId();

		try {
			currentTaskSession =
				TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public String createTaskSession() throws Exception {
		if (getSelectedTaskId() == 0) {
			throw new NoSelectedTaskException();
		}

		Date startDate = new Date();

		Date startTime = getStartTime();

		if (startTime != null) {
			startDate = startTime;
		}

		long userId = TimesheetUtil.getCurrentUserId();

		closeCurrentTaskSession(userId, startDate);

		TaskSessionLocalServiceUtil.addTaskSession(
			startDate, getSelectedTaskId(), userId);

		return "/views/view.xhtml";
	}

	public String finishTaskSession() throws ParseException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		Date endDate = new Date();

		if (endTime != null) {
			endDate = endTime;
		}

		currentTaskSession.setEndTime(endDate);

		TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);

		return "/views/view.xhtml";
	}

	/**
	 *  Currently it gives back TaskSessions only for the current day.
	 *  TO DO: Passing date parameter from xhtml, so that it will be more
	 *  generic.
	 * @return
	 * @throws ParseException 
	 * @throws SystemException 
	 */
	public List<TaskSession> getTaskSessionsByD_U()
		throws ParseException, SystemException {

		long userId = TimesheetUtil.getCurrentUserId();

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
				TimesheetUtil.getTodayWithoutTime(), userId);

		if (taskSessions == null) {
			taskSessions = Collections.emptyList();
		}

		return taskSessions;
	}

	public Date getTodayWithoutTime() throws Exception {
		return TimesheetUtil.getTodayWithoutTime();
	}

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public Date getEndTime() {
		return endTime;
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	protected TaskSession closeCurrentTaskSession(long userId, Date endDate)
		throws SystemException {

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		if (currentTaskSession != null) {
			currentTaskSession.setEndTime(endDate);

			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		}

		return currentTaskSession;
	}

}