package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StoryImpl extends TaskImpl implements Story {

    public static final String PRIORITY_SET_ERROR = "Priority is already set to %s!";
    public static final String SIZE_SET_ERROR = "Size is already set to %s!";

    private PriorityType priorityType;
    private StorySizeType storySizeType;
    private StoryStatusType statusType;
    private String assignee;

    public StoryImpl(int id, String title, String description, PriorityType priorityType,
                     StorySizeType storySizeType, String assignee) {
        super(id, title, description, TaskType.STORY);
        setPriorityType(priorityType);
        setStorySizeType(storySizeType);
        this.statusType = StoryStatusType.NOT_DONE;
        setAssignee(assignee);

        createNewHistory(String.format("New Story was created: %s!", viewInfo()));
    }

    @Override
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public PriorityType getPriority() {
        return priorityType;
    }

    @Override
    public StorySizeType getSize() {
        return storySizeType;
    }

    @Override
    public StoryStatusType getStatus() {
        return statusType;
    }

    @Override
    public void changeStatus() {
        if (getStatus() != StoryStatusType.DONE) {
            StoryStatusType newStatus = StoryStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Story status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            throw new IllegalArgumentException(String.format("Can't change, already at %s.", getStatus()));
        }
    }

    @Override
    public void changePriority(PriorityType newPriorityType) {
        if (newPriorityType.equals(priorityType)) {
            throw new IllegalArgumentException(String.format(PRIORITY_SET_ERROR, priorityType));
        }
        setPriorityType(newPriorityType);

        createNewHistory(String.format("Story priority was changed from %s to %s!", priorityType, newPriorityType));
    }

    @Override
    public void changeSeverity(BugSeverityType newSeverityType) {
        throw new IllegalArgumentException("Story does not have severity type!");
    }

    @Override
    public void changeRating(int newRating) {
        throw new IllegalArgumentException("Story does not have rating!");
    }

    @Override
    public void changeSize(StorySizeType newSizeType) {
        if (newSizeType.equals(storySizeType)) {
            throw new IllegalArgumentException(String.format(SIZE_SET_ERROR, storySizeType));
        }
        setStorySizeType(newSizeType);

        createNewHistory(String.format("Story size was changed from %s to %s!", storySizeType, newSizeType));
    }

    @Override
    public String getAssignee() {
        return assignee;
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        List<Comment> currentTaskComments = this.getComments();
        AtomicInteger count = new AtomicInteger(1);

        sb.append(String.format("Title: %s | Description: %s | Priority: %s | Size: %s | Status: %s | Assignee %s",
                getTitle(),getDescription(),getPriority(),getSize(),getStatus(), getAssignee())).append(System.lineSeparator());

        currentTaskComments.stream().map(c -> sb.append(count.getAndIncrement()).append(c.toString()));

        return sb.toString().trim();
    }

    private void setStatusType(StoryStatusType statusType) {
        this.statusType = statusType;
    }

    private void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setStorySizeType(StorySizeType storySizeType) {
        this.storySizeType = storySizeType;
    }

    @Override
    public String toString() {
        return getStatus().toString();
    }
}
