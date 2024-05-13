package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Comment;
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

    private String memberName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public MemberImpl(String memberName, List<Task> tasks) {
        setMemberName(memberName);
        this.tasks = new ArrayList<>();
        this.histories = new ArrayList<>();
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
        tasks.add(task);
    }

    @Override
    public void unassignTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public void addComment(Comment commentToAdd, Task taskToAddComment) {
        taskToAddComment.addComment(commentToAdd);
    }

    @Override
    public void removeComment(Comment commentToRemove, Task taskToRemoveComment) {
        taskToRemoveComment.removeComment(commentToRemove);
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return new ArrayList<>(histories);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
}
