package com.company.oop.test.menagement.core;

import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.BoardImpl;
import com.company.oop.test.menagement.models.MemberImpl;
import com.company.oop.test.menagement.models.TeamsImpl;
import com.company.oop.test.menagement.models.contracts.*;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagementRepositoryImplTest {
    private static final String VALID_TITLE = "C".repeat(10);
    private static final String VALID_DESCRIPTION = "C".repeat(10);

    private TaskManagementRepository taskManagementRepository;

    @BeforeEach
    void setUp() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
    }

    @Test
    void memberExists_should_returnTrue_when_exists() {
        String member = "Gosho";
        taskManagementRepository.createMember(member);

        Assertions.assertTrue(taskManagementRepository.memberExist(member));
    }

    @Test
    void memberExists_should_returnFalse_when_DoesNot_exists() {
        Assertions.assertFalse(taskManagementRepository.memberExist("Pesho"));
    }

    @Test
    void teamExists_should_returnTrue_when_exists() {
        String team = "GoshoTeam";
        taskManagementRepository.createTeam(team);

        Assertions.assertTrue(taskManagementRepository.teamExist(team));
    }

    @Test
    void teamExists_should_returnFalse_when_DoesNot_exists() {
        Assertions.assertFalse(taskManagementRepository.teamExist("PeshoTeam"));
    }

    @Test
    void findMemberByMemberName_Should_returnMember_WhenExists() {
        String member = "Gosho";
        taskManagementRepository.createMember(member);

        assertAll(
                () -> assertDoesNotThrow(() -> taskManagementRepository.findMemberByMemberName(member)),
                () -> assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findMemberByMemberName("Pesho"))
        );
    }

    @Test
    void findTeamByTeamName_Should_returnTeam_WhenExists() {
        String team = "TeamOne";
        taskManagementRepository.createTeam(team);

        assertAll(
                () -> assertDoesNotThrow(() -> taskManagementRepository.findTeamByTeamName(team)),
                () -> assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findTeamByTeamName("Pesho"))
        );
    }

    @Test
    void findBoardByBoardName_Should_returnBoard_WhenExists() {
        String board = "BoardOne";
        taskManagementRepository.createBoard(board);

        assertAll(
                () -> assertDoesNotThrow(() -> taskManagementRepository.findBoardByBoardName(board)),
                () -> assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findBoardByBoardName("Pesho"))
        );
    }

    @Test
    public void createComment_Should_ReturnTask_When_InputIsValid() {
        Comment testComment = taskManagementRepository.createComment("Pesho", "Neveroqtno");

        assertAll(
                () -> Assertions.assertEquals(testComment.getContent(), "Pesho"),
                () -> Assertions.assertEquals(testComment.getAuthor(), "Neveroqtno"));
    }

    @Test
    public void findTaskById_Should_ThrowException_When_VehicleDoesNotExist() {
        assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findTaskById(1));
    }

    @Test
    public void findBugById_Should_ThrowException_When_VehicleDoesNotExist() {
        assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findBugById(1));
    }

    @Test
    public void findStoryById_Should_ThrowException_When_VehicleDoesNotExist() {
        assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findStoryById(1));
    }

    @Test
    public void findFeedbackById_Should_ThrowException_When_VehicleDoesNotExist() {
        assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findFeedbackById(1));
    }

    @Test
    void createBug_Should_CreateBug_When_Arguments_AreValid() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2", "Step3"));

        assertAll(
                () -> Assertions.assertEquals(bug.getTitle(), VALID_TITLE),
                () -> Assertions.assertEquals(bug.getDescription(), VALID_DESCRIPTION),
                () -> Assertions.assertEquals(bug.getPriority(), PriorityType.MEDIUM),
                () -> Assertions.assertEquals(bug.getSeverity(), BugSeverityType.MINOR),
                () -> Assertions.assertEquals(bug.getAssignee(), "Gosho"),
                () -> Assertions.assertEquals(3, bug.getStepsToReproduce().size()));
    }

    @Test
    void createStory_Should_CreateStory_When_Arguments_AreValid() {
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        assertAll(
                () -> Assertions.assertEquals(story.getTitle(), VALID_TITLE),
                () -> Assertions.assertEquals(story.getDescription(), VALID_DESCRIPTION),
                () -> Assertions.assertEquals(story.getPriority(), PriorityType.MEDIUM),
                () -> Assertions.assertEquals(story.getSize(), StorySizeType.LARGE),
                () -> Assertions.assertEquals(story.getAssignee(), "Pesho"));
    }

    @Test
    void createFeedback_Should_CreateFeedback_When_Arguments_AreValid() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 25);

        assertAll(
                () -> Assertions.assertEquals(feedback.getTitle(), VALID_TITLE),
                () -> Assertions.assertEquals(feedback.getDescription(), VALID_DESCRIPTION),
                () -> Assertions.assertEquals(feedback.getRating(), 25));
    }

    @Test
    public void getTeams_Should_ReturnCopyOfTheCollection() {
        Teams team = new TeamsImpl("TeamOne");
        taskManagementRepository.getTeams().add(team);

        assertEquals(0, taskManagementRepository.getTeams().size());
    }

    @Test
    public void getMembers_Should_ReturnCopyOfTheCollection() {
        Member member = new MemberImpl("Pesho");
        taskManagementRepository.getMembers().add(member);

        assertEquals(0, taskManagementRepository.getMembers().size());
    }

    @Test
    void findTeamByBoardName_Should_returnTeam_WhenExists() {
        Board board = new BoardImpl("BoardOne");
        Teams team = taskManagementRepository.createTeam("TeamOne");
        Board board2 = new BoardImpl("BoardTwo");
        team.addBoard(board);

        assertAll(
                () -> assertDoesNotThrow(() -> taskManagementRepository.findTeamByBoardName(board)),
                () -> assertThrows(ElementNotFoundException.class, () -> taskManagementRepository.findTeamByBoardName(board2))
        );
    }

    @Test
    void filterTasksWithAssignee_Should_returnTasks_OnlyWithAssignee() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 25);
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2", "Step3"));
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        List<Task> tasks = new ArrayList<>();
        tasks.add(feedback);
        tasks.add(story);
        tasks.add(bug);
        List<Task> filteredTasks = taskManagementRepository.filterTasksWithAssignee(tasks);

        assertEquals(2, filteredTasks.size());
    }

    @Test
    void unassignTask_Should_UnassignTask_IfMember_HaveThisTask() {
        Member member = taskManagementRepository.createMember("Gosho");
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        member.assignTask(story);
        assertEquals(1, member.getTasks().size());
        taskManagementRepository.unassignTask(story);
        assertEquals(0, member.getTasks().size());
    }
}