package com.company.oop.test.menagement.models.enums.story_enums;

public enum StoryStatusType {
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            default:
                return "Unknown";
        }
    }
}
