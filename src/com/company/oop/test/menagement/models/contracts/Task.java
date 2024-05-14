package com.company.oop.test.menagement.models.contracts;


public interface Task extends Commentable, HistorySavable {

    int getId();

    String getTitle();

    String getDescription();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void changeStatus();

    void displayFullHistory();

    String viewInfo();
}
