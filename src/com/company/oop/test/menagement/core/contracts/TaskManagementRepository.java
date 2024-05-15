package com.company.oop.test.menagement.core.contracts;

import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;

import java.util.List;

public interface TaskManagementRepository {
    List<Task> getTask();

    List<Teams> getTeams();

    List<Member> getMembers();

    List<Board> getBoards();

    Bug createBug(String title, String description, PriorityType priorityType, BugSeverityType severityType, BugStatusType statusType, String assignee);

    Story createStory(String title, String description, PriorityType priorityType, StorySizeType storySizeType, StoryStatusType statusType, String assignee);

    Feedback createFeedback(String title, String description, int rating);

    Teams createTeam(String name);

    Member createMember(String memberName);

    Board createBoard(String boardName);

    Comment createComment(String author, String content);

    Task findTaskById(int id);

    boolean teamExist(String teamName);

    boolean boardExist(String boardName);

    boolean memberExist(String memberName);

    Teams findTeamByTeamName(String teamName);

    Member findMemberByMemberName(String memberName);

    Board findBoardByBoardName(String boardName);
}
