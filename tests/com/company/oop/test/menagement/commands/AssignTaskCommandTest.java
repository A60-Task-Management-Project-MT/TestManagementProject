package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignTaskCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String VALID_TITLE_LENGTH = "C".repeat(20);
    public static final String VALID_DESCRIPTION_LENGTH = "C".repeat(20);
    public static final String VALID_PERSON_NAME = "C".repeat(8);

    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new AssignTaskCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_TryToAssignTask_WhoAlreadyGotIt() {
        Story story = taskManagementRepository.createStory(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, VALID_PERSON_NAME);
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Story", "Large", VALID_PERSON_NAME);

        assertThrows(DuplicateEntityException.class, () -> command.execute(params));
    }

    @Test
    public void should_AssignStoryTaskToPerson_When_ArgumentsAreValid() {
        Story story = taskManagementRepository.createStory(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Story", "Large", VALID_PERSON_NAME);
        command.execute(params);

        assertEquals(VALID_PERSON_NAME, story.getAssignee());
    }

    @Test
    public void should_AssignBugTaskToPerson_When_ArgumentsAreValid() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2", "Step3"));
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Bug", "Minor", VALID_PERSON_NAME);
        command.execute(params);

        assertEquals(VALID_PERSON_NAME, bug.getAssignee());
    }

    @Test
    public void should_ThrowException_When_StoriesList_With_ThisSpecification_IsEmpty() {
        Story story = taskManagementRepository.createStory(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Story", "Small", VALID_PERSON_NAME);

        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_StoriesList_IsEmpty() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Story", "Small", VALID_PERSON_NAME);

        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_BugsList_IsEmpty() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Bug", "Small", VALID_PERSON_NAME);

        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_BugsList_With_ThisSpecification_IsEmpty() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, BugSeverityType.MINOR,
                "Gosho", List.of("Step1", "Step2", "Step3"));
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Bug", "Small", VALID_PERSON_NAME);

        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_TryToAssignTask_ToFeedback() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);

        List<String> params = List.of("Feedback", "Large", VALID_PERSON_NAME);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}