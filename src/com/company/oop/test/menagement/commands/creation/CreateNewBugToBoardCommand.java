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

        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(1), TaskType.class);
        String title = parameters.get(2);
        String description = parameters.get(3);
        PriorityType priorityType = ParsingHelpers.tryParseEnum(parameters.get(4), PriorityType.class);
        BugSeverityType severityType = ParsingHelpers.tryParseEnum(parameters.get(5), BugSeverityType.class);
        BugStatusType statusType = ParsingHelpers.tryParseEnum(parameters.get(6), BugStatusType.class);
        String assignee = parameters.get(7);

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Task task = createTask(taskType, title, description, priorityType, severityType, statusType, assignee);

        board.addTask(task);

        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task, board);
    }

    private Task createTask(TaskType taskType, String title, String description, PriorityType priorityType,
                           BugSeverityType severityType, BugStatusType statusType, String assignee) {
        return taskManagementRepository.createBug(title, description, priorityType, severityType, statusType, assignee);
    }
}
