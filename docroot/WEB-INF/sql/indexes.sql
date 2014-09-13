create index IX_20358DE2 on timesheet_Day (companyId, type_);
create index IX_C0AEF3C0 on timesheet_Day (type_);

create index IX_1B6563E3 on timesheet_Department (companyId);

create index IX_8BA46E73 on timesheet_Project (departmentId);
create index IX_62216527 on timesheet_Project (departmentId, parentProjectId);
create index IX_81C4F2D4 on timesheet_Project (organizationId);
create index IX_23E00EE6 on timesheet_Project (organizationId, parentProjectId);
create index IX_5DA849AD on timesheet_Project (ownerGroupId);
create index IX_CBFC252D on timesheet_Project (ownerGroupId, parentProjectId);
create index IX_F1CF7648 on timesheet_Project (parentProjectId);
create index IX_F508FA42 on timesheet_Project (uuid_);
create index IX_6ABD6CA6 on timesheet_Project (uuid_, companyId);
create unique index IX_89E4E4A8 on timesheet_Project (uuid_, groupId);

create index IX_F3AE5F87 on timesheet_Task (companyId, creatorId);
create index IX_82876CED on timesheet_Task (companyId, groupId, taskType);
create index IX_3B674AA7 on timesheet_Task (companyId, groupId, userId, taskType);
create index IX_3F299F0A on timesheet_Task (companyId, userId);
create index IX_61D96719 on timesheet_Task (departmentId);
create index IX_7F881152 on timesheet_Task (groupId);
create index IX_3FF535A9 on timesheet_Task (groupId, companyId, taskType);
create index IX_6A90776C on timesheet_Task (projectId);
create index IX_D0DE61DB on timesheet_Task (taskName, creatorId);
create index IX_ED5CDD36 on timesheet_Task (taskName, userId);
create index IX_6212E5EB on timesheet_Task (taskType);
create index IX_E006107D on timesheet_Task (type_);
create index IX_39208692 on timesheet_Task (userId);
create index IX_8FE0E5A5 on timesheet_Task (userId, taskType);

create index IX_817C6465 on timesheet_TaskSession (departmentId, userId);
create index IX_AD36F903 on timesheet_TaskSession (startTime, endTime);
create index IX_2C1C7C24 on timesheet_TaskSession (userId);
create unique index IX_FC7413D4 on timesheet_TaskSession (userId, endTime);
create index IX_19221F9B on timesheet_TaskSession (userId, startTime);
create index IX_640EE27 on timesheet_TaskSession (userId, taskId, startTime);
create index IX_73375DB8 on timesheet_TaskSession (userId, taskId, startTime, startTime);