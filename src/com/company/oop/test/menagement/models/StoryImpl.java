package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

public class StoryImpl extends TaskImpl implements Story {

    public static final String PRIORITY_SET_ERROR = "Priority is already set to %s!";
    public static final String SIZE_SET_ERROR = "Size is already set to %s!";
    public static final String STORY_ALREADY_ASSIGNED_TO_ASSIGNEE_ERROR = "This story was already assigned to assignee %s!";

    private PriorityType priorityType;
    private StorySizeType storySizeType;
    private StoryStatusType statusType;
    private String assignee;

    public StoryImpl(int id, String title, String description, PriorityType priorityType,
                     StorySizeType storySizeType, String assignee) {
        super(id, title, description);
        setPriorityType(priorityType);
        setStorySizeType(storySizeType);
        this.statusType = StoryStatusType.NOT_DONE;
        setAssignee(assignee);
    }

    @Override
    public void changeStatus() {
        if (getStatus() != StoryStatusType.DONE) {
            StoryStatusType newStatus = StoryStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Story status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Can't change, already at %s", getStatus()));
        }
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
    public void changePriority(PriorityType newPriorityType) {
        if (newPriorityType.equals(priorityType)) {
            throw new IllegalArgumentException(String.format(PRIORITY_SET_ERROR, priorityType));
        }
        setPriorityType(newPriorityType);

        createNewHistory(String.format("Story priority was changed from %s to %s!", priorityType, newPriorityType));
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

    private void setStatusType(StoryStatusType statusType) {
        this.statusType = statusType;
    }

    private void setPriorityType(PriorityType priorityType) {
        this.priorityType = priorityType;
    }

    private void setStorySizeType(StorySizeType storySizeType) {
        this.storySizeType = storySizeType;
    }

    private void setAssignee(String assignee) {
        if (this.assignee.equals(assignee)) {
            throw new IllegalArgumentException(String.format(STORY_ALREADY_ASSIGNED_TO_ASSIGNEE_ERROR, assignee));
        }
        this.assignee = assignee;
    }
}
