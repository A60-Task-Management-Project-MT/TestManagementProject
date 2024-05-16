package com.company.oop.test.menagement.core;

import com.company.oop.test.menagement.commands.*;
import com.company.oop.test.menagement.commands.changebug.ChangeBugPriorityCommand;
import com.company.oop.test.menagement.commands.changebug.ChangeBugSeverityCommand;
import com.company.oop.test.menagement.commands.changefeedback.ChangeFeedbackRatingCommand;
import com.company.oop.test.menagement.commands.changestory.ChangeStoryPriorityCommand;
import com.company.oop.test.menagement.commands.changestory.ChangeStorySizeCommand;
import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.commands.CreateNewPersonCommand;
import com.company.oop.test.menagement.commands.creation.CreateNewBugToBoardCommand;
import com.company.oop.test.menagement.commands.creation.CreateNewFeedbackToBoardCommand;
import com.company.oop.test.menagement.commands.creation.CreateNewStoryToBoardCommand;
import com.company.oop.test.menagement.commands.enums.CommandType;
import com.company.oop.test.menagement.core.contracts.CommandFactory;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.units.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class, String.format(INVALID_COMMAND_MESSAGE, commandTypeAsString));
        switch (commandType) {
            case ADD_COMMENT -> {
                return new AddCommentToTaskCommand(taskManagementRepository);
            }
            case ADD_PERSON -> {
                return new AddPersonToTeamCommand(taskManagementRepository);
            }
            case CREATE_BOARD -> {
                return new CreateBoardInTeamCommand(taskManagementRepository);
            }
            case CREATE_PERSON -> {
                return new CreateNewPersonCommand(taskManagementRepository);
            }
            case CREATE_BUG -> {
                return new CreateNewBugToBoardCommand(taskManagementRepository);
            }
            case CREATE_STORY -> {
                return new CreateNewStoryToBoardCommand(taskManagementRepository);
            }
            case CREATE_FEEDBACK -> {
                return new CreateNewFeedbackToBoardCommand(taskManagementRepository);
            }
            case CREATE_TEAM -> {
                return new CreateNewTeamCommand(taskManagementRepository);
            }
            case SHOW_PEOPLE -> {
                return new ShowAllPeopleCommand(taskManagementRepository);
            }
            case SHOW_TEAM_BOARDS -> {
                return new ShowAllTeamBoardsCommand(taskManagementRepository);
            }
            case SHOW_TEAM_MEMBERS -> {
                return new ShowAllTeamMembersCommand(taskManagementRepository);
            }
            case SHOW_TEAMS -> {
                return new ShowAllTeamsCommand(taskManagementRepository);
            }
            case SHOW_BOARDS_ACTIVITY -> {
                return new ShowBoardActivityCommand(taskManagementRepository);
            }
            case SHOW_PERSONS_ACTIVITY -> {
                return new ShowPersonActivityCommand(taskManagementRepository);
            }
            case SHOW_TEAMS_ACTIVITY -> {
                return new ShowTeamActivityCommand(taskManagementRepository);
            }
            case CHANGE_BUG_PRIORITY -> {
                return new ChangeBugPriorityCommand(taskManagementRepository);
            }
            case CHANGE_BUG_SEVERITY -> {
                return new ChangeBugSeverityCommand(taskManagementRepository);
            }
            case CHANGE_TASK_STATUS -> {
                return new ChangeTaskStatus(taskManagementRepository);
            }
            case CHANGE_FEEDBACK_RATING -> {
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            }
            case CHANGE_STORY_PRIORITY -> {
                return new ChangeStoryPriorityCommand(taskManagementRepository);
            }
            case CHANGE_STORY_SIZE -> {
                return new ChangeStorySizeCommand(taskManagementRepository);
            }
            default ->
                throw new IllegalArgumentException(String.format(INVALID_COMMAND_MESSAGE, commandTypeAsString));
        }
    }
}
