package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.ActivityHistory;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Task;
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

    private String boardName;
    private List<Task> tasks;
    private List<ActivityHistory> histories;

    public BoardImpl(String boardName) {
        setBoardName(boardName);
        tasks = new ArrayList<>();
        histories = new ArrayList<>();

        createNewHistory(String.format("A new board with name %s was created!", boardName));
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
        boolean exists = tasks.stream().anyMatch(t -> t.getId() == task.getId());
        if (!exists) {
            tasks.add(task);
            createNewHistory(String.format("A new %s task was added to board %s.", task.getTaskType(), boardName));
        } else {
            throw new DuplicateEntityException(String.format("Task already added to board %s!", getBoardName()));
        }
    }

    @Override
    public void createNewHistory(String activity) {
        histories.add(new ActivityHistoryImpl(activity));
    }

    @Override
    public List<ActivityHistory> getHistory() {
        return histories;
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public String printHistory() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("~~~ BOARD %s HISTORY ~~~", getBoardName().toUpperCase())).append(System.lineSeparator());
        if (histories.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE HISTORY ~~~").append(System.lineSeparator());
        } else {
            for (ActivityHistory activityHistory : histories) {
                builder.append(activityHistory.viewInfo()).append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    @Override
    public String printTasks() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardImpl board = (BoardImpl) o;
        return Objects.equals(boardName, board.boardName) &&
                Objects.equals(tasks, board.tasks) && Objects.equals(histories, board.histories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardName, tasks, histories);
    }
}
