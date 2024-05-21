package com.company.oop.test.menagement.commands.listings;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.units.ListingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class ListFeedbacksCommand implements Command {
    private final static int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private List<Feedback> feedbacks;
    private TaskManagementRepository taskManagementRepository;

    public ListFeedbacksCommand(TaskManagementRepository taskManagementRepository) {
        this.feedbacks = taskManagementRepository.getFeedbacks();
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String filterOrSort = parameters.get(0);
        String wordToSearch = parameters.get(1);

        return filteringOrSorting(filterOrSort, wordToSearch, feedbacks);
    }

    private String filteringOrSorting(String filterOrSort, String wordToSearch, List<Feedback> feedbacks) {
        switch (filterOrSort) {
            case "Filter":
                if (wordToSearch.equals("New") || wordToSearch.equals("Unscheduled")
                        || wordToSearch.equals("Scheduled") || wordToSearch.equals("Done")) {
                    List<Feedback> filteredStatusList = feedbacks.stream()
                            .filter(feedback -> feedback.getStatus().toString().equals(wordToSearch)).toList();

                    if (filteredStatusList.isEmpty()) {
                        throw new ElementNotFoundException(String.format("There are no feedbacks found with status %s!", wordToSearch));
                    }
                    return ListingHelpers.elementsToString(filteredStatusList);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid filter type %s!", wordToSearch));
                }
            case "Sort":
                if (wordToSearch.equals("Rating")) {
                    List<Feedback> sortedRatingFeedbacks = feedbacks.stream()
                            .sorted(Comparator.comparing(Feedback::getRating)).toList();
                    return ListingHelpers.elementsToString(sortedRatingFeedbacks);

                } else if (wordToSearch.equals("Title")) {
                    List<Feedback> sortedTitleFeedbacks = feedbacks.stream()
                            .sorted(Comparator.comparing(Feedback::getTitle)).toList();
                    return ListingHelpers.elementsToString(sortedTitleFeedbacks);

                } else {
                    throw new ElementNotFoundException(String.format("Invalid searching type %s!", wordToSearch));
                }
            default:
                return String.format("Invalid searching or filtering type %s!", filterOrSort);
        }
    }
}
