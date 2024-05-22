package com.company.oop.test.menagement.commands.enums;

import com.company.oop.test.menagement.commands.listings.ListAllTaskCommand;

public enum CommandType {
    ADDCOMMENT,
    ADDPERSONTOTEAM,
    ASSIGNTASK,
    UNASSIGNTASK,
    CREATEBOARD,
    CREATEPERSON,
    CREATEBUG,
    CREATESTORY,
    CREATEFEEDBACK,
    CREATETEAM,
    SHOWPEOPLE,
    SHOWTEAMBOARDS,
    SHOWTEAMMEMBERS,
    SHOWTEAMS,
    SHOWBOARDACTIVITY,
    SHOWPERSONACTIVITY,
    SHOWTEAMACTIVITY,
    CHANGEBUGPRIORITY,
    CHANGEBUGSEVERITY,
    CHANGEFEEDBACKRATING,
    CHANGESTORYPRIORITY,
    CHANGESTORYSIZE,
    CHANGETASKSTATUS,
    LISTALLTASKS,
    LISTBUGS,
    LISTFEEDBACKS,
    LISTSTORIES,
    LISTTASKSWITHASSIGNEE;
}
