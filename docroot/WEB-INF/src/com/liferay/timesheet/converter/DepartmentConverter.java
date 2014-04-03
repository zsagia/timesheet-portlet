package com.liferay.timesheet.converter;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.model.Department;
import com.liferay.timesheet.service.DepartmentLocalServiceUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("DepartmentConverter")
public class DepartmentConverter implements Converter {

	private static final Logger logger =
		LoggerFactory.getLogger(DepartmentConverter.class);

	@Override
	public Object getAsObject(
		FacesContext context, UIComponent component, String value) {

		if (value == null) {
			return value;
		}

		Department department = null;

		try {
			department = DepartmentLocalServiceUtil.getDepartment(
				Long.parseLong(value));
		} catch (Exception e) {
			logger.error("Conversion error", e);
		}

		return department;
	}

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object value) {

		if ((value == null) || value.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		return String.valueOf(((Department)value).getDepartmentId());
	}

}