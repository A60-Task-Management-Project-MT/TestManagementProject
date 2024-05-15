package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;

import java.util.List;

public class CreateNewStoryToBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 8;
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s with ID %d added to board %s!";

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewStoryToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
