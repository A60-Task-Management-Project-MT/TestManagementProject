package com.company.oop.test.menagement.models.contracts;


import com.company.oop.test.menagement.models.enums.TaskType;

import java.util.List;

public interface Task extends HistorySavable {

    int getId();

    String getTitle();

    String getDescription();

    TaskType getTaskType();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    String displayFullHistory();

    String viewInfo();

    void changeStatus();

    List<Comment> getComments();
}
