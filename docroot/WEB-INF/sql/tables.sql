create table timesheet_Department (
	departmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	departmentName VARCHAR(75) null
);

create table timesheet_Project (
	uuid_ VARCHAR(75) null,
	projectId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	departmentId LONG,
	description VARCHAR(75) null,
	enabled BOOLEAN,
	parentProjectId LONG,
	projectName VARCHAR(75) null
);

create table timesheet_Task (
	taskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	description VARCHAR(75) null,
	projectId LONG,
	taskName VARCHAR(75) null,
	type_ INTEGER
);

create table timesheet_TaskSession (
	taskSessionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	description VARCHAR(75) null,
	endTime DATE null,
	startTime DATE null,
	taskId LONG
);