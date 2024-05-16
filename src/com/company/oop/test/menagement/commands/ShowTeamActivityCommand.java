package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ShowTeamActivityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    public ShowTeamActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        Teams team = taskManagementRepository.findTeamByTeamName(teamName);

        return showTeamActivity(team);
    }

    private String showTeamActivity(Teams team) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(String.format("~~~ TEAM %s ACTIVITY ~~~", team.getName().toUpperCase())).append(System.lineSeparator());
        for (Board board : team.getBoards()) {
            sb.append(count).append(". ");
            sb.append(board.printHistory()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
