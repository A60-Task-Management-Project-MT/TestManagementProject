package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssignTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_TASK_TYPE_ERROR = "Invalid task type!";
    private TaskManagementRepository taskManagementRepository;

    public AssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class, INVALID_TASK_TYPE_ERROR);
        String filter = parameters.get(1);
        String personName = parameters.get(2);

        Member member = taskManagementRepository.findMemberByMemberName(personName);

        switch (taskType) {
            case BUG -> {
                List<Bug> bugs = taskManagementRepository.getBugs();
                bugs = filterBugsList(bugs, filter);
                bugs.stream().forEach(bug -> {
                    bug.setAssignee(member.getMemberName());
                    member.assignTask(bug);
                });

            }
            case STORY -> {
                List<Story> stories = taskManagementRepository.getStories();
                filterStoryList(stories, filter);
            }
            case FEEDBACK -> {
                List<Feedback> feedbacks = taskManagementRepository.getFeedbacks();
                filterFeedbackList(feedbacks, filter);
            }
        }


        return member.printTasks();
    }
    private List<Bug> filterBugsList(List<Bug> bugs, String filter) {
        List<Bug> filteredTasks = new ArrayList<>();
        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getPriority().toString().contains(filter))
                .toList());

        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getSeverity().toString().contains(filter))
                .toList());

        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getStatus().toString().contains(filter))
                .toList());

        return filteredTasks;
    }

    private List<Story> filterStoryList(List<Story> stories, String filter) {
        List<Story> filteredTasks = new ArrayList<>();
        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getPriority().toString().contains(filter))
                .toList());

        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getSize().toString().contains(filter))
                .toList());

        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getStatus().toString().contains(filter))
                .toList());

        return filteredTasks;
    }

    private List<Feedback> filterFeedbackList(List<Feedback> feedbacks, String filter) {
        List<Feedback> filteredTasks = new ArrayList<>();
        filteredTasks.addAll(feedbacks.stream()
                .filter(task -> task.getRating() == Integer.parseInt(String.valueOf(filter)))
                .toList());

        filteredTasks.addAll(feedbacks.stream()
                .filter(task -> task.getStatus().toString().contains(filter))
                .toList());

        return filteredTasks;
    }
}
