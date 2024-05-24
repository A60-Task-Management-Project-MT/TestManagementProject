package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.models.contracts.Feedback;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class AddCommentToTaskCommandTest {
    public static final String VALID_TITLE_LENGTH = "C".repeat(10);
    public static final String VALID_DESCRIPTION_LENGTH = "C".repeat(10);
    public static final String VALID_CONTENT = "C".repeat(50);
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new AddCommentToTaskCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_Create_When_InputIsValid() {
        Feedback feedback = taskManagementRepository.createFeedback(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH, 25);
        Member member = taskManagementRepository.createMember("Gosho");
        List<String> params = List.of(VALID_CONTENT, "Gosho", "1");

        command.execute(params);

        Assertions.assertEquals(1, feedback.getComments().size());
    }
}