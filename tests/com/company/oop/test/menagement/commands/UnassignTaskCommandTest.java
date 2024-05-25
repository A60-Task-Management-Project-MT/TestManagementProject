package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.ElementNotFoundException;
import com.company.oop.test.menagement.models.contracts.Bug;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.enums.PriorityType;
import com.company.oop.test.menagement.models.enums.bug_enums.BugSeverityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnassignTaskCommandTest {
    public static final String VALID_TITLE_LENGTH = "C".repeat(20);
    public static final String VALID_DESCRIPTION_LENGTH = "C".repeat(20);
    public static final String VALID_PERSON_NAME = "C".repeat(8);

    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new UnassignTaskCommand(taskManagementRepository);
    }

    @Test
    void should_Unassign_AllTasks_With_StatusDone() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);
        Bug bug = taskManagementRepository.createBug(VALID_TITLE_LENGTH, VALID_DESCRIPTION_LENGTH,
                PriorityType.MEDIUM, BugSeverityType.MINOR,
                VALID_PERSON_NAME, List.of("Step1", "Step2", "Step3"));
        bug.changeStatus();

        List<String> params = List.of();

        command.execute(params);

        assertEquals(0, member.getTasks().size());
    }

    @Test
    void should_ThrowException_IfThereAre_NoTasks_WithStatusDone() {
        List<String> params = List.of();

        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

}