package com.company.oop.test.menagement.commands.listings;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.units.ListingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListBugsCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private List<Bug> bugs;
    private TaskManagementRepository taskManagementRepository;

    public ListBugsCommand(TaskManagementRepository taskManagementRepository) {
        this.bugs = taskManagementRepository.getBugs();
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String wordToSearch = parameters.get(0);
        String filterOrSort = parameters.get(1);

        return filteringOrSorting(filterOrSort, wordToSearch, bugs, taskManagementRepository);
    }

    private String filteringOrSorting(String filterOrSort, String wordToSearch, List<Bug> bugs, TaskManagementRepository taskManagementRepository) {
        switch (filterOrSort) {
            case "Filter":
                if (wordToSearch.equals("Active") || wordToSearch.equals("Done")) {
                    List<Bug> filteredStatusList = bugs.stream()
                            .filter(bug -> bug.getStatus().toString().equals(wordToSearch)).toList();

                    if (filteredStatusList.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There are no bugs found with status %s!", wordToSearch));
                    }
                    return ListingHelpers.elementsToString(filteredStatusList);

                } else if (taskManagementRepository.memberExist(wordToSearch)) {
                    List<Bug> filteredAssigneeList = bugs.stream()
                            .filter(bug -> bug.getAssignee().equals(wordToSearch)).toList();

                    if (filteredAssigneeList.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There are no bugs found with assignee %s!", wordToSearch));
                    }
                    return ListingHelpers.elementsToString(filteredAssigneeList);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid filter type %s!", wordToSearch));
                }
            case "Sort":
                if (wordToSearch.equals("Priority")) {
                    List<Bug> sortedPriorityBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getPriority)).toList();
                    return ListingHelpers.elementsToString(sortedPriorityBugs);

                } else if (wordToSearch.equals("Severity")) {
                    List<Bug> sortedSeverityBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getSeverity)).toList();
                    return ListingHelpers.elementsToString(sortedSeverityBugs);

                } else if (wordToSearch.equals("Title")) {
                    List<Bug> sortedTitleBugs = bugs.stream()
                            .sorted(Comparator.comparing(Bug::getTitle)).toList();
                    return ListingHelpers.elementsToString(sortedTitleBugs);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid searching type %s!", wordToSearch));
                }
            default:
                return String.format("Invalid searching or filtering type %s!", filterOrSort);
        }
    }
}
