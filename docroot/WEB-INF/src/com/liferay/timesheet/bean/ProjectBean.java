package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.admin.BaseAdminBean;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "projectBean")
@ViewScoped
public class ProjectBean extends BaseAdminBean implements Serializable {

	public ProjectBean() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(false, 0, root);
		} catch (Exception e) {
			logger.error("Tree generation is failed!");
		}
	}

	public String createProjectAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			
			
			Project project = projectModelBean.createProject(
				selectedDepartment, selectedProject);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New project is created: " + project.getProjectName());
			}
		} catch (Exception e) {
			logger.error("Creation new project is failed!", e);

			liferayFacesContext.addGlobalErrorMessage(
				"Project exception", "Creation new project is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	@Override
	public void doEditAction() {
		setAction(ACTION_EDIT);

		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		projectModelBean.setProjectName(project.getProjectName());
	}

	@Override
	public void doNewAction() {
		setActionValues(ACTION_NEW, false, null, null);
	}

	public void onDepartmentSelect() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(
				false, selectedDepartment.getDepartmentId(), root);
		} catch (Exception e) {
			e.printStackTrace();
		}

		setActionValues(ACTION_NEW, false, null, null);

		selectedProjectNode = null;
	}

	@Override
	public void onNodeSelect() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		setActionValues(
			ACTION_SELECTED, project.getEnabled(), project.getProjectName(),
			project.getDescription());
	}

	@Override
	public void onNodeUnSelect() {
		doNewAction();
	}

	public String updateProjectAction() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		project.setEnabled(projectModelBean.isEnabled());
		project.setDescription(projectModelBean.getDescription());
		project.setProjectName(projectModelBean.getProjectName());

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			projectModelBean.updateProject(project);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Project is updated: " + project.getProjectName());
			}
		} catch (Exception e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Project update is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	protected void generateTreeNodes(
			boolean checkEnabled, long departmentId, TreeNode parentNode)
		throws PortalException, SystemException {

		if (departmentId > 0) {
			Project projectNode = ((ProjectTreeNode)parentNode).getProject();

			long projectId =
				projectNode != null ? projectNode.getProjectId() : 0;

			List<Project> projects = ProjectServiceUtil.getProjectsByD_PP(
				departmentId, projectId);

			for (Project project: projects) {
				boolean enabled = true;

				if (checkEnabled && !project.getEnabled()) {
					enabled = false;
				}

				if (enabled) {
					TreeNode treeNode = new ProjectTreeNode(
						project.getProjectName(), parentNode);

					((ProjectTreeNode)treeNode).setProject(project);

					generateTreeNodes(checkEnabled, departmentId, treeNode);
				}
			}
		}
	}

	protected void setActionValues(
		String action, boolean enabled, String projectName, String description) {

		setAction(action);

		projectModelBean.setDescription(description);
		projectModelBean.setEnabled(enabled);
		projectModelBean.setProjectName(projectName);
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedProjectNode() {
		return selectedProjectNode;
	}

	public boolean isEnabled() {
		if (ACTION_NEW.equals(getAction())) {
			projectModelBean.setEnabled(false);
		}
		else if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			projectModelBean.setEnabled(project.getEnabled());
		}

		return projectModelBean.isEnabled();
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setSelectedProjectNode(TreeNode selectedProjectNode) {
		this.selectedProjectNode = selectedProjectNode;

		if (selectedProjectNode != null) {
			this.setSelectedProject(
				((ProjectTreeNode)selectedProjectNode).getProject());
		}
	}

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public ProjectModelBean getProjectModelBean() {
		return projectModelBean;
	}

	public void setProjectModelBean(ProjectModelBean projectModelBean) {
		this.projectModelBean = projectModelBean;
	}

	private Department selectedDepartment = null;

	private TreeNode root = null;

	private TreeNode selectedProjectNode = null;

	private Project selectedProject = null;

	@ManagedProperty(name = "projectModelBean",
		value = "#{projectModelBean}")
	private ProjectModelBean projectModelBean;

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(ProjectBean.class);

}