package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.CurrentTaskSessionIsAlreadyEndedException;
import com.liferay.timesheet.EndTimeException;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.TaskSessionCloseException;
import com.liferay.timesheet.TaskSessionCreationException;
import com.liferay.timesheet.TaskSessionUpdateException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeCalculatorUtil;
import com.liferay.timesheet.util.TimesheetUtil;
import com.liferay.timesheet.validator.DateTimeValidatorUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class TaskSessionBaseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(TaskSessionBaseBean.class);

	private TaskSession currentTaskSession = null;

	private String description = null;

	private Date endTime = null;

	private long selectedTaskId;

	private Date startTime = null;

	public TaskSessionBaseBean() {
		init();
	}

	private void init() {
		long userId = TimesheetUtil.getCurrentUserId();

		try {
			currentTaskSession =
				TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);
		} catch (SystemException e) {
			logger.error(e);
		}
	}

	public String createTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			TaskSession taskSession = createTaskSession();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Started taskSession: " + taskSession.getTaskSessionId());
			}
		} catch (NoSelectedTaskException e) {
			logger.error("Unable to select task!");

			liferayFacesContext.addGlobalErrorMessage("Unable to select task!");
		} catch (TaskSessionCloseException e) {
			logger.error("Closing current task session is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Closing current task session is failed!");
		} catch (TaskSessionCreationException e) {
			logger.error(
				"Unable to add task session for task: " + getSelectedTaskId());

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to add task session for task: " + getSelectedTaskId());
		}

		return "/views/task/view.xhtml";
	}

	public TaskSession createTaskSession()
		throws NoSelectedTaskException, TaskSessionCloseException,
			TaskSessionCreationException {

		if (getSelectedTaskId() == 0) {
			throw new NoSelectedTaskException();
		}

		long userId = TimesheetUtil.getCurrentUserId();

		try {
			closeCurrentTaskSession(userId, getStartTime());
		} catch (SystemException se) {
			throw new TaskSessionCloseException();
		}

		TaskSession taskSession = null;
	
		ServiceContext serviceContext =
			TimesheetUtil.createServiceContext();

		try {
			taskSession = TaskSessionLocalServiceUtil.addTaskSession(
				userId, getStartTime(), getSelectedTaskId(), description,
				serviceContext);
		} catch (Exception e) {
			throw new TaskSessionCreationException();
		}

		clear();

		return taskSession;
	}

	public String finishTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			DateTimeValidatorUtil.validateEndTime(
				currentTaskSession, new Date());

			finishTaskSession();
		} catch (EndTimeException ete) {
			logger.error("Invalid endTime");

			liferayFacesContext.addGlobalErrorMessage(
				"Invalid endTime!");
		} catch (CurrentTaskSessionIsAlreadyEndedException ctsiae) {
			logger.error("current_task_session_is_already_ended");

			liferayFacesContext.addGlobalErrorMessage(
				"current_task_session_is_already_ended");
		} catch (NoCurrentTaskSessionException e) {
			logger.error("No current task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"No current task session!");
		} catch (TaskSessionUpdateException e) {
			logger.error("Unable to update task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to update task session!");
		}

		return "/views/task/view.xhtml";
	}

	public void finishTaskSession()
		throws NoCurrentTaskSessionException, TaskSessionUpdateException {

		long userId = TimesheetUtil.getCurrentUserId();

		TaskSession currentTaskSession = null;

		try {
			currentTaskSession =
				TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);
		} catch (SystemException se) {
			throw new NoCurrentTaskSessionException();
		}

		Calendar endDate = Calendar.getInstance();

		endDate.set(Calendar.MILLISECOND, 0);

		currentTaskSession.setEndTime(endDate.getTime());

		try {
			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		} catch (SystemException e) {
			throw new TaskSessionUpdateException();
		}
	}

	/**
	 *  Currently it gives back TaskSessions only for the current day.
	 *  TO DO: Passing date parameter from xhtml, so that it will be more
	 *  generic.
	 * @return
	 * @throws ParseException 
	 * @throws SystemException 
	 */
	public List<TaskSession> getTaskSessionsByD_U() {

		long userId = TimesheetUtil.getCurrentUserId();

		List<TaskSession> taskSessions = null;

		try {
			taskSessions = TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
				TimesheetUtil.getTodayWithoutTime(), userId);
		} catch (Exception e) {
			logger.error("Getting task sessions is failed!");
		}

		if (taskSessions == null) {
			taskSessions = Collections.emptyList();
		}

		return taskSessions;
	}

	public String getDayTime() {
		long userId = TimesheetUtil.getCurrentUserId();

		String dayTime = null;

		try {
			List<TaskSession> taskSessions =
				TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
					TimesheetUtil.getTodayWithoutTime(), userId);

			long time = TimeCalculatorUtil.summerizeDayTime(taskSessions);

			dayTime = TimeCalculatorUtil.getStringFromTime(time);
		} catch (Exception e) {
			logger.error("Day time calculation is failed!", e);
		}

		return dayTime;
	}

	public String getMonthTime() {
		long userId = TimesheetUtil.getCurrentUserId();

		String monthTime = null;

		try {
			long time = TimeCalculatorUtil.summerizeMonthTime(
				TimesheetUtil.getCompanyId(),
				TimesheetUtil.getTodayWithoutTime(), userId);

			monthTime = TimeCalculatorUtil.getStringFromTime(time);
		} catch (Exception e) {
			logger.error("Month time calculation is failed!", e);
		}

		return monthTime;
	}

	public String getWeekTime() {
		long userId = TimesheetUtil.getCurrentUserId();

		String weekTime = null;

		try {
			long time =
				TimeCalculatorUtil.summerizeWeekTime(
					TimesheetUtil.getCompanyId(),
					TimesheetUtil.getTodayWithoutTime(), userId);

			weekTime = TimeCalculatorUtil.getStringFromTime(time);
		} catch (Exception e) {
			logger.error("Week time calculation is failed!", e);
		}

		return weekTime;
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

	protected void clear() {
		setStartTime(null);
		setEndTime(null);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}