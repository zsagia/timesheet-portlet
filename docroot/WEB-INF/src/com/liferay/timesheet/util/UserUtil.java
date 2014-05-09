package com.liferay.timesheet.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserUtil {

	public static boolean isLeader(long companyId, long organizationId)
		throws SystemException, PortalException {

		List<Organization> organizations =
			OrganizationUtil.getAvailableOrganizations(
				companyId, organizationId);

		if (organizations.size() > 0) {
			return true;
		}
	
		return false;
	}

	public static boolean hasOrganizationRole() {
		return false;
	}

	public static boolean isMemberOfTeam() {
		return false;
	}

	public static long getDuration(
			long companyId, long userId, Date date, Date now)
		throws Exception {

		List<TaskSession> taskSessions =
			TaskSessionLocalServiceUtil.getTaskSessionsByC_U_I(
				companyId, userId, date, now);

		return TimeCalculatorUtil.summerizeTime(taskSessions);
	}

	public static String getFormattedDuration(
			long companyId, long userId, Date date, Date now)
		throws Exception {

		return TimeCalculatorUtil.getStringFromTime(
			getDuration(companyId, userId, date, now));
	}

	public static List<User> getOrganizationUsers(
			long companyId, long organizationId)
		throws Exception {

		List<User> users = new ArrayList<User>();

		List<Organization> organizations =
			OrganizationUtil.getOrganizations(companyId, organizationId);

		for (Organization organization: organizations) {
			users.addAll(
				UserLocalServiceUtil.getOrganizationUsers(
					organization.getOrganizationId()));

			users.addAll(
				getOrganizationUsers(
					companyId, organization.getOrganizationId()));
		}

		return users;
	}

}