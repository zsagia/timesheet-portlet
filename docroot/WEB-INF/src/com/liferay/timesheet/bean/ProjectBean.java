package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.lang.StringPool;
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

	public static final String ACTION_NEW = "new";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_SELECTED = "selected";

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(ProjectBean.class);

	private String action = ACTION_NEW;

	private boolean enabled = false;

	private String projectName;

	private TreeNode root = null;

	private TreeNode selectedProjectNode = null;

	public ProjectBean() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(false, root);
		} catch (SystemException e) {
			logger.error("Tree generation is failed!");
		}
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
				selectedProjectId, true);
		} catch (Exception e) {
			throw new ProjectCreationException();
		}

		return project;
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

	public void editAction() {
		action = ACTION_EDIT;

		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		projectName = project.getProjectName();
	}

	public void newAction() {
		setActionValues(ACTION_NEW, false, StringPool.BLANK);
	}

	public void onNodeSelect() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		setActionValues(
			ACTION_SELECTED, project.getEnabled(), project.getProjectName());
	}

	public void onNodeUnSelect() {
		newAction();
	}

	public Project updateProject(Project project) throws SystemException{
		ProjectLocalServiceUtil.updateProject(project);

		return project;
	}

	public String updateProjectAction() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		project.setEnabled(enabled);
		project.setProjectName(projectName);

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			updateProject(project);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Project is updated: " + project.getProjectName());
			}
		} catch (SystemException e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				" Project update is failed!");
		}

		return "/views/preferences.xhtml";
	}

	protected void generateTreeNodes(boolean checkEnabled, TreeNode parentNode)
		throws SystemException {

		Project projectNode = ((ProjectTreeNode)parentNode).getProject();

		long projectId = projectNode != null ? projectNode.getProjectId() : 0;

		List<Project> projects = ProjectLocalServiceUtil.getProjects(projectId);

		for (Project project: projects) {
			boolean enabled = true;

			if (checkEnabled && !project.getEnabled()) {
				enabled = false;
			}

			if (enabled) {
				TreeNode treeNode = new ProjectTreeNode(
					project.getProjectName(), parentNode);

				((ProjectTreeNode)treeNode).setProject(project);

				generateTreeNodes(checkEnabled, treeNode);
			}
		}
	}

	protected void setActionValues(
		String action, boolean enabled, String projectName) {

		this.action = action;

		this.projectName = projectName;

		this.enabled = enabled;
	}

	public String getAction() {
		return action;
	}

	public String getProjectName() {
		return projectName;
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public boolean isEnabled() {
		if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			enabled = project.getEnabled();
		}

		return enabled;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setSelectedProjectNode(TreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;
	}

}