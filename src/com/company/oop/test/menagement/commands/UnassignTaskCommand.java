package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class UnassignTaskCommand implements Command {

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

        filteredTasks.addAll(tasks.stream()
                .filter(task -> task.getStatus().toString().equals("Done")).toList());

        if (filteredTasks.isEmpty()) {
            throw new ElementNotFoundException("No tasks with status Done were found!");
        }

        return filteredTasks;
    }
}
