package com.company.oop.test.menagement.commands.listings;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ListingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListAllTaskCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_TASKS_FOUND_ERROR = "There are no tasks found with %s word in title!";

    private List<Task> tasks;

    public ListAllTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.tasks = taskManagementRepository.getTasks();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        if (tasks.isEmpty()) {
            return "There are no registered tasks!";
        }
        String filterWordToFind = parameters.get(0);
        String filterOrSort = parameters.get(1);

        return filteringOrSorting(filterOrSort, filterWordToFind, tasks);
    }

    private String filteringOrSorting(String filterOrSort, String filterWordToFind, List<Task> tasks) {
        if (filterOrSort.equals("Sort")) {
            List<Task> sortedTasks = tasks.stream()
                    .sorted(Comparator.comparing(Task::getTitle)).toList();

            return ListingHelpers.elementsToString(sortedTasks);
        } else {
            //TODO:
            List<Task> filteredTask = tasks.stream()
                    .filter(task -> task.getTitle().contains(filterWordToFind)).toList();
            if (filteredTask.isEmpty()) {
                throw new ElementNotFoundException(String.format(NO_TASKS_FOUND_ERROR, filterOrSort));
            }
            return ListingHelpers.elementsToString(filteredTask);
        }
    }
}
