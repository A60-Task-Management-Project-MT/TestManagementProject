package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Task;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.units.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamsImpl implements Teams {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_MIN_OR_MAX_LENGTH_ERROR = String.format(
            "Member name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);

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
    public void removeMember(Member member) {
        members.remove(member);
    }

    @Override
    public void addBoard(Board board) {
        boards.add(board);
    }

    @Override
    public void removeBoard(Board board) {
        boards.remove(board);
    }

    @Override
    public String printMembers() {
        StringBuilder builder = new StringBuilder();
        builder.append("~~~ MEMBERS ~~~").append(System.lineSeparator());
        if (members.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE MEMBERS ~~~").append(System.lineSeparator());
        } else {
            for (Member member : members) {
                builder.append(member).append(System.lineSeparator());
            }
            builder.append("~~~ MEMBERS ~~~").append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    @Override
    public String printBoards() {
        StringBuilder builder = new StringBuilder();
        builder.append("~~~ BOARDS ~~~").append(System.lineSeparator());
        if (boards.isEmpty()) {
            builder.append(" ~~~ NO AVAILABLE BOARDS ~~~").append(System.lineSeparator());
        } else {
            for (Board board : boards) {
                builder.append(board).append(System.lineSeparator());
            }
            builder.append("~~~ BOARDS ~~~").append(System.lineSeparator());
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
    public int hashCode() {
        return Objects.hash(name, members, boards);
    }
}
