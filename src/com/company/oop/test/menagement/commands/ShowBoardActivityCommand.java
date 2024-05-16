package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private TaskManagementRepository taskManagementRepository;

    public ShowBoardActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        Board board = taskManagementRepository.findBoardByBoardName(boardName);

        return showBoardActivity(board);
    }

    private String showBoardActivity(Board board) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(String.format("~~~ BOARD %s ACTIVITY ~~~",board.getBoardName().toUpperCase())).append(System.lineSeparator());
        for (Board currentBoard : taskManagementRepository.getBoards()) {
            sb.append(count).append(". ");
            sb.append(currentBoard.printHistory()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
