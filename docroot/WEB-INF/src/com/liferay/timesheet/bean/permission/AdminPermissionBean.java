package com.liferay.timesheet.bean.permission;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.permission.AdminPermission;
import com.liferay.timesheet.service.permission.ProjectPermission;
import com.liferay.timesheet.util.ActionKeys;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "adminPermissionBean")
@ApplicationScoped
public class AdminPermissionBean {

	public boolean checkAddProject() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay(); 

		return AdminPermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			ActionKeys.ADD_PROJECT);
	}

	public boolean checkPermissionsProject(Project project) {
		if (project == null) {
			return false;
		}

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay(); 

		return ProjectPermission.contains(
			themeDisplay.getPermissionChecker(), project,
			ActionKeys.PERMISSIONS);
	}

	public boolean checkUpdateProject(Project project) {
		if (project == null) {
			return false;
		}

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay(); 

		return ProjectPermission.contains(
			themeDisplay.getPermissionChecker(), project, ActionKeys.UPDATE);
	}

	public boolean checkViewProject() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay(); 

		return AdminPermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			ActionKeys.VIEW_PROJECT);
	}

	public boolean checkViewTask() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay(); 

		return AdminPermission.contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			ActionKeys.VIEW_TASK);
	}

}