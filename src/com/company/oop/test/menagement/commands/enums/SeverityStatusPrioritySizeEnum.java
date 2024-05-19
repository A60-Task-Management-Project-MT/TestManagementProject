package com.company.oop.test.menagement.commands.enums;

public enum SeverityStatusPrioritySizeEnum {
    HIGH,
    MEDIUM,
    LOW,
    CRITICAL,
    MAJOR,
    MINOR,
    ACTIVE,
    DONE,
    LARGE,
    SMALL,
    NOT_DONE,
    IN_PROGRESS;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            case LARGE:
                return "Large";
            case SMALL:
                return "Small";
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In Progress";
            default:
                return "Unknown";
        }
    }
}
