package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}