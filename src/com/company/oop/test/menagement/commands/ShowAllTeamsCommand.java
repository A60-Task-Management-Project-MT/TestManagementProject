package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Team;

import java.util.List;

public class ShowAllTeamsCommand implements Command {

    private TaskManagementRepository taskManagementRepository;

    public ShowAllTeamsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return showTeams();
    }

    private String showTeams() {
        if (taskManagementRepository.getTeams().isEmpty()) {
            throw new ElementNotFoundException("No teams created!");
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("~~~ TEAMS ~~~").append(System.lineSeparator());
        for (Team team : taskManagementRepository.getTeams()) {
            sb.append(count).append(". ");
            sb.append(team.getName()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
