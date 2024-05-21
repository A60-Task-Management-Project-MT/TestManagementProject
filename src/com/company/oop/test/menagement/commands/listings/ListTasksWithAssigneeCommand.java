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

        String filterOrSort = parameters.get(0);

        if (parameters.size() == 2 && filterOrSort.equals("Filter")) {
            String wordForFilter = parameters.get(1);
            return filterByWord(tasks, wordForFilter, taskManagementRepository);
        } else if (parameters.size() == 1 && filterOrSort.equals("Sort")) {
            return sort(tasks);
        } else {
            throw new IllegalArgumentException("Invalid number of arguments. Expect 1 parameter if sorting and 2 if filtering!");
        }
    }

    private String filterByWord(List<Task> tasks, String filterWord, TaskManagementRepository taskManagementRepository) {
        if (filterWord.equals("Active") || filterWord.equals("Done") || filterWord.equals("Not done") || filterWord.equals("In progress")) {
            List<Task> filteredTasks = tasks
                    .stream()
                    .filter(t -> t.getStatus().toString().equals(filterWord))
                    .toList();
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
    }

    private String sort(List<Task> tasks) {
        List<Task> sortedTasks = tasks
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
        return ListingHelpers.elementsToString(sortedTasks);
    }
}
