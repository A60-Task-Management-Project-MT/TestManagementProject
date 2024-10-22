package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardImpl implements Board {
    public static final int BOARD_NAME_MIN_LENGTH = 5;
    public static final int BOARD_NAME_MAX_LENGTH = 10;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Board name must be between %s and %s characters long!",
            BOARD_NAME_MIN_LENGTH,
            BOARD_NAME_MAX_LENGTH);
    public static final String TASK_ADD_TO_BOARD_ERROR_MESSAGE = "Task already added to board %s!";
    public static final String NEW_TASK_ADDED_MESSAGE = "A new %s task was added to board %s.";
    public static final String NEW_BOARD_CREATION_MESSAGE = "A new board with name %s was created!";

    private String boardName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public BoardImpl(String boardName) {
        setBoardName(boardName);
        tasks = new ArrayList<>();
        histories = new ArrayList<>();

        createNewHistory(String.format(NEW_BOARD_CREATION_MESSAGE, boardName));
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
    public String getBoardName() {
        return boardName;
    }

    @Override
    public void addTask(Task task) {
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == task.getId());
        if (!exists) {
            tasks.add(task);
            createNewHistory(String.format(NEW_TASK_ADDED_MESSAGE, task.getTaskType(), boardName));
        } else {
            throw new DuplicateEntityException(String.format(TASK_ADD_TO_BOARD_ERROR_MESSAGE, getBoardName()));
        }
    }

    @Override
    public void createNewHistory(String activity) {
        histories.add(new ActivityHistoryImpl(activity));
    }

    @Override
    public String displayFullHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("~~~ BOARD %s HISTORY ~~~", getBoardName().toUpperCase())).append(System.lineSeparator());

        for (ActivityHistory activityHistory : histories) {
            builder.append(activityHistory.displayHistory()).append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    private void setBoardName(String boardName) {
        ValidationHelpers.validateStringLength(boardName,
                BOARD_NAME_MIN_LENGTH,
                BOARD_NAME_MAX_LENGTH,
                NAME_MIN_OR_MAX_LENGTH_ERROR);
        this.boardName = boardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardImpl board = (BoardImpl) o;
        return Objects.equals(boardName, board.boardName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("~~~ BOARD %s TASKS ~~~", boardName.toUpperCase())).append(System.lineSeparator());
        if (tasks.isEmpty()) {
            throw new ElementNotFoundException(String.format("Board with name %s is empty.", boardName));
        }
        int count = 1;
        for (Task task : tasks ) {
            sb.append(count).append(". ");
            sb.append(task.viewInfo()).append(System.lineSeparator());
            count ++;
        }
        return sb.toString().trim();
    }
}
