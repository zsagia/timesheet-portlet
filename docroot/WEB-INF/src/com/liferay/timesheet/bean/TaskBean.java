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
import javax.faces.bean.ManagedProperty;
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

	private String taskName;

	@ManagedProperty(name = "taskSessionBean", value = "#{taskSessionBean}")
	private TaskSessionBean taskSessionBean;

	public String createTask()
		throws PortalException, SystemException, ParseException {

		long userId = TimesheetUtil.getCurrentUserId();

		Task task = TaskLocalServiceUtil.addTask(taskName, userId);

		taskSessionBean.setSelectedTask(task);
		taskSessionBean.createTaskSession();

		return "success";
	}

	public List<Task> getTaskByUser() throws PortalException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		List<Task> tasksToday = TaskLocalServiceUtil.getTasksByUserId(userId);

		return tasksToday;
	}

	public String getTaskName() {
		return taskName;
	}

	public TaskSessionBean getTaskSessionBean() {
		return taskSessionBean;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskSessionBean(TaskSessionBean taskSessionBean) {
		this.taskSessionBean = taskSessionBean;
	}

}