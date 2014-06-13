package com.liferay.timesheet.bean.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.ProjectServiceUtil;
import com.liferay.timesheet.util.DateTimeUtil;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "projectModelBean")
@ViewScoped
public class ProjectModelBean implements Serializable {

	public Project createProject(Project selectedProject)
		throws PortalException, SystemException {

		long selectedProjectId = 0;

		if (selectedProject != null) {
			selectedProjectId = selectedProject.getProjectId();
		}

		ServiceContext serviceContext =
			DateTimeUtil.createServiceContext();

		Project project = null;

		project = ProjectServiceUtil.addProject(
			DateTimeUtil.getCurrentUserId(),0, true,
			selectedProjectId, projectName, description,
			serviceContext);

		return project;
	}

	public Project updateProject(Project project)
		throws PortalException, SystemException {

		ProjectServiceUtil.updateProject(project);

		return project;
	}

	public String getDescription() {
		return description;
	}

	public String getProjectName() {
		return projectName;
	}

	public boolean isEnabled() {
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

	private boolean enabled = false;
	private String description = null;
	private String projectName = null;

	private static final long serialVersionUID = 4411045587555113969L;

}