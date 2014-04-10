package com.liferay.timesheet.bean.view;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.faces.util.lang.StringPool;
import com.liferay.timesheet.bean.model.DepartmentModelBean;
import com.liferay.timesheet.service.DepartmentServiceUtil;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DepartmentViewBean
	extends AbstractEntityViewBean implements Serializable {

	@PostConstruct
	public void init() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			setDepartmentList(DepartmentServiceUtil.getDepartments(
				liferayFacesContext.getCompanyId()));
		} catch (Exception e) {
			
		}

		setAction(ACTION_NEW);
	}

	@Override
	public void doEditAction(Object bean) {
		DepartmentModelBean departmentModelBean = (DepartmentModelBean)bean;

		setAction(ACTION_EDIT);

		departmentModelBean.setDepartmentName(
			getSelectedDepartment().getDepartmentName());
	}

	@Override
	public void doNewAction(Object bean) {
		setActionValues(
			ACTION_NEW, StringPool.BLANK, (DepartmentModelBean)bean);
	}

	@Override
	public void onNodeSelect(Object bean) {
		DepartmentModelBean departmentModelBean = (DepartmentModelBean)bean;

		setActionValues(
			ACTION_SELECTED, getSelectedDepartment().getDepartmentName(),
			departmentModelBean);
	}

	protected void setActionValues(
		String action, String departmentName,
		DepartmentModelBean departmentModelBean) {

		setAction(action);

		departmentModelBean.setDepartmentName(departmentName);
	}

	@Override
	public void onNodeUnSelect(Object bean) {
		doNewAction(bean);
	}

	protected DepartmentModelBean getDepartmentModelBean() {
		return null;
	}

	private static final long serialVersionUID = -970908785010755050L;

}