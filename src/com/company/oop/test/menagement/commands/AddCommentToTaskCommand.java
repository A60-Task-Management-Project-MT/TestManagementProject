package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class AddCommentToTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_PARAMETER_ERROR = "You need task ID as number to write comment!";
    private TaskManagementRepository taskManagementRepository;

    public AddCommentToTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String content = parameters.get(0);
        String author = parameters.get(1);
        int taskId = ParsingHelpers.tryParseInt(parameters.get(2), INVALID_PARAMETER_ERROR);

        taskManagementRepository.findMemberByMemberName(author);
        Comment comment = createComment(author, content);
        Task task = taskManagementRepository.findTaskById(taskId);

        task.addComment(comment);

        return String.format("Comment successfully added to task %s with ID %d!", task.getTaskType(), taskId);
    }

    private Comment createComment(String author, String content) {
        return taskManagementRepository.createComment(author, content);
    }
}
