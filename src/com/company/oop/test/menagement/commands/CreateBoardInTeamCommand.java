package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateBoardInTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private TaskManagementRepository taskManagementRepository;

    public CreateBoardInTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        String teamName = parameters.get(1);

        Board board = createBoard(boardName);
        Teams team = taskManagementRepository.findTeamByTeamName(teamName);

        team.addBoard(board);
        return String.format("Board with name %s was successfully added to team %s!",boardName,teamName);
    }
    private Board createBoard(String boardName) {
        return taskManagementRepository.createBoard(boardName);
    }
}
