package com.liferay.timesheet.bean.admin;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.bean.model.DayModelBean;
import com.liferay.timesheet.bean.model.ProjectModelBean;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.TreeNode;

/**
 * @author Zsolt Szabo
 */

@ManagedBean
@RequestScoped
public class AdminManagedBean implements Serializable {

	public String createDayAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		Day day = null;

		try {
			day = dayModelBean.createDay();

			if (logger.isDebugEnabled()) {
				logger.debug("New day object is created: " + day.getType());
			}
		} catch (Exception e) {
			logger.error("Creation new day is failed!", e);

			liferayFacesContext.addGlobalErrorMessage(
				"Day exception", "Creation new day is failed!");
		}

		liferayFacesContext.addGlobalInfoMessage(
			"Day operation", "Day creation was succesfull!");

		return ADMIN_VIEW;
	}

	public String createProjectAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Project project = projectModelBean.createProject(
				projectViewBean.getSelectedProject());

			projectViewBean.init();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New project is created: " + project.getProjectName());
			}
		} catch (Exception e) {
			logger.error("Creation new project is failed!", e);

			liferayFacesContext.addGlobalErrorMessage(
				"Project exception", "Creation new project is failed!");
		}

		return ADMIN_VIEW;
	}

	public String createTaskAction() throws PortalException, SystemException {
		long userId = TimeSheetUtil.getCurrentUserId();

		TreeNode selectedProjectNode = null;

		Project selectedProject = null;

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ServiceContext serviceContext = TimeSheetUtil.createServiceContext();

		try {
			selectedProjectNode = adminTaskViewBean.getSelectedProjectNode();

			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Project node is not selected!", e);
			}

			liferayFacesContext.addGlobalErrorMessage(
				"Project node is not selected!");

			return ADMIN_VIEW;
		}

		Task task = null;

		try {
			long[] assignedUserIds = generateUserIds();

			long[] assignedRoleIds = generateRoleIds();

			task = taskModelBean.createTask(
				TimeSheetUtil.getCompanyId(), userId,
				selectedProject.getProjectId(),
				TimeSheetConstants.TASK_ASSIGNED, assignedUserIds,
				assignedRoleIds, serviceContext);

			if (logger.isDebugEnabled()) {
				logger.debug("New Task: " + task.getTaskName());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Adding task is failed!", e);
			}

			liferayFacesContext.addGlobalErrorMessage("Adding task is failed!");

			return "/views/admin/view.xhtml";
		}

		adminTaskViewBean.init();

		return ADMIN_VIEW;
	}

	public void deleteDayAction(Day day) {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		if (day != null) {
			try {
				dayModelBean.deleteDay(day.getDayId());
			} catch (Exception e) {
				logger.error("Day deletion is failed!", e);

				liferayFacesContext.addGlobalErrorMessage(
					"Day exception", "Day deletion is failed!");
			}

			liferayFacesContext.addGlobalInfoMessage(
				"Day operation", "Day deletion was succesfull!");
		}
	}

	public void deleteTaskAction() {
		try {
			Task task = adminTaskViewBean.getSelectedTask();

			if (task != null) {
				taskModelBean.deleteTask(task.getTaskId());
			}

			adminTaskViewBean.init();
		} catch (Exception e) {
		}
	}

	public String updateTaskAction() {
		Task editedTask = adminTaskViewBean.getSelectedTask();

		TreeNode selectedProjectTreeNode =
			adminTaskViewBean.getSelectedProjectNode();

		String description = taskModelBean.getDescription();
		String taskName = taskModelBean.getTaskName();

		Project selectedProject = null;

		if (selectedProjectTreeNode != null) {
			ProjectTreeNode projectTreeNode =
				(ProjectTreeNode)selectedProjectTreeNode;
	
			selectedProject = projectTreeNode.getProject();
		}

		long[] assignedUserIds = generateUserIds();

		long[] assignedRoleIds = generateRoleIds();

		try {
			taskModelBean.updateTask(
				editedTask, taskName, description, selectedProject,
				assignedUserIds, assignedRoleIds);

			adminTaskViewBean.init();
		} catch (Exception e) {
		}

		return ADMIN_VIEW;
	}

	public String updateProjectAction() {
		Project project =
			((ProjectTreeNode)projectViewBean.getSelectedProjectNode())
				.getProject();

		project.setEnabled(projectModelBean.isEnabled());
		project.setDescription(projectModelBean.getDescription());
		project.setProjectName(projectModelBean.getProjectName());

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			projectModelBean.updateProject(project);

			projectViewBean.init();

			if (logger.isDebugEnabled()) {
				logger.debug("Project is updated: " + project.getProjectName());
			}
		} catch (Exception e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Project update is failed!");
		}

		return ADMIN_VIEW;
	}

	protected long[] generateUserIds() {
		List<User> assignedUsers = taskModelBean.getAssignedUsers();

		long[] assignedUserIds = new long[assignedUsers.size()];

		for (int i = 0; i < assignedUsers.size(); i++) {
			User user = assignedUsers.get(i);

			assignedUserIds[i] = user.getUserId();
		}

		return assignedUserIds;
	}

	protected long[] generateRoleIds() {
		List<Role> assignedRoles = taskModelBean.getAssignedRoles();

		long[] assignedRoleIds = new long[assignedRoles.size()];

		for (int i = 0; i < assignedRoles.size(); i++) {
			Role role = assignedRoles.get(i);

			assignedRoleIds[i] = role.getRoleId();
		}

		return assignedRoleIds;
	}

	public AdminTaskViewBean getAdminTaskViewBean() {
		return adminTaskViewBean;
	}

	public DayModelBean getDayModelBean() {
		return dayModelBean;
	}

	public ProjectModelBean getProjectModelBean() {
		return projectModelBean;
	}

	public ProjectViewBean getProjectViewBean() {
		return projectViewBean;
	}

	public TaskModelBean getTaskModelBean() {
		return taskModelBean;
	}

	public boolean isProjectTree() {
		return adminTaskViewBean.getRoot() != null;
	}

	public void selectRole(long roleId)
		throws PortalException, SystemException {

		Role role = RoleLocalServiceUtil.getRole(roleId);

		List<Role> assignedRoles = taskModelBean.getAssignedRoles();

		Iterator<Role> roleIterator = assignedRoles.iterator();

		boolean flag = false;

		while (roleIterator.hasNext()) {
			Role actualRole = roleIterator.next();

			if (actualRole.getRoleId() == roleId) {
				roleIterator.remove();

				flag = true;
			}
		}

		if (!flag) {
			assignedRoles.add(role);
		}
	}

	public void selectUser(long userId)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		List<User> assignedUsers = taskModelBean.getAssignedUsers();

		Iterator<User> userIterator = assignedUsers.iterator();

		boolean flag = false;

		while (userIterator.hasNext()) {
			User actualUser = userIterator.next();

			if (actualUser.getUserId() == userId) {
				userIterator.remove();

				flag = true;
			}
		}

		if (!flag) {
			assignedUsers.add(user);
		}
	}

	public void setAdminTaskViewBean(AdminTaskViewBean adminTaskViewBean) {
		this.adminTaskViewBean = adminTaskViewBean;
	}

	public void setDayModelBean(DayModelBean dayModelBean) {
		this.dayModelBean = dayModelBean;
	}

	public void setProjectModelBean(ProjectModelBean projectModelBean) {
		this.projectModelBean = projectModelBean;
	}

	public void setProjectViewBean(ProjectViewBean projectViewBean) {
		this.projectViewBean = projectViewBean;
	}

	public void setTaskModelBean(TaskModelBean taskModelBean) {
		this.taskModelBean = taskModelBean;
	}

	@ManagedProperty(name = "adminTaskViewBean",
		value = "#{adminTaskViewBean}")
	private AdminTaskViewBean adminTaskViewBean;

	@ManagedProperty(name = "dayModelBean",
		value = "#{dayModelBean}")
	private DayModelBean dayModelBean;

	@ManagedProperty(name = "projectModelBean",
		value = "#{projectModelBean}")
	private ProjectModelBean projectModelBean;

	@ManagedProperty(name = "projectViewBean",
		value = "#{projectViewBean}")
	private ProjectViewBean projectViewBean;

	@ManagedProperty(name = "taskModelBean",
		value = "#{taskModelBean}")
	private TaskModelBean taskModelBean;

	public static final String ADMIN_VIEW = "/views/admin/view.xhtml";

	private static final long serialVersionUID = 3679608295250497309L;
	private static Logger logger = LoggerFactory.getLogger(
		AdminManagedBean.class);

}