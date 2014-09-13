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

package com.liferay.timesheet.service.base;

import com.liferay.timesheet.service.TaskLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Istvan Sajtos, Zsolt Szabo
 * @generated
 */
public class TaskLocalServiceClpInvoker {
	public TaskLocalServiceClpInvoker() {
		_methodName0 = "addTask";

		_methodParameterTypes0 = new String[] { "com.liferay.timesheet.model.Task" };

		_methodName1 = "createTask";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTask";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTask";

		_methodParameterTypes3 = new String[] { "com.liferay.timesheet.model.Task" };

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchTask";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTask";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getTasks";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getTasksCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateTask";

		_methodParameterTypes15 = new String[] {
				"com.liferay.timesheet.model.Task"
			};

		_methodName52 = "getBeanIdentifier";

		_methodParameterTypes52 = new String[] {  };

		_methodName53 = "setBeanIdentifier";

		_methodParameterTypes53 = new String[] { "java.lang.String" };

		_methodName58 = "addTask";

		_methodParameterTypes58 = new String[] {
				"long", "long", "java.lang.String", "long", "java.lang.String",
				"int", "com.liferay.portal.service.ServiceContext"
			};

		_methodName59 = "addTask";

		_methodParameterTypes59 = new String[] {
				"long", "long", "java.lang.String", "long", "java.lang.String",
				"int", "long[][]", "long[][]",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName60 = "addTaskResources";

		_methodParameterTypes60 = new String[] {
				"com.liferay.timesheet.model.Task", "boolean", "boolean"
			};

		_methodName61 = "addTaskResources";

		_methodParameterTypes61 = new String[] {
				"com.liferay.timesheet.model.Task", "java.lang.String[][]",
				"java.lang.String[][]"
			};

		_methodName62 = "deleteTask";

		_methodParameterTypes62 = new String[] { "long" };

		_methodName63 = "deleteTask";

		_methodParameterTypes63 = new String[] {
				"com.liferay.timesheet.model.Task"
			};

		_methodName64 = "getTaskByType";

		_methodParameterTypes64 = new String[] { "int" };

		_methodName65 = "getTasksByC_G_TT";

		_methodParameterTypes65 = new String[] { "long", "long", "int" };

		_methodName66 = "getTasksByC_G_U";

		_methodParameterTypes66 = new String[] { "long", "long", "long" };

		_methodName67 = "getTasksByC_G_U_TT";

		_methodParameterTypes67 = new String[] { "long", "long", "long", "int" };

		_methodName68 = "getTasksByU_D";

		_methodParameterTypes68 = new String[] { "long", "java.util.Date" };

		_methodName69 = "getTaskByU_TT";

		_methodParameterTypes69 = new String[] { "long", "int" };

		_methodName70 = "getTasksByC_U_I";

		_methodParameterTypes70 = new String[] {
				"long", "long", "java.util.Date", "java.util.Date"
			};

		_methodName71 = "updateTask";

		_methodParameterTypes71 = new String[] {
				"com.liferay.timesheet.model.Task", "long[][]", "long[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TaskLocalServiceUtil.addTask((com.liferay.timesheet.model.Task)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TaskLocalServiceUtil.createTask(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TaskLocalServiceUtil.deleteTask(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TaskLocalServiceUtil.deleteTask((com.liferay.timesheet.model.Task)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TaskLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TaskLocalServiceUtil.fetchTask(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TaskLocalServiceUtil.getTask(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TaskLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TaskLocalServiceUtil.getTasks(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TaskLocalServiceUtil.updateTask((com.liferay.timesheet.model.Task)arguments[0]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return TaskLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			TaskLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return TaskLocalServiceUtil.addTask(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return TaskLocalServiceUtil.addTask(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(), (long[])arguments[6],
				(long[])arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			TaskLocalServiceUtil.addTaskResources((com.liferay.timesheet.model.Task)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			TaskLocalServiceUtil.addTaskResources((com.liferay.timesheet.model.Task)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.String[])arguments[2]);

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return TaskLocalServiceUtil.deleteTask(((Long)arguments[0]).longValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return TaskLocalServiceUtil.deleteTask((com.liferay.timesheet.model.Task)arguments[0]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return TaskLocalServiceUtil.getTaskByType(((Integer)arguments[0]).intValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksByC_G_TT(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksByC_G_U(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksByC_G_U_TT(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksByU_D(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return TaskLocalServiceUtil.getTaskByU_TT(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return TaskLocalServiceUtil.getTasksByC_U_I(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				(java.util.Date)arguments[3]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return TaskLocalServiceUtil.updateTask((com.liferay.timesheet.model.Task)arguments[0],
				(long[])arguments[1], (long[])arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
}