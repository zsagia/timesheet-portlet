/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ResourcePermissionServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl;
import com.liferay.timesheet.util.ActionKeys;
import com.liferay.timesheet.util.RoleUtil;
import com.liferay.timesheet.util.TimeSheetConstants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;;

/**
 * The implementation of the task local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.liferay.timesheet.service.TaskLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Istvan Sajtos
 * @author Zsolt Szabo
 * @see com.liferay.timesheet.service.base.TaskLocalServiceBaseImpl
 * @see com.liferay.timesheet.service.TaskLocalServiceUtil
 */
public class TaskLocalServiceImpl extends TaskLocalServiceBaseImpl {

	public Task addTask(
			long companyId, long userId, String taskName, long projectId,
			String description, int taskType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Task task = createTask(
			companyId, userId, taskName, projectId, description, taskType,
			serviceContext);

		return task;
	}

	public Task addTask(
			long companyId, long userId, String taskName, long projectId,
			String description, int taskType, long[] assignedUserIds,
			long[] assignedRoleIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Task task = createTask(
			companyId, userId, taskName, projectId, description, taskType,
			serviceContext);

		Role role = addRoleToTask(task);

		assignUsersToTask(task, role, assignedUserIds);

		//assignRolesToTask(task, assignedRoleIds);

		return task;
	}

