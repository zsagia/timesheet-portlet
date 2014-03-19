package com.liferay.timesheet.validator;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.timesheet.StartTimeException;
import com.liferay.timesheet.model.TaskSession;
import com.liferay.timesheet.service.TaskSessionLocalServiceUtil;
import com.liferay.timesheet.util.MessageUtil;
import com.liferay.timesheet.util.TimesheetUtil;

import java.text.ParseException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
* @author Istvan Sajtos
* @author Zsolt Szabo
*/

@FacesValidator("StartTimeValidator")
public class StartTimeValidator implements Validator {
	
	private static final Logger logger =
		LoggerFactory.getLogger(StartTimeValidator.class);

	@Override
	public void validate(
			FacesContext context, UIComponent component, Object value)
		throws ValidatorException {

		if (value != null) {
			long userId = TimesheetUtil.getCurrentUserId();

			try {
				Date todayWithoutTime = TimesheetUtil.getTodayWithoutTime();

				TaskSession lastTaskSession =
					TaskSessionLocalServiceUtil.getLastTaskSessionsByD_U(
						todayWithoutTime, userId);

				if (lastTaskSession != null) {

					DateTimeValidatorUtil.validateStartTime(
						lastTaskSession, (Date)value);
				}
			} catch (StartTimeException ste) {
				logger.error(
					"another_task_is_already_recorded_in_the_given_period",
					ste);

				FacesMessage facesMessage = MessageUtil.getFacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"start_session_validation_error",
					"another_task_is_already_recorded" +
						"_in_the_given_period");

				throw new ValidatorException(facesMessage);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

}