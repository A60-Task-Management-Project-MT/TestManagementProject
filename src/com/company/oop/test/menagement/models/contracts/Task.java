package com.company.oop.test.menagement.models.contracts;


import com.company.oop.test.menagement.models.enums.TaskType;

public interface Task extends Commentable, HistorySavable, Changeable {

    int getId();

    String getTitle();

    String getDescription();

    TaskType getTaskType();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    String displayFullHistory();

    String viewInfo();
}
