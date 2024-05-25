package com.company.oop.test.menagement.models;

import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.TaskType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BugImplTest {
    public static final String VALID_TITLE_NAME_LENGTH = "C".repeat(100);
    public static final String VALID_DESCRIPTION_NAME_LENGTH = "C".repeat(500);

    @Test
    void constructor_ShouldCreate_Bug_WhenArguments_AreValid() {
        Bug bug = new BugImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH, PriorityType.MEDIUM,
                BugSeverityType.MINOR, "Gosho", List.of("Step1", "Step2", "Step3"));

        assertAll(
                () -> assertEquals(1, bug.getId()),
                () -> assertEquals(VALID_TITLE_NAME_LENGTH, bug.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION_NAME_LENGTH, bug.getDescription()),
                () -> assertEquals(PriorityType.MEDIUM, bug.getPriority()),
                () -> assertEquals(BugSeverityType.MINOR, bug.getSeverity()),
                () -> assertEquals("Gosho", bug.getAssignee()),
                () -> assertEquals(3, bug.getStepsToReproduce().size()),
                () -> assertEquals(TaskType.BUG, bug.getTaskType()),
                () -> assertEquals(0, bug.getComments().size())
        );
    }

    @Test
    void changeAssignee_Should_ThrowException_IfAssignee_IsSame() {
        Bug bug = new BugImpl(1, VALID_TITLE_NAME_LENGTH, VALID_DESCRIPTION_NAME_LENGTH, PriorityType.MEDIUM,
                BugSeverityType.MINOR, "Gosho", List.of("Step1", "Step2", "Step3"));

        assertThrows(DuplicateEntityException.class, () -> bug.changeAssignee("Gosho"));
    }

}