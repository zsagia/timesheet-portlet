package com.liferay.timesheet.bean.model;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.NoCurrentTaskSessionException;
import com.liferay.timesheet.NoSelectedTaskException;
import com.liferay.timesheet.TaskSessionCloseException;
import com.liferay.timesheet.TaskSessionCreationException;
import com.liferay.timesheet.TaskSessionUpdateException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

	@PostConstruct
	public void init() {
		startTimes = new HashMap<Long, Date>();
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
		throws NoSelectedTaskException, TaskSessionCloseException,
			TaskSessionCreationException {

		if (selectedTaskId == 0) {
			throw new NoSelectedTaskException();
		}

		if (getStartTime() == null) {
			setStartTime(new Date());
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
				userId, getStartTime(), selectedTaskId, description,
				serviceContext);
		} catch (Exception e) {
			throw new TaskSessionCreationException();
		}

		clear();

		return taskSession;
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

		if (endTime == null) {
			Calendar endDate = Calendar.getInstance();

			endDate.set(Calendar.MILLISECOND, 0);

			endTime = endDate.getTime();
		}

		currentTaskSession.setEndTime(endTime);

		try {
			TaskSessionLocalServiceUtil.updateTaskSession(currentTaskSession);
		} catch (SystemException e) {
			throw new TaskSessionUpdateException();
		}

		clear();
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

	public String getDescription() {
		return description;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void clear() {
		endTime = null;
		startTime = null;
		startTimes = new HashMap<Long, Date>();
	}

	public Map<Long, Date> getStartTimes() {
		return startTimes;
	}

	public void setStartTimes(Map<Long, Date> startTimes) {
		this.startTimes = startTimes;
	}

	private String description = null;
	private Date endTime = null;
	private Date startTime = null;
	private Map<Long, Date> startTimes;

	private static final long serialVersionUID = -2671767034731473059L;
	private static final Logger logger =
		LoggerFactory.getLogger(TaskSessionModelBean.class);

}