package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BugImpl extends TaskImpl<BugStatusType> implements Bug {

    public static final String PRIORITY_SET_ERROR = "Priority is already set to %s!";
    public static final String SEVERITY_SET_ERROR = "Severity is already set to %s!";
    public static final String BUG_SEVERITY_CHANGE_MESSAGE = "Bug severity was changed from %s to %s!";
    public static final String BUG_PRIORITY_CHANGE_MESSAGE = "Bug priority was changed from %s to %s!";
    public static final String BUG_STATUS_CHANGED_MESSAGE = "Bug with ID: %d status changed from %s to %s";
    public static final String BUG_STATUS_CHANGE_ERROR_MESSAGE = "Can't change, already at %s.";
    public static final String ADDED_NEW_ASSIGNEE_MESSAGE = "A new assignee %s was set for task %s with ID: %d";
    public static final String NEW_BUG_CREATION_MESSAGE = "New Bug was created: %s!";

    private List<String> stepsToReproduce;
    private PriorityType priorityType;
    private BugSeverityType severityType;
    private BugStatusType statusType;
    private String assignee;

    public BugImpl(int id, String title, String description, PriorityType priorityType,
                   BugSeverityType severityType, String assignee, List<String> stepsToReproduce) {

        super(id, title, description, TaskType.BUG);
        this.stepsToReproduce = new ArrayList<>();
        setPriorityType(priorityType);
        setSeverityType(severityType);
        this.statusType = BugStatusType.ACTIVE;
        setAssignee(assignee);

        createNewHistory(String.format(NEW_BUG_CREATION_MESSAGE, viewInfo()));
    }

    @Override
    public BugStatusType getStatus() {
        return statusType;
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
    public String getAssignee() {
        return assignee;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override
    public void changeAssignee(String assignee) {
        if (this.assignee.equals(assignee)) {
            throw new DuplicateEntityException(String.format("%s already assigned to %s!", getTaskType(), getAssignee()));
        }
        setAssignee(assignee);
    }

    @Override
    public void changeStatus() {
        if (getStatus() != BugStatusType.DONE) {
            BugStatusType newStatus = BugStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format(BUG_STATUS_CHANGED_MESSAGE, getId(), getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            throw new IllegalArgumentException(String.format(BUG_STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public void changePriority(PriorityType newPriorityType) {
        if (newPriorityType.equals(priorityType)) {
            throw new IllegalArgumentException(String.format(PRIORITY_SET_ERROR, priorityType));
        }
        setPriorityType(newPriorityType);

        createNewHistory(String.format(BUG_PRIORITY_CHANGE_MESSAGE, priorityType, newPriorityType));
    }

    @Override
    public void changeSeverity(BugSeverityType newSeverityType) {
        if (newSeverityType.equals(severityType)) {
            throw new IllegalArgumentException(String.format(SEVERITY_SET_ERROR, severityType));
        }
        setSeverityType(newSeverityType);

        createNewHistory(String.format(BUG_SEVERITY_CHANGE_MESSAGE, severityType, newSeverityType));
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        List<Comment> currentTaskComments = this.getComments();
        AtomicInteger count = new AtomicInteger(1);

        sb.append(String.format("Title: %s | Description: %s | Priority: %s | Severity: %s | Status: %s | Assignee: %s",
                getTitle(),getDescription(),getPriority(),getSeverity(),getStatus(), getAssignee())).append(System.lineSeparator());

        currentTaskComments.stream().map(c -> sb.append(count.getAndIncrement()).append(c.toString()));

        return sb.toString().trim();
    }

    private void setStatusType(BugStatusType statusType) {
        this.statusType = statusType;
    }

    private void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setSeverityType(BugSeverityType severityType) {
        this.severityType = severityType;
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;

        createNewHistory(String.format(ADDED_NEW_ASSIGNEE_MESSAGE, assignee, getTaskType(), getId()));
    }
}
