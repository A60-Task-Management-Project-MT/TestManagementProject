package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FeedbackImpl extends TaskImpl<FeedbackStatusType> implements Feedback {

    public static final String FEEDBACK_RATING_CHANGE_MESSAGE = "Feedback rating was changed from %d to %d";
    public static final String FEEDBACK_RATING_ALREADY_SET_ERROR_MESSAGE = "Feedback rating is already %d!";
    public static final String FEEDBACK_STATUS_CHANGED_MESSAGE = "Feedback with ID: %d status changed from %s to %s.";
    public static final String FEEDBACK_STATUS_CHANGE_ERROR_MESSAGE = "Can't change, already at %s.";
    public static final String FEEDBACK_CREATION_MESSAGE = "New Bug with ID: %d was created: Title %s";

    private int rating;
    private FeedbackStatusType statusType;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, TaskType.FEEDBACK);
        setRating(rating);
        setStatusType(FeedbackStatusType.NEW);

        createNewHistory(String.format(FEEDBACK_CREATION_MESSAGE, getId(), getTitle()));
    }

    @Override
    public FeedbackStatusType getStatus() {
        return statusType;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void changeStatus() {
        if (getStatus() != FeedbackStatusType.DONE) {
            FeedbackStatusType newStatus = FeedbackStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format(FEEDBACK_STATUS_CHANGED_MESSAGE, getId(), getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            throw new IllegalArgumentException(String.format(FEEDBACK_STATUS_CHANGE_ERROR_MESSAGE, getStatus()));
        }
    }

    @Override
    public void changeRating(int newRating) {
        if (newRating == rating) {
            throw new IllegalArgumentException(String.format(FEEDBACK_RATING_ALREADY_SET_ERROR_MESSAGE, rating));
        }
        setRating(newRating);

        createNewHistory(String.format(FEEDBACK_RATING_CHANGE_MESSAGE, rating, newRating));
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        List<Comment> currentTaskComments = this.getComments();
        AtomicInteger count = new AtomicInteger(1);

        sb.append(String.format("Task ID: %d", getId())).append(System.lineSeparator());
        sb.append(String.format(" #Title: %s", getTitle())).append(System.lineSeparator());
        sb.append(String.format(" #Description: %s", getDescription())).append(System.lineSeparator());
        sb.append(String.format(" #Rating: %d", getRating())).append(System.lineSeparator());
        sb.append(String.format(" #Status: %s", getStatus())).append(System.lineSeparator());
        sb.append("~~~ Comments ~~~").append(System.lineSeparator());

        if (currentTaskComments.isEmpty()) {
            sb.append(" # NO COMMENTS AVAILABLE").append(System.lineSeparator());
        }
        currentTaskComments.stream().map(c -> sb.append(count.getAndIncrement()).append(c.toString()));

        return sb.toString().trim();
    }

    private void setStatusType(FeedbackStatusType statusType) {
        this.statusType = statusType;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
}
