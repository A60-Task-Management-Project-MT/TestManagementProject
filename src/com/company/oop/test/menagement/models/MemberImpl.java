package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Comment;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberImpl implements Member {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Member name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

    private String memberName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public MemberImpl(String memberName) {
        setMemberName(memberName);
        this.tasks = new ArrayList<>();
        this.histories = new ArrayList<>();

        createNewHistory(String.format("A new member with name %s was created!", memberName));
    }

    private void setMemberName(String memberName) {
        ValidationHelpers.validateStringLength(memberName, NAME_MIN_LENGTH, NAME_MAX_LENGTH, NAME_MIN_OR_MAX_LENGTH_ERROR);
        this.memberName = memberName;
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
            createNewHistory(String.format("A new %s task was assigned to member %s.", task, memberName));
        } else {
            throw new DuplicateEntityException(String.format("Task already assigned to member %s!", getMemberName()));
        }
    }

    @Override
    public void unassignTask(Task task) {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == task.getId());
        if (!exists) {
            throw new IllegalArgumentException(String.format("This member does not have a task with ID %s!", task.getId()));
        }

        tasks.remove(task);
        createNewHistory(String.format("A new %s task was unassigned from member %s.", task, memberName));
    }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {
        taskToAddComment.addComment(commentToAdd);

        createNewHistory(String.format("A new comment %s was add to task %s.", commentToAdd, taskToAddComment));
    }

    @Override
    public void removeComment(Comment commentToRemove, Task taskToRemoveComment) {
        taskToRemoveComment.removeComment(commentToRemove);

        createNewHistory(String.format("A comment %s was removed from task %s.", commentToRemove, taskToRemoveComment));
    }

    @Override
    public void createNewHistory(String activity) {
        histories.add(new ActivityHistoryImpl(activity));
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
    public String printHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("~~~ %s HISTORY ~~~", getMemberName().toUpperCase())).append(System.lineSeparator());
        if (histories.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE HISTORY ~~~").append(System.lineSeparator());
        } else {
            for (ActivityHistory activityHistory : histories) {
                builder.append(activityHistory.viewInfo()).append(System.lineSeparator());
            }
            builder.append("~~~ HISTORY ~~~").append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public String printTasks() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("~~~ %s TASKS ~~~", getMemberName().toUpperCase())).append(System.lineSeparator());
        if (tasks.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE TASKS ~~~").append(System.lineSeparator());
        } else {
            for (Task task : tasks) {
                builder.append(task.viewInfo()).append(System.lineSeparator());
            }
            builder.append("~~~ TASKS ~~~").append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberImpl member = (MemberImpl) o;
        return Objects.equals(memberName, member.memberName) && Objects.equals(tasks, member.tasks)
                && Objects.equals(histories, member.histories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberName, tasks, histories);
    }
}