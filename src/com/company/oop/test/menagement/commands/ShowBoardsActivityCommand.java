package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Board;

import java.util.List;

public class ShowBoardsActivityCommand implements Command {

    private TaskManagementRepository taskManagementRepository;

    public ShowBoardsActivityCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append("~~~ BOARDS ACTIVITY ~~~").append(System.lineSeparator());
        sb.append(count).append(". ");
        for (Board board : taskManagementRepository.getBoards()) {
            sb.append(board.printHistory()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
