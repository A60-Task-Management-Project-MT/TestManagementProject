package com.company.oop.test.menagement.models.contracts;

public interface Board extends Taskable, HistorySavable ,Printable{

    String getBoardName();

    void addTask(Task task);

    void createNewHistory(String activity);
}
