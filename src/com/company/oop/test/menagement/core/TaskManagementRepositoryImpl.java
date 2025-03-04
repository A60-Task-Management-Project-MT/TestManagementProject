package com.company.oop.test.menagement.core;

import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.*;
import com.company.oop.test.menagement.models.contracts.*;


import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String TEAM_EXIST_ERROR = "Team %s does not exist!";
    private int nextId;
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();

    public TaskManagementRepositoryImpl() {
        this.nextId = 0;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> result = new ArrayList<>();
        result.addAll(bugs);
        result.addAll(feedbacks);
        result.addAll(stories);

        return result;
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public Bug createBug(String title, String description, PriorityType priorityType, BugSeverityType severityType, String assignee, List<String> steps) {
        Bug bug = new BugImpl(++nextId, title, description, priorityType, severityType, assignee, steps);
        bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, PriorityType priorityType, StorySizeType storySizeType, String assignee) {
        Story story = new StoryImpl(++nextId, title, description, priorityType, storySizeType, assignee);
        stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }

    @Override
    public Member createMember(String memberName) {
        Member member = new MemberImpl(memberName);
        members.add(member);
        return member;
    }

    @Override
    public Board createBoard(String boardName) {
        Board board = new BoardImpl(boardName);
        boards.add(board);
        return board;
    }

    @Override
    public Comment createComment(String author, String content) {
        return new CommentImpl(content, author);
    }

    @Override
    public boolean teamExist(String teamName) {
        boolean exist = false;

        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    @Override
    public boolean memberExist(String memberName) {
        boolean exist = false;

        for (Member member : members) {
            if (member.getMemberName().equalsIgnoreCase(memberName)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    @Override
    public Team findTeamByTeamName(String teamName) {
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        throw new ElementNotFoundException(String.format(TEAM_EXIST_ERROR, teamName));
    }

    @Override
    public Member findMemberByMemberName(String memberName) {
        for (Member member : members) {
            if(member.getMemberName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }
        throw new ElementNotFoundException(String.format("Member %s does not exist!", memberName));
    }

    @Override
    public Board findBoardByBoardName(String boardName) {
        for (Board board : boards) {
            if (board.getBoardName().equalsIgnoreCase(boardName)) {
                return board;
            }
        }
        throw new ElementNotFoundException(String.format("Board %s does not exist!", boardName));
    }

    @Override
    public void unassignTask(Task task) {
        for (Member member : members) {
            if (member.getTasks().contains(task)) {
                member.unassignTask(task);
                return;
            }
        }
    }

    @Override
    public Task findTaskById(int id) {
        return findElementById(id, getTasks());
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return findElementById(id, getFeedbacks());
    }

    @Override
    public Bug findBugById(int id) {
        return findElementById(id, getBugs());
    }

    @Override
    public Story findStoryById(int id) {
        return findElementById(id, getStories());
    }

    private <T extends Task> T findElementById(int id, List<T> elements) {
        for (T task : elements) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new ElementNotFoundException(String.format("No task find with id %d", id));
    }

    @Override
    public List<Task> filterTasksWithAssignee(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> (task instanceof Bug && ((Bug) task).getAssignee() != null) ||
                        (task instanceof Story && ((Story) task).getAssignee() != null))
                .toList();
    }

    @Override
    public Team findTeamByBoardName(Board board) {
        for (Team team : getTeams()) {
            if (team.getBoards().contains(board)) {
                return team;
            }
        }
        throw new ElementNotFoundException("This board %s is not part of a team!");
    }
}
