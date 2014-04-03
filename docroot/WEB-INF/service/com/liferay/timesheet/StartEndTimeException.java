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

package com.liferay.timesheet;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Istvan Sajtos, Zsolt Szabo
 */
public class StartEndTimeException extends PortalException {

	public StartEndTimeException() {
		super();
	}

	public StartEndTimeException(String msg) {
		super(msg);
	}

	public StartEndTimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public StartEndTimeException(Throwable cause) {
		super(cause);
	}

}