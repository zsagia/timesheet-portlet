/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.timesheet.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Istvan Sajtos
 */
public class TaskSessionFinderUtil {
	public static java.util.List<com.liferay.timesheet.model.TaskSession> findByD_U(
		java.util.Date date, long userId, int start, int end) {
		return getFinder().findByD_U(date, userId, start, end);
	}

	public static TaskSessionFinder getFinder() {
		if (_finder == null) {
			_finder = (TaskSessionFinder)PortletBeanLocatorUtil.locate(com.liferay.timesheet.service.ClpSerializer.getServletContextName(),
					TaskSessionFinder.class.getName());

			ReferenceRegistry.registerReference(TaskSessionFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(TaskSessionFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(TaskSessionFinderUtil.class,
			"_finder");
	}

	private static TaskSessionFinder _finder;
}