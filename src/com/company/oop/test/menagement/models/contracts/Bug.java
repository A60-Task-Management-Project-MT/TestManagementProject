package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;

import java.util.List;

public interface Bug extends Task<BugStatusType>, Assignable {

    PriorityType getPriority();

    BugSeverityType getSeverity();

    List<String> getStepsToReproduce();

    void changeSeverity(BugSeverityType newSeverityType);

    void changePriority(PriorityType newPriorityType);
}
