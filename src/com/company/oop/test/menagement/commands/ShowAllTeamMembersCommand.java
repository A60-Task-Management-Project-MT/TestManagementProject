package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembersCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    public ShowAllTeamMembersCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);

        Teams team = taskManagementRepository.findTeamByTeamName(teamName);

        return team.printMembers();
    }

//    private String showTeamMembers(Teams team) {
//        if (team.getMembers().isEmpty()) {
//            throw new ElementNotFoundException(String.format("Team %s does not have active members!", team.getName()));
//        }
//
//        StringBuilder sb = new StringBuilder();
//        int count = 1;
//        sb.append(String.format("~~~ TEAM %s MEMBERS ~~~", team.getName())).append(System.lineSeparator());
//        for (Member member : team.getMembers()) {
//            sb.append(count).append(". ");
//            sb.append(member.getMemberName()).append(System.lineSeparator());
//            count++;
//        }
//        return sb.toString().trim();
//    }
}
