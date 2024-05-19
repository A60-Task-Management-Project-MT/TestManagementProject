package com.company.oop.test.menagement.models.contracts;

import java.util.List;

public interface HistoryTrackable {

    List<ActivityHistory> getHistory();

    void createNewHistory(String event);
}
