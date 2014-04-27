package com.liferay.timesheet.bean.view;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.service.GroupServiceUtil;
import com.liferay.timesheet.bean.model.ProjectModelBean;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.util.ProjectTreeNode;
import com.liferay.timesheet.util.TimesheetUtil;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProjectViewBean
	extends AbstractEntityViewBean implements Serializable {

	public ProjectViewBean() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() throws Exception {
		try {
			setOwnerGroupList(GroupServiceUtil.getGroups(
				TimesheetUtil.getCompanyId(), 0, false));

				if (getOwnerGroupList().size() > 0) {
					setSelectedOwnerGroup(getOwnerGroupList().get(0));

					setRoot(new ProjectTreeNode(null, null));

					generateTreeNodes(
						false, getSelectedOwnerGroup().getGroupId(),
						getRoot());
				}
		} catch (Exception e) {
			LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

			liferayFacesContext.addMessage(
				"Warning", FacesMessage.SEVERITY_WARN,
				"You need to create an Organization for this function!");

			throw e;
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

	public void onOwnerGroupSelect(Object bean) {
		setRoot(new ProjectTreeNode(null, null));

		try {
			generateTreeNodes(
				false, getSelectedOwnerGroup().getGroupId(), getRoot());
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