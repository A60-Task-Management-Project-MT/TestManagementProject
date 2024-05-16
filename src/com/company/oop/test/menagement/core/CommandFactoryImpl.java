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
            case ADDCOMMENT:
                return new AddCommentToTaskCommand(taskManagementRepository);
            case ADDPERSON:
                return new AddPersonToTeamCommand(taskManagementRepository);
            case ASSIGNTASK:
                return new AssignTaskCommand(taskManagementRepository);
            case UNASSIGNTASK:
                return new UnassignTaskCommand(taskManagementRepository);
            case CREATEBOARD:
                return new CreateBoardInTeamCommand(taskManagementRepository);
            case CREATEPERSON:
                return new CreateNewPersonCommand(taskManagementRepository);
            case CREATEBUG:
                return new CreateNewBugToBoardCommand(taskManagementRepository);
            case CREATESTORY:
                return new CreateNewStoryToBoardCommand(taskManagementRepository);
            case CREATEFEEDBACK:
                return new CreateNewFeedbackToBoardCommand(taskManagementRepository);
            case CREATETEAM:
                return new CreateNewTeamCommand(taskManagementRepository);
            case SHOWPEOPLE:
                return new ShowAllPeopleCommand(taskManagementRepository);
            case SHOWTEAMBOARDS:
                return new ShowAllTeamBoardsCommand(taskManagementRepository);
            case SHOWTEAMMEMBERS:
                return new ShowAllTeamMembersCommand(taskManagementRepository);
            case SHOWTEAMS:
                return new ShowAllTeamsCommand(taskManagementRepository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivityCommand(taskManagementRepository);
            case SHOWPERSONSACTIVITY:
                return new ShowPersonActivityCommand(taskManagementRepository);
            case SHOWTEAMSACTIVITY:
                return new ShowTeamActivityCommand(taskManagementRepository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriorityCommand(taskManagementRepository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverityCommand(taskManagementRepository);
            case CHANGETASKSTATUS:
                return new ChangeTaskStatus(taskManagementRepository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRatingCommand(taskManagementRepository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriorityCommand(taskManagementRepository);
            case CHANGESTORYSIZE:
                return new ChangeStorySizeCommand(taskManagementRepository);
            default:
                throw new IllegalArgumentException(String.format(INVALID_COMMAND_MESSAGE, commandTypeAsString));
        }
    }
}
