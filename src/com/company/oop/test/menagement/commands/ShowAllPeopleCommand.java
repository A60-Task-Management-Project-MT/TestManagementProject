package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
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
        if (taskManagementRepository.getMembers().isEmpty()) {
            throw new ElementNotFoundException("No active members!");
        }

        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("~~~ ALL COMPANY MEMBERS ~~~").append(System.lineSeparator());
        for (Member member : taskManagementRepository.getMembers()) {
            sb.append(count).append(". ");
            sb.append(member.getMemberName()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
