package com.company.oop.test.menagement.commands.changebug;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ChangeBugSeverityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID needs to be a number!";
    public static final String BUG_SEVERITY_CHANGE_MESSAGE = "Severity of task %s with ID %d was changed to %s!";
    private static final String INVALID_ENUM_TYPE = "Invalid severity type!";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeBugSeverityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR_MESSAGE);
        BugSeverityType newSeverityType = ParsingHelpers.tryParseEnum(parameters.get(1), BugSeverityType.class, INVALID_ENUM_TYPE);

        Bug task = taskManagementRepository.findBugById(id);
        task.changeSeverity(newSeverityType);

        return String.format(BUG_SEVERITY_CHANGE_MESSAGE, task.getTaskType(), task.getId(), newSeverityType);
    }
}
