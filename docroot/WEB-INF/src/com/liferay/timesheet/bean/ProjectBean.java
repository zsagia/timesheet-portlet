package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.ProjectCreationException;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectLocalServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(ProjectBean.class);

	private String projectName;

	private TreeNode root = null;

	private TreeNode selectedProjectNode = null;

	public ProjectBean() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(root);
		} catch (SystemException e) {
			logger.error("Tree generation is failed!");
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

	public String createProjectAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Project project = createProject();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New project is created: " + project.getProjectName());
			}
		} catch (ProjectCreationException e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Creation new project is failed!");
		}

		return "/views/preferences.xhtml";
	}

	public Project createProject() throws ProjectCreationException {
		long selectedProjectId = 0;

		if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			selectedProjectId = project.getProjectId();
		}

		Project project = null;

		try {
			project = ProjectLocalServiceUtil.addProject(
				getProjectName(), TimesheetUtil.getCurrentUserId(),
				selectedProjectId);
		} catch (Exception e) {
			throw new ProjectCreationException();
		}

		return project;
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