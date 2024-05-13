package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;

public interface Feedback extends Task {

    int getRating();

    FeedbackStatusType getStatus();
}
