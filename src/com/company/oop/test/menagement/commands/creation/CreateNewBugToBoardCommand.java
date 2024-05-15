package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.BoardImpl;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateNewBugToBoardCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String INVALID_ID_ERROR = "Invalid ID. Needs to be number!";
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s added to board %s!";


    private final TaskManagementRepository taskManagementRepository;

    public CreateNewBugToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        int id = ParsingHelpers.tryParseInt(parameters.get(1), INVALID_ID_ERROR);

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Task task = taskManagementRepository.findTaskById(id);

        board.addTask(task);

        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task, board);
    }
}
