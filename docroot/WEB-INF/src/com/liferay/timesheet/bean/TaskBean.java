package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.service.persistence.TaskSessionUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
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

	private Date endTime;
	private Date startTime;
	private String taskName;
	private Task actualTask;

	public String createTaskSession()
		throws ParseException, PortalException, SystemException {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		Task task = TaskLocalServiceUtil.addTask(taskName, userId);

		long taskId = task.getTaskId();

		Date todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

		TaskSessionLocalServiceUtil.addTaskSession(
			TimesheetUtil.addDateToDate(todayWithoutTime, getStartTime()),
			taskId, userId);

		return "success";
	}

	public Task getActualTask() {
		return actualTask;
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
	 * @throws ParseException 
	 * @throws SystemException 
	 */
	public List<TaskSession> getTaskSessionsByD_U()
		throws ParseException, SystemException {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		List<TaskSession> taskSessions = null;

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		taskSessions = TaskSessionLocalServiceUtil.getTaskSessionsByD_U(
			TimesheetUtil.getTodayWithoutTime(), userId);

		if (taskSessions == null) {
			taskSessions = Collections.emptyList();
		}

		return taskSessions;
	}

	public List<Task> getTaskByUser() throws PortalException, SystemException {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		long userId = Long.valueOf(
			facesContext.getExternalContext().getRemoteUser());

		List<Task> tasksToday = TaskLocalServiceUtil.getTasksByUserId(userId);

		return tasksToday;
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