package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;

import java.time.LocalDate;

public class FeedbackImpl extends TaskImpl implements Feedback {

    private int rating;
    private FeedbackStatusType statusType;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        setStatusType(FeedbackStatusType.NEW);
    }

    @Override
    public void changeStatus() {
        if (getStatus() != FeedbackStatusType.DONE) {
            FeedbackStatusType newStatus = FeedbackStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Feedback status changed from %s to %s.", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Can't change, already at %s.", getStatus()));
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

    private void setStatusType(FeedbackStatusType statusType) {
        this.statusType = statusType;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
}
