package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectName;

	private TreeNode root = null;

	private TreeNode selectedProjectNode = null;

	public ProjectBean() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(root);
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	}

	public void addProject() throws PortalException, SystemException {
		long selectedProjectId = 0;

		if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			selectedProjectId = project.getProjectId();
		}

		ProjectLocalServiceUtil.addProject(
			getProjectName(), TimesheetUtil.getCurrentUserId(),
			selectedProjectId);
	}

	protected void generateTreeNodes(TreeNode parentNode)
		throws SystemException {

		Project projectNode = ((ProjectTreeNode)parentNode).getProject();

		long projectId = projectNode != null ? projectNode.getProjectId() : 0;

		List<Project> projects = ProjectLocalServiceUtil.getProjects(projectId);

		for (Project project: projects) {
			TreeNode treeNode = new ProjectTreeNode(
				project.getProjectName(), parentNode);

			((ProjectTreeNode)treeNode).setProject(project);

			generateTreeNodes(treeNode);
		}
	}

}
