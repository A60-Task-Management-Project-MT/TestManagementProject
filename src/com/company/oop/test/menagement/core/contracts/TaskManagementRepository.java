package com.company.oop.test.menagement.core.contracts;

import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;

import java.util.List;

public interface TaskManagementRepository {
    List<Task> getTask();
    List<Teams> getTeams();
    Bug createBug(String title, String description, PriorityType priorityType, BugSeverityType severityType,String assignee);
    Story createStory(String title, String description, PriorityType priorityType, StorySizeType storySizeType, String assignee);
    Feedback createFeedback(String title, String description, int rating);
    Task findTaskById(int id);
    boolean teamExist(String teamName);
    boolean boardExist(String boardName);
    boolean memberExist(String memberName);
    void addBoardToTeam(Board board);
    void addMemberToTeam(Member member);
    Teams findTeamByTeamName(String teamName);
    Member findMemberByMemberName(String memberName);
}
