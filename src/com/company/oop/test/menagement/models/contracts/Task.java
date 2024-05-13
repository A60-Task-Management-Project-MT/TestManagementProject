package com.company.oop.test.menagement.models.contracts;


public interface Task extends Commentable, HistorySavable {

    int getId();

    String getTitle();

    String getDescription();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void revertStatus();

    void advanceStatus();

    void displayFullHistory();
}
