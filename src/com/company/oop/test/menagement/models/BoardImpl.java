package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Board name must be between %s and %s characters long!",
            BOARD_NAME_MIN_LENGTH,
            BOARD_NAME_MAX_LENGTH);

    private String boardName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public BoardImpl(String boardName) {
        setBoardName(boardName);
        tasks = new ArrayList<>();
        histories = new ArrayList<>();
    }

    private void setBoardName(String boardName) {
        ValidationHelpers.validateStringLength(boardName,
                BOARD_NAME_MIN_LENGTH,
                BOARD_NAME_MAX_LENGTH,
                NAME_MIN_OR_MAX_LENGTH_ERROR);
        this.boardName = boardName;
    }

    @Override
    public String getBoardName() {
        return boardName;
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void addHistory(ActivityHistory activity) {
        histories.add(activity);
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return histories;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }
}
