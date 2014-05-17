package com.liferay.timesheet.converter;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.model.Task;
import com.liferay.timesheet.service.TaskLocalServiceUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("TaskConverter")
public class TaskConverter implements Converter {

	private static final Logger logger =
		LoggerFactory.getLogger(TaskConverter.class);

	@Override
	public Object getAsObject(
		FacesContext context, UIComponent component, String value) {

		if (value == null) {
			return value;
		}

		Task task = null;

		try {
			task = TaskLocalServiceUtil.getTask(
				Long.parseLong(value));
		} catch (Exception e) {
			logger.error("Conversion error", e);
		}

		return task;
	}

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object value) {

		if ((value == null) || value.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		return String.valueOf(((Task)value).getTaskId());
	}

}