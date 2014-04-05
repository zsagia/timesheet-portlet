package com.liferay.timesheet.bean;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.util.TaskConstants;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "taskModelBean")
@ViewScoped
public class TaskModelBean implements Serializable {

	public Task createTask(
			long userId, long projectId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return TaskLocalServiceUtil.addTask(
			userId, taskName, projectId, description,
			TaskConstants.GENERAL_TASK, serviceContext);
	}

	public List<Task> getTasksByUser() {
		long userId = TimesheetUtil.getCurrentUserId();

		List<Task> tasksToday = null;

		try {
			tasksToday = TaskLocalServiceUtil.getTasksByUserId(userId);

			Task lunchTask = TaskLocalServiceUtil.getTaskByType(
				TaskConstants.LUNCH_TASK);

			tasksToday.add(0, lunchTask);
		} catch (Exception e) {
			logger.error(
				"Getting tasks for userId: " + userId + " is failed", e);
		}

		return tasksToday;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	private String description;

	private String taskName;

	private static final long serialVersionUID = 4961368400508391281L;
	
	private static Logger logger = LoggerFactory.getLogger(TaskModelBean.class);

}