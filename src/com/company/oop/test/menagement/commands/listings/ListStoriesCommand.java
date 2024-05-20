package com.company.oop.test.menagement.commands.listings;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.units.ListingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListStoriesCommand implements Command {

    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private List<Story> stories;
    private TaskManagementRepository taskManagementRepository;

    public ListStoriesCommand(TaskManagementRepository taskManagementRepository) {
        this.stories = taskManagementRepository.getStories();
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String filterOrSort = parameters.get(0);
        String wordToSearch = parameters.get(1);

        return filteringOrSorting(filterOrSort, wordToSearch, stories, taskManagementRepository);
    }

    private String filteringOrSorting(String filterOrSort, String wordToSearch, List<Story> stories, TaskManagementRepository taskManagementRepository) {
        switch (filterOrSort) {
            case "Filter":
                if (wordToSearch.equals("Not Done") || wordToSearch.equals("In Progress") || wordToSearch.equals("Done")) {
                    List<Story> filteredStatusList = stories.stream()
                            .filter(story -> story.getStatus().toString().equals(wordToSearch)).toList();

                    if (filteredStatusList.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There are no stories found with status %s!", wordToSearch));
                    }
                    return ListingHelpers.elementsToString(filteredStatusList);

                } else if (taskManagementRepository.memberExist(wordToSearch)) {
                    List<Story> filteredAssigneeList = stories.stream()
                            .filter(story -> story.getAssignee().equals(wordToSearch)).toList();

                    if (filteredAssigneeList.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There are no stories found with assignee %s!", wordToSearch));
                    }
                    return ListingHelpers.elementsToString(filteredAssigneeList);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid filter type %s!", wordToSearch));
                }
            case "Sort":
                if (wordToSearch.equals("Priority")) {
                    List<Story> sortedPriorityStory = stories.stream()
                            .sorted(Comparator.comparing(Story::getPriority)).toList();
                    return ListingHelpers.elementsToString(sortedPriorityStory);

                } else if (wordToSearch.equals("Size")) {
                    List<Story> sortedSizeStory = stories.stream()
                            .sorted(Comparator.comparing(Story::getSize)).toList();
                    return ListingHelpers.elementsToString(sortedSizeStory);

                } else if (wordToSearch.equals("Title")) {
                    List<Story> sortedTitleStory = stories.stream()
                            .sorted(Comparator.comparing(Story::getTitle)).toList();
                    return ListingHelpers.elementsToString(sortedTitleStory);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid searching type %s!", wordToSearch));
                }
            default:
                return String.format("Invalid searching or filtering type %s!", filterOrSort);
        }
    }
}
