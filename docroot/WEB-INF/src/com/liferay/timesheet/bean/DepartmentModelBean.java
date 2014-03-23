package com.liferay.timesheet.bean;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "departmentModelBean")
@ViewScoped
public class DepartmentModelBean implements Serializable {

	public Department createDepartment()
		throws PortalException, SystemException {

		Department department = null;

		ServiceContext serviceContext =
			TimesheetUtil.createServiceContext();

		department = DepartmentServiceUtil.addDepartment(
			TimesheetUtil.getCurrentUserId(), getDepartmentName(),
			serviceContext);

		return department;
	}

	public Department updateDepartment(Department entity)
		throws SystemException, PortalException{

		DepartmentServiceUtil.updateDepartment(entity);

		return entity;
	}

	private static final long serialVersionUID = 1L;

	public String departmentName = null;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}