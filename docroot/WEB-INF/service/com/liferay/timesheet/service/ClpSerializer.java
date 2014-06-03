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

package com.liferay.timesheet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.timesheet.model.DayClp;
import com.liferay.timesheet.model.ProjectClp;
import com.liferay.timesheet.model.TaskClp;
import com.liferay.timesheet.model.TaskSessionClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Istvan Sajtos, Zsolt Szabo
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"timesheet-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"timesheet-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "timesheet-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(DayClp.class.getName())) {
			return translateInputDay(oldModel);
		}

		if (oldModelClassName.equals(ProjectClp.class.getName())) {
			return translateInputProject(oldModel);
		}

		if (oldModelClassName.equals(TaskClp.class.getName())) {
			return translateInputTask(oldModel);
		}

		if (oldModelClassName.equals(TaskSessionClp.class.getName())) {
			return translateInputTaskSession(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputDay(BaseModel<?> oldModel) {
		DayClp oldClpModel = (DayClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDayRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputProject(BaseModel<?> oldModel) {
		ProjectClp oldClpModel = (ProjectClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getProjectRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTask(BaseModel<?> oldModel) {
		TaskClp oldClpModel = (TaskClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTaskRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputTaskSession(BaseModel<?> oldModel) {
		TaskSessionClp oldClpModel = (TaskSessionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getTaskSessionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals("com.liferay.timesheet.model.impl.DayImpl")) {
			return translateOutputDay(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.ProjectImpl")) {
			return translateOutputProject(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.TaskImpl")) {
			return translateOutputTask(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.timesheet.model.impl.TaskSessionImpl")) {
			return translateOutputTaskSession(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(PortalException.class.getName())) {
			return new PortalException();
		}

		if (className.equals(SystemException.class.getName())) {
			return new SystemException();
		}

		if (className.equals(
					"com.liferay.timesheet.TSEarliestStartTimeException")) {
			return new com.liferay.timesheet.TSEarliestStartTimeException();
		}

		if (className.equals("com.liferay.timesheet.TSEndTimeException")) {
			return new com.liferay.timesheet.TSEndTimeException();
		}

		if (className.equals("com.liferay.timesheet.TSEntityCreationException")) {
			return new com.liferay.timesheet.TSEntityCreationException();
		}

		if (className.equals("com.liferay.timesheet.TSFutureStartTimeException")) {
			return new com.liferay.timesheet.TSFutureStartTimeException();
		}

		if (className.equals(
					"com.liferay.timesheet.TSNoCurrentTaskSessionException")) {
			return new com.liferay.timesheet.TSNoCurrentTaskSessionException();
		}

		if (className.equals("com.liferay.timesheet.TSNoSelectedTaskException")) {
			return new com.liferay.timesheet.TSNoSelectedTaskException();
		}

		if (className.equals("com.liferay.timesheet.TSStartEndTimeException")) {
			return new com.liferay.timesheet.TSStartEndTimeException();
		}

		if (className.equals("com.liferay.timesheet.TSStartTimeException")) {
			return new com.liferay.timesheet.TSStartTimeException();
		}

		if (className.equals(
					"com.liferay.timesheet.TSTaskSessionCloseException")) {
			return new com.liferay.timesheet.TSTaskSessionCloseException();
		}

		if (className.equals(
					"com.liferay.timesheet.TSTaskSessionUpdateException")) {
			return new com.liferay.timesheet.TSTaskSessionUpdateException();
		}

		if (className.equals("com.liferay.timesheet.TSWorkDurationException")) {
			return new com.liferay.timesheet.TSWorkDurationException();
		}

		if (className.equals("com.liferay.timesheet.NoSuchDayException")) {
			return new com.liferay.timesheet.NoSuchDayException();
		}

		if (className.equals("com.liferay.timesheet.NoSuchProjectException")) {
			return new com.liferay.timesheet.NoSuchProjectException();
		}

		if (className.equals("com.liferay.timesheet.NoSuchTaskException")) {
			return new com.liferay.timesheet.NoSuchTaskException();
		}

		if (className.equals("com.liferay.timesheet.NoSuchTaskSessionException")) {
			return new com.liferay.timesheet.NoSuchTaskSessionException();
		}

		return throwable;
	}

	public static Object translateOutputDay(BaseModel<?> oldModel) {
		DayClp newModel = new DayClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDayRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputProject(BaseModel<?> oldModel) {
		ProjectClp newModel = new ProjectClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setProjectRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTask(BaseModel<?> oldModel) {
		TaskClp newModel = new TaskClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTaskRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputTaskSession(BaseModel<?> oldModel) {
		TaskSessionClp newModel = new TaskSessionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setTaskSessionRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}