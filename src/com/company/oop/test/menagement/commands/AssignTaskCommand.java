package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String ID_ERROR_MESSAGE = "ID should be a number!";
    private static final String INVALID_TASK_TYPE_ERROR = "Invalid task type!";
    private TaskManagementRepository taskManagementRepository;

    public AssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class, INVALID_TASK_TYPE_ERROR);
        String personName = parameters.get(1);


        List<Task> filteredTasks = taskManagementRepository.findTasksByTaskType(taskType);
        Member member = taskManagementRepository.findMemberByMemberName(personName);

        //assigntask Bug Critical Pesho

        //String filter = parameters.get(2);
        //List<Task> filteredTasks = taskManagementRepository.getTasks().stream.filter(t -> t.contains(filter)

        if (member.getTasks().contains(task)) {
            throw new IllegalArgumentException("Task already assigned to this person!");
        }

//        member.assignTask(filteredTasks);

        return String.format("Task %s was assigned to member %s!", task.getTaskType(), personName);
    }

    private static List<Task> assignTasks(List<Task> tasks, String filterWord) {
        List<Task> filteredTasks = new ArrayList<>();

        filteredTasks.addAll(tasks.stream()
                .filter(task -> task.getPriority().toString().contains(word))
                .collect(Collectors.toList()));

        // Filter by SeverityType enum
        filteredTasks.addAll(tasks.stream()
                .filter(task -> task.getSeverity().toString().contains(word))
                .collect(Collectors.toList()));

        // Filter by SizeType enum
        filteredTasks.addAll(tasks.stream()
                .filter(task -> task.getSize().toString().contains(word))
                .collect(Collectors.toList()));

        // Filter by Status enum
        filteredTasks.addAll(tasks.stream()
                .filter(task -> task.getStatus().toString().contains(word))
                .collect(Collectors.toList()));

    }
}
