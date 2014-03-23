package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;

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

	public String createTaskAction() {

		long userId = TimesheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = null;
		Project selectedProject = null;

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ServiceContext serviceContext =
			TimesheetUtil.createServiceContext();

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
			task = taskModelBean.createTask(
				userId, selectedProject.getProjectId(), serviceContext);

			if (logger.isDebugEnabled()) {
				logger.debug("New Task: " + task.getTaskName());
			}

			taskSessionSimpleBean.setSelectedTaskId(task.getTaskId()); 
			taskSessionModelBean.createTaskSession(task.getTaskId());
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage("Adding task is failed!");

			return "";
		}

		clear();

		return "/views/task/view.xhtml";
	}

	protected void clear() {
		taskModelBean.setTaskName(null);
	}

	public DepartmentBean getDepartmentBean() {
		return departmentBean;
	}

	public ProjectBean getEnabledProjectBean() {
		return enabledProjectBean;
	}

	public TaskSessionSimpleBean getTaskSessionSimpleBean() {
		return taskSessionSimpleBean;
	}

	public void setDepartmentBean(DepartmentBean departmentBean) {
		this.departmentBean = departmentBean;
	}

	public void setEnabledProjectBean(ProjectBean enabledProjectBean) {
		this.enabledProjectBean = enabledProjectBean;
	}

	public void setTaskSessionSimpleBean(
		TaskSessionSimpleBean taskSessionSimpleBean) {

		this.taskSessionSimpleBean = taskSessionSimpleBean;
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public void setTaskSessionModelBean(
		TaskSessionModelBean taskSessionModelBean) {

		this.taskSessionModelBean = taskSessionModelBean;
	}

	@ManagedProperty(name = "enabledProjectBean",
		value = "#{enabledProjectBean}")
	private ProjectBean enabledProjectBean;

	@ManagedProperty(name = "departmentBean",
		value = "#{departmentBean}")
	private DepartmentBean departmentBean;

	@ManagedProperty(name = "taskSessionSimpleBean",
		value = "#{taskSessionSimpleBean}")
	private TaskSessionSimpleBean taskSessionSimpleBean;

	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;

	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	private static final long serialVersionUID = -8412810082872360906L;

	private static Logger logger = LoggerFactory.getLogger(TaskBean.class);

}