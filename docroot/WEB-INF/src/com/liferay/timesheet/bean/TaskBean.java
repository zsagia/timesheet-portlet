package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
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

	private static Logger logger = LoggerFactory.getLogger(TaskBean.class);

	private String taskName;

	@ManagedProperty(name = "taskSessionSimpleBean",
		value = "#{taskSessionSimpleBean}")
	private TaskSessionSimpleBean taskSessionSimpleBean;

	@ManagedProperty(name = "enabledProjectBean",
		value = "#{enabledProjectBean}")
	private ProjectBean enabledProjectBean;

	@ManagedProperty(name = "departmentBean",
		value = "#{departmentBean}")
	private DepartmentBean departmentBean;

	public String createTaskAction() {

		long userId = TimesheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = null;
		Project selectedProject = null;

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			selectedProjectNode = enabledProjectBean.getSelectedProjectNode();

			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage(
				"Project node is not selected!");

			return "";
		}

		Task task = null;

		try {
			task = TaskLocalServiceUtil.addTask(
				taskName, userId, selectedProject.getProjectId());

			if (logger.isDebugEnabled()) {
				logger.debug("New Task: " + task.getTaskName());
			}

			taskSessionSimpleBean.setSelectedTaskId(task.getTaskId()); 
			taskSessionSimpleBean.createTaskSession();
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage("Adding task is failed!");

			return "";
		}

		clear();

		return "/views/task/view.xhtml";
	}

	public List<Task> getTasksByUser() {
		long userId = TimesheetUtil.getCurrentUserId();

		List<Task> tasksToday = null;

		try {
			tasksToday = TaskLocalServiceUtil.getTasksByUserId(userId);
		} catch (Exception e) {
			logger.error("Getting tasks for userId: " + userId + " is failed");
		}

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

	public ProjectBean getEnabledProjectBean() {
		return enabledProjectBean;
	}

	public void setEnabledProjectBean(ProjectBean enabledProjectBean) {
		this.enabledProjectBean = enabledProjectBean;
	}

	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}

	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}

}