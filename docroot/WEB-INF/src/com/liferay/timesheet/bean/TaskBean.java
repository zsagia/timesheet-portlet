package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.TreeNode;

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

	@ManagedProperty(name = "projectBean",
		value = "#{projectBean}")
	private ProjectBean projectBean;

	public String createTask()throws Exception {

		long userId = TimesheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = projectBean.getSelectedProjectNode();

		Project selectedProject =
			((ProjectTreeNode)selectedProjectNode).getProject();

		Task task = TaskLocalServiceUtil.addTask(
			taskName, userId, selectedProject.getProjectId());

		taskSessionSimpleBean.setSelectedTaskId(task.getTaskId()); 
		taskSessionSimpleBean.createTaskSession();

		clear();

		return "/views/view.xhtml";
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

	protected void clear() {
		setTaskName(null);
	}

	public ProjectBean getProjectBean() {
		return projectBean;
	}

	public void setProjectBean(ProjectBean projectBean) {
		this.projectBean = projectBean;
	}

}