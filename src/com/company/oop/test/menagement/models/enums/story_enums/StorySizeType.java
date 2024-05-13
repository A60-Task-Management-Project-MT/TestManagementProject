package com.company.oop.test.menagement.models.enums.story_enums;

public enum StorySizeType {
    LARGE,
    MEDIUM,
    SMALL;

    @Override
    public String toString() {
        switch (this) {
            case LARGE:
                return "Large";
            case MEDIUM:
                return "Medium";
            case SMALL:
                return "Small";
            default:
                return "Unknown";
        }
    }

}
