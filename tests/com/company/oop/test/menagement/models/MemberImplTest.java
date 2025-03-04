package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.test.menagement.models.StoryImplTest.VALID_DESCRIPTION_NAME_LENGTH;
import static com.company.oop.test.menagement.models.StoryImplTest.VALID_TITLE_NAME_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class MemberImplTest {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int INVALID_NAME_LENGTH = 16;

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);

        Assertions.assertEquals(validName, member.getMemberName());
    }

    @Test
    public void constructor_Should_ThrowError_When_NameIsInvalid() {
        String invalidName = "C".repeat(INVALID_NAME_LENGTH);

        assertThrows(IllegalArgumentException.class, () -> new MemberImpl(invalidName));
    }

    @Test
    public void constructor_Should_NotInitializeName_When_ArgumentsAreInvalid() {
        String inValidName = "C".repeat(MIN_NAME_LENGTH - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new MemberImpl(inValidName));
    }

    @Test
    public void getHistory_Should_ReturnCopyOfTheCollection() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        ActivityHistory activityHistory = new ActivityHistoryImpl("Description");

        member.getHistory().add(activityHistory);

        //Очакваме да върне копие от колекция и прави точно това. Просто при инициализиране на ново Story вкарва 1 ActivityHistory веднага в листа.
        Assertions.assertEquals(1, member.getHistory().size());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        member.getTasks().add(feedback);

        Assertions.assertEquals(0, member.getTasks().size());
    }

    @Test
    void assignTask_Should_AssignTask_WhenArguments_AreValid() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        member.assignTask(feedback);

        assertEquals(1, member.getTasks().size());
    }

    @Test
    void unassignTask_Should_UnassignTask_WhenArguments_AreValid() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        member.assignTask(feedback);
        assertEquals(1, member.getTasks().size());

        member.unassignTask(feedback);
        assertEquals(0, member.getTasks().size());
    }

    @Test
    void unassignTask_Should_ThrowException_When_CantFindTask_WithId() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        assertThrows(IllegalArgumentException.class, () -> member.unassignTask(feedback));
    }

    @Test
    void assignTask_Should_ThrowException_WhenMember_AlreadyHave_ThisTask() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Member member = new MemberImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        member.assignTask(feedback);

        assertThrows(DuplicateEntityException.class, () -> member.assignTask(feedback));
    }

    @Test
    void viewInfo_Should_PrintFullHistoryInfo() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        Team team = new TeamImpl("TeamOne");
        Board board = new BoardImpl("BoardOne");
        Member member = new MemberImpl("Pesho");
        team.addBoard(board);
        team.addMember(member);
        member.assignTask(story);

        String result = story.viewInfo();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(10, member.toString().split("\n").length);
    }

    @Test
    void toString_Should_ThrowException_IfMember_DoesNotHaveTasks() {
        Member member = new MemberImpl("Gosho");
        assertThrows(ElementNotFoundException.class, member::toString);
    }
}