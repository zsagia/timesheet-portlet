package com.liferay.timesheet.bean.portlet;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.bean.model.DayModelBean;
import com.liferay.timesheet.bean.model.ProjectModelBean;
import com.liferay.timesheet.bean.view.ProjectViewBean;
import com.liferay.timesheet.model.Day;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.primefaces.ProjectTreeNode;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AdminManagedBean implements Serializable {

	public String createDayAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		Day day = null;

		try {
			day = dayModelBean.createDay();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New day object is created: " + day.getType());
			}
		} catch (Exception e) {
			logger.error("Creation new day is failed!", e);

			liferayFacesContext.addGlobalErrorMessage(
				"Day exception", "Creation new day is failed!");
		}

		liferayFacesContext.addGlobalInfoMessage(
			"Day operation", "Day creation was succesfull!");

		return "/views/admin/view.xhtml";
	}

	public void deleteDayAction(Day day) {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		if (day != null) {
			try {
				dayModelBean.deleteDay(day.getDayId());
			} catch (Exception e) {
				logger.error("Day deletion is failed!", e);

				liferayFacesContext.addGlobalErrorMessage(
					"Day exception", "Day deletion is failed!");
			}

			liferayFacesContext.addGlobalInfoMessage(
				"Day operation", "Day deletion was succesfull!");
		}
	}

	public String createProjectAction() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			Project project = projectModelBean.createProject(
				projectViewBean.getSelectedProject());

			projectViewBean.init();

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

	public String updateProjectAction() {
		Project project =
			((ProjectTreeNode)projectViewBean.getSelectedProjectNode())
				.getProject();

		project.setEnabled(projectModelBean.isEnabled());
		project.setDescription(projectModelBean.getDescription());
		project.setProjectName(projectModelBean.getProjectName());

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			projectModelBean.updateProject(project);

			projectViewBean.init();

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

	public ProjectModelBean getProjectModelBean() {
		return projectModelBean;
	}

	public void setProjectModelBean(ProjectModelBean projectModelBean) {
		this.projectModelBean = projectModelBean;
	}

	public ProjectViewBean getProjectViewBean() {
		return projectViewBean;
	}

	public void setProjectViewBean(ProjectViewBean projectViewBean) {
		this.projectViewBean = projectViewBean;
	}

	public DayModelBean getDayModelBean() {
		return dayModelBean;
	}

	public void setDayModelBean(DayModelBean dayModelBean) {
		this.dayModelBean = dayModelBean;
	}

	@ManagedProperty(name = "projectViewBean",
		value = "#{projectViewBean}")
	private ProjectViewBean projectViewBean;
	@ManagedProperty(name = "dayModelBean",
		value = "#{dayModelBean}")
	private DayModelBean dayModelBean;
	@ManagedProperty(name = "projectModelBean",
		value = "#{projectModelBean}")
	private ProjectModelBean projectModelBean;

	private static final long serialVersionUID = 3679608295250497309L;
	private static Logger logger = LoggerFactory.getLogger(
		AdminManagedBean.class);

}