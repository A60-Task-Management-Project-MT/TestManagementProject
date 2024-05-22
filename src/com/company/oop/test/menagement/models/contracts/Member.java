package com.company.oop.test.menagement.models.contracts;

public interface Member extends TaskRegistry, HistoryTrackable{

    String getMemberName();

    void assignTask(Task task);

    void unassignTask(Task task);
}
