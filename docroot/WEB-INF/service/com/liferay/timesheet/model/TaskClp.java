package com.liferay.timesheet.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.timesheet.service.ClpSerializer;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TaskClp extends BaseModelImpl<Task> implements Task {
    private long _taskId;
    private long _companyId;
    private long _userId;
    private String _userUuid;
    private Date _startDate;
    private Date _endDate;
    private String _taskName;
    private BaseModel<?> _taskRemoteModel;

    public TaskClp() {
    }

    public Class<?> getModelClass() {
        return Task.class;
    }

    public String getModelClassName() {
        return Task.class.getName();
    }

    public long getPrimaryKey() {
        return _taskId;
    }

    public void setPrimaryKey(long primaryKey) {
        setTaskId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_taskId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("taskId", getTaskId());
        attributes.put("companyId", getCompanyId());
        attributes.put("userId", getUserId());
        attributes.put("startDate", getStartDate());
        attributes.put("endDate", getEndDate());
        attributes.put("taskName", getTaskName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long taskId = (Long) attributes.get("taskId");

        if (taskId != null) {
            setTaskId(taskId);
        }

        Long companyId = (Long) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String taskName = (String) attributes.get("taskName");

        if (taskName != null) {
            setTaskName(taskName);
        }
    }

    public long getTaskId() {
        return _taskId;
    }

    public void setTaskId(long taskId) {
        _taskId = taskId;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setTaskId", long.class);

                method.invoke(_taskRemoteModel, taskId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(long companyId) {
        _companyId = companyId;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", long.class);

                method.invoke(_taskRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public long getUserId() {
        return _userId;
    }

    public void setUserId(long userId) {
        _userId = userId;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", long.class);

                method.invoke(_taskRemoteModel, userId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public String getUserUuid() throws SystemException {
        return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
    }

    public void setUserUuid(String userUuid) {
        _userUuid = userUuid;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_taskRemoteModel, startDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_taskRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public String getTaskName() {
        return _taskName;
    }

    public void setTaskName(String taskName) {
        _taskName = taskName;

        if (_taskRemoteModel != null) {
            try {
                Class<?> clazz = _taskRemoteModel.getClass();

                Method method = clazz.getMethod("setTaskName", String.class);

                method.invoke(_taskRemoteModel, taskName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getTaskRemoteModel() {
        return _taskRemoteModel;
    }

    public void setTaskRemoteModel(BaseModel<?> taskRemoteModel) {
        _taskRemoteModel = taskRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _taskRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_taskRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            TaskLocalServiceUtil.addTask(this);
        } else {
            TaskLocalServiceUtil.updateTask(this);
        }
    }

    @Override
    public Task toEscapedModel() {
        return (Task) ProxyUtil.newProxyInstance(Task.class.getClassLoader(),
            new Class[] { Task.class }, new AutoEscapeBeanHandler(this));
    }

    public Task toUnescapedModel() {
        return this;
    }

    @Override
    public Object clone() {
        TaskClp clone = new TaskClp();

        clone.setTaskId(getTaskId());
        clone.setCompanyId(getCompanyId());
        clone.setUserId(getUserId());
        clone.setStartDate(getStartDate());
        clone.setEndDate(getEndDate());
        clone.setTaskName(getTaskName());

        return clone;
    }

    public int compareTo(Task task) {
        long primaryKey = task.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof TaskClp)) {
            return false;
        }

        TaskClp task = (TaskClp) obj;

        long primaryKey = task.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{taskId=");
        sb.append(getTaskId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", taskName=");
        sb.append(getTaskName());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("com.liferay.timesheet.model.Task");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>taskId</column-name><column-value><![CDATA[");
        sb.append(getTaskId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>taskName</column-name><column-value><![CDATA[");
        sb.append(getTaskName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
