package com.company.oop.test.menagement.commands.changestory;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ChangeStorySizeCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID needs to be a number!";
    public static final String STORY_SIZE_CHANGE_MESSAGE = "Size of task %s with ID %d was changed to %s!";
    private static final String INVALID_ENUM_TYPE = "Invalid size type!";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeStorySizeCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR_MESSAGE);
        StorySizeType newSizeType = ParsingHelpers.tryParseEnum(parameters.get(1), StorySizeType.class, INVALID_ENUM_TYPE);

        Story task = taskManagementRepository.findStoryById(id);
        task.changeSize(newSizeType);

        return String.format(STORY_SIZE_CHANGE_MESSAGE, task.getTaskType(), task.getId(), newSizeType);
    }
}
