package com.company.oop.test.menagement.commands.changefeedback;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRatingCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID needs to be a number!";
    public static final String RATING_ERROR_MESSAGE = "Rating needs to be a valid number!";
    public static final String FEEDBACK_RATING_CHANGE_MESSAGE = "Rating of task %s with ID %d was changed to %d!";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeFeedbackRatingCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR_MESSAGE);
        int newRating = ParsingHelpers.tryParseInt(parameters.get(1), RATING_ERROR_MESSAGE);

        Task task = taskManagementRepository.findTaskById(id);
        task.changeRating(newRating);

        return String.format(FEEDBACK_RATING_CHANGE_MESSAGE, task.getTaskType(), task.getId(), newRating);
    }
}
