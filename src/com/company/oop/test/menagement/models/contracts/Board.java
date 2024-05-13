package com.company.oop.test.menagement.models.contracts;

public interface Board extends Taskable, HistorySavable {

    String getBoardName();

    void addTask(Task task);

    void addHistory(ActivityHistory activity);


}
