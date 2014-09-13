package com.liferay.timesheet.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Istvan Sajtos
 */
public class PortletPropsValues {

	public static final String RESTRICTIONS_BASETIME_END = GetterUtil.getString(
		PortletProps.get(PortletPropsKeys.RESTRICTIONS_BASETIME_END));

	public static final String RESTRICTIONS_BASETIME_START =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.RESTRICTIONS_BASETIME_START));

	public static final String RESTRICTIONS_BREAKTIME_DURATION =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.RESTRICTIONS_BREAKTIME_DURATION));

	public static final String RESTRICTIONS_ENDTIME_LATEST =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.RESTRICTIONS_ENDTIME_LATEST));

	public static final String RESTRICTIONS_LUNCHTIME_MIN_DURATION =
		GetterUtil.getString(PortletProps.get(
			PortletPropsKeys.RESTRICTIONS_LUNCHTIME_MIN_DURATION));

	public static final String RESTRICTIONS_STARTTIME_EARLIEST =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.RESTRICTIONS_STARTTIME_EARLIEST));

	public static final String RESTRICTIONS_WORKDURATIOIN_MAX =
		GetterUtil.getString(
			PortletProps.get(PortletPropsKeys.RESTRICTIONS_WORKDURATIOIN_MAX));

}