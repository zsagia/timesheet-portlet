package com.liferay.timesheet.util;

import com.liferay.faces.portal.context.LiferayFacesContext;

import java.text.MessageFormat;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class MessageUtil {

	public static FacesMessage getMessage(
		String bundleName, String resourceId, Object[] params) {

		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();

		String summary = getString(
			appBundle, bundleName, resourceId, locale, loader, params);

		if (summary == null) {
			summary = "???" + resourceId + "???";
		}

		String detail = getString(appBundle, bundleName, 
			resourceId + "_detail", locale, loader, params);

		return new FacesMessage(summary, detail);
	}

	public static String getString(
		String bundle, String resourceId, Object[] params) {

		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();

		return getString(appBundle, bundle, resourceId, locale, loader, params);
	}

	public static String getString(String bundle1, String bundle2, 
		String resourceId, Locale locale, ClassLoader loader, Object[] params) {

		ResourceBundle bundle;
		String resource = null;

		if (bundle1 != null) {
			bundle = ResourceBundle.getBundle(bundle1, locale, loader);
			if (bundle != null) {
				try {
					resource = bundle.getString(resourceId);
				} catch (MissingResourceException e) {
				}
			}
		}

		if (resource == null) {
			return null;
		}

		if (params == null) {
			return resource;
		}

		MessageFormat formatter = new MessageFormat(resource, locale);

		return formatter.format(params);
	}

	public static Locale getLocale(FacesContext context) {
		UIViewRoot viewRoot = context.getViewRoot();
		Locale locale = null;

		if (viewRoot != null) {
			locale = viewRoot.getLocale();
		}

		if (locale == null) {
			locale = Locale.getDefault();
		}

		return locale;
	}

	public static ClassLoader getClassLoader() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}

		return loader;
	}

	public static FacesMessage getFacesMessage(
		Severity severity, String errorMessageVariable,
		String errorMessageDetailVariable) {

		LiferayFacesContext liferayFacesContext =
			LiferayFacesContext.getInstance(); 

		String errorMessage = liferayFacesContext.getMessage(
			errorMessageVariable);
		String errorMessageDetail = liferayFacesContext.getMessage(
			errorMessageDetailVariable);

		FacesMessage facesMessage = new FacesMessage(
			severity, errorMessage, errorMessageDetail);

		return facesMessage;
	}

}