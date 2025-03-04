package com.company.oop.test.menagement.core.contracts;

import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.contracts.*;

import java.util.List;

public interface TaskManagementRepository {
    List<Task> getTasks();

    List<Team> getTeams();

    List<Member> getMembers();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    List<Bug> getBugs();

    Bug createBug(String title, String description, PriorityType priorityType, BugSeverityType severityType, String assignee, List<String> steps);

    Story createStory(String title, String description, PriorityType priorityType, StorySizeType storySizeType, String assignee);

    Feedback createFeedback(String title, String description, int rating);

    Team createTeam(String name);

    Member createMember(String memberName);

    Board createBoard(String boardName);

    Comment createComment(String author, String content);

    boolean teamExist(String teamName);

    boolean memberExist(String memberName);

    Team findTeamByTeamName(String teamName);

    Member findMemberByMemberName(String memberName);

    Board findBoardByBoardName(String boardName);

    void unassignTask(Task task);

    Task findTaskById(int id);

    Feedback findFeedbackById(int id);

    Bug findBugById(int id);

    Story findStoryById(int id);

    List<Task> filterTasksWithAssignee(List<Task> tasks);

    Team findTeamByBoardName(Board board);
}
