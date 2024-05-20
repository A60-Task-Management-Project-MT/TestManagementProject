package com.company.oop.test.menagement.models.enums.story_enums;

public enum StorySizeType {
    SMALL,
    MEDIUM,
    LARGE;

    @Override
    public String toString() {
        switch (this) {
            case SMALL:
                return "Small";
            case MEDIUM:
                return "Medium";
            case LARGE:
                return "Large";
            default:
                return "Unknown";
        }
    }

}
