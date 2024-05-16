package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class AssignTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID should be a number!";
    private TaskManagementRepository taskManagementRepository;

    public AssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR_MESSAGE);
        String personName = parameters.get(1);

        Task task = taskManagementRepository.findTaskById(id);
        Member member = taskManagementRepository.findMemberByMemberName(personName);

        if (member.getTasks().contains(task)) {
            throw new IllegalArgumentException("Task already assigned to this person!");
        }

        member.assignTask(task);

        return String.format("Task %s was assigned to member %s!", task.getTaskType(), personName);
    }
}
