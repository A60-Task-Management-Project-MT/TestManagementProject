package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.commands.enums.SeverityStatusPrioritySizeEnum;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.units.ParsingHelpers;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_TASK_TYPE_ERROR = "Invalid task type!";
    public static final String INVALID_VALUE_FOR_ENUM_ERROR = "Invalid value for enum!";
    private TaskManagementRepository taskManagementRepository;

    public AssignTaskCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        TaskType taskType = ParsingHelpers.tryParseEnum(parameters.get(0), TaskType.class, INVALID_TASK_TYPE_ERROR);
        String filter = String.valueOf(ParsingHelpers.tryParseEnum(parameters.get(1), SeverityStatusPrioritySizeEnum.class, INVALID_VALUE_FOR_ENUM_ERROR));
        String personName = parameters.get(2);

        Member member = taskManagementRepository.findMemberByMemberName(personName);
        int countOfTasks = 0;

        switch (taskType) {
            case BUG -> {
                List<Bug> bugs = taskManagementRepository.getBugs();
                bugs = filterBugsList(bugs, filter);
                bugs.stream().forEach(bug -> {
                    bug.changeAssignee(member.getMemberName());
                    member.assignTask(bug);
                    taskManagementRepository.unassignTask(bug);
                });
                countOfTasks += bugs.size();
            }
            case STORY -> {
                List<Story> stories = taskManagementRepository.getStories();
                stories = filterStoryList(stories, filter);
                stories.stream().forEach(story -> {
                    story.changeAssignee(member.getMemberName());
                    member.assignTask(story);
                    taskManagementRepository.unassignTask(story);
                });
                countOfTasks += stories.size();
            }
            default -> throw new IllegalArgumentException("Feedbacks can not have assignees!");

        }
        return String.format("New %d %s tasks were added to person %s!", countOfTasks, taskType, member.getMemberName());
    }

    private List<Bug> filterBugsList(List<Bug> bugs, String filter) {
        if (bugs.isEmpty()) {
            throw new ElementNotFoundException("No tasks of type Bug are available at the moment");
        }

        List<Bug> filteredTasks = new ArrayList<>();
        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getPriority().toString().equals(filter))
                .toList());

        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getSeverity().toString().equals(filter))
                .toList());

        filteredTasks.addAll(bugs.stream()
                .filter(task -> task.getStatus().toString().equals(filter))
                .toList());

        if (filteredTasks.isEmpty()) {
            throw new ElementNotFoundException(String.format("No tasks of type Bug with specification %s were found!", filter));
        }
        return filteredTasks;
    }

    private List<Story> filterStoryList(List<Story> stories, String filter) {
        List<Story> filteredTasks = new ArrayList<>();
        if (stories.isEmpty()) {
            throw new ElementNotFoundException(String.format("No tasks of type Story with specification %s were found!", filter));
        }

        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getPriority().toString().equals(filter))
                .toList());

        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getSize().toString().equals(filter))
                .toList());

        filteredTasks.addAll(stories.stream()
                .filter(task -> task.getStatus().toString().equals(filter))
                .toList());

        if (filteredTasks.isEmpty()) {
            throw new ElementNotFoundException(String.format("No tasks of type Story with specification %s were found!", filter));
        }

        return filteredTasks;
    }
}
