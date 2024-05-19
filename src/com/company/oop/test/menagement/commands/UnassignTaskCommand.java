package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class UnassignTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private TaskManagementRepository taskManagementRepository;

    public UnassignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> parameters) {
        List<Task> tasks = taskManagementRepository.getTasks();
        tasks = filterTaskList(tasks);
        tasks.forEach(task -> taskManagementRepository.unassignTask(task));

        return "All tasks with status Done unassigned!";
    }

    private List<Task> filterTaskList(List<Task> tasks) {
        List<Task> filteredTasks = new ArrayList<>();

        filteredTasks.addAll(taskManagementRepository.getTasks().stream()
                .filter(task -> task.getStatus().toString().equals("Done")).toList());

        return filteredTasks;
    }
}
