package com.company.oop.test.menagement.core;

import com.company.oop.test.menagement.commands.*;
import com.company.oop.test.menagement.commands.changebug.ChangeBugPriorityCommand;
import com.company.oop.test.menagement.commands.changebug.ChangeBugSeverityCommand;
import com.company.oop.test.menagement.commands.changebug.ChangeBugStatusCommand;
import com.company.oop.test.menagement.commands.changefeedback.ChangeFeedbackRatingCommand;
import com.company.oop.test.menagement.commands.changefeedback.ChangeFeedbackStatusCommand;
import com.company.oop.test.menagement.commands.changestory.ChangeStoryPriorityCommand;
import com.company.oop.test.menagement.commands.changestory.ChangeStorySizeCommand;
import com.company.oop.test.menagement.commands.changestory.ChangeStoryStatusCommand;
import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.commands.CreateNewPersonCommand;
import com.company.oop.test.menagement.commands.enums.CommandType;
import com.company.oop.test.menagement.core.contracts.CommandFactory;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.units.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
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
//            case CREATE_TASK -> {
//                return new CreateNewTaskCommand(taskManagementRepository);
//            }
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
                return new ShowBoardsActivityCommand(taskManagementRepository);
            }
            case SHOW_PERSONS_ACTIVITY -> {
                return new ShowPersonsActivityCommand(taskManagementRepository);
            }
            case SHOW_TEAMS_ACTIVITY -> {
                return new ShowTeamsActivityCommand(taskManagementRepository);
            }
            case CHANGE_BUG_PRIORITY -> {
                return new ChangeBugPriorityCommand(taskManagementRepository);
            }
            case CHANGE_BUG_SEVERITY -> {
                return new ChangeBugSeverityCommand(taskManagementRepository);
            }
            case CHANGE_BUG_STATUS -> {
                return new ChangeBugStatusCommand(taskManagementRepository);
            }
            case CHANGE_FEEDBACK_RATING -> {
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            }
            case CHANGE_FEEDBACK_STATUS -> {
                return new ChangeFeedbackStatusCommand(taskManagementRepository);
            }
            case CHANGE_STORY_PRIORITY -> {
                return new ChangeStoryPriorityCommand(taskManagementRepository);
            }
            case CHANGE_STORY_SIZE -> {
                return new ChangeStorySizeCommand(taskManagementRepository);
            }
            case CHANGE_STORY_STATUS -> {
                return new ChangeStoryStatusCommand(taskManagementRepository);
            }
            default:
                throw new IllegalArgumentException();
        }
    }
}
