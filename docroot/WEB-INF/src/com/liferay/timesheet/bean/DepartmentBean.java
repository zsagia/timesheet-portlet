package com.liferay.timesheet.bean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.lang.StringPool;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.admin.BaseAdminBean;
import com.liferay.timesheet.model.Department;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "departmentBean")
@ViewScoped
public class DepartmentBean extends BaseAdminBean implements Serializable {

	public DepartmentBean() {
		departments = super.getDepartments();

		if (departments.size() > 0) {
			selectedDepartment = departments.get(0);
		}
	}

	public String createDepartmentAction() {
		LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

		try {
			Department department = departmentModelBean.createDepartment();

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

		departmentModelBean.setDepartmentName(
			selectedDepartment.getDepartmentName());
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

		departmentModelBean.setDepartmentName(departmentName);
	}

	@Override
	public void onNodeUnSelect() {
		doNewAction();
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String updateDepartmentAction() {
		selectedDepartment.setDepartmentName(
			departmentModelBean.departmentName);

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			departmentModelBean.updateDepartment(selectedDepartment);

			if (logger.isDebugEnabled()) {
				logger.debug(
					"Project is updated: " +
						selectedDepartment.getDepartmentName());
			}
		} catch (Exception e) {
			logger.error("Department update is failed!");

			liferayFacesContext.addGlobalErrorMessage(
				"Department update is failed!");
		}

		return "/views/admin/view.xhtml";
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public Department getSelectedDepartment() {
		return selectedDepartment;
	}

	public void setSelectedDepartment(Department selectedDepartment) {
		this.selectedDepartment = selectedDepartment;
	}

	public DepartmentModelBean getDepartmentModelBean() {
		return departmentModelBean;
	}

	public void setDepartmentModelBean(DepartmentModelBean departmentModelBean) {
		this.departmentModelBean = departmentModelBean;
	}

	public Department selectedDepartment = null;

	public List<Department> departments = null;

	@ManagedProperty(name = "departmentModelBean",
		value = "#{departmentModelBean}")
	private DepartmentModelBean departmentModelBean;

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(DepartmentBean.class);

}