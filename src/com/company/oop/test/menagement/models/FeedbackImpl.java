package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private int rating;
    private FeedbackStatusType statusType;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, TaskType.FEEDBACK);
        setRating(rating);
        setStatusType(FeedbackStatusType.NEW);

        createNewHistory(String.format("New Feedback was created: %s!", viewInfo()));
    }

    @Override
    public void changeStatus() {
        if (getStatus() != FeedbackStatusType.DONE) {
            FeedbackStatusType newStatus = FeedbackStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Feedback status changed from %s to %s.", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            throw new IllegalArgumentException(String.format("Can't change, already at %s.", getStatus()));
        }
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public FeedbackStatusType getStatus() {
        return statusType;
    }

    @Override
    public void changeRating(int newRating) {
        if (newRating == rating) {
            throw new IllegalArgumentException(String.format("Feedback rating is already %d!", rating));
        }
        setRating(newRating);

        createNewHistory(String.format("Feedback rating was changed from %d to %d", rating, newRating));
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        List<Comment> currentTaskComments = this.getComments();
        AtomicInteger count = new AtomicInteger(1);

        sb.append(String.format("Title: %s | Description: %s | Rating: %d | Status: %s",
                getTitle(), getDescription(), getRating(), getStatus())).append(System.lineSeparator());

        currentTaskComments.stream().map(c -> sb.append(count.getAndIncrement()).append(c.toString()));

        return sb.toString().trim();
    }

    private void setStatusType(FeedbackStatusType statusType) {
        this.statusType = statusType;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return getStatus().toString();
    }
}
