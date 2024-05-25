package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TeamImplTest {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 15;


    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Team team = new TeamImpl(validName);

        Assertions.assertEquals(validName, team.getName());
    }

    @Test
    public void constructor_Should_NotInitializeName_When_ArgumentsAreInvalid() {
        String inValidName = "C".repeat(MIN_NAME_LENGTH - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamImpl(inValidName));
    }

    @Test
    void addMember_Should_AddMember_When_ArgumentsAreValid() {
        String validMember = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Member member = new MemberImpl(validMember);
        Team team = new TeamImpl(validTeam);

        team.addMember(member);

        Assertions.assertEquals(1, team.getMembers().size());
    }

    @Test
    void addBoard_Should_AddBoard_When_ArgumentsAreValid() {
        String validBoard = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validBoard);
        Team team = new TeamImpl(validTeam);

        team.addBoard(board);

        Assertions.assertEquals(1, team.getBoards().size());
    }

    @Test
    void addBoard_Should_ThrowException_When_BoardAlreadyExist_InTeam() {
        String validBoard = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validBoard);
        Team team = new TeamImpl(validTeam);

        team.addBoard(board);

        Assertions.assertThrows(DuplicateEntityException.class, () -> team.addBoard(board));
    }

    @Test
    public void getBoards_Should_ReturnCopyOfTheCollection() {
        String validBoard = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validBoard);
        Team team = new TeamImpl(validTeam);

        team.getBoards().add(board);

        Assertions.assertEquals(0, team.getBoards().size());
    }

    @Test
    void printMembers_Should_PrintMembers() {
        String validTeam = "C".repeat(MAX_NAME_LENGTH);
        Team team = new TeamImpl(validTeam);
        Member member = new MemberImpl("Gosho");
        team.addMember(member);
        String result = team.printMembers();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, team.printMembers().split("\n").length);
    }

    @Test
    void printBoards_Should_PrintBoards() {
        String validTeam = "C".repeat(MAX_NAME_LENGTH);
        Team team = new TeamImpl(validTeam);
        Board board = new BoardImpl("Gosho");
        team.addBoard(board);
        String result = team.printBoards();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, team.printBoards().split("\n").length);
    }

    @Test
    void equalsMethod_Should_ReturnFalse_When_TwoDifferentTeams_AreMade() {
        Team team = new TeamImpl("TeamOne");
        Team team1 = new TeamImpl("TeamTwo");

        Assertions.assertNotEquals(team1, team);
    }

    @Test
    void viewInfo_Should_PrintFullHistoryInfo() {
        String validTeam = "C".repeat(MAX_NAME_LENGTH);
        Team team = new TeamImpl(validTeam);
        Board board = new BoardImpl("Gosho");
        Member member = new MemberImpl("Pesho");
        team.addBoard(board);
        team.addMember(member);
        String result = team.viewInfo();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(6, team.viewInfo().split("\n").length);
    }
}