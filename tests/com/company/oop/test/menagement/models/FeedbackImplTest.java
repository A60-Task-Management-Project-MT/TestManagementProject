package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void viewInfo_Should_PrintFullHistoryInfo() {
        Feedback feedback = new FeedbackImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH, 25);
        Team team = new TeamImpl("TeamOne");
        Board board = new BoardImpl("BoardOne");
        Member member = new MemberImpl("Pesho");
        team.addBoard(board);
        team.addMember(member);

        String result = feedback.viewInfo();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(7, feedback.viewInfo().split("\n").length);
    }
}
