package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Teams;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    private TaskManagementRepository taskManagementRepository;

    public ShowAllTeamsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("~~~ TEAMS ~~~").append(System.lineSeparator());
        for (Teams team : taskManagementRepository.getTeams()) {
            sb.append(count).append(". ");
            sb.append(team.getName()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
