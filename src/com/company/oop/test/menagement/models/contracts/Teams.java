package com.company.oop.test.menagement.models.contracts;

import java.util.List;

public interface Teams extends Printable {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void addBoard(Board board);

    String printMembers();

    String printBoards();
}
