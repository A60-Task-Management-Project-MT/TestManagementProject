package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StoryImpl extends TaskImpl<StoryStatusType> implements Story {
    public static final String PRIORITY_SET_ERROR = "Priority is already set to %s!";

    public static final String SIZE_SET_ERROR = "Size is already set to %s!";
    public static final String STORY_SIZE_CHANGED_MESSAGE = "Story size was changed from %s to %s!";
    public static final String STORY_PRIORITY_CHANGED_MESSAGE = "Story priority was changed from %s to %s!";
    public static final String STORY_STATUS_CHANGED_MESSAGE = "Story with ID: %d status changed from %s to %s";
    public static final String STORY_STATUS_CHANGE_ERROR_MESSAGE = "Can't change, already at %s.";
    public static final String ADDED_NEW_ASSIGNEE_MESSAGE = "A new assignee %s was set for task %s with ID: %d";
    public static final String NEW_STORY_CREATION_MESSAGE = "New Story with ID: %d was created: Title %s";

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

        createNewHistory(String.format(NEW_STORY_CREATION_MESSAGE, getId(), getTitle()));
    }

    @Override
    public String getAssignee() {
        return assignee;
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
    public void changeAssignee(String assignee) {
        if (this.assignee.equals(assignee)) {
            throw new DuplicateEntityException(String.format("%s already assigned to %s!", getTaskType(), getAssignee()));
        }
        setAssignee(assignee);
        createNewHistory(String.format(ADDED_NEW_ASSIGNEE_MESSAGE, assignee, getTaskType(), getId()));
    }

    @Override
    public void changeStatus() {
        if (getStatus() != StoryStatusType.DONE) {
            StoryStatusType newStatus = StoryStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format(STORY_STATUS_CHANGED_MESSAGE, getId(), getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            throw new IllegalArgumentException(String.format(STORY_STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public void changePriority(PriorityType newPriorityType) {
        if (newPriorityType.equals(priorityType)) {
            throw new IllegalArgumentException(String.format(PRIORITY_SET_ERROR, priorityType));
        }
        setPriorityType(newPriorityType);

        createNewHistory(String.format(STORY_PRIORITY_CHANGED_MESSAGE, priorityType, newPriorityType));
    }

    @Override
    public void changeSize(StorySizeType newSizeType) {
        if (newSizeType.equals(storySizeType)) {
            throw new IllegalArgumentException(String.format(SIZE_SET_ERROR, storySizeType));
        }
        setStorySizeType(newSizeType);

        createNewHistory(String.format(STORY_SIZE_CHANGED_MESSAGE, storySizeType, newSizeType));
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        List<Comment> currentTaskComments = this.getComments();
        AtomicInteger count = new AtomicInteger(1);

        sb.append(String.format("ID: %d | Title: %s | Description: %s | Priority: %s | Size: %s | Status: %s | Assignee %s",
                getId(), getTitle(), getDescription(), getPriority(), getSize(), getStatus(), getAssignee())).append(System.lineSeparator());
        sb.append("~~~ Comments ~~~").append(System.lineSeparator());
        if (currentTaskComments.isEmpty()) {
            sb.append(" # NO COMMENTS AVAILABLE").append(System.lineSeparator());
        }
        currentTaskComments.stream().map(c -> sb.append(count.getAndIncrement()).append(c.toString()));
        sb.append("~~~~~~~~~~~~~~~~").append(System.lineSeparator());
        return sb.toString().trim();
    }

    private void setAssignee(String assignee) {
        this.assignee = assignee;
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
}
