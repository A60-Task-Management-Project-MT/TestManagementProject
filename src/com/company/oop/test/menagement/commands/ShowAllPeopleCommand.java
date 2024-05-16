package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Member;

import java.util.List;

public class ShowAllPeopleCommand implements Command {

    private TaskManagementRepository taskManagementRepository;

    public ShowAllPeopleCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        return showMembers();
    }

    private String showMembers() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("~~~ ALL COMPANY MEMBERS ~~~").append(System.lineSeparator());
        sb.append(count).append(". ");
        for (Member member : taskManagementRepository.getMembers()) {
            sb.append(member.getMemberName()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
