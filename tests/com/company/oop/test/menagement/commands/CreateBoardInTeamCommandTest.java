package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Team;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateBoardInTeamCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String VALID_TEAM_NAME = "C".repeat(8);
    public static final String VALID_BOARD_NAME = "C".repeat(10);

    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new CreateBoardInTeamCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_AddBoard_ToTeam_WhenArguments_AreValid() {
        Team team = taskManagementRepository.createTeam(VALID_TEAM_NAME);
        Board board = taskManagementRepository.createBoard(VALID_BOARD_NAME);

        List<String> params = List.of(VALID_BOARD_NAME, VALID_TEAM_NAME);

        command.execute(params);

        assertEquals(1, team.getBoards().size());
    }
}