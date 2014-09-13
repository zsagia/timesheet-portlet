package com.liferay.timesheet.bean.task;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.TSNoCurrentTaskSessionException;
import com.liferay.timesheet.TSNoSelectedTaskException;
import com.liferay.timesheet.TSTaskSessionCloseException;
import com.liferay.timesheet.TSTaskSessionUpdateException;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.bean.model.TaskSessionModelBean;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;
import com.liferay.timesheet.util.UserUtil;

import java.io.Serializable;

import java.util.Date;

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

@ManagedBean(name = "taskManagedBean")
@RequestScoped
public class TaskManagedBean implements Serializable {

	public String createTaskAction() {
		long userId = TimeSheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = null;
		Project selectedProject = null;

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ServiceContext serviceContext = TimeSheetUtil.createServiceContext();

		try {
			selectedProjectNode = taskViewBean.getSelectedProjectNode();

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
				TimeSheetUtil.getCompanyId(), userId, selectedProject.getProjectId(),
				TimeSheetConstants.TASK_GENERAL, serviceContext);

			if (logger.isDebugEnabled()) {
				logger.debug("New Task: " + task.getTaskName());
			}

			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				task.getTaskId());

			taskViewBean.setCurrentTaskSession(taskSession);
		} catch (Exception e) {
			logger.error(e);

			liferayFacesContext.addGlobalErrorMessage("Adding task is failed!");

			return "";
		}

		clear();

		taskViewBean.init();

		return "/views/task/view.xhtml";
	}

	public String createTaskSessionAction() throws PortalException {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Date startTime =
				taskSessionModelBean.getStartTimes().get(getSelectedTaskId());

			taskSessionModelBean.setStartTime(startTime);

			TaskSession taskSession = taskSessionModelBean.createTaskSession(
				selectedTaskId);

			taskViewBean.setCurrentTaskSession(taskSession);

			clear();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Started taskSession: " + taskSession.getTaskSessionId());
			}
		} catch (TSNoSelectedTaskException e) {
			logger.error("Unable to select task!");

			liferayFacesContext.addGlobalErrorMessage("Unable to select task!");
		} catch (TSTaskSessionCloseException e) {
			logger.error("Closing current task session is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Closing current task session is failed!");
		} catch (TSTaskSessionUpdateException e) {
			logger.error(
				"Unable to add task session for task: " + selectedTaskId);

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to add task session for task: " + selectedTaskId);
		}

		return "/views/task/view.xhtml";
	}

	public String finishTaskSessionAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			taskSessionModelBean.finishTaskSession();

			taskViewBean.setCurrentTaskSession(null);
		} catch (TSNoCurrentTaskSessionException e) {
			logger.error("No current task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"No current task session!");
		} catch (TSTaskSessionUpdateException e) {
			logger.error("Unable to update task session!");

			liferayFacesContext.addGlobalErrorMessage(
				"Unable to update task session!");
		} catch (Exception e) {
			logger.equals(e);
		}

		return "/views/task/view.xhtml";
	}

	public EditTaskViewBean getEditTaskViewBean() {
		return editTaskViewBean;
	}

	public long getSelectedTaskId() {
		return selectedTaskId;
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public TaskSessionModelBean getTaskSessionModelBean() {
		return taskSessionModelBean;
	}

	public TaskViewBean getTaskViewBean() {
		return taskViewBean;
	}

	public boolean isLeader() throws PortalException, SystemException {
		return UserUtil.isLeader(TimeSheetUtil.getCompanyId(),
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);
	}

	public boolean isProjectTree() {
		return taskViewBean.getRoot() != null;
	}

	public void setEditTaskViewBean(EditTaskViewBean editTaskViewBean) {
		this.editTaskViewBean = editTaskViewBean;
	}

	public void setSelectedTaskId(long selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	public void setTaskSessionModelBean(
		TaskSessionModelBean taskSessionModelBean) {

		this.taskSessionModelBean = taskSessionModelBean;
	}

	public void setTaskViewBean(TaskViewBean taskViewBean) {
		this.taskViewBean = taskViewBean;
	}

	public String updateTaskAction() {
		Task editedTask = editTaskViewBean.getEditedTask();
		ProjectTreeNode selectedProjectTreeNode =
			editTaskViewBean.getSelectedProjectNode();

		String taskName = editTaskViewBean.getTaskName();

		Project selectedProject = null;

		if (selectedProjectTreeNode != null) {
			selectedProject = selectedProjectTreeNode.getProject();
		}

		try {
			taskModelBean.updateTask(
				editedTask, taskName, null, selectedProject);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/views/task/view.xhtml";
	}

	protected void clear() {
		taskModelBean.setTaskName(null);
	}

	private static final long serialVersionUID = -8412810082872360906L;

	private static Logger logger = LoggerFactory.getLogger(
		TaskManagedBean.class);

	@ManagedProperty(name = "editTaskViewBean",
		value = "#{editTaskViewBean}")
	private EditTaskViewBean editTaskViewBean;

	@ManagedProperty(value ="#{param.selectedTaskId}")
	private long selectedTaskId = 0;

	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;

	@ManagedProperty(name = "taskSessionModelBean",
		value = "#{taskSessionModelBean}")
	private TaskSessionModelBean taskSessionModelBean;

	@ManagedProperty(name = "taskViewBean",
		value = "#{taskViewBean}")
	private TaskViewBean taskViewBean;

}