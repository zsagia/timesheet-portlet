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

package com.liferay.timesheet.bean.admin;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.timesheet.bean.AbstractEntityViewBean;
import com.liferay.timesheet.bean.model.TaskModelBean;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.UserTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;
import com.liferay.timesheet.service.ProjectServiceUtil;
import com.liferay.timesheet.service.TaskServiceUtil;
import com.liferay.timesheet.util.RoleUtil;
import com.liferay.timesheet.util.TimeSheetConstants;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

/*
 * @author Zsolt Szabo
 */

@ManagedBean
@ViewScoped
public class AdminTaskViewBean extends AbstractEntityViewBean {

	public AdminTaskViewBean() {
		init();
	}

	@Override
	public void doEditAction(Object bean) {
		try {
			TaskModelBean taskModelBean = (TaskModelBean)bean;

			Task selectedTask = getSelectedTask();

			taskModelBean.setTaskName(selectedTask.getTaskName());
			taskModelBean.setDescription(selectedTask.getDescription());
			taskModelBean.setAssignedRoles(selectedTask);
			taskModelBean.setAssignedUsers(selectedTask);
			taskModelBean.setRoleList();
			taskModelBean.setUserList();

			setAction(ACTION_EDIT);
		} catch (Exception e) {
		}
	}

	@Override
	public void doNewAction(Object bean) {
		TaskModelBean taskModelBean = (TaskModelBean)bean;

		taskModelBean.init();

		setSelectedTask(null);

		setAction(ACTION_NEW);
	}

	@Override
	public void doListAction(Object bean) {
		setAction(ACTION_LIST);
	}

	public TreeNode getUserTreeRoot() {
		return userTreeRoot;
	}

	public void init() {
		try {
			setAction(ACTION_LIST);

			setSelectedTask(null);

			initProjectTree();

			initUserTree();

			initRoleList();

			assignedTasks = TaskServiceUtil.getTasksByC_G_TT(
				TimeSheetUtil.getCompanyId(), TimeSheetUtil.getScopeGroupId(),
				TimeSheetConstants.TASK_ASSIGNED);
		} catch (Exception e) {
		}
	}

	@Override
	public void onNodeSelect(Object bean) {
	}

	@Override
	public void onNodeUnSelect(Object bean) {
	}

	public void onRowSelect(SelectEvent event) {
		Task task = (Task)event.getObject();

		setSelectedTask(task);
	}

	public void onRowUnSelect(SelectEvent event) {
		setSelectedTask(null);
	}

	private void initProjectTree() throws PortalException, SystemException {
		List<Project> projects = ProjectServiceUtil.getProjects(0);

		if ((projects != null) && (projects.size() > 0)) {
			setRoot(new ProjectTreeNode(null, null));

			TreeNodeUtil.generateProjectTreeNodes(true, getRoot());
		}
	}

	private void initUserTree() throws Exception {
		setUserTreeRoot(new UserTreeNode());

		TreeNodeUtil.generateUserTreeNodes(getUserTreeRoot());
	}

	private void initRoleList() throws SystemException {
		roleList = RoleUtil.getRegularRoles();
	}

	public List<Task> getAssignedTasks() {
		return assignedTasks;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setAssignedTasks(List<Task> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setUserTreeRoot(TreeNode userTreeRoot) {
		this.userTreeRoot = userTreeRoot;
	}

	private List<Task> assignedTasks = null;
	private List<Role> roleList = null;
	private TreeNode userTreeRoot = null;

	private static final long serialVersionUID = 1862205069150831867L;

}