package com.company.oop.test.menagement.models.contracts;


import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;

public interface Task extends Commentable, HistorySavable, Changable {

    int getId();

    String getTitle();

    String getDescription();

    TaskType getTaskType();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void displayFullHistory();

    String viewInfo();
}
