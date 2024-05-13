package com.company.oop.test.menagement.models.enums.bug_enums;

public enum BugSeverityType {
    CRITICAL,
    MAJOR,
    MINOR;


    @Override
    public String toString() {
        switch (this) {
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            default:
                return "Unknown";
        }
    }
}
