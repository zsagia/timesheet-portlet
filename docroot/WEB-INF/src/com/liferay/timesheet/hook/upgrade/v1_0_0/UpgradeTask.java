package com.liferay.timesheet.hook.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.timesheet.service.TaskLocalServiceUtil;
import com.liferay.timesheet.util.TimeSheetConstants;

/**
 * @author Istvan Sajtos
 */
public class UpgradeTask extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		ServiceContext serviceContext = new ServiceContext();

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		serviceContext.setScopeGroupId(group.getGroupId());

		long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);

		TaskLocalServiceUtil.addTask(defaultUserId, "Lunch Break", 0, null,
			TimeSheetConstants.TASK_LUNCH, serviceContext);
		TaskLocalServiceUtil.addTask(defaultUserId, "10min Break", 0, null,
			TimeSheetConstants.TASK_BREAK, serviceContext);
	}

}