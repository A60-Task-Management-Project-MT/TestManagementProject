package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;

import java.util.List;

public interface Bug extends Task {

    PriorityType getPriority();

    BugSeverityType getSeverity();

    BugStatusType getStatus();

    String getAssignee();

    void setAssignee(String name);

    List<String> getStepsToReproduce();
}
