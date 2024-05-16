package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ShowPersonActivityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    public ShowPersonActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String memberName = parameters.get(0);

        Member member = taskManagementRepository.findMemberByMemberName(memberName);

        return showMemberActivity(member);
    }

    private String showMemberActivity(Member member) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(String.format("~~~ MEMBER %s ACTIVITY ~~~",member.getMemberName().toUpperCase())).append(System.lineSeparator());
        for (Member person : taskManagementRepository.getMembers()) {
            sb.append(count).append(". ");
            sb.append(person.printHistory()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
