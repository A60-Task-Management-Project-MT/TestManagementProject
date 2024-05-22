package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Member name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    public static final String UNASSIGNE_MESSAGE = "%s task was unassigned from member %s.";
    public static final String TASK_NOT_EXIST_ERROR_MESSAGE = "This member does not have a task with ID %s!";
    public static final String TASK_ALREADY_ASSIGNED_ERROR_MESSAGE = "Task already assigned to member %s!";
    public static final String NEW_TASK_ASSIGNED_MESSAGE = "A new %s task was assigned to member %s.";
    public static final String NEW_MEMBER_CREATION_MESSAGE = "A new member with name %s was created!";

    private String memberName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public MemberImpl(String memberName) {
        setMemberName(memberName);
        this.tasks = new ArrayList<>();
        this.histories = new ArrayList<>();

        createNewHistory(String.format(NEW_MEMBER_CREATION_MESSAGE, memberName));
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return new ArrayList<>(histories);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public String getMemberName() {
        return memberName;
    }

    @Override
    public void assignTask(Task task) {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == task.getId());
        if (!exists) {
            tasks.add(task);
            createNewHistory(String.format(NEW_TASK_ASSIGNED_MESSAGE, task.getTaskType(), memberName));
        } else {
            throw new DuplicateEntityException(String.format(TASK_ALREADY_ASSIGNED_ERROR_MESSAGE, getMemberName()));
        }
    }


    @Override
    public void unassignTask(Task task) {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == task.getId());
        if (!exists) {
            throw new IllegalArgumentException(String.format(TASK_NOT_EXIST_ERROR_MESSAGE, task.getId()));
        }

        tasks.remove(task);
        createNewHistory(String.format(UNASSIGNE_MESSAGE, task.getTaskType(), memberName));
    }

    @Override
    public void createNewHistory(String activity) {
        histories.add(new ActivityHistoryImpl(activity));
    }

    @Override
    public String displayFullHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("~~~ MEMBER %s HISTORY ~~~", getMemberName().toUpperCase())).append(System.lineSeparator());
        if (histories.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE HISTORY ~~~").append(System.lineSeparator());
        } else {
            for (ActivityHistory activityHistory : histories) {
                builder.append(activityHistory.viewInfo()).append(System.lineSeparator());
            }
            builder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~").append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private void setMemberName(String memberName) {
        ValidationHelpers.validateStringLength(memberName, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_MIN_OR_MAX_LENGTH_ERROR);
        this.memberName = memberName;
    }
}