package com.company.oop.test.menagement.models.contracts;

public interface Board extends TaskRegistry, HistoryTrackable,Printable{

    String getBoardName();

    void addTask(Task task);
}
