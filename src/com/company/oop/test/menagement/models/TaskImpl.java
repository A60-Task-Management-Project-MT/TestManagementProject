package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class TaskImpl implements Task {

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

    private int id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<ActivityHistory> history;

    public TaskImpl(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.history = new ArrayList<>();
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

        createNewHistory(String.format("New comment was added to task %s", title));
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);

        createNewHistory(String.format("A comment was removed from task %s", title));
    }

    public abstract void changeStatus();

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public void displayFullHistory() {
        for (ActivityHistory activity : history) {
            System.out.println(activity.viewInfo());
        }
    }

    protected void createNewHistory(String event) {
        history.add(new ActivityHistoryImpl(event));
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
