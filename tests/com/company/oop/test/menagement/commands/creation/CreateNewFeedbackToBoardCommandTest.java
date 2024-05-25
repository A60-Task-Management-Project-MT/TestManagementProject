package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Team;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateNewFeedbackToBoardCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    public static final String VALID_PERSON = "C".repeat(8);
    public static final String VALID_TEAM = "C".repeat(8);
    public static final String VALID_BOARD = "C".repeat(10);
    public static final String VALID_TITLE = "C".repeat(50);
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new CreateNewFeedbackToBoardCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_CreateFeedback_InBoard_IfArguments_AreValid() {
        Member member = taskManagementRepository.createMember(VALID_PERSON);
        Team team = taskManagementRepository.createTeam(VALID_TEAM);
        Board board = taskManagementRepository.createBoard(VALID_BOARD);
        team.addBoard(board);
        team.addMember(member);

        List<String> params = List.of(
                VALID_TITLE,
                VALID_DESCRIPTION,
                String.valueOf(100),
                VALID_BOARD);

        command.execute(params);

        assertEquals(1, taskManagementRepository.getTasks().size());
    }
}