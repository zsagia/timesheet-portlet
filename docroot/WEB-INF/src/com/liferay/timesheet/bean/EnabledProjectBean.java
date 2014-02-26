package com.liferay.timesheet.bean;

import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.timesheet.util.ProjectTreeNode;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
* @author Zsolt Szabo
*/

@ManagedBean(name = "enabledProjectBean")
@ViewScoped
public class EnabledProjectBean extends ProjectBean {

	private static final long serialVersionUID = 1L;

	private static final Logger logger =
		LoggerFactory.getLogger(EnabledProjectBean.class);

	public EnabledProjectBean() {
		setRoot(new ProjectTreeNode(null, null));

		try {
			generateTreeNodes(true, getRoot());
		} catch (SystemException e) {
			logger.error("Tree generation is failed!");
		}
	}

}