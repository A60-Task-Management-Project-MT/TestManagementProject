package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class AddPersonToTeamCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private TaskManagementRepository taskManagementRepository;

    public AddPersonToTeamCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String personName = parameters.get(0);
        String teamName = parameters.get(1);

        Member member = taskManagementRepository.findMemberByMemberName(personName);
        Teams teams = taskManagementRepository.findTeamByTeamName(teamName);

        teams.addMember(member);

        return String.format("Member with name %s successfully added to team %s!", personName, teamName);
    }
}
