package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugStatusType;
import com.company.oop.test.menagement.models.enums.feedback_enums.FeedbackStatusType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.models.enums.story_enums.StoryStatusType;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeTaskStatusTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String VALID_TITLE = "C".repeat(50);
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ChangeTaskStatus(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeBugStatus_When_ArgumentsAreValid() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION, PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2"));

        List<String> params = List.of("1");
        command.execute(params);

        assertEquals(BugStatusType.DONE, BugStatusType.DONE);
    }

    @Test
    public void should_ThrowException_WhenAlready_AtFinalBugStatus() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION, PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2"));

        List<String> params = List.of("1");
        command.execute(params);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeStoryStatus_When_ArgumentsAreValid() {
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        List<String> params = List.of("1");
        command.execute(params);

        assertEquals(StoryStatusType.IN_PROGRESS, StoryStatusType.IN_PROGRESS);
    }

    @Test
    public void should_ThrowException_WhenAlready_AtFinalStoryStatus() {
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        List<String> params = List.of("1");
        command.execute(params);
        command.execute(params);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeFeedbackStatus_When_ArgumentsAreValid() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 22);

        List<String> params = List.of("1");
        command.execute(params);

        assertEquals(FeedbackStatusType.UNSCHEDULED, FeedbackStatusType.UNSCHEDULED);
    }

    @Test
    public void should_ThrowException_WhenAlready_AtFinalFeedbackStatus() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 22);

        List<String> params = List.of("1");
        command.execute(params);
        command.execute(params);
        command.execute(params);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}