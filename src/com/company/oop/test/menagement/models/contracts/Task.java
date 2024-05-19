package com.company.oop.test.menagement.models.contracts;


import com.company.oop.test.menagement.models.enums.TaskType;

import java.util.List;

public interface Task<T extends Enum<T>> extends HistoryTrackable {

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

    T getStatus();
}
