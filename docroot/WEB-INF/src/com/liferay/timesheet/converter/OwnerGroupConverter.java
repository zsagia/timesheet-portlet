package com.liferay.timesheet.converter;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupServiceUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter("OwnerGroupConverter")
public class OwnerGroupConverter implements Converter {

	@Override
	public Object getAsObject(
		FacesContext context, UIComponent component, String value) {

		if (value == null) {
			return value;
		}

		Group ownerGroup = null;

		try {
			ownerGroup = GroupServiceUtil.getGroup(Long.parseLong(value));
		} catch (Exception e) {
			logger.error("Conversion error", e);
		}

		return ownerGroup;
	}

	@Override
	public String getAsString(
		FacesContext context, UIComponent component, Object value) {

		if ((value == null) || value.equals(StringPool.BLANK)) {
			return StringPool.BLANK;
		}

		return String.valueOf(((Group)value).getGroupId());
	}

	private static final Logger logger = LoggerFactory.getLogger(
		OwnerGroupConverter.class);

}