package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

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

	@ManagedProperty(name = "taskSessionSimpleBean",
		value = "#{taskSessionSimpleBean}")
	private TaskSessionSimpleBean taskSessionSimpleBean;

	public String createTask()throws Exception {

		long userId = TimesheetUtil.getCurrentUserId();

		Task task = TaskLocalServiceUtil.addTask(taskName, userId);

		taskSessionSimpleBean.setSelectedTaskId(task.getTaskId()); 
		taskSessionSimpleBean.createTaskSession();

		return "success";
	}

	public List<Task> getTasksByUser() throws PortalException, SystemException {
		long userId = TimesheetUtil.getCurrentUserId();

		List<Task> tasksToday =
			TaskLocalServiceUtil.getTasksByUserId(userId);

		return tasksToday;
	}

	public String getTaskName() {
		return taskName;
	}

	public TaskSessionSimpleBean getTaskSessionSimpleBean() {
		return taskSessionSimpleBean;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskSessionSimpleBean(
		TaskSessionSimpleBean taskSessionSimpleBean) {

		this.taskSessionSimpleBean = taskSessionSimpleBean;
	}

}