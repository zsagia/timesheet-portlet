package com.liferay.timesheet.admin;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentServiceUtil;

import java.util.Collections;
import java.util.List;

public abstract class BaseAdminBean implements BaseAdmin {

	private String action = ACTION_NEW;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Department> getDepartments() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		List<Department> departments = null;

		try {
			departments = DepartmentServiceUtil.getDepartments(
				liferayFacesContext.getCompanyId());
		} catch (Exception e) {
			departments = Collections.emptyList();
		}

		return departments;
	}

}