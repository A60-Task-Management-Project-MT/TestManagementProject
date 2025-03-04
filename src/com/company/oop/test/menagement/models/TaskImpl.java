package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public abstract class TaskImpl<T extends Enum<T>> implements Task<T> {

    public static final int MIN_TITLE_SYMBOLS = 10;
    public static final int MAX_TITLE_SYMBOLS = 100;
    public static final String TITLE_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Title must be between %s and %s characters long!",
            MIN_TITLE_SYMBOLS,
            MAX_TITLE_SYMBOLS);
    public static final int MIN_DESCRIPTION_SYMBOLS = 10;
    public static final int MAX_DESCRIPTION_SYMBOLS = 500;
    public static final String DESCRIPTION_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Description must be between %s and %s characters long!",
            MIN_DESCRIPTION_SYMBOLS,
            MAX_DESCRIPTION_SYMBOLS);
    public static final String NEW_COMMENT_ADD_MESSAGE = "A new comment %s from author %s was add to task with ID %d.";

    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<ActivityHistory> history;
    private TaskType taskType;

    public TaskImpl(int id, String title, String description, TaskType taskType) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.history = new ArrayList<>();
        this.taskType = taskType;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);

        createNewHistory(String.format(NEW_COMMENT_ADD_MESSAGE, comment.getContent(), comment.getAuthor(), getId()));
    }

    @Override
    public String displayFullHistory() {
        StringBuilder sb = new StringBuilder(); 
        for (ActivityHistory activity : history) {
           sb.append(activity.displayHistory()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    @Override
    public void createNewHistory(String event) {
        history.add(new ActivityHistoryImpl(event));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskImpl task = (TaskImpl) o;
        return id == task.id;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title,
                MIN_TITLE_SYMBOLS,
                MAX_TITLE_SYMBOLS,
                TITLE_MIN_OR_MAX_LENGTH_ERROR);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description,
                MIN_DESCRIPTION_SYMBOLS,
                MAX_DESCRIPTION_SYMBOLS,
                DESCRIPTION_MIN_OR_MAX_LENGTH_ERROR);
        this.description = description;
    }
}
