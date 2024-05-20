package com.company.oop.test.menagement.models.enums.bug_enums;

public enum BugSeverityType {
    MINOR,
    MAJOR,
    CRITICAL;


    @Override
    public String toString() {
        switch (this) {
            case MINOR:
                return "Minor";
            case MAJOR:
                return "Major";
            case CRITICAL:
                return "Critical";
            default:
                return "Unknown";
        }
    }
}
