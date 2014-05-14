/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.timesheet.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Task service. Represents a row in the &quot;timesheet_Task&quot; database table, with each column mapped to a property of this class.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskModel
 * @see com.liferay.timesheet.model.impl.TaskImpl
 * @see com.liferay.timesheet.model.impl.TaskModelImpl
 * @generated
 */
public interface Task extends TaskModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.timesheet.model.impl.TaskImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public long getDuration(long userId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception;

	public long getDuration(long userId, java.util.Date date1,
		java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception;

	public java.lang.String getFormattedDuration(long userId,
		java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception;

	public java.lang.String getFormattedDuration(long userId,
		java.util.Date date1, java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException,
			java.lang.Exception;

	public com.liferay.timesheet.model.Project getProject();

	public java.lang.String getProjectName();

	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionList(
		long userId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.timesheet.model.TaskSession> getTaskSessionList(
		long userId, java.util.Date date1, java.util.Date date2)
		throws com.liferay.portal.kernel.exception.SystemException;

	public boolean isEditable();
}