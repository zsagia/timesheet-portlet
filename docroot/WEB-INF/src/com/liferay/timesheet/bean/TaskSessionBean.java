package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;

import java.text.ParseException;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
@ManagedBean
@RequestScoped
public class TaskSessionBean implements Serializable {

	private static final long serialVersionUID = -7318596896784233150L;

	private TaskSession currentTaskSession;
	private Date endTime;
	private Date startTime;
	private Task selectedTask;

	public String createTaskSession()
		throws ParseException, PortalException, SystemException {

		if (selectedTask == null) {
			throw new NoSelectedTaskException();
		}

		long taskId = selectedTask.getTaskId();

		long userId = TimesheetUtil.getCurrentUserId();

		closeCurrentTaskSession(userId, getStartTime());

		TaskSessionLocalServiceUtil.addTaskSession(
			getStartTime(), taskId, userId);

		return "success";
	}

	private TaskSession closeCurrentTaskSession(long userId, Date endDate)
		throws SystemException {

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		if (currentTaskSession != null) {
			currentTaskSession.setEndTime(endDate);

			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		}

		return currentTaskSession;
	}

	public String finishTaskSession() throws ParseException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);

		return "success";
	}

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Task getSelectedTask() {
		return selectedTask;
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

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

}