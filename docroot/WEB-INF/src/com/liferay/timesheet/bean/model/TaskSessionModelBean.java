package com.liferay.timesheet.bean.model;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.TSNoCurrentTaskSessionException;
import com.liferay.timesheet.TSNoSelectedTaskException;
import com.liferay.timesheet.TSTaskSessionCloseException;
import com.liferay.timesheet.TSTaskSessionUpdateException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "taskSessionModelBean")
@ViewScoped
public class TaskSessionModelBean implements Serializable {

	public void clear() {
		endTime = null;
		startTime = null;
		startTimes = new HashMap<Long, Date>();
		selectedTask = null;
		description = null;
	}

	public TaskSession closeCurrentTaskSession(long userId, Date endDate)
		throws SystemException {

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		if (currentTaskSession != null) {
			currentTaskSession.setEndTime(endDate);

			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		}

		return currentTaskSession;
	}

	public TaskSession createTaskSession(long selectedTaskId)
		throws PortalException {

		if (selectedTaskId == 0) {
			throw new TSNoSelectedTaskException();
		}

		if (getStartTime() == null) {
			Calendar startTime = Calendar.getInstance();

			startTime.set(Calendar.MILLISECOND, 0);
			startTime.set(Calendar.SECOND, 0);

			setStartTime(startTime.getTime());
		}

		long userId = TimeSheetUtil.getCurrentUserId();

		try {
			closeCurrentTaskSession(userId, getStartTime());
		} catch (SystemException se) {
			throw new TSTaskSessionCloseException();
		}

		TaskSession taskSession = null;

		ServiceContext serviceContext = TimeSheetUtil.createServiceContext();

		try {
			taskSession = TaskSessionLocalServiceUtil.addTaskSession(
				userId, getStartTime(), selectedTaskId, description,
				serviceContext);
		} catch (Exception e) {
			throw new TSTaskSessionUpdateException();
		}

		clear();

		return taskSession;
	}

	public TaskSession createTaskSession(
			Task selectedTask, Date startTime, Date endTime, String description)
		throws PortalException {

		long userId = TimeSheetUtil.getCurrentUserId();

		TaskSession taskSession = null;

		ServiceContext serviceContext = TimeSheetUtil.createServiceContext();

		try {
			taskSession = TaskSessionLocalServiceUtil.addTaskSession(
				userId, getStartTime(), getEndTime(), selectedTask.getTaskId(),
				description, serviceContext);
		} catch (Exception e) {
			throw new TSTaskSessionUpdateException();
		}

		clear();

		return taskSession;
	}

	public void deleteTaskSession(long taskSessionId) throws PortalException {
		try {
			TaskSessionLocalServiceUtil.deleteTaskSession(taskSessionId);
		} catch (Exception e) {
			throw new PortalException();
		}
	}

	public void finishTaskSession() throws PortalException {
		long userId = TimeSheetUtil.getCurrentUserId();

		TaskSession currentTaskSession = null;

		try {
			currentTaskSession =
				TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);
		} catch (SystemException se) {
			throw new TSNoCurrentTaskSessionException();
		}

		if (endTime == null) {
			Calendar endDate = Calendar.getInstance();

			endDate.set(Calendar.MILLISECOND, 0);
			endDate.set(Calendar.SECOND, 0);

			endTime = endDate.getTime();
		}

		currentTaskSession.setEndTime(endTime);

		try {
			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		} catch (SystemException e) {
			throw new TSTaskSessionUpdateException();
		}

		clear();
	}

	public String getDescription() {
		return description;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Task getSelectedTask() {
		return selectedTask;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Map<Long, Date> getStartTimes() {
		return startTimes;
	}

	@PostConstruct
	public void init() {
		startTimes = new HashMap<Long, Date>();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setSelectedTask(Task selectedTask) {
		this.selectedTask = selectedTask;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTimes(Map<Long, Date> startTimes) {
		this.startTimes = startTimes;
	}

	private static final Logger logger = LoggerFactory.getLogger(
		TaskSessionModelBean.class);
	private static final long serialVersionUID = -2671767034731473059L;

	private String description = null;
	private Date endTime = null;
	private Task selectedTask = null;
	private Date startTime = null;
	private Map<Long, Date> startTimes;

}