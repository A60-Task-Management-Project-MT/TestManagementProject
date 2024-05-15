package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateNewTeamCommand implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementRepository taskManagementRepository;

    public CreateNewTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);

        return createTeam(teamName);
    }

    private String createTeam(String teamName) {
        if (taskManagementRepository.teamExist(teamName)) {
            throw new DuplicateEntityException(String.format("Team with name %s already exist!", teamName));
        }
        taskManagementRepository.createTeam(teamName);
        return String.format("Team with name %s was created!", teamName);
    }
}
