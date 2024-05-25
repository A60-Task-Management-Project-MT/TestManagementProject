package com.company.oop.test.menagement.commands.changestory;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Story;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.story_enums.StorySizeType;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChangeStorySizeCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String VALID_TITLE = "C".repeat(50);
    public static final String VALID_DESCRIPTION = "C".repeat(50);

    private TaskManagementRepository taskManagementRepository;
    private Command command;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new ChangeStorySizeCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ChangeStorySize_WhenArguments_AreValid() {
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        List<String> params = List.of("1", String.valueOf(StorySizeType.MEDIUM));

        command.execute(params);

        assertEquals(StorySizeType.MEDIUM, story.getSize());
    }

    @Test
    public void should_ThrowException_WhenNewStorySize_IsSame() {
        Story story = taskManagementRepository.createStory(VALID_TITLE, VALID_DESCRIPTION,
                PriorityType.MEDIUM, StorySizeType.LARGE, "Pesho");

        List<String> params = List.of("1", String.valueOf(StorySizeType.LARGE));

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}