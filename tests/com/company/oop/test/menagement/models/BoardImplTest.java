package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.company.oop.test.menagement.models.StoryImplTest.VALID_DESCRIPTION_NAME_LENGTH;
import static com.company.oop.test.menagement.models.StoryImplTest.VALID_TITLE_NAME_LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class BoardImplTest {
    private static final int MAX_NAME_LENGTH = 10;
    private static final int INVALID_NAME_LENGTH = 11;


    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        String validName = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validName);

        Assertions.assertEquals(validName, board.getBoardName());
    }

    @Test
    public void constructor_Should_ThrowError_When_NameIsInvalid() {
        String invalidName = "C".repeat(INVALID_NAME_LENGTH);

        assertThrows(IllegalArgumentException.class, () -> new BoardImpl(invalidName));
    }

    @Test
    public void getHistory_Should_ReturnCopyOfTheCollection() {
        String validName = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validName);
        ActivityHistory activityHistory = new ActivityHistoryImpl("Description");

        board.getHistory().add(activityHistory);

        //Очакваме да върне копие от колекция и прави точно това. Просто при инициализиране на ново Story вкарва 1 ActivityHistory веднага в листа.
        Assertions.assertEquals(1, board.getHistory().size());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        String validName = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        board.getTasks().add(feedback);

        Assertions.assertEquals(0, board.getTasks().size());
    }

    @Test
    void addTask_Should_AddTask_WhenTask_DoesNotExist() {
        String validName = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        board.addTask(feedback);

        assertEquals(1, board.getTasks().size());
    }

    @Test
    void addTask_Should_ThrowError_WhenTask_AlreadyExist() {
        String validName = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validName);
        Feedback feedback = new FeedbackImpl(1, "VALID_TITLE_NAME_LENGTH", "VALID_DESCRIPTION_NAME_LENGTH", 25);

        board.addTask(feedback);

        assertThrows(DuplicateEntityException.class, () -> board.addTask(feedback));
    }

    @Test
    void viewInfo_Should_PrintFullHistoryInfo() {
        Story story = new StoryImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        Team team = new TeamImpl("TeamOne");
        Board board = new BoardImpl("BoardOne");
        Member member = new MemberImpl("Pesho");
        team.addBoard(board);
        board.addTask(story);
        team.addMember(member);
        member.assignTask(story);

        String result = story.viewInfo();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(10, board.toString().split("\n").length);
    }
}