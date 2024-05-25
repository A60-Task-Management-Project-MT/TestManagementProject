package com.company.oop.test.menagement.commands;

import com.company.oop.test.menagement.commands.contracts.Command;
import com.company.oop.test.menagement.core.TaskManagementRepositoryImpl;
import com.company.oop.test.menagement.core.contracts.TaskManagementRepository;
import com.company.oop.test.menagement.exceptions.DuplicateEntityException;
import com.company.oop.test.menagement.models.contracts.Member;
import com.company.oop.test.menagement.models.contracts.Team;
import com.company.oop.test.menagement.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddPersonToTeamCommandTest {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String VALID_TEAM_NAME = "C".repeat(10);
    public static final String VALID_PERSON_NAME = "C".repeat(11);

    private TaskManagementRepository taskManagementRepository;
    private Command command;


    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        command = new AddPersonToTeamCommand(taskManagementRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    void should_AddPerson_ToTeam_When_ArgumentsAreValid() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);
        Team team = taskManagementRepository.createTeam(VALID_TEAM_NAME);

        List<String> params = List.of(VALID_PERSON_NAME, VALID_TEAM_NAME);
        command.execute(params);

        assertEquals(1, team.getMembers().size());
    }

    @Test
    void should_ThrowException_WhenMember_AlreadyPartOf_ThisTeam() {
        Member member = taskManagementRepository.createMember(VALID_PERSON_NAME);
        Team team = taskManagementRepository.createTeam(VALID_TEAM_NAME);
        team.addMember(member);

        List<String> params = List.of(VALID_PERSON_NAME, VALID_TEAM_NAME);

        assertThrows(DuplicateEntityException.class, () -> command.execute(params));
    }

}