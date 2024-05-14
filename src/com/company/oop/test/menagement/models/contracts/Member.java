package com.company.oop.test.menagement.models.contracts;

public interface Member extends Taskable, HistorySavable, Printable{

    String getMemberName();

    void assignTask(Task task);

    void unassignTask(Task task);

    void addComment(Comment commentToAdd, Task taskToAddComment);

    void removeComment(Comment commentToRemove, Task taskToRemoveComment);

    void createNewHistory(String activity);
}
