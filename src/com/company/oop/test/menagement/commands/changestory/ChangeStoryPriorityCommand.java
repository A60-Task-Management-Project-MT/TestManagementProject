package com.company.oop.test.menagement.commands.changestory;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ChangeStoryPriorityCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID needs to be a number!";
    public static final String STORY_PRIORITY_CHANGE_MESSAGE = "Priority of task %s with ID %d was changed to %s!";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeStoryPriorityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR_MESSAGE);
        PriorityType newPriorityType = ParsingHelpers.tryParseEnum(parameters.get(1), PriorityType.class);

        Task task = taskManagementRepository.findTaskById(id);
        task.changePriority(newPriorityType);

        return String.format(STORY_PRIORITY_CHANGE_MESSAGE, task.getTaskType(), task.getId(), newPriorityType);
    }
}
