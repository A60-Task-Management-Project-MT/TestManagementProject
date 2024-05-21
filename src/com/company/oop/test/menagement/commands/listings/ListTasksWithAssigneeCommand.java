package com.company.oop.test.menagement.commands.listings;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ListingHelpers;

import java.util.Comparator;
import java.util.List;

public class ListTasksWithAssigneeCommand implements Command {

    private List<Task> tasks;
    private TaskManagementRepository taskManagementRepository;

    public ListTasksWithAssigneeCommand(TaskManagementRepository taskManagementRepository) {
        this.tasks = taskManagementRepository.filterTasksWithAssignee(taskManagementRepository.getTasks());
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        String wordForFilter = parameters.get(0);
        String filterOrSort = parameters.get(1);

        return filterOrSortByWord(tasks, filterOrSort, wordForFilter, taskManagementRepository);
    }

    private String filterOrSortByWord(List<Task> tasks, String filterOrSort, String filterWord, TaskManagementRepository taskManagementRepository) {
        switch (filterOrSort) {
            case "Filter":
                if (filterWord.equals("Active") || filterWord.equals("Done") || filterWord.equals("Not Done") || filterWord.equals("In Progress")) {
                    List<Task> filteredTasks = tasks
                            .stream()
                            .filter(t -> t.getStatus().toString().equals(filterWord))
                            .toList();
                    if (filteredTasks.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There is not tasks with such a filter %s", filterWord));
                    }
                    return ListingHelpers.elementsToString(filteredTasks);

                } else if (taskManagementRepository.memberExist(filterWord)) {
                    List<Task> filteredTasksWithAssignee = tasks.stream()
                            .filter(task -> (task instanceof Bug && ((Bug) task).getAssignee().equals(filterWord)) ||
                                    (task instanceof Story && ((Story) task).getAssignee().equals(filterWord)))
                            .toList();
                    return ListingHelpers.elementsToString(filteredTasksWithAssignee);

                } else {
                    throw new ElementNotFoundException("Invalid searching type!");
                }
            case "Sort":
                List<Task> sortedTasks = tasks
                        .stream()
                        .sorted(Comparator.comparing(Task::getTitle))
                        .toList();
                return ListingHelpers.elementsToString(sortedTasks);
            default:
                throw new ElementNotFoundException("There is not such a filter type!");
        }
    }
}
