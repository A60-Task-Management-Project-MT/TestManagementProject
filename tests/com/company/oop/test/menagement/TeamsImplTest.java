package com.company.oop.test.menagement;

import com.company.oop.test.menagement.models.contracts.Board;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Teams;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.BoardImpl;
import com.company.oop.test.menagement.models.MemberImpl;
import com.company.oop.test.menagement.models.TeamsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TeamsImplTest {
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 15;


    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        String validName = "C".repeat(MIN_NAME_LENGTH);

        Teams team = new TeamsImpl(validName);

        Assertions.assertEquals(validName, team.getName());
    }

    @Test
    public void constructor_Should_NotInitializeName_When_ArgumentsAreInValid() {
        String inValidName = "C".repeat(MIN_NAME_LENGTH - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> new TeamsImpl(inValidName));
    }

    @Test
    void addMember_Should_AddMember_When_ArgumentsAreValid() {
        String validMember = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Member member = new MemberImpl(validMember);
        Teams team = new TeamsImpl(validTeam);

        team.addMember(member);

        Assertions.assertEquals(1, team.getMembers().size());
    }

    @Test
    void addBoard_Should_AddBoard_When_ArgumentsAreValid() {
        String validBoard = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validBoard);
        Teams team = new TeamsImpl(validTeam);

        team.addBoard(board);

        Assertions.assertEquals(1, team.getBoards().size());
    }

    @Test
    void addBoard_Should_ThrowException_When_BoardAlreadyExist_InTeam() {
        String validBoard = "C".repeat(MIN_NAME_LENGTH);
        String validTeam = "C".repeat(MAX_NAME_LENGTH);

        Board board = new BoardImpl(validBoard);
        Teams team = new TeamsImpl(validTeam);

        team.addBoard(board);

        Assertions.assertThrows(DuplicateEntityException.class, () -> team.addBoard(board));
    }
}