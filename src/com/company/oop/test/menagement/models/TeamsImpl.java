package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamsImpl implements Teams {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Team name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    public static final String BOARD_EXIST_ERROR_MESSAGE = "Board with name %s already exist in team %s!";

    private String name;
    private List<Member> members;
    private List<Board> boards;

    public TeamsImpl(String name) {
        setName(name);
        members = new ArrayList<>();
        boards = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_MIN_OR_MAX_LENGTH_ERROR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
    }

    @Override
    public void addBoard(Board board) {
        if (boards.contains(board)) {
            throw new DuplicateEntityException(String.format(BOARD_EXIST_ERROR_MESSAGE, board.getBoardName(), name));
        }
        boards.add(board);
    }

    @Override
    public String printMembers() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        builder.append("~~~ MEMBERS ~~~").append(System.lineSeparator());
        if (members.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE MEMBERS ~~~").append(System.lineSeparator());
        } else {
            for (Member member : members) {
                builder.append(count).append(". ");
                builder.append(member.getMemberName()).append(System.lineSeparator());
                count++;
            }
        }
        return builder.toString().trim();
    }

    @Override
    public String printBoards()
    {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        builder.append("~~~ BOARDS ~~~").append(System.lineSeparator());
        if (boards.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE BOARDS ~~~").append(System.lineSeparator());
        } else {
            for (Board board : boards) {
                builder.append(count).append(". ");
                builder.append(board.getBoardName()).append(System.lineSeparator());
                count++;
            }
        }
        return builder.toString().trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamsImpl teams = (TeamsImpl) o;
        return Objects.equals(name, teams.name) && Objects.equals(members, teams.members)
                && Objects.equals(boards, teams.boards);
    }

    @Override
    public String viewInfo() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        sb.append(String.format("~~~ TEAM %s BOARDS ACTIVITY ~~~", getName().toUpperCase())).append(System.lineSeparator());
        if (getBoards().isEmpty()) {
            sb.append("~~~ NO AVAILABLE BOARDS ACTIVITY ~~~").append(System.lineSeparator());
        }
        for (Board board : boards) {
            sb.append(count).append(". ");
            sb.append(board.displayFullHistory()).append(System.lineSeparator());
            count++;
        }
        count = 1;
        sb.append(String.format("~~~ TEAM %s MEMBERS ACTIVITY ~~~", getName().toUpperCase())).append(System.lineSeparator());
        if (getMembers().isEmpty()) {
            sb.append("~~~ NO AVAILABLE MEMBERS ACTIVITY ~~~").append(System.lineSeparator());
        }
        for (Member member : members) {
            sb.append(count).append(". ");
            sb.append(member.displayFullHistory()).append(System.lineSeparator());
            count++;
        }
        return sb.toString().trim();
    }
}
