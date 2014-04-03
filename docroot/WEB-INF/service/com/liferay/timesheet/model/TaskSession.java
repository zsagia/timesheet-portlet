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
 * The extended model interface for the TaskSession service. Represents a row in the &quot;timesheet_TaskSession&quot; database table, with each column mapped to a property of this class.
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see TaskSessionModel
 * @see com.liferay.timesheet.model.impl.TaskSessionImpl
 * @see com.liferay.timesheet.model.impl.TaskSessionModelImpl
 * @generated
 */
public interface TaskSession extends TaskSessionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.timesheet.model.impl.TaskSessionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.timesheet.model.Task getTask()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public long getDuration() throws java.lang.Exception;

	public long getDuration(java.util.Date endTimeForOpenTask)
		throws java.lang.Exception;
}