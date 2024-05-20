package com.company.oop.test.menagement.models.enums;

public enum PriorityType {
    LOW,
    MEDIUM,
    HIGH;

    @Override
    public String toString() {
        switch (this) {
            case LOW:
                return "Low";
            case MEDIUM:
                return "Medium";
            case HIGH:
                return "High";
            default:
                return "Unknown";
        }
    }
}
