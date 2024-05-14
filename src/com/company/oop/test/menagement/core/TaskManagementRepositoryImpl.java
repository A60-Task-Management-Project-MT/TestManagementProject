package com.company.oop.test.menagement.core;

import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;

import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {
    private int nextId;
    private final List<Board> boards;
    private final List<Task> tasks;

    public TaskManagementRepositoryImpl(List<Board> boards, List<Task> tasks) {
        this.boards = boards;
        this.tasks = tasks;
        this.nextId = 0;
    }
}
