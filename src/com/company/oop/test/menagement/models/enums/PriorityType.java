package com.company.oop.test.menagement.models.enums;

public enum PriorityType {
    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Lol";
            default:
                return "Unknown";
        }
    }
}
