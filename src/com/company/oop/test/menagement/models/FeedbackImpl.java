package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private FeedbackStatusType statusType;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        this.rating = rating;
        setStatusType(FeedbackStatusType.NEW);
    }

    private void setStatusType(FeedbackStatusType statusType) {
        this.statusType = statusType;
    }

    @Override
    public void revertStatus() {
        if (getStatus() != FeedbackStatusType.NEW) {
            FeedbackStatusType newStatus = FeedbackStatusType.values()[getStatus().ordinal() - 1];
            createNewHistory(String.format("Feedback status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Cant revert, already at %s", getStatus()));
        }
    }

    @Override
    public void advanceStatus() {
        if (getStatus() != FeedbackStatusType.DONE) {
            FeedbackStatusType newStatus = FeedbackStatusType.values()[getStatus().ordinal() + 1];
            createNewHistory(String.format("Feedback status changed from %s to %s", getStatus(), newStatus));
            setStatusType(newStatus);
        } else {
            createNewHistory(String.format("Cant revert, already at %s", getStatus()));
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
}
