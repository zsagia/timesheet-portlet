package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.lang.StringPool;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.timesheet.EntityCreationException;
import com.liferay.timesheet.admin.BaseAdminBean;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentLocalServiceUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "departmentBean")
@ViewScoped
public class DepartmentBean extends BaseAdminBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(DepartmentBean.class);

	public String departmentName = null;

	public Department selectedDepartment = null;

	@Override
	public Object createEntity() throws EntityCreationException {
		Department department = null;

		try {
			ServiceContext serviceContext =
				TimesheetUtil.createServiceContext();

			department = DepartmentLocalServiceUtil.addDepartment(
				TimesheetUtil.getCurrentUserId(), getDepartmentName(),
				serviceContext);
		} catch (Exception e) {
			logger.error("Department creation is failed!");
		}

		return department;
	}

	@Override
	public String createEntityAction() {
		LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

		try {
			Department department = (Department)createEntity();

			if (logger.isDebugEnabled()) {
				logger.debug(
					"New department is created: " +
						department.getDepartmentName());
			}
		} catch (Exception e) {
			logger.error("Creation new department is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Creation new department is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	@Override
	public void doEditAction() {
		setAction(ACTION_EDIT);

		departmentName = selectedDepartment.getDepartmentName();
	}

	@Override
	public void doNewAction() {
		setActionValues(ACTION_NEW, StringPool.BLANK);
	}

	@Override
	public void onNodeSelect() {
		setActionValues(
			ACTION_SELECTED, selectedDepartment.getDepartmentName());
	}
	
	protected void setActionValues(
		String action, String departmentName) {

		setAction(action);

		this.departmentName = departmentName;
	}

	@Override
	public void onNodeUnSelect() {
		doNewAction();
	}
	
	@Override
	public Object updateEntity(Object entity) throws SystemException{
		DepartmentLocalServiceUtil.updateDepartment((Department)entity);

		return entity;
	}

	@Override
	public String updateEntityAction() {
		selectedDepartment.setDepartmentName(departmentName);

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			updateEntity(selectedDepartment);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Project is updated: " +
						selectedDepartment.getDepartmentName());
			}
		} catch (SystemException e) {
			logger.error("Department update is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				" Department update is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

}