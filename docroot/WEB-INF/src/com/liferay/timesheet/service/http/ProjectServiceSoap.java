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

package com.liferay.timesheet.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.timesheet.service.ProjectServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.timesheet.service.ProjectServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.timesheet.model.ProjectSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.timesheet.model.Project}, that is translated to a
 * {@link com.liferay.timesheet.model.ProjectSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Istvan Sajtos, Zsolt Szabo
 * @see ProjectServiceHttp
 * @see com.liferay.timesheet.model.ProjectSoap
 * @see com.liferay.timesheet.service.ProjectServiceUtil
 * @generated
 */
public class ProjectServiceSoap {
	public static com.liferay.timesheet.model.ProjectSoap addProject(
		long userId, long departmentId, boolean enabled, long parentProjectId,
		java.lang.String projectName, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.timesheet.model.Project returnValue = ProjectServiceUtil.addProject(userId,
					departmentId, enabled, parentProjectId, projectName,
					description, serviceContext);

			return com.liferay.timesheet.model.ProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.timesheet.model.ProjectSoap getProject(
		long projectId) throws RemoteException {
		try {
			com.liferay.timesheet.model.Project returnValue = ProjectServiceUtil.getProject(projectId);

			return com.liferay.timesheet.model.ProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.timesheet.model.ProjectSoap[] getProjects(
		long companyId) throws RemoteException {
		try {
			java.util.List<com.liferay.timesheet.model.Project> returnValue = ProjectServiceUtil.getProjects(companyId);

			return com.liferay.timesheet.model.ProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.timesheet.model.ProjectSoap[] getProjectsByO_PP(
		long organizationId, long parentProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.timesheet.model.Project> returnValue = ProjectServiceUtil.getProjectsByO_PP(organizationId,
					parentProjectId);

			return com.liferay.timesheet.model.ProjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.timesheet.model.ProjectSoap updateProject(
		com.liferay.timesheet.model.ProjectSoap project)
		throws RemoteException {
		try {
			com.liferay.timesheet.model.Project returnValue = ProjectServiceUtil.updateProject(com.liferay.timesheet.model.impl.ProjectModelImpl.toModel(
						project));

			return com.liferay.timesheet.model.ProjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ProjectServiceSoap.class);
}