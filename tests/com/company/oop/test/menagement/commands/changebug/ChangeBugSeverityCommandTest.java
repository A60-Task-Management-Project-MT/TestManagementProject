package com.company.oop.test.menagement.commands.changebug;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeBugSeverityCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String VALID_TITLE = "C".repeat(50);
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ChangeBugSeverityCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeBugSeverity_WhenArguments_AreValid() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, BugSeverityType.MINOR, "Gosho", List.of("Step1", "Step2", "Step3"));

        List<String> params = List.of("1", String.valueOf(BugSeverityType.CRITICAL));

        command.execute(params);

        assertEquals(BugSeverityType.CRITICAL, bug.getSeverity());
    }

    @Test
    public void should_ThrowException_WhenNewBugSeverity_IsSame() {
        Bug bug = taskManagementRepository.createBug(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, BugSeverityType.MINOR, "Gosho", List.of("Step1", "Step2", "Step3"));

        List<String> params = List.of("1", String.valueOf(BugSeverityType.MINOR));

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}