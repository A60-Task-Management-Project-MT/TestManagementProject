package com.company.oop.test.menagement.models.contracts;

import java.util.List;

public interface Teams {

    List<Member> getMembers();

    List<Board> getBoards();

    void addMember(Member member);

    void removeMember(Member member);

    void addBoard(Board board);

    void removeBoard(Board board);
}
