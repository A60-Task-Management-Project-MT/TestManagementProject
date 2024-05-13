package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;

public class ActivityHistoryImpl implements ActivityHistory {
    private String description;

    public ActivityHistoryImpl() {
        throw new IllegalArgumentException("Description cannot be empty");
    }

    public ActivityHistoryImpl(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String viewInfo() {
       return String.format("%s", getDescription());
    }
}
