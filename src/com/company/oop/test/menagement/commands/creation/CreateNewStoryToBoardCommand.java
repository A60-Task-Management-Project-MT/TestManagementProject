package com.company.oop.test.menagement.commands.creation;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.contracts.Team;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateNewStoryToBoardCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private static final String TASK_SUCCESSFULLY_ADDED_TO_BOARD = "Task %s with ID %d added to board %s!";
    private static final String INVALID_PRIORITY_TYPE = "Invalid priority type!";
    private static final String INVALID_SIZE_TYPE = "Invalid size type!";
    public static final String MEMBER_NOT_PART_OF_A_TEAM_WITH_BOARD_ERROR = "Member %s is not part of a team %s which got the board with name %s!";

    private final TaskManagementRepository taskManagementRepository;

    public CreateNewStoryToBoardCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String title = parameters.get(0);
        String description = parameters.get(1);
        PriorityType priorityType = ParsingHelpers.tryParseEnum(parameters.get(2), PriorityType.class, INVALID_PRIORITY_TYPE);
        StorySizeType storySizeType = ParsingHelpers.tryParseEnum(parameters.get(3), StorySizeType.class, INVALID_SIZE_TYPE);
        String assignee = parameters.get(4);
        String boardName = parameters.get(5);

        Board board = taskManagementRepository.findBoardByBoardName(boardName);
        Member member = taskManagementRepository.findMemberByMemberName(assignee);

        Team team = taskManagementRepository.findTeamByBoardName(board);

        if (!team.getMembers().contains(member)) {
            throw new IllegalArgumentException(String.format(MEMBER_NOT_PART_OF_A_TEAM_WITH_BOARD_ERROR, assignee, team.getName(), boardName));
        }
        Task task = createStory(title, description, priorityType, storySizeType, assignee);

        member.assignTask(task);
        board.addTask(task);

        return String.format(TASK_SUCCESSFULLY_ADDED_TO_BOARD, task.getTaskType(), task.getId(), boardName);
    }
    private Task createStory(String title, String description, PriorityType priorityType,
                             StorySizeType storySizeType, String assignee) {
        return taskManagementRepository.createStory(title,description,priorityType,storySizeType,assignee);
    }
}
