package com.liferay.timesheet.bean.admin;

import com.liferay.timesheet.bean.AbstractEntityViewBean;
import com.liferay.timesheet.bean.model.ProjectModelBean;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.util.TreeNodeUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
@ManagedBean
@ViewScoped
public class ProjectViewBean extends AbstractEntityViewBean {

	public ProjectViewBean() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
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

	public void init() throws Exception {
		setRoot(new ProjectTreeNode(null, null));

		TreeNodeUtil.generateProjectTreeNodes(false, getRoot());
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

	public void onOwnerGroupSelect(Object bean) {
		setRoot(new ProjectTreeNode(null, null));

		try {
			TreeNodeUtil.generateProjectTreeNodes(false, getRoot());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setActionValues(ACTION_NEW, false, null, null, (ProjectModelBean)bean);

		setSelectedProjectNode(null);
	}

	protected void setActionValues(
		String action, boolean enabled, String projectName, String description,
		ProjectModelBean projectModelBean) {

		setAction(action);

		projectModelBean.setDescription(description);
		projectModelBean.setEnabled(enabled);
		projectModelBean.setProjectName(projectName);
	}

	private static final long serialVersionUID = -8813535974762467309L;

}