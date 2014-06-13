package com.liferay.timesheet.validator;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.timesheet.TSEarliestStartTimeException;
import com.liferay.timesheet.TSEndTimeException;
import com.liferay.timesheet.TSFutureStartTimeException;
import com.liferay.timesheet.TSNoCurrentTaskSessionException;
import com.liferay.timesheet.TSStartTimeException;
import com.liferay.timesheet.TSWorkDurationException;
import com.liferay.timesheet.util.MessageUtil;
import com.liferay.timesheet.util.PortletPropsValues;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public abstract class AbstractValidator implements Validator {

	protected abstract void doValidate(
			FacesContext context, UIComponent component, Object value)
		throws Exception;

	@Override
	public void validate(
			FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		FacesMessage facesMessage = null;

		try {
			doValidate(context, component, value);
		} catch (Exception e) {
			if (e instanceof TSEndTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"another_task_is_already_recorded_in_the_given_period");
			}
			else if (e instanceof TSEarliestStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"the_given_time_does_not_fit_the_time_frame_defined_for_"
						+ "work_start "
						+ PortletPropsValues.RESTRICTIONS_STARTTIME_EARLIEST);
			}
			else if (e instanceof TSNoCurrentTaskSessionException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"there_is_not_current_task_session");
			}
			else if (e instanceof TSStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"another_task_is_already_recorded_in_the_given_period");
			}
			else if (e instanceof TSFutureStartTimeException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"task_is_not_startable_in_the_future");
			}
			else if (e instanceof TSWorkDurationException) {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"end_session_validation_error",
					"work_time_duration_exception");
			}
			else {
				facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR, "validation_error",
					"validation_exception");
				logger.error(e);
			}
		}

		if (facesMessage != null) {
			throw new ValidatorException(facesMessage);
		}
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

}