package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.EntityCreationException;
import com.liferay.timesheet.admin.BaseAdminBean;
import com.liferay.timesheet.model.Department;
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
public class ProjectBean extends BaseAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(ProjectBean.class);

	private boolean enabled = false;

	private String description = null;

	private Department selectedDepartment = null;

	private String projectName = null;

	private TreeNode root = null;

	private TreeNode selectedProjectNode = null;

	public ProjectBean() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(false, 0, root);
		} catch (SystemException e) {
			logger.error("Tree generation is failed!");
		}
	}

	@Override
	public Object createEntity() throws EntityCreationException {
		long selectedProjectId = 0;

		if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			selectedProjectId = project.getProjectId();
		}

		ServiceContext serviceContext =
			TimesheetUtil.createServiceContext();

		Project project = null;

		try {
			project = ProjectLocalServiceUtil.addProject(
				TimesheetUtil.getCurrentUserId(),
				selectedDepartment.getDepartmentId(), true, selectedProjectId,
				getProjectName(), description, serviceContext);
		} catch (Exception e) {
			throw new EntityCreationException();
		}

		return project;
	}

	@Override
	public String createEntityAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Project project = (Project)createEntity();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New project is created: " + project.getProjectName());
			}
		} catch (EntityCreationException e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Creation new project is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	@Override
	public void doEditAction() {
		setAction(ACTION_EDIT);

		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		projectName = project.getProjectName();
	}

	@Override
	public void doNewAction() {
		setActionValues(ACTION_NEW, false, null);
	}

	public void onDepartmentSelect() {
		root = new ProjectTreeNode(null, null);

		try {
			generateTreeNodes(
				false, selectedDepartment.getDepartmentId(), root);
		} catch (SystemException e) {
			e.printStackTrace();
		}

		setActionValues(ACTION_NEW, false, null);

		selectedProjectNode = null;
	}

	@Override
	public void onNodeSelect() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		setActionValues(
			ACTION_SELECTED, project.getEnabled(), project.getProjectName());
	}

	@Override
	public void onNodeUnSelect() {
		doNewAction();
	}

	@Override
	public Object updateEntity(Object entity) throws SystemException{
		ProjectLocalServiceUtil.updateProject((Project)entity);

		return entity;
	}

	@Override
	public String updateEntityAction() {
		Project project =
			((ProjectTreeNode)selectedProjectNode).getProject();

		project.setEnabled(enabled);
		project.setProjectName(projectName);

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			updateEntity(project);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Project is updated: " + project.getProjectName());
			}
		} catch (SystemException e) {
			logger.error("Creation new project is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				" Project update is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	protected void generateTreeNodes(
			boolean checkEnabled, long departmentId, TreeNode parentNode)
		throws SystemException {

		if (departmentId > 0) {
			Project projectNode = ((ProjectTreeNode)parentNode).getProject();

			long projectId =
				projectNode != null ? projectNode.getProjectId() : 0;

			List<Project> projects = ProjectLocalServiceUtil.getProjectsByD_PP(
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
		String action, boolean enabled, String projectName) {

		setAction(action);

		this.projectName = projectName;

		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
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
		if (ACTION_NEW.equals(getAction())) {
			enabled = false;
		}
		else if (selectedProjectNode != null) {
			Project project =
				((ProjectTreeNode)selectedProjectNode).getProject();

			enabled = project.getEnabled();
		}

		return enabled;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

}