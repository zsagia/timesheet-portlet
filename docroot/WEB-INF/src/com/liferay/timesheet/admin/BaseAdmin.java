package com.liferay.timesheet.admin;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.EntityCreationException;

public interface BaseAdmin {

	public static final String ACTION_NEW = "new";
	public static final String ACTION_EDIT = "edit";
	public static final String ACTION_SELECTED = "selected";

	public Object createEntity() throws EntityCreationException;

	public String createEntityAction();

	public void doEditAction();

	public void doNewAction();

	public void onNodeSelect();

	public void onNodeUnSelect();

	public Object updateEntity(Object entity)
		throws PortalException, SystemException;

	public String updateEntityAction();
}
