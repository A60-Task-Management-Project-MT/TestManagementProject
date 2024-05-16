package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoardsCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    public ShowAllTeamBoardsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);

        Teams team = taskManagementRepository.findTeamByTeamName(teamName);

        return showTeamBoards(team);
    }

    private String showTeamBoards(Teams team) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(String.format("~~~ TEAM %s BOARDS ~~~", team.getName())).append(System.lineSeparator());
        for (Board board : team.getBoards()) {
            sb.append(count).append(". ");
            sb.append(board.getBoardName()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
