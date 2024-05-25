package com.company.oop.test.menagement.commands.changefeedback;

import com.company.oop.test.menagement.commands.changestory.ChangeStoryPriorityCommand;
import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeFeedbackRatingCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String VALID_TITLE = "C".repeat(50);
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ChangeFeedbackRatingCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeFeedbackRating_WhenArguments_AreValid() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 25);

        List<String> params = List.of("1", "30");

        command.execute(params);

        assertEquals(30, feedback.getRating());
    }

    @Test
    public void should_ThrowException_WhenRating_IsSame() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, 25);

        List<String> params = List.of("1", "25");

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}