package com.liferay.timesheet.primefaces;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.timesheet.util.DateTimeUtil;
import com.liferay.timesheet.util.UserUtil;

import java.util.Date;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class UserTreeNode extends DefaultTreeNode {

	public UserTreeNode() {
		super("root", null);
	}

	public UserTreeNode(Organization organization, TreeNode parent) {
		super(organization, parent);

		this.className = ORGANIZATION_CLASS_NAME;
	}

	public UserTreeNode(User user, TreeNode parent) {
		super("user", user, parent);

		this.className = USER_CLASS_NAME;
	}

	@Override
	public Object getData() {
		return this;
	}

	public Organization getOrganization() {
		if (isOrganization()) {
			return (Organization)super.getData();
		}

		return null;
	}

	public User getUser() {
		if (isUser()) {
			return (User)super.getData();
		}

		return null;
	}

	public String getName() {
		if (USER_CLASS_NAME.equals(className)) {
			return ((User)super.getData()).getFullName();
		}
		else if (ORGANIZATION_CLASS_NAME.equals(className)) {
			return ((Organization)super.getData()).getName();
		}

		return StringPool.BLANK;
	}

	public String getDays() {
		if (USER_CLASS_NAME.equals(className)) {
			return String.valueOf(0);
		}

		return "";
	}

	public String getDuration() throws Exception {
		if (USER_CLASS_NAME.equals(className)) {
			return UserUtil.getFormattedDuration(
				DateTimeUtil.getCompanyId(), getUser().getUserId(),
				new Date(0), new Date());
		}

		return "";
	}

	public boolean isOrganization() {
		return ORGANIZATION_CLASS_NAME.equals(className);
	}

	public boolean isUser() {
		return USER_CLASS_NAME.equals(className);
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public boolean equals(Object object) {
		if ((object != null)) {
			String className = object.getClass().getName();

			if (className.equals(USER_CLASS_NAME)) {
				return (((User)super.getData()).getUserId() ==
					((User)object).getUserId());
			}

			if (className.equals(ORGANIZATION_CLASS_NAME)) {
				return (((Organization)super.getData()).getOrganizationId() ==
					((Organization)object).getOrganizationId());
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return getClassName() + "-" + super.toString();
	}

	private String className = null;

	public final static String USER_CLASS_NAME = User.class.getName();
	public final static String ORGANIZATION_CLASS_NAME =
		Organization.class.getName();

	private static final long serialVersionUID = -4492373011467607456L;

}