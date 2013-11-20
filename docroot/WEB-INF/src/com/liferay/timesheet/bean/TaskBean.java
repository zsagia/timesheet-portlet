package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

/**
* @author Adorjan Nagy
* @author Tibor Jandi
* @author Istvan Sajtos
* @author Zsolt Szabo
*/
public class TaskBean {

	private Date endTime;
	private Date startTime;
	private String taskName;

	public String createTaskSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		Task task = null;

		task = TaskLocalServiceUtil.getTaskByTN_U(taskName, userId);

		try {
			if (Validator.isNull(task)) {
				task = TaskLocalServiceUtil.addTask(taskName, userId);
			}

			long taskId = task.getTaskId();

			TaskSessionLocalServiceUtil.addTaskSession(
				getStartTime(), getEndTime(), taskId);
		}
		catch (SystemException se) {
			se.printStackTrace();
		}

		return "success";
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 *  Currently it gives back TaskSessions only for the current day.
	 *  TO DO: Passing date parameter from xhtml, so that it will be more generic.
	 * @return
	 */
	public List<TaskSession> getTaskSessionsByD_U() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		List<TaskSession> taskSessions = null;

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
				new Date(), userId);

		return taskSessions;
	}

	public List<Task> getTaskByUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		List<Task> taskToday = null;

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		try {
			taskToday = TaskLocalServiceUtil.getTasksByUserId(userId);
		}
		catch (SystemException se) {
			se.printStackTrace();
		}

		return taskToday;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}