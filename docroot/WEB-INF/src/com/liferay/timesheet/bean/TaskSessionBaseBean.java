package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
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
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedProperty;

public abstract class TaskSessionBaseBean implements Serializable{

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
			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				getSelectedTaskId());

			clear();

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

	public String finishTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			DateTimeValidatorUtil.validateEndTime(
				currentTaskSession, new Date());

			taskSessionModelBean.finishTaskSession();
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

	public void clear() {};

	public Date getTodayWithoutTime() throws Exception {
		return TimesheetUtil.getTodayWithoutTime();
	}

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public Date getStartTime() {
		return null;
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public void setTaskSessionModelBean(TaskSessionModelBean taskSessionModelBean) {
		this.taskSessionModelBean = taskSessionModelBean;
	}

	private TaskSession currentTaskSession = null;

	private long selectedTaskId;

	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(TaskSessionBaseBean.class);

}