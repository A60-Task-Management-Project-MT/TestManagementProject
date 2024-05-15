package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    public static final String PRIORITY_SET_ERROR = "Priority is already set to %s!";
    public static final String SEVERITY_SET_ERROR = "Severity is already set to %s!";
    public static final String BUG_ALREADY_ASSIGNED_TO_ASSIGNEE_ERROR = "This Bug was already assigned to assignee %s.";

    private List<String> stepsToReproduce;
    private PriorityType priorityType;
    private BugSeverityType severityType;
    private BugStatusType statusType;
    private String assignee;

    public BugImpl(int id, String title, String description, PriorityType priorityType,
                   BugSeverityType severityType, String assignee) {
        super(id, title, description, TaskType.BUG);
        this.stepsToReproduce = new ArrayList<>();
        setPriorityType(priorityType);
        setSeverityType(severityType);
        this.statusType = BugStatusType.ACTIVE;
        setAssignee(assignee);

        createNewHistory(String.format("New Bug was created: %s!", viewInfo()));
    }

    @Override
    public void changeStatus() {
        if (getStatus() != BugStatusType.DONE) {
            BugStatusType newStatus = BugStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Bug status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Can't change, already at %s.", getStatus()));
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
    public void addStep(String step) {
        stepsToReproduce.add(step);

        createNewHistory(String.format("A new Bug step %s was added to be reproduced!", step));
    }

    @Override
    public void changePriority(PriorityType newPriorityType) {
        if (newPriorityType.equals(priorityType)) {
            throw new IllegalArgumentException(String.format(PRIORITY_SET_ERROR, priorityType));
        }
        setPriorityType(newPriorityType);

        createNewHistory(String.format("Bug priority was changed from %s to %s!", priorityType, newPriorityType));
    }

    @Override
    public void changeSeverity(BugSeverityType newSeverityType) {
        if (newSeverityType.equals(severityType)) {
            throw new IllegalArgumentException(String.format(SEVERITY_SET_ERROR, severityType));
        }
        setSeverityType(newSeverityType);

        createNewHistory(String.format("Bug severity was changed from %s to %s!", severityType, newSeverityType));
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    private void setStatusType(BugStatusType statusType) {
        this.statusType = statusType;
    }

    private void setAssignee(String assignee) {
        if (this.assignee.equals(assignee)) {
            throw new IllegalArgumentException(String.format(BUG_ALREADY_ASSIGNED_TO_ASSIGNEE_ERROR, assignee));
        }
        this.assignee = assignee;
    }

    private void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setSeverityType(BugSeverityType severityType) {
        this.severityType = severityType;
    }

    @Override
    public String viewInfo() {
        return String.format("Title: %s | Description: %s | Priority: %s | Severity: %s | Status: %s%n",
                getTitle(),getDescription(),getPriority(),getSeverity(),getStatus());
    }
}
