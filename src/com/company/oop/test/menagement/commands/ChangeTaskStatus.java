package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ChangeTaskStatus implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String ID_ERROR_MESSAGE = "ID needs to be a number!";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeTaskStatus(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0),ID_ERROR_MESSAGE);

        Task task = taskManagementRepository.findTaskById(id);
        task.changeStatus();

        return String.format("Status of task %s successfully changed!",task.getTaskType());
    }
}
