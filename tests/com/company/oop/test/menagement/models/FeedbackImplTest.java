package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackImplTest {
    public static final String VALID_TITLE_NAME_LENGTH = "C".repeat(100);
    public static final String VALID_DESCRIPTION_NAME_LENGTH = "C".repeat(500);

    @Test
    void constructor_ShouldCreate_Story_WhenArguments_AreValid() {
        Feedback feedback = new FeedbackImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH, 25);

        assertAll(
                () -> assertEquals(1, feedback.getId()),
                () -> assertEquals(VALID_TITLE_NAME_LENGTH, feedback.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION_NAME_LENGTH, feedback.getDescription()),
                () -> assertEquals(25, feedback.getRating())
        );
    }
    @Test
    void constructor_ShouldCreate_Story_WithFirstStatus_New() {
        Feedback feedback = new FeedbackImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH, 25);

        assertEquals(FeedbackStatusType.NEW, feedback.getStatus());
    }
}
