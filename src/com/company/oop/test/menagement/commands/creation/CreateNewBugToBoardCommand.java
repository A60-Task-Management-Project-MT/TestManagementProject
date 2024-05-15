package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.BoardImpl;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateNewBugToBoardCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 8;
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s with ID %s added to board %s!";

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewBugToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        PriorityType priorityType = ParsingHelpers.tryParseEnum(parameters.get(2), PriorityType.class);
        BugSeverityType severityType = ParsingHelpers.tryParseEnum(parameters.get(3), BugSeverityType.class);
        String assignee = parameters.get(4);
        String boardName = parameters.get(5);
        List<String> steps = Arrays.asList(parameters.get(6).split("; "));

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Task task = createBug(title, description, priorityType, severityType, assignee, steps);

        board.addTask(task);

        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task.getTaskType(), task.getId(), boardName);
    }

    private Task createBug(String title, String description, PriorityType priorityType,
                            BugSeverityType severityType, String assignee, List<String> steps) {
        return taskManagementRepository.createBug(title, description, priorityType, severityType, assignee, steps);
    }
}
