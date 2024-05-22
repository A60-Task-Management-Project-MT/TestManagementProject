package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityHistoryImpl implements ActivityHistory {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private String description;
    private final LocalDateTime timestamp;

    public ActivityHistoryImpl() {
        throw new IllegalArgumentException("Description cannot be empty");
    }

    public ActivityHistoryImpl(String description) {
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String displayHistory() {
       return String.format("[%s] Description: %s", timestamp.format(formatter), description);
    }
}
