package com.liferay.timesheet.bean.view;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.ProjectServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.TreeNode;

public abstract class AbstractViewBean implements Serializable {

	protected void generateTreeNodes(
			boolean checkEnabled, long ownerGroupId, TreeNode parentNode)
		throws PortalException, SystemException {

		if (ownerGroupId > 0) {
			Project projectNode = ((ProjectTreeNode)parentNode).getProject();

			long projectId =
				projectNode != null ? projectNode.getProjectId() : 0;

			List<Project> projects = ProjectServiceUtil.getProjectsByO_PP(
				ownerGroupId, projectId);

			for (Project project: projects) {
				boolean enabled = true;

				if (checkEnabled && !project.getEnabled()) {
					enabled = false;
				}

				if (enabled) {
					TreeNode treeNode = new ProjectTreeNode(
						project.getProjectName(), parentNode);

					((ProjectTreeNode)treeNode).setProject(project);

					generateTreeNodes(checkEnabled, ownerGroupId, treeNode);
				}
			}
		}
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public void setSelectedProjectNode(TreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;

		if (selectedProjectNode != null) {
			selectedProject =
				((ProjectTreeNode)selectedProjectNode).getProject();
		}
	}

	public TaskSession getCurrentTaskSession() {
		return currentTaskSession;
	}

	public void setCurrentTaskSession(TaskSession currentTaskSession) {
		this.currentTaskSession = currentTaskSession;
	}

	public Date getTodayWithoutTime() {
		return todayWithoutTime;
	}

	public void setTodayWithoutTime(Date todayWithoutTime) {
		this.todayWithoutTime = todayWithoutTime;
	}

	public List<Group> getOwnerGroupList() {
		return ownerGroupList;
	}

	public void setOwnerGroupList(List<Group> ownerGroupList) {
		this.ownerGroupList = ownerGroupList;
	}

	public Group getSelectedOwnerGroup() {
		return selectedOwnerGroup;
	}

	public void setSelectedOwnerGroup(Group selectedOwnerGroup) {
		this.selectedOwnerGroup = selectedOwnerGroup;
	}

	private Date todayWithoutTime = null;
	private TaskSession currentTaskSession = null;
	private List<Group> ownerGroupList = null;
	private TreeNode root = null;
	private Group selectedOwnerGroup = null;
	private Project selectedProject = null;
	private TreeNode selectedProjectNode = null;

	private static final long serialVersionUID = 1L;
}