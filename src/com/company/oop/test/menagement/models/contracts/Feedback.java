package com.company.oop.test.menagement.models.contracts;

import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;

public interface Feedback extends Task<FeedbackStatusType> {

    int getRating();

    void changeRating(int newRating);
}
