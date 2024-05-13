package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    private List<String> stepsToReproduce;
    private PriorityType priorityType;
    private BugSeverityType severityType;
    private BugStatusType statusType;

    public BugImpl(int id, String title, String description, PriorityType priorityType, BugSeverityType severityType) {
        super(id, title, description);
        this.stepsToReproduce = new ArrayList<>();
        this.priorityType = priorityType;
        this.severityType = severityType;
        this.statusType = BugStatusType.ACTIVE;
    }

    private void setStatusType(BugStatusType statusType) {
        this.statusType = statusType;
    }

    @Override
    public void revertStatus() {
        if (getStatus() != BugStatusType.ACTIVE) {
            BugStatusType newStatus = BugStatusType.values()[getStatus().ordinal() - 1];
            createNewHistory(String.format("Bug status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Cant revert, already at %s", getStatus()));
        }
    }

    @Override
    public void advanceStatus() {
        if (getStatus() != BugStatusType.DONE) {
            BugStatusType newStatus = BugStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Bug status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Cant revert, already at %s", getStatus()));
        }
    }

    @Override
    public PriorityType getPriority() {
        return priorityType;
    }

    @Override
    public BugSeverityType getSeverity() {
        return severityType;
    }

    @Override
    public BugStatusType getStatus() {
        return statusType;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }
}
