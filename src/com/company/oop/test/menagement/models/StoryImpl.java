package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

public class StoryImpl extends TaskImpl implements Story {
    private PriorityType priorityType;
    private StorySizeType storySizeType;
    private StoryStatusType statusType;

    public StoryImpl(int id, String title, String description, PriorityType priorityType, StorySizeType storySizeType) {
        super(id, title, description);
        this.priorityType = priorityType;
        this.storySizeType = storySizeType;
        this.statusType = StoryStatusType.NOT_DONE;
    }

    private void setStatusType(StoryStatusType statusType) {
        this.statusType = statusType;
    }

    @Override
    public void revertStatus() {
        if (getStatus() != StoryStatusType.NOT_DONE) {
            StoryStatusType newStatus = StoryStatusType.values()[getStatus().ordinal() - 1];
            createNewHistory(String.format("Story status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Cant revert, already at %s", getStatus()));
        }
    }

    @Override
    public void advanceStatus() {
        if (getStatus() != StoryStatusType.DONE) {
            StoryStatusType newStatus = StoryStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Story status changed from %s to %s", getStatus(), newStatus));
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
    public StorySizeType getSize() {
        return storySizeType;
    }

    @Override
    public StoryStatusType getStatus() {
        return statusType;
    }
}