	public void addTaskResources(
			Task task, boolean addGroupPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			task.getCompanyId(), task.getGroupId(), task.getUserId(),
			Task.class.getName(), task.getTaskId(), false,
			addGroupPermissions, addGuestPermissions);
	}

	public void addTaskResources(
			Task task, String[] groupPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			task.getCompanyId(), task.getGroupId(), task.getUserId(),
			Task.class.getName(), task.getTaskId(), groupPermissions,
			guestPermissions);
	}

	@Override
	public Task deleteTask(long taskId)
		throws PortalException, SystemException {

		Task task = taskPersistence.findByPrimaryKey(taskId);

		resourceLocalService.deleteResource(
			task.getCompanyId(), Task.class.getName(),
			ResourceConstants.SCOPE_COMPANY, task.getTaskId());

		return deleteTask(task);
	}

	@Override
	public Task deleteTask(Task task) throws PortalException, SystemException {
		long companyId = task.getCompanyId();

		Role role = RoleLocalServiceUtil.getRole(
			companyId,
			TimeSheetConstants.TIMESHEET_TASK_ROLE + task.getTaskId());

		resourceLocalService.deleteResource(
			companyId, Role.class.getName(),
			ResourceConstants.SCOPE_COMPANY, role.getRoleId());

		RoleLocalServiceUtil.deleteRole(role);

		resourceLocalService.deleteResource(
			companyId, Task.class.getName(),
			ResourceConstants.SCOPE_COMPANY, task.getTaskId());

		taskPersistence.remove(task);

		return task;
	}

	public Task getTaskByType(int taskType) throws SystemException {
		List<Task> taskList = taskPersistence.findByTaskType(taskType);

		if (!taskList.isEmpty()) {
			return taskList.get(0);
		}

		return null;
	}

	public List<Task> getTasksByC_G_TT(
			long companyId, long groupId, int taskType)
		throws PortalException, SystemException {

		return taskPersistence.findByC_G_TT(companyId, groupId, taskType);
	}

	public List<Task> getTasksByC_G_U(
			long companyId, long groupId, long userId)
		throws PortalException, SystemException {

		return getTasksByC_G_U_TT(
			companyId, groupId, userId, TimeSheetConstants.TASK_GENERAL);
	}

	public List<Task> getTasksByC_G_U_TT(
			long companyId, long groupId, long userId, int taskType)
		throws PortalException, SystemException {

		return taskPersistence.findByC_G_TT(companyId, groupId, taskType);
	}

	public List<Task> getTasksByU_D(long userId, Date day)
		throws PortalException, SystemException {

		List<TaskSession> taskSessionList =
			taskSessionLocalService.getTaskSessionsByU_D(userId, day);

		if (taskSessionList == null) {
			return Collections.emptyList();
		}

		Set<Long> taskIds = new HashSet<Long>();

		List<Task> taskList = new ArrayList<Task>();

		for (TaskSession taskSession : taskSessionList) {
			Task task = taskSession.getTask();

			long taskId = task.getTaskId();

			if (!taskIds.contains(taskId)) {
				taskIds.add(taskId);

				taskList.add(task);
			}
		}

		return taskList;
	}

	public List<Task> getTaskByU_TT(long userId, int taskType) {
		return taskFinder.findByU_TT(userId, taskType);
	}

	public List<Task> getTasksByC_U_I(
			long companyId, long userId, Date date1, Date date2)
		throws PortalException, SystemException {

		List<TaskSession> taskSessionList =
			taskSessionLocalService.getTaskSessionsByC_U_I(
				companyId, userId, date1, date2);

		if (taskSessionList == null) {
			return Collections.emptyList();
		}

		Set<Long> taskIds = new HashSet<Long>();

		List<Task> taskList = new ArrayList<Task>();

		for (TaskSession taskSession : taskSessionList) {
			Task task = taskSession.getTask();

			long taskId = task.getTaskId();

			if (!taskIds.contains(taskId)) {
				taskIds.add(taskId);

				taskList.add(task);
			}
		}

		return taskList;
	}

	public Task updateTask(
			Task task, long[] assignedUserIds, long[] assignedRoleIds)
		throws PortalException, SystemException {

		taskPersistence.update(task);

		Role role = RoleLocalServiceUtil.getRole(
			task.getCompanyId(),
			TimeSheetConstants.TIMESHEET_TASK_ROLE + task.getTaskId());

		assignUsersToTask(task, role, assignedUserIds);

		assignRolesToTask(task, assignedRoleIds);

		return task;
	}

	protected Role addRoleToTask(Task task)
		throws PortalException, SystemException {

		String roleName =
			TimeSheetConstants.TIMESHEET_TASK_ROLE + task.getTaskId();

		Role role = RoleLocalServiceUtil.addRole(
			task.getUserId(), null, 0, roleName, null, null,
			RoleConstants.TYPE_REGULAR, TimeSheetConstants.TIMESHEET_TASK_ROLE,
			null);

		ResourcePermissionServiceUtil.setIndividualResourcePermissions(
			task.getGroupId(), task.getCompanyId(), Task.class.getName(),
			String.valueOf(task.getTaskId()), role.getRoleId(),
			new String[]{ActionKeys.VIEW});

		return role;
	}

	protected void assignRolesToTask(
			Task task, long[] assignedRoleIds)
		throws PortalException, SystemException {

		long companyId = task.getCompanyId();

		List<Role> oldAssignedRoles = RoleUtil.getAssignedRoles(
			companyId, task, ActionKeys.VIEW);

		List<Long> newAssignedRoleIds = null;

		if (assignedRoleIds != null) {
			newAssignedRoleIds = new ArrayList<Long>();

			for (int i = 0; i < assignedRoleIds.length; i++) {
				newAssignedRoleIds.add(assignedRoleIds[i]);
			}
		}

		for (Role role: oldAssignedRoles) {
			if (!ArrayUtil.contains(assignedRoleIds, role.getRoleId())) {
				ResourcePermissionServiceUtil.removeResourcePermission(
					task.getGroupId(), task.getCompanyId(),
					Task.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
					String.valueOf(task.getTaskId()), role.getRoleId(),
					ActionKeys.VIEW);
			}
			else {
				newAssignedRoleIds.remove(role);
			}
		}

		for (long roleId: assignedRoleIds) {
			ResourcePermissionServiceUtil.setIndividualResourcePermissions(
				task.getGroupId(), task.getCompanyId(),
				Task.class.getName(), String.valueOf(task.getTaskId()),
				roleId, new String[]{ActionKeys.VIEW});
		}
	}

	protected void assignUsersToTask(
			Task task, Role role, long[] assignedUserIds)
		throws PortalException, SystemException {

		List<User> assignedUsers = UserLocalServiceUtil.getRoleUsers(
			role.getRoleId());

		List<Long> newUserIds = null;

		if (assignedUserIds != null) {
			newUserIds = new ArrayList<Long>();

			for (int i = 0; i < assignedUserIds.length; i++) {
				newUserIds.add(assignedUserIds[i]);
			}
		}

		if (assignedUsers.size() > 0) {
			for (User user: assignedUsers) {
				if (!ArrayUtil.contains(assignedUserIds, user.getUserId())) {
					UserLocalServiceUtil.deleteRoleUser(
						role.getRoleId(), user.getUserId());
				}
				else {
					newUserIds.remove(user.getUserId());
				}
			}
		}

		UserLocalServiceUtil.addRoleUsers(
			role.getRoleId(), ArrayUtil.toLongArray(newUserIds));
	}

	protected Task createTask(
			long companyId, long userId, String taskName, long projectId,
			String description, int taskType, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		long groupId = serviceContext.getScopeGroupId();

		long taskId = counterLocalService.increment();

		Task task = taskPersistence.create(taskId);

		Date now = new Date();

		task.setCompanyId(user.getCompanyId());
		task.setCreateDate(now);
		task.setGroupId(groupId);
		task.setModifiedDate(now);
		task.setProjectId(projectId);
		task.setTaskName(taskName);
		task.setTaskType(taskType);
		task.setUserId(userId);
		task.setUserName(user.getFullName());

		taskPersistence.update(task);

		addTaskResources(
			task, serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());

		return task;
	}

}