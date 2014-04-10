package com.liferay.timesheet.bean.view;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.timesheet.bean.model.ProjectModelBean;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.service.DepartmentServiceUtil;
import com.liferay.timesheet.util.ProjectTreeNode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProjectViewBean
	extends AbstractEntityViewBean implements Serializable {

	@PostConstruct
	public void init() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		try {
			setDepartmentList(DepartmentServiceUtil.getDepartments(
					liferayFacesContext.getCompanyId()));

				if (getDepartmentList().size() > 0) {
					setSelectedDepartment(getDepartmentList().get(0));

					setRoot(new ProjectTreeNode(null, null));

					generateTreeNodes(
						false, getSelectedDepartment().getDepartmentId(),
						getRoot());
				}
		} catch (Exception e) {
			List<Department> departmnetList = Collections.emptyList();

			setDepartmentList(departmnetList);
		}
	}

	@Override
	public void doEditAction(Object bean) {
		ProjectModelBean projectModelBean = (ProjectModelBean)bean;

		setAction(ACTION_EDIT);

		Project project =
			((ProjectTreeNode)getSelectedProjectNode()).getProject();

		projectModelBean.setProjectName(project.getProjectName());
	}

	@Override
	public void doNewAction(Object bean) {
		setActionValues(ACTION_NEW, false, null, null, (ProjectModelBean)bean);
	}

	public void onDepartmentSelect(Object bean) {
		setRoot(new ProjectTreeNode(null, null));

		try {
			generateTreeNodes(
				false, getSelectedDepartment().getDepartmentId(), getRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setActionValues(ACTION_NEW, false, null, null, (ProjectModelBean)bean);

		setSelectedProjectNode(null);
	}

	@Override
	public void onNodeSelect(Object bean) {
		ProjectModelBean projectModelBean = (ProjectModelBean)bean;

		Project project =
			((ProjectTreeNode)getSelectedProjectNode()).getProject();

		setActionValues(
			ACTION_SELECTED, project.getEnabled(), project.getProjectName(),
			project.getDescription(), projectModelBean);
	}

	@Override
	public void onNodeUnSelect(Object bean) {
		doNewAction(bean);
	}

	protected void setActionValues(
		String action, boolean enabled, String projectName,
		String description, ProjectModelBean projectModelBean) {

		setAction(action);

		projectModelBean.setDescription(description);
		projectModelBean.setEnabled(enabled);
		projectModelBean.setProjectName(projectName);
	}

	private static final long serialVersionUID = -8813535974762467309L;

}