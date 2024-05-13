package com.company.oop.test.menagement.models.enums.bug_enums;

public enum BugStatusType {
    ACTIVE,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            default:
                return "Unknown";
        }
    }
}
