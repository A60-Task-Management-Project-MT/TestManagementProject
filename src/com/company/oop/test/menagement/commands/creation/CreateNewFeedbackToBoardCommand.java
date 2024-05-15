package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateNewFeedbackToBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s with ID %d added to board %s!";
    private static final String RATING_ERROR_MESSAGE = "Rating should be a number!";

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewFeedbackToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        int rating = ParsingHelpers.tryParseInt(parameters.get(2), RATING_ERROR_MESSAGE);
        String boardName = parameters.get(3);

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Task task = createFeedback(title,description,rating);
        board.addTask(task);

        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task.getTaskType(), task.getId(), boardName);
    }

    private Task createFeedback(String title, String description, int rating) {
        return taskManagementRepository.createFeedback(title, description, rating);
    }
}
