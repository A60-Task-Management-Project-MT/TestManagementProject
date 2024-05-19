package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class CreateNewPersonCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private TaskManagementRepository taskManagementRepository;

    public CreateNewPersonCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String personName = parameters.get(0);

        return createPerson(personName);
    }

    private String createPerson(String personName) {
        if (taskManagementRepository.memberExist(personName)) {
            throw new DuplicateEntityException(String.format("Person with name %s already exist!", personName));
        }
        taskManagementRepository.createMember(personName);
        return String.format("Person with name %s was created!", personName);
    }
}
