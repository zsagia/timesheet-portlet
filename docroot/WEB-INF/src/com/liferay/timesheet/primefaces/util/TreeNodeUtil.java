package com.liferay.timesheet.primefaces.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.model.Project;
import com.liferay.timesheet.primefaces.ProjectTreeNode;
import com.liferay.timesheet.primefaces.UserTreeNode;
import com.liferay.timesheet.service.ProjectServiceUtil;
import com.liferay.timesheet.util.OrganizationUtil;
import com.liferay.timesheet.util.TimeSheetUtil;

import java.util.List;

import org.primefaces.model.TreeNode;
public class TreeNodeUtil {

	public static void generateProjectTreeNodes(
			boolean checkEnabled, TreeNode parentNode)
		throws PortalException, SystemException {

		Project projectNode = ((ProjectTreeNode)parentNode).getProject();

		long projectId = projectNode != null ? projectNode.getProjectId() : 0;

		List<Project> projects = ProjectServiceUtil.getProjects(projectId);

		for (Project project : projects) {
			boolean enabled = true;

			if (checkEnabled && !project.getEnabled()) {
				enabled = false;
			}

			if (enabled) {
				TreeNode treeNode = new ProjectTreeNode(
					project.getProjectName(), parentNode);

				((ProjectTreeNode)treeNode).setProject(project);

				generateProjectTreeNodes(checkEnabled, treeNode);
			}
		}
	}

	public static void generateUserTreeNodes(TreeNode parentNode)
		throws Exception {

		Organization organizationNode =
			((UserTreeNode)parentNode).getOrganization();

		long organizationId =
			organizationNode != null ? organizationNode.getOrganizationId() : 0;

		List<Organization> organizations =
			OrganizationUtil.getAvailableOrganizations(
				TimeSheetUtil.getCompanyId(), organizationId);

		for (Organization organization : organizations) {
			TreeNode treeNode = new UserTreeNode(organization, parentNode);

			generateUserTreeNodes(treeNode);
		}

		List<User> users = UserLocalServiceUtil.getOrganizationUsers(
			organizationId);

		for (User user : users) {
			new UserTreeNode(user, parentNode);
		}
	}
}