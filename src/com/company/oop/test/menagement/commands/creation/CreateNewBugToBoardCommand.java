package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.contracts.Team;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

import static com.company.oop.test.menagement.commands.creation.CreateNewStoryToBoardCommand.MEMBER_NOT_PART_OF_A_TEAM_WITH_BOARD_ERROR;

public class CreateNewBugToBoardCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s with ID %s added to board %s with assignee %s!";
    private static final String INVALID_PRIORITY_TYPE = "Invalid priority type!";
    private static final String INVALID_SEVERITY_TYPE = "Invalid severity type!";


    private final TaskManagementRepository taskManagementRepository;

    public CreateNewBugToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        List<String> steps = Arrays.asList(parameters.get(2).split("; "));
        PriorityType priorityType = ParsingHelpers.tryParseEnum(parameters.get(3), PriorityType.class, INVALID_PRIORITY_TYPE);
        BugSeverityType severityType = ParsingHelpers.tryParseEnum(parameters.get(4), BugSeverityType.class, INVALID_SEVERITY_TYPE);
        String assignee = parameters.get(5);
        String boardName = parameters.get(6);

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Member member = taskManagementRepository.findMemberByMemberName(assignee);

        Team team = taskManagementRepository.findTeamByBoardName(board);

        if (!team.getMembers().contains(member)) {
            throw new IllegalArgumentException(String.format(MEMBER_NOT_PART_OF_A_TEAM_WITH_BOARD_ERROR, assignee, team.getName(), boardName));
        }
        Task task = createBug(title, description, priorityType, severityType, assignee, steps);

        member.assignTask(task);
        board.addTask(task);


        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task.getTaskType(), task.getId(), boardName, assignee);
    }

    private Task createBug(String title, String description, PriorityType priorityType,
                            BugSeverityType severityType, String assignee, List<String> steps) {
        return taskManagementRepository.createBug(title, description, priorityType, severityType, assignee, steps);
    }
}
