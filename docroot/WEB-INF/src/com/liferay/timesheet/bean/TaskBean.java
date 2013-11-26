package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
* @author Adorjan Nagy
* @author Tibor Jandi
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@ManagedBean(name = "taskBean")
@RequestScoped
public class TaskBean implements Serializable{

	private static final long serialVersionUID = -8412810082872360906L;

	private TaskSession currentTaskSession;
	private Date endTime;
	private Date startTime;
	private String taskName;

	public String createTaskSession()
		throws ParseException, PortalException, SystemException {

		long userId = TimesheetUtil.getCurrentUserId();

		Task task = TaskLocalServiceUtil.addTask(taskName, userId);

		long taskId = task.getTaskId();

		Date startDate = new Date();
		Date todayWithoutTime = null;

		if (startTime != null) {
			todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

			startDate =
				TimesheetUtil.addDateToDate(todayWithoutTime, getStartTime());
		}

		closeCurrentTaskSession(userId, startDate);

		TaskSessionLocalServiceUtil.addTaskSession(startDate, taskId, userId);

		return "success";
	}

	public String finishTaskSession() throws ParseException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		TaskSession currentTaskSession =
			TaskSessionLocalServiceUtil.getCurrentTaskSession(userId);

		Date endDate = new Date();
		Date todayWithoutTime = null;

		if (endTime != null) {
			todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

			endDate =
				TimesheetUtil.addDateToDate(todayWithoutTime, getEndTime());
		}

		currentTaskSession.setEndTime(endDate);

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

	public List<Task> getTaskByUser() throws PortalException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		List<Task> tasksToday = TaskLocalServiceUtil.getTasksByUserId(userId);

		return tasksToday;
	}

	public String getTaskName() {
		return taskName;
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

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

}