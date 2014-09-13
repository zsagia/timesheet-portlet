package com.liferay.timesheet.bean.model;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.service.TaskServiceUtil;
import com.liferay.timesheet.util.ActionKeys;
import com.liferay.timesheet.util.RoleUtil;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "taskModelBean")
@ViewScoped
public class TaskModelBean implements Serializable {

	public TaskModelBean() {
		init();
	}

	public Task createTask(
			long companyId, long userId, long projectId, int type,
			long[] assignedUserIds, long[] assignedRoleIds,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return TaskLocalServiceUtil.addTask(
			companyId, userId, taskName, projectId, description, type,
			assignedUserIds, assignedRoleIds, serviceContext);
	}

	public Task createTask(
			long companyId, long userId, long projectId, int type,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return TaskLocalServiceUtil.addTask(
			companyId, userId, taskName, projectId, description, type,
			serviceContext);
	}

	public void deleteTask(long taskId)
		throws PortalException, SystemException {

		TaskLocalServiceUtil.deleteTask(taskId);
	}

	public List<Role> getAssignedRoles() {
		return assignedRoles;
	}

	public List<Task> getAssignedTask()
		throws PortalException, SystemException {

		return TaskServiceUtil.getTasksByC_G_TT(
			TimeSheetUtil.getCompanyId(), TimeSheetUtil.getScopeGroupId(),
			TimeSheetConstants.TASK_ASSIGNED);
	}

	public List<User> getAssignedUsers() {
		return assignedUsers;
	}

	public String getDescription() {
		return description;
	}

	public Map<Long, Boolean> getRoleList() {
		return roleList;
	}

	public String getTaskName() {
		return taskName;
	}

	public List<Task> getTasksByUser() {
		ThemeDisplay themeDisplay = TimeSheetUtil.getThemeDisplay();

		long userId = TimeSheetUtil.getCurrentUserId();
		long groupId = themeDisplay.getScopeGroupId();

		List<Task> userTasks = null;

		try {
			userTasks = TaskLocalServiceUtil.getTaskByU_TT(
				userId, TimeSheetConstants.TASK_GENERAL);

			userTasks.addAll(
				TaskServiceUtil.getTasksByC_G_U_TT(
					TimeSheetUtil.getCompanyId(), groupId, userId,
					TimeSheetConstants.TASK_ASSIGNED));

			userTasks.add(
				0, TaskLocalServiceUtil.getTaskByType(
					TimeSheetConstants.TASK_BREAK));

			userTasks.add(
				0, TaskLocalServiceUtil.getTaskByType(
					TimeSheetConstants.TASK_MANDATORY_BREAK));

			userTasks.add(
				0, TaskLocalServiceUtil.getTaskByType(
					TimeSheetConstants.TASK_LUNCH));
		} catch (Exception e) {
			logger.error(
				"Getting tasks for userId: " + userId + " is failed", e);
		}

		return userTasks;
	}

	public Map<Long, Boolean> getUserList() {
		return userList;
	}

	public void init() {
		taskName = null;

		description = null;

		assignedRoles = new ArrayList<Role>();
		assignedUsers = new ArrayList<User>();

		roleList = new HashMap<Long, Boolean>();
		userList = new HashMap<Long, Boolean>();
	}

	public void setAssignedRoles(List<Role> assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	public void setAssignedRoles(Task task)
		throws PortalException, SystemException {

		List<Role> assignedRoles = RoleUtil.getAssignedRoles(
			task.getCompanyId(), task, ActionKeys.VIEW);

		setAssignedRoles(assignedRoles);
	}

	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	public void setAssignedUsers(Task task)
		throws PortalException, SystemException {

		Role role = RoleLocalServiceUtil.getRole(
			task.getCompanyId(),
			TimeSheetConstants.TIMESHEET_TASK_ROLE + task.getTaskId());

		List<User> assignedUsers = UserLocalServiceUtil.getRoleUsers(
			role.getRoleId());

		if (!assignedUsers.isEmpty()) {
			setAssignedUsers(ListUtil.copy(assignedUsers));
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRoleList() {
		roleList = new HashMap<Long, Boolean>();

		for (Role role : assignedRoles) {
			roleList.put(role.getRoleId(), Boolean.TRUE);
		}
	}

	public void setRoleList(Map<Long, Boolean> roleList) {
		this.roleList = roleList;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setUserList() {
		userList = new HashMap<Long, Boolean>();

		for (User user : assignedUsers) {
			userList.put(user.getUserId(), Boolean.TRUE);
		}
	}

	public void setUserList(Map<Long, Boolean> userList) {
		this.userList = userList;
	}

	public Task updateTask(
			Task task, String taskName, String description, Project project)
		throws PortalException, SystemException {

		task.setTaskName(taskName);
		task.setDescription(description);

		if (project != null) {
			task.setProjectId(project.getProjectId());
		}

		return TaskLocalServiceUtil.updateTask(task);
	}

	public Task updateTask(
			Task task, String taskName, String description, Project project,
			long[] assignedUserIds, long[] assignedRoleIds)
		throws PortalException, SystemException {

		task.setTaskName(taskName);
		task.setDescription(description);

		if (project != null) {
			task.setProjectId(project.getProjectId());
		}

		return TaskLocalServiceUtil.updateTask(
			task, assignedUserIds, assignedRoleIds);
	}

	private static final long serialVersionUID = 4961368400508391281L;

	private static Logger logger = LoggerFactory.getLogger(TaskModelBean.class);

	private List<Role> assignedRoles = null;
	private List<User> assignedUsers = null;
	private String description;
	private Map<Long, Boolean> roleList = null;
	private String taskName;
	private Map<Long, Boolean> userList = null;

}