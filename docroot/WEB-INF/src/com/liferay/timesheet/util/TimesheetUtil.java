package com.liferay.timesheet.util;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

/**
* @author Zsolt Szabo
*/

public class TimeSheetUtil {

	public static ServiceContext createServiceContext() {
		LiferayFacesContext liferayFacesContext =
				LiferayFacesContext.getInstance();

		ThemeDisplay themeDisplay = liferayFacesContext.getThemeDisplay();
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setScopeGroupId(themeDisplay.getScopeGroupId());

		return serviceContext;
	}

	public static long getCompanyId()
		throws PortalException, SystemException {

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getCompanyId();
	}

	public static User getCurrentUser()
		throws PortalException, SystemException {

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getUser();
	}

	public static long getCurrentUserId() {
		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance();

		return liferayFacesContext.getUserId();
	}

}