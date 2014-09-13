package com.liferay.timesheet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.permission.OrganizationPermissionUtil;

import java.util.Iterator;
import java.util.List;
public class OrganizationUtil {

	public static List<Organization> getAvailableOrganizations(
			long companyId, long organizationId)
		throws PortalException, SystemException {

		List<Organization> organizationList =
			OrganizationLocalServiceUtil.getOrganizations(
				TimeSheetUtil.getCompanyId(), organizationId);

		if (organizationList.size() == 0) {
			return organizationList;
		}

		List<Organization> filteredList =
			filterOrganizationsByPermissionChecking(
				organizationList, ActionKeys.VIEW);

		if (filteredList.size() > 0) {
			return filteredList;
		}

		for (Organization organization : organizationList) {
			filteredList.addAll(
				getAvailableOrganizations(
					companyId, organization.getOrganizationId()));
		}

		return filteredList;
	}

	public static List<Organization> getOrganizations(
			long companyId, long organizationId)
		throws Exception {

		List<Organization> organizationList =
			OrganizationLocalServiceUtil.getOrganizations(
				TimeSheetUtil.getCompanyId(), organizationId);

		organizationList = filterOrganizationsByPermissionChecking(
			organizationList, ActionKeys.VIEW);

		return organizationList;
	}

	private static List<Organization> filterOrganizationsByPermissionChecking(
			List<Organization> organizationList, String action)
		throws PortalException, SystemException {

		organizationList = ListUtil.copy(organizationList);

		Iterator<Organization> iterator = organizationList.iterator();

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		while (iterator.hasNext()) {
			Organization organization = iterator.next();

			if (!OrganizationPermissionUtil.contains(
					permissionChecker, organization.getOrganizationId(),
					action)) {

				iterator.remove();
			}
		}

		return organizationList;
	}

}